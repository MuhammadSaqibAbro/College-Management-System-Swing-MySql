package college_management_swing.Java.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import college_management_swing.Java.dao.StudentDAO;
import college_management_swing.Java.dbConnection.DbConnection;
import college_management_swing.Java.model.Department;
import college_management_swing.Java.model.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private final String INSERT_QUERY = "INSERT INTO student (st_name,st_age,st_email,dept_id,roll_no) VALUES (?, ?,?,?,?)";
    private final String UPDATE_QUERY = "UPDATE student  SET  st_name = ? , st_age = ?, st_email = ?,dept_id=?,roll_no=? "
            + "WHERE st_id=?";
    private final String DELETE_QUERY = "DELETE FROM student WHERE st_id=?";
    private final String SELECT_BY_ID_QUERY = "SELECT st_id,st_name, st_age, st_email,roll_no,dept_id FROM student WHERE st_id= ?";
    private final String SELECT_QUERY_WITH_JOIN_FOR_SINGLE_RECORD = "SELECT s.st_id,s.st_name, s.st_age, s.st_email,s.roll_no,s.dept_id,d.dept_name"
            + " FROM student s inner join department d on s.dept_id=d.dept_id where s.st_id=?";
    private final String SELECT_QUERY_WITH_JOIN = "SELECT s.st_id,s.st_name, s.st_age, s.st_email,s.roll_no,d.dept_name,s.dept_id\r\n"
            + "FROM student s inner join department d on s.dept_id=d.dept_id;";
    private final String SELECT_QUERY_COURSE = "SELECT s.st_id,sc.c_id,c.c_name,sc.marks FROM student s inner join student_course sc on s.st_id=sc.st_id "
            + "inner join course c on c.c_id=sc.c_id where  s.st_id=?";

    @Override
    public List<Student> getAllStudents() {
        Connection con = DbConnection.getConnection();

        List<Student> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_QUERY_WITH_JOIN);

            ResultSet rst = ps.executeQuery();
//                        while(rst.next()){
//                            System.out.println(rst.getInt("st_id"));
//                            System.out.println(rst.getString("st_name"));
//                           
//                        }

            while (rst.next()) {
                Student st = new Student();
                st.setId(rst.getInt("st_id"));
                st.setName(rst.getString("st_name"));
                st.setAge(rst.getInt("st_age"));
                st.setEmail(rst.getString("st_email"));
                st.setRollno(rst.getString("roll_no"));
                Department d = new Department();
                d.setId(rst.getInt("dept_id"));
                d.setName(rst.getString("dept_name"));
                st.setDepartment(d);
                list.add(st);
            }

            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean addStudent(Student student) {
        Connection con = DbConnection.getConnection();
        boolean success;
        try {
            PreparedStatement ps = con.prepareStatement(INSERT_QUERY);
//			ps.setInt(1, student.getId());
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getEmail());

            ps.setInt(4, student.getDepartment().getId());
            ps.setString(5, student.getRollno());
            ps.execute();
            success = true;

        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean updateStudent(Student student) {
        Connection con = DbConnection.getConnection();
        boolean success = false;
        try {
            PreparedStatement ps = con.prepareStatement(UPDATE_QUERY);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getEmail());

            ps.setInt(4, student.getDepartment().getId());
            ps.setString(5, student.getRollno());
            ps.setInt(6, student.getId());
            ps.execute();
            success = true;
            System.out.println("Student Updated.");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return success;
    }
//
//
//

    @Override
    public Student getStudentById(Integer id) {
        Connection con = DbConnection.getConnection();
        Student s = new Student();
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_QUERY_WITH_JOIN_FOR_SINGLE_RECORD);

            ps.setInt(1, id);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                s.setId(id);
                s.setName(rst.getString("st_name"));
                s.setAge(rst.getInt("st_age"));
                s.setEmail(rst.getString("st_email"));
                s.setRollno(rst.getString("roll_no"));
                Department d = new Department();
                d.setId(rst.getInt("dept_id"));
                d.setName(rst.getString("dept_name"));
                s.setDepartment(d);

            }
            return s;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteStudent(Student student) {
        Connection con = DbConnection.getConnection();
        boolean success = false;
        try {
            PreparedStatement ps = con.prepareStatement(DELETE_QUERY);
            ps.setInt(1, student.getId());
            ps.execute();
            success = true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public Student getStudentIDByName(String name) {
        Connection con = DbConnection.getConnection();
        Student s = new Student();
        try {
            PreparedStatement ps = con.prepareStatement("select st_id from student where st_name=?");

            ps.setString(1, name);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                s.setId(rst.getInt("st_id"));

            }
            return s;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
