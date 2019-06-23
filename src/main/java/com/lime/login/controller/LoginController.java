package com.lime.login.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.lime.common.service.CommonService;
import com.lime.login.service.UserService;
import com.lime.user.vo.UserVO;
import com.lime.util.CommUtils;


@Controller
public class LoginController {


	@Resource(name = "jsonView")
	private MappingJackson2JsonView jsonView;

	@Resource(name="commonService")
	private CommonService commonService;

	@Resource(name = "userService")
	private UserService userService;
	
	@RequestMapping(value="/login/login.do")
	public String loginview(HttpServletRequest request ) {
		System.out.println("--Log CTRL--");
		return "/login/login";
	}
	
	@ResponseBody
	@RequestMapping(value="/login/idCkedAjax.do",method=RequestMethod.POST)
	public ModelAndView idCkedAjax(HttpServletRequest request ) throws Exception {
		Map<String, Object> inOutMap  = CommUtils.getFormParam(request);
		System.out.println("===id checkkkkkkkkk===>>  "+request.getParameter("userId"));
		
		String userID = request.getParameter("userId");
		int idCheck = userService.idCheck(userID);
		
		inOutMap.put("idcnt",idCheck);
		return new ModelAndView(jsonView, inOutMap);
	}
	
	@ResponseBody
	@RequestMapping(value = "/login/assign.do",method=RequestMethod.POST)
	public ModelAndView selectLogin(HttpServletRequest request, ModelMap model) throws Exception {
		System.out.println("-------selectLogin-------");
		Map<String, Object> inOutMap  = CommUtils.getFormParam(request);
		HttpSession session = request.getSession();
		System.out.println("inOutMap"+ inOutMap);
		
		UserVO user = new UserVO();
		user.setUserId(request.getParameter("userId"));
		user.setPwd(request.getParameter("pwd"));
		user.setUserName(request.getParameter("userName"));
		
		UserVO login = userService.login(user);
		
		if(login == null){
			session.setAttribute("user", null);
			System.out.println("로그인 실패"+session.getAttribute("user"));
		}else{
			session.setAttribute("user", login);
			System.out.println("로그인 성공"+session.getAttribute("user"));
		}
		model.put("inOutMap", inOutMap);
		model.put("user", login);
		
		return new ModelAndView(jsonView, inOutMap);
	}

	@RequestMapping("/login/logout.do")
	public String selectLogout(HttpServletRequest request){
		HttpSession session = request.getSession();
		System.out.println("로그아웃 1!!!!!!!"+session);
		session.invalidate();
		System.out.println("로그아웃 2!!!!!!!"+session);
		return "/login/login";
		
	}

}// end of class
