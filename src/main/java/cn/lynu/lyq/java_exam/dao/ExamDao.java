package cn.lynu.lyq.java_exam.dao;

import java.util.List;

import cn.lynu.lyq.java_exam.entity.Exam;

public interface ExamDao {

	List<Exam> findAll();
	Exam findById(int id);
	void save(Exam e);
	void update(Exam e);
	void delete(Exam e);
	void composeExamRandom(Exam exam, int choiceNum,int blankFillingNum, int judgeNum);

}