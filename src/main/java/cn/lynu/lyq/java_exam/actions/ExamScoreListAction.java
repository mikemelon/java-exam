package cn.lynu.lyq.java_exam.actions;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.dao.StudentExamScoreDao;
import cn.lynu.lyq.java_exam.entity.StudentExamScore;

@Component("examScoreList")
@Scope("prototype")
public class ExamScoreListAction extends ActionSupport {

	private static final long serialVersionUID = 4675761085855839420L;
	
	private List<StudentExamScore> examScoreList;
	
	@Resource
	private StudentExamScoreDao studentExamScoreDao;

	public List<StudentExamScore> getExamScoreList() {
		return examScoreList;
	}
	public void setExamScoreList(List<StudentExamScore> examScoreList) {
		this.examScoreList = examScoreList;
	}

	@Override
	public String execute() throws Exception {
		ActionContext ctx =ActionContext.getContext();
		if(ctx.getSession().containsKey("USER_INFO")){
//			Student stu=(Student)ctx.getSession().get("USER_INFO");
			examScoreList = studentExamScoreDao.findByExamPhase("最终得分");
			
			return SUCCESS;
		}else{
			this.addActionError("您还没有登录，请登录后再点击进入");
			return ERROR;
		}
	}
	
}
