package com.cplerings.core.domain.blog;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.file.Image;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_blog")
public class Blog extends AbstractEntity {

    private static final String BLOG_SEQUENCE = "blog_seq";

    @Id
    @GeneratedValue(generator = BLOG_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = BLOG_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "blog_id")
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Lob
    @Column(name = "content", columnDefinition = DatabaseConstant.LOB_DEFINITION, nullable = false)
    private String content;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cover_image", unique = true)
    private Image coverImage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "blogger_id")
    private Account blogger;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @OneToMany(mappedBy = "blog", fetch = FetchType.LAZY)
    private Set<BlogTag> tags;
}
