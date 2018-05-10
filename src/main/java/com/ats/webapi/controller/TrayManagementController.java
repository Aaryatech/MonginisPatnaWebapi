package com.ats.webapi.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.commons.Common;
import com.ats.webapi.model.ErrorMessage;
import com.ats.webapi.model.Info;
import com.ats.webapi.model.TrayMgtDetailList;
import com.ats.webapi.model.tray.FrOutTrays;
import com.ats.webapi.model.tray.FranchiseInRoute;
import com.ats.webapi.model.tray.GetTrayMgtHeader;
import com.ats.webapi.model.tray.GetVehDriverMobNo;
import com.ats.webapi.model.tray.TrayMgtDetail;
import com.ats.webapi.model.tray.TrayMgtDetailBean;
import com.ats.webapi.model.tray.TrayMgtHeader;
import com.ats.webapi.repository.tray.GetVehDriverMobNoRepo;
import com.ats.webapi.repository.tray.TrayMgtHeaderRepository;
import com.ats.webapi.service.tray.TrayMgtService;

@RestController
@RequestMapping("/traymgt")
public class TrayManagementController {
	@Autowired
	TrayMgtHeaderRepository trayMgtHeaderRepository;

	@Autowired
	TrayMgtService trayMgtService;
	
	@Autowired
	GetVehDriverMobNoRepo vehMobNoRepo;//Sachin 20 MArch
	
	@RequestMapping(value = { "/getVehMobNo" }, method = RequestMethod.POST)
	public @ResponseBody List<GetVehDriverMobNo> getVehMobNo(@RequestParam("routeId") int routeId,@RequestParam("curDate")String curDate) {

		List<GetVehDriverMobNo> driverList = vehMobNoRepo.getVehicleAndMobNo(routeId, curDate);

		return driverList;

	}

	

	// ----------------------------SAVE Tray Management Header---------------------------
		@RequestMapping(value = { "/saveTrayMgtHeader" }, method = RequestMethod.POST)
		public @ResponseBody TrayMgtHeader saveTrayMgtHeader(@RequestBody TrayMgtHeader trayMgtHeader) {

			TrayMgtHeader trayMgtHeaderRes = null;
			try {
				TrayMgtHeader isHeaderAvail=null;
				try {
					System.out.println("----------------------"+trayMgtHeader.getTranDate()+"--"+new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	              isHeaderAvail = trayMgtHeaderRepository.findByTranDateAndVehIdAndDelStatus(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), trayMgtHeader.getVehId(), 0);

				}
				catch (Exception e) {
					 isHeaderAvail=null;
					e.printStackTrace();
				}
	              if (isHeaderAvail!=null) {

	  				trayMgtHeaderRes = new TrayMgtHeader();
	  				trayMgtHeaderRes.setError(true);
	  				trayMgtHeaderRes.setMessage("TrayMgtHeader Not Saved .");
				} else {
					trayMgtHeader.setTranDate(new Date());
					trayMgtHeaderRes = trayMgtService.saveTrayMgtHeader(trayMgtHeader);

					trayMgtHeaderRes.setError(false);
					trayMgtHeaderRes.setMessage("TrayMgtHeader Saved Successfully.");
				}

			} catch (Exception e) {

				trayMgtHeaderRes.setError(true);
				trayMgtHeaderRes.setMessage("TrayMgtHeader Not Saved .Exc");

				e.printStackTrace();
				System.out.println("Exception In TrayManagementController /saveTrayMgtHeader" + e.getMessage());

			}
			return trayMgtHeaderRes;

		}
	// ----------------------------SAVE Tray Management Detail---------------------------
	@RequestMapping(value = { "/saveTrayMgtDetail" }, method = RequestMethod.POST)
	public @ResponseBody Info saveTrayMgtDetail(@RequestBody TrayMgtDetailBean trayMgtDetail) {

		TrayMgtDetailBean trayMgtDetailRes = null;
		Info info = new Info();
		try {

			trayMgtDetail.setOuttrayDate(new Date());
			trayMgtDetailRes = trayMgtService.saveTrayMgtDetail(trayMgtDetail);

			if (trayMgtDetailRes != null) {
				info.setError(false);
				info.setMessage("TrayMgtDetail Saved Successfully.");
			} else {
				info.setError(true);
				info.setMessage("TrayMgtDetail Not Saved .");
			}

		} catch (Exception e) {

			info.setError(true);
			info.setMessage("TrayMgtDetail Not Saved .");

			e.printStackTrace();
			System.out.println("Exception In TrayManagementController /saveTrayMgtDetail" + e.getMessage());

		}
		return info;

	}

