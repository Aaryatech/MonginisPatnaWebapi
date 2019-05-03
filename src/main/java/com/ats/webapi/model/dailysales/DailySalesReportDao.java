package com.ats.webapi.model.dailysales;

import java.util.List;

public class DailySalesReportDao {
	
	List<DailySalesRegular> dailySalesRegularList;

	public List<DailySalesRegular> getDailySalesRegularList() {
		return dailySalesRegularList;
	}

	public void setDailySalesRegularList(List<DailySalesRegular> dailySalesRegularList) {
		this.dailySalesRegularList = dailySalesRegularList;
	}

	@Override
	public String toString() {
		return "DailySalesReportDao [dailySalesRegularList=" + dailySalesRegularList + "]";
	}
	
	

}
