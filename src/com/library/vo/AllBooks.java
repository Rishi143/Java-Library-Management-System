package com.library.vo;

public class AllBooks {
	private int count;
	private String bname;
	public AllBooks() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AllBooks(int count, String bname) {
		super();
		this.count = count;
		this.bname = bname;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	
}
