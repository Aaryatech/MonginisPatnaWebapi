package com.ats.webapi.service.logistics;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ats.webapi.model.Info;
import com.ats.webapi.model.logistics.DriverMaster;

@Service
public interface DriverMasterService {

	DriverMaster postDriverMaster(DriverMaster driverMaster);

	Info deleteDriverMaster(int driverId);

	List<DriverMaster> getAllDriverList();

	DriverMaster getDriverById(int driverId);

	List<DriverMaster> getAlertDriverRecord(String today);

}
