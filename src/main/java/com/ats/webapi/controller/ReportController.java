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
import com.ats.webapi.model.ItemReport;
import com.ats.webapi.model.ItemReportDetail;
import com.ats.webapi.report.model.CrNoteRegItem;
import com.ats.webapi.report.model.CrNoteRegSp;
import com.ats.webapi.report.model.CrNoteRegisterList;
import com.ats.webapi.report.repo.CrNoteRegItemRepo;
import com.ats.webapi.report.repo.CrNoteRegSpRepo;
import com.ats.webapi.repository.CustListRepo;
import com.ats.webapi.repository.HSNWiseReportRepo;
import com.ats.webapi.repository.ItemReportDetailRepo;
import com.ats.webapi.repository.ItemReportRepo;

@RestController
public class ReportController {

	@Autowired
	HSNWiseReportRepo hSNWiseReportRepo;

	@Autowired
	CustListRepo custListRepo;

	@Autowired
	ItemReportRepo itemReportRepo;

	@Autowired
	ItemReportDetailRepo itemReportDetailRepo;

	@Autowired
	CrNoteRegSpRepo getCrNoteRegSpRepo;
	@Autowired
	CrNoteRegItemRepo getCrNoteRegItemRepo;

	@RequestMapping(value = { "/getItemDetailReport" }, method = RequestMethod.POST)
	public @ResponseBody List<ItemReportDetail> getItemDetailReport(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("itemId") int itemId, @RequestParam("frId") int frId) {

		List<ItemReportDetail> saleList = new ArrayList<>();
		try {
			if (frId == -1) {

				saleList = itemReportDetailRepo.getItemReport(fromDate, toDate, itemId);
			} else {

				saleList = itemReportDetailRepo.getItemReportByItemId(fromDate, toDate, itemId, frId);
				System.out.println("saleListsaleListsaleList" + saleList.toString());
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return saleList;
	}

	@RequestMapping(value = { "/getItemReport" }, method = RequestMethod.POST)
	public @ResponseBody List<ItemReport> getItemReport(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("frId") int frId) {

		List<ItemReport> saleList = new ArrayList<>();
		try {

			if (frId == -1) {

				saleList = itemReportRepo.getItemReport(fromDate, toDate);
			} else {

				saleList = itemReportRepo.getItemReportByFrId(fromDate, toDate, frId);
				System.out.println(saleList.toString());
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return saleList;
	}

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

	@RequestMapping(value = { "/getCrNoteRegisterDone" }, method = RequestMethod.POST)
	public @ResponseBody CrNoteRegisterList getCrNoteRegisterDone(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {

		CrNoteRegisterList crNoteList = new CrNoteRegisterList();

		List<CrNoteRegItem> crNoteRegItemList;
		List<CrNoteRegSp> crNoteRegSpList = new ArrayList<>();

		crNoteRegItemList = getCrNoteRegItemRepo.getCrNoteRegItemDone(fromDate, toDate);
		crNoteList.setCrNoteRegItemList(crNoteRegItemList);

		crNoteRegSpList = getCrNoteRegSpRepo.getCrNoteRegSpDone(fromDate, toDate);
		crNoteList.setCrNoteRegSpList(crNoteRegSpList);

		System.err.println("size Item  crNoteList " + crNoteList.getCrNoteRegItemList().size());
		System.err.println("size Sp  crNoteList " + crNoteList.getCrNoteRegSpList());

		return crNoteList;
	}

}
