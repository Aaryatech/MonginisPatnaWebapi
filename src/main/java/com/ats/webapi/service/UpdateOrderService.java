package com.ats.webapi.service;



public interface UpdateOrderService {

	public int updateOrderQty(int orderId,int orderQty);
	public int deleteOrder(int orderId);
	}
