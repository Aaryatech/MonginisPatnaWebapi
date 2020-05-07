package com.ats.webapi.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.reportv2.SalesReport;
import com.ats.webapi.repository.reportv2.SalesReportRepo;

@RestController
public class ReportControllerV2 {

	@Autowired
	SalesReportRepo getSalesReportRepo;
	
	
	@RequestMapping(value = { "/getSalesReportV2" }, method = RequestMethod.POST)
	public @ResponseBody List<SalesReport> getSalesReportV2(@RequestParam("frIdList") List<String> frIdList,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		List<SalesReport> saleList = new ArrayList<>();

		if (frIdList.contains("-1")) {

			saleList = getSalesReportRepo.getSalesReportAllFr(fromDate, toDate);

		} else {

			saleList = getSalesReportRepo.getSalesReportSpecFr(fromDate, toDate, frIdList);
		}

		return saleList;
	}
	
}
