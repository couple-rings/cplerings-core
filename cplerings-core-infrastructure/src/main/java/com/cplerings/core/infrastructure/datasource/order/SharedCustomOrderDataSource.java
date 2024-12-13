package com.cplerings.core.infrastructure.datasource.order;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.cplerings.core.application.order.datasource.AssignJewelerToCustomOrderDataSource;
import com.cplerings.core.application.order.datasource.CancelStandardOrderDataSource;
import com.cplerings.core.application.order.datasource.CompleteOrderDataSource;
import com.cplerings.core.application.order.datasource.CreateStandardOrderDataSource;
import com.cplerings.core.application.order.datasource.GetCustomOrderByOrderNoDataSource;
import com.cplerings.core.application.order.datasource.GetStandardOrderByOrderNoDataSource;
import com.cplerings.core.application.order.datasource.PayStandardOrderDataSource;
import com.cplerings.core.application.order.datasource.ProcessPayStandardOrderDataSource;
import com.cplerings.core.application.order.datasource.RefundCustomOrderDataSource;
import com.cplerings.core.application.order.datasource.RefundStandardOrderDataSource;
import com.cplerings.core.application.order.datasource.ViewCustomOrderDataSource;
import com.cplerings.core.application.order.datasource.ViewCustomOrdersDataSource;
import com.cplerings.core.application.order.datasource.ViewRefundOrdersDataSource;
import com.cplerings.core.application.order.datasource.ViewStandardOrderDataSource;
import com.cplerings.core.application.order.datasource.ViewStandardOrdersDataSource;
import com.cplerings.core.application.order.datasource.data.JewelrySearchInfo;
import com.cplerings.core.application.order.datasource.result.CustomOrders;
import com.cplerings.core.application.order.datasource.result.Refunds;
import com.cplerings.core.application.order.datasource.result.StandardOrders;
import com.cplerings.core.application.order.input.ViewCustomOrdersInput;
import com.cplerings.core.application.order.input.ViewRefundOrdersInput;
import com.cplerings.core.application.order.input.ViewStandardOrdersInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.domain.address.QTransportationAddress;
import com.cplerings.core.domain.address.TransportationAddress;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.file.QImage;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.jewelry.JewelryStatus;
import com.cplerings.core.domain.jewelry.QJewelry;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderHistory;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.order.QCustomOrder;
import com.cplerings.core.domain.order.QStandardOrder;
import com.cplerings.core.domain.order.QStandardOrderItem;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderHistory;
import com.cplerings.core.domain.order.StandardOrderItem;
import com.cplerings.core.domain.order.StandardOrderStatus;
import com.cplerings.core.domain.order.TransportOrderHistory;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.refund.QRefund;
import com.cplerings.core.domain.refund.Refund;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingDiamond;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.domain.spouse.Agreement;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.AgreementRepository;
import com.cplerings.core.infrastructure.repository.CustomOrderHistoryRepository;
import com.cplerings.core.infrastructure.repository.CustomOrderRepository;
import com.cplerings.core.infrastructure.repository.DesignRepository;
import com.cplerings.core.infrastructure.repository.DiamondRepository;
import com.cplerings.core.infrastructure.repository.JewelryRepository;
import com.cplerings.core.infrastructure.repository.RefundRepository;
import com.cplerings.core.infrastructure.repository.RingDiamondRepository;
import com.cplerings.core.infrastructure.repository.RingRepository;
import com.cplerings.core.infrastructure.repository.StandardOrderHistoryRepository;
import com.cplerings.core.infrastructure.repository.StandardOrderItemRepository;
import com.cplerings.core.infrastructure.repository.StandardOrderRepository;
import com.cplerings.core.infrastructure.repository.TransportOrderHistoryRepository;
import com.cplerings.core.infrastructure.repository.TransportationOrderRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DataSource
public class SharedCustomOrderDataSource extends AbstractDataSource
        implements ViewCustomOrdersDataSource, ViewCustomOrderDataSource, AssignJewelerToCustomOrderDataSource,
        CreateStandardOrderDataSource, ViewStandardOrdersDataSource, PayStandardOrderDataSource,
        ProcessPayStandardOrderDataSource, ViewStandardOrderDataSource, CancelStandardOrderDataSource, CompleteOrderDataSource,
        GetCustomOrderByOrderNoDataSource, RefundStandardOrderDataSource, GetStandardOrderByOrderNoDataSource,
        RefundCustomOrderDataSource, ViewRefundOrdersDataSource {

    private static final QCustomOrder Q_CUSTOM_ORDER = QCustomOrder.customOrder;
    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QJewelry Q_JEWELRY = QJewelry.jewelry;
    private static final QTransportationAddress Q_TRANSPORTATION_ADDRESS = QTransportationAddress.transportationAddress;
    private static final QStandardOrder Q_STANDARD_ORDER = QStandardOrder.standardOrder;
    private static final QStandardOrderItem Q_STANDARD_ORDER_ITEM = QStandardOrderItem.standardOrderItem;
    private static final QImage Q_IMAGE = QImage.image;
    private static final QRefund Q_REFUND = QRefund.refund;

    private final CustomOrderRepository customOrderRepository;
    private final CustomOrderHistoryRepository customOrderHistoryRepository;
    private final StandardOrderRepository standardOrderRepository;
    private final StandardOrderHistoryRepository standardOrderHistoryRepository;
    private final JewelryRepository jewelryRepository;
    private final StandardOrderItemRepository standardOrderItemRepository;
    private final TransportationOrderRepository transportationOrderRepository;
    private final TransportOrderHistoryRepository transportOrderHistoryRepository;
    private final RefundRepository refundRepository;
    private final AccountRepository accountRepository;
    private final RingRepository ringRepository;
    private final DesignRepository designRepository;
    private final AgreementRepository agreementRepository;
    private final RingDiamondRepository ringDiamondRepository;
    private final DiamondRepository diamondRepository;

    @Override
    public CustomOrders getCustomOrders(ViewCustomOrdersInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<CustomOrder> query = createQuery()
                .select(Q_CUSTOM_ORDER)
                .from(Q_CUSTOM_ORDER);

        final BooleanExpressionBuilder booleanExpressionBuilder = createBooleanExpressionBuilder();
        if (input.getStatus() != null) {
            switch (input.getStatus()) {
                case PENDING -> booleanExpressionBuilder.and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.PENDING));
                case WAITING -> booleanExpressionBuilder.and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.WAITING));
                case IN_PROGRESS ->
                        booleanExpressionBuilder.and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.IN_PROGRESS));
                case DELIVERING -> booleanExpressionBuilder.and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.DELIVERING));
                case DONE -> booleanExpressionBuilder.and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.DONE));
                case CANCELED -> booleanExpressionBuilder.and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.CANCELED));
                case COMPLETED -> booleanExpressionBuilder.and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.COMPLETED));
            }
        }
        if (input.getCustomerId() != null) {
            booleanExpressionBuilder.and(Q_CUSTOM_ORDER.customer.id.eq(input.getCustomerId()));
        }

        if (input.getJewelerId() != null) {
            booleanExpressionBuilder.and(Q_CUSTOM_ORDER.jeweler.id.eq(input.getJewelerId()));
        }

        if (input.getBranchId() != null) {
            booleanExpressionBuilder.and(Q_CUSTOM_ORDER.firstRing.branch.id.eq(input.getBranchId()));
        }

        final BooleanExpression predicate = booleanExpressionBuilder.build();
        query.where(predicate);

        long count = query.distinct().fetchCount();
        List<CustomOrder> customOrders = query.limit(input.getPageSize()).offset(offset).fetch();
        return CustomOrders.builder()
                .customOrders(customOrders)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    @Override
    public Optional<CustomOrder> getCustomOrder(Long customOrderId) {
        return Optional.ofNullable(createQuery()
                .select(Q_CUSTOM_ORDER)
                .from(Q_CUSTOM_ORDER)
                .where(Q_CUSTOM_ORDER.id.eq(customOrderId))
                .fetchOne());
    }

    @Override
    public Optional<Account> getJewelerById(Long jewelerId) {
        return Optional.ofNullable(createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.eq(jewelerId))
                .fetchOne());
    }

    @Override
    public Optional<CustomOrder> getCustomOrderById(Long customOrderId) {
        return Optional.ofNullable(createQuery()
                .select(Q_CUSTOM_ORDER)
                .from(Q_CUSTOM_ORDER)
                .leftJoin(Q_CUSTOM_ORDER.customer).fetchJoin()
                .leftJoin(Q_CUSTOM_ORDER.customer.agreement).fetchJoin()
                .leftJoin(Q_CUSTOM_ORDER.firstRing).fetchJoin()
                .leftJoin(Q_CUSTOM_ORDER.secondRing).fetchJoin()
                .leftJoin(Q_CUSTOM_ORDER.firstRing.ringDiamonds).fetchJoin()
                .leftJoin(Q_CUSTOM_ORDER.secondRing.ringDiamonds).fetchJoin()
                .where(Q_CUSTOM_ORDER.id.eq(customOrderId))
                .fetchOne());
    }

    @Override
    public CustomOrder save(CustomOrder customOrder) {
        updateAuditor(customOrder);
        return customOrderRepository.save(customOrder);
    }

    @Override
    public Optional<Account> findStaffById(Long staffId) {
        return Optional.ofNullable(createQuery().select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.eq(staffId)
                        .and(Q_ACCOUNT.role.eq(Role.STAFF))
                        .and(Q_ACCOUNT.status.eq(AccountStatus.ACTIVE))
                        .and(Q_ACCOUNT.state.eq(State.ACTIVE)))
                .fetchFirst());
    }

    @Override
    public Optional<Image> findImageById(Long imageId) {
        return Optional.ofNullable(createQuery().select(Q_IMAGE)
                .from(Q_IMAGE)
                .where(Q_IMAGE.id.eq(imageId)
                        .and(Q_IMAGE.state.eq(State.ACTIVE)))
                .fetchFirst());
    }

    @Override
    public CustomOrderHistory save(CustomOrderHistory customOrderHistory) {
        updateAuditor(customOrderHistory);
        return customOrderHistoryRepository.save(customOrderHistory);
    }

    @Override
    public List<Ring> saveRings(Collection<Ring> rings) {
        rings.forEach(this::updateAuditor);
        return ringRepository.saveAll(rings);
    }

    @Override
    public List<Design> saveDesigns(Collection<Design> designs) {
        designs.forEach(this::updateAuditor);
        return designRepository.saveAll(designs);
    }

    @Override
    public void delete(Agreement agreement) {
        agreementRepository.delete(agreement);
    }

    @Override
    public void deleteRingDiamonds(Collection<RingDiamond> ringDiamonds) {
        ringDiamondRepository.deleteAll(ringDiamonds);
    }

    @Override
    public void saveDiamonds(Collection<Diamond> diamonds) {
        diamondRepository.deleteAll(diamonds);
    }

    @Override
    public Optional<Account> getCustomerById(Long id) {
        return Optional.ofNullable(createQuery().
                select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.eq(id))
                .fetchFirst());
    }

    @Override
    public Optional<Jewelry> getJewelryById(Long id) {
        return Optional.ofNullable(createQuery().
                select(Q_JEWELRY)
                .from(Q_JEWELRY)
                .where(Q_JEWELRY.id.eq(id))
                .fetchFirst());
    }

    @Override
    public StandardOrder save(StandardOrder order) {
        updateAuditor(order);
        return standardOrderRepository.save(order);
    }

    @Override
    public Optional<TransportationAddress> getTransportationAddressById(Long transportationAddressId) {
        return Optional.ofNullable(createQuery().select(Q_TRANSPORTATION_ADDRESS)
                .from(Q_TRANSPORTATION_ADDRESS)
                .where(Q_TRANSPORTATION_ADDRESS.id.eq(transportationAddressId))
                .fetchFirst());
    }

    @Override
    public StandardOrderHistory save(StandardOrderHistory history) {
        updateAuditor(history);
        return standardOrderHistoryRepository.save(history);
    }

    @Override
    public List<Jewelry> saveJewelries(List<Jewelry> jewelries) {
        jewelries.forEach(this::updateAuditor);
        return jewelryRepository.saveAll(jewelries);
    }

    @Override
    public List<StandardOrderItem> saveItems(List<StandardOrderItem> items) {
        items.forEach(this::updateAuditor);
        return standardOrderItemRepository.saveAll(items);
    }

    @Override
    public Optional<TransportationAddress> getAddressById(Long id) {
        return Optional.ofNullable(createQuery().
                select(Q_TRANSPORTATION_ADDRESS)
                .from(Q_TRANSPORTATION_ADDRESS)
                .where(Q_TRANSPORTATION_ADDRESS.id.eq(id))
                .fetchFirst());
    }

    @Override
    public TransportationOrder save(TransportationOrder order) {
        updateAuditor(order);
        return transportationOrderRepository.save(order);
    }

    @Override
    public TransportOrderHistory save(TransportOrderHistory history) {
        updateAuditor(history);
        return transportOrderHistoryRepository.save(history);
    }

    @Override
    public Optional<Jewelry> getJewelry(Long branchId, Long designId, Long metalSpecId) {
        return Optional.ofNullable(createQuery().
                select(Q_JEWELRY)
                .from(Q_JEWELRY)
                .leftJoin(Q_JEWELRY.design).fetchJoin()
                .leftJoin(Q_JEWELRY.metalSpecification).fetchJoin()
                .leftJoin(Q_JEWELRY.branch).fetchJoin()
                .where(Q_JEWELRY.design.id.eq(designId)
                        .and(Q_JEWELRY.metalSpecification.id.eq(metalSpecId))
                        .and(Q_JEWELRY.branch.id.eq(branchId))
                        .and(Q_JEWELRY.status.eq(JewelryStatus.AVAILABLE)))
                .fetchFirst());
    }

    @Override
    public StandardOrders getStandardOrders(ViewStandardOrdersInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<StandardOrder> query = createQuery()
                .select(Q_STANDARD_ORDER)
                .from(Q_STANDARD_ORDER)
                .leftJoin(Q_STANDARD_ORDER.customer).fetchJoin()
                .leftJoin(Q_STANDARD_ORDER.standardOrderItems).fetchJoin();

        final BooleanExpressionBuilder booleanExpressionBuilder = createBooleanExpressionBuilder();
        if (input.getStatus() != null) {
            switch (input.getStatus()) {
                case PENDING -> booleanExpressionBuilder.and(Q_STANDARD_ORDER.status.eq(StandardOrderStatus.PENDING));
                case DELIVERING ->
                        booleanExpressionBuilder.and(Q_STANDARD_ORDER.status.eq(StandardOrderStatus.DELIVERING));
                case COMPLETED ->
                        booleanExpressionBuilder.and(Q_STANDARD_ORDER.status.eq(StandardOrderStatus.COMPLETED));
                case CANCELLED ->
                        booleanExpressionBuilder.and(Q_STANDARD_ORDER.status.eq(StandardOrderStatus.CANCELLED));
                case PAID -> booleanExpressionBuilder.and(Q_STANDARD_ORDER.status.eq(StandardOrderStatus.PAID));
            }
        }
        if (input.getCustomerId() != null) {
            booleanExpressionBuilder.and(Q_STANDARD_ORDER.customer.id.eq(input.getCustomerId()));
        }

        if (input.getBranchId() != null) {
            booleanExpressionBuilder.and(Q_STANDARD_ORDER.standardOrderItems.any().branch.id.eq(input.getBranchId()));
        }

        final BooleanExpression predicate = booleanExpressionBuilder.build();
        query.where(predicate);

        long count = query.distinct().fetchCount();
        List<StandardOrder> standardOrders = query.limit(input.getPageSize()).offset(offset).fetch();
        return StandardOrders.builder()
                .standardOrders(standardOrders)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    @Override
    public Optional<StandardOrder> findStandardOrderByIdAndCustomerId(Long standardOrderId, Long customerId) {
        return Optional.ofNullable(createQuery().select(Q_STANDARD_ORDER)
                .from(Q_STANDARD_ORDER)
                .leftJoin(Q_STANDARD_ORDER.customer).fetchJoin()
                .leftJoin(Q_STANDARD_ORDER.standardOrderItems, Q_STANDARD_ORDER_ITEM).fetchJoin()
                .leftJoin(Q_STANDARD_ORDER_ITEM.branch).fetchJoin()
                .leftJoin(Q_STANDARD_ORDER_ITEM.design).fetchJoin()
                .leftJoin(Q_STANDARD_ORDER_ITEM.metalSpecification).fetchJoin()
                .leftJoin(Q_STANDARD_ORDER_ITEM.jewelry).fetchJoin()
                .leftJoin(Q_STANDARD_ORDER.transportationAddress).fetchJoin()
                .where(Q_STANDARD_ORDER.id.eq(standardOrderId)
                        .and(Q_STANDARD_ORDER.customer.id.eq(customerId)))
                .fetchFirst());
    }

    @Override
    public List<Jewelry> getJewelries(JewelrySearchInfo jewelrySearchInfo) {
        return createQuery().select(Q_JEWELRY)
                .from(Q_JEWELRY)
                .where(Q_JEWELRY.branch.id.eq(jewelrySearchInfo.branchId())
                        .and(Q_JEWELRY.design.id.eq(jewelrySearchInfo.designId()))
                        .and(Q_JEWELRY.metalSpecification.id.eq(jewelrySearchInfo.metalSpecificationId()))
                        .and(Q_JEWELRY.status.eq(JewelryStatus.AVAILABLE))
                        .and(Q_JEWELRY.state.eq(State.ACTIVE)))
                .limit(jewelrySearchInfo.count())
                .fetch();
    }

    @Override
    public List<Jewelry> save(List<Jewelry> jewelries) {
        return saveJewelries(jewelries);
    }

    @Override
    public List<StandardOrderItem> save(Collection<StandardOrderItem> standardOrderItems) {
        standardOrderItems.forEach(this::updateAuditor);
        return standardOrderItemRepository.saveAll(standardOrderItems);
    }

    @Override
    public Optional<StandardOrder> getStandardOrderById(Long id) {
        return Optional.ofNullable(createQuery()
                .select(Q_STANDARD_ORDER)
                .from(Q_STANDARD_ORDER)
                .leftJoin(Q_STANDARD_ORDER.standardOrderItems, Q_STANDARD_ORDER_ITEM).fetchJoin()
                .leftJoin(Q_STANDARD_ORDER_ITEM.jewelry).fetchJoin()
                .where(Q_STANDARD_ORDER.id.eq(id))
                .fetchFirst());
    }

    @Override
    public Optional<CustomOrder> getCustomOrderByOrderNo(String orderNo) {
        return Optional.ofNullable(createQuery()
                .select(Q_CUSTOM_ORDER)
                .from(Q_CUSTOM_ORDER)
                .where(Q_CUSTOM_ORDER.orderNo.toLowerCase().eq(orderNo.toLowerCase()))
                .fetchFirst());
    }

    @Override
    public Optional<StandardOrder> getStandardOrderWithOrderItem(Long id) {
        return Optional.ofNullable(createQuery()
                .select(Q_STANDARD_ORDER)
                .from(Q_STANDARD_ORDER)
                .leftJoin(Q_STANDARD_ORDER.standardOrderItems, Q_STANDARD_ORDER_ITEM).fetchJoin()
                .leftJoin(Q_STANDARD_ORDER_ITEM.jewelry).fetchJoin()
                .leftJoin(Q_STANDARD_ORDER.transportationOrders).fetchJoin()
                .where(Q_STANDARD_ORDER.id.eq(id))
                .fetchFirst());
    }

    @Override
    public Jewelry save(Jewelry jewelry) {
        updateAuditor(jewelry);
        return jewelryRepository.save(jewelry);
    }

    @Override
    public Refund save(Refund refund) {
        updateAuditor(refund);
        return refundRepository.save(refund);
    }

    @Override
    public Optional<Account> getStaffById(Long id) {
        return Optional.ofNullable(createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.eq(id))
                .fetchFirst());
    }

    @Override
    public Optional<Image> getImageById(Long id) {
        return Optional.ofNullable(createQuery()
                .select(Q_IMAGE)
                .from(Q_IMAGE)
                .where(Q_IMAGE.id.eq(id))
                .fetchFirst());
    }

    @Override
    public Optional<StandardOrder> getStandardOrderByOrderNo(String orderNo) {
        return Optional.ofNullable(createQuery()
                .select(Q_STANDARD_ORDER)
                .from(Q_STANDARD_ORDER)
                .where(Q_STANDARD_ORDER.orderNo.toLowerCase().eq(orderNo.toLowerCase()))
                .fetchFirst());
    }

    @Override
    public Optional<CustomOrder> findCustomOrderById(Long customOrderId) {
        return getCustomOrderById(customOrderId);
    }

    @Override
    public Refunds getRefunds(ViewRefundOrdersInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<Refund> query = createQuery()
                .select(Q_REFUND)
                .from(Q_REFUND);
        final BooleanExpressionBuilder booleanExpressionBuilder = createBooleanExpressionBuilder();

        if (input.getStaffId() != null) {
            booleanExpressionBuilder.and(Q_REFUND.staff.id.eq(input.getStaffId()));
        }

        final BooleanExpression predicate = booleanExpressionBuilder.build();
        query.where(predicate);

        long count = query.distinct().fetchCount();
        List<Refund> refunds = query.limit(input.getPageSize()).offset(offset).fetch();
        return Refunds.builder()
                .refunds(refunds)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }
}
