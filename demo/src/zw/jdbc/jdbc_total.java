package zw.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zw.static_class;

public class jdbc_total {
	// 声明公用变量
	private static String jdbc_class = static_class.jdbc_class;
	private static String jdbc_user = static_class.jdbc_user;
	private static String jdbc_password = static_class.jdbc_password;
	private static String jdbc_url = static_class.jdbc_url;
	private static String jdbc_type_insert_or_update = static_class.jdbc_type_insert_or_update;
	private static String jdbc_type_select = static_class.jdbc_type_select;
	private static String jdbc_type_count = static_class.jdbc_type_count;
	private static String jdbc_type_remove = static_class.jdbc_type_remove;
	
	private static Connection connection = null;
	private static Statement statement = null;
	private static String sql = "";

	@SuppressWarnings("unused")
	// 0.直接方便的使用，供外部调用
	public static jdbc_return jdbc_total(Object object, String type) {
		// 1.创建连接并打开连接
		open_connection();
		// 2.解析sql并执行完毕后解析数据
		parsing_sql(object, type);
		jdbc_return return_jdbc = execute_sql(object, type);
		// 3.返回并关闭连接
		close_connection();
		return return_jdbc;
	}
	//执行自定义sql
	@SuppressWarnings("unused")
	public static jdbc_return jdbc_sql(Object object, String type,String customize_sql) {
		open_connection();
		sql=customize_sql;
		jdbc_return return_jdbc = execute_sql(object, type);
		close_connection();
		return return_jdbc;
	}
	
	//执行自定义sql
	@SuppressWarnings("unused")
	public static jdbc_return jdbc_sql_map(String type,String customize_sql) {
		open_connection();
		sql=customize_sql;
		jdbc_return return_jdbc = execute_sql_map(type);
		close_connection();
		return return_jdbc;
	}

	public static jdbc_return jdbc_total(Object object, String type,
			jdbc_configuration configuration) {
		// 1.创建连接并打开连接
		open_connection();
		// 2.查询数据和总条数
		parsing_sql(object, type,configuration);
		jdbc_return return_jdbc = execute_sql(object, type);
		parsing_sql(object, jdbc_type_count,configuration);
		
		return_jdbc.setCount(execute_sql(object, jdbc_type_count).getCount());
		// 3.返回并关闭连接
		close_connection();
		return return_jdbc;
	}

	// 1.创建和打开连接
	private static void open_connection() {
		try {
			// 1.加载jdbc连接
			Class.forName(jdbc_class);
			// 2.写入连接属性
			connection = DriverManager.getConnection(jdbc_url, jdbc_user,
					jdbc_password);
			// 3.打开连接
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接失败");
			close_connection();
		}
	}

