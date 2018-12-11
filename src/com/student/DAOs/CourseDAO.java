package com.student.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.student.Models.CourseModel;


public class CourseDAO {
	 private DataSource mysqlDS;

	 public CourseDAO() throws Exception {
	     Context context = new InitialContext();
	     String jndiName = "java:comp/env/emp"; // name in web.xml & context.xml
	     mysqlDS = (DataSource) context.lookup(jndiName);
	  }
	 
	 public List<CourseModel> loadCourses() throws SQLException{
		 System.out.println("DAO works");
		 Connection conn = mysqlDS.getConnection();
		 Statement myStmt = conn.createStatement();
		 String query = "select * from course";
		 ResultSet rs = myStmt.executeQuery(query);
		 
		 List<CourseModel> courses = new ArrayList<>();
		 
		 while( rs.next() ) {
			String cid = rs.getString("cID");
			String cname = rs.getString("cName");
			int duration = rs.getInt("duration");

			courses.add(new CourseModel(cid, cname, duration));
		}
		 
		 return courses;
	 }

}
