package cn.lynu.lyq.java_exam.actions;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.dao.ExamDao;
import cn.lynu.lyq.java_exam.entity.Exam;

@Component("examListShow")
@Scope("prototype")
public class ExamListShowAction extends ActionSupport {

	private static final long serialVersionUID = 4675761085855839420L;
	@Resource
	private ExamDao examDao;
	
	private List<Exam> examList;
	
	public List<Exam> getExamList() {
		return examList;
	}
	
	public void setExamList(List<Exam> examList) {
		this.examList = examList;
	}

	@Override
	public String execute() throws Exception {
		ActionContext ctx =ActionContext.getContext();
		if(ctx.getSession().containsKey("USER_INFO")){
			examList = examDao.findAll();
			return SUCCESS;
		}else{
			this.addActionError("您还没有登录，请登录后再点击进入");
			return ERROR;
		}
	}
}
