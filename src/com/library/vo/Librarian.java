package com.library.vo;

public class Librarian {
	private int lid;
	private String lname;
	public Librarian() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Librarian(int lid, String lname) {
		super();
		this.lid = lid;
		this.lname = lname;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	
}
