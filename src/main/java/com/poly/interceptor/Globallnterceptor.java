package com.poly.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.poly.services.CategoryService;


@Component
public class Globallnterceptor implements HandlerInterceptor {

	@Autowired
	CategoryService categoryService;

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		req.setAttribute("cates", categoryService.findAll());

	}
}
