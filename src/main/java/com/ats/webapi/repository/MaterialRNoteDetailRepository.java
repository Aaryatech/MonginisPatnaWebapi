package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.MaterialRecNoteDetails;

@Repository
public interface MaterialRNoteDetailRepository extends JpaRepository<MaterialRecNoteDetails, Integer>{

	MaterialRecNoteDetails save(MaterialRecNoteDetails materialRecNoteDetails);

	@Query(value=" select * from t_material_receipt_note_detail where mrn_id=:mrnId AND del_status=0",nativeQuery=true)
	List<MaterialRecNoteDetails> getByMrnId(@Param("mrnId")int mrnId);

	
}
