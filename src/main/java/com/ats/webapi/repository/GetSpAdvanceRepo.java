package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.spprod.GetSpAdvanceReport;

public interface GetSpAdvanceRepo extends JpaRepository<GetSpAdvanceReport, Integer> {
	
	@Query(value="SELECT tsp.sp_cust_name as cust_name,msp.sp_name as item_name,tsp.order_date "
			+ "as order_date, " + 
			"tsp.sp_grand_total as total_mrp,tsp.sp_advance as adv_amt,tsp.sp_order_no ,"
			+ "tsp.sp_selected_weight as weight, tsp.sp_delivery_date as del_date " + 
			" FROM m_sp_cake msp,t_sp_cake tsp where  tsp.sp_id=msp.sp_id AND tsp.order_date "
			+ "BETWEEN :fromDate AND :toDate AND tsp.fr_id=:frId AND tsp.del_status=0 "
			,nativeQuery=true)
	List<GetSpAdvanceReport> getSpAdvance(@Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("frId") int frId);

}
