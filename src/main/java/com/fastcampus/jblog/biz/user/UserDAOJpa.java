package com.fastcampus.jblog.biz.user;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserDAOJpa implements UserDAO {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserVO getUser(UserVO vo) {
        Optional<User> user = userRepository.findByIdAndPassword(vo.getId(), vo.getPassword());
        return UserVO.entityToVO(user.orElse(new User()));
    }
}
