package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.report.GetRepItemwiseSell;

public interface RepFrItemwiseSellRepository extends JpaRepository<GetRepItemwiseSell, Integer>{
	
	@Query(value="select d.sell_bill_detail_no, d.item_id, h.fr_id, f.fr_name, t.item_name, c.cat_name, d.cat_id, h.bill_date, sum(d.qty) as qty,"
			+" sum(d.mrp*d.qty) as amount from t_sell_bill_detail d, t_sell_bill_header h, m_category c,"
			+" m_item t, m_franchisee f WHERE h.bill_date BETWEEN :fromDate AND :toDate AND d.cat_id IN(:catId)"
			+" AND c.cat_id=d.cat_id AND d.item_id=t.id AND h.sell_bill_no=d.sell_bill_no AND h.fr_id IN(:frId) AND"
			+" h.fr_id=f.fr_id GROUP BY d.item_id, h.fr_id ",nativeQuery=true)
	List<GetRepItemwiseSell> getRepFrItemwiseSell(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("frId") List<String> frId, @Param("catId") List<String> catId);
	
	
	@Query(value="select d.sell_bill_detail_no, d.item_id, h.fr_id, f.fr_name, t.item_name, c.cat_name, d.cat_id, h.bill_date, sum(d.qty) as qty,"
			+" sum(d.mrp*d.qty) as amount from t_sell_bill_detail d, t_sell_bill_header h, m_category c,"
			+" m_item t, m_franchisee f WHERE h.bill_date BETWEEN :fromDate AND :toDate AND d.cat_id IN(:catId)"
			+" AND c.cat_id=d.cat_id AND d.item_id=t.id AND h.sell_bill_no=d.sell_bill_no AND h.fr_id IN(:frId) AND"
			+" h.fr_id=f.fr_id GROUP BY h.bill_date, d.item_id, h.fr_id ",nativeQuery=true)
	List<GetRepItemwiseSell> getRepFrDateItemwiseSell(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("frId") List<String> frId, @Param("catId") List<String> catId);

	
	@Query(value="select d.sell_bill_detail_no, d.item_id, h.fr_id, f.fr_name, t.item_name, c.cat_name, d.cat_id, h.bill_date, sum(d.qty) as qty,"
			+" sum(d.mrp*d.qty) as amount from t_sell_bill_detail d, t_sell_bill_header h, m_category c,"
			+" m_item t, m_franchisee f WHERE h.bill_date BETWEEN :fromDate AND :toDate AND d.cat_id IN(:catId)"
			+" AND c.cat_id=d.cat_id AND d.item_id=t.id AND h.sell_bill_no=d.sell_bill_no AND h.fr_id IN(:frId) AND"
			+" h.fr_id=f.fr_id GROUP BY h.bill_date,  h.fr_id ",nativeQuery=true)
	List<GetRepItemwiseSell> getRepFrDateCatwiseSell(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("frId") List<String> frId, @Param("catId") List<String> catId);

	
}
