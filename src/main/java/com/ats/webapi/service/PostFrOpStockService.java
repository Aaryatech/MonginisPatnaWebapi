package com.ats.webapi.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.FrStockBetweenMonth;
import com.ats.webapi.model.GetCurrentStockDetails;
import com.ats.webapi.model.PostFrItemStockDetail;
import com.ats.webapi.model.PostFrItemStockHeader;


public interface PostFrOpStockService {

	
	List<PostFrItemStockHeader> saveFrOpStockHeader( PostFrItemStockHeader postFrItemStockHeader);
	int updateEndMonth( PostFrItemStockHeader postFrItemStockHeader);
	PostFrItemStockHeader getFrOpStockHeader(int frId, int month, int year);
	
	PostFrItemStockHeader getRunningMonth(int frId);
	
	List<GetCurrentStockDetails> getStockBetweenMonth(int frId, int fromMonth, int toMonth, List<Integer>itemIdList);
	
	
}
