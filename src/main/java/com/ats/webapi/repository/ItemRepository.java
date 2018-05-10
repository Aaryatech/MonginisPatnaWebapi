package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	public Item save(Item item);

	public Item findOne(int id);

	public List<Item> findByItemGrp1AndDelStatusOrderByItemNameAsc(String itemGrp1,int i);

	@Query(value="select * from m_item where m_item.id IN (:itemList) AND m_item.del_status=0",nativeQuery=true)
	public List<Item> findByDelStatusAndItemIdIn(@Param("itemList")List<Integer> itemList);

	public List<Item> findByDelStatusOrderByItemGrp2(int i);//changed to order by subcatId 21/Apr

	@Query(value="select MAX(item_id)+1 from m_item",nativeQuery=true)
	public int findMaxId();

	public List<Item> findByItemGrp1AndDelStatusOrderByItemGrp2AscItemSortIdAsc(String itemGrp1, int i);

	public List<Item> findByDelStatusOrderByItemGrp2AscItemSortIdAsc(int i);

	@Query(value="select * from m_item where m_item.id IN (Select m_item_sup.item_id from m_item_sup where m_item_sup.is_allow_bday=:isAllowBday) AND m_item.del_status=:delStatus",nativeQuery=true)
	public List<Item> findByIsAllowBirthayAndDelStatus(@Param("isAllowBday")int isAllowBday,@Param("delStatus") int delStatus);

	@Query(value="select m_item.* from m_item where m_item.del_status=0 And m_item.item_grp1=:itemGrp1 And m_item.id not in(select m_item_sup.item_id from m_item_sup where m_item_sup.del_status=0) order by m_item.item_name ",nativeQuery=true)
	public List<Item> findByItemGrp1(@Param("itemGrp1")String itemGrp1);

	@Query(value="select * from m_item where m_item.id IN (:itemList)",nativeQuery=true)
	public List<Item> findAllItems(@Param("itemList")List<Integer> itemList);

	public List<Item> findByItemGrp1InAndDelStatusOrderByItemGrp2AscItemSortIdAsc(List<String> catIdList, int i);
	
	public List<Item>  findByItemGrp2OrderByItemGrp2(String subCatId);
	
} 
