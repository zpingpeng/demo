package zw.web;

import java.util.List;

public class web_return {
	private String message;
	private List<Object> data;
	private int count;
	private String line;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public web_return(String message, List<Object> data, int count) {
		super();
		this.message = message;
		this.data = data;
		this.count = count;
	}
	
	public web_return(String message, List<Object> data, int count,String line) {
		super();
		this.message = message;
		this.data = data;
		this.count = count;
		this.line = line;
	}
	
	public web_return() {
		super();
	}
	public web_return(String message) {
		super();
		this.message = message;
	}
	public web_return(int count) {
		super();
		this.count = count;
		if(count>0){
			this.message = "操作成功";
		}
		if(count==0){
			this.message = "操作失败";
		}
		
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	
	
	
	
}
