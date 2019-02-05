package com.ats.webapi.repository.frpurchasereport;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ats.webapi.model.report.frpurchase.SalesReportRoyalty;

public interface SalesReportRoyaltyRepo extends JpaRepository<SalesReportRoyalty, Integer> {
	//report 5
	@Query(value=" SELECT m_item.id,m_item.item_name,m_category.cat_name,m_category.cat_id, COALESCE((SELECT SUM(t_bill_detail.bill_qty) FROM t_bill_detail,t_bill_header WHERE t_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_bill_header.bill_no=t_bill_detail.bill_no AND m_item.id=t_bill_detail.item_id AND t_bill_header.fr_id IN(:frIdList) AND t_bill_header.del_status=0),0) AS t_bill_qty," + 
			" COALESCE((SELECT SUM(t_bill_detail.taxable_amt) FROM t_bill_detail,t_bill_header WHERE t_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_bill_header.bill_no=t_bill_detail.bill_no AND m_item.id=t_bill_detail.item_id AND t_bill_header.fr_id IN(:frIdList) AND t_bill_header.del_status=0),0) AS  t_bill_taxable_amt,\n" + 
			" COALESCE((SELECT SUM(t_credit_note_details.grn_gvn_qty) FROM t_credit_note_details,t_credit_note_header WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=1 AND t_credit_note_header.fr_id IN(:frIdList) AND t_credit_note_details.del_status=0),0) AS  t_grn_qty," + 
		
			" COALESCE((SELECT SUM(t_credit_note_details.taxable_amt) FROM t_credit_note_details,t_credit_note_header WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=1 AND t_credit_note_header.fr_id IN(:frIdList) AND t_credit_note_details.del_status=0),0) AS  t_grn_taxable_amt," + 
		
			" COALESCE((SELECT SUM(t_credit_note_details.grn_gvn_qty) FROM t_credit_note_details,t_credit_note_header WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=0 AND t_credit_note_header.fr_id IN(:frIdList) AND t_credit_note_details.del_status=0),0) AS  t_gvn_qty," + 
			
			" COALESCE((SELECT SUM(t_credit_note_details.taxable_amt) FROM t_credit_note_details,t_credit_note_header WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=0 AND t_credit_note_header.fr_id IN(:frIdList) AND t_credit_note_details.del_status=0),0) AS  t_gvn_taxable_amt" + 
			
			"	FROM m_item,m_category WHERE m_item.item_grp1=m_category.cat_id group by m_item.id order by m_category.cat_id",nativeQuery=true)
		
		List<SalesReportRoyalty> getSaleReportRoyalty(@Param("frIdList") List<String> frIdList,@Param("fromDate") String fromDate,@Param("toDate") String toDate);

	//report 5 all fr //Ok Tested changed to credit note 11 april
	@Query(value=" SELECT m_item.id,m_item.item_name,m_category.cat_name,m_category.cat_id, COALESCE((SELECT SUM(t_bill_detail.bill_qty) FROM t_bill_detail,t_bill_header WHERE t_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_bill_header.bill_no=t_bill_detail.bill_no AND m_item.id=t_bill_detail.item_id AND t_bill_header.del_status=0),0) AS t_bill_qty," + 
			"	COALESCE((SELECT SUM(t_bill_detail.taxable_amt) FROM t_bill_detail,t_bill_header WHERE t_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_bill_header.bill_no=t_bill_detail.bill_no AND m_item.id=t_bill_detail.item_id AND  t_bill_header.del_status=0),0) AS  t_bill_taxable_amt," + 
			"	COALESCE((SELECT SUM(t_credit_note_details.grn_gvn_qty) FROM t_credit_note_details,t_credit_note_header WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=1 AND  t_credit_note_details.del_status=0),0) AS  t_grn_qty," + 
			"	COALESCE((SELECT SUM(t_credit_note_details.taxable_amt) FROM t_credit_note_details,t_credit_note_header WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=1 AND  t_credit_note_details.del_status=0),0) AS  t_grn_taxable_amt," + 
			"	COALESCE((SELECT SUM(t_credit_note_details.grn_gvn_qty) FROM t_credit_note_details,t_credit_note_header WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=0 AND  t_credit_note_details.del_status=0),0) AS  t_gvn_qty," + 
			"	COALESCE((SELECT SUM(t_credit_note_details.taxable_amt) FROM t_credit_note_details,t_credit_note_header WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=0 AND  t_credit_note_details.del_status=0),0) AS  t_gvn_taxable_amt " + 
			"	FROM m_item,m_category WHERE m_item.item_grp1=m_category.cat_id group by m_item.id order by m_category.cat_id",nativeQuery=true)
		
