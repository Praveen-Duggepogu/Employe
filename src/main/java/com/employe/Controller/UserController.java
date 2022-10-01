package com.employe.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.employe.Dao.UserDao;
import com.employe.Entity.User;

@Controller
public class UserController {
	
	@Autowired 
	private UserDao userdao;

	@RequestMapping("/index")
	public String viewHomePage() {
		return "Homepage";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "SignUp";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userdao.save(user);
		return "Registration";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userdao.findAll();
		model.addAttribute("listUsers", listUsers);
		
		return "Users";
	}
}
