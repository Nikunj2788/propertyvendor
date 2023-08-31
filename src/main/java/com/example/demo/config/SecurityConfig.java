package com.example.demo.config;                                                                                                                              
                                                                                                                                                              
import org.springframework.beans.factory.annotation.Autowired;                                                                                                
import org.springframework.context.annotation.Bean;                                                                                                           
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;                                                                                     
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;                                                                             
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;                                                   
import org.springframework.security.config.annotation.web.builders.HttpSecurity;                                                                              
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;                                                                    
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;                                                         
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.repo.agentrepo;
import com.example.demo.repo.clientrepo;                                                                                      
                                                                                                                                                              
                                                                                                                                                              
@Configuration                                                                                                                                                
@EnableWebSecurity                                                                                                                                            
public class SecurityConfig extends WebSecurityConfigurerAdapter {                                                                                            
                                                                                                                                                              
    @Autowired                                                                                                                                                
    private clientrepo repo;   
    
    @Autowired 
    private agentrepo agentrepo;
                                                                                                                                                              
    @Override                                                                                                                                                 
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {                                                                            
        auth.authenticationProvider(authenticationProvider());                                                                                                
    }                                                                                                                                                         
                                                                                                                                                              
    @Override                                                                                                                                                 
    protected void configure(HttpSecurity http) throws Exception {                                                                                            
        http.authorizeRequests()                                                                                                                              
            .antMatchers("/client/**").hasRole("USER")
            .antMatchers("/agent/**").hasRole("AGENT")                        
            
            .antMatchers("/**,forgetpassword").permitAll()                                                                                                                   
            .and() 
            
            .formLogin()                                                                                                                                                 
            .loginPage("/loginclient") // Set the login page URL for clients
            .loginProcessingUrl("/loginprocess") // Set the login processing URL for both clients and agents
            .defaultSuccessUrl("/Home", true) // Redirect to "/Home" after successful login for clients
            .and()
				/*
				 * .formLogin() .loginPage("/loginagent") // Set the login page URL for agents
				 * .loginProcessingUrl("/loginprocess") // Set the login processing URL for both
				 * clients and agents .defaultSuccessUrl("/Homeagent", true).and()
				 */
            .logout().logoutSuccessUrl("/loginclient" )    

                                                                                                                                                              
            .and()                                                                                                                                            
            .csrf().disable();                                                                                                                                
    }  
    
   
    
                                                                                                                                                              
    @Bean                                                                                                                                                     
    public DaoAuthenticationProvider authenticationProvider() {                                                                                               
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();                                                                   
        authenticationProvider.setUserDetailsService(new CustomUserservice(repo, agentrepo));                                                                            
        authenticationProvider.setPasswordEncoder(passwordEncoder());                                                                                         
        return authenticationProvider;                                                                                                                        
    }                                                                                                                                                         
                                                                                                                                                              
    @Bean                                                                                                                                                     
    public BCryptPasswordEncoder passwordEncoder() {                                                                                                          
        return new BCryptPasswordEncoder();                                                                                                                   
    }                                                                                                                                                         
                                                                                                                                                              
    @Override                                                                                                                                                 
    @Bean                                                                                                                                                     
    public AuthenticationManager authenticationManagerBean() throws Exception {                                                                               
        return super.authenticationManagerBean();                                                                                                             
    }                                                                                                                                                         
                                                                                                                                                              
}                                                                                                                                                             
                                                                                                                                                              