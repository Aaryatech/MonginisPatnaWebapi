package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.FrCustomerList;

public interface FrCustomerListRepo extends JpaRepository<FrCustomerList, Integer> {

	
	@Query(value="SELECT\r\n" + 
			"    t1.id,\r\n" + 
			"    t1.bill_no,\r\n" + 
			"    t1.user,\r\n" + 
			"    t1.mobile,\r\n" + 
			"    DATE_FORMAT(t1.bill_date, \"%d/%m/%Y\") AS bill_date\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        UUID() AS id, h.sell_bill_no AS bill_no, IF(\r\n" + 
			"            h.user_name IS NULL OR h.user_name = '',\r\n" + 
			"            'NA',\r\n" + 
			"            TRIM(h.user_name)\r\n" + 
			"        ) AS USER,\r\n" + 
			"        IF(\r\n" + 
			"            h.user_phone IS NULL OR h.user_phone = '',\r\n" + 
			"            '-',\r\n" + 
			"            TRIM(h.user_phone)\r\n" + 
			"        ) AS mobile,\r\n" + 
			"        h.bill_date AS bill_date\r\n" + 
			"    FROM\r\n" + 
			"        t_sell_bill_header h\r\n" + 
			"    WHERE\r\n" + 
			"        h.del_status = 0 AND h.fr_id = :frId AND h.bill_date BETWEEN :fromDate AND :toDate  AND h.sell_bill_no IN(\r\n" + 
			"        SELECT\r\n" + 
			"            MAX(sell_bill_no)\r\n" + 
			"        FROM\r\n" + 
			"            t_sell_bill_header\r\n" + 
			"        WHERE\r\n" + 
			"            del_status = 0 AND fr_id = :frId\r\n" + 
			"        GROUP BY\r\n" + 
			"            user_phone\r\n" + 
			"    )\r\n" + 
			"UNION\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        UUID() AS id, t.sp_order_no AS bill_no, IF(\r\n" + 
			"            t.sp_cust_name IS NULL OR t.sp_cust_name = '',\r\n" + 
			"            'NA',\r\n" + 
			"            TRIM(t.sp_cust_name)\r\n" + 
			"        ) AS USER,\r\n" + 
			"        IF(\r\n" + 
			"            t.sp_cust_mob_no IS NULL OR t.sp_cust_mob_no = '',\r\n" + 
			"            '-',\r\n" + 
			"            TRIM(t.sp_cust_mob_no)\r\n" + 
			"        ) AS mobile,\r\n" + 
			"        t.sp_delivery_date AS bill_date\r\n" + 
			"    FROM\r\n" + 
			"        t_sp_cake t\r\n" + 
			"    WHERE\r\n" + 
			"        t.del_status = 0 AND t.fr_id = :frId AND  t.sp_delivery_date BETWEEN :fromDate AND :toDate  AND t.sp_order_no IN(\r\n" + 
			"        SELECT\r\n" + 
			"            MAX(sp_order_no)\r\n" + 
			"        FROM\r\n" + 
			"            t_sp_cake\r\n" + 
			"        WHERE\r\n" + 
			"            del_status = 0 AND fr_id = :frId\r\n" + 
			"        GROUP BY\r\n" + 
			"            sp_cust_mob_no\r\n" + 
			"    ))\r\n" + 
			") t1\r\n" + 
			"ORDER BY\r\n" + 
			"    t1.bill_date",nativeQuery=true)
	public List<FrCustomerList> getAllCustByFr(@Param("frId") int frId, @Param("fromDate") String fromDate, @Param("toDate") String toDate);

	
//	SELECT\r\n" + 
//	"    t1.id,\r\n" + 
//	"    t1.bill_no,\r\n" + 
//	"    t1.user,\r\n" + 
//	"    t1.mobile,\r\n" + 
//	"    DATE_FORMAT(t1.bill_date, \"%d/%m/%Y\") AS bill_date\r\n" + 
//	"FROM\r\n" + 
//	"    (\r\n" + 
//	"    SELECT\r\n" + 
//	"        UUID() AS id, h.sell_bill_no AS bill_no, IF(\r\n" + 
//	"            h.user_name IS NULL OR h.user_name = '',\r\n" + 
//	"            'NA',\r\n" + 
//	"            TRIM(h.user_name)\r\n" + 
//	"        ) AS USER,\r\n" + 
//	"        IF(\r\n" + 
//	"            h.user_phone IS NULL OR h.user_phone = '',\r\n" + 
//	"            '-',\r\n" + 
//	"            TRIM(h.user_phone)\r\n" + 
//	"        ) AS mobile,\r\n" + 
//	"        h.bill_date AS bill_date\r\n" + 
//	"    FROM\r\n" + 
//	"        t_sell_bill_header h\r\n" + 
//	"    WHERE\r\n" + 
//	"        h.del_status = 0 AND h.fr_id = :frId AND h.sell_bill_no IN(\r\n" + 
//	"        SELECT\r\n" + 
//	"            MAX(sell_bill_no)\r\n" + 
//	"        FROM\r\n" + 
//	"            t_sell_bill_header\r\n" + 
//	"        WHERE\r\n" + 
//	"            del_status = 0 AND fr_id = :frId\r\n" + 
//	"        GROUP BY\r\n" + 
//	"            user_phone\r\n" + 
//	"    )\r\n" + 
//	"UNION\r\n" + 
//	"    (\r\n" + 
//	"    SELECT\r\n" + 
//	"        UUID() AS id, t.sp_order_no AS bill_no, IF(\r\n" + 
//	"            t.sp_cust_name IS NULL OR t.sp_cust_name = '',\r\n" + 
//	"            'NA',\r\n" + 
//	"            TRIM(t.sp_cust_name)\r\n" + 
//	"        ) AS USER,\r\n" + 
//	"        IF(\r\n" + 
//	"            t.sp_cust_mob_no IS NULL OR t.sp_cust_mob_no = '',\r\n" + 
//	"            '-',\r\n" + 
//	"            TRIM(t.sp_cust_mob_no)\r\n" + 
//	"        ) AS mobile,\r\n" + 
//	"        t.sp_delivery_date AS bill_date\r\n" + 
//	"    FROM\r\n" + 
//	"        t_sp_cake t\r\n" + 
//	"    WHERE\r\n" + 
//	"        t.del_status = 0 AND t.fr_id = :frId AND t.sp_order_no IN(\r\n" + 
//	"        SELECT\r\n" + 
//	"            MAX(sp_order_no)\r\n" + 
//	"        FROM\r\n" + 
//	"            t_sp_cake\r\n" + 
//	"        WHERE\r\n" + 
//	"            del_status = 0 AND fr_id = :frId\r\n" + 
//	"        GROUP BY\r\n" + 
//	"            sp_cust_mob_no\r\n" + 
//	"    ))\r\n" + 
//	") t1\r\n" + 
//	"ORDER BY\r\n" + 
//	"    t1.user
	//public List<FrCustomerList> getAllCustByFr(@Param("frId") int frId);
}
