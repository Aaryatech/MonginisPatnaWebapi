package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.Info;
import com.ats.webapi.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	public Item save(Item item);

	public Item findOne(int id);

	public List<Item> findByItemGrp1AndDelStatusOrderByItemNameAscItemGrp2Asc(String itemGrp1, int i);
	public List<Item> findByItemGrp1AndDelStatusAndItemIsUsedOrderByItemNameAscItemGrp2Asc(String itemGrp1, int i,int isUsed);

	@Query(value = "select * from m_item where m_item.id IN (:itemList) AND m_item.del_status=0", nativeQuery = true)
	public List<Item> findByDelStatusAndItemIdIn(@Param("itemList") List<Integer> itemList);

	public List<Item> findByDelStatusOrderByItemGrp2(int i);// changed to order by subcatId 21/Apr

	@Query(value = "select MAX(CAST(SUBSTRING(item_id,1,LENGTH(item_id)-0) AS SIGNED))+1  from m_item\n"
			+ "", nativeQuery = true)
	public int findMaxId();

	public List<Item> findByItemGrp1AndDelStatusOrderByItemGrp1AscItemGrp2AscItemNameAsc(String itemGrp1, int i);

	public List<Item> findByDelStatusOrderByItemGrp2AscItemSortIdAsc(int i);

	@Query(value = "select * from m_item where m_item.id IN (Select m_item_sup.item_id from m_item_sup where m_item_sup.is_allow_bday=:isAllowBday) AND m_item.del_status=:delStatus", nativeQuery = true)
	public List<Item> findByIsAllowBirthayAndDelStatus(@Param("isAllowBday") int isAllowBday,
			@Param("delStatus") int delStatus);

	@Query(value = "select m_item.* from m_item where m_item.del_status=0 And m_item.item_grp1=:itemGrp1 And m_item.id not in(select m_item_sup.item_id from m_item_sup where m_item_sup.del_status=0) order by m_item.item_name ", nativeQuery = true)
	public List<Item> findByItemGrp1(@Param("itemGrp1") String itemGrp1);

	@Query(value = "select * from m_item where m_item.id IN (:itemList) Order By item_name ASC,item_grp2", nativeQuery = true)
	public List<Item> findAllItems(@Param("itemList") List<Integer> itemList);

	/*
	 * public List<Item>
	 * findByItemGrp1InAndDelStatusOrderByItemGrp2AscItemSortIdAsc(List<String>
	 * catIdList, int i);
	 */
	public List<Item> findByItemGrp2AndDelStatusOrderByItemGrp2AscItemNameAsc(String subCatId, int delStatus);

	public List<Item> findByIdIn(List<String> id);

	@Transactional
	@Modifying
	@Query("UPDATE Item i SET i.delStatus=1  WHERE i.id IN (:idList)")
	public int deleteItems(@Param("idList") List<Integer> id);

	@Transactional
	@Modifying
	@Query("UPDATE Item i SET i.itemIsUsed=4  WHERE i.id IN (:idList)")
	public int inactivateItems(@Param("idList") List<Integer> id);

	@Transactional
	@Modifying
	@Query("UPDATE Item i SET itemTax1=:itemTax1,itemTax2=:itemTax2,itemTax3=:itemTax3  WHERE i.id=:id")
	public int updateItemHsnAndPerInItem(@Param("id") Integer id, @Param("itemTax1") double itemTax1,
			@Param("itemTax2") double itemTax2, @Param("itemTax3") double itemTax3);

	public List<Item> findByDelStatusOrderByItemGrp1AscItemGrp2AscItemNameAsc(int i);

	public List<Item> findByItemGrp1InAndDelStatusOrderByItemGrp2AscItemNameAsc(List<String> catIdList, int i);

	@Query(value = "SELECT m_item.* FROM m_item WHERE m_item.item_grp1=:catId and m_item.item_is_used=1 and m_item.del_status=0 ORDER BY m_item.item_grp2,m_item.item_name", nativeQuery = true)
	public List<Item> getOtherItemsForFr(@Param("catId") int catId);// @Param("frId") int frId,

	@Query(value="SELECT * FROM `m_item` WHERE item_grp2=:subCatId AND del_status=0 ORDER BY item_grp2 ASC",nativeQuery=true)
	public List<Item> findByItemGrp2(@Param("subCatId") String subCatId);

}
