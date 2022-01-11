package cn.lynu.lyq.java_exam.actions;

import cn.lynu.lyq.java_exam.dao.StudentDao;
import cn.lynu.lyq.java_exam.entity.Student;
import com.opensymphony.xwork2.ActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("userList")
@Scope("prototype")
public class UserListAction extends ActionSupport {
    private static final int PAGE_SIZE = 10;
    public int pageIndex;
    private int totalPage;
    public int studentId;
    private final static Logger logger = LoggerFactory.getLogger(BlankQuestionSearchAction.class);
    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Student> studentList;
    @Resource
    private StudentDao studentDao;

    @Override
    public String execute() throws Exception {
        int totalCnt = studentDao.countAllStudent();
        totalPage = (totalCnt % PAGE_SIZE > 0) ? (totalCnt / PAGE_SIZE + 1) : (totalCnt / PAGE_SIZE);
        studentList = studentDao.findStudentWithPage(pageIndex, PAGE_SIZE);
        return SUCCESS;
    }
    public String delUser() throws Exception {
        studentList = studentDao.findStudentWithPage(1, PAGE_SIZE);
        studentDao.delete(studentDao.findById(studentId));
//        studentDao.deleteById(studentId);
        logger.info("删除用户");
        return SUCCESS;
    }
}
