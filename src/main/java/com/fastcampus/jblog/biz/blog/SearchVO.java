package com.fastcampus.jblog.biz.blog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchVO {
    private String searchValue;
    private String searchType;
}
