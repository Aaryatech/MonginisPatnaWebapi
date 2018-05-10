package com.ats.webapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.webapi.model.BillWisePurchase;
import com.ats.webapi.model.BillWisePurchaseList;
import com.ats.webapi.model.BillWiseTaxReport;
import com.ats.webapi.model.BillWiseTaxReportList;
import com.ats.webapi.model.ErrorMessage;
import com.ats.webapi.model.ItemWiseDetail;
import com.ats.webapi.model.ItemWiseDetailList;
import com.ats.webapi.model.ItemWiseReport;
import com.ats.webapi.model.ItemWiseReportList;
import com.ats.webapi.model.MonthWiseReport;
import com.ats.webapi.model.MonthWiseReportList;
import com.ats.webapi.model.report.DispatchReport;
import com.ats.webapi.repository.BillWisePurchaseRepository;
import com.ats.webapi.repository.BillWiseTaxReportRepository;
import com.ats.webapi.repository.DispatchReportRepository;
import com.ats.webapi.repository.ItemWiseDetailRepository;
import com.ats.webapi.repository.ItemWiseReportRepository;
import com.ats.webapi.repository.MonthWiseReportRepository;
@Service
public class ReportsServiceImpl implements ReportsService {

	@Autowired
	BillWisePurchaseRepository billWisePurchaseRepository; 
	
	@Autowired
	ItemWiseDetailRepository itemWiseDetailRepository;
	
	@Autowired
	ItemWiseReportRepository itemWiseReportRepository;
	
	@Autowired
	MonthWiseReportRepository	monthWiseReportRepository;
	
	@Autowired
	BillWiseTaxReportRepository billWiseTaxReportRepository;
	
	@Autowired
	DispatchReportRepository dispatchReportRepository;
	
	@Override
	public BillWisePurchaseList getBillWisePurchaseReport(int frId, String fromDate, String toDate) {

		List<BillWisePurchase> billWisePurchase=billWisePurchaseRepository.findBillWisePurchaseReport(frId,fromDate,toDate);
		
		BillWisePurchaseList billWisePurchaseList=new BillWisePurchaseList();
		ErrorMessage errorMessage=new ErrorMessage();
		
		if(billWisePurchase==null)
		{
			errorMessage.setError(true);
			errorMessage.setMessage("Records Not Found.");
		}
		else
		{     errorMessage.setError(false);
		      errorMessage.setMessage("Records Found Successfully.");
		      
			  billWisePurchaseList.setBillWisePurchaseList(billWisePurchase);
			  billWisePurchaseList.setErrorMessage(errorMessage);
		}
		return billWisePurchaseList;
	}

	@Override
	public ItemWiseDetailList getItemWiseDetailReport(int frId, int catId,String fromDate, String toDate) {

		System.out.println("Date: "+fromDate+"To"+toDate);
		ItemWiseDetailList itemWiseDetailList=new ItemWiseDetailList();
		if(catId==5)
		{
			List<ItemWiseDetail> itemWiseDetail=itemWiseDetailRepository.findSpecialCakeWiseDetailReport(frId,catId,fromDate,toDate);
			
			
			
			ErrorMessage errorMessage=new ErrorMessage();

			
			if(itemWiseDetail==null)
			{
				errorMessage.setError(true);
				errorMessage.setMessage("Records Not Found.");
			}
			else
			{     errorMessage.setError(false);
			      errorMessage.setMessage("Records Found Successfully.");
			      
			      itemWiseDetailList.setErrorMessage(errorMessage);
			      itemWiseDetailList.setItemWiseDetailList(itemWiseDetail);
			}
			
		}
		else
		{
		List<ItemWiseDetail> itemWiseDetail=itemWiseDetailRepository.findItemWiseDetailReport(frId,catId,fromDate,toDate);
		
		
		
		ErrorMessage errorMessage=new ErrorMessage();

		
		if(itemWiseDetail==null)
		{
			errorMessage.setError(true);
			errorMessage.setMessage("Records Not Found.");
		}
		else
		{     errorMessage.setError(false);
		      errorMessage.setMessage("Records Found Successfully.");
		      
		      itemWiseDetailList.setErrorMessage(errorMessage);
		      itemWiseDetailList.setItemWiseDetailList(itemWiseDetail);
		}
		
   	} return itemWiseDetailList;
	}
	@Override
	public MonthWiseReportList getMonthWiseReport(int frId, String fromDate, String toDate) {
		
    List<MonthWiseReport> monthWiseReport=monthWiseReportRepository.findMonthWiseReport(frId,fromDate,toDate);
		
		
    MonthWiseReportList monthWiseReportList=new MonthWiseReportList();
		ErrorMessage errorMessage=new ErrorMessage();

		
		if(monthWiseReport==null)
		{
			errorMessage.setError(true);
			errorMessage.setMessage("Records Not Found.");
		}
		else
		{     errorMessage.setError(false);
		      errorMessage.setMessage("Records Found Successfully.");
		      
		      monthWiseReportList.setErrorMessage(errorMessage);
		      monthWiseReportList.setMonthWiseReportList(monthWiseReport);
		}
		return monthWiseReportList;
	}

