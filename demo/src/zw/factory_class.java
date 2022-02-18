package zw;

import java.util.Map;

import zw.jdbc.jdbc_configuration;
import zw.jdbc.jdbc_return;
import zw.jdbc.jdbc_total;

public class factory_class {
	// 返回jdbc的类自己执行
	public static jdbc_total jdbc_object() {
		return new jdbc_total();
	}

	// 直接执行jdbc
	public static jdbc_return jdbc_execute(Object object, String type) {
		return jdbc_total.jdbc_total(object, type);
	}
	
	//执行自定义语句
	public static jdbc_return jdbc_execute_sql(Object object, String type ,String customize_sql) {
		return jdbc_total.jdbc_sql(object, type,customize_sql);
	}
	
	//执行自定义语句map
	public static jdbc_return jdbc_execute_sql_map(String type ,String customize_sql) {
		return jdbc_total.jdbc_sql_map(type,customize_sql);
	}
	
	//分页查询
	public static jdbc_return jdbc_execute(Object object, String type,String current) {
		if(current==null){
			current="1";
		}
		jdbc_configuration jdbc=new jdbc_configuration("10","",current);
		
		return jdbc_total.jdbc_total(object, type,jdbc);
	}

	public static void main(String[] args) {
	}
}
