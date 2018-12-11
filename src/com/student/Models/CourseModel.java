package com.student.Models;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class CourseModel {

	private String cid;
	private String cname;
	private int duration;
	
	public String getcid() {
		return cid;
	}
	public void setcid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public CourseModel(String cid, String cname, int duration) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.duration = duration;
	}

}
