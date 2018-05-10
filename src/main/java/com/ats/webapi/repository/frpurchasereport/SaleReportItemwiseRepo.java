package com.ats.webapi.repository.frpurchasereport;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ats.webapi.model.report.frpurchase.SalesReportItemwise;
//sales report no 8
public interface SaleReportItemwiseRepo extends JpaRepository<SalesReportItemwise, Integer> {

	@Query(value=" SELECT m_item_sup.item_hsncd,m_item.id,m_item.item_tax1,m_item.item_tax2,m_item. item_tax3,m_item.item_name," + 
			"sum(t_bill_detail.taxable_amt) AS taxable_amt_sum ,sum(t_bill_detail.sgst_rs) AS sgst_rs_sum,sum(t_bill_detail.cgst_rs) AS cgst_rs_sum," + 
			"sum(t_bill_detail.igst_rs) AS igst_rs_sum, " + 
			"SUM(t_bill_detail.bill_qty) AS bill_qty_sum FROM t_bill_header,t_bill_detail,m_item,m_item_sup " + 
			" WHERE t_bill_header.bill_no=t_bill_detail.bill_no AND m_item.id=m_item_sup.item_id AND " + 
			"t_bill_detail.item_id=m_item.id AND t_bill_header.bill_date BETWEEN :fromDate AND :toDate GROUP BY m_item.item_name ",nativeQuery=true)
		
		List<SalesReportItemwise> getSaleReportItemwise(@Param("fromDate") String fromDate,@Param("toDate") String toDate);
	
}
