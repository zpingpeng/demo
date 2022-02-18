package zw.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import zw.static_class;
import zw.annotation.zw_scanner;


@WebFilter(filterName="zw_filter",urlPatterns="/*")
@MultipartConfig
public class zw_intercept implements  Filter{
	private zw_scanner scanner=new zw_scanner();
	@Override
	public void destroy() {
		
	}
	@Override
	public void doFilter(ServletRequest servlet_request, ServletResponse servlet_response,
			FilterChain filter) throws IOException, ServletException {
		
		//1.将请求转换为专门针对于http操作的请求
		  HttpServletRequest request = (HttpServletRequest) servlet_request;//ServletResponse的子接口
		  HttpServletResponse response = (HttpServletResponse) servlet_response;//专门针对于http协议操作
		//2.获取路径
		  String url=request.getRequestURI();
			System.out.println(url);
			//3.重编，判断首页..重定向，改变url，不然地址的登录会出现问题
		if(url.equals("/")){
			response.sendRedirect(request.getContextPath()+url+static_class.web_index);
					return;
		}
		//3.判断是否为静态资源
		if(url.length()>8&&url.substring(1, 7).equals("static")){
			request.getRequestDispatcher(request.getContextPath()+url).forward(request, response);
			return;//重定向后程序会运行所以要返回
		}else{
			//4.如果不是静态资源则查找路径所对因的方法并执行
			String return_string= scanner.getMapping(request.getRequestURI(),servlet_request,servlet_response);
			//5.具体方法在下面。执行方法完毕后获取的数据。写入到页面返回流中
			return_data(response,return_string);
		}
	}
	
	@Override
	public void init(FilterConfig filter) throws ServletException {
		
	}
	
	
	private void return_data(HttpServletResponse response ,String data){
		response.setCharacterEncoding("UTF-8");  
//		解决办法是修改Content-Type为”text/html;charset=utf-8“，然后在前端对数据做一次JSON编码再进行使用。
//				该问题只在IE系列下存在，在FF和Chrome上均正常。
	    response.setContentType("application/json; charset=utf-8");
	    PrintWriter out = null;  
	    try {  
	        out = response.getWriter();  
	        out.append(data);  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (out != null) {  
	            out.close();  
	        }  
	    } 
	}
}
