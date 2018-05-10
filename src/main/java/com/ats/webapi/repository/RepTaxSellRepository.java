package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.report.GetRepTaxSell;

public interface RepTaxSellRepository extends JpaRepository<GetRepTaxSell, Integer>{

	@Query(value=" select h.user_gst_no, h.bill_date, d.sell_bill_detail_no, d.sell_bill_no, d.cgst_per+d.sgst_per as tax_per,"
			+" sum(d.taxable_amt) as tax_amount, sum(d.cgst_rs) as cgst, sum(d.sgst_rs) as sgst, sum(d.igst_rs) as igst, h.fr_id, f.fr_name, sum(d.grand_total) as bill_amount from t_sell_bill_detail d,"
			+" t_sell_bill_header h, m_franchisee f WHERE h.fr_id IN(:frId) AND h.sell_bill_no=d.sell_bill_no AND h.bill_date BETWEEN :fromDate"
			+" AND :toDate AND f.fr_id=h.fr_id group by (d.cgst_per+d.sgst_per), h.fr_id",nativeQuery=true)
	List<GetRepTaxSell> getRepFrTaxSell(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("frId") List<String> frId);

	
	@Query(value=" select h.user_gst_no, h.bill_date, d.sell_bill_detail_no, d.sell_bill_no, d.cgst_per+d.sgst_per as tax_per,"
			+" sum(d.taxable_amt) as tax_amount, sum(d.cgst_rs) as cgst, sum(d.sgst_rs) as sgst, sum(d.igst_rs) as igst, h.fr_id, f.fr_name, sum(d.grand_total) as bill_amount from t_sell_bill_detail d,"
			+" t_sell_bill_header h, m_franchisee f WHERE h.fr_id IN(:frId) AND h.sell_bill_no=d.sell_bill_no AND h.bill_date BETWEEN :fromDate"
			+" AND :toDate AND f.fr_id=h.fr_id group by (d.cgst_per+d.sgst_per),h.bill_date, h.fr_id",nativeQuery=true)
	List<GetRepTaxSell> getRepFrDatewiseTaxSell(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("frId") List<String> frId);

	
	@Query(value=" select h.user_gst_no, h.bill_date, d.sell_bill_detail_no, d.sell_bill_no, d.cgst_per+d.sgst_per as tax_per,"
			+" sum(d.taxable_amt) as tax_amount, sum(d.cgst_rs) as cgst, sum(d.sgst_rs) as sgst, sum(d.igst_rs) as igst, h.fr_id, f.fr_name, sum(d.grand_total) as bill_amount from t_sell_bill_detail d,"
			+" t_sell_bill_header h, m_franchisee f WHERE h.fr_id IN(:frId) AND h.sell_bill_no=d.sell_bill_no AND h.bill_date BETWEEN :fromDate"
			+" AND :toDate AND f.fr_id=h.fr_id group by (d.cgst_per+d.sgst_per),h.sell_bill_no, h.fr_id",nativeQuery=true)
	List<GetRepTaxSell> getRepFrBillwiseTaxSell(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("frId") List<String> frId);

}
