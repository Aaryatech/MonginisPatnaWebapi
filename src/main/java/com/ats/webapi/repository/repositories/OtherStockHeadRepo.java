package com.ats.webapi.repository.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ats.webapi.model.OtherItemStockHeader;

public interface OtherStockHeadRepo extends JpaRepository<OtherItemStockHeader, Integer> {

	public @ResponseBody List<OtherItemStockHeader> findAllByFrIdAndStatus(int frId, int i);

	
	public @ResponseBody List<OtherItemStockHeader> findAllByOtherStockHeaderIdAndStatus(int herderId, int i);


	

	
}
