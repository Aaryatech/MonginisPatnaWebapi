package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.FrStockBetweenMonth;
import com.ats.webapi.model.GetCurrentStockDetails;


public interface FrStockBetweenMonthRepository extends JpaRepository<GetCurrentStockDetails, Integer> {

	
	@Query(value = "SELECT m_item.item_id,m_item.item_name , m_fr_opening_stock_detail.opening_stock_detail_id,m_fr_opening_stock_detail.opening_stock_header_id , COALESCE((SUM(opening_stock)),0) AS opening_stock , COALESCE((SUM(total_purchase)),0) AS total_purchase, COALESCE((SUM(total_grn_gvn)),0) AS total_grn_gvn, "
			+ "COALESCE((SUM(total_sell)),0) AS total_sell FROM m_fr_opening_stock_detail,m_item WHERE m_fr_opening_stock_detail.item_id=m_item.id AND "
			+ " opening_stock_header_id IN(SELECT opening_stock_header_id FROM m_fr_opening_stock_header WHERE  month BETWEEN :fromMonth AND :toMonth AND fr_id=:frId ) AND m_fr_opening_stock_detail.item_id IN(:itemIdList) GROUP BY item_id", nativeQuery = true)
	List<GetCurrentStockDetails> getStockBetweenMonths(@Param("frId") int frId,@Param("fromMonth") int fromMonth,@Param("toMonth") int toMonth, @Param("itemIdList") List<Integer> itemIdList);

	 
} 
