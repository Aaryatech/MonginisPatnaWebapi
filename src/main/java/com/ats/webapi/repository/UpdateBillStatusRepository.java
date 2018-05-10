package com.ats.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.GetBillHeader;
import com.ats.webapi.model.UpdateBillStatus; 

public interface UpdateBillStatusRepository extends JpaRepository<UpdateBillStatus, Long> {

	
	
	UpdateBillStatus save(UpdateBillStatus updateBillStatus);
}
