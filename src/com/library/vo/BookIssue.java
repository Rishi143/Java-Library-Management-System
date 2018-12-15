package com.library.vo;

import java.sql.Date;

public class BookIssue {
	private int issueid;
	private int sid;
	private int bid;
	private  String issueDate;
	private  String dueDate;
	private int flag;
	public int getIssueid() {
		return issueid;
	}
	public void setIssueid(int issueid) {
		this.issueid = issueid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public BookIssue(int issueid, int sid, int bid, String issueDate, String dueDate, int flag) {
		super();
		this.issueid = issueid;
		this.sid = sid;
		this.bid = bid;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.flag = flag;
	}
	public BookIssue() {
		super();
		// TODO Auto-generated constructor stub
	}
}
