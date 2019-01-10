package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.MixingDetailed;
import com.ats.webapi.model.bom.BillOfMaterialDetailed;

public interface BillOfMaterialDetailedRepository extends JpaRepository<BillOfMaterialDetailed, Integer>{
	
	BillOfMaterialDetailed save(BillOfMaterialDetailed billOfMaterialDetailed);
	
	@Query(value=" select * from t_req_bom_detail where req_id=:reqId",nativeQuery=true)
	List<BillOfMaterialDetailed> getBomdetailedListById(@Param("reqId")int reqId);

	@Query(value=" select * from t_req_bom_detail where req_id=:reqId and rm_type=1",nativeQuery=true)
	List<BillOfMaterialDetailed> getBomdetailedListByIdAndRmType(@Param("reqId")int reqId);

	List<BillOfMaterialDetailed> findByReqIdAndExInt1(int reqId, int i);

	List<BillOfMaterialDetailed> findByReqIdAndExInt1AndRmType(int reqId, int i, int j);
}
