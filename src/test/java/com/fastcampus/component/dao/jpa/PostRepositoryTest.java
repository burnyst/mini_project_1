package com.fastcampus.component.dao.jpa;

import com.fastcampus.component.vo.PostVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    void test_1(){
        PostVO post = PostVO.builder("테스트", "테스트 내용입니다.")
                .categoryId(3)
                .build();
        postRepository.save(post);

        System.out.println(postRepository.findByPostId(3).toString());;


    }

    @DisplayName("13.2")
    @Test
    void test_2(){
        postRepository.findAllByCategoryIdOrderByCreatedDateDesc(2).forEach(System.out::println);
    }

    @DisplayName("13.3")
    @Test
    void test_3(){
        PostVO postVO = postRepository.findByPostId(2);
        System.out.println(postVO);
        postRepository.delete(postVO);
        System.out.println(postRepository.findByPostId(2));
    }

}