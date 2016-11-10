package cn.lynu.lyq.java_exam.dao;

import java.util.List;

import cn.lynu.lyq.java_exam.entity.Student;
import cn.lynu.lyq.java_exam.entity.StudentExamScore;

public interface StudentExamScoreDao {
	
	List<StudentExamScore> findByStudent(Student s);
	
	StudentExamScore findById(int id);
	List<StudentExamScore> findAll();
	void save(StudentExamScore ses);
	void update(StudentExamScore ses);
	void delete(StudentExamScore ses);
}