		List<SalesReportRoyalty> getSaleReportRoyaltyAllFr(@Param("fromDate") String fromDate,@Param("toDate") String toDate);

	
/*	//report 5 all fr //Ok Tested
		@Query(value=" SELECT m_item.id,m_item.item_name,m_category.cat_name,m_category.cat_id, COALESCE((SELECT SUM(t_bill_detail.bill_qty) FROM t_bill_detail,t_bill_header WHERE t_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_bill_header.bill_no=t_bill_detail.bill_no AND m_item.id=t_bill_detail.item_id  AND t_bill_header.del_status=0),0) AS t_bill_qty,\n" + 
				"\n" + 
				"COALESCE((SELECT SUM(t_bill_detail.taxable_amt) FROM t_bill_detail,t_bill_header WHERE t_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_bill_header.bill_no=t_bill_detail.bill_no AND m_item.id=t_bill_detail.item_id  AND t_bill_header.del_status=0),0) AS  t_bill_taxable_amt,\n" + 
				"\n" + 
				"COALESCE((SELECT SUM(t_grn_gvn.grn_gvn_qty) FROM t_grn_gvn WHERE t_grn_gvn.grn_gvn_date  BETWEEN :fromDate AND :toDate AND   m_item.id=t_grn_gvn.item_id AND t_grn_gvn.is_credit_note=1 AND t_grn_gvn.is_grn=1  AND t_grn_gvn.del_status=0),0) AS  t_grn_qty,\n" + 
				"\n" + 
				"COALESCE((SELECT SUM(t_grn_gvn.taxable_amt) FROM t_grn_gvn WHERE t_grn_gvn.grn_gvn_date BETWEEN :fromDate AND :toDate AND   m_item.id=t_grn_gvn.item_id AND t_grn_gvn.is_credit_note=1 AND t_grn_gvn.is_grn=1  AND t_grn_gvn.del_status=0),0) AS  t_grn_taxable_amt,\n" + 
				"\n" + 
				"COALESCE((SELECT SUM(t_grn_gvn.grn_gvn_qty) FROM t_grn_gvn WHERE t_grn_gvn.grn_gvn_date BETWEEN :fromDate AND :toDate AND   m_item.id=t_grn_gvn.item_id AND t_grn_gvn.is_credit_note=1 AND t_grn_gvn.is_grn=0  AND t_grn_gvn.del_status=0),0) AS  t_gvn_qty,\n" + 
				"\n" + 
				"COALESCE((SELECT SUM(t_grn_gvn.taxable_amt) FROM t_grn_gvn WHERE t_grn_gvn.grn_gvn_date BETWEEN :fromDate AND :toDate AND   m_item.id=t_grn_gvn.item_id AND t_grn_gvn.is_credit_note=1 AND t_grn_gvn.is_grn=0  AND t_grn_gvn.del_status=0),0) AS  t_gvn_taxable_amt\n" + 
				"\n" + 
				"from m_item,m_category where m_item.item_grp1=m_category.cat_id group by m_item.id order by m_category.cat_id",nativeQuery=true)
			
			List<SalesReportRoyalty> getSaleReportRoyaltyAllFr(@Param("fromDate") String fromDate,@Param("toDate") String toDate);
*/
		
