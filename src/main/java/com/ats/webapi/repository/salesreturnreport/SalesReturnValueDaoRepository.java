package com.ats.webapi.repository.salesreturnreport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.salesvaluereport.SalesReturnValueDao;

public interface SalesReturnValueDaoRepository extends JpaRepository<SalesReturnValueDao, Integer>{

//	@Query(value="select CONCAT(:month,\"\",sub_cat_id) as id,sub_cat_id,coalesce((SELECT  SUM(t_bill_detail.grand_total) as grand_total   FROM    t_bill_detail,t_bill_header,m_item    WHERE   DATE_FORMAT(t_bill_header.bill_date,'%Y-%m')=:month AND t_bill_header.del_status=0 And t_bill_header.bill_no=t_bill_detail.bill_no and t_bill_detail.cat_id!=5 and m_item.item_grp2=m_cat_sub.sub_cat_id and m_item.id=t_bill_detail.item_id),0) as grand_total,\n" + 
//			"\n" + 
//			"coalesce((select SUM(grn_gvn_amt) as grn_qty from t_credit_note_header,t_credit_note_details,m_item where t_credit_note_header.crn_id=t_credit_note_details.crn_id and t_credit_note_header.is_grn=1 and DATE_FORMAT(t_credit_note_header.crn_date,'%Y-%m')=:month and t_credit_note_details.cat_id!=5 and m_item.id=t_credit_note_details.item_id and m_item.item_grp2=m_cat_sub.sub_cat_id),0) as grn_qty,\n" + 
//			"\n" + 
//			"coalesce((select SUM(grn_gvn_amt) as gvn_qty from t_credit_note_header,t_credit_note_details,m_item where t_credit_note_header.crn_id=t_credit_note_details.crn_id  and DATE_FORMAT(t_credit_note_header.crn_date,'%Y-%m')=:month and t_credit_note_header.is_grn=0 and t_credit_note_details.cat_id!=5 and m_item.id=t_credit_note_details.item_id and m_item.item_grp2=m_cat_sub.sub_cat_id),0) as gvn_qty\n" + 
//			"\n" + 
//			"from m_cat_sub where m_cat_sub.cat_id!=5 and m_cat_sub.del_status=0\n" + 
//			"\n" + 
//			"UNION ALL\n" + 
//			"select  CONCAT(:month,\"\",sub_cat_id) as id,m_cat_sub.sub_cat_id,coalesce((SELECT\n" + 
//			"        SUM(t_bill_detail.grand_total) as bill_qty\n" + 
//			"          FROM\n" + 
//			"        t_bill_detail,t_bill_header,m_sp_cake\n" + 
//			"    WHERE\n" + 
//			"        DATE_FORMAT(t_bill_header.bill_date,'%Y-%m')=:month\n" + 
//			"        AND t_bill_header.del_status=0 And t_bill_header.bill_no=t_bill_detail.bill_no and t_bill_detail.cat_id=5 and  m_sp_cake.sp_id=t_bill_detail.item_id),0) as bill_qty,\n" + 
//			"        0 as grn_qty,\n" + 
//			"        coalesce((select SUM(grn_gvn_amt) as gvn_qty from t_credit_note_header,t_credit_note_details,m_sp_cake where t_credit_note_header.crn_id=t_credit_note_details.crn_id and t_credit_note_header.is_grn=0 and DATE_FORMAT(t_credit_note_header.crn_date,'%Y-%m')=:month and t_credit_note_details.cat_id=5 and m_sp_cake.sp_id=t_credit_note_details.item_id),0) as gvn_qty\n" + 
//			"        from m_cat_sub where m_cat_sub.cat_id=5 and m_cat_sub.del_status=0",nativeQuery=true)
//	List<SalesReturnValueDao> getSalesReturnValueReport(@Param("month")String month);
	
	
	@Query(value="SELECT\n" + 
			"    CONCAT(:month, t1.sub_cat_id) AS id,\n" + 
			"    t1.sub_cat_id,\n" + 
			"    COALESCE(t2.grand_total, 0) AS grand_total,\n" + 
			"    COALESCE(t3.grn_qty, 0) AS grn_qty,\n" + 
			"    COALESCE(t4.gvn_qty, 0) AS gvn_qty\n" + 
			"FROM\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        *\n" + 
			"    FROM\n" + 
			"        m_cat_sub s\n" + 
			"    WHERE\n" + 
			"        s.del_status = 0\n" + 
			") t1\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        m_item.item_grp2 AS sub_cat_id,\n" + 
			"        SUM(t_bill_detail.grand_total) AS grand_total\n" + 
			"    FROM\n" + 
			"        t_bill_detail,\n" + 
			"        t_bill_header,\n" + 
			"        m_item\n" + 
			"    WHERE\n" + 
			"        DATE_FORMAT(t_bill_header.bill_date, '%Y-%m') = :month AND t_bill_header.del_status = 0 AND t_bill_header.bill_no = t_bill_detail.bill_no AND t_bill_detail.cat_id != 5 AND m_item.id = t_bill_detail.item_id\n" + 
			"    GROUP BY\n" + 
			"        m_item.item_grp2\n" + 
			"    UNION\n" + 
			"SELECT\n" + 
			"    21 AS sub_cat_id,\n" + 
			"    SUM(t_bill_detail.grand_total) AS grand_total\n" + 
			"FROM\n" + 
			"    t_bill_detail,\n" + 
			"    t_bill_header,\n" + 
			"    m_sp_cake\n" + 
			"WHERE\n" + 
			"    DATE_FORMAT(t_bill_header.bill_date, '%Y-%m') = :month AND t_bill_header.del_status = 0 AND t_bill_header.bill_no = t_bill_detail.bill_no AND t_bill_detail.cat_id = 5 AND m_sp_cake.sp_id = t_bill_detail.item_id\n" + 
			") t2\n" + 
			"ON\n" + 
			"    t1.sub_cat_id = t2.sub_cat_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        m_item.item_grp2 AS sub_cat_id,\n" + 
			"        SUM(grn_gvn_amt) AS grn_qty\n" + 
			"    FROM\n" + 
			"        t_credit_note_header,\n" + 
			"        t_credit_note_details,\n" + 
			"        m_item\n" + 
			"    WHERE\n" + 
			"        t_credit_note_header.crn_id = t_credit_note_details.crn_id AND t_credit_note_header.is_grn = 1 AND DATE_FORMAT(\n" + 
			"            t_credit_note_header.crn_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) = :month AND t_credit_note_details.cat_id != 5 AND m_item.id = t_credit_note_details.item_id\n" + 
			"    GROUP BY\n" + 
			"        m_item.item_grp2\n" + 
			"    UNION\n" + 
			"SELECT\n" + 
			"    21 AS sub_cat_id,\n" + 
			"    SUM(grn_gvn_amt) AS grn_qty\n" + 
			"FROM\n" + 
			"    t_credit_note_header,\n" + 
			"    t_credit_note_details,\n" + 
			"    m_sp_cake\n" + 
			"WHERE\n" + 
			"    t_credit_note_header.crn_id = t_credit_note_details.crn_id AND t_credit_note_header.is_grn = 1 AND DATE_FORMAT(\n" + 
			"        t_credit_note_header.crn_date,\n" + 
			"        '%Y-%m'\n" + 
			"    ) = :month AND t_credit_note_details.cat_id = 5 AND m_sp_cake.sp_id = t_credit_note_details.item_id\n" + 
			") t3\n" + 
			"ON\n" + 
			"    t1.sub_cat_id = t3.sub_cat_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        m_item.item_grp2 AS sub_cat_id,\n" + 
			"        SUM(grn_gvn_amt) AS gvn_qty\n" + 
			"    FROM\n" + 
			"        t_credit_note_header,\n" + 
			"        t_credit_note_details,\n" + 
			"        m_item\n" + 
			"    WHERE\n" + 
			"        t_credit_note_header.crn_id = t_credit_note_details.crn_id AND t_credit_note_header.is_grn = 0 AND DATE_FORMAT(\n" + 
			"            t_credit_note_header.crn_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) = :month AND t_credit_note_details.cat_id != 5 AND m_item.id = t_credit_note_details.item_id\n" + 
			"    GROUP BY\n" + 
			"        m_item.item_grp2\n" + 
			"    UNION\n" + 
			"SELECT\n" + 
			"    21 AS sub_cat_id,\n" + 
			"    SUM(grn_gvn_amt) AS gvn_qty\n" + 
			"FROM\n" + 
			"    t_credit_note_header,\n" + 
			"    t_credit_note_details,\n" + 
			"    m_sp_cake\n" + 
			"WHERE\n" + 
			"    t_credit_note_header.crn_id = t_credit_note_details.crn_id AND t_credit_note_header.is_grn = 0 AND DATE_FORMAT(\n" + 
			"        t_credit_note_header.crn_date,\n" + 
			"        '%Y-%m'\n" + 
			"    ) = :month AND t_credit_note_details.cat_id = 5 AND m_sp_cake.sp_id = t_credit_note_details.item_id\n" + 
			") t4\n" + 
			"ON\n" + 
			"    t1.sub_cat_id = t4.sub_cat_id",nativeQuery=true)
	List<SalesReturnValueDao> getSalesReturnValueReport(@Param("month")String month);

}
