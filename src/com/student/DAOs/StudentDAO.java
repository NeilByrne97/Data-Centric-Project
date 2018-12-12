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

import com.student.Models.StudentModel;

public class StudentDAO {
	private DataSource mysqlDS;

	public StudentDAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/emp"; // name in web.xml & context.xml
		mysqlDS = (DataSource) context.lookup(jndiName);
	}

	public List<StudentModel> loadStudents() throws SQLException {
		System.out.println("StudentDAO works");
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "select * from student";
		ResultSet rs = myStmt.executeQuery(query);

		List<StudentModel> students = new ArrayList<>();

		while (rs.next()) {
			String sid = rs.getString("sID");
			String cid = rs.getString("cID");
			String name = rs.getString("name");
			String address = rs.getString("address");

			students.add(new StudentModel(sid, cid, name, address));
		}
		return students;
	}

	public void insertStudent(StudentModel s){
		
		String id = s.getCid();
		String name = s.getName();
		String sid = s.getSid();
		String address = s.getAddress();
	
		PreparedStatement myStmt;
		try {
			Connection conn = mysqlDS.getConnection();
			myStmt = conn.prepareStatement("INSERT into student (sid, cID, name, address) VALUES (?, ?, ?, ?)");
			myStmt.setString(1, sid);
			myStmt.setString(2, id);
			myStmt.setString(3, address);
			myStmt.setString(4, name);
			
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

	public void deleteStudent(StudentModel s) {
		System.out.println("Deleting");
		Connection conn;
		try {
		conn = mysqlDS.getConnection();
		// sql command that deletes course where course id is entered
		PreparedStatement myStmt = conn.prepareStatement("DELETE from student where sid = ?");
		myStmt.setString(1, s.getSid());
		myStmt.executeUpdate();
		
		myStmt.close();// closes statement
		conn.close();// closes connection
		} catch (SQLException e) {
		e.printStackTrace();
		} // catch		
	}
}
