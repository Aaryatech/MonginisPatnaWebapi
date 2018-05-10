package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.OrderSpecialCake;


public interface ConfiSpCodeRepository extends JpaRepository<OrderSpecialCake, Long>{
	
	@Query(value="SELECT  m.sp_code FROM m_sp_cake m , m_fr_configure f"
			+ " WHERE m.sp_id IN(:items) AND  m.del_status=0"
			+ " AND f.menu_id=:menuId AND f.fr_id=:frId",nativeQuery=true)
	public List<String> findSpCode(@Param ("items") List<Integer>items,@Param("frId")int frId,@Param("menuId")int menuId);

	
	
}