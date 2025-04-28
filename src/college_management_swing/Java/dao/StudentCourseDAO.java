/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package college_management_swing.Java.dao;

import college_management_swing.Java.model.Course;
import college_management_swing.Java.model.Student;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface StudentCourseDAO {
    public void addStudentCourse(Integer sID, Integer cID);
    public List<Course> getCourses(Integer id);
    
}
