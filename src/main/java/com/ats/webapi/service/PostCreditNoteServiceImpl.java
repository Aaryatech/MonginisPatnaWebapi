package com.ats.webapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.webapi.model.grngvn.PostCreditNoteDetails;
import com.ats.webapi.model.grngvn.PostCreditNoteHeader;
import com.ats.webapi.repository.FrItemStockConfigureRepository;
import com.ats.webapi.repository.PostCreditNoteDetailsRepository;
import com.ats.webapi.repository.PostCreditNoteHeaderRepository;
import com.ats.webapi.repository.UpdateGrnGvnForCreditNoteRepository;
import com.ats.webapi.repository.UpdateSeetingForPBRepo;
import com.ats.webapi.repository.grngvnheader.UpdateGrnGvnHeaderForCNRepo;

@Service
public class PostCreditNoteServiceImpl implements PostCreditNoteService {

	@Autowired
	PostCreditNoteHeaderRepository postCreditNoteHeaderRepository;

	@Autowired
	PostCreditNoteDetailsRepository postCreditNoteDetailsRepository;
	
	@Autowired
	UpdateGrnGvnForCreditNoteRepository updateGrnGvnForCreditNoteRepository;
	
	@Autowired
	FrItemStockConfigureRepository frItemStockConfRepo;
	
	@Autowired
	UpdateGrnGvnHeaderForCNRepo updateGrnGvnHeaderForCNRepo;
	
	@Autowired//added here on 3 march
	UpdateSeetingForPBRepo updateSeetingForPBRepo;
	
	@Override
	public List<PostCreditNoteHeader> savePostCreditNote(List<PostCreditNoteHeader> postCreditNoteHeader) {

		PostCreditNoteHeader creditNoteHeader = null;

		List<PostCreditNoteHeader> postCreditNoteHeaderList = new ArrayList<PostCreditNoteHeader>();
		for (int i = 0; i < postCreditNoteHeader.size(); i++) {

			creditNoteHeader = new PostCreditNoteHeader();

			int crnSrNo=frItemStockConfRepo.findBySettingKey("CRE_NOTE_NO");
			
			postCreditNoteHeader.get(i).setCrnNo(""+crnSrNo);
			creditNoteHeader = postCreditNoteHeaderRepository.save(postCreditNoteHeader.get(i));

			if(creditNoteHeader.getCrnId()!=0) {
				/*	
					int result= updateGrnGvnForCreditNoteRepository.updateGrnGvnForCreditNoteInsert(
							creditNoteHeader.getGrnGvnId(), 1);*/
						
					System.err.println("crnSrNo  while update " +crnSrNo);
					int result = updateSeetingForPBRepo.updateSeetingForPurBill(crnSrNo+1, "CRE_NOTE_NO");
					
				}
			postCreditNoteHeaderList.add(creditNoteHeader);
			
			int res=0;
			

			int crnId = postCreditNoteHeader.get(i).getCrnId();

			List<PostCreditNoteDetails> postCreditNoteDetailsList = postCreditNoteHeader.get(i)
					.getPostCreditNoteDetails();

			for (int j = 0; j < postCreditNoteDetailsList.size(); j++) {

				PostCreditNoteDetails postCreditNoteDetails = postCreditNoteDetailsList.get(j);

				postCreditNoteDetails.setCrnId(crnId);
				
				postCreditNoteDetailsRepository.save(postCreditNoteDetails);
				
				int result= updateGrnGvnForCreditNoteRepository.updateGrnGvnForCreditNoteInsert(
						postCreditNoteDetails.getGrnGvnId(), 1);
				
				res=updateGrnGvnHeaderForCNRepo.updateGrnGvnHeaderForCN(crnSrNo, 1, postCreditNoteDetails.getGrnGvnHeaderId());

			}
		}

		return postCreditNoteHeaderList;
	}

}
