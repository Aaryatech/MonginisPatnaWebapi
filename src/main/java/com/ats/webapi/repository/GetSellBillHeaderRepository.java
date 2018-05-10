package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.GetSellBillHeader;

public interface GetSellBillHeaderRepository extends JpaRepository<GetSellBillHeader,Integer>{

	@Query(value =  "SELECT t_sell_bill_header.sell_bill_no,t_sell_bill_header.bill_type, t_sell_bill_header.invoice_no,"
			+" t_sell_bill_header.bill_date, t_sell_bill_header.taxable_amt, t_sell_bill_header.total_tax,"
			+" t_sell_bill_header.grand_total, t_sell_bill_header.paid_amt, t_sell_bill_header.remaining_amt,"
			+" t_sell_bill_header.payment_mode, t_sell_bill_header.discount_per, t_sell_bill_header.payable_amt, m_franchisee.fr_name"
			+" FROM t_sell_bill_header, m_franchisee WHERE m_franchisee.fr_id=t_sell_bill_header.fr_id AND t_sell_bill_header.fr_id IN(:frId)"
			+" AND t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate", nativeQuery = true)
	
	List<GetSellBillHeader> getFrSellBillHeader(@Param("fromDate") String fromDate ,@Param("toDate") String toDate ,@Param("frId") List<String> frId);
	
}