	// 2.解析sql
	private static void parsing_sql(Object object, String type) {
		try {
			if (type == jdbc_type_insert_or_update) {
				// 1.获取id是否存在如果存在就修改
				Method met_id = object.getClass().getMethod("getId");
				String value_id = (String) met_id.invoke(object);
				// 2.获取表名
				String table_name = object.getClass().getName();
				table_name = table_name.substring(
						table_name.lastIndexOf(".") + 1, table_name.length());
				// 3.进入执行修改的方法
				if (value_id != null && !value_id.equals("")) {
					sql = "update " + table_name + " set ";

					// 4.获取所有的属性
					Field[] fields = object.getClass().getDeclaredFields();
					for (int i = 0; i < fields.length; i++) {// 遍历
						String name = fields[i].getName();
						// 5.获取这个属性的值
						String name_vo = name.substring(0, 1).toUpperCase()
								+ name.substring(1);
						Method met = object.getClass().getMethod(
								"get" + name_vo);
						String value = (String) met.invoke(object);
						// 6.编辑sql
						if (value != null && !value.equals("")) {
							sql += "   " + name + "='" + value + "' ,";
						}
					}
					// 7.添加上id
					sql = sql.substring(0, sql.length() - 1);
					sql += " where id='" + value_id + "'";
				}

				// 3.新增方法
				if (value_id == null || value_id.equals("")) {
					// 4.创建新增的列和新增的值
					sql = "insert into " + table_name + " (";
					String column_name = "";
					String column_value = "";
					// 5.获取所有的属性
					Field[] fields = object.getClass().getDeclaredFields();
					for (int i = 0; i < fields.length; i++) {// 遍历
						String name = fields[i].getName();
						// 6.获取这个属性的值
						String name_vo = name.substring(0, 1).toUpperCase()
								+ name.substring(1);
						Method met = object.getClass().getMethod(
								"get" + name_vo);
						String value = (String) met.invoke(object);
						// 7.如果有值那么新增的列和值+1
						if (value != null && !value.equals("")) {
							column_name += name + ",";
							column_value += "'" + value + "',";
						}
					}
					// 8.删除最后的,并拼接
					column_name = column_name.substring(0,
							column_name.length() - 1);
					column_value = column_value.substring(0,
							column_value.length() - 1);
					sql += column_name + ")values(";
					sql += column_value + ")";
				}

			}

			if (type == jdbc_type_select) {
				sql = "select * from ";// 创建sql
				// 1.根据类名获取表名
				String table_name = object.getClass().getName();
				table_name = table_name.substring(
						table_name.lastIndexOf(".") + 1, table_name.length());
				sql += table_name + " where 1=1";

				// 2.获取所有的属性
				Field[] fields = object.getClass().getDeclaredFields();
				for (Field fie : fields) {
					String name = fie.getName();
					// 3.获取属性上的注解
					zw_sql_type annotation = fie
							.getAnnotation(zw_sql_type.class);
					// 4.获取这个属性的值
					String name_vo = name.substring(0, 1).toUpperCase()
							+ name.substring(1);
					Method met = object.getClass().getMethod("get" + name_vo);
					String value = (String) met.invoke(object);

					// 如果有值就加入到sql中
					if (value != null && !value.equals("")) {

						// 然后根据注解来确定加载那一段sql 默认是等于
						if (annotation == null) {
							sql += " and " + name + "='" + value + "'";
						}
						if (annotation != null) {
							String annotation_value = annotation.value();// 找到RequestMapping的注入value值
							if (annotation_value.equals("like")) {
								sql += " and " + name + " like '%" + value
										+ "%'";
							}
						}
					}
				}

			}
			
			if (type == jdbc_type_remove) {
				String table_name = object.getClass().getName();
				table_name = table_name.substring(
						table_name.lastIndexOf(".") + 1, table_name.length());
				sql = "delete from " + table_name + "  where  1=1 ";
				// 5.获取所有的属性
				Field[] fields = object.getClass().getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {// 遍历
					String name = fields[i].getName();
					// 6.获取这个属性的值
					String name_vo = name.substring(0, 1).toUpperCase()
							+ name.substring(1);
					Method met = object.getClass().getMethod(
							"get" + name_vo);
					String value = (String) met.invoke(object);
					// 7.如果有值那么新增的列和值+1
					if (value != null && !value.equals("")) {
						sql +=" and "+ name +"='"+ value+"'";
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 2.5解析sql
	private static void parsing_sql(Object object, String type,
			jdbc_configuration configuration) {
		try {
			if (type == jdbc_type_insert_or_update) {
				// 1.获取id是否存在如果存在就修改
				Method met_id = object.getClass().getMethod("getId");
				String value_id = (String) met_id.invoke(object);
				// 2.获取表名
				String table_name = object.getClass().getName();
				table_name = table_name.substring(
						table_name.lastIndexOf(".") + 1, table_name.length());
				// 3.进入执行修改的方法
				if (value_id != null && !value_id.equals("")) {
					sql = "update " + table_name + " set ";

					// 4.获取所有的属性
					Field[] fields = object.getClass().getDeclaredFields();
					for (int i = 0; i < fields.length; i++) {// 遍历
						String name = fields[i].getName();
						// 5.获取这个属性的值
						String name_vo = name.substring(0, 1).toUpperCase()
								+ name.substring(1);
						Method met = object.getClass().getMethod(
								"get" + name_vo);
						String value = (String) met.invoke(object);
						// 6.编辑sql
						if (value != null && !value.equals("")) {
							sql += "   " + name + "='" + value + "' ,";
						}
					}
					// 7.添加上id
					sql = sql.substring(0, sql.length() - 1);
					sql += " where id='" + value_id + "'";
				}

				// 3.新增方法
				if (value_id == null || value_id.equals("")) {
					// 4.创建新增的列和新增的值
					sql = "insert into " + table_name + " (";
					String column_name = "";
					String column_value = "";
					// 5.获取所有的属性
					Field[] fields = object.getClass().getDeclaredFields();
					for (int i = 0; i < fields.length; i++) {// 遍历
						String name = fields[i].getName();
						// 6.获取这个属性的值
						String name_vo = name.substring(0, 1).toUpperCase()
								+ name.substring(1);
						Method met = object.getClass().getMethod(
								"get" + name_vo);
						String value = (String) met.invoke(object);
						// 7.如果有值那么新增的列和值+1
						if (value != null && !value.equals("")) {
							column_name += name + ",";
							column_value += "'" + value + "',";
						}
					}
					// 8.删除最后的,并拼接
					column_name = column_name.substring(0,
							column_name.length() - 1);
					column_value = column_value.substring(0,
							column_value.length() - 1);
					sql += column_name + ")values(";
					sql += column_value + ")";
				}

			}

			if (type == jdbc_type_select) {
				sql = "select * from ";// 创建sql
				// 1.根据类名获取表名
				String table_name = object.getClass().getName();
				table_name = table_name.substring(
						table_name.lastIndexOf(".") + 1, table_name.length());
				sql += table_name + " where  1=1    ";

				// 2.获取所有的属性
				Field[] fields = object.getClass().getDeclaredFields();
				for (Field fie : fields) {
					String name = fie.getName();
					// 3.获取属性上的注解
					zw_sql_type annotation = fie
							.getAnnotation(zw_sql_type.class);
					// 4.获取这个属性的值
					String name_vo = name.substring(0, 1).toUpperCase()
							+ name.substring(1);
					Method met = object.getClass().getMethod("get" + name_vo);
					String value = (String) met.invoke(object);

					// 如果有值就加入到sql中
					if (value != null && !value.equals("")) {

						// 然后根据注解来确定加载那一段sql 默认是等于
						if (annotation == null) {
							sql += " and " + name + "='" + value + "'";
						}
						if (annotation != null) {
							String annotation_value = annotation.value();// 找到RequestMapping的注入value值
							if (annotation_value.equals("like")) {
								sql += " and " + name + " like '%" + value
										+ "%'";
							}
						}
					}
				}
					sql+=" limit  "
							+( (Integer.parseInt(configuration.getCurrent()) - 1)*Integer.parseInt(configuration.getPage() ))+ ","
							+ configuration.getPage() + " ";
			}

			if (type == jdbc_type_count) {
				sql = "select count(*) as count from ";// 创建sql
				// 1.根据类名获取表名
				String table_name = object.getClass().getName();
				table_name = table_name.substring(
						table_name.lastIndexOf(".") + 1, table_name.length());
				sql += table_name + " where  1=1  ";
				// 2.获取所有的属性
				Field[] fields = object.getClass().getDeclaredFields();
				for (Field fie : fields) {
					String name = fie.getName();
					// 3.获取属性上的注解
					zw_sql_type annotation = fie
							.getAnnotation(zw_sql_type.class);
					// 4.获取这个属性的值
					String name_vo = name.substring(0, 1).toUpperCase()
							+ name.substring(1);
					Method met = object.getClass().getMethod("get" + name_vo);
					String value = (String) met.invoke(object);

					// 如果有值就加入到sql中
					if (value != null && !value.equals("")) {

						// 然后根据注解来确定加载那一段sql 默认是等于
						if (annotation == null) {
							sql += " and " + name + "='" + value + "'";
						}
						if (annotation != null) {
							String annotation_value = annotation.value();// 找到RequestMapping的注入value值
							if (annotation_value.equals("like")) {
								sql += " and " + name + " like '%" + value
										+ "%'";
							}
						}
					}
				}
			}
			
			
			if (type == jdbc_type_remove) {
				String table_name = object.getClass().getName();
				table_name = table_name.substring(
						table_name.lastIndexOf(".") + 1, table_name.length());
			 sql = "delete from " + table_name + "  where  1=1 ";
				// 5.获取所有的属性
				Field[] fields = object.getClass().getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {// 遍历
					String name = fields[i].getName();
					// 6.获取这个属性的值
					String name_vo = name.substring(0, 1).toUpperCase()
							+ name.substring(1);
					Method met = object.getClass().getMethod(
							"get" + name_vo);
					String value = (String) met.invoke(object);
					// 7.如果有值那么新增的列和值+1
					if (value != null && !value.equals("")) {
						sql +=" and "+ name +"='"+ value+"'";
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 3.执行sql
	private static jdbc_return execute_sql(Object object, String type) {
		int count = 0;
		List<Object> list = new ArrayList<Object>();
		try {

			if (type == jdbc_type_select) {
				// 1.创建查询的list
				// 2.执行这个语句并循环结果集
				ResultSet result_set = statement.executeQuery(sql);
				while (result_set.next()) {
					// 3.获取所有列名并创建 实体类对应的对象
					ResultSetMetaData rsmd = result_set.getMetaData();
					Object object_data = object.getClass().newInstance();
					// 4.循环每一列
					for (int i = 1; i <= rsmd.getColumnCount(); i++) {
						if (result_set.getString(i) != null) {
							String name = rsmd.getColumnName(i);
							name = name.substring(0, 1).toUpperCase()
									+ name.substring(1);
							// 5.根据列名搜索属性
							Method met = object_data.getClass().getMethod(
									"set" + name, String.class);
							// 6.将属性传入到sql中
							met.invoke(object_data, result_set.getString(i));
						}
					}
					// 7.添加到list中
					list.add(object_data);

				}
			}

			if (type == jdbc_type_insert_or_update) {
				count = statement.executeUpdate(sql);
			}
			if (type == jdbc_type_count) {
				ResultSet result_set = statement.executeQuery(sql);
				while (result_set.next()) {
					count=result_set.getInt(1);
				}
			}
			
			if (type == jdbc_type_remove) {
				count = statement.executeUpdate(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("执行语句失败以下是sql：");
 		//System.out.println(sql);
			close_connection();
		}
		return new jdbc_return(list, count);
	}
	
	
	// 3.执行sql
		private static jdbc_return execute_sql_map(String type) {
			int count = 0;
			List<Object> list = new ArrayList<Object>();
			
			try {

				if (type == jdbc_type_select) {
					// 1.创建查询的list
					// 2.执行这个语句并循环结果集
					ResultSet result_set = statement.executeQuery(sql);
					while (result_set.next()) {
						// 3.获取所有列名并创建 实体类对应的对象
						ResultSetMetaData rsmd = result_set.getMetaData();
						Map<String,String> map=new HashMap<String, String>();
						// 4.循环每一列
						for (int i = 1; i <= rsmd.getColumnCount(); i++) {
							if (result_set.getString(i) != null) {
								String name = rsmd.getColumnName(i);
								name = name.substring(0, 1).toUpperCase()
										+ name.substring(1);
								map.put(name.toLowerCase(), result_set.getString(i));
							}
						}
						// 7.添加到list中
						list.add(map);
					}
				}

				if (type == jdbc_type_insert_or_update) {
					count = statement.executeUpdate(sql);
				}
				if (type == jdbc_type_count) {
					ResultSet result_set = statement.executeQuery(sql);
					while (result_set.next()) {
						count=result_set.getInt(1);
					}
				}
				
				if (type == jdbc_type_remove) {
					count = statement.executeUpdate(sql);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("执行语句失败以下是sql：");
	 		//System.out.println(sql);
				close_connection();
			}
			return new jdbc_return(list, count);
		}

	// 4.关闭连接
	private static void close_connection() {
		try {
			connection.close();
			statement.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("关闭连接失败");
		}
	}

	public static void main(String[] args) {
		// listObject转listT
		// List<Object> system_list=get_select(vo);
		// Object ob = (Object) system_list;
		// List<system> system_list_1 = (List<system>)ob ;

		// system vo=new system();
		// vo.setId("1");
		// vo.setName("1");
		// List<Object> system_list=get_select(vo);
		// Object ob = (Object) system_list;
		// List<system> system_list_1 = (List<system>)ob ;
		// for(int i=0;i<system_list_1.size();i++){
		// system object_system=(system) system_list_1.get(i);
		// System.out.println(object_system.getId());
		// System.out.println(object_system.getName());
		// System.out.println(object_system.getRemarks());
		// System.out.println(object_system.getUpdate_by());
		// }

		// vo.setName("4");
		// vo.setValue("4");
		// get_insert_update(vo);
		// // System.out.println(get_insert_update_sql(vo));
		// // System.out.println(get_select_sql(vo));
		// system vo1=new system();
		// vo1.setId("2");
		// vo1.setName("1");
		// vo1.setValue("1");
		// System.out.println(get_insert_update(vo1));
	}

}
