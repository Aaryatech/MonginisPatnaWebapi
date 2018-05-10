package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
import com.ats.webapi.model.report.GetRepMenuwiseSell;

public interface RepFrMenuwiseSellRepository extends JpaRepository<GetRepMenuwiseSell, Integer>{
	
	@Query(value="select d.sell_bill_detail_no, h.fr_id, f.fr_name, c.cat_name, d.cat_id, sum(d.qty) as qty,"
			+" sum(d.mrp*d.qty) as amount from t_sell_bill_detail d, t_sell_bill_header h, m_category c,"
			+" m_franchisee f WHERE h.bill_date BETWEEN :fromDate AND :toDate AND d.cat_id !=6"
			+" AND c.cat_id=d.cat_id AND h.sell_bill_no=d.sell_bill_no AND h.fr_id IN(:frId) AND"
			+" h.fr_id=f.fr_id GROUP BY d.cat_id, h.fr_id ",nativeQuery=true)
	List<GetRepMenuwiseSell> getRepFrMenuwiseSell(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("frId") List<String> frId);
	

}
