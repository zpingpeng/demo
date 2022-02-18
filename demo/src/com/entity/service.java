package com.entity;

//


public class service {
//	服务站名称
//	服务站地址
//	服务站电话
//	服务站区域
	
	private String id;
	private String title;
	private String address;
	private String phone;
	private String region;
	private String update_people;
	private String update_time;
	private String chassis_brand;
	
	   private String current;	
	   
	   
	   
	public String getChassis_brand() {
		return chassis_brand;
	}
	public void setChassis_brand(String chassis_brand) {
		this.chassis_brand = chassis_brand;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getUpdate_people() {
		return update_people;
	}
	public void setUpdate_people(String update_people) {
		this.update_people = update_people;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	



 
	
}
