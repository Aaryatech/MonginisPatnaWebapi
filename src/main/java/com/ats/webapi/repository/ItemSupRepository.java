package com.ats.webapi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.ItemSup;

@Repository
public interface ItemSupRepository extends JpaRepository<ItemSup, Integer>{

	@Modifying
	@Transactional
	@Query("Update ItemSup  SET del_status=1 WHERE item_id=:itemId")
	int deleteItemSup(@Param("itemId")int itemId);

}
