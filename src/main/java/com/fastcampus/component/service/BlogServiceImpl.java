package com.fastcampus.component.service;

import com.fastcampus.component.dao.jpa.PostRepository;
import com.fastcampus.component.vo.BlogVO;
import com.fastcampus.component.dao.jdbc.BlogDAO;
import com.fastcampus.component.vo.PostVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    final BlogDAO blogDAO;
    public BlogServiceImpl(BlogDAO blogDAO, PostRepository postRepository) {
        this.blogDAO = blogDAO;
    }

    @Override
    public Boolean hasSearchWord(BlogVO vo) {
        if (vo.getSearchKeyword() == null && vo.getSearchCondition() == null) {
            return false;
        }
        return true;
    }

    @Override
    public BlogVO getBlog(BlogVO vo) {
        return blogDAO.getBlog(vo);
    }

    @Override
    public List<BlogVO> getBlogList(BlogVO vo) {
        return blogDAO.getBlogList(vo);
    }

    @Override
    public void registerBlog(BlogVO vo) {
        blogDAO.registerBlog(vo);
    }

    @Override
    public void updateBlog(BlogVO vo) {
        blogDAO.updateBlog(vo);
    }

    @Override
    public void deleteBlog(BlogVO vo) {
        blogDAO.deleteBlog(vo);
    }

    @Override
    public List<PostVO> getBlogPosts(BlogVO vo) {
        return blogDAO.getBlogPosts(vo);
    }

}
