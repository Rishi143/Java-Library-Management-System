package com.library.dao;

public interface DbConstants {
	public static final String bookInsertCommand="INSERT INTO book_table VALUES(?,?,?,?)";
	public static final String bookUpdateCommand="Update book_table set bookname=?,author=?,publisher=? where bookid=?";
	public static final String searchBookCommand="SELECT * FROM book_table where bookname=?";
	public static final String librarianInsertCommand="INSERT INTO librarian VALUES(?,?)";
	public static final String searchBookByIdCommand="SELECT * FROM book_table where bookid=?";
	
	public static final String studentInsertCommand="INSERT INTO student VALUES(?,?)";
	
	public static final String bookIssueInsertCommand="INSERT INTO book_issue(sid,bookid,issuedate,duedate,flag) VALUES(?,?,?,?,?)";
	
	public static final String url="jdbc:mysql://localhost/library?verifyServerCertificate=false&useSSL=true";
	public static final String username="root";
	public static final String password="P@ssw0rd";
	public static final String driverClassName="com.mysql.jdbc.Driver";
}
