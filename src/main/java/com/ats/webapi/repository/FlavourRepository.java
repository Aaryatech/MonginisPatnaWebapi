package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.Flavour;
import com.ats.webapi.model.Message;

@Repository
public interface FlavourRepository extends JpaRepository<Flavour, Integer>{
	Flavour save(Flavour flavour);
    Flavour findOne(int spfId);
	List<Flavour> findByDelStatus(int i);
	List<Flavour> findByDelStatusOrderBySpfNameAsc(int i);
	List<Flavour> findBySpfIdIn(List<Integer> spfId);
}
