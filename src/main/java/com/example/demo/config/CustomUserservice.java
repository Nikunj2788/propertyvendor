package com.example.demo.config;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Agent;
import com.example.demo.entity.client;
import com.example.demo.entity.passwordresettoken;
import com.example.demo.repo.agentrepo;
import com.example.demo.repo.clientrepo;
import com.example.demo.repo.tokenrepo;


@Service
public class CustomUserservice implements UserDetailsService {

    private final clientrepo userRepository;
    private final agentrepo agentrepo;

    public CustomUserservice(clientrepo userRepository, agentrepo agentrepo) {
        this.userRepository = userRepository;
        this.agentrepo = agentrepo;
    }
    
    
    @Autowired 
    private  JavaMailSender javaMailSender;

    @Autowired 
    tokenrepo tokenrepo;	
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implement the logic to fetch the user from the database based on the provided username (email).
        // You can use the userRepository to query the database.
        client user = userRepository.findByEmail(username);
        if (user != null) {
        	return new CustomUserDetails(user);
        }
        Agent agent = agentrepo.findByEmail(username);
        if(agent != null) {
        	return new CustomUserDetails(agent);
        	
        }
        // Return an instance of UserDetails (you can create a new class or use an existing one).
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
    
    
   
    public String sendEmail(client client) throws IOException{
    	try {
    		// String resetlink = generateResetToken(client);
    		//System.out.println("RESET_LINK:"+resetlink);
    		
			SimpleMailMessage msg = new SimpleMailMessage();
			
			msg.setTo(client.getEmail());
			
			msg.setSubject("Welcome to the DashBoard");
			msg.setText("Hello" + "Please click on this link to reset Your password");
			
			
			javaMailSender.send(msg);
			 			
			return "success";
    	
    	} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "error";
		}
    } 
    
	/*
	 * private String generateResetToken(client client) {
	 * 
	 * UUID uuid = UUID.randomUUID(); LocalDateTime currentDateTime =
	 * LocalDateTime.now(); LocalDateTime expiryDateTime =
	 * currentDateTime.plusMinutes(30); passwordresettoken resetToekn = new
	 * passwordresettoken(); resetToekn.setClient(client);
	 * resetToekn.setToken(uuid.toString());
	 * resetToekn.setExpirydatetime(expiryDateTime); resetToekn.setClient(client);
	 * 
	 * passwordresettoken token = tokenrepo.save(resetToekn); if(token != null) {
	 * String endpointurl = "http://localhost:8081/resetpassword"; return
	 * endpointurl + "/" + resetToekn.getToken();
	 * 
	 * } // TODO Auto-generated method stub return ""; } public boolean
	 * hasExipred(LocalDateTime expirydatetime) { // TODO Auto-generated method stub
	 * 
	 * LocalDateTime currentDateTime = LocalDateTime.now();
	 * 
	 * return expirydatetime.isAfter(currentDateTime); }
	 */
    
}

