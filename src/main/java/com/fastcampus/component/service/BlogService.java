package com.fastcampus.component.service;

import com.fastcampus.component.vo.BlogVO;
import com.fastcampus.component.vo.PostVO;

import java.util.List;

public interface BlogService {

    // keyword check
    Boolean hasSearchWord(BlogVO vo);

    // 블로그 조회
    BlogVO getBlog(BlogVO vo);

    // 블로그 리스트
    List<BlogVO> getBlogList(BlogVO vo);

    // 블로그 등록
    void registerBlog(BlogVO vo);

    // 블로그 수정
    void updateBlog(BlogVO vo);

    // 블로그 삭제 요청
    void deleteBlog(BlogVO vo);

    List<PostVO> getBlogPosts(BlogVO vo);

}
