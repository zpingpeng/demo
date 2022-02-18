package com.entity;

public class video {


	//标题
	//视频地址
	//类型，上装等
	//关联汽车类型
	//是否首页
	//是否广告
	//标题图
	private String id;
	private String title;
	private String video;
	private String type;
	private String sort;
	private String related_car_type;
	private String whether_home;
	private String whether_ad;
	
	private String image;
	private String current;
	private String update_people;
	private String update_time;
	private String ctr;
	
	
	
	
	
	public String getCtr() {
		return ctr;
	}
	public void setCtr(String ctr) {
		this.ctr = ctr;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRelated_car_type() {
		return related_car_type;
	}
	public void setRelated_car_type(String related_car_type) {
		this.related_car_type = related_car_type;
	}
	public String getWhether_home() {
		return whether_home;
	}
	public void setWhether_home(String whether_home) {
		this.whether_home = whether_home;
	}
	public String getWhether_ad() {
		return whether_ad;
	}
	public void setWhether_ad(String whether_ad) {
		this.whether_ad = whether_ad;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	
	
	
	
	
	
}
