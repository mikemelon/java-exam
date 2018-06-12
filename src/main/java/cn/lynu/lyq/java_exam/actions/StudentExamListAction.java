package cn.lynu.lyq.java_exam.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.common.Constants;
import cn.lynu.lyq.java_exam.common.ExamPhase;
import cn.lynu.lyq.java_exam.common.QuestionType;
import cn.lynu.lyq.java_exam.dao.BankQuestionDao;
import cn.lynu.lyq.java_exam.dao.ExamQuestionDao;
import cn.lynu.lyq.java_exam.dao.StudentExamScoreDao;
import cn.lynu.lyq.java_exam.entity.BankBlankFillingQuestion;
import cn.lynu.lyq.java_exam.entity.BankChoiceQuestion;
import cn.lynu.lyq.java_exam.entity.BankJudgeQuestion;
import cn.lynu.lyq.java_exam.entity.Exam;
import cn.lynu.lyq.java_exam.entity.ExamQuestion;
import cn.lynu.lyq.java_exam.entity.Student;
import cn.lynu.lyq.java_exam.entity.StudentExamScore;
import cn.lynu.lyq.java_exam.utils.PropertyUtils;

@Component("studentExamList")
@Scope("prototype")
public class StudentExamListAction extends ActionSupport {

	private static final long serialVersionUID = 4675761085855839420L;
	private final static Logger logger = LoggerFactory.getLogger(StudentExamListAction.class);
	
	private List<StudentExamScore> studentExamList;//未完成考试列表
	private List<StudentExamScore> studentFinishedExamList; //已完成考试列表
	
	@Resource
	private StudentExamScoreDao studentExamScoreDao;
	@Resource
	private ExamQuestionDao examQuestionDao;
	@Resource 
	private BankQuestionDao bankQuestionDao;
	
	private boolean examDetailAllowed;
	
	public List<StudentExamScore> getStudentExamList() {
		return studentExamList;
	}
	public void setStudentExamList(List<StudentExamScore> studentExamList) {
		this.studentExamList = studentExamList;
	}
	public List<StudentExamScore> getStudentFinishedExamList() {
		return studentFinishedExamList;
	}
	public void setStudentFinishedExamList(List<StudentExamScore> studentFinishedExamList) {
		this.studentFinishedExamList = studentFinishedExamList;
	}
	public boolean isExamDetailAllowed() {
		return examDetailAllowed;
	}
	public void setExamDetailAllowed(boolean examDetailAllowed) {
		this.examDetailAllowed = examDetailAllowed;
	}
	
	@Override
	public String execute() throws Exception {
		ActionContext ctx =ActionContext.getContext();
		logger.info("学生未考和已考列表");
		examDetailAllowed = Boolean.parseBoolean(PropertyUtils.getProperty(Constants.EXAM_DETAIL_ALLOWED));
		if(ctx.getSession().containsKey("USER_INFO")){
			Student stu=(Student)ctx.getSession().get("USER_INFO");
			studentExamList = studentExamScoreDao.findByStudentAndExamPhase(stu,ExamPhase.PAPER_INITIALIZED.getChineseName());
			
			for(StudentExamScore ses:studentExamList){
					Exam exam = ses.getExam();
			        List<ExamQuestion> eqList = examQuestionDao.findByExam(exam);
			        
			        ArrayList<BankChoiceQuestion> choiceList=new ArrayList<>();
			        ArrayList<BankBlankFillingQuestion> blankFillingList = new ArrayList<>();
			        ArrayList<BankJudgeQuestion> judgeList = new ArrayList<>();
			        for(ExamQuestion eq:eqList){
			        	if(eq.getQuestionType()==QuestionType.CHOICE.ordinal()){
			        		choiceList.add(bankQuestionDao.findChoiceById(eq.getBankChoiceQuestion().getId()));
			        	}else if(eq.getQuestionType()==QuestionType.BLANK_FILLING.ordinal()){
			        		blankFillingList.add(bankQuestionDao.findBlankFillingById(eq.getBankBlankFillingQuestion().getId()));
			        	}else if(eq.getQuestionType()==QuestionType.JUDGE.ordinal()){
			        		judgeList.add(bankQuestionDao.findJudgeById(eq.getBankJudgeQuestion().getId()));
			        	}
			        }
			        Map<String,Object> eqListMap = new HashMap<>();
			        eqListMap.put("CHOICE_LIST", choiceList);
			        eqListMap.put("BLANK_LIST", blankFillingList);
			        int blankCnt=0;
			        for(BankBlankFillingQuestion bq:blankFillingList){
			        	blankCnt+=countBlank(bq.getContent());
			        }
			        eqListMap.put("BLANK_CNT", blankCnt);
			        eqListMap.put("JUDGE_LIST", judgeList);
			        ctx.put("EXAM_QUESTION_"+exam.getId(), eqListMap);
			}
			
			studentFinishedExamList = studentExamScoreDao.findByStudentAndExamPhase(stu,ExamPhase.FINAL_SCORED.getChineseName());
			return SUCCESS;
		}else{
			this.addActionError("您还没有登录，请登录后再点击进入");
			return ERROR;
		}
	}
	
	/*
	 * 统计填空题中空的个数
	 */
	private int countBlank(String content){
		Pattern p = Pattern.compile("[_]{2,}");//含有至少两个_符号表示空白
		Matcher m = p.matcher(content);
		int cnt=0;
		while (m.find()) {
		    cnt++;
		}
		return cnt;
	}
}
