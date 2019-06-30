package com.lime.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lime.user.vo.UserVO;

public class LoginInterceptor implements HandlerInterceptor{
	// interceptor를 활용한 로그인 * 세션 유지에 용이하다!
	
	// controller 호출 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		
		if(user == null){
            response.sendRedirect("/login.do");
            return false; // 더 이상 컨트롤러 요청으로 가지 않도록 false 
        }

		
		return true; // 컨트롤러 요청 uri 로 가도 되는지의 의미인데, true의 경우 컨트롤러 uri로 가게 된다.
	}

	// controller 완료 후 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	// controller 완료 하고, view 까지 완료 후 
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
