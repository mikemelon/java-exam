package cn.lynu.lyq.java_exam.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.lynu.lyq.java_exam.dao.ExamQuestionAnswerDao;
import cn.lynu.lyq.java_exam.entity.Exam;
import cn.lynu.lyq.java_exam.entity.ExamQuestion;
import cn.lynu.lyq.java_exam.entity.ExamQuestionAnswer;
import cn.lynu.lyq.java_exam.entity.Student;
@Component("examQuestionAnswerDao")
@Transactional
public class ExamQuestionAnswerDaoImpl implements ExamQuestionAnswerDao {
	@Resource
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public ExamQuestionAnswer findById(int id) {
		ExamQuestionAnswer eqa = sessionFactory.getCurrentSession().get(ExamQuestionAnswer.class, id);
		return eqa;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<ExamQuestionAnswer> findAll(){
		Query q=sessionFactory.getCurrentSession().createQuery("from ExamQuestionAnswer");
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<ExamQuestionAnswer> findByExamQuestion(ExamQuestion eq){
		Query q=sessionFactory.getCurrentSession().createQuery("from ExamQuestionAnswer where examQuestion=?");
		q.setParameter(0, eq);
		return q.list();
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public ExamQuestionAnswer findByStudentAndExamQuestion(Student student, ExamQuestion examQuestion){
		Query q=sessionFactory.getCurrentSession().createQuery("from ExamQuestionAnswer where student=? and examQuestion=?");
		q.setParameter(0, student);
		q.setParameter(1, examQuestion);
		return (ExamQuestionAnswer)q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<ExamQuestionAnswer> findByStudentAndExam(Exam exam,Student student){
		Query q=sessionFactory.getCurrentSession().createQuery("from ExamQuestionAnswer eqa where eqa.student=? and eqa.examQuestion.exam=?");
		q.setParameter(0, student);
		q.setParameter(1, exam);
		return q.list();
	}
	
	@Override
	public void save(ExamQuestionAnswer eqa){
		sessionFactory.getCurrentSession().save(eqa);
	}
	
	@Override
	public void update(ExamQuestionAnswer eqa){
		sessionFactory.getCurrentSession().update(eqa);
	}
	
	@Override
	public void delete(ExamQuestionAnswer eqa){
		sessionFactory.getCurrentSession().delete(eqa);
	}

}
