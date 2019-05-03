package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.GetCurrentStockDetails;
import com.ats.webapi.model.dailysales.DailySalesRegular;
import com.ats.webapi.model.dailysales.DailySalesReportDao;
import com.ats.webapi.repository.dailysales.DailySalesRegularReportRepository;

@RestController
public class DailySalesReportController {
	
	@Autowired
	DailySalesRegularReportRepository dailySalesRegularReportRepo;
	
	@RequestMapping(value = "/getDailySalesData", method = RequestMethod.POST)
	public @ResponseBody DailySalesReportDao getDailySalesData(@RequestParam("frId") int frId,
			@RequestParam("date") String date,@RequestParam("currentMonth") int currentMonth
			, @RequestParam("year") int year) {
		
		DailySalesReportDao dailySalesReportDaoList=new DailySalesReportDao();
		
		List<DailySalesRegular> dailySalesReport=dailySalesRegularReportRepo.getDailySalesData(frId,date,currentMonth,year);
		DailySalesRegular dailySalesOtherReport=dailySalesRegularReportRepo.getDailySalesOtherData(frId,date,currentMonth,year);
        dailySalesReport.add(dailySalesOtherReport);
		
		dailySalesReportDaoList.setDailySalesRegularList(dailySalesReport);
		return dailySalesReportDaoList;
		
	}

}
