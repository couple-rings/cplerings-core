package com.cplerings.core.infrastructure.datasource.account;

import static com.querydsl.jpa.JPAExpressions.select;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.cplerings.core.application.account.datasource.GetDesignStaffsDataSource;
import com.cplerings.core.application.account.datasource.GetRandomStaffDataSource;
import com.cplerings.core.application.account.datasource.RegisterCustomerDataSource;
import com.cplerings.core.application.account.datasource.RequestResetPasswordDataSource;
import com.cplerings.core.application.account.datasource.ResetPasswordDataSource;
import com.cplerings.core.application.account.datasource.VerifyCustomerDataSource;
import com.cplerings.core.application.account.datasource.ViewAccountDataSource;
import com.cplerings.core.application.account.datasource.ViewCurrentProfileDataSource;
import com.cplerings.core.application.account.datasource.ViewJewelersUseDataSource;
import com.cplerings.core.application.account.datasource.ViewTransportersDataSource;
import com.cplerings.core.application.account.datasource.ViewUsersDataSource;
import com.cplerings.core.application.account.datasource.result.DesignStaffsResult;
import com.cplerings.core.application.account.datasource.result.Jewelers;
import com.cplerings.core.application.account.datasource.result.Transporters;
import com.cplerings.core.application.account.datasource.result.Users;
import com.cplerings.core.application.account.input.GetDesignStaffsInput;
import com.cplerings.core.application.account.input.ViewJewelersInput;
import com.cplerings.core.application.account.input.ViewTransportersInput;
import com.cplerings.core.application.account.output.result.JewelersOutputResult;
import com.cplerings.core.application.shared.entity.account.ADesignStaff;
import com.cplerings.core.application.shared.entity.account.AJeweler;
import com.cplerings.core.application.shared.entity.account.ATransporter;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountPasswordReset;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.account.AccountVerification;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.account.QAccountPasswordReset;
import com.cplerings.core.domain.account.QAccountVerification;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.domain.account.StaffPosition;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.CustomRequestStatus;
import com.cplerings.core.domain.design.request.QCustomRequest;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.order.QCustomOrder;
import com.cplerings.core.domain.order.QTransportationOrder;
import com.cplerings.core.domain.order.TransportStatus;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.AccountPasswordResetRepository;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.AccountVerificationRepository;
import com.cplerings.core.infrastructure.repository.SpouseAccountRepository;
import com.querydsl.core.types.dsl.Expressions;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class SharedAccountDataSource extends AbstractDataSource
        implements RegisterCustomerDataSource, VerifyCustomerDataSource, RequestResetPasswordDataSource,
        ResetPasswordDataSource, ViewAccountDataSource, ViewCurrentProfileDataSource, ViewTransportersDataSource,
        ViewJewelersUseDataSource, ViewUsersDataSource, GetRandomStaffDataSource, GetDesignStaffsDataSource {

    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QAccountVerification Q_ACCOUNT_VERIFICATION = QAccountVerification.accountVerification;
    private static final QAccountPasswordReset Q_ACCOUNT_PASSWORD_RESET = QAccountPasswordReset.accountPasswordReset;
    private static final QCustomRequest Q_CUSTOM_REQUEST = QCustomRequest.customRequest;
    private static final QCustomOrder Q_CUSTOM_ORDER = QCustomOrder.customOrder;
    private static final QTransportationOrder Q_TRANSPORTATION_ORDER = QTransportationOrder.transportationOrder;

    private final AccountRepository accountRepository;
    private final AccountVerificationRepository accountVerificationRepository;
    private final AccountPasswordResetRepository accountPasswordResetRepository;
    private final SpouseAccountRepository spouseAccountRepository;

    @Override
    public boolean emailIsNew(String email) {
        return !accountRepository.existsByEmail(email);
    }

    @Override
    public boolean usernameIsNew(String username) {
        return !accountRepository.existsByUsername(username);
    }

    @Override
    public Account save(Account account) {
        updateAuditor(account);
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccountByEmailWithMostRecentVerificationCode(String email) {
        final QAccountVerification accountVerificationSubQuery = new QAccountVerification("accountVerificationSubQuery");
        return Optional.ofNullable(createQuery().select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .leftJoin(Q_ACCOUNT.verification, Q_ACCOUNT_VERIFICATION)
                .on(Q_ACCOUNT_VERIFICATION.account.eq(Q_ACCOUNT)
                        .and(Q_ACCOUNT_VERIFICATION.id.in(select(accountVerificationSubQuery.id)
                                .from(accountVerificationSubQuery)
                                .orderBy(accountVerificationSubQuery.id.desc())
                                .limit(1))))
                .fetchJoin()
                .where(Q_ACCOUNT.email.eq(email))
                .fetchFirst());
    }

    @Override
    public void saveAccountVerification(AccountVerification accountVerification) {
        updateAuditor(accountVerification);
        accountVerificationRepository.save(accountVerification);
    }

    @Override
    public Account saveAccount(Account account) {
        return save(account);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public void save(AccountPasswordReset accountPasswordReset) {
        updateAuditor(accountPasswordReset);
        accountPasswordResetRepository.save(accountPasswordReset);
    }

    @Override
    public Optional<Account> findAccountByEmailWithMostRecentResetPasswordOTP(String email) {
        final QAccountPasswordReset accountPasswordResetSubQuery = new QAccountPasswordReset("accountPasswordResetSubQuery");
        return Optional.ofNullable(createQuery().select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .leftJoin(Q_ACCOUNT.passwordResets, Q_ACCOUNT_PASSWORD_RESET)
                .on(Q_ACCOUNT_PASSWORD_RESET.id.in(select(accountPasswordResetSubQuery.id)
                        .from(accountPasswordResetSubQuery)
                        .orderBy(accountPasswordResetSubQuery.id.desc())
                        .limit(1)))
                .fetchJoin()
                .where(Q_ACCOUNT.email.eq(email))
                .fetchFirst());
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return Optional.ofNullable(createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.eq(id))
                .fetchOne());
    }

    @Override
    public boolean accountHasSpouse(Long accountId) {
        return spouseAccountRepository.existsByCustomerId(accountId);
    }

    @Override
    public Optional<Account> findAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Transporters getTransporters(ViewTransportersInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<Account> query = createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.branch.id.eq(input.getBranchId())
                        .and(Q_ACCOUNT.role.eq(Role.TRANSPORTER)));
        long count = query.distinct().fetchCount();
        List<Account> transporters = query.limit(input.getPageSize()).offset(offset).fetch();
        return Transporters.builder()
                .transporters(transporters)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    @Override
    public Long calculateNoOfHandleTransportationOrders(ATransporter transporter) {
        BlazeJPAQuery<TransportationOrder> query = createQuery()
                .select(Q_TRANSPORTATION_ORDER)
                .from(Q_TRANSPORTATION_ORDER)
                .where(Q_TRANSPORTATION_ORDER.transporter.id.eq(transporter.getId())
                        .and(Q_TRANSPORTATION_ORDER.status.eq(TransportStatus.WAITING))
                        .or(Q_TRANSPORTATION_ORDER.status.eq(TransportStatus.ON_GOING))
                        .or(Q_TRANSPORTATION_ORDER.status.eq(TransportStatus.DELIVERING))
                        .or(Q_TRANSPORTATION_ORDER.status.eq(TransportStatus.REDELIVERING)));
        return query.distinct().fetchCount();
    }

    @Override
    public Jewelers getJewelers(ViewJewelersInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<Account> query = createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.branch.id.eq(input.getBranchId())
                        .and(Q_ACCOUNT.role.eq(Role.JEWELER)));
        long count = query.distinct().fetchCount();
        List<Account> jewelers = query.limit(input.getPageSize()).offset(offset).fetch();
        return Jewelers.builder()
                .jewelers(jewelers)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    @Override
    public Long calculateNoOfHandleCustomOrder(AJeweler jeweler) {
        BlazeJPAQuery<CustomOrder> query = createQuery()
                .select(Q_CUSTOM_ORDER)
                .from(Q_CUSTOM_ORDER)
                .where(Q_CUSTOM_ORDER.jeweler.id.eq(jeweler.getId())
                        .and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.IN_PROGRESS)));
        return query.distinct().fetchCount();
    }

    @Override
    public Users getUsers(Collection<Long> userIds) {
        List<Account> users = createQuery().select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.in(userIds))
                .fetch();
        return Users.builder().users(users).build();
    }

    @Override
    public Optional<Account> getRandomStaff() {
        return Optional.ofNullable(createQuery().select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.role.eq(Role.STAFF)
                        .and(Q_ACCOUNT.status.eq(AccountStatus.ACTIVE))
                        .and(Q_ACCOUNT.state.eq(State.ACTIVE)))
                .orderBy(Expressions.numberTemplate(Double.class, "function('random')").asc())
                .fetchFirst());
    }

    @Override
    public DesignStaffsResult getDesignStaffs(GetDesignStaffsInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<Account> query = createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.branch.id.eq(input.getBranchId())
                        .and(Q_ACCOUNT.role.eq(Role.STAFF))
                        .and(Q_ACCOUNT.staffPosition.eq(StaffPosition.DESIGNER)));
        long count = query.distinct().fetchCount();
        List<Account> staffs = query.limit(input.getPageSize()).offset(offset).fetch();
        return DesignStaffsResult.builder()
                .staffs(staffs)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    @Override
    public Long calculateNoOfHandleCustomRequest(ADesignStaff staff) {
        BlazeJPAQuery<CustomRequest> query = createQuery()
                .select(Q_CUSTOM_REQUEST)
                .from(Q_CUSTOM_REQUEST)
                .where(Q_CUSTOM_REQUEST.staff.id.eq(staff.getId())
                        .and(Q_CUSTOM_REQUEST.status.eq(CustomRequestStatus.APPROVED)));
        return query.distinct().fetchCount();
    }
}
