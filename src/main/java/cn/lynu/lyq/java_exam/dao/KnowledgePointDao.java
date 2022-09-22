package cn.lynu.lyq.java_exam.dao;

import cn.lynu.lyq.java_exam.entity.KnowledgePoint;

import java.util.List;

public interface KnowledgePointDao {
    List <KnowledgePoint> findElemByName(String name);
    List <KnowledgePoint> findStudentWithPage(int pageIndex, int pageSize);
    int countAll();
    KnowledgePoint findById(int id);
    void save(KnowledgePoint knowledgePoint);
    void delete(KnowledgePoint knowledgePoint);
    void update(KnowledgePoint knowledgePoint);
}
