package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.FranchiseForDispatch;
@Repository
public interface FranchiseForDispatchRepository extends JpaRepository<FranchiseForDispatch, Integer>{

	@Query(value=" SELECT CONCAT(fr_name, ' ', fr_code) AS fr_name,fr_id,  fr_route_id,fr_rate,fr_rate_cat FROM m_franchisee WHERE "
			+ "fr_route_id =:routeId  and del_status=0"
			+ "",nativeQuery=true)
	List<FranchiseForDispatch> getFranchiseForDispatch(@Param("routeId")int routeId);

}
