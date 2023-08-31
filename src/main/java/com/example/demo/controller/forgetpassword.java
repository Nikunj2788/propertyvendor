package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.config.CustomUserservice;
import com.example.demo.entity.client;
import com.example.demo.entity.passwordresettoken;
import com.example.demo.repo.clientrepo;
import com.example.demo.repo.tokenrepo;

@Controller
public class forgetpassword {
	
	
	@Autowired 
	private clientrepo clientrepo;
	
	@Autowired 
	private tokenrepo tokenrepo;
	
	@Autowired 
	private CustomUserservice customUserservice;
	
	
	
	 @GetMapping("/forgetpassword") // Corrected mapping for the signup page
	    public String forgetpassword() {
	        return "forgetpassword"; // Assuming you have a "signup.html" or "signup" template for the signup page
	    }
	    
	    
	    @PostMapping("/forgetpassword")
	    public String forgetpassowrdProcess(@ModelAttribute client client) throws IOException {
	        String output = "";
	        System.out.println("CLIENT:"+client);
	        client user = clientrepo.findByEmail(client.getEmail());
	        System.out.println("user"+user);
	        if (user != null) {
	            output = customUserservice.sendEmail(user); // Call the static method using the class name
	        }
//	        if(output.equals("success")) {
//	        	return "redirect:/loginclient?success";
//	      	  
//	        }
	        return "redirect:/loginclient?error";
			}
//	    
//	    @GetMapping("/resetpassword/{token}") 
//	    public String resetpassword(String token,Model model) {
//	    	passwordresettoken reset = tokenrepo.findByToken(token);
//	    	if(reset != null && customUserservice.hasExipred(reset.getExpirydatetime())) {
//	    		model.addAttribute("email", reset.getClient().getEmail());
//	    		return "resetpassword";
//	    	}
//	    	return "redirect:/forgetpassword?error";
//	    }	
//	    
//	    @PostMapping("/resetpassword/{token}")
//	    public String passwordResetRecover(@ModelAttribute client client) {
//	    	client user = clientrepo.findByEmail(client.getEmail());
//	    	
//	    	if(user != null) {
//	    		user.setPassword(client.getPassword());
//	    		clientrepo.save(user);
//	    		
//	    	}
//	    	return "redirect:/loginclient";
//	    }
}
