package com.ats.webapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ats.webapi.model.BillWisePurchaseList;
import com.ats.webapi.model.BillWiseTaxReportList;
import com.ats.webapi.model.ItemWiseDetailList;
import com.ats.webapi.model.ItemWiseReportList;
import com.ats.webapi.model.MonthWiseReportList;
import com.ats.webapi.model.report.DispatchReport;
@Service
public interface ReportsService {

	BillWisePurchaseList getBillWisePurchaseReport(int frId, String fromDate, String toDate);

	ItemWiseDetailList getItemWiseDetailReport(int frId,int catId, String fromDate, String toDate);

	MonthWiseReportList getMonthWiseReport(int frId, String fromDate, String toDate);

	BillWiseTaxReportList getBillWiseTaxReport(int frId, String fromDate, String toDate);

	ItemWiseReportList getItemWiseReport(int frId, int catId, String fromDate, String toDate);

	List<DispatchReport> getDispatchItemReport(String billDateYMD, List<String> frId, List<String> categories);

}
