package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zw.factory_class;
import zw.static_class;
import zw.jdbc.jdbc_return;
import zw.web.web_return;
import zw.web.zw_http;
import zw.web.zw_json;
import zw.web.zw_path;
import zw.web.zw_web;

import com.entity.service;
import com.entity.gearbox;

@zw_web
public class gearbox_controller {
	zw_json json=new zw_json();
	
	
	@zw_path("/gearbox_synchronous")
	public String synchronous(HttpServletRequest request,HttpServletResponse response){
		gearbox vo=new gearbox();
		zw_http.get_parameter(request, response, vo);
		
		  Date date = new Date();
	        String str = "yyy-MM-dd HH:mm:ss";
	        SimpleDateFormat sdf = new SimpleDateFormat(str);
		vo.setUpdate_time(sdf.format(date));
		vo.setUpdate_people("更新人");
		jdbc_return return_jdbc=factory_class.jdbc_execute(vo, static_class.jdbc_type_insert_or_update,"1");
		web_return return_object=new web_return("操作成功");
//			
		return  json.toJsonString(return_object);
	}
	
	@zw_path("/gearbox_remove")
	public String remove(HttpServletRequest request,HttpServletResponse response){
		gearbox vo=new gearbox();
		zw_http.get_parameter(request, response, vo);
		jdbc_return return_jdbc=factory_class.jdbc_execute(vo, static_class.jdbc_type_remove);
		web_return return_object=new web_return("删除成功");
		return  json.toJsonString(return_object);
	}
	
	
	@zw_path("/gearbox_query")
	public String query(HttpServletRequest request,HttpServletResponse response){
		gearbox vo=new gearbox();
		jdbc_return return_jdbc=factory_class.jdbc_execute(vo, static_class.jdbc_type_select);
		web_return return_object=new web_return("查询成功",return_jdbc.getList(),return_jdbc.getCount());
		return  json.toJsonString(return_object);
	}
	
	@zw_path("/gearbox_query_title")
	public String gearbox_query_title(HttpServletRequest request,HttpServletResponse response){
		service vo=new service();
		
		jdbc_return return_jdbc=factory_class.jdbc_execute_sql(vo, static_class.jdbc_type_select,"select id ,title  from gearbox where parentId=0");
		web_return return_object=new web_return("查询成功",return_jdbc.getList(),return_jdbc.getCount());
		return  json.toJsonString(return_object);
	}
	
	
}
