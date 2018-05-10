package com.ats.webapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.commons.Common;
import com.ats.webapi.model.report.frpurchase.SalesReportBillwise;
import com.ats.webapi.model.report.frpurchase.SalesReportBillwiseAllFr;
import com.ats.webapi.model.report.frpurchase.SalesReportItemwise;
import com.ats.webapi.model.report.frpurchase.SalesReportRoyalty;
import com.ats.webapi.model.report.frpurchase.SalesReportRoyaltyFr;
import com.ats.webapi.repository.frpurchasereport.SaleReportBillwiseAllFrRepo;
import com.ats.webapi.repository.frpurchasereport.SaleReportBillwiseRepo;
import com.ats.webapi.repository.frpurchasereport.SaleReportItemwiseRepo;
import com.ats.webapi.repository.frpurchasereport.SalesReportRoyaltyFrRepo;
import com.ats.webapi.repository.frpurchasereport.SalesReportRoyaltyRepo;

@RestController
public class SalesReportController {
	
	@Autowired
	SaleReportBillwiseRepo saleReportBillwiseRepo;
	
	@Autowired
	SalesReportRoyaltyRepo  salesReportRoyaltyRepo;
	
	@Autowired
	SalesReportRoyaltyFrRepo salesReportRoyaltyFrRepo;
	
	@Autowired
	SaleReportBillwiseAllFrRepo saleReportBillwiseAllFrRepo;//report 7
	
	@Autowired
	SaleReportItemwiseRepo saleReportItemwiseRepo; //report 8
	
	//Report 1 sales report bill wise order by date
	@RequestMapping(value = { "/getSaleReportBillwise" }, method = RequestMethod.POST)
	public @ResponseBody List<SalesReportBillwise> getSaleReportBillwise(@RequestParam("frIdList") List<String> frIdList,@RequestParam("fromDate")
	String fromDate,
			@RequestParam("toDate") String toDate) {

		List<SalesReportBillwise> salesReportBillwiseList = null;
		try {
			fromDate = Common.convertToYMD(fromDate);
			toDate = Common.convertToYMD(toDate);
			System.out.println("Input received "+fromDate+""+toDate+""+frIdList);
			salesReportBillwiseList = saleReportBillwiseRepo.getSaleReportBillwise(frIdList, fromDate, toDate);
			System.out.println("getSaleReportBillwise"+salesReportBillwiseList.toString());

		} catch (Exception e) {
			System.out.println(" Exce in sale Report Billwise  " + e.getMessage());
			e.printStackTrace();
		}
		return salesReportBillwiseList;
	}
	
	//Report 1 sales report bill wise order by date All Fr
		@RequestMapping(value = { "/getSaleReportBillwiseAllFrSelected" }, method = RequestMethod.POST)
		public @ResponseBody List<SalesReportBillwise> getSaleReportBillwiseAllFrSelected(@RequestParam("fromDate")
		String fromDate,
				@RequestParam("toDate") String toDate) {

			List<SalesReportBillwise> salesReportBillwiseList = null;
			try {
				fromDate = Common.convertToYMD(fromDate);
				toDate = Common.convertToYMD(toDate);
				System.out.println("Input received "+fromDate+""+toDate+"");
				salesReportBillwiseList = saleReportBillwiseRepo.getSaleReportBillwiseAllFr(fromDate, toDate);
				System.out.println("getSaleReportBillwise"+salesReportBillwiseList.toString());

			} catch (Exception e) {
				System.out.println(" Exce in sale Report Billwise  " + e.getMessage());
				e.printStackTrace();
			}
			return salesReportBillwiseList;
		}
		

	
	//report 2 sales report Summary Group By Party ie Fr Name
	@RequestMapping(value = { "/getSaleReportBillwiseByFr" }, method = RequestMethod.POST)
	public @ResponseBody List<SalesReportBillwise> getSaleReportBillwiseByFr(@RequestParam("frIdList") List<String> frIdList,@RequestParam("fromDate")
	String fromDate,
			@RequestParam("toDate") String toDate) {

		List<SalesReportBillwise> salesReportBillwiseList = null;
		try {
			fromDate = Common.convertToYMD(fromDate);
			toDate = Common.convertToYMD(toDate);
			System.out.println("Input received " +fromDate+ "" +toDate+ "" +frIdList);

			salesReportBillwiseList = saleReportBillwiseRepo.getSaleReportBillwiseByFr(frIdList, fromDate, toDate);
			System.out.println("getSaleReportBillwiseByFr"+salesReportBillwiseList.toString());
		} catch (Exception e) {
			System.out.println(" Exce in sale Report Billwise  by Fr " + e.getMessage());
			e.printStackTrace();
		}
		return salesReportBillwiseList;
	}

