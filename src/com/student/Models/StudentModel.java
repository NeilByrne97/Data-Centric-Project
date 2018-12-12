package com.student.Models;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class StudentModel {

	private String sid;
	private String cid;
	private String name;
	private String address;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public StudentModel(String sid, String cid, String name, String address) {
		super();
		this.sid = sid;
		this.cid = cid;
		this.name = name;
		this.address = address;

	}

}
