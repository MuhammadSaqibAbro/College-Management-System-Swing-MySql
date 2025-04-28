package college_management_swing.Java.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import college_management_swing.Java.dao.CourseDAO;
import college_management_swing.Java.dbConnection.DbConnection;
import college_management_swing.Java.model.Course;
import college_management_swing.Java.model.Student;

public class CourseDAOImpl implements CourseDAO {
	private final String INSERT_QUERY = "INSERT INTO course(c_name,c_code) VALUES ( ?,?)";
	private final String SELECT_BY_ID_QUERY = "SELECT c_id,c_name,c_code FROM course WHERE c_id= ?";
	private final String UPDATE_QUERY = "UPDATE course SET c_name=? ,c_code=? WHERE c_id=?";
	private final String DELETE_QUERY = "DELETE FROM course WHERE c_id=?";
	private final String SELECT_QUERY = "SELECT * FROM course";

	@Override
	public boolean addCourse(Course course) {
             boolean success= false;
		Connection con = DbConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(INSERT_QUERY);
			
			ps.setString(1, course.getName());
                        ps.setString(2, course.getCode());
			ps.execute();
                        success=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                return success;
	}

        
	@Override
	public Course getCourseById(Integer id) {
		Connection con = DbConnection.getConnection();
		Course c = new Course();
		try {
			PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_QUERY);
			ps.setInt(1, id);
			ResultSet rst = ps.executeQuery();
			while (rst.next()) {
				c.setId(rst.getInt("c_id"));
				c.setName(rst.getString("c_name"));
                                c.setCode(rst.getString("c_code"));
			}
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateCourse(Course course) {
             boolean success= false;
		Connection con = DbConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(UPDATE_QUERY);
			ps.setString(1, course.getName());
                        ps.setString(2, course.getCode());
                        ps.setInt(3, course.getId());
			ps.execute();
			success=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                return success;
	}

	@Override
	public boolean deleteCourse(Integer id) {
            boolean success= false;
		Connection con = DbConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(DELETE_QUERY);
			ps.setInt(1, id);
			ps.execute();
			success=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                return success;
	}

	

	@Override
	public List<Course> getAllCourses() {
		Connection con = DbConnection.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(SELECT_QUERY);
			ResultSet rst = ps.executeQuery();

			List<Course> courseList = new ArrayList<>();

			while (rst.next()) {
				Course c = new Course();
				c.setId(rst.getInt("c_id"));
				c.setName(rst.getString("c_name"));
                                c.setCode(rst.getString("c_code"));
				courseList.add(c);
			}
			return courseList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
        
        
        @Override
        public Course getCourseIDByName(String name) {
        Connection con = DbConnection.getConnection();
        Course c = new Course();
        try {
            PreparedStatement ps = con.prepareStatement("select c_id from course where c_name=?");

            ps.setString(1, name);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                c.setId(rst.getInt("c_id"));

            }
            return c;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
