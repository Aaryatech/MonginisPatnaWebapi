package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.CustList;

public interface CustListRepo extends JpaRepository<CustList, Integer> {

	@Query(value = "  SELECT  DISTINCT b.user_phone , b.user_name,b.user_gst_no  FROM \n"
			+ "(SELECT  DISTINCT b.user_phone , b.user_name,b.user_gst_no  FROM t_sell_bill_header b WHERE b.del_status=0\n"
			+ "UNION\n"
			+ "SELECT DISTINCT s.sp_cust_mob_no As user_phone, s.sp_cust_name AS user_name ,s.sp_cust_dob  AS  user_gst_no FROM t_sp_cake s WHERE s.del_status=0) b WHERE trim(coalesce( b.user_phone, '')) <>''", nativeQuery = true)
	List<CustList> getOpStockAdjReport();

	@Query(value = "   SELECT  DISTINCT b.user_phone , b.user_name,b.user_gst_no  FROM \n"
			+ "(SELECT  DISTINCT b.user_phone , b.user_name,b.user_gst_no  FROM t_sell_bill_header b WHERE b.del_status=:frId AND b.fr_id=91\n"
			+ "UNION\n"
			+ "SELECT DISTINCT s.sp_cust_mob_no As user_phone, s.sp_cust_name AS user_name ,s.sp_cust_dob  AS  user_gst_no FROM t_sp_cake s WHERE s.del_status=0  AND s.fr_id=:frId) b WHERE trim(coalesce( b.user_phone, '')) <>'' ", nativeQuery = true)
	List<CustList> getOpStockAdjReportByfrId(@Param("frId") int frId);
}
