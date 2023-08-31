package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Agent;
@Repository
public interface agentrepo extends JpaRepository<Agent, Long> {
	public boolean existsByEmail(String email);
	public Agent findByEmail(String username);
	public Agent deleteById(long id);
	Optional<Agent> findById(long id);
}
