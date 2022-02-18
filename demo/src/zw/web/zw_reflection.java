package zw.web;

import java.lang.reflect.Method;

//反射类
public class zw_reflection {
public static Object obtain_param(String name,String value,Object object) throws   Exception{
		
//		Class<?> object = class_object.getClass();// 传递的是哪个子类的对象，class1就是该子类的类类型
        // 获取类的名称
//        System.out.println("类的名称是：" + class_object.getName());
        /**
         * 成员变量也是对象 java.lang.reflect.Field Field类封装了关于成员变量的操作
         * getFields()方法获取的是所有的public的成员变量的信息
         * getDeclaredFields获取的是该类自己声明的成员变量的信息
         */
        // Field[] fs = class1.getFields();
//        Field[] field = object.getDeclaredFields();
     
//        for (Field fie : field) {
//            // 得到成员变量的类型的类类型
////            Class<?> filedType = field.getType();
////            String typeName = filedType.getName();
//            String fieldName = fie.getName();
//            System.out.println(" " + fieldName);
//        }
//        Method[] method = class_object.getDeclaredMethods();
//      for (Method met : method) {
//   	 String fieldName = met.getName();
//   	  
////   	 met.invoke(class_object.newInstance(),"aaa");
//   	 System.out.println(" " + fieldName);
//   }
//         object = class_object.newInstance();//创建一个对象来获取值
//		class_object=object.getClass();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);//首字母大写
        Method met = object.getClass().getMethod("set"+name, String.class);//只能执行set String方法
        met.invoke(object,value);//执行这个对象的这个方法
        
        return object;
	}
}
