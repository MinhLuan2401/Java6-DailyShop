package com.poly.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.services.AccountService;
import com.poly.services.OAuth2Service;
import com.poly.utils.ParamService;
import com.poly.utils.SessionService;


@Controller
public class SecurityController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	ParamService paramService;
	
	@Autowired
	SessionService sessionService;
	
	@RequestMapping("/security/login/form")
	public String form() {
		return "user/account/login";

	}

	@RequestMapping("/security/login/success")
	public String success(Model model) {
		model.addAttribute("message", "Logged in successfully !");
		return "forward:/security/login/form";

	}

	@RequestMapping("/security/login/error")
	public String error(Model model) {
		model.addAttribute("message", "Wrong login information !");
		return "forward:/security/login/form";

	}

	@RequestMapping("/security/logoff/success")
	public String logoff(Model model) {
		model.addAttribute("message", "Sign out successfully !");
		return "forward:/security/login/form";

	}

	@RequestMapping("/security/access/denied")
	public String denied(Model model) {
		model.addAttribute("message", "You do not have access !");
		return "user/account/login";

	}
	
	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping("/rest/security/authentication")
	public Object getAuthentication(HttpSession session) {
		return session.getAttribute("authentication");
	}
	
		// OAuth2
		@Autowired
		OAuth2Service oauth2Service;
		
		@RequestMapping("/oauth2/login/success")
		public String success(OAuth2AuthenticationToken oauth2) {
			oauth2Service.loginFormOAuth2(oauth2);
			return "forward:/security/login/success";
		}
		
		

}
