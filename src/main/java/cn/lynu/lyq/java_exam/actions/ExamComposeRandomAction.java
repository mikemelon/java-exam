package cn.lynu.lyq.java_exam.actions;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.dao.ExamDao;
import cn.lynu.lyq.java_exam.dao.ExamQuestionDao;
import cn.lynu.lyq.java_exam.dao.ExamStrategyDao;
import cn.lynu.lyq.java_exam.dao.StudentDao;
import cn.lynu.lyq.java_exam.dao.StudentExamScoreDao;
import cn.lynu.lyq.java_exam.entity.Exam;
import cn.lynu.lyq.java_exam.entity.ExamStrategy;
import cn.lynu.lyq.java_exam.entity.Student;
import cn.lynu.lyq.java_exam.entity.StudentExamScore;
@Component("examComposeRandom")
@Scope("prototype")
public class ExamComposeRandomAction extends ActionSupport {

	private static final long serialVersionUID = -9026822432724575083L;
	@Resource
	private StudentDao studentDao;
	@Resource
	private StudentExamScoreDao studentExamScoreDao;
	@Resource
	private ExamDao examDao;
	@Resource
	private ExamQuestionDao examQuestionDao;
	@Resource
	private ExamStrategyDao examStrategyDao;
	
	private List<Student> studentList;

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	@Override
	public String execute() throws Exception {
		ActionContext ctx =ActionContext.getContext();
		if(ctx.getSession().containsKey("USER_INFO")){
			if(ctx.getValueStack().findValue("selectStudentFor")!=null){  //从学生选择action chain过来
				int[] studentIds = (int[])ctx.getSession().get("EXAM_STUDENT_IDS");
				if(studentIds!=null){
					studentList=new ArrayList<>();
					for(int sid:studentIds){
						Student stu = studentDao.findById(sid);
						studentList.add(stu);
					}
				}
			}else{
				ctx.getSession().remove("EXAM_STUDENT_IDS");
			}
			return SUCCESS;
		}else{
			this.addActionError("您还没有登录，请登录后再点击进入");
			return ERROR;
		}
	}
	
	
	public String executeForCreateStudentExam() throws Exception{
		//让画面仍然显示student列表
		ActionContext ctx =ActionContext.getContext();
		int[] studentIds = (int[])ctx.getSession().get("EXAM_STUDENT_IDS");
		
		if(studentIds!=null){
			studentList=new ArrayList<>();
			for(int sid:studentIds){
				Student theStudent = studentDao.findById(sid);
				studentList.add(theStudent);
				
				Exam theExam = new Exam("随机试卷005->"+theStudent.getName(), "随机试卷005->"+theStudent.getName(),1);
				examDao.save(theExam);
				
				int choiceQuestionNum = 5;
				int blankQuestionNum = 5;
				int judgeQuestionNum = 5;
				examDao.composeExamRandom(theExam, choiceQuestionNum, blankQuestionNum, judgeQuestionNum);
				
				ExamStrategy theStrategy = new ExamStrategy(theExam,"随机抽题给分策略005->"+theStudent.getName(),8,6,6,"随机抽题给分策略005->"+theStudent.getName());
				examStrategyDao.save(theStrategy);
				
				StudentExamScore ses=new StudentExamScore(theStudent,theExam,theStrategy,0,"试卷初始化");
				studentExamScoreDao.save(ses);
			}
			this.clearMessages();
			this.addActionMessage("已经为这"+studentIds.length+"个学生生成了试卷, "
								+  ", 请登录后进入试卷列表查看。");
		}
		return SUCCESS;
	}
	
	
}
