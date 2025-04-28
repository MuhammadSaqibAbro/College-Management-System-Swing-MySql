package college_management_swing.Java.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import college_management_swing.Java.dao.DepartmentDAO;
import college_management_swing.Java.dbConnection.DbConnection;
import college_management_swing.Java.model.Department;

public class DepartmentDAOImpl implements DepartmentDAO {
	private final String SELECT_QUERY = "SELECT * FROM department";
	private final String INSERT_QUERY = "INSERT INTO department( dept_name,dept_code) VALUES ( ?,?)";
	private final String DELETE_QUERY = "DELETE FROM department WHERE dept_id=?";
	private final String UPDATE_QUERY = "UPDATE Department SET dept_name=?, dept_code= ? WHERE dept_id=?";
	private final String SELECT_BY_ID_QUERY = "SELECT dept_id,dept_name,dept_code FROM department WHERE dept_id= ?";

	@Override
	public List<Department> getAllDepartment() {
		Connection con = DbConnection.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(SELECT_QUERY);
			ResultSet rst = ps.executeQuery();

			List<Department> deptList = new ArrayList<>();

			while (rst.next()) {
				Department dept = new Department();
				dept.setId(rst.getInt("dept_id"));
				dept.setName(rst.getString("dept_name"));
                                dept.setCode(rst.getString("dept_code"));
				deptList.add(dept);
			}
			return deptList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Department getDepartmentById(Integer id) {
		Connection con = DbConnection.getConnection();
		Department d = new Department();
		try {
			PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_QUERY);
			ps.setInt(1, id);
			ResultSet rst = ps.executeQuery();
			while (rst.next()) {
				d.setId(rst.getInt("dept_id"));
				d.setName(rst.getString("dept_name"));
                                d.setCode(rst.getString("dept_code"));
			}
			return d;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
 public Department getDepartmentByName(String name) {
        Connection con = DbConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("select * from department where dept_name= ?");
            ps.setString(1, name);
            ResultSet rst = ps.executeQuery();
            // do not call a query in a loop
            while (rst.next()) {
                Department d = new Department();
                d.setId(rst.getInt("dept_id"));
                d.setName(rst.getString("dept_name"));
               
                return d;
            }
            // con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

	@Override
	public void getDepartmentInfo(Integer id) {
		Connection con = DbConnection.getConnection();
		// Department d = new Department();
		try {
			PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_QUERY);
			ps.setInt(1, id);
			ResultSet rst = ps.executeQuery();
			while (rst.next()) {
				System.out.println("ID : " + rst.getInt("dept_id"));
				System.out.println("Name : " + rst.getString("dept_name"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean addDepartment(Department department) {
		Connection con = DbConnection.getConnection();
                boolean success=false;
		try {
			PreparedStatement ps = con.prepareStatement(INSERT_QUERY);
//			ps.setInt(1, department.getId());
			ps.setString(1, department.getName());
                        ps.setString(2,department.getCode());
			ps.execute();
			success = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
                       success=false;
			e.printStackTrace();
                        
		}
                return success;

	}

	@Override
	public boolean deleteDepartment(Integer id) {
		Connection con = DbConnection.getConnection();
                boolean success;
		try {
			PreparedStatement ps = con.prepareStatement(DELETE_QUERY);
			ps.setInt(1, id);
			ps.execute();
			success=true;
		} catch (SQLException e) {
			success=false;
			e.printStackTrace();
		}
            return success;
	}

	@Override
	public boolean updateDepartment(Department department) {
		Connection con = DbConnection.getConnection();
                boolean success;
		try {
			PreparedStatement ps = con.prepareStatement(UPDATE_QUERY);
			ps.setString(1, department.getName());
                        ps.setString(2, department.getCode());
			ps.setInt(3, department.getId());
			ps.execute();
			success=true;
		} catch (SQLException e) {
			success=false;
			e.printStackTrace();
		}
                return success;
	}

}
