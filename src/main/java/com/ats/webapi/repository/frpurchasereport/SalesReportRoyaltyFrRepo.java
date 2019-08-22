package com.ats.webapi.repository.frpurchasereport;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ats.webapi.model.report.frpurchase.SalesReportRoyaltyFr;
//SalesFReport no 6
public interface SalesReportRoyaltyFrRepo extends JpaRepository<SalesReportRoyaltyFr, Integer> {
	
	/*@Query(value=" SELECT m_franchisee.fr_id,m_franchisee.fr_code, m_franchisee.fr_name,m_franchisee.fr_city," + 
			"        COALESCE((SELECT SUM(t_bill_detail.taxable_amt)" + 
			"        FROM" + 
			"            t_bill_detail,t_bill_header " + 
			"        WHERE" + 
			"            t_bill_header.bill_date BETWEEN :fromDate AND :toDate " + 
			"            AND t_bill_header.bill_no=t_bill_detail.bill_no " + 
			"            AND t_bill_header.fr_id IN(:frIdList) " + 
			"            AND t_bill_header.del_status=0)," + 
			"        0) AS  t_bill_taxable_amt," + 
			"        COALESCE((SELECT " + 
			"            SUM(t_grn_gvn.taxable_amt)" + 
			"        FROM" + 
			"            t_grn_gvn " + 
			"        WHERE" + 
			"            t_grn_gvn.grn_gvn_date BETWEEN :fromDate AND :toDate " + 
			"            AND t_grn_gvn.is_credit_note=1 " + 
			"            AND t_grn_gvn.is_grn=1 " + 
			"            AND t_grn_gvn.fr_id IN(:frIdList) " + 
			"            AND t_grn_gvn.del_status=0)," + 
			"        0) AS  t_grn_taxable_amt," + 
			"        " + 
			"        COALESCE((SELECT" + 
			"            SUM(t_grn_gvn.taxable_amt)" + 
			"        FROM" + 
			"            t_grn_gvn " + 
			"        WHERE" + 
			"            t_grn_gvn.grn_gvn_date BETWEEN :fromDate AND :toDate " + 
			"            AND t_grn_gvn.is_credit_note=1" + 
			"            AND t_grn_gvn.is_grn=0 " + 
			"            AND t_grn_gvn.fr_id IN(:frIdList)" + 
			"            AND t_grn_gvn.del_status=0)," + 
			"        0) AS  t_gvn_taxable_amt " + 
			"    from" + 
			"        m_franchisee" + 
		
			"    group by" + 
			"        m_franchisee.fr_id" 
			,nativeQuery=true)
		
		List<SalesReportRoyaltyFr> getSaleReportRoyaltyFr(@Param("frIdList") List<String> frIdList,@Param("fromDate") String fromDate,@Param("toDate") String toDate);
*/
	
