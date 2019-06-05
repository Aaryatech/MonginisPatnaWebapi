package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.CustList;
import com.ats.webapi.model.HSNWiseReport;
import com.ats.webapi.repository.CustListRepo;
import com.ats.webapi.repository.HSNWiseReportRepo;

@RestController
public class ReportController {

	@Autowired
	HSNWiseReportRepo hSNWiseReportRepo;

	@Autowired
	CustListRepo custListRepo;

	@RequestMapping(value = { "/getHsnReport" }, method = RequestMethod.POST)
	public @ResponseBody List<HSNWiseReport> getHsnReport(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {

		List<HSNWiseReport> saleList = new ArrayList<>();
		try {

			saleList = hSNWiseReportRepo.getReportHsn(fromDate, toDate);
			System.out.println(saleList.toString());

		} catch (Exception e) {
			// TODO: handle exception
		}

		return saleList;
	}

	@RequestMapping(value = { "/getCutslListFroFranchasee" }, method = RequestMethod.POST)
	public @ResponseBody List<CustList> getCutslListFroFranchasee(@RequestParam("frId") int frId) {

		List<CustList> OpStockUpdateList = null;
		try {

			OpStockUpdateList = custListRepo.getOpStockAdjReportByfrId(frId);

		} catch (Exception e) {
			System.out.println(" Exce in Tax1 Report " + e.getMessage());
			e.printStackTrace();
		}
		return OpStockUpdateList;
	}

}
