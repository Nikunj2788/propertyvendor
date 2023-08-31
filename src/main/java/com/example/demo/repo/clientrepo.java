package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.client;

@Repository
public interface clientrepo extends JpaRepository<client, Long>{

	public boolean existsByEmail(String email);
	public client findByEmail(String email);
	public client findByName(String name);
	public client deleteById(long id);
	Optional<client> findById(long id);
}
