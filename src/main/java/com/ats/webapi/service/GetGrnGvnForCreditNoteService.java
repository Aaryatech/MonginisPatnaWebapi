package com.ats.webapi.service;

import java.util.List;

import com.ats.webapi.model.grngvn.GetGrnGvnForCreditNoteList;

public interface GetGrnGvnForCreditNoteService {
	
	
	GetGrnGvnForCreditNoteList getGrnGvnForCreditNote(int isGrn,List<String> frList);

}
