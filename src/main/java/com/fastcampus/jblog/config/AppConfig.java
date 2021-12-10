package com.fastcampus.jblog.config;

import com.fastcampus.jblog.biz.blog.BlogDAO;
import com.fastcampus.jblog.biz.blog.BlogDAOJdbc;
import com.fastcampus.jblog.biz.category.CategoryDAO;
import com.fastcampus.jblog.biz.category.CategoryDAOJdbc;
import com.fastcampus.jblog.biz.post.PostDAO;
import com.fastcampus.jblog.biz.post.PostDAOJdbc;
import com.fastcampus.jblog.biz.user.UserDAO;
import com.fastcampus.jblog.biz.user.UserDAOJdbc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserDAO userDAO() {
        return new UserDAOJdbc();
//        return new UserDAOMybatis();
//        return new UserDAOJpa();
    }

    @Bean
    public BlogDAO blogDAO() {
        return new BlogDAOJdbc();
//        return new BlogDAOMybatis();
//        return new BlogDAOJpa();
    }

    @Bean
    public CategoryDAO categoryDAO() {
        return new CategoryDAOJdbc();
//        return new CategoryDAOMybatis();
//        return new CategoryDAOJpa();
    }

    @Bean
    public PostDAO postDAO() {
        return new PostDAOJdbc();
//        return new PostDAOMybatis();
//        return new PostDAOJpa();
    }
}
