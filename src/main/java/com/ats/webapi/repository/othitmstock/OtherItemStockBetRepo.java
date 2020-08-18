package com.ats.webapi.repository.othitmstock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.otheritemstock.OtherItemStockBet;

public interface OtherItemStockBetRepo extends JpaRepository<OtherItemStockBet, Integer> {

	/*@Query(value = " SELECT m_item.id,m_item.item_name,m_item.item_id, m_franchisee.fr_name, "
			+ "COALESCE((SELECT SUM(other_item_stock_detail.opening_stock) FROM other_item_stock_detail,other_item_stock_header"
			+ " WHERE  m_franchisee.fr_id=other_item_stock_header.fr_id and "
			+ "other_item_stock_header.other_stock_header_id=other_item_stock_detail.other_stock_header_id AND "
			+ "other_item_stock_detail.other_item_id=m_item.id AND other_item_stock_header.month  IN(:month)),0) as first_opening, "
			+

			" COALESCE((SELECT SUM(other_item_stock_detail.damage_stock) FROM other_item_stock_detail,other_item_stock_header "
			+ " WHERE other_item_stock_header.month IN (:month) AND m_franchisee.fr_id=other_item_stock_header.fr_id and "
			+ " other_item_stock_header.other_stock_header_id=other_item_stock_detail.other_stock_header_id AND "
			+ " other_item_stock_detail.other_item_id=m_item.id AND other_item_stock_header.month  IN(:month)),0) as damaged_stock, "
			+

			" COALESCE((SELECT SUM(t_other_bill_detail.bill_qty) FROM t_other_bill_header,t_other_bill_detail "
			+ " WHERE t_other_bill_header.bill_no=t_other_bill_detail.bill_no AND t_other_bill_header.fr_id=m_franchisee.fr_id "
			+ " AND t_other_bill_header.bill_date BETWEEN :prevFromDate AND :prevToDate AND t_other_bill_detail.item_id=m_item.id),0) "
			+ " AS first_purchase, "

			+ "  COALESCE((SELECT SUM(t_sell_bill_detail.qty) FROM t_sell_bill_detail,t_sell_bill_header WHERE "
			+ "       t_sell_bill_header.sell_bill_no=t_sell_bill_detail.sell_bill_no AND t_sell_bill_header.fr_id=m_franchisee.fr_id "
			+ " AND t_sell_bill_header.bill_date BETWEEN :prevFromDate AND :prevToDate AND t_sell_bill_detail.item_id=m_item.id),0) "
			+ " AS first_sell,"
			+ " COALESCE((SELECT SUM(t_other_bill_detail.bill_qty) FROM t_other_bill_header,t_other_bill_detail WHERE "
			+ "       t_other_bill_header.bill_no=t_other_bill_detail.bill_no AND t_other_bill_header.fr_id=m_franchisee.fr_id "
			+ " AND t_other_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_other_bill_detail.item_id=m_item.id),0) "
			+ " AS purchase_qty,"
			+ "  COALESCE((SELECT SUM(t_sell_bill_detail.qty) FROM t_sell_bill_detail,t_sell_bill_header WHERE "
			+ "       t_sell_bill_header.sell_bill_no=t_sell_bill_detail.sell_bill_no AND t_sell_bill_header.fr_id=m_franchisee.fr_id "
			+ " AND t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_sell_bill_detail.item_id=m_item.id),0) "
			+ " AS sell_qty " + "       FROM m_item,m_franchisee "
			+ "       WHERE m_item.item_grp1=:catId AND m_franchisee.fr_id=:frId GROUP by m_item.id  ORDER BY m_item.item_grp2,m_item.item_name", nativeQuery = true)
	List<OtherItemStockBet> getOtherItemStockBet(@Param("catId") int catId, @Param("month") int month,
			@Param("frId") int frId, @Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("prevFromDate") String prevFromDate, @Param("prevToDate") String prevToDate);
*/
	//SACHIN 22-07-2020
	
	
	@Query(value = "SELECT  " + 
			"dd.id,dd.item_name,dd.item_id,dd.fr_name,coalesce(b.first_opening,0) as first_opening,coalesce(c.damaged_stock,0) as damaged_stock,"
			+ "coalesce(d.first_purchase,0) as first_purchase, " + 
			"coalesce(e.first_sell,0) as first_sell ,coalesce(f.purchase_qty,0) as purchase_qty ,"
			+ "coalesce(g.sell_qty,0) as sell_qty from ( select  m_item.id, " + 
			"        m_item.item_name,m_franchisee.fr_name, " + 
			"        m_item.item_id from m_franchisee,m_item where    m_item.item_grp1=:catId  and m_item.del_status=0 and m_item.item_is_used=1 " + 
			"         and m_franchisee.fr_id=:frId  ORDER BY " + 
			"        m_item.item_grp2, " + 
			"        m_item.item_name) dd "
			+ ""
			+ "LEFT JOIN (SELECT SUM(other_item_stock_detail.opening_stock) as first_opening,other_item_stock_detail.other_item_id  FROM other_item_stock_detail,other_item_stock_header " + 
			" WHERE  other_item_stock_header.fr_id=:frId and " + 
			"other_item_stock_header.other_stock_header_id=other_item_stock_detail.other_stock_header_id AND  " + 
			"other_item_stock_header.month  IN(:month) GROUP BY  other_item_stock_detail.other_item_id) b on b.other_item_id=dd.id "
	+	" LEFT JOIN (SELECT SUM(other_item_stock_detail.damage_stock) AS damaged_stock,other_item_stock_detail.other_item_id FROM other_item_stock_detail,other_item_stock_header "
		+ " WHERE other_item_stock_header.month IN (:month) AND other_item_stock_header.fr_id=:frId and "
		+ " other_item_stock_header.other_stock_header_id=other_item_stock_detail.other_stock_header_id AND "
		+ "  other_item_stock_header.month  IN(:month) GROUP BY other_item_stock_detail.other_item_id)  c on c.other_item_id=dd.id  "
		+" LEFT JOIN (SELECT SUM(t_other_bill_detail.bill_qty) AS first_purchase,t_other_bill_detail.item_id  FROM t_other_bill_header,t_other_bill_detail "
		+ " WHERE t_other_bill_header.bill_no=t_other_bill_detail.bill_no AND t_other_bill_header.fr_id=:frId "
		+ " AND t_other_bill_header.bill_date BETWEEN :prevFromDate AND :prevToDate group by t_other_bill_detail.item_id ) d on d.item_id=dd.id  "
		+ "  LEFT JOIN (SELECT SUM(t_sell_bill_detail.qty) as first_sell,t_sell_bill_detail.item_id  FROM t_sell_bill_detail,t_sell_bill_header WHERE "
		+ "       t_sell_bill_header.sell_bill_no=t_sell_bill_detail.sell_bill_no AND t_sell_bill_header.fr_id=:frId"
		+ " AND t_sell_bill_header.bill_date BETWEEN :prevFromDate AND :prevToDate  group by t_sell_bill_detail.item_id)  e on e.item_id=dd.id "
		+ " LEFT JOIN (SELECT SUM(t_other_bill_detail.bill_qty) as purchase_qty,t_other_bill_detail.item_id  FROM t_other_bill_header,t_other_bill_detail WHERE "
		+ "       t_other_bill_header.bill_no=t_other_bill_detail.bill_no AND t_other_bill_header.fr_id=:frId "
		+ " AND t_other_bill_header.bill_date BETWEEN :fromDate AND :toDate group by t_other_bill_detail.item_id )  f on f.item_id=dd.id "
		+ "  LEFT JOIN(SELECT SUM(t_sell_bill_detail.qty) as sell_qty,t_sell_bill_detail.item_id  FROM t_sell_bill_detail,t_sell_bill_header WHERE "
		+ "       t_sell_bill_header.sell_bill_no=t_sell_bill_detail.sell_bill_no AND t_sell_bill_header.fr_id=:frId "
		+ " AND t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate  group by t_sell_bill_detail.item_id ) g on g.item_id=dd.id   "
		
			
			+ " ", nativeQuery = true)
	List<OtherItemStockBet> getOtherItemStockBet(@Param("catId") int catId, @Param("month") int month,
			@Param("frId") int frId, @Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("prevFromDate") String prevFromDate, @Param("prevToDate") String prevToDate);
	/*@Query(value = " SELECT m_item.id,m_item.item_name,m_item.item_id, m_franchisee.fr_name, "
			+ "COALESCE((SELECT SUM(other_item_stock_detail.opening_stock) FROM other_item_stock_detail,other_item_stock_header"
			+ " WHERE  m_franchisee.fr_id=other_item_stock_header.fr_id and "
			+ "other_item_stock_header.other_stock_header_id=other_item_stock_detail.other_stock_header_id AND "
			+ "other_item_stock_detail.other_item_id=m_item.id AND other_item_stock_header.month  IN(:month)),0) as first_opening, "
			+

			" COALESCE((SELECT SUM(other_item_stock_detail.damage_stock) FROM other_item_stock_detail,other_item_stock_header "
			+ " WHERE other_item_stock_header.month IN (:month) AND m_franchisee.fr_id=other_item_stock_header.fr_id and "
			+ " other_item_stock_header.other_stock_header_id=other_item_stock_detail.other_stock_header_id AND "
			+ " other_item_stock_detail.other_item_id=m_item.id AND other_item_stock_header.month  IN(:month)),0) as damaged_stock, "
			+

			" COALESCE((SELECT SUM(t_other_bill_detail.bill_qty) FROM t_other_bill_header,t_other_bill_detail "
			+ " WHERE t_other_bill_header.bill_no=t_other_bill_detail.bill_no AND t_other_bill_header.fr_id=m_franchisee.fr_id "
			+ " AND t_other_bill_header.bill_date BETWEEN :prevFromDate AND :prevToDate AND t_other_bill_detail.item_id=m_item.id),0) "
			+ " AS first_purchase, "

			+ "  COALESCE((SELECT SUM(t_sell_bill_detail.qty) FROM t_sell_bill_detail,t_sell_bill_header WHERE "
			+ "       t_sell_bill_header.sell_bill_no=t_sell_bill_detail.sell_bill_no AND t_sell_bill_header.fr_id=m_franchisee.fr_id "
			+ " AND t_sell_bill_header.bill_date BETWEEN :prevFromDate AND :prevToDate AND t_sell_bill_detail.item_id=m_item.id),0) "
			+ " AS first_sell,"
			+ " COALESCE((SELECT SUM(t_other_bill_detail.bill_qty) FROM t_other_bill_header,t_other_bill_detail WHERE "
			+ "       t_other_bill_header.bill_no=t_other_bill_detail.bill_no AND t_other_bill_header.fr_id=m_franchisee.fr_id "
			+ " AND t_other_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_other_bill_detail.item_id=m_item.id),0) "
			+ " AS purchase_qty,"
			+ "  COALESCE((SELECT SUM(t_sell_bill_detail.qty) FROM t_sell_bill_detail,t_sell_bill_header WHERE "
			+ "       t_sell_bill_header.sell_bill_no=t_sell_bill_detail.sell_bill_no AND t_sell_bill_header.fr_id=m_franchisee.fr_id "
			+ " AND t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_sell_bill_detail.item_id=m_item.id),0) "
			+ " AS sell_qty " + "       FROM m_item,m_franchisee "
			+ "       WHERE m_item.item_grp1=:catId AND m_franchisee.fr_id=:frId GROUP by m_item.id  ORDER BY m_item.item_grp2,m_item.item_name", nativeQuery = true)
	List<OtherItemStockBet> getOtherItemStockBet(@Param("catId") int catId, @Param("month") int month,
			@Param("frId") int frId, @Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("prevFromDate") String prevFromDate, @Param("prevToDate") String prevToDate);
*/
}