	//report 2 sales report Summary Group By Party ie Fr Name All Fr Selected
	@RequestMapping(value = { "/getSaleReportBillwiseByFrAllFr" }, method = RequestMethod.POST)
	public @ResponseBody List<SalesReportBillwise> getSaleReportBillwiseByFrAllFrSel(@RequestParam("fromDate")
	String fromDate,
			@RequestParam("toDate") String toDate) {

		List<SalesReportBillwise> salesReportBillwiseList = null;
		try {
			fromDate = Common.convertToYMD(fromDate);
			toDate = Common.convertToYMD(toDate);
			System.out.println("Input received " +fromDate+ "" +toDate+ "");

			salesReportBillwiseList = saleReportBillwiseRepo.getSaleReportBillwiseByFrAllFr(fromDate, toDate);
			System.out.println("getSaleReportBillwiseByFr"+salesReportBillwiseList.toString());
		} catch (Exception e) {
			System.out.println(" Exce in sale Report Billwise  by Fr " + e.getMessage());
			e.printStackTrace();
		}
		return salesReportBillwiseList;
	}

	//report 3 sales report datewise group by date
	@RequestMapping(value = { "/getSaleReportBillwiseByDate" }, method = RequestMethod.POST)
	public @ResponseBody List<SalesReportBillwise> getSaleReportBillwiseByDate(@RequestParam("frIdList") List<String> frIdList,@RequestParam("fromDate")
	String fromDate,
			@RequestParam("toDate") String toDate) {

		List<SalesReportBillwise> salesReportBillwiseList = null;
		try {
			fromDate = Common.convertToYMD(fromDate);
			toDate = Common.convertToYMD(toDate);
			System.out.println("Input received " +fromDate+ "" +toDate+ "" +frIdList);

			salesReportBillwiseList = saleReportBillwiseRepo.getSaleReportBillwiseByDate(frIdList, fromDate, toDate);
			System.out.println("getSaleReportBillwiseByDate"+salesReportBillwiseList.toString());
		} catch (Exception e) {
			System.out.println(" Exce in sale Report Billwise by Date " + e.getMessage());
			e.printStackTrace();
		}
		return salesReportBillwiseList;
	}
	

	//report 3 sales report datewise group by date   all Fr Se
		@RequestMapping(value = { "/getSaleReportBillwiseByDateAllFr" }, method = RequestMethod.POST)
		public @ResponseBody List<SalesReportBillwise> getSaleReportBillwiseByDate(@RequestParam("fromDate")
		String fromDate,
				@RequestParam("toDate") String toDate) {

			List<SalesReportBillwise> salesReportBillwiseList = null;
			try {
				fromDate = Common.convertToYMD(fromDate);
				toDate = Common.convertToYMD(toDate);
				System.out.println("Input received " +fromDate+ "" +toDate+ "");

				salesReportBillwiseList = saleReportBillwiseRepo.getSaleReportBillwiseByDateAllFr(fromDate, toDate);
				System.out.println("getSaleReportBillwiseByDate"+salesReportBillwiseList.toString());
			} catch (Exception e) {
				System.out.println(" Exce in sale Report Billwise by Date " + e.getMessage());
				e.printStackTrace();
			}
			return salesReportBillwiseList;
		}
		

