package com.example.demo.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.Agent;
import com.example.demo.entity.client;


public class CustomUserDetails implements UserDetails {
 
	private Object u;
	
	public CustomUserDetails(Object u) {
		super();
		this.u = u;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        if (u instanceof client) {
            authorities.add(new SimpleGrantedAuthority(((client) u).getRole()));
        } else if (u instanceof Agent) {
            authorities.add(new SimpleGrantedAuthority(((Agent) u).getRole()));
        }
        return authorities;
	}

	 @Override
	    public String getPassword() {
	        if (u instanceof client) {
	            return ((client) u).getPassword();
	        } else if (u instanceof Agent) {
	            return ((Agent) u).getPassword();
	        } else {
	            throw new RuntimeException("Invalid user object type. Cannot get password.");
	        }
	    }

	    @Override
	    public String getUsername() {
	        if (u instanceof client) {
	            return ((client) u).getEmail();
	        } else if (u instanceof Agent) {
	            return ((Agent) u).getEmail();
	        } else {
	            throw new RuntimeException("Invalid user object type. Cannot get username.");
	        }
	    }

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}

