package com.library.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.library.vo.AllBooks;
import com.library.vo.Book;
import com.library.vo.BookIssue;
@Path("/library")
public class LibraryOperationsImpl implements ILibraryOperations{

	PreparedStatement viewAllBooksPS=null;
	PreparedStatement addBookPS=null;
	PreparedStatement deleteBookPS=null;
	PreparedStatement listBookPS=null;
	PreparedStatement searchPS=null;
	PreparedStatement bookInsertPS=null;
	PreparedStatement updateBookPS=null;
	PreparedStatement getBookPS=null;
	
	@Override
	@POST
	@Path("/add")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public boolean addBook(Book b) {
		// TODO Auto-generated method stub
		addBookPS=new DbUtil().getPreparedStatement(DbConstants.bookInsertCommand);
		
		
		try {
			addBookPS.setInt(1, b.getBid());
			addBookPS.setString(2, b.getAuthor());
			addBookPS.setString(3, b.getBookName());
			addBookPS.setString(4, b.getPublisher());
			int t=addBookPS.executeUpdate();
			if(t>=1) {
				return true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	

	@Override
	@DELETE
	@Path("/delete/{bid}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public boolean deleteBook(@PathParam("bid") int bid) {
		// TODO Auto-generated method stub
		int k = bid;
		System.out.println("Inside Delete "+k);
		String str="delete from book_table where bookid="+bid;
		
		deleteBookPS=new DbUtil().getPreparedStatement(str);
		try {
			int t=deleteBookPS.executeUpdate();
			if(t>=1)
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean acceptRequest(int bid, int sid) {
		// TODO Auto-generated method stub
		if(checkForAvailability(bid)) 
		{
			bookInsertPS=new DbUtil().getPreparedStatement(DbConstants.bookIssueInsertCommand);
			
			  long millis=System.currentTimeMillis();
			  Date date=new Date(millis);
			  
			 
			  Date dueDate = new Date(date.getTime() + 31l*24l*60l*60l*1000l);	  
			
			
			try {
				bookInsertPS.setInt(1, sid);
				bookInsertPS.setInt(2, bid);
				bookInsertPS.setDate(3, date);
				bookInsertPS.setDate(4, dueDate);
				bookInsertPS.setInt(5, 1);
				
				int t=bookInsertPS.executeUpdate();
				System.out.println(t);
				if(t>=1)
				{
					return true;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	
	@Override
	@GET
	@Path("/bissue")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<BookIssue> viewBookIssue() {
		// TODO Auto-generated method stub
		String str="select * from book_issue where flag=1";
		List<BookIssue> book=new ArrayList<>(); 
		listBookPS=new DbUtil().getPreparedStatement(str);
		try {
			ResultSet rs=listBookPS.executeQuery();
			while(rs.next())
			{
				book.add(new BookIssue(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4).toString(),rs.getDate(5).toString(),rs.getInt(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}

	@Override
	@GET
	@Path("/viewallbooks")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<AllBooks> viewAllBooks() {
		
		// TODO Auto-generated method stub
		String str="Select count(*),bookname from book_table group by bookname";
		List<AllBooks> book=new ArrayList<>(); 
		viewAllBooksPS=new DbUtil().getPreparedStatement(str);
		
		
		try {
			ResultSet rs=viewAllBooksPS.executeQuery();
			while(rs.next())
			{
				book.add(new AllBooks(rs.getInt(1),rs.getString(2)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
		
	}

	@Override
	public boolean checkForAvailability(int bid) {
		// TODO Auto-generated method stub
				String str="SELECT count(*) from book_issue where bookid="+bid+" and flag=1";
				
				searchPS=new DbUtil().getPreparedStatement(str);
				
				 try {
					
					ResultSet RS=searchPS.executeQuery();
					if(RS.next()) 
					{
						int flagf=RS.getInt(1);
						if(flagf==0) return true;
						else return false;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return true;
	}

	

	@Override
	@PUT
	@Path("/updatebook")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public boolean updateBook(Book b) {
		// TODO Auto-generated method stub
		updateBookPS=new DbUtil().getPreparedStatement(DbConstants.bookUpdateCommand);
		
		
		try {
			updateBookPS.setInt(4, b.getBid());
			updateBookPS.setString(2, b.getAuthor());
			updateBookPS.setString(1, b.getBookName());
			updateBookPS.setString(3, b.getPublisher());
			int t=updateBookPS.executeUpdate();
			if(t>=1) {
				return true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}



	@Override
	@GET
	@Path("/check/{bid}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Book getBook(@PathParam("bid") int bid) {
		// TODO Auto-generated method stub
	
		System.out.println("came");
		Book book=null; 
		getBookPS=new DbUtil().getPreparedStatement(DbConstants.searchBookByIdCommand);
		
		 try {
			getBookPS.setInt(1, bid);
			ResultSet RS=getBookPS.executeQuery();
			
			while(RS.next()) 
			{
				System.out.println(RS.getInt(1));
				book=new Book(RS.getInt(1),RS.getString(2),RS.getString(3),RS.getString(4),true);
				System.out.println(book.getBid());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return book;
		
	
	}
	
}
