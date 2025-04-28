package college_management_swing.Java.dao;

import java.util.List;

import college_management_swing.Java.model.Course;

public interface CourseDAO {
	public boolean addCourse(Course course);
	public Course getCourseById(Integer id);
	public boolean updateCourse(Course course);
	public boolean deleteCourse(Integer id);
	
	public List<Course> getAllCourses();
        public Course getCourseIDByName(String name);
	
}
