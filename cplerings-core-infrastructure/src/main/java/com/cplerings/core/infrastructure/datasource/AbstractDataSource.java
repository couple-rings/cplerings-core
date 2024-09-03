package com.cplerings.core.infrastructure.datasource;

import org.springframework.beans.factory.annotation.Autowired;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataSource
public abstract class AbstractDataSource {

    @PersistenceContext
    protected EntityManager em;

    protected CriteriaBuilderFactory cbf;

    @Autowired
    public void setCriteriaBuilderFactory(CriteriaBuilderFactory cbf) {
        this.cbf = cbf;
    }

    protected <T> BlazeJPAQuery<T> createQuery() {
        return new BlazeJPAQuery<>(em, cbf);
    }
}
