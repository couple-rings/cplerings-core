package com.cplerings.core.infrastructure.datasource.jewelry;

import java.util.List;
import java.util.Optional;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.cplerings.core.application.jewelry.datasource.CreateJewelryDataSource;
import com.cplerings.core.application.jewelry.datasource.ViewJewelryCategoriesDataSource;
import com.cplerings.core.application.jewelry.datasource.result.JewelryCategories;
import com.cplerings.core.application.jewelry.input.ViewJewelryCategoriesInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.branch.QBranch;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.QDesign;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.diamond.QDiamond;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.jewelry.JewelryCategory;
import com.cplerings.core.domain.jewelry.QJewelryCategory;
import com.cplerings.core.domain.metal.MetalSpecification;
import com.cplerings.core.domain.metal.QMetalSpecification;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.JewelryRepository;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class SharedJewelryDataSource extends AbstractDataSource implements CreateJewelryDataSource, ViewJewelryCategoriesDataSource {

    private static final QDesign Q_DESIGN = QDesign.design;
    private static final QMetalSpecification Q_METAL_SPECIFICATION = QMetalSpecification.metalSpecification;
    private static final QBranch Q_BRANCH = QBranch.branch;
    private static final QDiamond Q_DIAMOND = QDiamond.diamond;
    private static final QJewelryCategory Q_JEWELRY_CATEGORY = QJewelryCategory.jewelryCategory;

    private final JewelryRepository jewelryRepository;

    @Override
    public Optional<Branch> getBranchById(Long id) {
        return Optional.ofNullable(createQuery()
                .select(Q_BRANCH)
                .from(Q_BRANCH)
                .where(Q_BRANCH.id.eq(id))
                .fetchFirst());
    }

    @Override
    public Optional<Design> getDesignById(Long id) {
        return Optional.ofNullable(createQuery()
                .select(Q_DESIGN)
                .from(Q_DESIGN)
                .where(Q_DESIGN.id.eq(id))
                .fetchFirst());
    }

    @Override
    public Optional<Diamond> getDiamondById(Long id) {
        return Optional.ofNullable(createQuery()
                .select(Q_DIAMOND)
                .from(Q_DIAMOND)
                .where(Q_DIAMOND.id.eq(id))
                .fetchFirst());
    }

    @Override
    public Optional<MetalSpecification> getMetalSpecById(Long id) {
        return Optional.ofNullable(createQuery()
                .select(Q_METAL_SPECIFICATION)
                .from(Q_METAL_SPECIFICATION)
                .where(Q_METAL_SPECIFICATION.id.eq(id))
                .fetchFirst());
    }

    @Override
    public Jewelry save(Jewelry jewelry) {
        updateAuditor(jewelry);
        return jewelryRepository.save(jewelry);
    }

    @Override
    public JewelryCategories getJewelryCategories(ViewJewelryCategoriesInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<JewelryCategory> query = createQuery()
                .select(Q_JEWELRY_CATEGORY)
                .from(Q_JEWELRY_CATEGORY);
        long count = query.distinct().fetchCount();
        List<JewelryCategory> jewelryCategories = query.limit(input.getPageSize()).offset(offset).fetch();
        return JewelryCategories.builder()
                .jewelryCategories(jewelryCategories)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }
}
