package com.fastcampus.component.service;

import com.fastcampus.component.vo.CategoryVO;

import java.util.List;

public interface CategoryService {
    // 미분류 카테고리 추가
    public void addDefaultCategory(CategoryVO vo);
    // 카테고리 추가
    void addCategory(CategoryVO vo);

    // 설정 창에서 카테고리 보여주기
    List<CategoryVO> getCategory(CategoryVO vo);

    void updateCategory(CategoryVO vo);

    void deleteCategory(CategoryVO vo);

    CategoryVO getCategoryName(CategoryVO vo);

    CategoryVO selectCategory(CategoryVO vo);
}
