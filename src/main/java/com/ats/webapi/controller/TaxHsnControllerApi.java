package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.GetTaxHsn;
import com.ats.webapi.model.Info;
import com.ats.webapi.model.SubCategory;
import com.ats.webapi.model.TaxHsn;
import com.ats.webapi.model.logistics.DriverMaster;
import com.ats.webapi.repository.GetTaxHsnRepo;
import com.ats.webapi.repository.SubCategoryRepository;
import com.ats.webapi.repository.TaxHsnRepo;

import javassist.compiler.SyntaxError;


@RestController
public class TaxHsnControllerApi {
	
	@Autowired TaxHsnRepo getTaxHsnRepo;
	
	@Autowired GetTaxHsnRepo getGetTaxHsnRepo;

	@Autowired SubCategoryRepository getSubCategoryRepository;
	
	
	
	//insertTaxHsn
	
	@RequestMapping(value = { "/insertTaxHsn" }, method = RequestMethod.POST)
	public @ResponseBody TaxHsn insertTaxHsn(@RequestBody TaxHsn txHsn )
	{ 
		TaxHsn getTaxHsn =new  	TaxHsn();
		try {
			  
			getTaxHsn = getTaxHsnRepo.saveAndFlush(txHsn);
			
		} catch (Exception e) {
			
			System.err.println("Excep in /insertTaxHsn " +e.getMessage());
			getTaxHsn=null;
			e.printStackTrace();
			
		}
		
		return getTaxHsn;

	}
	
	
	@RequestMapping(value = { "/deleteTaxHsn" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteTaxHsn(@RequestParam("taxHsnIdList") List<Integer> taxHsnIdList)
	{ 
		Info info =new  	Info();
		int x=0;
		try {
			  
			x = getTaxHsnRepo.deleteTaxHsn(taxHsnIdList);
			if(x>0) {
				info.setError(false);
				info.setMessage("Success");
			}
			
		} catch (Exception e) {
			
			System.err.println("Excep in /insertTaxHsn " +e.getMessage());
			info=null;
			e.printStackTrace();
			
		}
		
		return info;

	}
	
	
	@RequestMapping(value = { "/getSubCatForTaxHsn" }, method = RequestMethod.POST)
	public @ResponseBody List<SubCategory> getSubCatForTaxHsn(@RequestParam("catId") int catId)
	{ 
		List<SubCategory> subCatList = new ArrayList<SubCategory>();
		try {
			  
			subCatList = getSubCategoryRepository.getAllSubCategoriesForTaxHsn(catId);
			
		} catch (Exception e) {
			
			System.err.println("Excep in Getting sub Cat List for Tax HSn  " +e.getMessage());
			e.printStackTrace();
			
		}
		
		return subCatList;

	}
	
	
	@RequestMapping(value = { "/getAllTaxHsnList" }, method = RequestMethod.GET)
	public @ResponseBody List<GetTaxHsn> getAllTaxHsnList()
	{ 
		List<GetTaxHsn> txHsnList = new ArrayList<GetTaxHsn>();
		try {
			  
			txHsnList = getGetTaxHsnRepo.getAllGetTaxHsn();
			
		} catch (Exception e) {
			
			System.err.println("Excep in Getting getAllTaxHsnList for Tax HSn  " +e.getMessage());
			txHsnList=null;
			e.printStackTrace();
			
		}
		
		return txHsnList;

	}
	
	
	@RequestMapping(value = { "/getTaxHsnByTxHsnId" }, method = RequestMethod.POST)
	public @ResponseBody GetTaxHsn getGetTaxHsnByTxHsnId(@RequestParam("taxHsnId") int texHsnId)
	{ 
		GetTaxHsn getTaxHsn =new  GetTaxHsn();
		try {
			  
			getTaxHsn = getGetTaxHsnRepo.getGetTaxHsnByTxHsnId(texHsnId);
			
		} catch (Exception e) {
			
			System.err.println("Excep in Getting getTaxHsnByTxHsnId  " +e.getMessage());
			getTaxHsn=null;
			e.printStackTrace();
			
		}
		
		return getTaxHsn;

	}
	
	@RequestMapping(value = {"/getTaxHsnBySubCatId"}, method = RequestMethod.POST)
	public @ResponseBody GetTaxHsn getTaxHsnBySubCatId(@RequestParam("subCatId") int subCatId)
	{ 
		GetTaxHsn getTaxHsn =new  GetTaxHsn();
		try {
			  
			getTaxHsn = getGetTaxHsnRepo.getGetTaxHsnBySubCatd(subCatId);
			
		} catch (Exception e) {
			
			System.err.println("Excep in Getting sub Cat List for Tax HSn  " +e.getMessage());
			getTaxHsn=null;
			e.printStackTrace();
			
		}
		
		return getTaxHsn;

	}
	

}
