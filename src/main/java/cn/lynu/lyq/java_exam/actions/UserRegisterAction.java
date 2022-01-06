package cn.lynu.lyq.java_exam.actions;

import cn.lynu.lyq.java_exam.dao.GradeDao;
import cn.lynu.lyq.java_exam.dao.StudentDao;
import cn.lynu.lyq.java_exam.entity.Grade;
import cn.lynu.lyq.java_exam.entity.Student;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.print.DocFlavor;
import java.util.List;

@Component("userRegister")
@Scope("prototype")
public class UserRegisterAction extends ActionSupport {
    public String RegisterNo;
    public String Password;
    public Boolean Sex;
    public String name;
    public int role;
    public void setSex(String value){
        this.Sex = Boolean.parseBoolean(value);
    }
//    public String getRegisterNo(){
//        return RegisterNo;
//    }
//    public String getPassword(){
//        return Password;
//    }
//    public void setRegisterNo(String value){
//        this.RegisterNo = value;
//    }
//    public void setPassword(String value){
//        this.Password = value;
//    }
    private final static Logger logger = LoggerFactory.getLogger(UserRegisterAction.class);
    @Resource
    private StudentDao studentDao;

    @Resource
    private GradeDao gradeDao;

    @Override
    public String execute() throws Exception {
//        this.studentDao.save(new Student());
        logger.info(name);
        logger.info(Password);
        logger.info(RegisterNo);
        List<Grade> gradeList=gradeDao.findAll();
        Grade grade = gradeList.get(0);
        studentDao.save(new Student(name, RegisterNo, Sex, Password, role, grade));
        return SUCCESS;
    }
}
