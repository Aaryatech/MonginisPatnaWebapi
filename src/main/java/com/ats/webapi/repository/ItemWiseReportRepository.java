package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.ItemWiseReport;

@Repository
public interface ItemWiseReportRepository extends JpaRepository<ItemWiseReport, Long>{
	
	@Query(value="SELECT t_bill_detail.bill_detail_no, t_bill_detail.item_id ,  m_sp_cake.sp_name as item_name ,t_bill_detail.rate,COALESCE(SUM(bill_qty),0) AS qty, COALESCE(SUM(t_bill_detail.grand_total),0) AS total  FROM t_bill_detail , m_sp_cake, t_bill_header WHERE  m_sp_cake.sp_id= t_bill_detail.item_id AND t_bill_detail.bill_no IN(SELECT bill_no FROM t_bill_header WHERE bill_date BETWEEN :fromDate AND :toDate AND fr_id=:frId) AND t_bill_detail.bill_no=t_bill_header.bill_no AND t_bill_detail.cat_id=:catId and t_bill_header.del_status=0 and t_bill_detail.del_status=0 GROUP BY t_bill_detail.item_id",nativeQuery=true)
	List<ItemWiseReport> findSpecialCakeWiseReport(@Param("frId")int frId,@Param("catId") int catId,@Param("fromDate") String fromDate,@Param("toDate") String toDate);

	
	@Query(value="SELECT t_bill_detail.bill_detail_no, t_bill_detail.item_id ,  m_item.item_name ,t_bill_detail.rate,COALESCE(SUM(bill_qty),0) AS qty, COALESCE(SUM(t_bill_detail.grand_total),0) AS total  FROM t_bill_detail , m_item, t_bill_header WHERE  m_item.id= t_bill_detail.item_id AND t_bill_detail.bill_no IN(SELECT bill_no FROM t_bill_header WHERE bill_date BETWEEN  :fromDate AND :toDate  AND fr_id=:frId) AND t_bill_detail.bill_no=t_bill_header.bill_no AND t_bill_detail.cat_id=:catId  and t_bill_header.del_status=0 and t_bill_detail.del_status=0 GROUP BY t_bill_detail.item_id",nativeQuery=true)
	List<ItemWiseReport> findItemWiseReport(@Param("frId")int frId,@Param("catId") int catId,@Param("fromDate") String fromDate,@Param("toDate") String toDate);

}
