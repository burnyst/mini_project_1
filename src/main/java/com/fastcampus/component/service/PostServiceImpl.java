package com.fastcampus.component.service;

import com.fastcampus.component.dao.jdbc.PostDAO;
import com.fastcampus.component.dao.jpa.PostRepository;
import com.fastcampus.component.vo.PostVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void createPost(PostVO vo) {
        if (postRepository.findByPostId(vo.getPostId()) == null){
            postRepository.save(vo);
        }
    }

    @Override
    public PostVO getPost(PostVO vo) {
        return postRepository.findByPostId(vo.getPostId());
    }

    @Override
    public List<PostVO> getPosts(PostVO vo) {
        return postRepository.findAllByCategoryIdOrderByCreatedDateDesc(vo.getCategoryId());
    }

    @Override
    public void updatePost(PostVO vo) {
        PostVO postVO = postRepository.findByPostId(vo.getPostId());
        postVO.setContent(vo.getContent());
        postVO.setTitle(vo.getTitle());
        postVO.setCategoryId(vo.getCategoryId());
        postRepository.save(postVO);
    }

    @Override
    public void deletePost(PostVO vo) {
        PostVO postVO = postRepository.findByPostId(vo.getPostId());
        postRepository.delete(postVO);
    }
}
