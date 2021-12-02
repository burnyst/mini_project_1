package com.fastcampus.component.vo;

import lombok.Data;

@Data
public class BlogVO {
    private int blogId;
    private String userName;
    private String title;
    private String tag;
    private int cntDisplayPost;
    private String status;

    private String searchCondition;
    private String searchKeyword;
}
