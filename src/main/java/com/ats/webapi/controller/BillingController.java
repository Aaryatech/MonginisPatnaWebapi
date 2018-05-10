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

import com.ats.webapi.model.Info;
import com.ats.webapi.model.SellBillDataCommon;
import com.ats.webapi.model.SellBillDetail;
import com.ats.webapi.model.SellBillDetailList;
import com.ats.webapi.model.SellBillHeader;
import com.ats.webapi.model.bill.ExpressBillService;
import com.ats.webapi.model.bill.GetItemHsnCode;
import com.ats.webapi.model.bill.SlabwiseBill;
import com.ats.webapi.model.bill.SlabwiseBillList;
import com.ats.webapi.repository.SlabwiseDetailsRepository;
import com.ats.webapi.repository.UpdateSellBillTimeStampRepo;

@RestController
public class BillingController {

	@Autowired
	ExpressBillService expressBillService;
	
	@Autowired
	SlabwiseDetailsRepository slabwiseDetailsRepository; 
	
	@Autowired
	UpdateSellBillTimeStampRepo  updateSellBillTimeStampRepo;
	
	
	@RequestMapping(value = { "/updateSellBillTimeStamp" }, method = RequestMethod.POST)
	public @ResponseBody Info updateSellBillTimeStamp(@RequestParam("sellBillNo") int sellBillNo,
			@RequestParam("timeStamp") String timeStamp) {
		
		Info info=new Info();

		int response=updateSellBillTimeStampRepo.updateTimeForSellBill(sellBillNo, timeStamp);
		
		if(response>0) {
			info.setError(false);
			info.setMessage("successfully Updated Time Stamp /updateSellBillTimeStamp");
		}
		System.err.println("BillingController -/updateSellBillTimeStamp ->response " +response);
		    
		return info;
	  }
	
	
	@RequestMapping(value = { "/deleteExBillHeader" }, method = RequestMethod.POST)
	public @ResponseBody int deleteExPBillHeader(@RequestParam("sellBillNo") int sellBillNo) {

		int response=expressBillService.deleteSellBillHeader(sellBillNo);
		    
		return response;
	  }
	
	
	 
	@RequestMapping(value = { "/showNotDayClosedRecord" }, method = RequestMethod.POST)
	public @ResponseBody SellBillDataCommon showNotDayClosedRecord(@RequestParam("frId") int frId) {

		SellBillDataCommon dayNotCloseRecord=expressBillService.showNotDayClosedRecord(frId);
		    
			return dayNotCloseRecord;
	  }
	
	@RequestMapping(value = { "/getSellBillDetails" }, method = RequestMethod.POST)
	public @ResponseBody SellBillDetailList getSellBillDetails(@RequestParam("billNo") int billNo) {

		SellBillDetailList sellBillDetailList=expressBillService.getSellBillDetails(billNo);
		    
			return sellBillDetailList;
	  }
	@RequestMapping(value = { "/saveSellBillHeader" }, method = RequestMethod.POST)
	public @ResponseBody SellBillHeader saveSellBillHeader(@RequestBody SellBillHeader sellBillHeader) {

		SellBillHeader sellBillHeaderRes=expressBillService.saveSellBillHeader(sellBillHeader);
		    
			return sellBillHeaderRes;
	  }
	@RequestMapping(value = { "/saveSellBillDetail" }, method = RequestMethod.POST)
	public @ResponseBody SellBillDetail saveSellBillDetail(@RequestBody SellBillDetail sellBillDetail) {

		SellBillDetail SellBillDetailRes=expressBillService.saveSellBillDetail(sellBillDetail);
		    
			return SellBillDetailRes;
	  }
	
	
	@RequestMapping(value = { "/getSellBillHeaderForDayClose" }, method = RequestMethod.POST)
	public @ResponseBody SellBillHeader getSellBillHeader(@RequestParam("sellBillNo") int sellBillNo) {
		
		SellBillHeader sellBillHeader=null;
		
		try {

		 sellBillHeader=expressBillService.getSellBillHeaderBysellBillNo(sellBillNo);
		 
		}catch (Exception e) {
			
			System.out.println("Exce in getting sell Bill Header "+e.getMessage());
			e.printStackTrace();
			
		}
			return sellBillHeader;
	  }
	
	
	
	@RequestMapping(value = { "/deleteSellBillDetail" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteSellBillDetail(
			@RequestParam("sellBillDetailNo") int sellBillDetailNo) {

		int result= expressBillService.deleteBillDetail(sellBillDetailNo);
		
		Info info=new Info();
		
		if(result>0) {
			
			info.setError(false);
			info.setMessage(" Sell Bill Detail Updated Successfully");
			
		}
		else {
			info.setError(true);
			info.setMessage("Error: Sell Bill Detail update  failed");
			
			
		}
			return info;
	  }
	@RequestMapping(value = { "/getItemHsnCode" }, method = RequestMethod.POST)
	public @ResponseBody GetItemHsnCode getItemHsnCode(@RequestParam("itemId") int itemId) {
	
		GetItemHsnCode getItemHsnCode=expressBillService.getItemHsnCode(itemId);
		
		return getItemHsnCode;
	}
	@RequestMapping(value = { "/getSlabwiseBillData" }, method = RequestMethod.POST)
	public @ResponseBody List<SlabwiseBillList> getSlabwiseBillData(@RequestParam("billNoList") List<String> billNos) {
	
		List<SlabwiseBillList> slabwiseBillList=new ArrayList<SlabwiseBillList>();
		for(int i=0;i<billNos.size();i++)
		{
			System.out.println("billNo"+billNos.get(i));
			
		  SlabwiseBillList slabwiseBill=new SlabwiseBillList();
		  
		  List<SlabwiseBill> slabwiseBills=slabwiseDetailsRepository.getSlabwiseBillData(Integer.parseInt(billNos.get(i)));
		  System.out.println("slabwiseBills"+slabwiseBills.toString());
		  slabwiseBill.setBillNo(Integer.parseInt(billNos.get(i)));
		  slabwiseBill.setSlabwiseBill(slabwiseBills);
		  slabwiseBillList.add(slabwiseBill);
		  System.out.println("slabwiseBillList"+slabwiseBillList.toString());

		}
		return slabwiseBillList;
	}
	
}
