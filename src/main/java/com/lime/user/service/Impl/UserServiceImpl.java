package com.lime.user.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lime.login.service.UserService;
import com.lime.user.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	public void insertMember(UserVO user){
		userDAO.insertMember(user);
		System.out.println("UserSIMP");
	}

	@Override
	public int idCheck(String userId) {
		System.out.println("UserSIMP idCheck");
		return userDAO.idCheck(userId);
	}

	@Override
	public UserVO login(UserVO user) {
		return userDAO.login(user);
	}
}