	//report 4
	@RequestMapping(value = { "/getSaleReportBillwiseByMonth" }, method = RequestMethod.POST)
	public @ResponseBody List<SalesReportBillwise> getSaleReportBillwiseByMonth(@RequestParam("frIdList") List<String> frIdList,@RequestParam("fromDate")
	String fromDate,
			@RequestParam("toDate") String toDate) {

		List<SalesReportBillwise> salesReportBillwiseList = null;
		try {
			fromDate = Common.convertToYMD(fromDate);
			toDate = Common.convertToYMD(toDate);
			System.out.println("Input received " +fromDate+ "" +toDate+ "" +frIdList);

			salesReportBillwiseList = saleReportBillwiseRepo.getSaleReportBillwiseByMonth(frIdList, fromDate, toDate);
			System.out.println("getSaleReportBillwiseByMonth "+salesReportBillwiseList.toString());
		} catch (Exception e) {
			System.out.println(" Exce in sale Report Billwise by Month " + e.getMessage());
 			e.printStackTrace();
		}
		return salesReportBillwiseList;
	}

	//report 4 all Fr selected
	@RequestMapping(value = { "/getSaleReportBillwiseByMonthAllFr" }, method = RequestMethod.POST)
	public @ResponseBody List<SalesReportBillwise> getSaleReportBillwiseByMonthAllFrSel(@RequestParam("fromDate")
	String fromDate,
			@RequestParam("toDate") String toDate) {

		List<SalesReportBillwise> salesReportBillwiseList = null;
		try {
			fromDate = Common.convertToYMD(fromDate);
			toDate = Common.convertToYMD(toDate);
			System.out.println("Input received " +fromDate+ "" +toDate+ "");

			salesReportBillwiseList = saleReportBillwiseRepo.getSaleReportBillwiseByMonthAllFr(fromDate, toDate);
			System.out.println("getSaleReportBillwiseByMonth "+salesReportBillwiseList.toString());
		} catch (Exception e) {
			System.out.println(" Exce in sale Report Billwise by Month " + e.getMessage());
			e.printStackTrace();
		}
		return salesReportBillwiseList;
	}

	
	
	
	// Royalty report Started
	//Report 5
	@RequestMapping(value = { "/getSalesReportRoyalty" }, method = RequestMethod.POST)
	public @ResponseBody List<SalesReportRoyalty> getSalesReportRoyalty(@RequestParam("frIdList") List<String> frIdList,@RequestParam("fromDate")
	String fromDate,
			@RequestParam("toDate") String toDate) {

		List<SalesReportRoyalty> salesReportRoyaltyList = null;
		try {
			fromDate = Common.convertToYMD(fromDate);
			toDate = Common.convertToYMD(toDate);
			System.out.println("Input received "+fromDate+""+toDate+""+frIdList);
			salesReportRoyaltyList = salesReportRoyaltyRepo.getSaleReportRoyalty(frIdList, fromDate, toDate);
			System.out.println("getSaleReportBillwise"+salesReportRoyaltyList.toString());

		} catch (Exception e) {
			System.out.println(" Exce in sales Report Royalty  " + e.getMessage());
			e.printStackTrace();
		}
		return salesReportRoyaltyList;
	}
	
	//Report 5 all Fr Selected//Ok tested
		@RequestMapping(value = { "/getSalesReportRoyaltyAllFr" }, method = RequestMethod.POST)
		public @ResponseBody List<SalesReportRoyalty> getSalesReportRoyaltyAllFrSelc(@RequestParam("fromDate")
		String fromDate,@RequestParam("toDate") String toDate) {

			List<SalesReportRoyalty> salesReportRoyaltyList = null;
			try {
				fromDate = Common.convertToYMD(fromDate);
				toDate = Common.convertToYMD(toDate);
				System.out.println("Input received "+fromDate+""+toDate);
				salesReportRoyaltyList = salesReportRoyaltyRepo.getSaleReportRoyaltyAllFr(fromDate, toDate);
				System.out.println("getSaleReportBillwise"+salesReportRoyaltyList.toString());

			} catch (Exception e) {
				System.out.println(" Exce in sales Report Royalty all fr " + e.getMessage());
				e.printStackTrace();
			}
			return salesReportRoyaltyList;
		}
	
	
	//report no 6 
	@RequestMapping(value = { "/getSalesReportRoyaltyFr" }, method = RequestMethod.POST)
	public @ResponseBody List<SalesReportRoyaltyFr> getSalesReportRoyaltyFr(@RequestParam("frIdList") List<String> frIdList,@RequestParam("fromDate")
	String fromDate,
			@RequestParam("toDate") String toDate) {

		List<SalesReportRoyaltyFr> salesReportRoyaltyFrList = null;
		try {
			fromDate = Common.convertToYMD(fromDate);
			toDate = Common.convertToYMD(toDate);
			System.out.println("Input received "+fromDate+""+toDate+""+frIdList);
			salesReportRoyaltyFrList = salesReportRoyaltyFrRepo.getSaleReportRoyaltyFr(frIdList, fromDate, toDate);
			System.out.println("sale sReportRoyalty Fr List"+salesReportRoyaltyFrList.toString());

		} catch (Exception e) {
			System.out.println(" Exce in sales Report Royalty Fr  " + e.getMessage());
			e.printStackTrace();
		}
		return salesReportRoyaltyFrList;
	}
	
