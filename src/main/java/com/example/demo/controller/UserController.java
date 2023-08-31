package com.example.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.addproperty;
import com.example.demo.entity.client;
import com.example.demo.repo.addpropertyrepo;
import com.example.demo.repo.clientrepo;

@Controller

public class UserController {

	@Autowired
	private clientrepo clientrepo;

	@GetMapping("/Home")
	public String home(Principal p,Model m) {
		String em = p.getName();
		client user = clientrepo.findByEmail(em);
		
		m.addAttribute("id",user.getId());
		m.addAttribute("address",user.getAddress());
		m.addAttribute("password",user.getPassword());
		m.addAttribute("role",user.getRole());	  
		m.addAttribute("phoneNumber",user.getPhoneNumber());	  
		m.addAttribute("name",user.getName());
		m.addAttribute("email",user.getEmail());
		return "Home";
	}
	@GetMapping("/edit/{id}")
    public String edituser(@PathVariable(value = "id") Long id, Model model) {
     Optional<client> user  = clientrepo.findById(id);
     System.out.println(id);	

     client use = user.get();
     model.addAttribute("user", use);
     return "edit";
    } 
	

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @ModelAttribute client updatedUser) {
        // Retrieve the existing user from the database based on the ID
        client existingUser = clientrepo.findById(id).orElse(null);
        if (existingUser == null) {
            // Handle the case when the user with the given ID is not found
            // For example, redirect to an error page or show an error message
            return "redirect:/error"; // Redirect to the error page
        }
        
        // Update only specific fields (name, address, phoneNumber) if they are not null in the updatedUser
        String newName = updatedUser.getName();
        if (newName != null) {
            existingUser.setName(newName);
        }
        
        String newAddress = updatedUser.getAddress();
        if (newAddress != null) {
            existingUser.setAddress(newAddress);
        }
        
        String newPhoneNumber = updatedUser.getPhoneNumber();
        if (newPhoneNumber != null) {
            existingUser.setPhoneNumber(newPhoneNumber);
        }
        
        // Save the updated user in the database
        clientrepo.save(existingUser);
        
        return "redirect:/Home"; // Redirect to the success page or some other page after updating
    }
    

  }

