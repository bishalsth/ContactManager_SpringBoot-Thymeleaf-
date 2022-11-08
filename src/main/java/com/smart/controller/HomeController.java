package com.smart.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("titile","Home-Smart Contact Manager");
		return "home";
	}
	
	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("titile","About-Smart Contact Manager");
		return "about";
	}
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("titile","Register-Smart Contact Manager");
		model.addAttribute("user",new User());
		return "signup";
	}
	
//	Handler for Registering user
	@PostMapping("/do_register")
	public String registerUser(@ModelAttribute("user") User user,@RequestParam(value="agreement",defaultValue = "false") boolean agreement,Model model,HttpSession session) {
		
	try {
		if(!agreement) {
			System.out.println("You have not agreed the terms and conditions");
			throw new Exception("You have not agreed the terms and conditions");
			
		}
		user.setRole("Role_user");
		user.setEnabled(true);
		user.setImageUrl("default.png");
		
		System.out.println("Agreement"+ agreement);
		System.out.println("USer"+ user);
		User result = this.userRepository.save(user);
		
		model.addAttribute("user",new User());
		session.setAttribute("message", new Message("Successfully Registerd","alert-success"));
		return "signup";
		
	} catch (Exception e) {
		e.printStackTrace();
		model.addAttribute("user",user);
		session.setAttribute("message", new Message("Something Went Wrong!! "+e.getMessage(),"alert-danger"));
		return "signup";
		
	}
		
	}

}