	//report 6 
	
	
	/* paste this 
	sELECT
        m_franchisee.fr_id,
        m_franchisee.fr_code,
        m_franchisee.fr_name,
        m_franchisee.fr_city,
        COALESCE((SELECT
            SUM(t_bill_detail.taxable_amt)                 
        FROM
            t_bill_detail,
            t_bill_header                  
        WHERE
            t_bill_header.bill_date BETWEEN '2018-04-01' AND '2018-04-10'                       
            AND t_bill_header.bill_no=t_bill_detail.bill_no                          

            AND t_bill_header.fr_id=m_franchisee.fr_id                           
            AND t_bill_header.del_status=0),
        0) AS  t_bill_taxable_amt,
        COALESCE((SELECT
            SUM(t_credit_note_details.taxable_amt)                 
        FROM
            t_credit_note_details,t_credit_note_header                  
        WHERE
            t_credit_note_header.crn_date BETWEEN '2018-04-01' AND '2018-04-10'                         
                         
            AND t_credit_note_header.is_grn=1                          
                                
            AND t_credit_note_header.fr_id=m_franchisee.fr_id  
AND           t_credit_note_header.crn_id= t_credit_note_details.crn_id         
            ),
        0) AS  t_grn_taxable_amt,
        COALESCE((SELECT
            SUM(t_credit_note_details.taxable_amt)                 
        FROM
            t_credit_note_details,t_credit_note_header                  
        WHERE
            t_credit_note_header.crn_date BETWEEN '2018-04-01' AND '2018-04-10'                         
                         
            AND t_credit_note_header.is_grn=0                        
                                
            AND t_credit_note_header.fr_id=m_franchisee.fr_id  
AND           t_credit_note_header.crn_id= t_credit_note_details.crn_id         
            ),
        0) AS  t_gvn_taxable_amt     
    from
        m_franchisee
     
    where
        m_franchisee.fr_id IN(
            17
        )         
    group by
        m_franchisee.fr_id         
    order by
        m_franchisee.fr_id
	 */
	@Query(value="\n" + 
			"         SELECT\n" + 
			"        m_franchisee.fr_id,\n" + 
			"        m_franchisee.fr_code,\n" + 
			"        m_franchisee.fr_name,\n" + 
			"        m_franchisee.fr_city,\n" + 
			"        COALESCE((SELECT\n" + 
			"         SUM(t_bill_detail.taxable_amt)                              \n" + 
			"        FROM\n" + 
			"        t_bill_header,t_bill_detail                                           \n" + 
			"        WHERE\n" + 
			"            t_bill_header.bill_date BETWEEN :fromDate AND :toDate                                        \n" + 
			"            AND t_bill_header.del_status=0 and t_bill_detail.bill_no=t_bill_header.bill_no and t_bill_detail.cat_id NOT IN(3,7)" + 
			"            AND m_franchisee.fr_id=t_bill_header.fr_id ),\n" + 
			"        0) AS  t_bill_taxable_amt,\n" + 
			"        COALESCE((SELECT\n" + 
			"            SUM(t_grn_gvn.apr_taxable_amt)                           \n" + 
			"        FROM\n" + 
			"            t_grn_gvn                              \n" + 
			"        WHERE\n" + 
			"            t_grn_gvn.grn_gvn_date BETWEEN :fromDate AND :toDate                                      \n" + 
			"            AND t_grn_gvn.is_grn=1 and t_grn_gvn.cat_id NOT IN(3,7)" + 
			"            AND  t_grn_gvn.grn_gvn_status=6                              \n" + 
			"            AND m_franchisee.fr_id=t_grn_gvn.fr_id                             ),\n" + 
			"        0) AS  t_grn_taxable_amt,\n" + 
			"        COALESCE((SELECT\n" + 
			"            SUM(t_grn_gvn.apr_taxable_amt)                           \n" + 
			"        FROM\n" + 
			"            t_grn_gvn                              \n" + 
			"        WHERE\n" + 
			"            t_grn_gvn.grn_gvn_date BETWEEN :fromDate AND :toDate                                       \n" + 
			"            AND t_grn_gvn.is_grn IN (0,2) and t_grn_gvn.cat_id NOT IN(3,7)" + 
			"            AND  t_grn_gvn.grn_gvn_status=6                              \n" + 
			"            AND m_franchisee.fr_id=t_grn_gvn.fr_id                             ),\n" + 
			"        0) AS   t_gvn_taxable_amt                 \n" + 
			"    from\n" + 
			"        m_franchisee              \n" + 
			"    where\n" + 
			"        m_franchisee.fr_id IN(:frIdList)                   \n" + 
			"    order by\n" + 
			"        m_franchisee.fr_id" 
			,nativeQuery=true)
		
		List<SalesReportRoyaltyFr> getSaleReportRoyaltyFr(@Param("frIdList") List<String> frIdList,@Param("fromDate") String fromDate,@Param("toDate") String toDate);
	
	
	
