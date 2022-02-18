package com.controller;

import java.io.File;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import zw.factory_class;
import zw.static_class;
import zw.jdbc.jdbc_return;
import zw.web.web_return;
import zw.web.zw_http;
import zw.web.zw_json;
import zw.web.zw_path;
import zw.web.zw_web;

import com.entity.image;
import com.entity.user;

@zw_web
@MultipartConfig
public class image_controller {
	zw_json json=new zw_json();
	
	@zw_path("/image_query")
	public String query(HttpServletRequest request,HttpServletResponse response){
		image vo=new image();
		
		zw_http.get_parameter(request, response, vo);
	    String current=vo.getCurrent();
	    vo.setCurrent(null);
		jdbc_return return_jdbc=factory_class.jdbc_execute(vo, static_class.jdbc_type_select,current);
		web_return return_object=new web_return("查询成功",return_jdbc.getList(),return_jdbc.getCount());
		return  json.toJsonString(return_object);
	}
	
	@zw_path("/image_synchronous")
	public String synchronous(HttpServletRequest request,HttpServletResponse response) throws Exception{
		image vo=new image();
		zw_http.get_parameter(request, response, vo);
//		vo.setContent(URLDecoder.decode(vo.getContent(),"UTF-8"));
//		vo.setImg(URLDecoder.decode(vo.getImg(),"UTF-8"));
		  Date date = new Date();
	        String str = "yyy-MM-dd HH:mm:ss";
	        SimpleDateFormat sdf = new SimpleDateFormat(str);
		vo.setUpdate_time(sdf.format(date));
		vo.setUpdate_people("更新人");
		jdbc_return return_jdbc=factory_class.jdbc_execute(vo, static_class.jdbc_type_insert_or_update,"1");
		web_return return_object=new web_return("操作成功");
		return  json.toJsonString(return_object);
	}
	
	@zw_path("/image_remove")
	public String remove(HttpServletRequest request,HttpServletResponse response){
		image vo=new image();
		zw_http.get_parameter(request, response, vo);
		jdbc_return return_jdbc=factory_class.jdbc_execute(vo, static_class.jdbc_type_remove);
		web_return return_object=new web_return("删除成功");
		return  json.toJsonString(return_object);
	}
	
	
}
