package com.fastcampus.component.service;

import com.fastcampus.component.vo.CategoryVO;
import com.fastcampus.component.dao.jdbc.CategoryDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public void addDefaultCategory(CategoryVO vo) {
        categoryDAO.addDefaultCategory(vo);
    }

    @Override
    public void addCategory(CategoryVO vo) {
        categoryDAO.addCategory(vo);
    }

    @Override
    public List<CategoryVO> getCategory(CategoryVO vo) {
        return categoryDAO.getCategory(vo);
    }

    @Override
    public void updateCategory(CategoryVO vo) {
        categoryDAO.updateCategory(vo);
    }

    @Override
    public void deleteCategory(CategoryVO vo) {
        categoryDAO.deleteCategory(vo);
    }

    @Override
    public CategoryVO getCategoryName(CategoryVO vo) {
        return categoryDAO.getCategoryName(vo);
    }

    @Override
    public CategoryVO selectCategory(CategoryVO vo) {
        return categoryDAO.selectCategory(vo);
    }
}
