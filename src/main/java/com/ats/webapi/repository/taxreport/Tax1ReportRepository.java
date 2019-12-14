package com.ats.webapi.repository.taxreport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.taxreport.Tax1Report;

@Repository
public interface Tax1ReportRepository extends JpaRepository<Tax1Report, Integer>{

	/*@Query(value="SELECT\n" + 
			"    t_bill_detail.bill_detail_no,\n" + 
			"    t_bill_header.invoice_no,\n" + 
			"    t_bill_header.bill_date,\n" + 
			"    m_franchisee.fr_name,\n" + 
			"    m_franchisee.fr_gst_no,\n" + 
			"    t_bill_detail.bill_no,\n" + 
			"    t_bill_detail.cgst_per,\n" + 
			"    t_bill_detail.sgst_per,\n" + 
			"    t_bill_detail.cgst_per + sgst_per AS tax_per,\n" + 
			"    ROUND(\n" + 
			"        SUM(t_bill_detail.taxable_amt),\n" + 
			"        2\n" + 
			"    ) AS taxable_amt,\n" + 
			"    ROUND(SUM(t_bill_detail.cgst_rs),\n" + 
			"    2) AS cgst_amt,\n" + 
			"    ROUND(SUM(t_bill_detail.sgst_rs),\n" + 
			"    2) AS sgst_amt,\n" + 
			"    ROUND(SUM(t_bill_detail.total_tax),\n" + 
			"    2) AS total_tax,\n" + 
			"    COALESCE((select ROUND(SUM(d.grand_total),2) from t_bill_detail d, t_bill_header h WHERE d.bill_no=h.bill_no AND h.invoice_no=t_bill_header.invoice_no),0) AS grand_total\n" + 
			"FROM\n" + 
			"    t_bill_detail,\n" + 
			"    t_bill_header,\n" + 
			"    m_franchisee\n" + 
			"WHERE\n" + 
			"    t_bill_header.bill_no = t_bill_detail.bill_no AND t_bill_header.bill_date BETWEEN :fromDate AND :toDate AND m_franchisee.fr_id = t_bill_header.fr_id AND t_bill_detail.del_status = 0 AND t_bill_header.del_status = 0\n" + 
			"GROUP BY\n" + 
			"    t_bill_detail.cgst_per + sgst_per,\n" + 
			"    bill_no\n" + 
			"ORDER BY\n" + 
			"    t_bill_detail.bill_no,\n" + 
			"    tax_per",nativeQuery=true)*/
	@Query(value=" Select a.bill_detail_no,a.invoice_no,a.bill_date,a.fr_name,a.fr_gst_no,a.bill_no,a.cgst_per,a.sgst_per,a.tax_per,a.taxable_amt,a.cgst_amt,a.sgst_amt,\n" + 
			"a.total_tax,b.grand_total from (\n" + 
			"SELECT \n" + 
			"			    t_bill_detail.bill_detail_no, \n" + 
			"			    t_bill_header.invoice_no,\n" + 
			"			    t_bill_header.bill_date, \n" + 
			"			    m_franchisee.fr_name, \n" + 
			"			    m_franchisee.fr_gst_no, \n" + 
			"			    t_bill_detail.bill_no, \n" + 
			"			    t_bill_detail.cgst_per, \n" + 
			"			    t_bill_detail.sgst_per, \n" + 
			"			    t_bill_detail.cgst_per + sgst_per AS tax_per, \n" + 
			"			    ROUND(SUM(t_bill_detail.taxable_amt), 2) AS taxable_amt, \n" + 
			"			    ROUND(SUM(t_bill_detail.cgst_rs), \n" + 
			"			    2) AS cgst_amt, \n" + 
			"			    ROUND(SUM(t_bill_detail.sgst_rs), \n" + 
			"			    2) AS sgst_amt, \n" + 
			"			    ROUND(SUM(t_bill_detail.total_tax), \n" + 
			"			    2) AS total_tax\n" + 
			"			   \n" + 
			"			FROM \n" + 
			"			    t_bill_detail, \n" + 
			"			    t_bill_header, \n" + 
			"			    m_franchisee \n" + 
			"			WHERE \n" + 
			"			    t_bill_header.bill_no = t_bill_detail.bill_no AND t_bill_header.bill_date BETWEEN :fromDate AND :toDate AND m_franchisee.fr_id = t_bill_header.fr_id AND t_bill_detail.del_status = 0 AND t_bill_header.del_status = 0 \n" + 
			"			GROUP BY \n" + 
			"			    t_bill_detail.cgst_per + sgst_per, \n" + 
			"			    t_bill_detail.bill_no \n" + 
			"			ORDER BY \n" + 
			"			    t_bill_detail.bill_no, \n" + 
			"			    tax_per\n" + 
			") a  left join\n" + 
			" (select h.bill_no,ROUND(SUM(d.grand_total),2) as grand_total from t_bill_detail d, t_bill_header h WHERE d.bill_no=h.bill_no   AND  h.bill_date BETWEEN :fromDate AND :toDate AND d.del_status = 0 AND h.del_status = 0  GROUP BY h.bill_no ) b on a.bill_no=b.bill_no",nativeQuery=true)
	List<Tax1Report> getTax1Report(@Param("fromDate")String fromDate,@Param("toDate") String toDate);

	//07-12-2019
	//select t_bill_detail.bill_detail_no,t_bill_header.invoice_no,t_bill_header.bill_date,m_franchisee.fr_name,m_franchisee.fr_gst_no, t_bill_detail.bill_no, t_bill_detail.cgst_per,t_bill_detail.sgst_per,t_bill_detail.cgst_per+sgst_per as tax_per,ROUND(SUM(t_bill_detail.taxable_amt),2) as taxable_amt,ROUND(SUM(t_bill_detail.cgst_rs),2) as cgst_amt,ROUND(SUM(t_bill_detail.sgst_rs),2) as sgst_amt,ROUND(SUM(t_bill_detail.total_tax),2) as total_tax,ROUND(SUM(t_bill_detail.grand_total),2) as grand_total from  t_bill_detail,t_bill_header,m_franchisee where t_bill_header.bill_no=t_bill_detail.bill_no AND t_bill_header.bill_date BETWEEN :fromDate AND :toDate AND m_franchisee.fr_id=t_bill_header.fr_id and t_bill_detail.del_status=0 and t_bill_header.del_status=0  group by t_bill_detail. cgst_per+sgst_per,bill_no order by t_bill_detail.bill_no,tax_per",nativeQuery=true)
	
}
