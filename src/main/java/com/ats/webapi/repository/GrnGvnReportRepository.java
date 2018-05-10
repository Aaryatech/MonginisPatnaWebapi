package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.GrnGvnReport; 

public interface GrnGvnReportRepository extends JpaRepository<GrnGvnReport, Integer>{
	
	@Query(value="select g.grn_gvn_id, g.grn_gvn_date, g.item_id, i.item_name, g.sgst_per+g.cgst_per as tax_rate, sum(g.taxable_amt) as taxable_amt, "
			+ "sum(g.total_tax) as total_tax, sum(g.grn_gvn_amt) as grn_gvn_amt, sum(g.apr_taxable_amt) as apr_taxable_amt, "
			+ "sum(g.apr_sgst_rs) as apr_sgst_rs, sum(g.apr_cgst_rs) as apr_cgst_rs, sum(g.apr_igst_rs) as apr_igst_rs, "
			+ "sum(g.apr_grand_total) as apr_grand_total from t_grn_gvn g, m_item i where g.grn_gvn_date between "
			+ ":fromDate and :toDate and g.item_id=i.id and g.is_grn in(:isGrn) and g.fr_id=:frId group by g.item_id ",nativeQuery=true)
	List<GrnGvnReport> grnGvnReportDateWise( @Param("frId")int frId,@Param("fromDate")String fromDate,@Param("toDate")String toDate,
			@Param("isGrn")String isGrn);

}
