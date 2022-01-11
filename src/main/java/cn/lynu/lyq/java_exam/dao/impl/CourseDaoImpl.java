package cn.lynu.lyq.java_exam.dao.impl;

import cn.lynu.lyq.java_exam.dao.CourseDao;
import cn.lynu.lyq.java_exam.entity.BankChoiceQuestion;
import cn.lynu.lyq.java_exam.entity.Course;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component("CourseDao")
@Transactional
public class CourseDaoImpl implements CourseDao {

    private final static Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);
    @Resource
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Course findById(int id) {
        return sessionFactory.getCurrentSession().get(Course.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Course> findAll() {
        Query q=sessionFactory.getCurrentSession().createQuery("from Course");
        return q.list();
    }

    @Override
    public int countAllCourse() {
        Object obj = sessionFactory.getCurrentSession().createQuery("select count(*) from Course").uniqueResult();
        return ((Number)obj).intValue();
    }


    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Course> findByName(String name) {
        logger.debug("name="+name);
        Query q=sessionFactory.getCurrentSession().createQuery("from Course where name=?0");
        q.setString("0", name);
        return q.list();
    }

    @Override
    public List<Course> findAllWithPage(int pageIndex, int pageSize) {
        Query q=sessionFactory.getCurrentSession().createQuery("from Course");
        int firstResult = pageIndex*pageSize;
        q.setFirstResult(firstResult);
        q.setMaxResults(pageSize);
        @SuppressWarnings("unchecked")
        List<Course> list=(List<Course>)q.list();
        return list;
    }

    @Override
    public void save(Course c) {
        sessionFactory.getCurrentSession().save(c);
    }

    @Override
    public void update(Course c) {
        sessionFactory.getCurrentSession().update(c);
    }

    @Override
    public void delete(Course c) {
        sessionFactory.getCurrentSession().delete(c);
    }
}
