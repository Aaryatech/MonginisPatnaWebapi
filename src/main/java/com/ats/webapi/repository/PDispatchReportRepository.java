package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.report.PDispatchReport;

@Repository
public interface PDispatchReportRepository extends JpaRepository<PDispatchReport, Integer>{

	@Query(value="select t_order.order_id,m_category.cat_id,m_category.cat_name,m_cat_sub.sub_cat_id,t_order.fr_id,m_franchisee.fr_name,t_order.item_id,m_item.item_name,SUM(t_order.order_qty) as order_qty,SUM(t_order.edit_qty) as edit_qty,t_order.is_bill_generated from t_order,m_category,m_franchisee,m_item,m_cat_sub\n" + 
			"	where t_order.order_id In(select order_id from t_order where production_date=:productionDateYMD and fr_id in(:frId)) And (select cat_id from m_fr_menu_show where menu_id=t_order.menu_id) In(:categories) \n" + 
			"	And (select cat_id from m_fr_menu_show where menu_id=t_order.menu_id)=m_category.cat_id And t_order.fr_id=m_franchisee.fr_id and m_item.id=t_order.item_id And m_cat_sub.sub_cat_id=m_item.item_grp2 \n" + 
			"	group by t_order.item_id,t_order.fr_id order by t_order.fr_id,m_item.item_grp1,m_item.item_grp2,m_item.item_name" + 
			"",nativeQuery=true)
	List<PDispatchReport> getPDispatchItemReport(@Param("productionDateYMD")String productionDateYMD,@Param("frId") List<String> frId,@Param("categories") List<String> categories);
	
	

}
