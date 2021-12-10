package com.fastcampus.jblog.biz.category;

import com.fastcampus.jblog.biz.blog.Blog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Integer categoryId;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    private String categoryName;

    private String displayType;

    private int cntDisplayPost;

    private String description;

    @Column(columnDefinition = "date default sysdate")
    private Date createdDate;

    @Column(columnDefinition = "date default sysdate")
    private Date modifiedDate;
}
