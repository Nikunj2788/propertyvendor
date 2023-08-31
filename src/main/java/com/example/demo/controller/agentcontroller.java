package com.example.demo.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Agent;
import com.example.demo.entity.addproperty;
import com.example.demo.repo.addpropertyrepo;

@Controller
public class agentcontroller {

	@Autowired
	private com.example.demo.repo.agentrepo agentrepo;
	
	@Autowired 
	private addpropertyrepo addpropertyrepo;
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	 @GetMapping("/signupAgent") // Corrected mapping for the signup page
	    public String signupPage() {
	        return "signupAgent"; // Assuming you have a "signup.html" or "signup" template for the signup page
	    }
	    
	    @PostMapping("/signupAgent") // Corrected mapping for the form submission
	    public String createuser(@ModelAttribute Agent agent ,@RequestParam("file") MultipartFile file) throws IOException {
	    	// TODO: handle exception
			System.out.println(agent);      
	        agent.setImageData(file.getBytes());
	        agent.setPassword(bCryptPasswordEncoder.encode(agent.getPassword()));
	        agent.setRole("ROLE_AGENT");
	        agentrepo.save(agent);
	    	return "redirect:/loginagent";
  }
	
	    @GetMapping("/loginagent")
	    public String loginagent() {
	        return "loginagent";
	    }
	    
	  
	    @GetMapping("/Homeagent")
		public String homeagent(Principal p,Model m) {
			String em = p.getName();
			Agent user = agentrepo.findByEmail(em);						
			m.addAttribute("id",user.getId());
			m.addAttribute("address",user.getAddress());
			m.addAttribute("password",user.getPassword());
			m.addAttribute("role",user.getRole());	  
			m.addAttribute("phoneNumber",user.getPhoneNumber());	  
			m.addAttribute("name",user.getName());
			m.addAttribute("email",user.getEmail());
			m.addAttribute("image_data", user.getImageData());
			return "Homeagent";
		}
	    
	    @GetMapping("/editagent/{id}")
	    public String editagent(@PathVariable(value = "id") Long id, Model model) {
	     Optional<Agent> user  = agentrepo.findById(id);
	     System.out.println(id);	
	     Agent use = user.get();
	     model.addAttribute("user", use);
	     return "editagent";
	    } 
	    
	    @PostMapping("/updateagent/{id}")
	    public String updateUser(@PathVariable("id") long id, @ModelAttribute Agent updatedUser) {
	        // Retrieve the existing user from the database based on the ID
	        Agent existingUser = agentrepo.findById(id).orElse(null);
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
	        agentrepo.save(existingUser);
	        
	        return "redirect:/Homeagent"; // Redirect to the success page or some other page after updating
	    }
	    
	    

	    @GetMapping("/addproperty/{id}")
	    public String addproperty(@PathVariable(value = "id") Long id, Model model) {
	        Optional<Agent> user = agentrepo.findById(id);

	        // Check if the Optional contains a value before calling get()
	        if (user.isPresent()) {
	            Agent use = user.get();
	            model.addAttribute("user", use);
	            return "addproperty";
	        } else {
	            // Handle the case where the user with the given ID is not found
	            // You may want to redirect to an error page or handle the situation accordingly.
	            return "user_not_found"; // Replace "user_not_found" with the appropriate error view name
	        }
	    }
	    
	    @PostMapping("/addproperty") // Corrected mapping for the form submission
	    public String createproperty(@ModelAttribute addproperty agent , @RequestParam("agentId") String agentId,@RequestParam("file") MultipartFile file) throws IOException {
	    	// TODO: handle exception
			System.out.println(agent);      
	        agent.setImageData(file.getBytes());
	        Optional<Agent> agentObj = agentrepo.findById(Long.parseLong(agentId));
	        agent.setAgent(agentObj.get());
	        addpropertyrepo.save(agent);
	    	return "redirect:/addproperty/"+agentId;
	    	   
  }

	    @GetMapping("/locate")
	    public String search(@RequestParam(value = "location", required = false) String location, Model model) {
	    	 List<addproperty> addproperties = this.addpropertyrepo.findByLocation(location);
	    	    model.addAttribute("addproperties", addproperties);   	
   	
	    	return "search"; // Return the name of the HTML template to display the search results
	    }

	   
	    
	    
}