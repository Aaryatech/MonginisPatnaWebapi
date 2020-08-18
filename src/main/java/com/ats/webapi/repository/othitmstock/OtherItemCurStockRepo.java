package com.ats.webapi.repository.othitmstock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.otheritemstock.OtherItemCurStock;

public interface OtherItemCurStockRepo extends JpaRepository<OtherItemCurStock, Integer> {

	/*@Query(value = " SELECT m_item.id,m_item.item_name,m_item.item_id, m_franchisee.fr_name,"
			+ " COALESCE((SELECT other_item_stock_detail.opening_stock FROM other_item_stock_detail,other_item_stock_header "
			+ " WHERE other_item_stock_header.month=:month AND m_franchisee.fr_id=other_item_stock_header.fr_id AND "
			+ " other_item_stock_header.other_stock_header_id=other_item_stock_detail.other_stock_header_id AND other_item_stock_header.status=0 AND "

			+ " other_item_stock_detail.other_item_id=m_item.id),0) as opening_stock , "
			+ " COALESCE((SELECT sum(other_item_stock_detail.damage_stock) FROM other_item_stock_detail,other_item_stock_header "
			+ " WHERE other_item_stock_header.month=:month AND m_franchisee.fr_id=other_item_stock_header.fr_id AND other_item_stock_header.status=0 AND "
			+ " other_item_stock_header.other_stock_header_id=other_item_stock_detail.other_stock_header_id AND "
			+ " other_item_stock_detail.other_item_id=m_item.id),0) as damaged_stock ,"
			+ " COALESCE((SELECT SUM(t_other_bill_detail.bill_qty) FROM t_other_bill_header,t_other_bill_detail WHERE "
			+ "       t_other_bill_header.bill_no=t_other_bill_detail.bill_no AND t_other_bill_header.fr_id=m_franchisee.fr_id AND "
			+ " t_other_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_other_bill_detail.item_id=m_item.id),0)"
			+ " AS purchase_qty,"
			+ " COALESCE((SELECT SUM(t_sell_bill_detail.qty) FROM t_sell_bill_detail,t_sell_bill_header WHERE "
			+ " t_sell_bill_header.sell_bill_no=t_sell_bill_detail.sell_bill_no "
			+ " AND t_sell_bill_header.fr_id=m_franchisee.fr_id AND t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate "
			+ " AND t_sell_bill_detail.item_id=m_item.id),0) AS sell_qty " + " FROM m_item,m_franchisee "
			+ " WHERE m_item.item_grp1=:catId AND m_franchisee.fr_id=:frId and m_item.del_status=0 GROUP by m_item.id ORDER BY m_item.item_grp2,m_item.item_name", nativeQuery = true)
	List<OtherItemCurStock> getCurOtherItemCurStock(@Param("catId") int catId, @Param("month") int month,
			@Param("frId") int frId, @Param("fromDate") String fromDate, @Param("toDate") String toDate);
*/
	
	@Query(value = "  SELECT\n" + 
			"        dd.id,\n" + 
			"        dd.item_name,\n" + 
			"        dd.item_id,\n" + 
			"        dd.fr_name,\n" + 
			"\n" + 
			"coalesce(b.opening_stock,0) as opening_stock,coalesce(c.damaged_stock,0) as damaged_stock,\n" + 
			"coalesce(d.purchase_qty,0) as purchase_qty,coalesce(e.sell_qty,0) as sell_qty\n" + 
			"\n" + 
			"from\n" + 
			"        ( select\n" + 
			"            m_item.id,\n" + 
			"            m_item.item_name,\n" + 
			"            m_franchisee.fr_name,\n" + 
			"            m_item.item_id \n" + 
			"        from\n" + 
			"            m_franchisee,\n" + 
			"            m_item \n" + 
			"        where\n" + 
			"            m_item.item_grp1=:catId  \n" + 
			"            and m_item.del_status=0 \n" + 
			"            and m_item.item_is_used=1          \n" + 
			"            and m_franchisee.fr_id=:frId  \n" + 
			"        ORDER BY\n" + 
			"            m_item.item_grp2,\n" + 
			"            m_item.item_name) dd \n" + 
			"\n" + 
			"        left join (SELECT\n" + 
			"            other_item_stock_detail.opening_stock as opening_stock,other_item_stock_detail.other_item_id\n" + 
			"        FROM\n" + 
			"            other_item_stock_detail,\n" + 
			"            other_item_stock_header  \n" + 
			"        WHERE\n" + 
			"            other_item_stock_header.month=:month and  \n" + 
			"            other_item_stock_header.fr_id=:frId   \n" + 
			"            AND  other_item_stock_header.other_stock_header_id=other_item_stock_detail.other_stock_header_id \n" + 
			"            AND other_item_stock_header.status=0 \n" + 
			"            group by other_item_stock_detail.other_item_id ) b on b.other_item_id=dd.id\n" + 
			"\n" + 
			"        LEFT JOIN (SELECT\n" + 
			"            sum(other_item_stock_detail.damage_stock) as damaged_stock,other_item_stock_detail.other_item_id\n" + 
			"        FROM\n" + 
			"            other_item_stock_detail,\n" + 
			"            other_item_stock_header  \n" + 
			"        WHERE\n" + 
			"            other_item_stock_header.month=:month\n" + 
			"            AND other_item_stock_header.fr_id=:frId\n" + 
			"            AND other_item_stock_header.status=0 \n" + 
			"            AND  other_item_stock_header.other_stock_header_id=other_item_stock_detail.other_stock_header_id \n" + 
			"             group by other_item_stock_detail.other_item_id\n" + 
			"        ) c on c.other_item_id=dd.id\n" + 
			"        left join (SELECT\n" + 
			"            SUM(t_other_bill_detail.bill_qty) as purchase_qty,t_other_bill_detail.item_id\n" + 
			"        FROM\n" + 
			"            t_other_bill_header,\n" + 
			"            t_other_bill_detail \n" + 
			"        WHERE\n" + 
			"            t_other_bill_header.bill_no=t_other_bill_detail.bill_no \n" + 
			"            AND t_other_bill_header.fr_id=:frId\n" + 
			"            AND  t_other_bill_header.bill_date BETWEEN :fromDate  AND :toDate\n" + 
			"           group by t_other_bill_detail.item_id )d on  d.item_id=dd.id\n" + 
			"    \n" + 
			"        left join (SELECT\n" + 
			"            SUM(t_sell_bill_detail.qty) as sell_qty, t_sell_bill_detail.item_id\n" + 
			"        FROM\n" + 
			"            t_sell_bill_detail,\n" + 
			"            t_sell_bill_header \n" + 
			"        WHERE\n" + 
			"            t_sell_bill_header.sell_bill_no=t_sell_bill_detail.sell_bill_no  \n" + 
			"            AND t_sell_bill_header.fr_id=:frId\n" + 
			"            AND t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate  \n" + 
			"          group by t_sell_bill_detail.item_id  )e on  e.item_id=dd.id", nativeQuery = true)
	List<OtherItemCurStock> getCurOtherItemCurStock(@Param("catId") int catId, @Param("month") int month,
			@Param("frId") int frId, @Param("fromDate") String fromDate, @Param("toDate") String toDate);

	
}
