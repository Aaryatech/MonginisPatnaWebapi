package com.ats.webapi.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.commons.Common;
import com.ats.webapi.model.BillWisePurchaseList;
import com.ats.webapi.model.BillWiseTaxReportList;
import com.ats.webapi.model.ItemWiseDetailList;
import com.ats.webapi.model.ItemWiseReportList;
import com.ats.webapi.model.MonthWiseReportList;
import com.ats.webapi.model.Orders;
import com.ats.webapi.model.POrder;
import com.ats.webapi.model.report.DispatchReport;
import com.ats.webapi.model.report.GetCustBillTax;
import com.ats.webapi.model.report.GetCustomerBill;
import com.ats.webapi.model.report.GetRepFrDatewiseSell;
import com.ats.webapi.model.report.GetRepItemwiseSell;
import com.ats.webapi.model.report.GetRepMenuwiseSell;
import com.ats.webapi.model.report.GetRepMonthwiseSell;
import com.ats.webapi.model.report.GetRepTaxSell;
import com.ats.webapi.model.report.PDispatchReport;
import com.ats.webapi.repository.DispatchOrderRepository;
import com.ats.webapi.repository.PDispatchReportRepository;
import com.ats.webapi.service.RepFrSellServise;
import com.ats.webapi.service.ReportsService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class ReportsController {

	@Autowired
	ReportsService reportsService;

	@Autowired
	RepFrSellServise repFrSellServise;

	@Autowired
	PDispatchReportRepository pDispatchReportRepository;

	@Autowired
	DispatchOrderRepository dispatchOrderRepository;

	@RequestMapping(value = { "/updateEditedQty" }, method = RequestMethod.POST)

	public @ResponseBody List<POrder> updateEditedQty(@RequestBody List<POrder> orderList)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		List<POrder> ordersList = null;
		try {
			ordersList = dispatchOrderRepository.save(orderList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordersList;
	}

	@RequestMapping(value = { "/showBillWisePurchaseReport" }, method = RequestMethod.POST)
	public @ResponseBody BillWisePurchaseList showBillWisePurchase(@RequestParam("frId") int frId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		BillWisePurchaseList billWisePurchaseList = reportsService.getBillWisePurchaseReport(frId, fromDate, toDate);

		return billWisePurchaseList;
	}

	@RequestMapping(value = { "/showItemWiseDetailsReport" }, method = RequestMethod.POST)
	public @ResponseBody ItemWiseDetailList showItemWiseDetail(@RequestParam("frId") int frId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("catId") int catId) {

		ItemWiseDetailList ItemWiseDetailList = reportsService.getItemWiseDetailReport(frId, catId, fromDate, toDate);

		return ItemWiseDetailList;
	}

	@RequestMapping(value = { "/showItemWiseReport" }, method = RequestMethod.POST)
	public @ResponseBody ItemWiseReportList showItemWiseReport(@RequestParam("frId") int frId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("catId") int catId) {

		ItemWiseReportList ItemWiseReportList = reportsService.getItemWiseReport(frId, catId, fromDate, toDate);

		return ItemWiseReportList;
	}

	@RequestMapping(value = { "/showMonthWiseReport" }, method = RequestMethod.POST)
	public @ResponseBody MonthWiseReportList showMonthWiseReport(@RequestParam("frId") int frId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		MonthWiseReportList monthWiseReportList = reportsService.getMonthWiseReport(frId, fromDate, toDate);

		return monthWiseReportList;
	}

	@RequestMapping(value = { "/showBillWiseTaxReport" }, method = RequestMethod.POST)
	public @ResponseBody BillWiseTaxReportList showBillWiseTaxReport(@RequestParam("frId") int frId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		BillWiseTaxReportList billWiseTaxReportList = reportsService.getBillWiseTaxReport(frId, fromDate, toDate);

		return billWiseTaxReportList;
	}

	// -----------------------------------------------------------------------------------
	// Sell Reports start Sac 11 May

	@RequestMapping(value = "/getRepDatewiseSell", method = RequestMethod.POST)
	public @ResponseBody List<GetRepFrDatewiseSell> getRepDatewiseSell(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("frId") List<String> frId) {
		/*
		 * fromDate = Common.convertToYMD(fromDate); toDate =
		 * Common.convertToYMD(toDate); List<GetRepFrDatewiseSell>
		 * repFrDatewiseSellList=repFrSellServise.getDatewiseSellReport(fromDate,
		 * toDate, frId); return repFrDatewiseSellList;
		 */

		List<GetRepFrDatewiseSell> tempList = null;

		fromDate = Common.convertToYMD(fromDate);
		toDate = Common.convertToYMD(toDate);
		List<GetRepFrDatewiseSell> repFrDatewiseSellList = repFrSellServise.getDatewiseSellReport(fromDate, toDate,
				frId);

		LinkedHashMap<Date, GetRepFrDatewiseSell> hashList = new LinkedHashMap<Date, GetRepFrDatewiseSell>();

		for (int i = 0; i < repFrDatewiseSellList.size(); i++) {
			float cash = 0, card = 0, other = 0;

			if (hashList.containsKey(repFrDatewiseSellList.get(i).getBillDate()) == false) {

				for (int j = 0; j < repFrDatewiseSellList.size(); j++) {

					if (repFrDatewiseSellList.get(j).getBillDate().equals(repFrDatewiseSellList.get(i).getBillDate())) {
						cash = cash + repFrDatewiseSellList.get(j).getCash();
						card = card + repFrDatewiseSellList.get(j).getCard();
						other = other + repFrDatewiseSellList.get(j).getOther();
					}
				}

				// System.err.println(getRepFrDatewiseSellResponse.get(i).getBillDate() + " cash
				// " + cash + "card "
				// + card + "other " + other);
				repFrDatewiseSellList.get(i).setCash(cash);
				repFrDatewiseSellList.get(i).setCard(card);
				repFrDatewiseSellList.get(i).setOther(other);
				hashList.put(repFrDatewiseSellList.get(i).getBillDate(), repFrDatewiseSellList.get(i));

			}
		}

		tempList = new ArrayList<GetRepFrDatewiseSell>(hashList.values());

		return tempList;
	}

	// ---------------------------------Dispatch Item
	// Report-----------------------------------------
	@RequestMapping(value = "/getDispatchItemReport", method = RequestMethod.POST)
	public @ResponseBody List<DispatchReport> getDispatchItemReport(@RequestParam("billDate") String billDate,
			@RequestParam("frId") List<String> frId, @RequestParam("categories") List<String> categories) {

		String billDateYMD = Common.convertToYMD(billDate);
		List<DispatchReport> dispatchReportList = reportsService.getDispatchItemReport(billDateYMD, frId, categories);
		return dispatchReportList;

	}

	// ---------------------------------PDispatch Item
	// Report-----------------------------------------
	@RequestMapping(value = "/getPDispatchItemReport", method = RequestMethod.POST)
	public @ResponseBody List<PDispatchReport> getPDispatchItemReport(
			@RequestParam("productionDate") String productionDate, @RequestParam("frId") List<String> frId,
			@RequestParam("categories") List<String> categories) {

		String productionDateYMD = Common.convertToYMD(productionDate);
		List<PDispatchReport> dispatchReportList = pDispatchReportRepository.getPDispatchItemReport(productionDateYMD,
				frId, categories);
		return dispatchReportList;

	}

	// sAC 11 mAY
	// ------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/getRepMonthwiseSell", method = RequestMethod.POST)
	public @ResponseBody List<GetRepMonthwiseSell> getRepMonthwiseSell(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("frId") List<String> frId) {
		/*
		 * SSD System.out.println("from" + fromDate); System.out.println("to" + toDate);
		 * 
		 * fromDate = Common.convertToYMD(fromDate); toDate =
		 * Common.convertToYMD(toDate);
		 * 
		 * System.out.println("to" + toDate); System.out.println("from" + fromDate);
		 * List<GetRepMonthwiseSell>
		 * GetRepMonthwiseSellList=repFrSellServise.getMonthwiseSellReport(fromDate,
		 * toDate, frId); return GetRepMonthwiseSellList;
		 */

		List<GetRepMonthwiseSell> tempList = null;
		System.out.println("from" + fromDate);
		System.out.println("to" + toDate);

		/*
		 * fromDate = Common.convertToYMD(fromDate); toDate =
		 * Common.convertToYMD(toDate);
		 */

		System.out.println("to" + toDate);
		System.out.println("from" + fromDate);
		List<GetRepMonthwiseSell> GetRepMonthwiseSellList = repFrSellServise.getMonthwiseSellReport(fromDate, toDate,
				frId);

		LinkedHashMap<String, GetRepMonthwiseSell> hashList = new LinkedHashMap<String, GetRepMonthwiseSell>();

		for (int i = 0; i < GetRepMonthwiseSellList.size(); i++) {
			float cash = 0, card = 0, other = 0;

			if (hashList.containsKey(GetRepMonthwiseSellList.get(i).getMonth()) == false) {

				for (int j = 0; j < GetRepMonthwiseSellList.size(); j++) {

					if (GetRepMonthwiseSellList.get(j).getMonth().equals(GetRepMonthwiseSellList.get(i).getMonth())) {
						cash = cash + GetRepMonthwiseSellList.get(j).getCash();
						card = card + GetRepMonthwiseSellList.get(j).getCard();
						other = other + GetRepMonthwiseSellList.get(j).getOther();
					}
				}

				// System.err.println(getRepFrDatewiseSellResponse.get(i).getBillDate() + " cash
				// " + cash + "card "
				// + card + "other " + other);
				GetRepMonthwiseSellList.get(i).setCash(cash);
				GetRepMonthwiseSellList.get(i).setCard(card);
				GetRepMonthwiseSellList.get(i).setOther(other);
				hashList.put(GetRepMonthwiseSellList.get(i).getMonth(), GetRepMonthwiseSellList.get(i));

			}
		}

		tempList = new ArrayList<GetRepMonthwiseSell>(hashList.values());
		return tempList;

	}

	// sAC 11 mAY
	@RequestMapping(value = "/getRepItemwiseSell", method = RequestMethod.POST)
	public @ResponseBody List<GetRepItemwiseSell> getRepItemwiseSell(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("frId") List<String> frId,
			@RequestParam("catId") List<String> catId) {

		/*
		 * fromDate = Common.convertToYMD(fromDate); toDate =
		 * Common.convertToYMD(toDate); List<GetRepItemwiseSell>
		 * getRepItemwiseSellList=repFrSellServise.getItemwiseSellReport(fromDate,
		 * toDate, frId, catId); return getRepItemwiseSellList;
		 */

		List<GetRepItemwiseSell> getRepItemwiseSellList = new ArrayList<>();

		fromDate = Common.convertToYMD(fromDate);
		toDate = Common.convertToYMD(toDate);
		if (catId.contains("5")) {
			getRepItemwiseSellList = repFrSellServise.getItemwiseSellReportForCat5(fromDate, toDate, frId);

		} else {
			getRepItemwiseSellList = repFrSellServise.getItemwiseSellReport(fromDate, toDate, frId, catId);
		}
		return getRepItemwiseSellList;
	}

	@RequestMapping(value = "/getRepMenuwiseSell", method = RequestMethod.POST)
	public @ResponseBody List<GetRepMenuwiseSell> getRepMenuwiseSell(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("frId") List<String> frId) {

		fromDate = Common.convertToYMD(fromDate);
		toDate = Common.convertToYMD(toDate);
		List<GetRepMenuwiseSell> getRepMenuwiseSellList = repFrSellServise.getMenuwiseSellReport(fromDate, toDate,
				frId);
		return getRepMenuwiseSellList;

	}

	// Sac 11 May
	@RequestMapping(value = "/getRepDateItemwiseSell", method = RequestMethod.POST)
	public @ResponseBody List<GetRepItemwiseSell> getRepDateItemwiseSell(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("frId") List<String> frId,
			@RequestParam("catId") List<String> catId) {
		/*
		 * dfdf fromDate = Common.convertToYMD(fromDate); toDate =
		 * Common.convertToYMD(toDate); List<GetRepItemwiseSell>
		 * getRepItemwiseSellList=repFrSellServise.getDateItemwiseSellReport(fromDate,
		 * toDate, frId, catId); return getRepItemwiseSellList;
		 */

		List<GetRepItemwiseSell> getRepItemwiseSellList = new ArrayList<>();

		fromDate = Common.convertToYMD(fromDate);
		toDate = Common.convertToYMD(toDate);
		if (catId.contains("5")) {
			getRepItemwiseSellList = repFrSellServise.getItemwiseSellReportForCat5(fromDate, toDate, frId);

		} else {
			getRepItemwiseSellList = repFrSellServise.getDateItemwiseSellReport(fromDate, toDate, frId, catId);
		}
		return getRepItemwiseSellList;

	}

	@RequestMapping(value = "/getRepDateCatwiseSell", method = RequestMethod.POST)
	public @ResponseBody List<GetRepItemwiseSell> getRepDateCatwiseSell(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("frId") List<String> frId,
			@RequestParam("catId") List<String> catId) {

		fromDate = Common.convertToYMD(fromDate);
		toDate = Common.convertToYMD(toDate);
		List<GetRepItemwiseSell> getRepItemwiseSellList = repFrSellServise.getDateCatwiseSellReport(fromDate, toDate,
				frId, catId);
		return getRepItemwiseSellList;

	}
	// View Sell Tax Report Sac 11May

	@RequestMapping(value = "/getRepTaxSell", method = RequestMethod.POST)
	public @ResponseBody List<GetRepTaxSell> getRepTaxSell(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("frId") List<String> frId) {

		/*
		 * fromDate = Common.convertToYMD(fromDate); toDate =
		 * Common.convertToYMD(toDate); List<GetRepTaxSell>
		 * getRepTaxSellList=repFrSellServise.getTaxSellReport(fromDate, toDate, frId);
		 * System.out.println("  List  :" +getRepTaxSellList); return getRepTaxSellList;
		 */

		List<GetRepTaxSell> tempList = null;

		fromDate = Common.convertToYMD(fromDate);
		toDate = Common.convertToYMD(toDate);
		List<GetRepTaxSell> getRepTaxSellList = repFrSellServise.getTaxSellReport(fromDate, toDate, frId);
		System.out.println("  List  :" + getRepTaxSellList);

		LinkedHashMap<Float, GetRepTaxSell> hashList = new LinkedHashMap<Float, GetRepTaxSell>();

		for (int i = 0; i < getRepTaxSellList.size(); i++) {
			float taxable = 0, igst = 0, sgst = 0, cgst = 0;

			if (hashList.containsKey(getRepTaxSellList.get(i).getTax_per()) == false) {

				for (int j = 0; j < getRepTaxSellList.size(); j++) {

					if (getRepTaxSellList.get(j).getTax_per() == (getRepTaxSellList.get(i).getTax_per())) {
						taxable = taxable + getRepTaxSellList.get(j).getTax_amount();
						igst = igst + getRepTaxSellList.get(j).getIgst();
						sgst = sgst + getRepTaxSellList.get(j).getSgst();
						cgst = cgst + getRepTaxSellList.get(j).getCgst();
					}
				}

				// System.err.println(getRepFrDatewiseSellResponse.get(i).getBillDate() + " cash
				// " + cash + "card "
				// + card + "other " + other);
				getRepTaxSellList.get(i).setTax_amount(taxable);
				getRepTaxSellList.get(i).setIgst(igst);
				getRepTaxSellList.get(i).setSgst(sgst);
				getRepTaxSellList.get(i).setCgst(cgst);
				hashList.put(getRepTaxSellList.get(i).getTax_per(), getRepTaxSellList.get(i));

			}
		}

		tempList = new ArrayList<GetRepTaxSell>(hashList.values());
		System.out.println("  List  :" + getRepTaxSellList);
		return tempList;
	}
	// View Sell Tax Date wise Report Sac 11May

	@RequestMapping(value = "/getRepDatewiseTaxSell", method = RequestMethod.POST)
	public @ResponseBody List<GetRepTaxSell> getRepDatewiseTaxSell(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("frId") List<String> frId) {

		/*
		 * fromDate = Common.convertToYMD(fromDate); toDate =
		 * Common.convertToYMD(toDate); List<GetRepTaxSell>
		 * getRepTaxSellList=repFrSellServise.getDatewiseTaxSellReport(fromDate, toDate,
		 * frId); System.out.println("  List  :" +getRepTaxSellList); return
		 * getRepTaxSellList;
		 */

		List<GetRepTaxSell> tempList = null;
		fromDate = Common.convertToYMD(fromDate);
		toDate = Common.convertToYMD(toDate);
		List<GetRepTaxSell> getRepTaxSellList = repFrSellServise.getDatewiseTaxSellReport(fromDate, toDate, frId);

		LinkedHashMap<Date, GetRepTaxSell> hashList = new LinkedHashMap<Date, GetRepTaxSell>();

		for (int i = 0; i < getRepTaxSellList.size(); i++) {
			float taxable = 0, igst = 0, sgst = 0, cgst = 0;

			if (hashList.containsKey(getRepTaxSellList.get(i).getBillDate()) == false) {

				for (int j = 0; j < getRepTaxSellList.size(); j++) {

					if (getRepTaxSellList.get(j).getBillDate().equals(getRepTaxSellList.get(i).getBillDate())) {
						taxable = taxable + getRepTaxSellList.get(j).getTax_amount();
						igst = igst + getRepTaxSellList.get(j).getIgst();
						sgst = sgst + getRepTaxSellList.get(j).getSgst();
						cgst = cgst + getRepTaxSellList.get(j).getCgst();
					}
				}

				// System.err.println(getRepFrDatewiseSellResponse.get(i).getBillDate() + " cash
				// " + cash + "card "
				// + card + "other " + other);
				getRepTaxSellList.get(i).setTax_amount(taxable);
				getRepTaxSellList.get(i).setIgst(igst);
				getRepTaxSellList.get(i).setSgst(sgst);
				getRepTaxSellList.get(i).setCgst(cgst);
				hashList.put(getRepTaxSellList.get(i).getBillDate(), getRepTaxSellList.get(i));

			}
		}

		tempList = new ArrayList<GetRepTaxSell>(hashList.values());
		System.out.println("  List  :" + getRepTaxSellList);
		return tempList;

	}

	@RequestMapping(value = "/getRepBillwiseTaxSell", method = RequestMethod.POST)
	public @ResponseBody List<GetRepTaxSell> getRepBillwiseTaxSell(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("frId") List<String> frId) {

		fromDate = Common.convertToYMD(fromDate);
		toDate = Common.convertToYMD(toDate);
		List<GetRepTaxSell> getRepTaxSellList = repFrSellServise.getBillwiseTaxSellReport(fromDate, toDate, frId);
		System.out.println("  List  :" + getRepTaxSellList);
		return getRepTaxSellList;

	}

	// Sell Report
	// End-------------------------------------------------------------------

	// customer bill-----------------------

	@RequestMapping(value = "/getCustomerBill", method = RequestMethod.POST)
	public @ResponseBody List<GetCustomerBill> getCustomerBill(@RequestParam("billNo") int billNo) {

		List<GetCustomerBill> getCustomerBillList = repFrSellServise.getCustBill(billNo);
		System.out.println("  List  :" + getCustomerBillList);
		return getCustomerBillList;

	}

	@RequestMapping(value = "/getCustomerBillTax", method = RequestMethod.POST)
	public @ResponseBody List<GetCustBillTax> getCustomerBillTax(@RequestParam("billNo") int billNo) {

		List<GetCustBillTax> getCustBillTaxList = repFrSellServise.getCustBillTax(billNo);
		System.out.println("  List  :" + getCustBillTaxList);
		return getCustBillTaxList;

	}
}
