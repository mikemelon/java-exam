package cn.lynu.lyq.java_exam.dao;

import java.util.List;

import cn.lynu.lyq.java_exam.entity.Exam;
import cn.lynu.lyq.java_exam.entity.ExamQuestionAnswer;
import cn.lynu.lyq.java_exam.entity.Student;

public interface ExamQuestionAnswerDao {
	
	List<ExamQuestionAnswer> findAll();
//	List<ExamQuestionAnswer> findForExam(Exam exam);
	
	//针对对每种题库中的题型搜索
//	List<ExamQuestionAnswer> findForBankChoiceQuestion(BankChoiceQuestion q);
//	List<ExamQuestionAnswer> findForBankBlankFillingQuestion(BankBlankFillingQuestion q);
//	List<ExamQuestionAnswer> findForBankJudgeQuestion(BankJudgeQuestion q);
	
	//针对某个学生的对某次考试的搜索，相当于该学生的一份答卷
	List<ExamQuestionAnswer> findByStudentAndExam(Exam exam,Student student);
	
	void save(ExamQuestionAnswer eqa);
	void update(ExamQuestionAnswer eqa);
	void delete(ExamQuestionAnswer eqa);

}