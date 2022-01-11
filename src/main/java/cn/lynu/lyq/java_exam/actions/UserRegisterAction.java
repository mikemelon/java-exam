package cn.lynu.lyq.java_exam.actions;

import cn.lynu.lyq.java_exam.dao.GradeDao;
import cn.lynu.lyq.java_exam.dao.StudentDao;
import cn.lynu.lyq.java_exam.entity.Grade;
import cn.lynu.lyq.java_exam.entity.Student;
import com.opensymphony.xwork2.ActionSupport;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("userRegister")
@Scope("prototype")
public class UserRegisterAction extends ActionSupport {
    public String RegisterNo;
    public String Password;
    public Boolean Sex;
    public String name;
    public List<Grade> gradeList;
    public int role;
    public void setSex(String value){
        this.Sex = Boolean.parseBoolean(value);
    }
    private final static Logger logger = LoggerFactory.getLogger(UserRegisterAction.class);
    @Resource
    private StudentDao studentDao;
    @Resource
    private GradeDao gradeDao;

    @Override
    public String execute() throws Exception {
        gradeList = gradeDao.findAll();
        Grade grade = gradeList.get(0);
        studentDao.save(new Student(name, RegisterNo, Sex, Password, role, grade));
        logger.info("新用户注册");
        this.addActionMessage("用户注册成功，请点击登录");
        return SUCCESS;
    }

    public String registerPage() throws Exception {
        gradeList = gradeDao.findAll();
        return SUCCESS;
    }
}