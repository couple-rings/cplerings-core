package com.cplerings.core.domain.collection;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_discount_campaign", schema = DatabaseConstant.SCHEME_CORE)
public class DiscountCampaign extends AbstractEntity {

    private static final String DISCOUNT_CAMPAIGN_SEQUENCE = "discount_campaign_seq";

    @Id
    @GeneratedValue(generator = DISCOUNT_CAMPAIGN_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = DISCOUNT_CAMPAIGN_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "discount_campaign_id")
    private Long id;

    @OneToMany(mappedBy = "discountCampaign")
    private Set<DiscountCampaignCollection> discountCampaignCollections;
}
