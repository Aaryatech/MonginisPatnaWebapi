package com.ats.webapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "m_user")
public class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="usr_id")
	private Long id;
    @Column(name="usr_name",unique=true)
	private String username;
    @Column(name="usr_pwd")
    private String password;
	@Column(name="usr_type")
    private int usertype;
	@Column(name="del_status")
    private int delStatus;
	
	@Column(name="dept_id")
    private int deptId;
	
	@Column(name="role_id")
    private int roleId;
	
    
    public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getUsertype() {
  		return usertype;
  	}

  	public void setUsertype(int usertype) {
  		this.usertype = usertype;
  	}
  	
	public Long getId() {
		return id;
	}
   
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", usertype=" + usertype
				+ ", delStatus=" + delStatus + ", deptId=" + deptId + ", roleId=" + roleId + "]";
	}

	 
}
