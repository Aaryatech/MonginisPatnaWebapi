package com.ats.webapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ats.webapi.model.ItemWithSubCat;
import com.ats.webapi.repository.GetFrItemRepository;

@Service("GetFrItemsService")

public class GetFrItemsServiceImpl implements GetFrItemsService {

	@Autowired
	private GetFrItemRepository getFrItemRepository;

	
	@Override
	public List<ItemWithSubCat> findFrItems(List<Integer> items) {
		List<ItemWithSubCat> list=getFrItemRepository.findFrItems(items);

		
		return list;
	}

}
