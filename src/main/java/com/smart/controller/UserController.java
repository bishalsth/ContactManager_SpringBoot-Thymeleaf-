package com.smart.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@ModelAttribute
	public void  addCommonData(Model model,Principal principal) {
		String username = principal.getName();
//		System.out.println(username);
		User user = this.userRepository.getUserByUserName(username);
		model.addAttribute("user",user);
		System.out.println("USER" + user);
		
		
		
	}
	
	@RequestMapping("/index")
	public String dashboard(Model model,Principal principal) {
		
		
		
		return "normal/user_dashboard";
	}
	
	//	open add form handler
	@GetMapping("/add-contact")
	public String opedAddContactForm(Model model) {
		model.addAttribute("title","Add Contact");
		model.addAttribute("contact", new Contact());
		return "normal/add_contact_from";
		
	}

}
