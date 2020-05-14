package com.ats.webapi.repository.frpurchasereport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.report.frpurchase.SalesReportDateMonth;

public interface SalesReportDateMonthRepo extends JpaRepository<SalesReportDateMonth, Integer> {

	@Query(value = "select 0 as crn_id,\r\n" + 
			"uuid() as crn_no,\r\n" + 
			":fromDate as crn_date,\r\n" + 
			"0 as fr_id,\r\n" + 
			"m1.date_str as month,\r\n" + 
			":fromDate as bill_date,\r\n" + 
			"COALESCE(t1.grand_total,0) grand_total,\r\n" + 
			"COALESCE(t1.taxable_amt,0) taxable_amt,\r\n" + 
			"COALESCE(t1.total_tax,0) total_tax,\r\n" + 
			"\r\n" + 
			"COALESCE(t2.grand_total,0) as gvn_grand_total,\r\n" + 
			"COALESCE(t2.taxable_amt,0) as gvn_taxable_amt,\r\n" + 
			"COALESCE(t2.crn_total_tax,0) as gvn_total_tax,\r\n" + 
			"\r\n" + 
			"COALESCE(t3.grand_total,0) as grn_grand_total,\r\n" + 
			"COALESCE(t3.taxable_amt,0) as grn_taxable_amt,\r\n" + 
			"COALESCE(t3.crn_total_tax,0) as grn_total_tax\r\n" + 
			"\r\n" + 
			"FROM\r\n" + 
			"(\r\n" + 
			"select \r\n" + 
			"DATE_FORMAT(m1, '%M - %Y') as date_str,\r\n" + 
			"DATE_FORMAT(m1, '%b') as date_month,\r\n" + 
			"DATE_FORMAT(m1, '%Y') as date_year\r\n" + 
			"\r\n" + 
			"from\r\n" + 
			"(\r\n" + 
			"select \r\n" + 
			"(:fromDate - INTERVAL DAYOFMONTH(:fromDate)-1 DAY) \r\n" + 
			"+INTERVAL m MONTH as m1\r\n" + 
			"from\r\n" + 
			"(\r\n" + 
			"select @rownum\\:=@rownum+1 as m from\r\n" + 
			"(select 1 union select 2 union select 3 union select 4) t1,\r\n" + 
			"(select 1 union select 2 union select 3 union select 4) t2,\r\n" + 
			"(select 1 union select 2 union select 3 union select 4) t3,\r\n" + 
			"(select 1 union select 2 union select 3 union select 4) t4,\r\n" + 
			"(select @rownum\\:=-1) t0\r\n" + 
			") d1\r\n" + 
			") d2 \r\n" + 
			"where m1<=:toDate\r\n" + 
			"order by m1\r\n" + 
			")m1\r\n" + 
			"LEFT JOIN \r\n" + 
			"(\r\n" + 
			"    SELECT\r\n" + 
			"    MONTHNAME(t_bill_header.bill_date) AS MONTH,\r\n" + 
			"    DATE_FORMAT(t_bill_header.bill_date, '%b') as date_month,\r\n" + 
			"    DATE_FORMAT(t_bill_header.bill_date, '%Y') as date_year,\r\n" + 
			"    t_bill_header.bill_no,\r\n" + 
			"    t_bill_header.bill_date,\r\n" + 
			"    t_bill_header.invoice_no,\r\n" + 
			"    t_bill_header.fr_id,\r\n" + 
			"    t_bill_header.fr_code,\r\n" + 
			"    t_bill_header.tax_applicable,\r\n" + 
			"    SUM(t_bill_header.taxable_amt) AS taxable_amt,\r\n" + 
			"    SUM(t_bill_header.total_tax) AS total_tax,\r\n" + 
			"    SUM(t_bill_header.grand_total) AS grand_total,\r\n" + 
			"    SUM(t_bill_header.round_off) AS round_off,\r\n" + 
			"    SUM(t_bill_header.sgst_sum) AS sgst_sum,\r\n" + 
			"    SUM(t_bill_header.cgst_sum) AS cgst_sum,\r\n" + 
			"    SUM(t_bill_header.igst_sum) AS igst_sum,\r\n" + 
			"    m_franchisee.fr_name,\r\n" + 
			"    m_franchisee.fr_city,\r\n" + 
			"    m_franchisee.fr_gst_no,\r\n" + 
			"    m_franchisee.is_same_state\r\n" + 
			"FROM\r\n" + 
			"    m_franchisee,\r\n" + 
			"    t_bill_header\r\n" + 
			"WHERE\r\n" + 
			"    t_bill_header.fr_id = m_franchisee.fr_id AND t_bill_header.fr_id IN(:frIdList) AND t_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_bill_header.del_status = 0\r\n" + 
			"GROUP BY\r\n" + 
			"    month,date_year\r\n" + 
			") t1 ON m1.date_month=t1.date_month AND m1.date_year=t1.date_year\r\n" + 
			"\r\n" + 
			"LEFT JOIN\r\n" + 
			"\r\n" + 
			"(\r\n" + 
			"SELECT\r\n" + 
			"    MONTHNAME(t_credit_note_header.crn_date) AS MONTH,\r\n" + 
			"    DATE_FORMAT(t_credit_note_header.crn_date, '%b') as date_month,\r\n" + 
			"    DATE_FORMAT(t_credit_note_header.crn_date, '%Y') as date_year,\r\n" + 
			"    t_credit_note_header.crn_id,\r\n" + 
			"    t_credit_note_header.crn_date,\r\n" + 
			"    t_credit_note_header.crn_no,\r\n" + 
			"    m_franchisee.fr_id,\r\n" + 
			"    SUM(\r\n" + 
			"        t_credit_note_header.crn_taxable_amt\r\n" + 
			"    ) AS taxable_amt,\r\n" + 
			"    SUM(\r\n" + 
			"        t_credit_note_header.crn_total_tax\r\n" + 
			"    ) AS crn_total_tax,\r\n" + 
			"    SUM(\r\n" + 
			"        t_credit_note_header.crn_grand_total\r\n" + 
			"    ) AS grand_total,\r\n" + 
			"    SUM(t_credit_note_header.round_off) AS round_off\r\n" + 
			"FROM\r\n" + 
			"    m_franchisee,\r\n" + 
			"    t_credit_note_header\r\n" + 
			"WHERE\r\n" + 
			"    t_credit_note_header.is_grn =0 AND t_credit_note_header.fr_id = m_franchisee.fr_id AND t_credit_note_header.fr_id IN(:frIdList) AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate\r\n" + 
			"GROUP BY\r\n" + 
			"    month\r\n" + 
			") t2 ON m1.date_month=t2.date_month AND m1.date_year=t2.date_year\r\n" + 
			"\r\n" + 
			"LEFT JOIN\r\n" + 
			"\r\n" + 
			"(\r\n" + 
			"SELECT\r\n" + 
			"    MONTHNAME(t_credit_note_header.crn_date) AS MONTH,\r\n" + 
			"    DATE_FORMAT(t_credit_note_header.crn_date, '%b') as date_month,\r\n" + 
			"    DATE_FORMAT(t_credit_note_header.crn_date, '%Y') as date_year,\r\n" + 
			"    t_credit_note_header.crn_id,\r\n" + 
			"    t_credit_note_header.crn_date,\r\n" + 
			"    t_credit_note_header.crn_no,\r\n" + 
			"    m_franchisee.fr_id,\r\n" + 
			"    SUM(\r\n" + 
			"        t_credit_note_header.crn_taxable_amt\r\n" + 
			"    ) AS taxable_amt,\r\n" + 
			"    SUM(\r\n" + 
			"        t_credit_note_header.crn_total_tax\r\n" + 
			"    ) AS crn_total_tax,\r\n" + 
			"    SUM(\r\n" + 
			"        t_credit_note_header.crn_grand_total\r\n" + 
			"    ) AS grand_total,\r\n" + 
			"    SUM(t_credit_note_header.round_off) AS round_off\r\n" + 
			"FROM\r\n" + 
			"    m_franchisee,\r\n" + 
			"    t_credit_note_header\r\n" + 
			"WHERE\r\n" + 
			"    t_credit_note_header.is_grn =1 AND t_credit_note_header.fr_id = m_franchisee.fr_id AND t_credit_note_header.fr_id IN(:frIdList) AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate\r\n" + 
			"GROUP BY\r\n" + 
			"    month\r\n" + 
			") t3 ON m1.date_month=t3.date_month AND m1.date_year=t3.date_year\r\n" + 
			" ", nativeQuery = true)

