package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.report.GetRepFrDatewiseSell;
 

public interface RepFrDatewiseSellRepository extends JpaRepository<GetRepFrDatewiseSell, Integer>{
	
/*@Query(value="SELECT DAYNAME(t_sell_bill_header.bill_date) as day,t_sell_bill_header.bill_date ,t_sell_bill_header.sell_bill_no ,t_sell_bill_header.fr_id,"
+" sum(CASE WHEN t_sell_bill_header.payment_mode = 1 THEN t_sell_bill_header.payable_amt ELSE 0 END) as cash,"
		+" sum(CASE WHEN t_sell_bill_header.payment_mode = 2 THEN t_sell_bill_header.payable_amt ELSE 0 END) as card ,"
+ " sum(CASE WHEN t_sell_bill_header.payment_mode = 3 THEN t_sell_bill_header.payable_amt ELSE 0 END) as other,"
		+" m_franchisee.fr_name FROM t_sell_bill_header, m_franchisee WHERE t_sell_bill_header.bill_date BETWEEN :fromDate "
+" AND :toDate AND t_sell_bill_header.fr_id IN(:frId) AND m_franchisee.fr_id=t_sell_bill_header.fr_id GROUP BY"
		+" t_sell_bill_header.bill_date,t_sell_bill_header.fr_id ",nativeQuery=true)
List<GetRepFrDatewiseSell> getRepFrDatewiseSell(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("frId") List<String> frId);
*/
	
	
	@Query(value="SELECT DAYNAME(t_sell_bill_header.bill_date) as day,t_sell_bill_header.bill_date ,t_sell_bill_header.sell_bill_no ,t_sell_bill_header.fr_id,"
			+" sum(CASE WHEN t_sell_bill_header.payment_mode = 1 THEN t_sell_bill_header.payable_amt ELSE 0 END) as cash,"
					+" sum(CASE WHEN t_sell_bill_header.payment_mode = 2 THEN t_sell_bill_header.payable_amt ELSE 0 END) as card ,"
			+ " sum(CASE WHEN t_sell_bill_header.payment_mode = 3 THEN t_sell_bill_header.payable_amt ELSE 0 END) as other,"
					+" m_franchisee.fr_name FROM t_sell_bill_header, m_franchisee WHERE t_sell_bill_header.bill_date BETWEEN :fromDate "
			+" AND :toDate AND t_sell_bill_header.fr_id IN(:frId) AND m_franchisee.fr_id=t_sell_bill_header.fr_id GROUP BY"
					+" t_sell_bill_header.bill_date,t_sell_bill_header.fr_id "
					+ ""
					+ ""
					+ " UNION ALL" + 
					"       SELECT DAYNAME(t_sp_cake.sp_delivery_date) as day," + 
					"       t_sp_cake.sp_delivery_date as bill_date," + 
					"       t_sp_cake.sp_order_no as bill_no," + 
					"       t_sp_cake.fr_id," + 
					"       sum(t_sp_cake.sp_grand_total) as cash," + 
					"       0 as card," + 
					"       0 as other," + 
					"       m_franchisee.fr_name" + 
					"       from t_sp_cake,m_franchisee" + 
					"        WHERE" + 
					"        t_sp_cake.sp_delivery_date BETWEEN :fromDate AND  :toDate "  + 
					"        AND t_sp_cake.fr_id IN(" + 
					"           :frId " + 
					"        ) " + 
					"        AND m_franchisee.fr_id=t_sp_cake.fr_id " + 
					"    GROUP BY" + 
					"        t_sp_cake.sp_delivery_date," + 
					"        t_sp_cake.fr_id",nativeQuery=true)
			List<GetRepFrDatewiseSell> getRepFrDatewiseSell(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("frId") List<String> frId);

}
