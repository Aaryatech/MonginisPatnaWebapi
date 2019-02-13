package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.bill.GetBillDetailPrint;

public interface BillDetailForPrintRepo extends JpaRepository<GetBillDetailPrint, Integer>{
	

	@Query(value="SELECT\n" + 
			"        t_bill_detail.bill_detail_no,\n" + 
			"        t_bill_detail.bill_no,\n" + 
			"        t_bill_detail.order_id,\n" + 
			"        t_bill_detail.menu_id,"
			+ "      t_bill_header.bill_date, m_category.cat_name,\n" + 
			"        t_bill_detail.cat_id,\n" + 
			"        t_bill_detail.item_id,\n" + 
			"        t_bill_detail.order_qty,\n" + 
			"        t_bill_detail.bill_qty,\n" + 
			"        t_bill_detail.mrp,\n" + 
			"        t_bill_detail.rate_type,\n" + 
			"        t_bill_detail.rate,\n" + 
			"        t_bill_detail.base_rate,\n" + 
			"        t_bill_detail.taxable_amt,\n" + 
			"        t_bill_detail.sgst_per,\n" + 
			"        t_bill_detail.sgst_rs,\n" + 
			"        t_bill_detail.cgst_per,\n" + 
			"        t_bill_detail.cgst_rs,\n" + 
			"        t_bill_detail.igst_per,\n" + 
			"        t_bill_detail.igst_rs,\n" + 
			"        t_bill_detail.total_tax,\n" + 
			"        t_bill_detail.grand_total,\n" + 
			"        t_bill_detail.del_status,\n" + 
			"        t_bill_detail.grn_type,\n" + 
			"        t_bill_detail.expiry_date,\n" + 
			"        t_bill_detail.is_grngvn_applied,\n" + 
			"        t_bill_detail.disc_per,\n" + 
			"        t_bill_detail.cess_per,\n" + 
			"        t_bill_detail.cess_rs,\n" + 
			"        (CASE  \n" + 
			"            WHEN t_bill_detail.cat_id=5 THEN (SELECT\n" + 
			"                CONCAT(m_sp_cake.sp_name,\n" + 
			"                '-' ,\n" + 
			"                m_sp_cake.sp_code)  \n" + 
			"            FROM\n" + 
			"                m_sp_cake \n" + 
			"            WHERE\n" + 
			"                m_sp_cake.sp_id= t_bill_detail.item_id) \n" + 
			"            ELSE (SELECT\n" + 
			"                m_item.item_name \n" + 
			"            FROM\n" + 
			"                m_item \n" + 
			"            WHERE\n" + 
			"                t_bill_detail.item_id=m_item.id)\n" + 
			"        END) AS item_name,\n" + 
			"        (CASE \n" + 
			"            WHEN t_bill_detail.cat_id=5 THEN (SELECT\n" + 
			"                m_spcake_sup.sp_hsncd   \n" + 
			"            FROM\n" + 
			"                m_spcake_sup \n" + 
			"            WHERE\n" + 
			"                m_spcake_sup.sp_id=t_bill_detail.item_id) \n" + 
			"            ELSE (SELECT\n" + 
			"                m_item_sup.item_hsncd        \n" + 
			"            FROM\n" + 
			"                m_item_sup \n" + 
			"            WHERE\n" + 
			"                m_item_sup.item_id=t_bill_detail.item_id)                \n" + 
			"        END) AS item_hsncd,\n" + 
			"        (CASE                    \n" + 
			"            WHEN t_bill_detail.cat_id=5 THEN (SELECT\n" + 
			"                m_spcake_sup.sp_uom  \n" + 
			"            FROM\n" + 
			"                m_spcake_sup         \n" + 
			"            WHERE\n" + 
			"                m_spcake_sup.sp_id=t_bill_detail.item_id)                   \n" + 
			"            ELSE (SELECT\n" + 
			"                m_item_sup.item_uom \n" + 
			"            FROM\n" + 
			"                m_item_sup         \n" + 
			"            WHERE\n" + 
			"                m_item_sup.item_id=t_bill_detail.item_id)                \n" + 
			"        END) AS item_uom,\n" + 
			"        (CASE  \n" + 
			"            WHEN t_bill_detail.cat_id=5 THEN (0)                   \n" + 
			"            ELSE (SELECT\n" + 
			"                m_item.item_grp2 \n" + 
			"            FROM\n" + 
			"                m_item         \n" + 
			"            WHERE\n" + 
			"                m_item.id=t_bill_detail.item_id)                \n" + 
			"        END) AS sub_cat_id,\n" + 
			"        (CASE  \n" + 
			"            WHEN t_bill_detail.cat_id=5 THEN (concat(\n" + 
			"            t_bill_detail.remark,'  (',(select\n" + 
			"                t_sp_cake.sp_selected_weight \n" + 
			"            from\n" + 
			"                t_sp_cake \n" + 
			"            where\n" + 
			"                t_sp_cake.sp_order_no=t_bill_detail.order_id),\n" + 
			"            ' kg ',\n" + 
			"            ')') )                   \n" + 
			"            ELSE (0)                \n" + 
			"        END) AS remark         \n" + 
			"    FROM\n" + 
			"        t_bill_detail,\n" + 
			"        m_category,\n" + 
			"        t_bill_header               \n" + 
			"    WHERE\n" + 
			"        t_bill_detail.bill_no IN (\n" + 
			"            :billNoList \n" + 
			"        )                \n" + 
			"        AND m_category.cat_id=t_bill_detail.cat_id                \n" + 
			"        AND t_bill_detail.bill_qty>0 \n" + 
			"        and t_bill_detail.bill_no=t_bill_header.bill_no  \n" + 
			"    order by\n" + 
			"        m_category.cat_id,\n" + 
			"        sub_cat_id,\n" + 
			"        item_name",nativeQuery=true)
	
	List<GetBillDetailPrint> getBillDetailsForPrint(@Param("billNoList") List<String> billNoList);
	
}