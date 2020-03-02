package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.AllFrIdName;

public interface AllFrIdNameRepository extends JpaRepository<AllFrIdName, Integer> {
	
	@Query(value=" SELECT fr_id,CONCAT(fr_name, ' ', fr_code) AS fr_name from m_franchisee WHERE del_status=0 order by fr_Id Asc",nativeQuery=true)
	
	List<AllFrIdName> getAllFrIdName();
	
		@Query(value="select m_franchisee.fr_id,CONCAT(m_franchisee.fr_name, ' ', m_franchisee.fr_code) AS  fr_name from m_franchisee where m_franchisee.del_status=0 And  m_franchisee.fr_id NOT"
					+" IN(select t_order.fr_id from t_order where order_date=:orderDate AND menu_id=:menuId) order by m_franchisee.fr_name Asc"
					,nativeQuery=true)
	public List<AllFrIdName> findNonOrders(@Param("orderDate") String orderDate, @Param("menuId") int menuId);

		@Query(value=" SELECT fr_id,CONCAT(fr_name, ' ', fr_code) AS fr_name from m_franchisee where fr_id=:frId",nativeQuery=true)
		AllFrIdName findByFrId(@Param("frId")int frId);

}

