package com.lime.login.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@RequestMapping(value = "/login/assign.do",method=RequestMethod.POST)
	public String selectLogin(@ModelAttribute("user") UserVO user, 
			HttpServletRequest request, ModelMap model) throws Exception {
		System.out.println("-------selectLogin-------");
		Map<String, Object> inOutMap  = CommUtils.getFormParam(request);
		System.out.println("inOutMap"+ inOutMap);
		
		UserVO resultVO = new UserVO();
		
		resultVO = userService.login(user);
		
		if(resultVO != null && !resultVO.getUserId().equals("") && !resultVO.getPwd().equals("")){
			request.getSession().setAttribute("user", resultVO);
			System.out.println("로그인 성공"+request.getSession().getAttribute("user"));
			return "forward:/account/accountList.do";
		}else{
			System.out.println("로그인 실패"+request.getSession().getAttribute("user"));
			model.addAttribute("msg", "사용자의 ID 혹은 패스워드가 일치하지 않습니다.");
			return "redirect:/login.do";
		}
//		model.put("inOutMap", inOutMap);
		
	}

	@RequestMapping("/login/logout.do")
	public String selectLogout(@ModelAttribute("user") UserVO user, HttpServletRequest request){
		
		request.getSession().removeAttribute("user");
		
		return "redirect:/login/login.do";
		
	}

}// end of class