	// ---------------------------------------------------------------------------
	// ----------------------------SAVE Tray Management Detail---------------------------
	@RequestMapping(value = { "/saveTrayMgtDetailForBill" }, method = RequestMethod.POST)
	public @ResponseBody Info saveTrayMgtDetailForBill(@RequestBody List<TrayMgtDetailBean> trayMgtDetails) {

		List<TrayMgtDetailBean> trayMgtDetailRes = null;
		Info info = new Info();
		try {

			trayMgtDetailRes = trayMgtService.saveTrayMgtDetailForBill(trayMgtDetails);

			if (!trayMgtDetailRes.isEmpty()) {
				info.setError(false);
				info.setMessage("TrayMgtDetails Saved Successfully.");
			} else {
				info.setError(true);
				info.setMessage("TrayMgtDetails Not Saved .");
			}

		} catch (Exception e) {

			info.setError(true);
			info.setMessage("TrayMgtDetails Not Saved .");

			e.printStackTrace();
			System.out.println("Exception In TrayManagementController /saveTrayMgtDetail" + e.getMessage());

		}
		return info;

	}

	// ---------------------------------------------------------------------------
	// --------------------------END--------------------------------------------------
	@RequestMapping(value = { "/deleteTrayMgtHeader" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage deleteTrayMgtHeader(@RequestParam("tranId") int tranId) {

		ErrorMessage errorMessage = trayMgtService.deleteTrayMgtHeader(tranId);

		return errorMessage;
	}
	// --------------------------END--------------------------------------------------

	// ---------------------------Getting TrayMgtDetail By FrId-------------------------
	@RequestMapping(value = { "/getTrayMgtDetailByFrId" }, method = RequestMethod.POST)
	public @ResponseBody TrayMgtDetail getTrayMgtDetailByFrId(@RequestParam("frId") int frId,
			@RequestParam("tranId") int tranId) {

		TrayMgtDetail trayMgtDetailRes = trayMgtService.getTrayMgtDetailByFrId(frId, tranId);

		return trayMgtDetailRes;

	}

	// ------------------------------------------------------------------------------------
	// ---------------------------Getting TrayMgtDetail By TranId-------------------------
	@RequestMapping(value = { "/getTrayMgtDetailByTranId" }, method = RequestMethod.POST)
	public @ResponseBody List<TrayMgtDetail> getTrayMgtDetailByTranId(@RequestParam("tranId") int tranId) {

		List<TrayMgtDetail> trayMgtDetailRes = trayMgtService.getTrayMgtDetailByTranId(tranId);

		return trayMgtDetailRes;

	}

	// ------------------------------------------------------------------------------------
	// ---------------------------Getting TrayMgtDetails By TranId-------------------------
	@RequestMapping(value = { "/getTrayMgtDetailsByTranIdAndDate" }, method = RequestMethod.POST)
	public @ResponseBody List<TrayMgtDetailList> getTrayMgtDetailsByTranIdAndDate(@RequestParam("tranId") int tranId,
			@RequestParam("date") String date) {

		List<TrayMgtDetailList> trayMgtDetailRes = trayMgtService.getTrayMgtDetailsByTranIdAndDate(tranId, date);

		return trayMgtDetailRes;

	}

		// ------------------------------------------------------------------------------------
	// ---------------------------Getting TrayMgtDetail By Status For Bill-------------------------
	@RequestMapping(value = { "/getTrayMgtDetailsForBill" }, method = RequestMethod.GET)
	public @ResponseBody List<TrayMgtDetail> getTrayMgtDetailsForBill() {

		List<TrayMgtDetail> trayMgtDetailRes = trayMgtService.getTrayMgtDetailsForBill();

		return trayMgtDetailRes;

	}

	// ------------------------------------------------------------------------------------
	// ---------------------------Getting TrayMgtDetail By Status For Bill-------------------------
	@RequestMapping(value = { "/viewTrayMgtBillDetails" }, method = RequestMethod.POST)
	public @ResponseBody List<TrayMgtDetail> viewTrayMgtBillDetails(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("frIds") List<String> frIds) {
		fromDate = Common.convertToYMD(fromDate);
		toDate = Common.convertToYMD(toDate);
		List<TrayMgtDetail> trayMgtDetailRes = trayMgtService.getTrayMgtBillDetails(fromDate, toDate, frIds);

		return trayMgtDetailRes;

	}

	// ------------------------------------------------------------------------------------
	// ---------------------------Getting TrayMgtDetail Between Date And Status-------------------------
	@RequestMapping(value = { "/getTrayMgtDetailList" }, method = RequestMethod.POST)
	public @ResponseBody List<TrayMgtDetail> getTrayMgtDetailList(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("frId") int frId,
			@RequestParam("IsDepositUsed") int isDepositUsed) {
		fromDate = Common.convertToYMD(fromDate);
		toDate = Common.convertToYMD(toDate);
		List<TrayMgtDetail> trayMgtDetailRes = trayMgtService.getTrayMgtDetailList(fromDate, toDate, frId,
				isDepositUsed);

		return trayMgtDetailRes;

	}
	// ------------------------------------------------------------------------------------

	// ---------------------------Getting TrayMgtDetail By FrId And status-------------------------
	@RequestMapping(value = { "/getTrayDetailByStatus" }, method = RequestMethod.POST)
	public @ResponseBody TrayMgtDetail getTrayDetailByStatus(@RequestParam("frId") int frId,
			@RequestParam("trayStatus") int trayStatus, @RequestParam("isSameDay") int isSameDay) {

		TrayMgtDetail trayMgtDetailRes = trayMgtService.getTrayDetailByStatus(frId, trayStatus, isSameDay);

		return trayMgtDetailRes;

	}

	// ------------------------------------------------------------------------------------
	// ---------------------------Getting TrayMgtDetail-------------------------
	@RequestMapping(value = { "/getTrayDetailForTrayIn" }, method = RequestMethod.POST)
	public @ResponseBody List<TrayMgtDetail> getTrayDetailForTrayIn(@RequestParam("frId") int frId,
			@RequestParam("isSameDay") int isSameDay) {

		List<TrayMgtDetail> trayMgtDetailRes = trayMgtService.getTrayDetailForTrayIn(frId, isSameDay);

		return trayMgtDetailRes;

	}

	// ------------------------------------------------------------------------------------
	// --------Getting Out Trays of different types For Franchise(Based on order of Items,no. of tray needed to Franchise)----
	@RequestMapping(value = { "/getOutTraysForFr" }, method = RequestMethod.POST)
	public @ResponseBody List<FrOutTrays> getOutTraysForFr(@RequestParam("frId") int frId,
			@RequestParam("billDate") String billDate) {

		List<FrOutTrays> frOutTrays = trayMgtService.getOutTraysForFr(frId, billDate);

		return frOutTrays;
	}

	// --------------------------------------------------------------------------------------------------------------------------
	// --------------------------Update Extra Out Trays------------------------------------------------------
	@RequestMapping(value = { "/updateExtraOutTrays" }, method = RequestMethod.POST)
	public @ResponseBody Info updateExtraOutTrays(@RequestParam("tranId") int tranId,
			@RequestParam("extraOutTrays") int extraOutTrays) {

		Info info = trayMgtService.updateExtraOutTrays(tranId, extraOutTrays);

		return info;
	}

	// ----------------------------------------------------------------------------------------------------------
	// --------------------------Update Extra In Trays------------------------------------------------------
	@RequestMapping(value = { "/updateExtraInTrays" }, method = RequestMethod.POST)
	public @ResponseBody Info updateExtraInTrays(@RequestParam("tranId") int tranId,
			@RequestParam("extraInTrays") int extraInTrays) {

		Info info = trayMgtService.updateExtraInTrays(tranId, extraInTrays);

		return info;
	}

	// ----------------------------------------------------------------------------------------------------------
	// --------------------------Update Diesel ------------------------------------------------------
	@RequestMapping(value = { "/updateDieselOfVehicle" }, method = RequestMethod.POST)
	public @ResponseBody Info updateDieselForTransaction(@RequestParam("tranId") int tranId,
			@RequestParam("diesel") float diesel) {

		Info info = trayMgtService.updateDieselForTransaction(tranId, diesel);

		return info;
	}

	// ----------------------------------------------------------------------------------------------------------
	// --------------------------------Getting Tray Management Headers---------------------------
	@RequestMapping(value = { "/getLoadedVehicles" }, method = RequestMethod.POST)
	public @ResponseBody List<GetTrayMgtHeader> getLoadedVehicles(@RequestParam("date") String date,
			@RequestParam("vehStatus") int vehStatus) {

		List<GetTrayMgtHeader> getTrayMgtHeaders = trayMgtService.getLoadedVehicles(date, vehStatus);

		return getTrayMgtHeaders;
	}

	// --------------------------------------------------------------------------------------------------------------------------
	// --------------------------------Getting Tray Management Headers---------------------------
	@RequestMapping(value = { "/getLoadedVehiclesByStatus" }, method = RequestMethod.POST)
	public @ResponseBody List<GetTrayMgtHeader> getLoadedVehiclesByStatus(@RequestParam("vehStatus") int vehStatus) {

		List<GetTrayMgtHeader> getTrayMgtHeaders = trayMgtService.getLoadedVehiclesByStatus(vehStatus);

		return getTrayMgtHeaders;
	}

	// --------------------------------------------------------------------------------------------------------------------------
	// --------------------------------Getting Server Date---------------------------
	@RequestMapping(value = { "/getServerDate" }, method = RequestMethod.GET)
	public @ResponseBody Info getServerDate() {

		Info info = new Info();
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			info.setError(false);
			info.setMessage(dateFormat.format(date));
		} catch (Exception e) {
			info.setError(true);
			info.setMessage("");
			e.printStackTrace();
		}

		return info;
	}

	// --------------------------------------------------------------------------------------------------------------------------
	// --------------------------------Getting Tray Management Headers---------------------------
	@RequestMapping(value = { "/getAllVehicles" }, method = RequestMethod.POST)
	public @ResponseBody List<GetTrayMgtHeader> getAllVehicles(@RequestParam("date") String date) {

		List<GetTrayMgtHeader> getTrayMgtHeaders = trayMgtService.getAllVehicles(date);

		return getTrayMgtHeaders;
	}

	// --------------------------------------------------------------------------------------------------------------------------
	// --------------------------------Getting Tray Management Header---------------------------
	@RequestMapping(value = { "/getTrayMgtHeader" }, method = RequestMethod.POST)
	public @ResponseBody GetTrayMgtHeader getTrayMgtHeader(@RequestParam("tranId") int tranId) {

		GetTrayMgtHeader getTrayMgtHeaderRes = trayMgtService.getTrayMgtHeader(tranId);

		return getTrayMgtHeaderRes;
	}

	// --------------------------------------------------------------------------------------------------------------------------
	// --------------------------Update Vehicle Status to (1) and out km&time------------------------------------------------------
	@RequestMapping(value = { "/updateOutVehicleData" }, method = RequestMethod.POST)
	public @ResponseBody Info updateVehicleOutData(@RequestParam("tranId") int tranId, @RequestParam("vehOutkm") float vehOutKm) {
		
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		
		Info info = trayMgtService.updateVehicleOutData(tranId, df.format(calobj.getTime()), vehOutKm);

		return info;
	}

	// ----------------------------------------------------------------------------------------------------------
	// --------------------------Update Vehicle Status to (2) and In km,running km & time------------------------------------------------------
	@RequestMapping(value = { "/updateInVehicleData" }, method = RequestMethod.POST)
	public @ResponseBody Info updateInVehicleData(@RequestParam("tranId") int tranId,@RequestParam("vehInkm") float vehInkm,
			@RequestParam("extraTrayIn") int extraTrayIn) {

		Info info = null;
		try {
			TrayMgtHeader getTrayMgtHeaderRes = trayMgtService.getTrayMgtHeaderByTranId(tranId);
			DateFormat df = new SimpleDateFormat("HH:mm:ss");
			Calendar calobj = Calendar.getInstance();
			getTrayMgtHeaderRes.setVehIntime(df.format(calobj.getTime()));
			getTrayMgtHeaderRes.setVehInkm(vehInkm);
			getTrayMgtHeaderRes.setExtraTrayIn(extraTrayIn);
			getTrayMgtHeaderRes.setVehStatus(2);
			getTrayMgtHeaderRes.setVehRunningKm(vehInkm - getTrayMgtHeaderRes.getVehOutkm());//update current km in vehical against vehId

			TrayMgtHeader trayMgtHeader = trayMgtService.saveTrayMgtHeader(getTrayMgtHeaderRes);
			if (trayMgtHeader != null) {
				info = new Info();
				info.setError(false);
				info.setMessage("In Vehicle Data Updated");
			}
		} catch (Exception e) {
			info = new Info();
			info.setError(true);
			info.setMessage("In Vehicle Data Not Updated");
			e.printStackTrace();
		}
		return info;
	}

	// ----------------------------------------------------------------------------------------------------------
	// ----------------------------------------Getting Franchise In Route-----------------------------------------------------
	@RequestMapping(value = { "/getFranchiseInRoute" }, method = RequestMethod.POST)
	public @ResponseBody List<FranchiseInRoute> getFranchiseInRoute(@RequestParam("routeId") int routeId,
			@RequestParam("tranId") int tranId) {

		List<FranchiseInRoute> franchiseInRoute = trayMgtService.getFranchiseInRoute(routeId, tranId);

		return franchiseInRoute;
	}

	// --------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = { "/insertTrayInAndBalance" }, method = RequestMethod.POST)
	public @ResponseBody Info insertTrayInAndBalance(@RequestParam("tranStatus1") int tranStatus1,
			@RequestParam("tranStatus2") int tranStatus2, @RequestParam("intrayBig") int intrayBig,
			@RequestParam("intraySmall") int intraySmall, @RequestParam("intrayLead") int intrayLead,
			@RequestParam("intrayExtra") int intrayExtra, @RequestParam("tranStatus3") int tranStatus3,
			@RequestParam("intrayBig1") int intrayBig1, @RequestParam("intraySmall1") int intraySmall1,
			@RequestParam("intrayLead1") int intrayLead1, @RequestParam("intrayExtra1") int intrayExtra1) {
		Info info = new Info();
		try {
			if (tranStatus3 != 0) {
				TrayMgtDetailBean trayMgtDetailRes = trayMgtService.getTrayDetailByDetailId(tranStatus3);

				int balanceBig = trayMgtDetailRes.getOuttrayBig() - (trayMgtDetailRes.getIntrayBig() + intrayBig1);
				int balanceSmall = trayMgtDetailRes.getOuttraySmall()
						- (trayMgtDetailRes.getIntraySmall() + intraySmall1);
				int balanceLead = trayMgtDetailRes.getOuttrayLead() - (trayMgtDetailRes.getIntrayLead() + intrayLead1);
				int balanceExtra = trayMgtDetailRes.getOuttrayExtra()
						- (trayMgtDetailRes.getIntrayExtra() + intrayExtra1);

				trayMgtDetailRes.setBalanceBig(balanceBig);
				trayMgtDetailRes.setBalanceSmall(balanceSmall);
				trayMgtDetailRes.setBalanceLead(balanceLead);
				trayMgtDetailRes.setBalanceExtra(balanceExtra);

				trayMgtDetailRes.setIntrayBig1(intrayBig1);
				trayMgtDetailRes.setIntraySmall1(intraySmall1);
				trayMgtDetailRes.setIntrayExtra1(intrayExtra1);
				trayMgtDetailRes.setIntrayLead1(intrayLead1);

				trayMgtDetailRes.setIntrayDate1(new Date());
				int trayStatus;
				if (balanceBig > 0 || balanceSmall > 0 || balanceLead > 0 || balanceExtra > 0) {
					trayStatus = 4;
				} else {
					trayStatus = 5;
				}
				trayMgtDetailRes.setTrayStatus(trayStatus);
				TrayMgtDetailBean trayMgtDetailUpdated = trayMgtService.saveTrayMgtDetail(trayMgtDetailRes);
				// int isUpdated=trayMgtService.updateTrayStatus(tranStatus3,status);

			}
			if (tranStatus2 != 0) {
				TrayMgtDetailBean trayMgtDetailRes = trayMgtService.getTrayDetailByDetailId(tranStatus2);

				int balanceBig = trayMgtDetailRes.getOuttrayBig() - (intrayBig);
				int balanceSmall = trayMgtDetailRes.getOuttraySmall() - (intraySmall);
				int balanceLead = trayMgtDetailRes.getOuttrayLead() - (intrayLead);
				int balanceExtra = trayMgtDetailRes.getOuttrayExtra() - (intrayExtra);

				trayMgtDetailRes.setBalanceBig(balanceBig);
				trayMgtDetailRes.setBalanceSmall(balanceSmall);
				trayMgtDetailRes.setBalanceLead(balanceLead);
				trayMgtDetailRes.setBalanceExtra(balanceExtra);

				trayMgtDetailRes.setIntrayBig(intrayBig);
				trayMgtDetailRes.setIntraySmall(intraySmall);
				trayMgtDetailRes.setIntrayExtra(intrayExtra);
				trayMgtDetailRes.setIntrayLead(intrayLead);
				trayMgtDetailRes.setIntrayDate(new Date());
				int trayStatus;
				if (balanceBig > 0 || balanceSmall > 0 || balanceLead > 0 || balanceExtra > 0) {
					trayStatus = 3;
				} else {
					trayStatus = 5;
				}
				trayMgtDetailRes.setTrayStatus(trayStatus);
				TrayMgtDetailBean trayMgtDetailUpdated = trayMgtService.saveTrayMgtDetail(trayMgtDetailRes);
				// int isUpdated=trayMgtService.updateTrayStatus(tranStatus2,status);

			}
			if (tranStatus1 != 0) {
				int isUpdated = trayMgtService.updateTrayStatus(tranStatus1, 2);
				info.setError(false);
				info.setMessage("Records Inserted");

			}

		} catch (Exception e) {
			info.setError(true);
			info.setMessage("Records Insertion Failed");
			e.printStackTrace();
		}
		return info;
	}
}
