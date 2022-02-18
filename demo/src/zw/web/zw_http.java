package zw.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class zw_http {
	
	public static Map<String,String> get_parameter_map(HttpServletRequest request,HttpServletResponse response,Map<String,String> return_map){
		 try {
			 String method=request.getMethod();
				if (method.equals("POST")) {
					// type
					BufferedReader br =new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
					StringBuffer sb = new StringBuffer("");
					String line;
				
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
					br.close();
				 Map<String, String> map=getStringToMap(sb.toString());
				 
				 for(Map.Entry<String, String> entry : map.entrySet()){
					    String mapKey = entry.getKey();
					    String mapValue = entry.getValue();
					    if(mapKey.substring(0,1).equals("{")){
					    	mapKey=mapKey.substring(1);
					    }
					    if(mapValue.substring(mapValue.length()-1,mapValue.length()).equals("}")){
					    	mapValue=mapValue.substring(0,mapValue.length()-1);
					    }
						mapKey=mapKey.substring(1,mapKey.length()-1);
						mapValue=mapValue.substring(1,mapValue.length()-1);
//					    if(mapKey.substring(beginIndex, endIndex)){
//					    	mapKey
//					    }
						return_map.put(mapKey, mapValue);
					}
				 
					// Map parseObject = JSON.parseObject(paramJson, Map.class);
					// params.putAll(parseObject);
				}
				if (method.equals("GET")) {
				Enumeration<String> request_parameter_name=request.getParameterNames();//枚举获取�?有参�?
				while (request_parameter_name.hasMoreElements()){
					String name=request_parameter_name.nextElement();
					return_map.put(name, request.getParameter(name));
			      }
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("获取参数失败");
			}
		 return return_map;
	 }
	
	
	
	
 public static Object get_parameter(HttpServletRequest request,HttpServletResponse response,Object object){
	 try {
		 String method=request.getMethod();
			if (method.equals("POST")) {
				// type
				BufferedReader br =new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
				StringBuffer sb = new StringBuffer("");
				String line;
			
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
			 Map<String, String> map=getStringToMap(sb.toString());
			 
			 for(Map.Entry<String, String> entry : map.entrySet()){
				    String mapKey = entry.getKey();
				    String mapValue = entry.getValue();
				    if(mapKey.substring(0,1).equals("{")){
				    	mapKey=mapKey.substring(1);
				    }
				    if(mapValue.substring(mapValue.length()-1,mapValue.length()).equals("}")){
				    	mapValue=mapValue.substring(0,mapValue.length()-1);
				    }
					mapKey=mapKey.substring(1,mapKey.length()-1);
					mapValue=mapValue.substring(1,mapValue.length()-1);
//				    if(mapKey.substring(beginIndex, endIndex)){
//				    	mapKey
//				    }
					object=  zw_reflection.obtain_param(mapKey,mapValue,object);
				}
			 
				// Map parseObject = JSON.parseObject(paramJson, Map.class);
				// params.putAll(parseObject);
			}
			if (method.equals("GET")) {
			Enumeration<String> request_parameter_name=request.getParameterNames();//枚举获取�?有参�?
			while (request_parameter_name.hasMoreElements()){
				String name=request_parameter_name.nextElement();
				object=  zw_reflection.obtain_param(name,request.getParameter(name),object);
		      }
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取参数失败");
		}
	 return object;
 }
 
 
 public static Map<String,String> getStringToMap(String str){
     //根据逗号截取字符串数�?
     String[] str1 = str.split(",");
     //创建Map对象
     Map<String,String> map = new HashMap<>();
     //循环加入map集合
     for (int i = 0; i < str1.length; i++) {
         //根据":"截取字符串数�?
         String[] str2 = str1[i].split(":");
         //str2[0]为KEY,str2[1]为�??
         map.put(str2[0],str2[1]);
     }
     return map;
 }
}
