package zw;

public class static_class {
	public static final String web_index = "static/index.html";
	public static final String jdbc_user = "root";
	public static final String jdbc_password = "123";
	public static final String jdbc_class= "com.mysql.cj.jdbc.Driver";
 	public static final String jdbc_url = "jdbc:mysql://localhost:3306/car?characterEncoding=utf8&serverTimezone=UTC";
	//public static final String jdbc_url = "jdbc:mysql://localhost:3306/car?characterEncoding=utf8&serverTimezone=UTC";

	public static final String jdbc_type_insert_or_update = "insert_or_update";
	public static final String jdbc_type_select =  "select";
	public static final String jdbc_type_count =  "count";
	public static final String jdbc_type_remove =  "remove";
	
	//查询列明和注释  ORA是select * from user_tab_cols 
	//select COLUMN_COMMENT,COLUMN_NAME from information_schema.COLUMNS where TABLE_SCHEMA='car' and table_name='user';#

	//mysql重启后就要修改这个最大值.。也可以修改配置文件
	//	set global max_allowed_packet = 500*1024*1024
}
