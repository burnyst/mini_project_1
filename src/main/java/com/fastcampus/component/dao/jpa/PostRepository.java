package com.fastcampus.component.dao.jpa;

import com.fastcampus.component.vo.PostVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostVO, Integer> {

    List<PostVO> findAllByCategoryIdOrderByCreatedDateDesc(Integer categoryId);

    PostVO findByPostId(Integer postId);

}