	//report no 10 multiple fr and multiple category
	@Query(value="  SELECT m_item.id,m_item.item_name,m_category.cat_name,m_category.cat_id, " + 
			"		COALESCE((SELECT SUM(t_bill_detail.bill_qty) FROM t_bill_detail,t_bill_header WHERE t_bill_header.bill_date " + 
			"			BETWEEN :fromDate AND :toDate AND t_bill_header.bill_no=t_bill_detail.bill_no AND m_item.id=t_bill_detail.item_id AND " + 
			"			t_bill_header.fr_id IN(:frIdList) AND t_bill_detail.cat_id IN(:catIdList) AND t_bill_header.del_status=0),0) AS t_bill_qty," + 
			"			COALESCE((SELECT SUM(t_bill_detail.taxable_amt) FROM t_bill_detail,t_bill_header WHERE t_bill_header.bill_date " + 
			"			BETWEEN :fromDate AND :toDate AND t_bill_header.bill_no=t_bill_detail.bill_no AND m_item.id=t_bill_detail.item_id " + 
			"			AND t_bill_header.fr_id IN(:frIdList) AND t_bill_detail.cat_id IN(:catIdList) AND t_bill_header.del_status=0),0) AS  t_bill_taxable_amt," + 
			"			COALESCE((SELECT SUM(t_credit_note_details.grn_gvn_qty) FROM t_credit_note_details,t_credit_note_header WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=1 AND t_credit_note_details.cat_id IN(:catIdList) AND  t_credit_note_details.del_status=0 AND t_credit_note_header.fr_id IN(:frIdList)),0) AS  t_grn_qty," + 
			"			COALESCE((SELECT SUM(t_credit_note_details.taxable_amt) FROM t_credit_note_details,t_credit_note_header WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=1 AND t_credit_note_details.cat_id IN(:catIdList) AND  t_credit_note_details.del_status=0 AND t_credit_note_header.fr_id IN(:frIdList)),0) AS  t_grn_taxable_amt," + 
			"			COALESCE((SELECT SUM(t_credit_note_details.grn_gvn_qty) FROM t_credit_note_details,t_credit_note_header WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=0 AND t_credit_note_details.cat_id IN(:catIdList) AND  t_credit_note_details.del_status=0 AND t_credit_note_header.fr_id IN(:frIdList)),0) AS  t_gvn_qty," + 
			"			COALESCE((SELECT SUM(t_credit_note_details.taxable_amt) FROM t_credit_note_details,t_credit_note_header WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=0 AND t_credit_note_details.cat_id IN(:catIdList) AND  t_credit_note_details.del_status=0 AND t_credit_note_header.fr_id IN(:frIdList)),0) AS  t_gvn_taxable_amt " + 
			"			FROM m_item,m_category where m_item.item_grp1=m_category.cat_id AND m_category.cat_id IN(:catIdList) group by m_item.id order by m_category.cat_id,m_item.id " + 
			"		",nativeQuery=true)
		
		List<SalesReportRoyalty> getSaleReportRoyConsoByCat(@Param("frIdList") List<String> frIdList,@Param("catIdList") List<String> catIdList,@Param("fromDate") String fromDate,@Param("toDate") String toDate);

	//report no 10 all fr and multiple category
	@Query(value=" SELECT m_item.id,m_item.item_name,m_category.cat_name,m_category.cat_id, \n" + 
			"		COALESCE((SELECT SUM(t_bill_detail.bill_qty) FROM t_bill_detail,t_bill_header,m_franchisee WHERE t_bill_header.bill_date\n" + 
			"			BETWEEN :fromDate AND :toDate AND t_bill_header.bill_no=t_bill_detail.bill_no AND m_item.id=t_bill_detail.item_id AND \n" + 
			"			t_bill_header.fr_id =m_franchisee.fr_id AND t_bill_detail.cat_id IN(:catIdList) AND t_bill_header.del_status=0),0) AS t_bill_qty,\n" + 
			"			COALESCE((SELECT SUM(t_bill_detail.taxable_amt) FROM t_bill_detail,t_bill_header,m_franchisee WHERE t_bill_header.bill_date \n" + 
			"			BETWEEN :fromDate AND :toDate AND t_bill_header.bill_no=t_bill_detail.bill_no AND m_item.id=t_bill_detail.item_id \n" + 
			"			AND t_bill_header.fr_id  =m_franchisee.fr_id AND t_bill_detail.cat_id IN(:catIdList) AND t_bill_header.del_status=0),0) AS  t_bill_taxable_amt,\n" + 
			"			COALESCE((SELECT SUM(t_credit_note_details.grn_gvn_qty) FROM t_credit_note_details,t_credit_note_header,m_franchisee WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=1 AND t_credit_note_details.cat_id IN(:catIdList) AND  t_credit_note_details.del_status=0 AND t_credit_note_header.fr_id = m_franchisee.fr_id),0) AS  t_grn_qty,\n" + 
			"			COALESCE((SELECT SUM(t_credit_note_details.taxable_amt) FROM t_credit_note_details,t_credit_note_header,m_franchisee WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=1 AND t_credit_note_details.cat_id IN(:catIdList) AND  t_credit_note_details.del_status=0 AND t_credit_note_header.fr_id = m_franchisee.fr_id),0) AS  t_grn_taxable_amt,\n" + 
			"			COALESCE((SELECT SUM(t_credit_note_details.grn_gvn_qty) FROM t_credit_note_details,t_credit_note_header,m_franchisee WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=0 AND t_credit_note_details.cat_id IN(:catIdList) AND  t_credit_note_details.del_status=0 AND t_credit_note_header.fr_id = m_franchisee.fr_id),0) AS  t_gvn_qty,\n" + 
			"			COALESCE((SELECT SUM(t_credit_note_details.taxable_amt) FROM t_credit_note_details,t_credit_note_header,m_franchisee WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=0 AND t_credit_note_details.cat_id IN(:catIdList) AND  t_credit_note_details.del_status=0 AND t_credit_note_header.fr_id = m_franchisee.fr_id),0) AS  t_gvn_taxable_amt\n" + 
			"			FROM m_item,m_category where m_item.item_grp1=m_category.cat_id AND m_category.cat_id IN(:catIdList) group by m_item.id order by m_category.cat_id,m_item.id\n" + 
			"		",nativeQuery=true)
		
