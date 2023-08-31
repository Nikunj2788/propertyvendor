package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.addproperty;

@Repository
public interface addpropertyrepo extends JpaRepository<addproperty, Long> {

	List<addproperty> findByLocation(String location);

	List<addproperty> findByLocationContaining(String location);
	
}
