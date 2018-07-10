package cn.lynu.lyq.java_exam.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.lynu.lyq.java_exam.common.ExamPhase;
import cn.lynu.lyq.java_exam.common.QuestionType;
import cn.lynu.lyq.java_exam.dao.BankQuestionDao;
import cn.lynu.lyq.java_exam.dao.ExamDao;
import cn.lynu.lyq.java_exam.dao.ExamQuestionDao;
import cn.lynu.lyq.java_exam.dao.ExamQuestionAnswerDao;
import cn.lynu.lyq.java_exam.dao.GradeDao;
import cn.lynu.lyq.java_exam.dao.StudentDao;
import cn.lynu.lyq.java_exam.dao.StudentExamScoreDao;
import cn.lynu.lyq.java_exam.entity.BankChoiceQuestion;
import cn.lynu.lyq.java_exam.entity.Exam;
import cn.lynu.lyq.java_exam.entity.ExamQuestion;
import cn.lynu.lyq.java_exam.entity.ExamQuestionAnswer;
import cn.lynu.lyq.java_exam.entity.Student;
import cn.lynu.lyq.java_exam.entity.StudentExamScore;
/**
 * 2018-6-25日测验（25道随机选择题）后，有三道题题目答案更改，因此分数也更改了！
 * @author Administrator
 *
 */
public class ScoreRecaculateTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		GradeDao gradeDao = (GradeDao) context.getBean("gradeDao");
		StudentDao studentDao = (StudentDao) context.getBean("studentDao");
		ExamDao examDao = (ExamDao) context.getBean("examDao");
		ExamQuestionDao examQuestionDao = (ExamQuestionDao) context.getBean("examQuestionDao");
		ExamQuestionAnswerDao examQuestionAnswerDao = (ExamQuestionAnswerDao) context.getBean("examQuestionAnswerDao");
		StudentExamScoreDao studentExamScoreDao = (StudentExamScoreDao) context.getBean("studentExamScoreDao");
		BankQuestionDao bankQuestionDao = (BankQuestionDao) context.getBean("bankQuestionDao");
		List<Student> stuList = studentDao.findByGrade(gradeDao.findById(7));
		int cnt = 0;
		for (Student s1 : stuList) {
//			List<Exam> examList = examDao.findByStudentNameAndExamNameAlike(s1.getName(), "2018年6月8日面向对象随机乱序25道选择题");
			List<Exam> examList = examDao.findByStudentNameAndExamNameAlike(s1.getName(), "2018年6月22日面向对象选择题乱序");
			if (examList.size() != 1) {
				System.out.println(s1.getName() + ":该学生缺考，或考试次数不对！" + examList.size());
				continue;
			}
			List<StudentExamScore> sesList = studentExamScoreDao.findByStudentAndExam(s1, examList.get(0));
			if (sesList.size() != 1) {
				System.out.println("系统出错。" + s1.getName() + " 该次考试(exam_id=" + examList.get(0).getId() + ")的成绩多于一个。");
				continue;
			}
			StudentExamScore ses = sesList.get(0);
			System.out.println((++cnt) + "->" + s1.getName() + ":" + ses.getScore() + ":" + ses.getExamPhase());
			
			if(ses.getExamPhase().equals(ExamPhase.FINAL_SCORED.getChineseName())){
				Exam exam = examList.get(0);
				List<ExamQuestion> eqList = examQuestionDao.findByExam(exam);
				int qCnt = 0, correctQCnt=0;
				for (ExamQuestion eq : eqList) {
					if (eq.getQuestionType() == QuestionType.CHOICE.ordinal()) {
						BankChoiceQuestion choiceQ = bankQuestionDao.findChoiceById(eq.getBankChoiceQuestion().getId());
						String realAnswer = choiceQ.getAnswer();
						ExamQuestionAnswer eqa = examQuestionAnswerDao.findByStudentAndExamQuestion(s1, eq);
						String stuAnswer = eqa.getAnswer();
						stuAnswer = stuAnswer!=null?stuAnswer:"";
						qCnt++;
						if(realAnswer.equals(stuAnswer)) correctQCnt++;
					}
				}
				System.out.println("qCnt="+qCnt+"/correctQCnt="+correctQCnt);
				int realScore = correctQCnt*5;
				if(ses.getScore()==realScore){
					System.out.println("木问题！！！！！！！！！！！！！！");
				}else{
					System.out.println("原分数="+ses.getScore()+"/真实分数="+realScore+", +"+(realScore-ses.getScore()));
					ses.setScore(realScore);
					studentExamScoreDao.update(ses);
				}
			}
			
		}
		context.close();
	}

}
