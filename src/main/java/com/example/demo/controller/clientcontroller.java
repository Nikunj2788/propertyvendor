package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Agent;
import com.example.demo.entity.addproperty;
import com.example.demo.entity.client;
import com.example.demo.repo.addpropertyrepo;
import com.example.demo.repo.agentrepo;
import com.example.demo.repo.clientrepo;
@Controller
public class clientcontroller {
	  @Autowired 
		private clientrepo clientrepo;

		@Autowired
		private BCryptPasswordEncoder bCryptPasswordEncoder;
		
		@Autowired 
		private addpropertyrepo addpropertyrepo;
		
		@Autowired
		private agentrepo agentrepo;
		
		
		
		private List<client> clients;
		
	    @GetMapping("/")
	    public String index(Model model) {
	    	List<addproperty> properties = addpropertyrepo.findAll();
	        int propertyCount = properties.size(); // Calculate the count

	    	model.addAttribute("properties", properties);
	        model.addAttribute("propertyCount", propertyCount); // Pass the count to the template

	    	List<Agent> agents = agentrepo.findAll();       
	        model.addAttribute("agents", agents);
	        
	     	
	        return "index";
	    }
	    @GetMapping("/loginclient")
	    public String login() {
	        return "loginclient";
	    }

	    @GetMapping("/logout")
	    public String logout() {
	        return "loginclient";
	    }

	    @GetMapping("/about")
	    public String about() {
	        return "about";
	    }

	    @GetMapping("/agent")
	    public String agent(Model model) {
	       List<Agent> agents = agentrepo.findAll();
	       model.addAttribute("agents", agents);
	    	
	    	
	    	return "agent";
	    }
	    
	    @GetMapping("/properties")
	    public String properties() {
	        return "properties";
	    }
	    

	    @GetMapping("/properties-single")
	    public String propertiessingle() {
	        return "properties-single";
	    }
	    
	    @GetMapping("/signupclients") // Corrected mapping for the signup page
	    public String signupPage() {
	        return "signupclient"; // Assuming you have a "signup.html" or "signup" template for the signup page
	    }
	    
	    
	    
	    @PostMapping("/signupclients") // Corrected mapping for the form submission
	    public String createuser(@ModelAttribute client client ,Model model) {
	        System.out.println(client);   
	        
			/*
			 * if(isEmailExists(client.getEmail())) { model.addAttribute("error",
			 * "Email already exists"); return "redirect:/loginclient"; }
			 */
	        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
	        client.setRole("ROLE_USER");
	        clientrepo.save(client);       
	        return "redirect:/loginclient";    
	    }
	    
}
