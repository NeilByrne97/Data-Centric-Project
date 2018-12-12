package com.student.Controllers;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.student.DAOs.StudentDAO;
import com.student.Models.StudentModel;

@ManagedBean
@ApplicationScoped
public class StudentController {

	private StudentDAO dao;
	private List<StudentModel> students;

	public List<StudentModel> getStudents() {
		return students;
	}

	public void setStudents(List<StudentModel> students) {
		this.students = students;
	}

	public StudentDAO getDao() {
		return dao;
	}

	public void setDao(StudentDAO dao) {
		this.dao = dao;
	}

	public StudentController() throws Exception {
		super();
		dao = new StudentDAO();
	}

	public void loadStudents() throws SQLException {
		System.out.println("load students");
		this.students = dao.loadStudents();
	}
	
	public String addStudent(StudentModel s){
		System.out.println("Howdy"+s.toString());
		
		try{
			dao.insertStudent(s);
		}catch(SQLException e){
			FacesMessage message = new FacesMessage("Error");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}//	catch
		return "list_courses.xhtml";
	}


}
