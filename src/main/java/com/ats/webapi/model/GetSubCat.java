package com.ats.webapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GetSubCat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int subCatId;
	private String subCatName;
	private int catId;
	public int getSubCatId() {
		return subCatId;
	}
	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}
	public String getSubCatName() {
		return subCatName;
	}
	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	@Override
	public String toString() {
		return "GetSubCat [subCatId=" + subCatId + ", subCatName=" + subCatName + ", catId=" + catId + "]";
	}
	
	
}
