package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.webapi.model.MCategory;

public interface CategoryRepository extends JpaRepository<MCategory, Integer>{
	
	public List<MCategory> findByDelStatus(int i);

	public List<MCategory> findByCatId(int catId);

	
	

}
