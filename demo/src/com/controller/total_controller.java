package com.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.dbcp.pool2.impl.AbandonedConfig;

import com.entity.total_entity;

import zw.factory_class;
import zw.static_class;
import zw.jdbc.jdbc_configuration;
import zw.jdbc.jdbc_return;
import zw.web.web_return;
import zw.web.zw_http;
import zw.web.zw_json;
import zw.web.zw_path;
import zw.web.zw_web;

@zw_web
public class total_controller {
	zw_json json=new zw_json();
	
	//1.查询是普通增删改查还是自定义
	//2.然后根据自定义的增删改查来判断
	
	
	
	@zw_path("/total")
	public String query(HttpServletRequest request,HttpServletResponse response){
		//获取所有参数
		Map<String,String> map=new HashMap<String,String>();
		//获取查询参数
	
		total_entity total=new total_entity();
		zw_http.get_parameter_map(request, response, map);
		
		String where_sql=get_param_map(map);
	
		map.put("update_time",get_present_date());
		map.put("update_people","管理员");
		//创建初始参数
		total.setSql_table(map.get("sql_table"));
		map.remove("sql_table");
		total.setSql_type(map.get("sql_type"));
		map.remove("sql_type");
		String id=map.get("id");
		String sql="";
	   String current=map.get("current");
	   map.remove("current");
	
		//查询默认分页
		if(total.getSql_type()!=null&&total.getSql_type().equals("select")){
			if(current==null||current==""){
				current="1";
			}
			jdbc_configuration configuration=new jdbc_configuration("10","",current);
			
			  sql="select * from "+total.getSql_table() + where_sql+" limit  "
						+( (Integer.parseInt(configuration.getCurrent()) - 1)*Integer.parseInt(configuration.getPage() ))+ ","
						+ configuration.getPage() + " ";
			jdbc_return return_jdbc=factory_class.jdbc_execute_sql_map(static_class.jdbc_type_select,sql);
			web_return return_object=new web_return("查询成功",return_jdbc.getList(),return_jdbc.getCount());
			System.out.println(sql);
			//总行数
			sql = "select count(*) as line from "+total.getSql_table()+ where_sql;
			 return_jdbc=factory_class.jdbc_execute_sql_map(static_class.jdbc_type_select,sql);
			 String line=return_jdbc.getList().get(0).toString();
			 line=line.substring(6, line.length()-1);
			 return_object.setLine(line);
			
			return  json.toJsonString(return_object);
		}
		//如果是查询所有
		if(total.getSql_type()!=null&&total.getSql_type().equals("select_all")){
			  sql="select * from "+total.getSql_table() ;
			jdbc_return return_jdbc=factory_class.jdbc_execute_sql_map(static_class.jdbc_type_select,sql);
			web_return return_object=new web_return("查询成功",return_jdbc.getList(),return_jdbc.getCount());
			return  json.toJsonString(return_object);
		}
	
		
		//如果是删除
		if(total.getSql_type()!=null&&total.getSql_type().equals("remove")){
			 	sql = "delete from " + total.getSql_table() + "  where  1=1 "+"and id="+id;
			jdbc_return return_jdbc=factory_class.jdbc_execute_sql_map(static_class.jdbc_type_remove,sql);
			web_return return_object=new web_return(return_jdbc.getCount());
			return  json.toJsonString(return_object);
		}
	
		//如果是同步
		if(total.getSql_type()!=null&&total.getSql_type().equals("synchronous")){
		 if(id.equals("")){
				sql = "insert into " + total.getSql_table() + " (";
				String column_name = "";
				String column_value = "";
				
				 for (String key:map.keySet()){
						if (map.get(key) != null && !map.get(key).equals("")) {
							column_name += key + ",";
							column_value += "'" + map.get(key)  + "',";
						}
					 }
					// 8.删除最后的,并拼接
					column_name = column_name.substring(0,
							column_name.length() - 1);
					column_value = column_value.substring(0,
							column_value.length() - 1);
					sql += column_name + ")values(";
					sql += column_value + ")";
					jdbc_return return_jdbc=factory_class.jdbc_execute_sql_map(static_class.jdbc_type_insert_or_update,sql);
					web_return return_object=new web_return(return_jdbc.getCount());
					return  json.toJsonString(return_object);
		 }
		 
		 if(!id.equals("")){
			 sql = "update " + total.getSql_table() + " set ";
			 for (String key:map.keySet()){
					sql += "   " + key + "='" + map.get(key) + "' ,";
				 }
		 }
		   sql = sql.substring(0, sql.length() - 1);
			sql += " where id='" + map.get("id") + "'";
			
				jdbc_return return_jdbc=factory_class.jdbc_execute_sql_map(static_class.jdbc_type_insert_or_update,sql);
				web_return return_object=new web_return(return_jdbc.getCount());
				return  json.toJsonString(return_object);
		}
		        //如果是自定义
	 if(total.getSql_type()!=null&&total.getSql_type().equals("customize")){
					return   customize(map);
 	 }
		return  json.toJsonString(new web_return());
	}
	
	public String customize(Map<String,String> map){
		String customize_type=map.get("customize_type");
		//判断执行那个具体的自定义方法
		if(customize_type.equals("")){
			
		}
		web_return return_object=new web_return("");
		return  json.toJsonString(return_object);
	}
	
	public String get_present_date(){
		  Date date = new Date();
	        String str = "yyy-MM-dd HH:mm:ss";
	        SimpleDateFormat sdf = new SimpleDateFormat(str);
		return  sdf.format(date);
	}
	
	public String get_param_map(Map<String,String> map){
		String return_map=" where 1=1";
		String current=map.get("current");
		String sql_table=map.get("sql_table");
		String sql_type=map.get("sql_type");
		map.remove("current");//分页
		map.remove("sql_table");
		map.remove("sql_type");
		//查询条件
		if(sql_type.equals("select")){
		
	 Iterator<String> iterator = map.keySet().iterator();
	 while (iterator.hasNext()) {
	 String key = iterator.next();
	 String value = map.get(key);
	 if(!value.equals("")&&value!=null){
	  return_map+=" and "+key+" like '%"+value+"%'";
	 }
	        }
		}
		map.put("current", current);
		map.put("sql_table", sql_table);
		map.put("sql_type", sql_type);
	return return_map;
	}
}
