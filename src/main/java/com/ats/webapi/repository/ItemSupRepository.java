package com.ats.webapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.Info;
import com.ats.webapi.model.ItemSup;

@Repository
public interface ItemSupRepository extends JpaRepository<ItemSup, Integer>{

	@Modifying
	@Transactional
	@Query("Delete from ItemSup  WHERE item_id IN(:itemId)")
	int deleteItemSup(@Param("itemId")List<String> itemId);
	

	@Transactional
	@Modifying
	@Query("UPDATE ItemSup i SET i.itemHsncd=:itemHsncd  WHERE i.itemId=:id")
	int updateItemHsnAndPerInSup(@Param("id")Integer id,@Param("itemHsncd") String itemHsncd);


	List<ItemSup> findByItemId(int itemId);


}
