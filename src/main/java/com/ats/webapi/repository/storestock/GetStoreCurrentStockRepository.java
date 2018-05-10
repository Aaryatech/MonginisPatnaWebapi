package com.ats.webapi.repository.storestock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.stock.GetStoreCurrentStock;

public interface GetStoreCurrentStockRepository extends JpaRepository<GetStoreCurrentStock, Integer>{

	
	@Query(value=" SELECT d.store_stock_detail_id,h.store_stock_date,m_rm.rm_id, m_rm.rm_name,"
			+" coalesce((Select SUM(t_req_bom_detail.rm_issue_qty) From t_req_bom_detail,t_req_bom where"
			+" t_req_bom.approved_date=h.store_stock_date AND t_req_bom.to_dept_id=:deptId AND"
			+" t_req_bom.req_id=t_req_bom_detail.req_id AND t_req_bom.status>0 AND m_rm.rm_id=t_req_bom_detail.rm_id),0)"
			+" AS bms_issue_qty,coalesce((Select (SUM( t_material_receipt_note_detail.recd_qty)"
			+" -SUM( t_material_receipt_note_detail.rejected_qty)) From t_material_receipt_note_detail,"
			+"t_material_receipt_note where t_material_receipt_note.mrn_store_date=h.store_stock_date"
			+" AND t_material_receipt_note.mrn_id=t_material_receipt_note_detail.mrn_id AND"
			+" t_material_receipt_note.status=4 AND m_rm.rm_id=t_material_receipt_note_detail.rm_id),0)"
			+" AS pur_rec_qty,coalesce((Select t_store_stock_detail.store_opening_stock From t_store_stock_detail,"
			+" t_store_stock_header where t_store_stock_detail.store_stock_id=t_store_stock_header.store_stock_id AND"
			+" t_store_stock_header.store_stock_status=0 AND t_store_stock_detail.rm_id=m_rm.rm_id),0) AS store_opening_stock,"
			+" coalesce((Select t_store_stock_detail.store_closing_stock From t_store_stock_detail,t_store_stock_header where"
			+" t_store_stock_detail.store_stock_id=t_store_stock_header.store_stock_id AND t_store_stock_header.store_stock_status=0 AND"
			+" t_store_stock_detail.rm_id=m_rm.rm_id),0) AS store_closing_stock FROM m_rm,t_store_stock_detail d,"
			+" t_store_stock_header h where m_rm.rm_id=d.rm_id AND h.store_stock_id=d.store_stock_id AND h.store_stock_status=0"
			+" GROUP by m_rm.rm_id",nativeQuery=true)
	List<GetStoreCurrentStock> getCurrentStock(@Param("deptId")int deptId);

	 
	
}
