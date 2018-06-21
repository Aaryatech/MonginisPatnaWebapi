package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.webapi.model.Route;


public interface RouteRepository extends JpaRepository<Route, Integer> {
	Route save(Route route);
	List<Route> findAll();
	Route findOne(int routeId);
	List<Route> findByDelStatus(int id);
	List<Route> findByDelStatusOrderByRouteNameAsc(int i);
	List<Route> findByRouteIdIn(List<Integer> routeId);	

}
