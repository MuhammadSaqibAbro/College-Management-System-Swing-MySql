package college_management_swing.Java.dao;

import college_management_swing.Java.model.Student;
import java.util.List;

public interface StudentDAO {
	public boolean addStudent(Student student);
	public boolean updateStudent(Student student);
//	
	public Student getStudentById(Integer id);
	
	public boolean deleteStudent(Student student);
    public List<Student> getAllStudents();
public Student getStudentIDByName(String name);
	
	
}
