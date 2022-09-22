package cn.lynu.lyq.java_exam.dao;

import java.io.File;
import java.util.List;

import cn.lynu.lyq.java_exam.entity.Grade;
import cn.lynu.lyq.java_exam.entity.Student;

public interface StudentDao {
	Student findByRegNoAndPassword(String regNo, String password);
	List<Student> findStudentForSearch(String regNo, String name, Boolean gender, String gradeName);
	
	Student findById(int id);
	List<Student> findAll();
	List<Student> findByGrade(Grade g);
	
	void save(Student s);
	void update(Student s);
	void delete(Student s);
	void deleteById(int id);
	int countAllStudent();
	List<Student> findStudentWithPage(int pageIndex, int pageSize);
	
	int importFromTxt(File file);

}