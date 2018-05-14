package cn.lynu.lyq.java_exam.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.lynu.lyq.java_exam.dao.StudentExamScoreDao;
import cn.lynu.lyq.java_exam.entity.Exam;
import cn.lynu.lyq.java_exam.entity.Student;
import cn.lynu.lyq.java_exam.entity.StudentExamScore;

@Component("studentExamScoreDao")
@Transactional

public class StudentExamScoreDaoImpl implements StudentExamScoreDao {
	@Resource
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<StudentExamScore> findByStudent(Student s) {
		Query q=sessionFactory.getCurrentSession().createQuery("from StudentExamScore where student=?");
		q.setParameter(0, s);
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<StudentExamScore> findByExamPhase(String examPhase) {
		Query q=sessionFactory.getCurrentSession().createQuery("from StudentExamScore where examPhase=?");
		q.setParameter(0, examPhase);
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<StudentExamScore> findByClassAndExamNameAndExamPhase(String className, String examName,
			String examPhase) {
		Query q=sessionFactory.getCurrentSession().createQuery("from StudentExamScore ses where ses.student.grade.name=? and ses.exam.name like ? and ses.examPhase=?");
		q.setParameter(0, className);
		q.setParameter(1, examName+"%");
		q.setParameter(2, examPhase);
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<StudentExamScore> findByStudentAndExamPhase(Student s, String examPhase) {
		Query q=sessionFactory.getCurrentSession().createQuery("from StudentExamScore where student=? and examPhase=?");
		q.setParameter(0, s);
		q.setParameter(1, examPhase);
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<StudentExamScore> findByStudentAndExam(Student s, Exam e) {
		Query q=sessionFactory.getCurrentSession().createQuery("from StudentExamScore where student=? and exam=?");
		q.setParameter(0, s);
		q.setParameter(1, e);
		return q.list();
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public StudentExamScore findById(int id) {
		StudentExamScore ses = sessionFactory.getCurrentSession().get(StudentExamScore.class, id);
		return ses;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<StudentExamScore> findAll() {
		Query q=sessionFactory.getCurrentSession().createQuery("from StudentExamScore");
		return q.list();
	}
	
	@Override
	public void save(StudentExamScore ses){
		sessionFactory.getCurrentSession().save(ses);
	}
	
	@Override
	public void update(StudentExamScore ses){
		sessionFactory.getCurrentSession().update(ses);
	}
	
	@Override
	public void delete(StudentExamScore ses){
		sessionFactory.getCurrentSession().delete(ses);
	}

}
