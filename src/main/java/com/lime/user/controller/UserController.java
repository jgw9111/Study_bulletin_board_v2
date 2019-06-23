package com.lime.user.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.lime.login.service.UserService;
import com.lime.user.vo.UserVO;
import com.lime.util.CommUtils;

@Controller
public class UserController {
	@Resource(name = "jsonView")
	private MappingJackson2JsonView jsonView;

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping(value = "/user/userInsert.do")
	public String userInsert() {
		System.out.println("-USER CTRL-");
		return "/user/userInsert";
	}

	@RequestMapping(value = "/user/join.do", method = RequestMethod.POST)
	public ModelAndView userJoin(UserVO user, HttpServletRequest request)
			throws Exception {
		userService.insertMember(user);
		System.out.println("회원가입완료");

		Map<String, Object> inOutMap = CommUtils.getFormParam(request);
		inOutMap.put("result", "ok");

		return new ModelAndView(jsonView, inOutMap);
	}

}
