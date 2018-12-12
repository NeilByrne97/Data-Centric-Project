package com.student.Controllers;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.student.DAOs.CourseDAO;
import com.student.Models.CourseModel;
import com.student.Models.StudentModel;

@ManagedBean
@ApplicationScoped
public class CourseController {

	private CourseDAO dao;
	private List<CourseModel> courses;
	
	public List<CourseModel> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseModel> courses) {
		this.courses = courses;
	}

	public CourseDAO getDao() {
		return dao;
	}

	public void setDao(CourseDAO dao) {
		this.dao = dao;
	}

	public CourseController() throws Exception {
		super();
		dao = new CourseDAO();
	}

	public void loadCourses() throws SQLException{
		System.out.println("load courses");
		this.courses = dao.loadCourses();
	}
	
	public String addCourse(CourseModel c){
		System.out.println("Howdy"+c.toString());
		dao.insertCourse(c);

		return "list_courses.xhtml";
	}
	
	public String deleteCourse(CourseModel c){
		System.out.println("Deleting "+c.toString());
		dao.deleteCourse(c);

		return "list_courses.xhtml";
	}
	

}
