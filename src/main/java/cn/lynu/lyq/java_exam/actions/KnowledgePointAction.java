package cn.lynu.lyq.java_exam.actions;

import cn.lynu.lyq.java_exam.dao.KnowledgePointDao;
import cn.lynu.lyq.java_exam.entity.KnowledgePoint;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("KnowledgePoint")
@Scope("prototype")
@Slf4j
@Data
public class KnowledgePointAction extends ActionSupport {
    @Resource
    private KnowledgePointDao knowledgePointDao;
    private static final int PAGE_SIZE = 10;
    public int pageIndex;
    private String name;
    private int totalPage;
    private int pointId;
    private List<KnowledgePoint> knowledgePoints;

    @Override
    public String execute() throws Exception {
        int totalCnt = knowledgePointDao.countAll();
        totalPage = (totalCnt % PAGE_SIZE > 0) ? (totalCnt / PAGE_SIZE + 1) : (totalCnt / PAGE_SIZE);
        knowledgePoints = knowledgePointDao.findStudentWithPage(pageIndex, PAGE_SIZE);
        return SUCCESS;
    }

    public String delete() throws Exception{
        KnowledgePoint knowledgePoint = knowledgePointDao.findById(pointId);
        knowledgePointDao.delete(knowledgePoint);
        return SUCCESS;
    }

    public String update() throws Exception {
        KnowledgePoint knowledgePoint = knowledgePointDao.findById(pointId);
        knowledgePoint.setName(name);
        knowledgePointDao.update(knowledgePoint);
        return SUCCESS;
    }

    public String create() throws Exception {
        knowledgePointDao.save(new KnowledgePoint(name));
        return SUCCESS;
    }

    public String updatePage() throws Exception {
        KnowledgePoint point = knowledgePointDao.findById(pointId);
        name = point.getName();
        pointId = pointId;
        return SUCCESS;
    }
}
