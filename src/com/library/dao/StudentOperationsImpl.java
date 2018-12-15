package com.library.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.library.vo.Book;
import com.library.vo.BookIssue;


@Path("/student")
public class StudentOperationsImpl implements IStudentOperations{
	PreparedStatement searchPS=null;
	PreparedStatement viewHistoryPS=null;
	@Override
	@GET
	@Path("/search/{bookname}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Book> searchBook(@PathParam("bookname") String str) {
	    
	//	int k=Integer.parseInt(str);
		System.out.println("Received string "+str);
		System.out.println("came");
		List<Book> book=new ArrayList<>(); 
		searchPS=new DbUtil().getPreparedStatement(DbConstants.searchBookCommand);
		System.out.println("hello");
		 try {
			searchPS.setString(1,str);
			ResultSet RS=searchPS.executeQuery();
			
			while(RS.next()) 
			{
				boolean flag=checkForAvailability(RS.getInt(1));
				System.out.println(RS.getInt(1)+RS.getString(2)+RS.getString(3)+RS.getString(4)+flag);
				book.add(new Book(RS.getInt(1),RS.getString(2),RS.getString(3),RS.getString(4),flag));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return book;
	}

	@Override
	@GET
	@Path("/request/{sid}/{bid}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public boolean requestIssue(@PathParam("sid") int sid,@PathParam("bid")  int bid) {
		// TODO Auto-generated method stub
		System.out.println("Requested Issue ");
		LibraryOperationsImpl obj=new LibraryOperationsImpl();
		return obj.acceptRequest(bid, sid);
	
	}

	@Override
	@GET
	@Path("/issued/{sid}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<BookIssue> viewHistory(@PathParam("sid") int sid) {
		// TODO Auto-generated method stub
		
		String str="SELECT * FROM BOOK_ISSUE WHERE SID=?";
		List<BookIssue> book=new ArrayList<>(); 
		viewHistoryPS=new DbUtil().getPreparedStatement(str);
		
		
		try {
			viewHistoryPS.setInt(1, sid);
			ResultSet rs=viewHistoryPS.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getDate(4));
				book.add(new BookIssue(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4).toString(),rs.getDate(5).toString(),rs.getInt(6)));
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

}
