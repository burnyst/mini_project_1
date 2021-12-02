package com.fastcampus.component.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryVO {
    private int categoryId;
    private int blogId;
    private String categoryName;
    private String displayType;
    private int cntDisplayPost;
    private String description;
    private String deletion;
    private Date createdDate = new Date();
    private Date modifiedDate = new Date();
}
