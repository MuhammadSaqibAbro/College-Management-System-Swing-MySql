/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package college_management_swing.Java.dao.impl;

import college_management_swing.Java.dao.StudentCourseDAO;
import college_management_swing.Java.dbConnection.DbConnection;
import college_management_swing.Java.model.Course;
import college_management_swing.Java.model.StudentCourse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class StudentCourseDAOImpl implements StudentCourseDAO {

    @Override
    public void addStudentCourse(Integer sID, Integer cID) {
        try {
            System.out.println("Course ID: " + cID);
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into student_course values(?,?)");
            ps.setInt(1, sID);
            ps.setInt(2, cID);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentCourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Course> getCourses(Integer id) {
        List<Course> list = new ArrayList<>();

        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select c.c_name from course c inner join student_course sc on c.c_id=sc.c_id where sc.st_id=? ");
            ps.setInt(1, id);
            ResultSet rst = ps.executeQuery();

            while (rst.next()) {
                Course course = new Course();

                course.setName(rst.getString("c.c_name"));

                list.add(course);

            }

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(StudentCourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
