package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.report.GetRepMonthwiseSell;

public interface RepFrMonthwiseSellRepository extends JpaRepository<GetRepMonthwiseSell, Integer>{

	
	@Query(value="SELECT t_sell_bill_header.bill_date ,t_sell_bill_header.sell_bill_no ,t_sell_bill_header.fr_id,"
			+" sum(CASE WHEN t_sell_bill_header.payment_mode = 1 THEN t_sell_bill_header.payable_amt ELSE 0 END) as cash,"
					+" sum(CASE WHEN t_sell_bill_header.payment_mode = 2 THEN t_sell_bill_header.payable_amt ELSE 0 END) as card ,"
			+ " sum(CASE WHEN t_sell_bill_header.payment_mode = 3 THEN t_sell_bill_header.payable_amt ELSE 0 END) as other,"
					+" m_franchisee.fr_name, MONTH(t_sell_bill_header.bill_date) as month FROM t_sell_bill_header, m_franchisee WHERE t_sell_bill_header.bill_date BETWEEN :fromDate "
			+" AND :toDate AND t_sell_bill_header.fr_id IN(:frId) AND m_franchisee.fr_id=t_sell_bill_header.fr_id GROUP BY"
					+" MONTH(t_sell_bill_header.bill_date),t_sell_bill_header.fr_id ",nativeQuery=true)
			List<GetRepMonthwiseSell> getRepFrMonthwiseSell(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("frId") List<String> frId);
}