	//report no 6 All Fr //Ok tested
		@RequestMapping(value = { "/getSalesReportRoyaltyFrAllFr" }, method = RequestMethod.POST)
		public @ResponseBody List<SalesReportRoyaltyFr> getSalesReportRoyaltyFrAllFr(@RequestParam("fromDate")
		String fromDate,
				@RequestParam("toDate") String toDate) {

			System.err.println("in method getSalesReportRoyaltyFrAllFr salesReportController");
			List<SalesReportRoyaltyFr> salesReportRoyaltyFrList = null;
			try {
				fromDate = Common.convertToYMD(fromDate);
				toDate = Common.convertToYMD(toDate);
				System.out.println("Input received "+fromDate+""+toDate);
				salesReportRoyaltyFrList = salesReportRoyaltyFrRepo.getSaleReportRoyaltyFrAllFrSel(fromDate, toDate);
				System.out.println("sale getSalesReportRoyaltyFrAllFr Fr List"+salesReportRoyaltyFrList.toString());

			} catch (Exception e) {
				System.out.println(" Exce in sales Report Royalty Fr all fr sel /getSalesReportRoyaltyFrAllFr " + e.getMessage());
				e.printStackTrace();
			}
			return salesReportRoyaltyFrList;
		}
	
	
	//report 7 by default All Fr report
	
	@RequestMapping(value = { "/getSaleReportBillwiseAllFr" }, method = RequestMethod.POST)
	public @ResponseBody List<SalesReportBillwiseAllFr> getSaleReportBillwiseAllFr(@RequestParam("fromDate")
	String fromDate,
			@RequestParam("toDate") String toDate) {

		List<SalesReportBillwiseAllFr> salesReportBillwiseAllFr = null;
		try {
			fromDate = Common.convertToYMD(fromDate);
			toDate = Common.convertToYMD(toDate);
			System.out.println("Input received "+fromDate+""+toDate);
			salesReportBillwiseAllFr = saleReportBillwiseAllFrRepo.getSaleReportBillwiseAllFr(fromDate, toDate);
			System.out.println("getSaleReportBillwiseAllFr " +salesReportBillwiseAllFr.toString());

		} catch (Exception e) {
			System.out.println(" Exce in sale Report Billwise all fr  " + e.getMessage());
			e.printStackTrace();
		}
		return salesReportBillwiseAllFr;
	}
	
	@RequestMapping(value = { "/getSaleReportItemwise" }, method = RequestMethod.POST)
	public @ResponseBody List<SalesReportItemwise> getSaleReportItemwise(@RequestParam("fromDate")
	String fromDate,
			@RequestParam("toDate") String toDate) {

		List<SalesReportItemwise> salesReportItemwise = null;
		try {
			fromDate = Common.convertToYMD(fromDate);
			toDate = Common.convertToYMD(toDate);
			System.out.println("Input received "+fromDate+""+toDate);
			salesReportItemwise = saleReportItemwiseRepo.getSaleReportItemwise(fromDate, toDate);
			System.out.println("salesReportItemwise " +salesReportItemwise.toString());

		} catch (Exception e) {
			System.out.println(" Exce in sale Report Billwisesales Report Itemwise " + e.getMessage());
			e.printStackTrace();
		}
		return salesReportItemwise;
	}
	
