package com.fastcampus.component.dao.mybatis;

import com.fastcampus.component.vo.BlogVO;
import com.fastcampus.component.vo.UserVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UserDAOMybatis{
    public SqlSessionTemplate mybatis;

    public UserDAOMybatis(SqlSessionTemplate mybatis) {
        this.mybatis = mybatis;
    }

    public UserVO getUser(UserVO vo){
        return (UserVO) mybatis.selectOne("UserDAO.getUser", vo);
    }

    public BlogVO getUserBlog(UserVO vo){
        return (BlogVO) mybatis.selectOne("UserDAO.getUserBlog", vo);
    }

    public void insertUser(UserVO vo){
        mybatis.insert("UserDAO.insertUser", vo);
    }

    public void updateUser(UserVO vo){
        mybatis.update("UserDAO.updateUser", vo);
    }

    public void deleteUser(UserVO vo){
        mybatis.delete("UserDAO.deleteUser", vo);
    }
}