	List<SalesReportDateMonth> getMonthData(@Param("frIdList") List<String> frIdList,
			@Param("fromDate") String fromDate, @Param("toDate") String toDate
	 );
	
	
	@Query(value = "SELECT 0 as crn_id,\r\n" + 
			"uuid() as crn_no,\r\n" + 
			"m1.date_str as crn_date,\r\n" + 
			"0 as fr_id,\r\n" + 
			"m1.date_str as month,\r\n" + 
			"m1.date_str as bill_date,\r\n" + 
			"COALESCE(t1.grand_total,0) grand_total,\r\n" + 
			"COALESCE(t1.taxable_amt,0) taxable_amt,\r\n" + 
			"COALESCE(t1.total_tax,0) total_tax,\r\n" + 
			"\r\n" + 
			"COALESCE(t2.grand_total,0) as gvn_grand_total,\r\n" + 
			"COALESCE(t2.taxable_amt,0) as gvn_taxable_amt,\r\n" + 
			"COALESCE(t2.crn_total_tax,0) as gvn_total_tax,\r\n" + 
			"\r\n" + 
			"COALESCE(t3.grand_total,0) as grn_grand_total,\r\n" + 
			"COALESCE(t3.taxable_amt,0) as grn_taxable_amt,\r\n" + 
			"COALESCE(t3.crn_total_tax,0) as grn_total_tax\r\n" + 
			"\r\n" + 
			"FROM\r\n" + 
			"(\r\n" + 
			"\r\n" + 
			"select * from \r\n" + 
			"(select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) date_str from\r\n" + 
			" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0,\r\n" + 
			" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1,\r\n" + 
			" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2,\r\n" + 
			" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3,\r\n" + 
			" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v\r\n" + 
			"where date_str between :fromDate AND :toDate\r\n" + 
			")m1\r\n" + 
			"LEFT JOIN \r\n" + 
			"(\r\n" + 
			"    SELECT\r\n" + 
			"    MONTHNAME(t_bill_header.bill_date) AS MONTH,\r\n" + 
			"    DATE_FORMAT(t_bill_header.bill_date, '%b') as date_month,\r\n" + 
			"    DATE_FORMAT(t_bill_header.bill_date, '%Y') as date_year,\r\n" + 
			"    t_bill_header.bill_no,\r\n" + 
			"    t_bill_header.bill_date,\r\n" + 
			"    t_bill_header.invoice_no,\r\n" + 
			"    t_bill_header.fr_id,\r\n" + 
			"    t_bill_header.fr_code,\r\n" + 
			"    t_bill_header.tax_applicable,\r\n" + 
			"    SUM(t_bill_header.taxable_amt) AS taxable_amt,\r\n" + 
			"    SUM(t_bill_header.total_tax) AS total_tax,\r\n" + 
			"    SUM(t_bill_header.grand_total) AS grand_total,\r\n" + 
			"    SUM(t_bill_header.round_off) AS round_off,\r\n" + 
			"    SUM(t_bill_header.sgst_sum) AS sgst_sum,\r\n" + 
			"    SUM(t_bill_header.cgst_sum) AS cgst_sum,\r\n" + 
			"    SUM(t_bill_header.igst_sum) AS igst_sum,\r\n" + 
			"    m_franchisee.fr_name,\r\n" + 
			"    m_franchisee.fr_city,\r\n" + 
			"    m_franchisee.fr_gst_no,\r\n" + 
			"    m_franchisee.is_same_state\r\n" + 
			"FROM\r\n" + 
			"    m_franchisee,\r\n" + 
			"    t_bill_header\r\n" + 
			"WHERE\r\n" + 
			"    t_bill_header.fr_id = m_franchisee.fr_id AND t_bill_header.fr_id IN(:frIdList) AND t_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_bill_header.del_status = 0\r\n" + 
			"GROUP BY\r\n" + 
			"   t_bill_header.bill_date\r\n" + 
			") t1 ON m1.date_str=t1.bill_date\r\n" + 
			"\r\n" + 
			"LEFT JOIN\r\n" + 
			"\r\n" + 
			"(\r\n" + 
			"SELECT\r\n" + 
			"    MONTHNAME(t_credit_note_header.crn_date) AS MONTH,\r\n" + 
			"    DATE_FORMAT(t_credit_note_header.crn_date, '%b') as date_month,\r\n" + 
			"    DATE_FORMAT(t_credit_note_header.crn_date, '%Y') as date_year,\r\n" + 
			"    t_credit_note_header.crn_id,\r\n" + 
			"    t_credit_note_header.crn_date,\r\n" + 
			"    t_credit_note_header.crn_no,\r\n" + 
			"    m_franchisee.fr_id,\r\n" + 
			"    SUM(\r\n" + 
			"        t_credit_note_header.crn_taxable_amt\r\n" + 
			"    ) AS taxable_amt,\r\n" + 
			"    SUM(\r\n" + 
			"        t_credit_note_header.crn_total_tax\r\n" + 
			"    ) AS crn_total_tax,\r\n" + 
			"    SUM(\r\n" + 
			"        t_credit_note_header.crn_grand_total\r\n" + 
			"    ) AS grand_total,\r\n" + 
			"    SUM(t_credit_note_header.round_off) AS round_off\r\n" + 
			"FROM\r\n" + 
			"    m_franchisee,\r\n" + 
			"    t_credit_note_header\r\n" + 
			"WHERE\r\n" + 
			"    t_credit_note_header.is_grn =0 AND t_credit_note_header.fr_id = m_franchisee.fr_id AND t_credit_note_header.fr_id IN(:frIdList) AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate\r\n" + 
			"GROUP BY\r\n" + 
			"    t_credit_note_header.crn_date\r\n" + 
			") t2 ON m1.date_str=t2.crn_date\r\n" + 
			"\r\n" + 
			"LEFT JOIN\r\n" + 
			"\r\n" + 
			"(\r\n" + 
			"SELECT\r\n" + 
			"    MONTHNAME(t_credit_note_header.crn_date) AS MONTH,\r\n" + 
			"    DATE_FORMAT(t_credit_note_header.crn_date, '%b') as date_month,\r\n" + 
			"    DATE_FORMAT(t_credit_note_header.crn_date, '%Y') as date_year,\r\n" + 
			"    t_credit_note_header.crn_id,\r\n" + 
			"    t_credit_note_header.crn_date,\r\n" + 
			"    t_credit_note_header.crn_no,\r\n" + 
			"    m_franchisee.fr_id,\r\n" + 
			"    SUM(\r\n" + 
			"        t_credit_note_header.crn_taxable_amt\r\n" + 
			"    ) AS taxable_amt,\r\n" + 
			"    SUM(\r\n" + 
			"        t_credit_note_header.crn_total_tax\r\n" + 
			"    ) AS crn_total_tax,\r\n" + 
			"    SUM(\r\n" + 
			"        t_credit_note_header.crn_grand_total\r\n" + 
			"    ) AS grand_total,\r\n" + 
			"    SUM(t_credit_note_header.round_off) AS round_off\r\n" + 
			"FROM\r\n" + 
			"    m_franchisee,\r\n" + 
			"    t_credit_note_header\r\n" + 
			"WHERE\r\n" + 
			"    t_credit_note_header.is_grn =1 AND t_credit_note_header.fr_id = m_franchisee.fr_id AND t_credit_note_header.fr_id IN(:frIdList) AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate\r\n" + 
			"GROUP BY\r\n" + 
			"    t_credit_note_header.crn_date\r\n" + 
			") t3 ON m1.date_str=t3.crn_date" + 
			" ", nativeQuery = true)

	List<SalesReportDateMonth> getDateWiseData(@Param("frIdList") List<String> frIdList,
			@Param("fromDate") String fromDate, @Param("toDate") String toDate
	 );
	
}