	//report 10 AS OF REPORT 5
	@RequestMapping(value = { "/getSaleReportRoyConsoByCat" }, method = RequestMethod.POST)
	public @ResponseBody List<SalesReportRoyalty> getSaleReportRoyConsoByCat(@RequestParam("frIdList") List<String> frIdList,@RequestParam("catIdList") List<String> catIdList,@RequestParam("fromDate")
	String fromDate,
			@RequestParam("toDate") String toDate) {

		List<SalesReportRoyalty> salesReportRoyaltyList = null;
		try {
			fromDate = Common.convertToYMD(fromDate);
			toDate = Common.convertToYMD(toDate);
			
			System.out.println("Input received for report 10 roy by category few fr Selected "+fromDate+""+toDate+""+frIdList+"cat="+catIdList);
			
if(catIdList.contains("0")) {
				
				System.err.println("Cat ID List contains zero ");
				catIdList.clear();
				
				//String s="1"+","+"2"+","+"3"+","+ "4";
				for(int i=1;i<=4;i++) {
					String s=new String();
					
					s=i+",";
					
					catIdList.add(s);

					
				}
				
				System.err.println("New cat ID List" +catIdList );
}
			salesReportRoyaltyList = salesReportRoyaltyRepo.getSaleReportRoyConsoByCat(frIdList, catIdList, fromDate, toDate);
			System.out.println("getSaleReportBillwise"+salesReportRoyaltyList.toString());

		} catch (Exception e) {
			System.out.println(" Exce in sales Report Royalty  By Category " + e.getMessage());
			e.printStackTrace();
		}
		return salesReportRoyaltyList;
	}
	
	
	//report no 10 all fr and multiple category

	@RequestMapping(value = { "/getSaleReportRoyConsoByCatAllFr" }, method = RequestMethod.POST)
	public @ResponseBody List<SalesReportRoyalty> getSaleReportRoyConsoByCatAllFr(@RequestParam("catIdList") List<String> catIdList,@RequestParam("fromDate")
	String fromDate,
			@RequestParam("toDate") String toDate) {

		List<SalesReportRoyalty> salesReportRoyaltyList = null;
		try {
			fromDate = Common.convertToYMD(fromDate);
			toDate = Common.convertToYMD(toDate);
			System.out.println("Input received for report 10 roy by category all fr Selected "+fromDate+""+toDate+""+"cat="+catIdList);
			
if(catIdList.contains("0")) {
				
				System.err.println("Cat ID List contains zero ");
				catIdList.clear();
				
				//String s="1"+","+"2"+","+"3"+","+ "4";
				for(int i=1;i<=4;i++) {
					String s=new String();
					
					s=i+",";
					
					catIdList.add(s);

					
				}
				
				System.err.println("New cat ID List" +catIdList );
				
			}

			salesReportRoyaltyList = salesReportRoyaltyRepo.getSaleReportRoyConsoByCatAllFr(catIdList, fromDate, toDate);
			System.out.println("getSaleReportBillwise"+salesReportRoyaltyList.toString());

		} catch (Exception e) {
			System.out.println(" Exce in sales Report Royalty  By Category " + e.getMessage());
			e.printStackTrace();
		}
		return salesReportRoyaltyList;
	}
	
	
	//report 10 AS OF REPORT 5 for Graph
		@RequestMapping(value = { "/getSaleReportRoyConsoByCatForGraph" }, method = RequestMethod.POST)
		public @ResponseBody List<SalesReportRoyalty> getSaleReportRoyConsoByCatForGraph(@RequestParam("frIdList") List<String> frIdList,@RequestParam("catIdList") List<String> catIdList,@RequestParam("fromDate")
		String fromDate,
				@RequestParam("toDate") String toDate) {

			List<SalesReportRoyalty> salesReportRoyaltyList = null;
			try {
				fromDate = Common.convertToYMD(fromDate);
				toDate = Common.convertToYMD(toDate);
				System.out.println("Input received for report 10 roy by category "+fromDate+""+toDate+""+frIdList+"cat="+catIdList);
				salesReportRoyaltyList = salesReportRoyaltyRepo.getSaleReportRoyConsoByCatForGraph(frIdList, catIdList, fromDate, toDate);
				System.out.println("getSaleReportBillwise for Graph r 10 "+salesReportRoyaltyList.toString());

			} catch (Exception e) {
				System.out.println(" Exce in sales Report Royalty  By Category For Graph  " + e.getMessage());
				e.printStackTrace();
			}
			return salesReportRoyaltyList;
		}
}
