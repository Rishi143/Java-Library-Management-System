package com.library.vo;

public class Book {
	private int bid;
	private String bookName;
	private String author;
	private String publisher;
	private boolean available;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(int bid, String author, String bookName, String publisher,boolean available) {
		super();
		this.bid = bid;
		this.bookName = bookName;
		this.author = author;
		this.publisher = publisher;
		this.available=available;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
}
