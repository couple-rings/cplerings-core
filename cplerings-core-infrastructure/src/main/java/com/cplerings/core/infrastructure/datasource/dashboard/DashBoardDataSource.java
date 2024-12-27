package com.cplerings.core.infrastructure.datasource.dashboard;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cplerings.core.application.dashboard.datasource.ViewBranchRevenueDataSource;
import com.cplerings.core.application.dashboard.datasource.data.Revenue;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.branch.QBranch;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.order.QCustomOrder;
import com.cplerings.core.domain.refund.QRefund;
import com.cplerings.core.domain.resell.QResellOrder;
import com.cplerings.core.domain.ring.QRing;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.dsl.Expressions;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class DashBoardDataSource extends AbstractDataSource implements ViewBranchRevenueDataSource {

    private static final QCustomOrder Q_CUSTOM_ORDER = QCustomOrder.customOrder;
    private static final QResellOrder Q_RESELL_ORDER = QResellOrder.resellOrder;
    private static final QRefund Q_REFUND = QRefund.refund;
    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QRing Q_FIRST_RING = QRing.ring;
    private static final QBranch Q_BRANCH = QBranch.branch;

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
        List<BigDecimal> revenueEachDayForCustomOrder = new ArrayList<>();

        LocalDate startDateLocalDate = startDate.atZone(ZoneId.systemDefault()).toLocalDate();
        for (int i = 1; i <= numOfDays; i++) {
            BigDecimal totalRevenueEachDay = BigDecimal.ZERO;
            BigDecimal customOrderRevenueEachDay = Optional.ofNullable(createQuery().select(Q_CUSTOM_ORDER.totalPrice.amount.sum())
                    .from(Q_CUSTOM_ORDER)
                    .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                    .leftJoin(Q_FIRST_RING.branch, Q_BRANCH)
                    .where(
                            Expressions.stringTemplate(
                                            "FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt
                                    ).eq(Expressions.constant(startDateLocalDate.atStartOfDay().atZone(targetZone).toInstant())
                                    )
                                    .and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.COMPLETED))
                                    .and(Q_FIRST_RING.branch.isNotNull())
                                    .and(Q_FIRST_RING.branch.id.eq(branchId)))
                    .fetchOne()).orElse(BigDecimal.ZERO);
            totalRevenue = totalRevenue.add(customOrderRevenueEachDay);
            totalRevenueEachDay = totalRevenueEachDay.add(customOrderRevenueEachDay);
            BigDecimal resellOrderRevenueEachDay =  Optional.ofNullable(createQuery().select(Q_RESELL_ORDER.amount.amount.sum())
                    .from(Q_RESELL_ORDER)
                    .leftJoin(Q_RESELL_ORDER.customOrder, Q_CUSTOM_ORDER)
                    .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                    .leftJoin(Q_FIRST_RING.branch)
                    .where(
                            Expressions.stringTemplate(
                                    "FUNCTION('DATE_TRUNC', 'day', {0})", Q_RESELL_ORDER.createdAt
                            ).eq( Expressions.constant(startDateLocalDate.atStartOfDay().atZone(targetZone).toInstant())
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
            revenueEachDayForCustomOrder.add(totalRevenueEachDay);

            startDateLocalDate = startDateLocalDate.plusDays(i);
        }
        return Revenue.builder()
                .totalRevenue(totalRevenue)
                .revenueForEach(revenueEachDayForCustomOrder)
                .build();
    }

    private Revenue getTotalRevenueOfCustomOrderForEachWeek(Instant startDate, Long quotient, Long remainder, Long branchId) {
        BigDecimal totalRevenue = BigDecimal.ZERO;
        List<BigDecimal> revenueEachWeekForCustomOrder = new ArrayList<>();

        LocalDate startDateLocalDate = startDate.atZone(ZoneId.systemDefault()).toLocalDate();
        for (int i = 1; i <= quotient; i++) {
            if (i < quotient) {
                BigDecimal totalRevenueEachWeek = BigDecimal.ZERO;
                BigDecimal customOrderRevenueEachWeek = Optional.ofNullable(createQuery().select(Q_CUSTOM_ORDER.totalPrice.amount.sum())
                        .from(Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(
                                Expressions.predicate(
                                                Ops.BETWEEN,
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(6L).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.COMPLETED))
                                        .and(Q_FIRST_RING.branch.isNotNull().isNotNull())
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
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt),
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
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(6L).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_FIRST_RING.branch.isNotNull())
                                        .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne()).orElse(BigDecimal.ZERO);
                totalRevenue = totalRevenue.subtract(refundOrderRevenueEachWeek);
                totalRevenueEachWeek = totalRevenueEachWeek.subtract(refundOrderRevenueEachWeek);
                revenueEachWeekForCustomOrder.add(totalRevenueEachWeek);

                startDateLocalDate = startDateLocalDate.plusDays(7);
            } else {
                BigDecimal totalRevenueEachWeek = BigDecimal.ZERO;
                BigDecimal customOrderRevenueEachWeek = Optional.ofNullable(createQuery().select(Q_CUSTOM_ORDER.totalPrice.amount.sum())
                        .from(Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(
                                Expressions.predicate(
                                                Ops.BETWEEN,
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(remainder).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.COMPLETED))
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
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(remainder).atStartOfDay().atZone(targetZone).toInstant()))
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
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(remainder).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_FIRST_RING.branch.isNotNull())
                                        .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne()).orElse(BigDecimal.ZERO);
                totalRevenue = totalRevenue.subtract(refundOrderRevenueEachWeek);
                totalRevenueEachWeek = totalRevenueEachWeek.subtract(refundOrderRevenueEachWeek);
                revenueEachWeekForCustomOrder.add(totalRevenueEachWeek);
            }
        }
        return Revenue.builder()
                .totalRevenue(totalRevenue)
                .revenueForEach(revenueEachWeekForCustomOrder)
                .build();
    }

    private Revenue getTotalRevenueOfCustomOrderForEachMonth(Instant startDate, Long quotient, Long remainder, Long branchId) {
        BigDecimal totalRevenue = BigDecimal.ZERO;
        List<BigDecimal> revenueEachMonthForCustomOrder = new ArrayList<>();

        LocalDate startDateLocalDate = startDate.atZone(ZoneId.systemDefault()).toLocalDate();
        for (int i = 1; i <= quotient; i++) {
            if (i < quotient) {
                BigDecimal totalRevenueEachMonth = BigDecimal.ZERO;
                BigDecimal customOrderRevenueEachMonth = Optional.ofNullable(createQuery().select(Q_CUSTOM_ORDER.totalPrice.amount.sum())
                        .from(Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(
                                Expressions.predicate(
                                                Ops.BETWEEN,
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(29L).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.COMPLETED))
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
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt),
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
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(29L).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_FIRST_RING.branch.id.isNotNull())
                                        .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne()).orElse(BigDecimal.ZERO);
                totalRevenue = totalRevenue.subtract(refundOrderRevenueEachMonth);
                totalRevenueEachMonth = totalRevenueEachMonth.subtract(refundOrderRevenueEachMonth);
                revenueEachMonthForCustomOrder.add(totalRevenueEachMonth);

                startDateLocalDate = startDateLocalDate.plusDays(30);
            } else {
                BigDecimal totalRevenueEachMonth = BigDecimal.ZERO;
                BigDecimal customOrderRevenueEachMonth = Optional.ofNullable(createQuery().select(Q_CUSTOM_ORDER.totalPrice.amount.sum())
                        .from(Q_CUSTOM_ORDER)
                        .leftJoin(Q_CUSTOM_ORDER.firstRing, Q_FIRST_RING)
                        .leftJoin(Q_FIRST_RING.branch)
                        .where(
                                Expressions.predicate(
                                                Ops.BETWEEN,
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(remainder).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.COMPLETED))
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
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(remainder).atStartOfDay().atZone(targetZone).toInstant()))
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
                                                Expressions.stringTemplate("FUNCTION('DATE_TRUNC', 'day', {0})", Q_CUSTOM_ORDER.createdAt),
                                                Expressions.constant(startDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                                Expressions.constant(startDateLocalDate.plusDays(remainder).atStartOfDay().atZone(targetZone).toInstant()))
                                        .and(Q_FIRST_RING.branch.isNotNull())
                                        .and(Q_FIRST_RING.branch.id.eq(branchId)))
                        .fetchOne()).orElse(BigDecimal.ZERO);
                totalRevenue = totalRevenue.subtract(refundOrderRevenueEachMonth);
                totalRevenueEachMonth = totalRevenueEachMonth.subtract(refundOrderRevenueEachMonth);
                revenueEachMonthForCustomOrder.add(totalRevenueEachMonth);
            }
        }
        return Revenue.builder()
                .totalRevenue(totalRevenue)
                .revenueForEach(revenueEachMonthForCustomOrder)
                .build();
    }
}