		List<SalesReportRoyalty> getSaleReportRoyConsoByCatAllFr(@Param("catIdList") List<String> catIdList,@Param("fromDate") String fromDate,@Param("toDate") String toDate);

	
	
	//Only For Graph report no 10 :
	@Query(value=" SELECT m_item.id,m_item.item_name,m_category.cat_name,m_category.cat_id, \n" + 
			"\n" + 
			"COALESCE((SELECT SUM(t_bill_detail.bill_qty) FROM t_bill_detail,t_bill_header WHERE t_bill_header.bill_date \n" + 
			"BETWEEN :fromDate AND :toDate AND t_bill_header.bill_no=t_bill_detail.bill_no AND m_item.id=t_bill_detail.item_id AND \n" + 
			"t_bill_header.fr_id IN(:frIdList) AND t_bill_detail.cat_id IN(:catIdList) AND t_bill_header.del_status=0),0) AS t_bill_qty,\n" + 
			"			\n" + 
			"COALESCE((SELECT SUM(t_bill_detail.taxable_amt) FROM t_bill_detail,t_bill_header WHERE t_bill_header.bill_date \n" + 
			"BETWEEN :fromDate AND :toDate AND t_bill_header.bill_no=t_bill_detail.bill_no AND m_item.id=t_bill_detail.item_id \n" + 
			"AND t_bill_header.fr_id IN(:frIdList) AND t_bill_detail.cat_id IN(:catIdList) AND t_bill_header.del_status=0),0) AS  t_bill_taxable_amt,\n" + 
			"		\n" + 
			"			COALESCE((SELECT SUM(t_credit_note_details.grn_gvn_qty) FROM t_credit_note_details,t_credit_note_header WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=1 AND t_credit_note_details.cat_id IN(:catIdList) AND  t_credit_note_details.del_status=0 AND t_credit_note_header.fr_id IN(:frIdList)),0) AS  t_grn_qty," + 

"	COALESCE((SELECT SUM(t_credit_note_details.taxable_amt) FROM t_credit_note_details,t_credit_note_header WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=1 AND t_credit_note_details.cat_id IN(:catIdList) AND  t_credit_note_details.del_status=0 AND t_credit_note_header.fr_id IN(:frIdList)),0) AS  t_grn_taxable_amt," + 
"			COALESCE((SELECT SUM(t_credit_note_details.grn_gvn_qty) FROM t_credit_note_details,t_credit_note_header WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=0 AND t_credit_note_details.cat_id IN(:catIdList) AND  t_credit_note_details.del_status=0 AND t_credit_note_header.fr_id IN(:frIdList)),0) AS  t_gvn_qty," + 
"			COALESCE((SELECT SUM(t_credit_note_details.taxable_amt) FROM t_credit_note_details,t_credit_note_header WHERE t_credit_note_header.crn_date  BETWEEN :fromDate AND :toDate AND t_credit_note_header.crn_id=t_credit_note_details.crn_id AND m_item.id=t_credit_note_details.item_id  AND t_credit_note_header.is_grn=0 AND t_credit_note_details.cat_id IN(:catIdList) AND  t_credit_note_details.del_status=0 AND t_credit_note_header.fr_id IN(:frIdList)),0) AS  t_gvn_taxable_amt " +
			
			"FROM m_item,m_category where m_item.item_grp1=m_category.cat_id AND m_category.cat_id IN(:catIdList) group by m_category.cat_id order by m_category.cat_id,m_item.id",nativeQuery=true)
		
		List<SalesReportRoyalty> getSaleReportRoyConsoByCatForGraph(@Param("frIdList") List<String> frIdList,@Param("catIdList") List<String> catIdList,@Param("fromDate") String fromDate,@Param("toDate") String toDate);

	
}
