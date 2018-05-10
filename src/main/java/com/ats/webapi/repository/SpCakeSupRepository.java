package com.ats.webapi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.GetSpCkSupplement;
import com.ats.webapi.model.SpCakeSupplement;

@Repository
public interface SpCakeSupRepository extends JpaRepository<SpCakeSupplement, Integer>{

	SpCakeSupplement saveAndFlush(SpCakeSupplement spCakeSupplement);

	@Modifying
	@Transactional
	@Query("Update SpCakeSupplement  SET del_status=1 WHERE sp_id=:id")
	int deleteSpCakeSup(@Param("id") int id);


}
