package com.library.dao;

import java.util.List;

import com.library.vo.Book;
import com.library.vo.BookIssue;

public interface IStudentOperations {
	public List<Book> searchBook(String str);
	public boolean requestIssue(int sid,int bid);
	public List<BookIssue> viewHistory(int sid);
	public boolean checkForAvailability(int bid);
}
