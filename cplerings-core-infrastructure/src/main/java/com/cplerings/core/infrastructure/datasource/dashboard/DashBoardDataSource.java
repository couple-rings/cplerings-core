package com.cplerings.core.infrastructure.datasource.dashboard;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.cplerings.core.application.dashboard.datasource.ViewBranchOrdersDataSource;
import com.cplerings.core.application.dashboard.datasource.ViewBranchOrdersPaginateDataSource;
import com.cplerings.core.application.dashboard.datasource.ViewBranchRevenueDataSource;
import com.cplerings.core.application.dashboard.datasource.ViewCustomOrdersWithDateDataSource;
import com.cplerings.core.application.dashboard.datasource.ViewPaymentWithDateDataSource;
import com.cplerings.core.application.dashboard.datasource.ViewRefundOrdersWithDateDataSource;
import com.cplerings.core.application.dashboard.datasource.ViewResellOrdersWithDateDataSource;
import com.cplerings.core.application.dashboard.datasource.data.CombinedOrder;
import com.cplerings.core.application.dashboard.datasource.data.CombinedOrders;
import com.cplerings.core.application.dashboard.datasource.data.OrderTypeStatistic;
import com.cplerings.core.application.dashboard.datasource.data.Orders;
import com.cplerings.core.application.dashboard.datasource.data.Payments;
import com.cplerings.core.application.dashboard.datasource.data.Revenue;
import com.cplerings.core.application.dashboard.input.ViewBranchOrdersPaginateInput;
import com.cplerings.core.application.dashboard.input.ViewCustomOrdersWithDateInput;
import com.cplerings.core.application.dashboard.input.ViewPaymentWithDateInput;
import com.cplerings.core.application.dashboard.input.ViewRefundOrdersWithDateInput;
import com.cplerings.core.application.dashboard.input.ViewResellOrdersWithDateInput;
import com.cplerings.core.application.order.datasource.result.CustomOrders;
import com.cplerings.core.application.order.datasource.result.Refunds;
import com.cplerings.core.application.order.datasource.result.ResellOrders;
import com.cplerings.core.application.shared.entity.order.APaymentMethod;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.branch.QBranch;
import com.cplerings.core.domain.crafting.QCraftingStage;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.QCustomOrder;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentStatus;
import com.cplerings.core.domain.payment.QPayment;
import com.cplerings.core.domain.refund.QRefund;
import com.cplerings.core.domain.refund.Refund;
import com.cplerings.core.domain.resell.QResellOrder;
import com.cplerings.core.domain.resell.ResellOrder;
import com.cplerings.core.domain.ring.QRing;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class DashBoardDataSource extends AbstractDataSource implements ViewBranchRevenueDataSource, ViewBranchOrdersDataSource, ViewBranchOrdersPaginateDataSource, ViewCustomOrdersWithDateDataSource, ViewResellOrdersWithDateDataSource,
        ViewRefundOrdersWithDateDataSource, ViewPaymentWithDateDataSource {

    private static final QCustomOrder Q_CUSTOM_ORDER = QCustomOrder.customOrder;
    private static final QResellOrder Q_RESELL_ORDER = QResellOrder.resellOrder;
    private static final QRefund Q_REFUND = QRefund.refund;
    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QRing Q_FIRST_RING = QRing.ring;
    private static final QBranch Q_BRANCH = QBranch.branch;
    private static final QPayment Q_PAYMENT = QPayment.payment;
    private static final QCraftingStage Q_CRAFTING_STAGE = QCraftingStage.craftingStage;

    private final ZoneId targetZone = ZoneId.of("UTC");

    @Override
    public Revenue getTotalRevenue(Instant start, Instant end, Long branchId) {
        var numOfDays = ChronoUnit.DAYS.between(start, end) + 1L;
        if (numOfDays > 0 && numOfDays < 30) {
            return getTotalRevenueOfCustomOrderForEachDay(numOfDays, start, branchId);
        } else if (numOfDays >= 30 && numOfDays < 60) {
            Long quotient = numOfDays / 7;
            Long remainder = numOfDays % 7;
            return getTotalRevenueOfCustomOrderForEachWeek(start, quotient, remainder, branchId);
        } else {
            Long quotient = numOfDays / 30;
            Long remainder = numOfDays % 30;
            return getTotalRevenueOfCustomOrderForEachMonth(start, quotient, remainder, branchId);
        }
    }

    @Override
    public Account getAccountById(Long id) {
        return createQuery().select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .leftJoin(Q_ACCOUNT.branch)
                .where(Q_ACCOUNT.id.eq(id))
                .fetchOne();
    }

    private Revenue getTotalRevenueOfCustomOrderForEachDay(Long numOfDays, Instant startDate, Long branchId) {
        BigDecimal totalRevenue = BigDecimal.ZERO;
        Map<String, BigDecimal> revenueEachDayForCustomOrder = new HashMap<>();

        LocalDate startDateLocalDate = startDate.atZone(ZoneId.systemDefault()).toLocalDate();
        for (int i = 1; i <= numOfDays; i++) {
            BigDecimal totalRevenueEachDay = BigDecimal.ZERO;
            BigDecimal customOrderRevenueEachDay = Optional.ofNullable(createQuery().select(Q_PAYMENT.amount.amount.sum())
                    .from(Q_PAYMENT)
                    .leftJoin(Q_PAYMENT.craftingStage, Q_CRAFTING_STAGE)
                    .leftJoin(Q_CRAFTING_STAGE.customOrder, Q_CUSTOM_ORDER)
                    .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                    .leftJoin(Q_FIRST_RING.branch)
                    .where(
                            Expressions.stringTemplate(
                                            "FUNCTION('DATE_TRUNC', 'day', {0})", Q_PAYMENT.createdAt
                                    ).eq(Expressions.constant(startDateLocalDate.atStartOfDay().atZone(targetZone).toInstant())
                                    )
                                    .and(Q_PAYMENT.craftingStage.isNotNull())
                                    .and(Q_PAYMENT.status.eq(PaymentStatus.SUCCESSFUL))
                                    .and(Q_FIRST_RING.branch.isNotNull())
                                    .and(Q_FIRST_RING.branch.id.eq(branchId)))
                    .fetchOne()).orElse(BigDecimal.ZERO);
            totalRevenue = totalRevenue.add(customOrderRevenueEachDay);
            totalRevenueEachDay = totalRevenueEachDay.add(customOrderRevenueEachDay);
            BigDecimal resellOrderRevenueEachDay = Optional.ofNullable(createQuery().select(Q_RESELL_ORDER.amount.amount.sum())
                    .from(Q_RESELL_ORDER)
                    .leftJoin(Q_RESELL_ORDER.customOrder, Q_CUSTOM_ORDER)
                    .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                    .leftJoin(Q_FIRST_RING.branch)
                    .where(
                            Expressions.stringTemplate(
                                            "FUNCTION('DATE_TRUNC', 'day', {0})", Q_RESELL_ORDER.createdAt
                                    ).eq(Expressions.constant(startDateLocalDate.atStartOfDay().atZone(targetZone).toInstant())
                                    ).and(Q_FIRST_RING.branch.isNotNull())
                                    .and(Q_FIRST_RING.branch.id.eq(branchId)))
                    .fetchOne()).orElse(BigDecimal.ZERO);
            totalRevenue = totalRevenue.subtract(resellOrderRevenueEachDay);
            totalRevenueEachDay = totalRevenueEachDay.subtract(resellOrderRevenueEachDay);
            BigDecimal refundOrderRevenueEachDay = Optional.ofNullable(createQuery().select(Q_REFUND.amount.amount.sum())
                    .from(Q_REFUND)
                    .leftJoin(Q_REFUND.customOrder, Q_CUSTOM_ORDER)
                    .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                    .leftJoin(Q_FIRST_RING.branch)
                    .where(
                            Expressions.stringTemplate(
                                    "FUNCTION('DATE_TRUNC', 'day', {0})", Q_REFUND.createdAt
                            ).eq(Expressions.constant(startDateLocalDate.atStartOfDay().atZone(targetZone).toInstant())
                            ).and(Q_FIRST_RING.branch.isNotNull()
                                    .and(Q_FIRST_RING.branch.id.eq(branchId))))
                    .fetchOne()).orElse(BigDecimal.ZERO);
            totalRevenue = totalRevenue.subtract(refundOrderRevenueEachDay);
            totalRevenueEachDay = totalRevenueEachDay.subtract(refundOrderRevenueEachDay);
            revenueEachDayForCustomOrder.put(startDateLocalDate.toString(), totalRevenueEachDay);

            startDateLocalDate = startDateLocalDate.plusDays(1);
        }
        return Revenue.builder()
                .totalRevenue(totalRevenue)
                .revenueForEach(revenueEachDayForCustomOrder)
                .build();
    }

    private Revenue getTotalRevenueOfCustomOrderForEachWeek(Instant startDate, Long quotient, Long remainder, Long branchId) {
        BigDecimal totalRevenue = BigDecimal.ZERO;
        Map<String, BigDecimal> revenueEachWeekForCustomOrder = new HashMap<>();

        LocalDate startDateLocalDate = startDate.atZone(ZoneId.systemDefault()).toLocalDate();
        for (int i = 0; i <= quotient; i++) {
            if (i < quotient) {
                BigDecimal totalRevenueEachWeek = BigDecimal.ZERO;
                BigDecimal customOrderRevenueEachWeek = Optional.ofNullable(createQuery().select(Q_PAYMENT.amount.amount.sum())
                        .from(Q_PAYMENT)
                        .leftJoin(Q_PAYMENT.craftingStage, Q_CRAFTING_STAGE)
                        .leftJoin(Q_CRAFTING_STAGE.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(
                                Expressions.predicate(
                                                Ops.BETWEEN,
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_PAYMENT.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(6L).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_PAYMENT.craftingStage.isNotNull())
                                        .and(Q_PAYMENT.status.eq(PaymentStatus.SUCCESSFUL))
                                        .and(Q_FIRST_RING.branch.isNotNull())
                                        .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne()).orElse(BigDecimal.ZERO);
                totalRevenue = totalRevenue.add(customOrderRevenueEachWeek);
                totalRevenueEachWeek = totalRevenueEachWeek.add(customOrderRevenueEachWeek);
                BigDecimal resellOrderRevenueEachWeek = Optional.ofNullable(createQuery().select(Q_RESELL_ORDER.amount.amount.sum())
                        .from(Q_RESELL_ORDER)
                        .leftJoin(Q_RESELL_ORDER.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(
                                Expressions.predicate(
                                                Ops.BETWEEN,
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_RESELL_ORDER.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(6L).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_FIRST_RING.branch.isNotNull())
                                        .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne()).orElse(BigDecimal.ZERO);
                totalRevenue = totalRevenue.subtract(resellOrderRevenueEachWeek);
                totalRevenueEachWeek = totalRevenueEachWeek.subtract(resellOrderRevenueEachWeek);
                BigDecimal refundOrderRevenueEachWeek = Optional.ofNullable(createQuery().select(Q_REFUND.amount.amount.sum())
                        .from(Q_REFUND)
                        .leftJoin(Q_REFUND.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(
                                Expressions.predicate(
                                                Ops.BETWEEN,
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_REFUND.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(6L).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_FIRST_RING.branch.isNotNull())
                                        .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne()).orElse(BigDecimal.ZERO);
                totalRevenue = totalRevenue.subtract(refundOrderRevenueEachWeek);
                totalRevenueEachWeek = totalRevenueEachWeek.subtract(refundOrderRevenueEachWeek);
                revenueEachWeekForCustomOrder.put(startDateLocalDate + " - " + startDateLocalDate.plusDays(6L), totalRevenueEachWeek);

                startDateLocalDate = startDateLocalDate.plusDays(7);
            } else {
                BigDecimal totalRevenueEachWeek = BigDecimal.ZERO;
                BigDecimal customOrderRevenueEachWeek = Optional.ofNullable(createQuery().select(Q_PAYMENT.amount.amount.sum())
                        .from(Q_PAYMENT)
                        .leftJoin(Q_PAYMENT.craftingStage, Q_CRAFTING_STAGE)
                        .leftJoin(Q_CRAFTING_STAGE.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(
                                Expressions.predicate(
                                                Ops.BETWEEN,
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_PAYMENT.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(remainder - 1).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_PAYMENT.craftingStage.isNotNull())
                                        .and(Q_PAYMENT.status.eq(PaymentStatus.SUCCESSFUL))
                                        .and(Q_FIRST_RING.branch.isNotNull())
                                        .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne()).orElse(BigDecimal.ZERO);
                totalRevenue = totalRevenue.add(customOrderRevenueEachWeek);
                totalRevenueEachWeek = totalRevenueEachWeek.add(customOrderRevenueEachWeek);
                BigDecimal resellOrderRevenueEachWeek = Optional.ofNullable(createQuery().select(Q_RESELL_ORDER.amount.amount.sum())
                        .from(Q_RESELL_ORDER)
                        .leftJoin(Q_RESELL_ORDER.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(
                                Expressions.predicate(
                                                Ops.BETWEEN,
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_RESELL_ORDER.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(remainder - 1).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_FIRST_RING.branch.isNotNull())
                                        .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne()).orElse(BigDecimal.ZERO);
                totalRevenue = totalRevenue.subtract(resellOrderRevenueEachWeek);
                totalRevenueEachWeek = totalRevenueEachWeek.subtract(resellOrderRevenueEachWeek);
                BigDecimal refundOrderRevenueEachWeek = Optional.ofNullable(createQuery().select(Q_REFUND.amount.amount.sum())
                        .from(Q_REFUND)
                        .leftJoin(Q_REFUND.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(
                                Expressions.predicate(
                                                Ops.BETWEEN,
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_REFUND.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(remainder - 1).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_FIRST_RING.branch.isNotNull())
                                        .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne()).orElse(BigDecimal.ZERO);
                totalRevenue = totalRevenue.subtract(refundOrderRevenueEachWeek);
                totalRevenueEachWeek = totalRevenueEachWeek.subtract(refundOrderRevenueEachWeek);
                revenueEachWeekForCustomOrder.put(startDateLocalDate + " - " + startDateLocalDate.plusDays(remainder - 1), totalRevenueEachWeek);
            }
        }
        return Revenue.builder()
                .totalRevenue(totalRevenue)
                .revenueForEach(revenueEachWeekForCustomOrder)
                .build();
    }

    private Revenue getTotalRevenueOfCustomOrderForEachMonth(Instant startDate, Long quotient, Long remainder, Long branchId) {
        BigDecimal totalRevenue = BigDecimal.ZERO;
        Map<String, BigDecimal> revenueEachMonthForCustomOrder = new HashMap<>();

        LocalDate startDateLocalDate = startDate.atZone(ZoneId.systemDefault()).toLocalDate();
        for (int i = 0; i <= quotient; i++) {
            if (i < quotient) {
                BigDecimal totalRevenueEachMonth = BigDecimal.ZERO;
                BigDecimal customOrderRevenueEachMonth = Optional.ofNullable(createQuery().select(Q_PAYMENT.amount.amount.sum())
                        .from(Q_PAYMENT)
                        .leftJoin(Q_PAYMENT.craftingStage, Q_CRAFTING_STAGE)
                        .leftJoin(Q_CRAFTING_STAGE.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(
                                Expressions.predicate(
                                                Ops.BETWEEN,
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_PAYMENT.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(29L).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_PAYMENT.craftingStage.isNotNull())
                                        .and(Q_PAYMENT.status.eq(PaymentStatus.SUCCESSFUL))
                                        .and(Q_FIRST_RING.branch.isNotNull())
                                        .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne()).orElse(BigDecimal.ZERO);
                totalRevenue = totalRevenue.add(customOrderRevenueEachMonth);
                totalRevenueEachMonth = totalRevenueEachMonth.add(customOrderRevenueEachMonth);
                BigDecimal resellOrderRevenueEachMonth = Optional.ofNullable(createQuery().select(Q_RESELL_ORDER.amount.amount.sum())
                        .from(Q_RESELL_ORDER)
                        .leftJoin(Q_RESELL_ORDER.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(
                                Expressions.predicate(
                                                Ops.BETWEEN,
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_RESELL_ORDER.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(29L).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_FIRST_RING.branch.isNotNull())
                                        .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne()).orElse(BigDecimal.ZERO);
                totalRevenue = totalRevenue.subtract(resellOrderRevenueEachMonth);
                totalRevenueEachMonth = totalRevenueEachMonth.subtract(resellOrderRevenueEachMonth);
                BigDecimal refundOrderRevenueEachMonth = Optional.ofNullable(createQuery().select(Q_REFUND.amount.amount.sum())
                        .from(Q_REFUND)
                        .leftJoin(Q_REFUND.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(
                                Expressions.predicate(
                                                Ops.BETWEEN,
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_REFUND.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(29L).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_FIRST_RING.branch.id.isNotNull())
                                        .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne()).orElse(BigDecimal.ZERO);
                totalRevenue = totalRevenue.subtract(refundOrderRevenueEachMonth);
                totalRevenueEachMonth = totalRevenueEachMonth.subtract(refundOrderRevenueEachMonth);
                revenueEachMonthForCustomOrder.put(startDateLocalDate + " - " + startDateLocalDate.plusDays(29L), totalRevenueEachMonth);

                startDateLocalDate = startDateLocalDate.plusDays(30);
            } else {
                BigDecimal totalRevenueEachMonth = BigDecimal.ZERO;
                BigDecimal customOrderRevenueEachMonth = Optional.ofNullable(createQuery().select(Q_PAYMENT.amount.amount.sum())
                        .from(Q_PAYMENT)
                        .leftJoin(Q_PAYMENT.craftingStage, Q_CRAFTING_STAGE)
                        .leftJoin(Q_CRAFTING_STAGE.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(
                                Expressions.predicate(
                                                Ops.BETWEEN,
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_PAYMENT.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(remainder - 1).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_PAYMENT.craftingStage.isNotNull())
                                        .and(Q_PAYMENT.status.eq(PaymentStatus.SUCCESSFUL))
                                        .and(Q_CUSTOM_ORDER.firstRing.branch.id.eq(branchId)))
                        .fetchOne()).orElse(BigDecimal.ZERO);
                totalRevenue = totalRevenue.add(customOrderRevenueEachMonth);
                totalRevenueEachMonth = totalRevenueEachMonth.add(customOrderRevenueEachMonth);
                BigDecimal resellOrderRevenueEachMonth = Optional.ofNullable(createQuery().select(Q_RESELL_ORDER.amount.amount.sum())
                        .from(Q_RESELL_ORDER)
                        .leftJoin(Q_RESELL_ORDER.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(
                                Expressions.predicate(
                                                Ops.BETWEEN,
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_RESELL_ORDER.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(remainder - 1).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_FIRST_RING.branch.isNotNull())
                                        .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne()).orElse(BigDecimal.ZERO);
                totalRevenue = totalRevenue.subtract(resellOrderRevenueEachMonth);
                totalRevenueEachMonth = totalRevenueEachMonth.subtract(resellOrderRevenueEachMonth);
                BigDecimal refundOrderRevenueEachMonth = Optional.ofNullable(createQuery().select(Q_REFUND.amount.amount.sum())
                        .from(Q_REFUND)
                        .leftJoin(Q_REFUND.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(
                                Expressions.predicate(
                                                Ops.BETWEEN,
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_REFUND.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(remainder - 1).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_FIRST_RING.branch.isNotNull())
                                        .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne()).orElse(BigDecimal.ZERO);
                totalRevenue = totalRevenue.subtract(refundOrderRevenueEachMonth);
                totalRevenueEachMonth = totalRevenueEachMonth.subtract(refundOrderRevenueEachMonth);
                revenueEachMonthForCustomOrder.put(startDateLocalDate + " - " + startDateLocalDate.plusDays(remainder - 1), totalRevenueEachMonth);
            }
        }
        return Revenue.builder()
                .totalRevenue(totalRevenue)
                .revenueForEach(revenueEachMonthForCustomOrder)
                .build();
    }

    @Override
    public Orders getOrders(Instant start, Instant end, Long branchId) {
        var numOfDays = ChronoUnit.DAYS.between(start, end) + 1L;
        if (numOfDays > 0 && numOfDays < 30) {
            return getOrdersEachDay(numOfDays, start, branchId);
        } else if (numOfDays >= 30 && numOfDays < 60) {
            Long quotient = numOfDays / 7;
            Long remainder = numOfDays % 7;
            return getOrdersEachWeek(start, quotient, remainder, branchId);
        } else {
            Long quotient = numOfDays / 30;
            Long remainder = numOfDays % 30;
            return getOrdersEachMonth(start, quotient, remainder, branchId);
        }
    }

    private Orders getOrdersEachDay(Long numOfDays, Instant start, Long branchId) {
        Long totalCustomOrders = 0L;
        Long totalResellOrders = 0L;
        Long totalRefundOrders = 0L;

        Map<String, Long> customOrdersForEachDay = new HashMap<>();
        Map<String, Long> resellOrdersForEachDay = new HashMap<>();
        Map<String, Long> refundOrdersForEachDay = new HashMap<>();

        LocalDate startDateLocalDate = start.atZone(ZoneId.systemDefault()).toLocalDate();
        for (int i = 1; i <= numOfDays; i++) {
            long customOrderForDay = createQuery().select(Q_CUSTOM_ORDER.count())
                    .from(Q_CUSTOM_ORDER)
                    .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                    .leftJoin(Q_FIRST_RING.branch)
                    .where(Expressions.stringTemplate(
                                    "FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt
                            ).eq(Expressions.constant(startDateLocalDate.atStartOfDay().atZone(targetZone).toInstant())
                            )
                            .and(Q_FIRST_RING.branch.isNotNull())
                            .and(Q_FIRST_RING.branch.id.eq(branchId)))
                    .fetchOne();
            totalCustomOrders = totalCustomOrders + customOrderForDay;
            customOrdersForEachDay.put(startDateLocalDate.toString(), customOrderForDay);

            long resellOrderForDay = createQuery().select(Q_RESELL_ORDER.count())
                    .from(Q_RESELL_ORDER)
                    .leftJoin(Q_RESELL_ORDER.customOrder, Q_CUSTOM_ORDER)
                    .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                    .leftJoin(Q_FIRST_RING.branch)
                    .where(Expressions.stringTemplate(
                                    "FUNCTION('DATE_TRUNC', 'day', {0})", Q_RESELL_ORDER.createdAt
                            ).eq(Expressions.constant(startDateLocalDate.atStartOfDay().atZone(targetZone).toInstant())
                            )
                            .and(Q_FIRST_RING.branch.isNotNull())
                            .and(Q_FIRST_RING.branch.id.eq(branchId)))
                    .fetchOne();
            totalResellOrders = totalResellOrders + resellOrderForDay;
            resellOrdersForEachDay.put(startDateLocalDate.toString(), resellOrderForDay);

            long refundOrderForDay = createQuery().select(Q_REFUND.count())
                    .from(Q_REFUND)
                    .leftJoin(Q_REFUND.customOrder, Q_CUSTOM_ORDER)
                    .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                    .leftJoin(Q_FIRST_RING.branch)
                    .where(Expressions.stringTemplate(
                                    "FUNCTION('DATE_TRUNC', 'day', {0})", Q_REFUND.createdAt
                            ).eq(Expressions.constant(startDateLocalDate.atStartOfDay().atZone(targetZone).toInstant())
                            )
                            .and(Q_FIRST_RING.branch.isNotNull())
                            .and(Q_FIRST_RING.branch.id.eq(branchId)))
                    .fetchOne();
            totalRefundOrders = totalRefundOrders + refundOrderForDay;
            refundOrdersForEachDay.put(startDateLocalDate.toString(), resellOrderForDay);

            startDateLocalDate = startDateLocalDate.plusDays(1L);
        }
        return Orders.builder()
                .customOrdersForEach(customOrdersForEachDay)
                .refundOrdersForEach(refundOrdersForEachDay)
                .resellOrdersForEach(resellOrdersForEachDay)
                .totalCustomOrder(totalCustomOrders)
                .totalRefundOrder(totalRefundOrders)
                .totalResellOrder(totalResellOrders)
                .build();
    }

    private Orders getOrdersEachWeek(Instant start, Long quotient, Long remainder, Long branchId) {
        Long totalCustomOrders = 0L;
        Long totalResellOrders = 0L;
        Long totalRefundOrders = 0L;

        Map<String, Long> customOrdersForEachDay = new HashMap<>();
        Map<String, Long> resellOrdersForEachDay = new HashMap<>();
        Map<String, Long> refundOrdersForEachDay = new HashMap<>();

        LocalDate startDateLocalDate = start.atZone(ZoneId.systemDefault()).toLocalDate();
        for (int i = 0; i <= quotient; i++) {
            if (i < quotient) {
                long customOrderForDay = createQuery().select(Q_CUSTOM_ORDER.count())
                        .from(Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(Expressions.predicate(
                                        Ops.BETWEEN,
                                        Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt),
                                        Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                        Expressions.constant(startDateLocalDate.plusDays(6L).atStartOfDay().atZone(targetZone).toInstant()))
                                .and(Q_FIRST_RING.branch.isNotNull())
                                .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne();
                totalCustomOrders = totalCustomOrders + customOrderForDay;
                customOrdersForEachDay.put(startDateLocalDate + " - " + startDateLocalDate.plusDays(6L), customOrderForDay);

                long resellOrderForDay = createQuery().select(Q_RESELL_ORDER.count())
                        .from(Q_RESELL_ORDER)
                        .leftJoin(Q_RESELL_ORDER.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(Expressions.predicate(
                                        Ops.BETWEEN,
                                        Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_RESELL_ORDER.createdAt),
                                        Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                        Expressions.constant(startDateLocalDate.plusDays(6L).atStartOfDay().atZone(targetZone).toInstant()))
                                .and(Q_FIRST_RING.branch.isNotNull())
                                .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne();
                totalResellOrders = totalResellOrders + resellOrderForDay;
                resellOrdersForEachDay.put(startDateLocalDate + " - " + startDateLocalDate.plusDays(6L), resellOrderForDay);

                long refundOrderForDay = createQuery().select(Q_REFUND.count())
                        .from(Q_REFUND)
                        .leftJoin(Q_REFUND.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(Expressions.predicate(
                                        Ops.BETWEEN,
                                        Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_REFUND.createdAt),
                                        Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                        Expressions.constant(startDateLocalDate.plusDays(6L).atStartOfDay().atZone(targetZone).toInstant()))
                                .and(Q_FIRST_RING.branch.isNotNull())
                                .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne();
                totalRefundOrders = totalRefundOrders + refundOrderForDay;
                refundOrdersForEachDay.put(startDateLocalDate + " - " + startDateLocalDate.plusDays(6L), resellOrderForDay);

                startDateLocalDate = startDateLocalDate.plusDays(7L);
            } else {
                long customOrderForDay = createQuery().select(Q_CUSTOM_ORDER.count())
                        .from(Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(Expressions.predicate(
                                        Ops.BETWEEN,
                                        Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt),
                                        Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                        Expressions.constant(startDateLocalDate.plusDays(remainder - 1).atStartOfDay().atZone(targetZone).toInstant()))
                                .and(Q_FIRST_RING.branch.isNotNull())
                                .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne();
                totalCustomOrders = totalCustomOrders + customOrderForDay;
                customOrdersForEachDay.put(startDateLocalDate + " - " + startDateLocalDate.plusDays(remainder - 1), customOrderForDay);

                long resellOrderForDay = createQuery().select(Q_RESELL_ORDER.count())
                        .from(Q_RESELL_ORDER)
                        .leftJoin(Q_RESELL_ORDER.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(Expressions.predicate(
                                        Ops.BETWEEN,
                                        Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_RESELL_ORDER.createdAt),
                                        Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                        Expressions.constant(startDateLocalDate.plusDays(remainder - 1).atStartOfDay().atZone(targetZone).toInstant()))
                                .and(Q_FIRST_RING.branch.isNotNull())
                                .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne();
                totalResellOrders = totalResellOrders + resellOrderForDay;
                resellOrdersForEachDay.put(startDateLocalDate + " - " + startDateLocalDate.plusDays(remainder - 1), resellOrderForDay);

                long refundOrderForDay = createQuery().select(Q_REFUND.count())
                        .from(Q_REFUND)
                        .leftJoin(Q_REFUND.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(Expressions.predicate(
                                        Ops.BETWEEN,
                                        Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_REFUND.createdAt),
                                        Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                        Expressions.constant(startDateLocalDate.plusDays(remainder - 1).atStartOfDay().atZone(targetZone).toInstant()))
                                .and(Q_FIRST_RING.branch.isNotNull())
                                .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne();
                totalRefundOrders = totalRefundOrders + refundOrderForDay;
                refundOrdersForEachDay.put(startDateLocalDate + " - " + startDateLocalDate.plusDays(remainder - 1), resellOrderForDay);
            }
        }

        return Orders.builder()
                .customOrdersForEach(customOrdersForEachDay)
                .refundOrdersForEach(refundOrdersForEachDay)
                .resellOrdersForEach(resellOrdersForEachDay)
                .totalCustomOrder(totalCustomOrders)
                .totalRefundOrder(totalRefundOrders)
                .totalResellOrder(totalResellOrders)
                .build();
    }

    private Orders getOrdersEachMonth(Instant start, Long quotient, Long remainder, Long branchId) {
        Long totalCustomOrders = 0L;
        Long totalResellOrders = 0L;
        Long totalRefundOrders = 0L;

        Map<String, Long> customOrdersForEachDay = new HashMap<>();
        Map<String, Long> resellOrdersForEachDay = new HashMap<>();
        Map<String, Long> refundOrdersForEachDay = new HashMap<>();

        LocalDate startDateLocalDate = start.atZone(ZoneId.systemDefault()).toLocalDate();
        for (int i = 0; i <= quotient; i++) {
            if (i < quotient) {
                long customOrderForDay = createQuery().select(Q_CUSTOM_ORDER.count())
                        .from(Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(Expressions.predicate(
                                        Ops.BETWEEN,
                                        Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt),
                                        Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                        Expressions.constant(startDateLocalDate.plusDays(29L).atStartOfDay().atZone(targetZone).toInstant()))
                                .and(Q_FIRST_RING.branch.isNotNull())
                                .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne();
                totalCustomOrders = totalCustomOrders + customOrderForDay;
                customOrdersForEachDay.put(startDateLocalDate + " - " + startDateLocalDate.plusDays(29L), customOrderForDay);

                long resellOrderForDay = createQuery().select(Q_RESELL_ORDER.count())
                        .from(Q_RESELL_ORDER)
                        .leftJoin(Q_RESELL_ORDER.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(Expressions.predicate(
                                        Ops.BETWEEN,
                                        Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_RESELL_ORDER.createdAt),
                                        Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                        Expressions.constant(startDateLocalDate.plusDays(29L).atStartOfDay().atZone(targetZone).toInstant()))
                                .and(Q_FIRST_RING.branch.isNotNull())
                                .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne();
                totalResellOrders = totalResellOrders + resellOrderForDay;
                resellOrdersForEachDay.put(startDateLocalDate + " - " + startDateLocalDate.plusDays(29L), resellOrderForDay);

                long refundOrderForDay = createQuery().select(Q_REFUND.count())
                        .from(Q_REFUND)
                        .leftJoin(Q_REFUND.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(Expressions.predicate(
                                        Ops.BETWEEN,
                                        Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_REFUND.createdAt),
                                        Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                        Expressions.constant(startDateLocalDate.plusDays(29L).atStartOfDay().atZone(targetZone).toInstant()))
                                .and(Q_FIRST_RING.branch.isNotNull())
                                .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne();
                totalRefundOrders = totalRefundOrders + refundOrderForDay;
                refundOrdersForEachDay.put(startDateLocalDate + " - " + startDateLocalDate.plusDays(29L), resellOrderForDay);

                startDateLocalDate = startDateLocalDate.plusDays(30L);
            } else {
                long customOrderForDay = createQuery().select(Q_CUSTOM_ORDER.count())
                        .from(Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(Expressions.predicate(
                                        Ops.BETWEEN,
                                        Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt),
                                        Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                        Expressions.constant(startDateLocalDate.plusDays(remainder - 1).atStartOfDay().atZone(targetZone).toInstant()))
                                .and(Q_FIRST_RING.branch.isNotNull())
                                .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne();
                totalCustomOrders = totalCustomOrders + customOrderForDay;
                customOrdersForEachDay.put(startDateLocalDate + " - " + startDateLocalDate.plusDays(remainder - 1), customOrderForDay);

                long resellOrderForDay = createQuery().select(Q_RESELL_ORDER.count())
                        .from(Q_RESELL_ORDER)
                        .leftJoin(Q_RESELL_ORDER.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(Expressions.predicate(
                                        Ops.BETWEEN,
                                        Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_RESELL_ORDER.createdAt),
                                        Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                        Expressions.constant(startDateLocalDate.plusDays(remainder - 1).atStartOfDay().atZone(targetZone).toInstant()))
                                .and(Q_FIRST_RING.branch.isNotNull())
                                .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne();
                totalResellOrders = totalResellOrders + resellOrderForDay;
                resellOrdersForEachDay.put(startDateLocalDate + " - " + startDateLocalDate.plusDays(remainder - 1), resellOrderForDay);

                long refundOrderForDay = createQuery().select(Q_REFUND.count())
                        .from(Q_REFUND)
                        .leftJoin(Q_REFUND.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(Expressions.predicate(
                                        Ops.BETWEEN,
                                        Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_REFUND.createdAt),
                                        Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                        Expressions.constant(startDateLocalDate.plusDays(remainder - 1).atStartOfDay().atZone(targetZone).toInstant()))
                                .and(Q_FIRST_RING.branch.isNotNull())
                                .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne();
                totalRefundOrders = totalRefundOrders + refundOrderForDay;
                refundOrdersForEachDay.put(startDateLocalDate + " - " + startDateLocalDate.plusDays(remainder - 1), resellOrderForDay);
            }
        }

        return Orders.builder()
                .customOrdersForEach(customOrdersForEachDay)
                .refundOrdersForEach(refundOrdersForEachDay)
                .resellOrdersForEach(resellOrdersForEachDay)
                .totalCustomOrder(totalCustomOrders)
                .totalRefundOrder(totalRefundOrders)
                .totalResellOrder(totalResellOrders)
                .build();
    }

    @Override
    public CombinedOrders getAllTypeOrders(ViewBranchOrdersPaginateInput input, Long branchId) {
        var numOfDays = ChronoUnit.DAYS.between(input.getStartDate(), input.getEndDate()) + 1L;
        LocalDate startDateLocalDate = input.getStartDate().atZone(ZoneId.systemDefault()).toLocalDate();
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());

        BlazeJPAQuery<CombinedOrder> customOrderQuery = createQuery();
        customOrderQuery
                .select(Projections.constructor(
                        CombinedOrder.class,
                        Q_CUSTOM_ORDER.id,
                        Q_CUSTOM_ORDER.totalPrice,
                        Expressions.constant(OrderTypeStatistic.CUSTOM),
                        Expressions.constant(APaymentMethod.TRANSFER),
                        Q_CUSTOM_ORDER.orderNo))
                .from(Q_CUSTOM_ORDER)
                .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                .leftJoin(Q_FIRST_RING.branch)
                .where(Expressions.predicate(
                                Ops.BETWEEN,
                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt),
                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                Expressions.constant(startDateLocalDate.plusDays(numOfDays).atStartOfDay().atZone(targetZone).toInstant()))
                        .and(Q_FIRST_RING.branch.isNotNull())
                        .and(Q_FIRST_RING.branch.id.eq(branchId)))
                .unionAll(customOrderQuery
                        .select(Projections.constructor(
                                CombinedOrder.class,
                                Q_RESELL_ORDER.id,
                                Q_RESELL_ORDER.amount,
                                Expressions.constant(OrderTypeStatistic.RESELL),
                                Expressions.constant(APaymentMethod.valueOf(Q_RESELL_ORDER.paymentMethod.toString())),
                                Q_RESELL_ORDER.orderNo))
                        .from(Q_RESELL_ORDER)
                        .leftJoin(Q_RESELL_ORDER.customOrder, Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(Expressions.predicate(
                                        Ops.BETWEEN,
                                        Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_RESELL_ORDER.createdAt),
                                        Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                        Expressions.constant(startDateLocalDate.plusDays(numOfDays).atStartOfDay().atZone(targetZone).toInstant()))
                                .and(Q_FIRST_RING.branch.isNotNull())
                                .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .unionAll(customOrderQuery
                                .select(Projections.constructor(
                                        CombinedOrder.class,
                                        Q_REFUND.id,
                                        Q_REFUND.amount,
                                        Expressions.constant(OrderTypeStatistic.REFUND),
                                        Expressions.constant(APaymentMethod.valueOf(Q_REFUND.method.toString())),
                                        Q_REFUND.orderNo))
                                .from(Q_REFUND)
                                .leftJoin(Q_REFUND.customOrder, Q_CUSTOM_ORDER)
                                .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                                .leftJoin(Q_FIRST_RING.branch)
                                .where(Expressions.predicate(
                                                Ops.BETWEEN,
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_REFUND.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(numOfDays).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_FIRST_RING.branch.isNotNull())
                                        .and(Q_FIRST_RING.branch.id.eq(branchId)))));
        long count = customOrderQuery.distinct().fetchCount();
        List<CombinedOrder> orders = customOrderQuery.limit(input.getPageSize()).offset(offset).fetch();
        return CombinedOrders.builder()
                .orders(orders)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    @Override
    public CustomOrders getCustomOrders(ViewCustomOrdersWithDateInput input, Long branchId) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        var numOfDays = ChronoUnit.DAYS.between(input.getStartDate(), input.getEndDate()) + 1L;
        LocalDate startDateLocalDate = input.getStartDate().atZone(ZoneId.systemDefault()).toLocalDate();
        BlazeJPAQuery<CustomOrder> query = createQuery()
                .select(Q_CUSTOM_ORDER)
                .from(Q_CUSTOM_ORDER)
                .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                .leftJoin(Q_FIRST_RING.branch)
                .where(Expressions.predicate(
                                Ops.BETWEEN,
                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt),
                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                Expressions.constant(startDateLocalDate.plusDays(numOfDays).atStartOfDay().atZone(targetZone).toInstant()))
                        .and(Q_FIRST_RING.branch.isNotNull())
                        .and(Q_FIRST_RING.branch.id.eq(branchId)));

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
    public ResellOrders geResellOrders(ViewResellOrdersWithDateInput input, Long branchId) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        var numOfDays = ChronoUnit.DAYS.between(input.getStartDate(), input.getEndDate()) + 1L;
        LocalDate startDateLocalDate = input.getStartDate().atZone(ZoneId.systemDefault()).toLocalDate();
        BlazeJPAQuery<ResellOrder> query = createQuery()
                .select(Q_RESELL_ORDER)
                .from(Q_RESELL_ORDER)
                .leftJoin(Q_RESELL_ORDER.customOrder, Q_CUSTOM_ORDER)
                .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                .leftJoin(Q_FIRST_RING.branch)
                .where(Expressions.predicate(
                                Ops.BETWEEN,
                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_RESELL_ORDER.createdAt),
                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                Expressions.constant(startDateLocalDate.plusDays(numOfDays).atStartOfDay().atZone(targetZone).toInstant()))
                        .and(Q_FIRST_RING.branch.isNotNull())
                        .and(Q_FIRST_RING.branch.id.eq(branchId)));

        long count = query.distinct().fetchCount();
        List<ResellOrder> resellOrders = query.limit(input.getPageSize()).offset(offset).fetch();
        return ResellOrders.builder()
                .resellOrders(resellOrders)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    @Override
    public Refunds getRefundOrders(ViewRefundOrdersWithDateInput input, Long branchId) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        var numOfDays = ChronoUnit.DAYS.between(input.getStartDate(), input.getEndDate()) + 1L;
        LocalDate startDateLocalDate = input.getStartDate().atZone(ZoneId.systemDefault()).toLocalDate();
        BlazeJPAQuery<Refund> query = createQuery()
                .select(Q_REFUND)
                .from(Q_REFUND)
                .leftJoin(Q_REFUND.customOrder, Q_CUSTOM_ORDER)
                .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                .leftJoin(Q_FIRST_RING.branch)
                .where(Expressions.predicate(
                                Ops.BETWEEN,
                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_REFUND.createdAt),
                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                Expressions.constant(startDateLocalDate.plusDays(numOfDays).atStartOfDay().atZone(targetZone).toInstant()))
                        .and(Q_FIRST_RING.branch.isNotNull())
                        .and(Q_FIRST_RING.branch.id.eq(branchId)));

        long count = query.distinct().fetchCount();
        List<Refund> refunds = query.limit(input.getPageSize()).offset(offset).fetch();
        return Refunds.builder()
                .refunds(refunds)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    @Override
    public Payments getPayments(ViewPaymentWithDateInput input, Long branchId) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        var numOfDays = ChronoUnit.DAYS.between(input.getStartDate(), input.getEndDate()) + 1L;
        LocalDate startDateLocalDate = input.getStartDate().atZone(ZoneId.systemDefault()).toLocalDate();
        BlazeJPAQuery<Payment> query = createQuery()
                .select(Q_PAYMENT)
                .from(Q_PAYMENT)
                .leftJoin(Q_PAYMENT.craftingStage, Q_CRAFTING_STAGE).fetchJoin()
                .leftJoin(Q_CRAFTING_STAGE.customOrder, Q_CUSTOM_ORDER).fetchJoin()
                .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING).fetchJoin()
                .leftJoin(Q_FIRST_RING.branch).fetchJoin()
                .where(Expressions.predicate(
                                Ops.BETWEEN,
                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_PAYMENT.createdAt),
                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                Expressions.constant(startDateLocalDate.plusDays(numOfDays).atStartOfDay().atZone(targetZone).toInstant()))
                        .and(Q_PAYMENT.craftingStage.isNotNull())
                        .and(Q_PAYMENT.status.eq(PaymentStatus.SUCCESSFUL))
                        .and(Q_FIRST_RING.branch.isNotNull())
                        .and(Q_FIRST_RING.branch.id.eq(branchId)));

        long count = query.distinct().fetchCount();
        List<Payment> payments = query.limit(input.getPageSize()).offset(offset).fetch();
        return Payments.builder()
                .payments(payments)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }
}
