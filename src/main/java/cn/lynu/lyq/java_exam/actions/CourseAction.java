package cn.lynu.lyq.java_exam.actions;

import cn.lynu.lyq.java_exam.dao.CourseDao;
import cn.lynu.lyq.java_exam.entity.Course;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("Course")
@Scope("prototype")
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class CourseAction  extends ActionSupport {
    @Resource
    private CourseDao courseDao;
    private List<Course> courses;
    private int totalPage;
    private int courseId;
    private int pageIndex;
    private static final int PAGE_SIZE = 10;
    @Override
    public String execute() throws Exception{
        int totalCnt = courseDao.countAllCourse();
        totalPage = (totalCnt%PAGE_SIZE > 0)?(totalCnt/PAGE_SIZE+1):(totalCnt/PAGE_SIZE);
        courses = courseDao.findAllWithPage(pageIndex,PAGE_SIZE);
        return SUCCESS;
    }
    
    public String delCourse() throws Exception{
        courseDao.delete(courseDao.findById(courseId));
        log.info("删除id为"+courseId+"的课程");
        return SUCCESS;
    }
}
