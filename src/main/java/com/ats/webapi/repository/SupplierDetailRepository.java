package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.SupplierMaster.SupplierDetails;

public interface SupplierDetailRepository extends JpaRepository<SupplierDetails, Integer>{
	
	SupplierDetails save(SupplierDetails supplierDetails);
	

	SupplierDetails findBySuppId(int suppId);
	
	
	@Transactional
	@Modifying
	@Query(" UPDATE SupplierDetails SET delStatus=1 WHERE suppId=:suppId ")
	int deleteSupplier(@Param("suppId") int suppId);


	List<SupplierDetails> findByDelStatus(int delStatus);


	List<SupplierDetails> findByIsTallySyncAndDelStatus(int i, int j);

	@Transactional
	@Modifying
	@Query(" UPDATE SupplierDetails SET isTallySync=:isTallySync WHERE suppId=:suppId ")
	int updateSupplier(@Param("suppId") int suppId,@Param("isTallySync") int isTallySync);



}
