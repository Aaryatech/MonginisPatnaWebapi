package com.ats.webapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

// Bean to insert bill :

@Entity
@Table(name = "t_bill_header")
public class PostBillHeader implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="bill_no")
	private int billNo;
		
	@Column(name="tax_applicable")
	private int taxApplicable;
	
	@Column(name="invoice_no")
	private String invoiceNo;
	
	@Column(name="bill_date")
	private Date billDate;
	
	@Column(name="fr_id")
	private int frId;
	
	@Column(name="fr_code")
	private String frCode;
	
	@Column(name="grand_total")
	private float grandTotal;
	
	@Column(name="taxable_amt")
	private float taxableAmt;
	
	@Column(name="total_tax")
	private float totalTax;
	
	@Column(name="status")
	private int status;
	
	
	@Column(name="del_status")
	private int DelStatus;
	
	@Column(name="remark")
	private String remark;
	
	
	@Column(name="time")
	private String time;
	
	@Column(name="bill_date_time")
	private String billDateTime;
	
	
	@Column(name="sgst_sum")
	private float sgstSum;
	
	@Column(name="cgst_sum")
	private float cgstSum;
	
	@Column(name="igst_sum")
	private float igstSum;
	

	@Transient
	List<PostBillDetail> postBillDetailsList;
	
	
	
	public List<PostBillDetail> getPostBillDetailsList() {
		return postBillDetailsList;
	}

	public void setPostBillDetailsList(List<PostBillDetail> postBillDetailsList) {
		this.postBillDetailsList = postBillDetailsList;
	}

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public String getFrCode() {
		return frCode;
	}

	public void setFrCode(String frCode) {
		this.frCode = frCode;
	}

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	public float getTaxableAmt() {
		return taxableAmt;
	}

	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}

	public float getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(float totalTax) {
		this.totalTax = totalTax;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDelStatus() {
		return DelStatus;
	}

	public void setDelStatus(int delStatus) {
		DelStatus = delStatus;
	}

	
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getTaxApplicable() {
		return taxApplicable;
	}

	public void setTaxApplicable(int taxApplicable) {
		this.taxApplicable = taxApplicable;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getBillDateTime() {
		return billDateTime;
	}

	public void setBillDateTime(String billDateTime) {
		this.billDateTime = billDateTime;
	}

	public float getSgstSum() {
		return sgstSum;
	}

	public void setSgstSum(float sgstSum) {
		this.sgstSum = sgstSum;
	}

	public float getCgstSum() {
		return cgstSum;
	}

	public void setCgstSum(float cgstSum) {
		this.cgstSum = cgstSum;
	}

	public float getIgstSum() {
		return igstSum;
	}

	public void setIgstSum(float igstSum) {
		this.igstSum = igstSum;
	}

	@Override
	public String toString() {
		return "PostBillHeader [billNo=" + billNo + ", taxApplicable=" + taxApplicable + ", invoiceNo=" + invoiceNo
				+ ", billDate=" + billDate + ", frId=" + frId + ", frCode=" + frCode + ", grandTotal=" + grandTotal
				+ ", taxableAmt=" + taxableAmt + ", totalTax=" + totalTax + ", status=" + status + ", DelStatus="
				+ DelStatus + ", remark=" + remark + ", time=" + time + ", billDateTime=" + billDateTime + ", sgstSum="
				+ sgstSum + ", cgstSum=" + cgstSum + ", igstSum=" + igstSum + ", postBillDetailsList="
				+ postBillDetailsList + "]";
	}


}
