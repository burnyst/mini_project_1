package com.fastcampus.jblog.biz.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserDAO userDAO;

	@Override
	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
	}
}
