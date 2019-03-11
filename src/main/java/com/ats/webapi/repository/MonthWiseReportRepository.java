package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.MonthWiseReport;

@Repository
public interface MonthWiseReportRepository extends JpaRepository<MonthWiseReport, Long> {

	
	@Query(value="select h.bill_no,CONCAT(MONTHNAME(h.bill_date),'-',(YEAR(h.bill_date))) as month, ROUND(SUM(d.taxable_amt),2) AS taxable_amt,ROUND(SUM(d.igst_rs),2) AS igst_rs,ROUND(SUM(d.cgst_rs),2) as cgst_rs,ROUND(SUM(d.sgst_rs),2) as sgst_rs,ROUND(SUM(d.grand_total),2) AS grand_total from t_bill_header h,t_bill_detail d where h.bill_no=d.bill_no AND h.fr_id=:frId  and h.del_status=0 and d.del_status=0 AND h.bill_date BETWEEN :fromDate AND :toDate group by month(h.bill_date), h.fr_id order by h.bill_date",nativeQuery=true)
	List<MonthWiseReport> findMonthWiseReport(@Param("frId")int frId,@Param("fromDate") String fromDate,@Param("toDate") String toDate);
	
	

}
