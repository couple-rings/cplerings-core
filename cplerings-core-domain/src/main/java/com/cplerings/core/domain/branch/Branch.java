package com.cplerings.core.domain.branch;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.shared.AbstractEntity;

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
@Table(name = "tbl_branch")
public class Branch extends AbstractEntity {

    private static final String BRANCH_SEQUENCE = "branch_seq";

    @Id
    @GeneratedValue(generator = BRANCH_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = BRANCH_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "branch_id")
    private Long id;

    @Column(name = "address", nullable = false, unique = true)
    private String address;

    @Column(name = "store_name", nullable = false, unique = true)
    private String storeName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "cover_image", nullable = false)
    private String coverImage;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    private Set<BranchStaff> staffs;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    private Set<BranchManager> managers;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    private Set<Ring> rings;
}
