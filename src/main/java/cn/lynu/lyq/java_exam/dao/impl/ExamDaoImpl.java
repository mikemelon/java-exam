package cn.lynu.lyq.java_exam.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.lynu.lyq.java_exam.dao.BankQuestionDao;
import cn.lynu.lyq.java_exam.dao.ExamDao;
import cn.lynu.lyq.java_exam.dao.ExamQuestionDao;
import cn.lynu.lyq.java_exam.entity.BankBlankFillingQuestion;
import cn.lynu.lyq.java_exam.entity.BankChoiceQuestion;
import cn.lynu.lyq.java_exam.entity.BankJudgeQuestion;
import cn.lynu.lyq.java_exam.entity.Exam;
import cn.lynu.lyq.java_exam.entity.ExamQuestion;

@Component("examDao")
@Transactional
public class ExamDaoImpl implements ExamDao {
	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private BankQuestionDao bankQuestionDao;
	@Resource
	private ExamQuestionDao examQuestionDao;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Exam> findAll(){
		Query q=sessionFactory.getCurrentSession().createQuery("from Exam");
		return q.list();
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Exam findById(int id) {
		Exam e = sessionFactory.getCurrentSession().get(Exam.class, id);
		return e;
	}
	
	@Override
	public void save(Exam e){
		sessionFactory.getCurrentSession().save(e);
	}
	
	@Override
	public void update(Exam e){
		sessionFactory.getCurrentSession().update(e);
	}
	
	@Override
	public void delete(Exam e){
		sessionFactory.getCurrentSession().delete(e);
	}
	
	@Override
	public void composeExamRandom(Exam exam, int choiceNum,int blankFillingNum, int judgeNum){
		List<BankChoiceQuestion> listChoice = bankQuestionDao.findAllChoice();
		List<BankBlankFillingQuestion> listBlankFilling = bankQuestionDao.findAllBlankFilling();
		List<BankJudgeQuestion> listJudge = bankQuestionDao.findAllJudge();
		
		List<BankChoiceQuestion> listChoiceExtracted = extractRandomQuestions(listChoice,choiceNum);
		List<BankBlankFillingQuestion> listBlankFillingExtracted = extractRandomQuestions(listBlankFilling,blankFillingNum);
		List<BankJudgeQuestion> listJudgeExtracted = extractRandomQuestions(listJudge,judgeNum);
		
		System.out.println(listChoiceExtracted);
		System.out.println(listBlankFillingExtracted);
		System.out.println(listJudgeExtracted);
		
		for(BankChoiceQuestion q:listChoiceExtracted){
			examQuestionDao.save(new ExamQuestion(exam,q));
		}
		for(BankBlankFillingQuestion q:listBlankFillingExtracted){
			examQuestionDao.save(new ExamQuestion(exam,q));
		}
		for(BankJudgeQuestion q:listJudgeExtracted){
			examQuestionDao.save(new ExamQuestion(exam,q));
		}
	}
	
	/*
	 * 从某类型（填空、选择、判断）题目list中随机抽取num个不重复的题
	 */
	private static <T> List<T> extractRandomQuestions(List<T> list,int num){
		int szOriginal = list.size();
		List<T> listExtracted = new ArrayList<>();
		if(szOriginal >= num){
			Random random = new Random();
			for(int i=0; i<num; i++){
				T q=list.get(random.nextInt(szOriginal-i));
				listExtracted.add(q);
				list.remove(q);
			}
		}else{
			System.out.println("抽取的题目数量:"+num+"，超过了题库中的该题型["
								+list.get(0).getClass().getSimpleName()+"]的题目数:"
								+szOriginal+"，该题型暂不抽题");
		}
		return listExtracted;
	}

	@Override
	public void examCreateWithQuestions(Exam exam, List<BankChoiceQuestion> choiceList,
			List<BankBlankFillingQuestion> blankList, List<BankJudgeQuestion> judgeList) {
		this.save(exam);
		
		if(choiceList!=null){
			for(BankChoiceQuestion bq:choiceList){
				ExamQuestion eq=new ExamQuestion(exam,bq);
				examQuestionDao.save(eq);
			}
		}
		if(blankList!=null){
			for(BankBlankFillingQuestion bq:blankList){
				ExamQuestion eq=new ExamQuestion(exam,bq);
				examQuestionDao.save(eq);
			}
		}
		if(judgeList!=null){
			for(BankJudgeQuestion bq:judgeList){
				ExamQuestion eq=new ExamQuestion(exam,bq);
				examQuestionDao.save(eq);
			}
		}
		
	}

}