	//report 6 All fr sel
		@Query(value=" SELECT\n" + 
				"        m_franchisee.fr_id,\n" + 
				"        m_franchisee.fr_code,\n" + 
				"        m_franchisee.fr_name,\n" + 
				"        m_franchisee.fr_city,\n" + 
				"        COALESCE((SELECT\n" + 
				"            SUM(t_bill_detail.taxable_amt)                             \n" + 
				"        FROM\n" + 
				"            t_bill_header,t_bill_detail                           \n" + 
				"        WHERE\n" + 
				"            t_bill_header.bill_date BETWEEN :fromDate AND :toDate                               \n" + 
				"            AND t_bill_header.del_status=0 and t_bill_detail.bill_no=t_bill_header.bill_no and t_bill_detail.cat_id NOT IN(3,7)\n" + 
				"            AND m_franchisee.fr_id=t_bill_header.fr_id ),\n" + 
				"        0) AS  t_bill_taxable_amt,\n" + 
				"        COALESCE((SELECT\n" + 
				"            SUM(t_grn_gvn.apr_taxable_amt)                           \n" + 
				"        FROM\n" + 
				"            t_grn_gvn                              \n" + 
				"        WHERE\n" + 
				"            t_grn_gvn.grn_gvn_date BETWEEN :fromDate AND :toDate                                \n" + 
				"            AND t_grn_gvn.is_grn=1 and t_grn_gvn.cat_id NOT IN(3,7)" + 
				"            AND  t_grn_gvn.grn_gvn_status=6                              \n" + 
				"            AND m_franchisee.fr_id=t_grn_gvn.fr_id                             ),\n" + 
				"        0) AS  t_grn_taxable_amt,\n" + 
				"        COALESCE((SELECT\n" + 
				"            SUM(t_grn_gvn.apr_taxable_amt)                           \n" + 
				"        FROM\n" + 
				"            t_grn_gvn                              \n" + 
				"        WHERE\n" + 
				"            t_grn_gvn.grn_gvn_date BETWEEN :fromDate AND :toDate                                  \n" + 
				"            AND t_grn_gvn.is_grn IN (0,2) and t_grn_gvn.cat_id Not IN(3,7)\n" + 
				"            AND  t_grn_gvn.grn_gvn_status=6                              \n" + 
				"            AND m_franchisee.fr_id=t_grn_gvn.fr_id                             ),\n" + 
				"        0) AS   t_gvn_taxable_amt                 \n" + 
				"    from\n" + 
				"        m_franchisee                                                    \n" + 
				"    order by\n" + 
				"        m_franchisee.fr_id" 
				,nativeQuery=true)
			
			List<SalesReportRoyaltyFr> getSaleReportRoyaltyFrAllFrSel(@Param("fromDate") String fromDate,@Param("toDate") String toDate);


		@Query(value="\n" + 
				"         SELECT\n" + 
				"        m_franchisee.fr_id,\n" + 
				"        m_franchisee.fr_code,\n" + 
				"        m_franchisee.fr_name,\n" + 
				"        m_franchisee.fr_city,\n" + 
				"        COALESCE((SELECT\n" + 
				"         SUM(t_bill_detail.taxable_amt)                              \n" + 
				"        FROM\n" + 
				"        t_bill_header,t_bill_detail                                           \n" + 
				"        WHERE\n" + 
				"            t_bill_header.bill_date BETWEEN :fromDate AND :toDate                                        \n" + 
				"            AND t_bill_header.del_status=0 and t_bill_detail.bill_no=t_bill_header.bill_no and t_bill_detail.cat_id NOT IN(3,7)" + 
				"            AND m_franchisee.fr_id=t_bill_header.fr_id ),\n" + 
				"        0) AS  t_bill_taxable_amt,\n" + 
				"        COALESCE((SELECT\n" + 
				"            SUM(t_credit_note_details.taxable_amt)                           \n" + 
				"        FROM\n" + 
				"            t_credit_note_details,t_credit_note_header                              \n" + 
				"        WHERE\n" + 
				"            t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate And t_credit_note_header.crn_id=t_credit_note_details.crn_id                                     \n" + 
				"            AND t_credit_note_details.is_grn=1 and t_credit_note_details.cat_id NOT IN(3,7)" + 
				"            AND m_franchisee.fr_id=t_credit_note_header.fr_id),\n" + 
				"        0) AS  t_grn_taxable_amt,\n" + 
				"        COALESCE((SELECT\n" + 
				"            SUM(t_credit_note_details.taxable_amt)                           \n" + 
				"        FROM\n" + 
				"             t_credit_note_details,t_credit_note_header                             \n" + 
				"        WHERE\n" + 
				"            t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate     And t_credit_note_header.crn_id=t_credit_note_details.crn_id                                    \n" + 
				"            AND t_credit_note_details.is_grn IN (0,2) and t_credit_note_details.cat_id NOT IN(3,7)" + 
				"            AND m_franchisee.fr_id=t_credit_note_header.fr_id                             ),\n" + 
				"        0) AS   t_gvn_taxable_amt                 \n" + 
				"    from\n" + 
				"        m_franchisee              \n" + 
				"    where\n" + 
				"        m_franchisee.fr_id IN(:frIdList)                   \n" + 
				"    order by\n" + 
				"        m_franchisee.fr_id" 
				,nativeQuery=true)
			