	@Override
	public BillWiseTaxReportList getBillWiseTaxReport(int frId, String fromDate, String toDate) {
		
       List<BillWiseTaxReport> billWiseTax=billWiseTaxReportRepository.findBillWiseTaxReport(frId,fromDate,toDate);
		
		BillWiseTaxReportList billWiseTaxReportList=new BillWiseTaxReportList();
		ErrorMessage errorMessage=new ErrorMessage();
		
		if(billWiseTax==null)
		{
			errorMessage.setError(true);
			errorMessage.setMessage("Records Not Found.");
		}
		else
		{     errorMessage.setError(false);
		      errorMessage.setMessage("Records Found Successfully.");
		      
		      billWiseTaxReportList.setBillWiseTaxReportList(billWiseTax);
		      billWiseTaxReportList.setErrorMessage(errorMessage);
		}
		return billWiseTaxReportList;
	}

	@Override
	public ItemWiseReportList getItemWiseReport(int frId, int catId, String fromDate, String toDate) {
		
		System.out.println("Date: "+fromDate+"To"+toDate);
		ItemWiseReportList itemWiseReportList=new ItemWiseReportList();
		if(catId==5)
		{
			List<ItemWiseReport> itemWiseReport=itemWiseReportRepository.findSpecialCakeWiseReport(frId,catId,fromDate,toDate);
			
			
			
			ErrorMessage errorMessage=new ErrorMessage();

			
			if(itemWiseReport==null)
			{
				errorMessage.setError(true);
				errorMessage.setMessage("Records Not Found.");
			}
			else
			{     errorMessage.setError(false);
			      errorMessage.setMessage("Records Found Successfully.");
			      
			      itemWiseReportList.setErrorMessage(errorMessage);
			      itemWiseReportList.setItemWiseReportList(itemWiseReport);
			}
			
		}
		else
		{
		List<ItemWiseReport> itemWiseReport=itemWiseReportRepository.findItemWiseReport(frId,catId,fromDate,toDate);
		
		
		
		ErrorMessage errorMessage=new ErrorMessage();

		
		if(itemWiseReport==null)
		{
			errorMessage.setError(true);
			errorMessage.setMessage("Records Not Found.");
		}
		else
		{     errorMessage.setError(false);
		      errorMessage.setMessage("Records Found Successfully.");
		      
		      itemWiseReportList.setErrorMessage(errorMessage);
		      itemWiseReportList.setItemWiseReportList(itemWiseReport);
		}
		
   	} return itemWiseReportList;
	}

	@Override
	public List<DispatchReport> getDispatchItemReport(String billDateYMD, List<String> frId, List<String> categories) {

		List<DispatchReport> dispatchReportList;
		try {
		dispatchReportList=dispatchReportRepository.findDispatchReportList(billDateYMD,frId,categories);
		
		}
		catch (Exception e) {
			dispatchReportList=new ArrayList<>();
            e.printStackTrace();
		}
		return dispatchReportList;
	}

}
