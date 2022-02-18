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

import com.entity.car;
import com.entity.user;

@zw_web
@MultipartConfig
public class car_controller {
	zw_json json=new zw_json();
	
	@zw_path("/car_query")
	public String query(HttpServletRequest request,HttpServletResponse response){
		car vo=new car();
		zw_http.get_parameter(request, response, vo);
		
	    String current=vo.getCurrent();
	    vo.setCurrent(null);
		jdbc_return return_jdbc=factory_class.jdbc_execute(vo, static_class.jdbc_type_select,current);
		web_return return_object=new web_return("查询成功",return_jdbc.getList(),return_jdbc.getCount());
		return  json.toJsonString(return_object);
	}
	
	@zw_path("/car_synchronous")
	public String synchronous(HttpServletRequest request,HttpServletResponse response) throws Exception{
		car vo=new car();
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
	
	@zw_path("/car_remove")
	public String remove(HttpServletRequest request,HttpServletResponse response){
		car vo=new car();
		zw_http.get_parameter(request, response, vo);
		jdbc_return return_jdbc=factory_class.jdbc_execute(vo, static_class.jdbc_type_remove);
		web_return return_object=new web_return("删除成功");
		return  json.toJsonString(return_object);
	}
	
	
	
	
	//上传文件。页面请求在最下面
	public void getFile(HttpServletRequest request){
		
		try{
			   DiskFileItemFactory factoy=new DiskFileItemFactory();
			    ServletFileUpload sfu=new ServletFileUpload(factoy);
			    //解析request
			        Map<String, List<FileItem>> map=sfu.parseParameterMap(request);
			        List<FileItem> list=map.get("file");
			        
//			        System.out.println("一般表单"+map.get("title").get(0).getName()+","
//			                +map.get("title").get(0).getString());
			        
			        
			        for (FileItem fileItem : list) {
			            fileItem.getFieldName();
			            //判断是表单元素还是文件元素
			            fileItem.isFormField();
			            File file=new File("D:/aaa.png");
			            fileItem.write(file);
//			            System.out.println(fileItem.getString());
//			            System.out.println(fileItem.getName());
//			            System.out.println(fileItem.getFieldName());
			        }

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
//	 function upload(obj){
////			<input type="file" id="file111" onchange="upload(this)">
//	  var files = document.getElementById('file111').files ;
//	  
//	  var formData = new FormData();
//	  formData.append("title","aaaaaaaaaa");
//	      for(var i = 0;i<files.length;i++){        
//	          formData.append("file", files[i]);
//	          
//	      }
//	    console.log(formData.get("file"))
//	    
//	    
//	 //进度条
//	   // xhr.upload.addEventListener("progress", function(event) {
//	 //       if(event.lengthComputable){
//	   //         progress.style.width = Math.ceil(event.loaded * 100 / event.total) + "%";
//	  //      }
//	  //  }, false);
//	    
//	      var dataarr=zw.ajax({url:"../car_synchronous",type:"post",parameter:formData});
//		console.log(dataarr)
//	    
//	    
//	  }
	
}
