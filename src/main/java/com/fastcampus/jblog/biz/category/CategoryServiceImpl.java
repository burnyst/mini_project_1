package com.fastcampus.jblog.biz.category;

import com.fastcampus.jblog.biz.blog.BlogVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    public List<CategoryVO> getCategorys(BlogVO blog) {
        return categoryDAO.getCategorys(blog);
    }

    @Override
    public CategoryVO getCategory(CategoryVO category) {
        return categoryDAO.getCategory(category);
    }

    @Override
    public void insertCategory(CategoryVO category, BlogVO blog) {
        // BLOG 셋팅
        category.setBlog(blog);
        categoryDAO.insertCategory(category);
    }

    @Override
    public void deleteCategory(CategoryVO category) {
        categoryDAO.deleteCategory(category);
    }

    @Override
    public void updateCategory(CategoryVO category) {
        categoryDAO.updateCategory(category);
    }
}
