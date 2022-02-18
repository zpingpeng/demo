package zw.jdbc;

import java.util.List;

//jdbc返回的数据
public class jdbc_return {
 private List<Object> list;
 private int count;
public List<Object> getList() {
	return list;
}
public void setList(List<Object> list) {
	this.list = list;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public jdbc_return(List<Object> list, int count) {
	super();
	this.list = list;
	this.count = count;
}

public jdbc_return() {
}
 
}
