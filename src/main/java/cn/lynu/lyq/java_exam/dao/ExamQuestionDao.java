package cn.lynu.lyq.java_exam.dao;

import java.util.List;

import cn.lynu.lyq.java_exam.entity.Exam;
import cn.lynu.lyq.java_exam.entity.ExamQuestion;

public interface ExamQuestionDao {
	ExamQuestion findById(int id);
	List<ExamQuestion> findAll();
	List<ExamQuestion> findByExam(Exam e);
	void save(ExamQuestion eq);
	void update(ExamQuestion eq);
	void delete(ExamQuestion eq);

}