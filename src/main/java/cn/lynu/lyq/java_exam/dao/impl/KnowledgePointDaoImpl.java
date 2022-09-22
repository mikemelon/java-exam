package cn.lynu.lyq.java_exam.dao.impl;

import cn.lynu.lyq.java_exam.dao.KnowledgePointDao;
import cn.lynu.lyq.java_exam.entity.KnowledgePoint;
import cn.lynu.lyq.java_exam.entity.Student;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component("KnowledgePointDao")
@Transactional
@Slf4j
@Data
public class KnowledgePointDaoImpl implements KnowledgePointDao {

    @Resource
    protected SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<KnowledgePoint> findElemByName(String name) {
        Query q=sessionFactory.getCurrentSession().createQuery("from KnowledgePoint where name=?0");
        q.setString("0", name);
        return q.list();
    }

    @Override
    public List<KnowledgePoint> findStudentWithPage(int pageIndex, int pageSize) {
        Query q=sessionFactory.getCurrentSession().createQuery("from KnowledgePoint");
        int firstResult = pageIndex*pageSize;
        q.setFirstResult(firstResult);
        q.setMaxResults(pageSize);
        @SuppressWarnings("unchecked")
        List<KnowledgePoint> list=(List<KnowledgePoint>)q.list();
        return list;
    }

    @Override
    public int countAll() {
        Object obj = sessionFactory.getCurrentSession().createQuery("select count(*) from KnowledgePoint").uniqueResult();
        return ((Number)obj).intValue();
    }

    @Override
    public KnowledgePoint findById(int id) {
        return sessionFactory.getCurrentSession().get(KnowledgePoint.class, id);
    }

    @Override
    public void save(KnowledgePoint knowledgePoint) {
        sessionFactory.getCurrentSession().save(knowledgePoint);
    }

    @Override
    public void delete(KnowledgePoint knowledgePoint) {
        sessionFactory.getCurrentSession().delete(knowledgePoint);
    }

    @Override
    public void update(KnowledgePoint knowledgePoint) {
        sessionFactory.getCurrentSession().update(knowledgePoint);
    }


}
