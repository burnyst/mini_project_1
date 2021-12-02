package com.fastcampus.component.service;

import com.fastcampus.component.dao.mybatis.UserDAOMybatis;
import com.fastcampus.component.vo.BlogVO;
import com.fastcampus.component.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

//	final UserDAO userDAO;
	final UserDAOMybatis mybatis;

	public UserServiceImpl(UserDAOMybatis mybatis) {
//		this.userDAO = userDAO;
		this.mybatis = mybatis;
	}


	@Override
	public UserVO getUser(UserVO vo) {
//		return mybatis.getUser(vo);
		return mybatis.getUser(vo);
	}

	@Override
	public void insertUser(UserVO vo) {
		mybatis.insertUser(vo);
	}

	@Override
	public void updateUser(UserVO vo) {
		mybatis.updateUser(vo);
	}

	@Override
	public void deleteUser(UserVO vo) {
		mybatis.deleteUser(vo);
	}

	@Override
	public BlogVO getUserBlog(UserVO vo) {
//		return mybatis.getUserBlog(vo);
		return mybatis.getUserBlog(vo);
	}

}
