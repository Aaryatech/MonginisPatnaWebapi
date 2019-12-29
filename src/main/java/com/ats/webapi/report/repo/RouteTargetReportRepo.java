package com.ats.webapi.report.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.webapi.model.report.RouteTargetReport;

public interface RouteTargetReportRepo extends JpaRepository<RouteTargetReport, Integer> {

	@Query(value="SELECT\n" + 
			"    UUID() AS id, \n" + 
			"    m_fr_route.route_name, \n" + 
			"    m_fr_route.route_id, \n" + 
			"    m_franchisee.fr_name, \n" + 
			"    m_franchisee.fr_target\n" + 
			"FROM\n" + 
			"    m_franchisee, \n" + 
			"    m_fr_route, \n" + 
			"    m_franchise_sup\n" + 
			"WHERE\n" + 
			"    m_franchisee.fr_route_id = m_fr_route.route_id AND\n" + 
			"    m_franchisee.fr_id = m_franchise_sup.fr_id AND \n" + 
			"    m_franchisee.del_status = 0\n" + 
			"ORDER BY\n" + 
			"    m_fr_route.route_seq_no, m_fr_route.route_id, \n" + 
			"    m_franchise_sup.no_in_route",nativeQuery=true)
	public List<RouteTargetReport> getRouteTargeTReportList();
}
