package com.student.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.student.Models.CourseModel;
import com.student.Models.StudentModel;

public class CourseDAO {
	 private DataSource mysqlDS;

	 public CourseDAO() throws Exception {
	     Context context = new InitialContext();
	     String jndiName = "java:comp/env/emp"; // name in web.xml & context.xml
	     mysqlDS = (DataSource) context.lookup(jndiName);
	  }
	 
	 public List<CourseModel> loadCourses() throws SQLException{
		 System.out.println("CourseDAO works");
		 Connection conn = mysqlDS.getConnection();
		 Statement myStmt = conn.createStatement();
		 String query = "select * from course";
		 ResultSet rs = myStmt.executeQuery(query);
		 
		 List<CourseModel> courses = new ArrayList<>();
		 
		 while(rs.next() ) {
			String cid = rs.getString("cID");
			String cname = rs.getString("cName");
			int duration = rs.getInt("duration");

			courses.add(new CourseModel(cid, cname, duration));
		}
		 
		 return courses;
	 }
	 
	 public void insertCourse(CourseModel c){
			
			String cid = c.getcid();
			String cname = c.getCname();
			int duration = c.getDuration();
		
			PreparedStatement myStmt;
			try {
				Connection conn = mysqlDS.getConnection();
				myStmt = conn.prepareStatement("INSERT into course (cid, cName, duration) VALUES (?, ?, ?)");
				myStmt.setString(1, cid);
				myStmt.setString(2, cname);
				myStmt.setInt(3, duration);
				
				myStmt.executeUpdate();	
			}
			catch (MySQLIntegrityConstraintViolationException e){
				e.printStackTrace();
				System.out.println("Invalid Course ID!");
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	 public void deleteCourse(CourseModel c) {
			Connection conn;
			try {
			conn = mysqlDS.getConnection();
			// sql command that deletes course where course id is entered
			PreparedStatement myStmt = conn.prepareStatement("DELETE from student where cid = ?");
			myStmt.setString(1, c.getcid());
			myStmt.executeUpdate();
			System.out.println("Deleted");

			myStmt.close();// closes statement
			conn.close();// closes connection
			} catch (SQLException e) {
			e.printStackTrace();
			} // catch		
		}

}
