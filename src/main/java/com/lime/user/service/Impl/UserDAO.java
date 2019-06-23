package com.lime.user.service.Impl;

import org.springframework.stereotype.Repository;

import com.lime.user.vo.UserVO;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("userDAO")
public class UserDAO extends EgovAbstractMapper {

	public void insertMember(UserVO user){
		insert("Login.insertMember", user);
		System.out.println("----User dao insertMember---------");
	}

	public int idCheck(String userId) {
		System.out.println("----User dao idCheck---------");
		return selectOne("Login.idCheck", userId);
	}
	
	public UserVO login(UserVO user){
		return selectOne("Login.login", user);
		
	}
	
}
