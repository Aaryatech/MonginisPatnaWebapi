package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.TaxHsn;

public interface TaxHsnRepo extends JpaRepository<TaxHsn, Integer> {
	
	@Transactional
	@Modifying
	@Query(value="UPDATE TaxHsn SET delStatus=1 WHERE taxHsnId IN (:taxHsnIdList)")
	int deleteTaxHsn(@Param("taxHsnIdList") List<Integer> taxHsnIdList);
	
	
	

}
