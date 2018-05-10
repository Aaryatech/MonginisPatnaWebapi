package com.ats.webapi.service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.webapi.model.FrItemStockConfigureList;
import com.ats.webapi.model.PostBillDetail;
import com.ats.webapi.model.PostBillHeader;
import com.ats.webapi.repository.FrItemStockConfigureRepository;
import com.ats.webapi.repository.OrderRepository;
import com.ats.webapi.repository.PostBillDetailRepository;
import com.ats.webapi.repository.PostBillHeaderRepository;
import com.ats.webapi.repository.RegularSpCkOrderRepository;
import com.ats.webapi.repository.SpCakeOrdersRepository;
import com.ats.webapi.repository.UpdateBillDetailForGrnGvnRepository;
import com.ats.webapi.repository.UpdateSeetingForPBRepo;

@Service
public class PostBillDataServiceImpl implements PostBillDataService {

	@Autowired
	PostBillHeaderRepository postBillHeaderRepository;

	@Autowired
	PostBillDetailRepository postBillDetailRepository;

	@Autowired
	OrderRepository orderRepository;
 
	@Autowired
	SpCakeOrdersRepository spCakeOrdersRepository;
	
	@Autowired
	RegularSpCkOrderRepository  regularSpCkOrderRepository;
	
	
	@Autowired
	UpdateBillDetailForGrnGvnRepository updateBillDetailForGrnGvnRepository;
	
	@Autowired//added here on 3 march
	UpdateSeetingForPBRepo updateSeetingForPBRepo;
	
	@Autowired// added here 3 march
	FrItemStockConfigureRepository frItemStockConfRepo;

	
	
	/*
	 * @Override public List<PostBillDetail> saveBillDetails(List<PostBillDetail>
	 * postBillDetail) {
	 * 
	 * List<PostBillDetail> billDetail=new ArrayList<PostBillDetail>();
	 * for(PostBillDetail pBDetails:postBillDetail) {
	 * 
	 * billDetail=postBillDataService.saveBillDetails(postBillDetail);
	 * 
	 * }
	 * 
	 * return billDetail; }
	 */

	@Override
	public List<PostBillHeader> saveBillHeader(List<PostBillHeader> postBillHeader) {
		
		List<PostBillHeader> pbHeaderList=new ArrayList<>();
		
		PostBillHeader postBillHeaders = new PostBillHeader();
		for (int i = 0; i < postBillHeader.size(); i++) {
			
			
			int settingValue=frItemStockConfRepo.findBySettingKey("PB");
			
			System.out.println("Setting Value Received " + settingValue);
			int year = Year.now().getValue();
			String strYear = String.valueOf(year);
			strYear = strYear.substring(2);

			int length = String.valueOf(settingValue).length();

			String invoiceNo = null;

			if (length == 1)

				invoiceNo = strYear + "-" + "0000" + settingValue;
			if (length == 2)

				invoiceNo = strYear + "-" + "000" + settingValue;

			if (length == 3)

				invoiceNo = strYear + "-" + "00" + settingValue;

			if (length == 4)

				invoiceNo = strYear + "-" + "0" + settingValue;

			System.out.println("*** settingValue= " + settingValue);
			
			
			postBillHeader.get(i).setInvoiceNo(invoiceNo);
			

			postBillHeaders = postBillHeaderRepository.save(postBillHeader.get(i));
			
			if(postBillHeaders!=null && postBillHeaders.getBillNo()>0 ) {
				
				settingValue=settingValue+1;
				
				int result = updateSeetingForPBRepo.updateSeetingForPurBill(settingValue, "PB");
				
				System.err.println("PB setting value updated "+ result);
				
			}

			int billNo = postBillHeader.get(i).getBillNo();

			List<PostBillDetail> postBillDetailList = postBillHeader.get(i).getPostBillDetailsList();

			for (int j = 0; j < postBillDetailList.size(); j++) {

				PostBillDetail billDetail = postBillDetailList.get(j);

				billDetail.setBillNo(billNo);
				
				
				if(billDetail.getOrderQty()==0) {
					
					
					
					System.out.println("order qty 0 received ");
					
					int  updateOrderStatus = orderRepository.updateBillStatus(billDetail.getOrderId(), 1);
					
				}
				
				else {

				postBillDetailRepository.save(billDetail);
				
				int res=0;
				if (billDetail.getCatId() != 5) { 
					
					if(billDetail.getMenuId()!= 42) {//item
						
					 res = orderRepository.updateBillStatus(billDetail.getOrderId(), 2);
					
					}else { // regular sp cake
						regularSpCkOrderRepository.updateRegSpCakeBillStatus(billDetail.getOrderId(), 2);
					}
					
				} else if (billDetail.getCatId() == 5){ //special cake
					 res = spCakeOrdersRepository.updateSpBillStatus(billDetail.getOrderId(), 2);
				}
				
				

				System.out.println("Result set for" + billDetail.getOrderId() + " status " + res);

			}
			// postBillDetail.get(i).setBillNo(billNo);
			// postBillDetailRepository.save(postBillDetail);

		}
			pbHeaderList.add(postBillHeaders);
		}

		return pbHeaderList;
	}




	@Override
	public List<PostBillHeader> updateBillHeader(List<PostBillHeader> postBillHeader) {
		
		
		List<PostBillHeader> postBillHeaders = new ArrayList<PostBillHeader>();
		for (int i = 0; i < postBillHeader.size(); i++) {

			postBillHeaders = postBillHeaderRepository.save(postBillHeader);

			int billNo = postBillHeader.get(i).getBillNo();

			List<PostBillDetail> postBillDetailList = postBillHeader.get(i).getPostBillDetailsList();

			for (int j = 0; j < postBillDetailList.size(); j++) {

				PostBillDetail billDetail = postBillDetailList.get(j);

				billDetail.setBillNo(billNo);

				postBillDetailRepository.save(billDetail);
				
				
				
				
				
			}
			// postBillDetail.get(i).setBillNo(billNo);
			// postBillDetailRepository.save(postBillDetail);

		}

		return postBillHeaders;
		
		
	}

}
