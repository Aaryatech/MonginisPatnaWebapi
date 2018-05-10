package com.ats.webapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.webapi.model.grngvn.PostCreditNoteDetails;
import com.ats.webapi.model.grngvn.PostCreditNoteHeader;
import com.ats.webapi.repository.PostCreditNoteDetailsRepository;
import com.ats.webapi.repository.PostCreditNoteHeaderRepository;
import com.ats.webapi.repository.UpdateGrnGvnForCreditNoteRepository;
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
	UpdateGrnGvnHeaderForCNRepo updateGrnGvnHeaderForCNRepo;
	
	@Override
	public List<PostCreditNoteHeader> savePostCreditNote(List<PostCreditNoteHeader> postCreditNoteHeader) {

		PostCreditNoteHeader creditNoteHeader = null;

		List<PostCreditNoteHeader> postCreditNoteHeaderList = new ArrayList<PostCreditNoteHeader>();
		for (int i = 0; i < postCreditNoteHeader.size(); i++) {

			creditNoteHeader = new PostCreditNoteHeader();

			creditNoteHeader = postCreditNoteHeaderRepository.save(postCreditNoteHeader.get(i));

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
				
				res=updateGrnGvnHeaderForCNRepo.updateGrnGvnHeaderForCN(postCreditNoteDetails.getCrnId(), 1, postCreditNoteDetails.getGrnGvnHeaderId());

			}
		}

		return postCreditNoteHeaderList;
	}

}
