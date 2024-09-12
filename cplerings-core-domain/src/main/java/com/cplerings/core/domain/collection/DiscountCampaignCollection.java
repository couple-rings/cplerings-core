package com.cplerings.core.domain.collection;

import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DISCOUNT_CAMPAIGN_COLLECTION")
public class DiscountCampaignCollection extends AbstractEntity {

    private static final String DISCOUNT_CAMPAIGN_COLLECTION_SEQ = "RING_SEQ";

    @Id
    @GeneratedValue(generator = DISCOUNT_CAMPAIGN_COLLECTION_SEQ, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = DISCOUNT_CAMPAIGN_COLLECTION_SEQ, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "DISCOUNT_CAMPAIGN_COLLECTION_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COLLECTION_ID")
    private Collection collection;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DISCOUNT_CAMPAIGN_ID")
    private DiscountCampaign discountCampaign;
}
