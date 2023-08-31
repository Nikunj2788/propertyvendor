package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.passwordresettoken;

public interface tokenrepo extends JpaRepository<passwordresettoken, Long> {

	passwordresettoken findByToken(String token);


}
