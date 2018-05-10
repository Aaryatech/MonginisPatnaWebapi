package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.MonthWiseReport;

@Repository
public interface MonthWiseReportRepository extends JpaRepository<MonthWiseReport, Long> {

	
	@Query(value="select h.bill_no, MONTH(h.bill_date) as month, SUM(d.taxable_amt) AS taxable_amt,SUM(d.igst_rs) AS igst_rs,SUM(d.cgst_rs) as cgst_rs,SUM(d.sgst_rs) as sgst_rs,SUM(d.grand_total) AS grand_total from t_bill_header h,t_bill_detail d where h.bill_no=d.bill_no AND h.fr_id=:frId AND h.bill_date BETWEEN :fromDate AND :toDate group by month(h.bill_date), h.fr_id",nativeQuery=true)
	List<MonthWiseReport> findMonthWiseReport(@Param("frId")int frId,@Param("fromDate") String fromDate,@Param("toDate") String toDate);
	
	

}
