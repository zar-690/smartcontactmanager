package com.itvedant.scm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itvedant.scm.entites.User;

import com.itvedant.scm.repository.UserRepository;

import jakarta.servlet.http.HttpSession;




@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;
 // Home handler
	@RequestMapping("/")
	public String home(Model model) {
		
		model.addAttribute("title", "Home - Smart Contact Manager");
		return "home";
	}
	

// About Handler
	@RequestMapping("/about")
	public String about(Model model) {
		
		model.addAttribute("title", "About - Smart Contact Manager");
		return "about";
	}
// handler
	@RequestMapping("/signup")
	public String signup(Model model) {
		
		model.addAttribute("title", "Register - Smart Contact Manager");
		model.addAttribute("user",new User());
		return "signup";
	}
	
// This handler for registering user
	@RequestMapping(value = "/do_register",method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user,@RequestParam(value = "agreement", defaultValue = "false") boolean agreement,Model model,HttpSession session)
	{
		try {
			if(!agreement)
			{
				System.out.println("You have not agreed the terms and conditions");
				throw new Exception("You have not agreed the terms and conditions");
			}
			
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			
			System.out.println("Agreement "+agreement);
			System.out.println("USER"+ user);
			
			User result = this.userRepository.save(user);
			
			model.addAttribute("user",new User());
			
			
			return "signup";
			
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
		
			return "signup";
		}
		
	}
}

