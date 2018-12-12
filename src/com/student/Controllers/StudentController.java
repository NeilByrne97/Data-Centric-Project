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
		System.out.println("Load students");
		this.students = dao.loadStudents();
	}
	
	public String addStudent(StudentModel s){
		System.out.println("Howdy"+s.toString());
		dao.insertStudent(s);

		return "list_students.xhtml";
	}
	
	public String deleteStudent(StudentModel s){
		System.out.println("Deleting"+s.toString());
		dao.deleteStudent(s);

		return "list_students.xhtml";
	}
	
	


}
