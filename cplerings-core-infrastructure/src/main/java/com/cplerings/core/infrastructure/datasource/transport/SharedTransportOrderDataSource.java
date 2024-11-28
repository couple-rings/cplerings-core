package com.cplerings.core.infrastructure.datasource.transport;

import java.util.List;
import java.util.Optional;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.cplerings.core.application.transport.datasource.AssignTransportOrderDataSource;
import com.cplerings.core.application.transport.datasource.CreateTransportationNoteDataSource;
import com.cplerings.core.application.transport.datasource.GetTransportationOrderByCustomOrderDataSource;
import com.cplerings.core.application.transport.datasource.UpdateImageDeliveryDataSource;
import com.cplerings.core.application.transport.datasource.UpdateTransportationOrderStatusDataSource;
import com.cplerings.core.application.transport.datasource.UpdateTransportationOrdersToOngoingDataSource;
import com.cplerings.core.application.transport.datasource.ViewTransportationAddressesDataSource;
import com.cplerings.core.application.transport.datasource.ViewTransportationNotesDataSource;
import com.cplerings.core.application.transport.datasource.ViewTransportationOrderDataSource;
import com.cplerings.core.application.transport.datasource.ViewTransportationOrdersDataSource;
import com.cplerings.core.application.transport.datasource.result.TransportationAddresses;
import com.cplerings.core.application.transport.datasource.result.TransportationNotes;
import com.cplerings.core.application.transport.datasource.result.TransportationOrders;
import com.cplerings.core.application.transport.input.ViewTransportationAddressesInput;
import com.cplerings.core.application.transport.input.ViewTransportationNotesInput;
import com.cplerings.core.application.transport.input.ViewTransportationOrdersInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.address.QTransportationAddress;
import com.cplerings.core.domain.address.TransportationAddress;
import com.cplerings.core.domain.branch.QBranch;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.file.QImage;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderHistory;
import com.cplerings.core.domain.order.QCustomOrder;
import com.cplerings.core.domain.order.QTransportationOrder;
import com.cplerings.core.domain.order.TransportOrderHistory;
import com.cplerings.core.domain.order.TransportStatus;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.order.status.QTransportationNote;
import com.cplerings.core.domain.order.status.TransportationNote;
import com.cplerings.core.domain.ring.QRing;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.CustomOrderHistoryRepository;
import com.cplerings.core.infrastructure.repository.CustomOrderRepository;
import com.cplerings.core.infrastructure.repository.TransportOrderHistoryRepository;
import com.cplerings.core.infrastructure.repository.TransportationNoteRepository;
import com.cplerings.core.infrastructure.repository.TransportationOrderRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DataSource
public class SharedTransportOrderDataSource extends AbstractDataSource implements AssignTransportOrderDataSource,
        UpdateTransportationOrdersToOngoingDataSource, ViewTransportationOrdersDataSource,
        UpdateTransportationOrderStatusDataSource, ViewTransportationAddressesDataSource, GetTransportationOrderByCustomOrderDataSource,
        ViewTransportationOrderDataSource, UpdateImageDeliveryDataSource, CreateTransportationNoteDataSource,
        ViewTransportationNotesDataSource {

    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QTransportationOrder Q_TRANSPORTATION_ORDER = QTransportationOrder.transportationOrder;
    private static final QCustomOrder Q_CUSTOM_ORDER = QCustomOrder.customOrder;
    private static final QTransportationAddress Q_TRANSPORTATION_ADDRESS = QTransportationAddress.transportationAddress;
    private static final QRing FIRST_Q_RING = new QRing("firstRing");
    private static final QRing SECOND_Q_RING = new QRing("secondRing");
    private static final QBranch FIRST_Q_BRANCH = new QBranch("firstBranch");
    private static final QBranch SECOND_Q_BRANCH = new QBranch("secondBranch");
    private static final QImage Q_IMAGE = QImage.image;
    private static final QTransportationNote Q_TRANSPORTATION_NOTE = QTransportationNote.transportationNote;

    private final TransportationOrderRepository transportationOrderRepository;
    private final CustomOrderRepository customOrderRepository;
    private final TransportationNoteRepository transportationNoteRepository;
    private final CustomOrderHistoryRepository customOrderHistoryRepository;
    private final TransportOrderHistoryRepository transportOrderHistoryRepository;

    @Override
    public Optional<Account> getTransporterById(Long transporterId) {
        return Optional.ofNullable(createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.eq(transporterId)
                        .and(Q_ACCOUNT.state.eq(State.ACTIVE)))
                .fetchOne());
    }

    @Override
    public Optional<TransportationOrder> getTransportationOrderById(Long transportationOrderId) {
        return Optional.ofNullable(createQuery()
                .select(Q_TRANSPORTATION_ORDER)
                .from(Q_TRANSPORTATION_ORDER)
                .leftJoin(Q_TRANSPORTATION_ORDER.customOrder, Q_CUSTOM_ORDER).fetchJoin()
                .where(Q_TRANSPORTATION_ORDER.id.eq(transportationOrderId))
                .fetchOne());
    }

    @Override
    public TransportationOrder save(TransportationOrder transportationOrder) {
        updateAuditor(transportationOrder);
        return transportationOrderRepository.save(transportationOrder);
    }

    @Override
    public CustomOrder save(CustomOrder customOrder) {
        updateAuditor(customOrder);
        return customOrderRepository.save(customOrder);
    }

    @Override
    public CustomOrderHistory save(CustomOrderHistory customOrderHistory) {
        updateAuditor(customOrderHistory);
        return customOrderHistoryRepository.save(customOrderHistory);
    }

    @Override
    public TransportOrderHistory save(TransportOrderHistory transportOrderHistory) {
        updateAuditor(transportOrderHistory);
        return transportOrderHistoryRepository.save(transportOrderHistory);
    }

    @Override
    public List<TransportationOrder> getTransportationOrders(List<Long> ids) {
        return createQuery().select(Q_TRANSPORTATION_ORDER)
                .from(Q_TRANSPORTATION_ORDER)
                .where(Q_TRANSPORTATION_ORDER.id.in(ids))
                .fetch();
    }

    @Override
    public List<TransportationOrder> updateToOngoing(List<TransportationOrder> transportationOrders) {
        transportationOrders.forEach(this::updateAuditor);
        return transportationOrderRepository.saveAll(transportationOrders);
    }

    @Override
    public TransportationOrders getTransportationOrders(ViewTransportationOrdersInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<TransportationOrder> query = createQuery()
                .select(Q_TRANSPORTATION_ORDER)
                .from(Q_TRANSPORTATION_ORDER)
                .leftJoin(Q_TRANSPORTATION_ORDER.customOrder, Q_CUSTOM_ORDER).fetchJoin()
                .leftJoin(Q_CUSTOM_ORDER.firstRing, FIRST_Q_RING).fetchJoin()
                .leftJoin(Q_CUSTOM_ORDER.secondRing, SECOND_Q_RING).fetchJoin()
                .leftJoin(FIRST_Q_RING.branch, FIRST_Q_BRANCH).fetchJoin()
                .leftJoin(SECOND_Q_RING.branch, SECOND_Q_BRANCH).fetchJoin();
        final BooleanExpressionBuilder booleanExpressionBuilder = createBooleanExpressionBuilder();
        if (input.getTransporterId() != null) {
            booleanExpressionBuilder.and(Q_TRANSPORTATION_ORDER.transporter.id.eq(input.getTransporterId()));
        }

        if (input.getBranchId() != null) {
            booleanExpressionBuilder.and(FIRST_Q_RING.branch.id.eq(input.getBranchId()));
        }

        if (input.getStatus() != null) {
            switch (input.getStatus()) {
                case WAITING -> booleanExpressionBuilder.and(Q_TRANSPORTATION_ORDER.status.eq(TransportStatus.WAITING));
                case ON_GOING ->
                        booleanExpressionBuilder.and(Q_TRANSPORTATION_ORDER.status.eq(TransportStatus.ON_GOING));
                case DELIVERING ->
                        booleanExpressionBuilder.and(Q_TRANSPORTATION_ORDER.status.eq(TransportStatus.DELIVERING));
                case PENDING -> booleanExpressionBuilder.and(Q_TRANSPORTATION_ORDER.status.eq(TransportStatus.PENDING));
                case COMPLETED ->
                        booleanExpressionBuilder.and(Q_TRANSPORTATION_ORDER.status.eq(TransportStatus.COMPLETED));
                case REJECTED ->
                        booleanExpressionBuilder.and(Q_TRANSPORTATION_ORDER.status.eq(TransportStatus.REJECTED));
            }
        }
        final BooleanExpression predicate = booleanExpressionBuilder.build();
        query.where(predicate);


        long count = query.distinct().fetchCount();
        List<TransportationOrder> transportationOrders = query.limit(input.getPageSize()).offset(offset).fetch();
        return TransportationOrders.builder()
                .transportationOrders(transportationOrders)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    @Override
    public TransportationAddresses getTransportationAddresses(ViewTransportationAddressesInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<TransportationAddress> query = createQuery()
                .select(Q_TRANSPORTATION_ADDRESS)
                .from(Q_TRANSPORTATION_ADDRESS);
        if (input.getCustomerId() != null) {
            query.where(Q_TRANSPORTATION_ADDRESS.customer.id.eq(input.getCustomerId()));
        }

        long count = query.distinct().fetchCount();
        List<TransportationAddress> transportationAddresses = query.limit(input.getPageSize()).offset(offset).fetch();
        return TransportationAddresses.builder()
                .transportationAddresses(transportationAddresses)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    @Override
    public Optional<TransportationOrder> getTransportationOrderByCustomOrderId(Long customOrderId) {
        return Optional.ofNullable(createQuery()
                .select(Q_TRANSPORTATION_ORDER)
                .from(Q_TRANSPORTATION_ORDER)
                .leftJoin(Q_TRANSPORTATION_ORDER.customOrder, Q_CUSTOM_ORDER)
                .where(Q_TRANSPORTATION_ORDER.customOrder.id.eq(customOrderId))
                .fetchFirst());
    }

    @Override
    public Optional<TransportationOrder> getTransportationOrder(Long id) {
        return Optional.ofNullable(createQuery()
                .select(Q_TRANSPORTATION_ORDER)
                .from(Q_TRANSPORTATION_ORDER)
                .where(Q_TRANSPORTATION_ORDER.id.eq(id))
                .fetchFirst());
    }

    @Override
    public TransportationNote save(TransportationNote transportationNote) {
        updateAuditor(transportationNote);
        return transportationNoteRepository.save(transportationNote);
    }

    @Override
    public Optional<Image> getImageById(Long imageId) {
        return Optional.ofNullable(createQuery()
                .select(Q_IMAGE)
                .from(Q_IMAGE)
                .where(Q_IMAGE.id.eq(imageId))
                .fetchFirst());
    }

    @Override
    public TransportationNotes getTransportationNotes(ViewTransportationNotesInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<TransportationNote> query = createQuery()
                .select(Q_TRANSPORTATION_NOTE)
                .from(Q_TRANSPORTATION_NOTE);

        long count = query.distinct().fetchCount();
        List<TransportationNote> transportationNotes = query.limit(input.getPageSize()).offset(offset).fetch();
        return TransportationNotes.builder()
                .transportationNotes(transportationNotes)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }
}
