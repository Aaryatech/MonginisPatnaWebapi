package com.ats.webapi.repository.newposrepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.webapi.model.newpos.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	
	@Query(value="SELECT uuid() AS id, a.* FROM (\n" + 
			"SELECT user_name AS user_name,user_gst_no AS gst_no ,user_phone AS phone_no FROM t_sell_bill_header GROUP BY user_phone UNION ALL SELECT sp_cust_name AS user_name,cust_gst_no AS gst_no,sp_cust_mob_no AS phone_no FROM t_sp_cake  GROUP BY  sp_cust_mob_no) a WHERE a.user_name != '' GROUP BY a.phone_no ORDER BY  a.user_name ASC",nativeQuery=true)
	List<Customer> getAllCustomer();

}
