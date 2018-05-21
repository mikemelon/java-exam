package cn.lynu.lyq.java_exam.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.lynu.lyq.java_exam.dao.GradeDao;
import cn.lynu.lyq.java_exam.entity.Grade;

@Component("gradeDao")
@Transactional

public class GradeDaoImpl implements GradeDao {
	@Resource
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Grade findById(int id) {
		Grade g = sessionFactory.getCurrentSession().get(Grade.class, id);
		return g;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Grade> findAll() {
		Query q=sessionFactory.getCurrentSession().createQuery("from Grade");
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Grade> findByName(String name){
		Query q=sessionFactory.getCurrentSession().createQuery("from Grade where name=?");
		q.setString(0, name);
		return q.list();
	}
	
	@Override
	public void save(Grade g){
		sessionFactory.getCurrentSession().save(g);
	}
	
	@Override
	public void update(Grade g){
		sessionFactory.getCurrentSession().update(g);
	}
	
	@Override
	public void delete(Grade g){
		sessionFactory.getCurrentSession().delete(g);
	}
}
