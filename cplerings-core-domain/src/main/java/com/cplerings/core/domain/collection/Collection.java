package com.cplerings.core.domain.collection;

import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;
import com.cplerings.core.domain.design.Design;

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
@Table(name = "COLLECTION")
public class Collection extends AbstractEntity {

    private static final String COLLECTION_SEQUENCE = "COLLECTION_SEQ";

    @Id
    @GeneratedValue(generator = COLLECTION_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = COLLECTION_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "COLLECTION_ID")
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @OneToMany(mappedBy = "collection")
    private Set<Design> designs;

    @OneToMany(mappedBy = "collection")
    private Set<DiscountCampaignCollection> discountCampaignCollections;
}
