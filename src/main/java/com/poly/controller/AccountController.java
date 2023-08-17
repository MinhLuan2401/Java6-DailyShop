package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.dao.AccountDAO;
import com.poly.entity.Account;
import com.poly.services.impl.MailerServiceImpl;
import com.poly.utils.ParamService;

import net.bytebuddy.utility.RandomString;

@Controller
@RequestMapping("home")
public class AccountController {

	@Autowired
	AccountDAO accountDAO;
	
	@Autowired
	ParamService paramService;
	
	@Autowired
	MailerServiceImpl mailer;
	

	// Change Pass
	@GetMapping("changepassword")
	public String changepass() {
		return "user/account/changepass";
	}
	
	@PostMapping("changepassword")
	public String change(Model model) {
		String username = paramService.getString("username", "");
		String password = paramService.getString("password", "");
		String newpassword= paramService.getString("newpassword", "");
		String confirmpassword= paramService.getString("confirmpassword", "");
		try {
			Account user = accountDAO.findById(username).get();
				if(!user.getPassword().equals(password)) {
					model.addAttribute("message", "Wrong Password!");
				}else {
					if(newpassword.equals(confirmpassword)) {
						user.setPassword(confirmpassword);
						accountDAO.save(user);
						model.addAttribute("message", "Change Password Done !");
					}else {
						model.addAttribute("message", "Password not match !");
					}
				}
		} catch (Exception e) {
			model.addAttribute("message", "Account does not exist !");
		}
		return "user/account/changepass";
	}

//	Forgot Pass
	@RequestMapping("forgotpassword")
	public String index() {		
		return "user/account/forgotpass";
	}
	

	@SuppressWarnings("unused")
	@PostMapping("forgotpassword")
	public String forgot(Model model) {
		String email = paramService.getString("email", "");
		String username = paramService.getString("username", "");
		String subject = "Send your Password !";
		String body = "Your Password: ";
		//String randomPassword = RandomString.make(6);
		
		try {
			Account user = accountDAO.findById(username).get();
				if(!user.getEmail().equals(email)) {
					model.addAttribute("message", " Email Wrong !");
				}else {
//					user.setPassword(randomPassword);
//					dao.save(user);
					mailer.send(email, subject, body+user.getPassword());
					model.addAttribute("message", "Check Email To See Password !");
				}
		} catch (Exception e) {
			model.addAttribute("message", "Account does not exist !");
		}
		return "user/account/forgotpass";
	}

//	Register
	@RequestMapping("register")
	public String register(Model model) {
		Account acc = new Account();
		model.addAttribute("acc", acc);
		return "user/account/register";
	}

	@RequestMapping("register/create")
	public String save(Model model, @Validated @ModelAttribute("acc") Account acc,Errors errors) {
		if(errors.hasErrors()) {
			model.addAttribute("message","Register failed, please fix the following errors:");
			return "user/account/register";
		}
		acc.setPhoto("user.png");
		accountDAO.save(acc);
		model.addAttribute("message","Register Successfully !");
		return "user/account/register";
		
	}
}
