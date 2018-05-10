package com.ats.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.webapi.model.grngvn.PostCreditNoteDetails;
import com.ats.webapi.model.grngvn.PostCreditNoteHeader;

public interface PostCreditNoteHeaderRepository extends JpaRepository<PostCreditNoteHeader, Integer> {
	
	PostCreditNoteHeader save(PostCreditNoteHeader postCreditNoteHeader);
	
}
