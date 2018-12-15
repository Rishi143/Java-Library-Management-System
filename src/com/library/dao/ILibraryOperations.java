package com.library.dao;

import java.util.List;

import com.library.vo.AllBooks;
import com.library.vo.Book;
import com.library.vo.BookIssue;

public interface ILibraryOperations {
//	1) Add/Edit/Delete books into the library collection
//	2) Issue a book to a user
//	3) View list of books issued with their due dates
//	4) View entire collection of books along with their quantities in the library collection
	
	
	public boolean addBook(Book b);
	public boolean deleteBook(int bid);
	public boolean acceptRequest(int bid,int sid);
	
	public boolean updateBook(Book b);
	public Book getBook(int bid);
	
	public List<BookIssue> viewBookIssue();
	public List<AllBooks> viewAllBooks();
	public boolean checkForAvailability(int bid);
}
