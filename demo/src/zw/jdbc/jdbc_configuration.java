package zw.jdbc;

public class jdbc_configuration {
	private String page;//美页数
	private String total;//总页数
	private String current;//当前页
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	public jdbc_configuration(){
		
	}
	
	public jdbc_configuration(String page, String total, String current) {
		super();
		this.page = page;
		this.total = total;
		this.current = current;
	}
	
	
	
	
	
	
	
}
