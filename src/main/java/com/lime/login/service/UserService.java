package com.lime.login.service;

import com.lime.user.vo.UserVO;

public interface UserService {

		void insertMember(UserVO user);
		public int idCheck(String userId);
		public UserVO login(UserVO user);
}