		List<SalesReportRoyaltyFr> getSaleReportRoyaltyFrCrn(@Param("frIdList") List<String> frIdList,@Param("fromDate") String fromDate,@Param("toDate") String toDate);


		@Query(value=" SELECT\n" + 
				"        m_franchisee.fr_id,\n" + 
				"        m_franchisee.fr_code,\n" + 
				"        m_franchisee.fr_name,\n" + 
				"        m_franchisee.fr_city,\n" + 
				"        COALESCE((SELECT\n" + 
				"            SUM(t_bill_detail.taxable_amt)                             \n" + 
				"        FROM\n" + 
				"            t_bill_header,t_bill_detail                           \n" + 
				"        WHERE\n" + 
				"            t_bill_header.bill_date BETWEEN :fromDate AND :toDate                               \n" + 
				"            AND t_bill_header.del_status=0 and t_bill_detail.bill_no=t_bill_header.bill_no and t_bill_detail.cat_id NOT IN(3,7)\n" + 
				"            AND m_franchisee.fr_id=t_bill_header.fr_id ),\n" + 
				"        0) AS  t_bill_taxable_amt,\n" + 
				"        COALESCE((SELECT\n" + 
				"            SUM(t_credit_note_details.taxable_amt)                           \n" + 
				"        FROM\n" + 
				"           t_credit_note_details,t_credit_note_header                                         \n" + 
				"        WHERE\n" + 
				"            t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate        And t_credit_note_header.crn_id=t_credit_note_details.crn_id                         \n" + 
				"            AND t_credit_note_details.is_grn=1 and t_credit_note_details.cat_id NOT IN(3,7)" + 
				"            AND m_franchisee.fr_id=t_credit_note_header.fr_id                             ),\n" + 
				"        0) AS  t_grn_taxable_amt,\n" + 
				"        COALESCE((SELECT\n" + 
				"            SUM(t_credit_note_details.taxable_amt)                           \n" + 
				"        FROM\n" + 
				"             t_credit_note_details,t_credit_note_header                                       \n" + 
				"        WHERE\n" + 
				"            t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate     And t_credit_note_header.crn_id=t_credit_note_details.crn_id                                \n" + 
				"            AND t_credit_note_details.is_grn IN (0,2) and t_credit_note_details.cat_id Not IN(3,7)\n" + 
				"            AND m_franchisee.fr_id=t_credit_note_header.fr_id                             ),\n" + 
				"        0) AS   t_gvn_taxable_amt                 \n" + 
				"    from\n" + 
				"        m_franchisee                                                    \n" + 
				"    order by\n" + 
				"        m_franchisee.fr_id" 
				,nativeQuery=true)
		List<SalesReportRoyaltyFr> getSaleReportRoyaltyFrAllFrSelCrn(@Param("fromDate") String fromDate,@Param("toDate") String toDate);

	
	
	
	
/* 7 No Query to be resolved 
 * SELECT t_bill_header.bill_date,t_bill_detail.bill_detail_no,t_bill_detail.item_id,m_franchisee.fr_name,m_franchisee.fr_city,
m_franchisee.fr_gst_no,m_item_sup.item_hsncd,m_item.item_tax1,m_item.item_tax2,m_item.item_tax3,sum(t_bill_detail.taxable_amt),
sum(t_bill_detail.sgst_rs)as sgst_sum,sum(t_bill_detail.cgst_rs)as cgst_sum,sum(t_bill_detail.igst_rs)as igst_sum
from m_item,m_franchisee,m_item_sup,t_bill_detail,t_bill_header WHERE t_bill_header.bill_no=t_bill_detail.bill_no and m_item.id=m_item_sup.item_id
 */
	
}
