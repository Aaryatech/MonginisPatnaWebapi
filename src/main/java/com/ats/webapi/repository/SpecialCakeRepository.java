package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.SpecialCake;

public interface SpecialCakeRepository extends JpaRepository<SpecialCake, Integer>{
	public SpecialCake save(SpecialCake specialcake);
	public SpecialCake findOne(int spId);
	public List<SpecialCake> findByDelStatus(int i);
	public List<SpecialCake> findByDelStatusOrderBySpNameAsc(int i);
	public List<SpecialCake> findBySpIdIn(List<Integer> spId);

}
