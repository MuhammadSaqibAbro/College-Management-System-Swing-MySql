package college_management_swing.Java.dao;

import java.util.List;

import college_management_swing.Java.model.Department;

public interface DepartmentDAO {
	public List<Department> getAllDepartment();
	public Department getDepartmentById(Integer id);
	public void getDepartmentInfo(Integer id);
	public boolean addDepartment(Department department);
	public boolean deleteDepartment(Integer id);
	public boolean updateDepartment(Department department);
        public Department getDepartmentByName(String name);
	
}
