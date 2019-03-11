package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.BillWiseTaxReport;

@Repository
public interface BillWiseTaxReportRepository  extends JpaRepository<BillWiseTaxReport, Long>{

	
	@Query(value="select d.bill_detail_no, h.invoice_no as bill_no,h.bill_date,SUM(d.taxable_amt) as taxable_amt,d.sgst_per+d.cgst_per as tax_rate,SUM(d.igst_rs) as igst_rs,SUM(d.cgst_rs) as cgst_rs,SUM(d.sgst_rs) as sgst_rs,SUM(d.grand_total) as grand_total from t_bill_header h,t_bill_detail d where h.bill_no=d.bill_no AND h.fr_id=:frId and h.del_status=0 and d.del_status=0 AND h.bill_date BETWEEN :fromDate AND :toDate group by d.sgst_per+d.cgst_per",nativeQuery=true)
	List<BillWiseTaxReport> findBillWiseTaxReport(@Param("frId")int frId,@Param("fromDate") String fromDate,@Param("toDate") String toDate);

}
