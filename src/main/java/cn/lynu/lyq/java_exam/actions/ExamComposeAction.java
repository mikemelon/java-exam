package cn.lynu.lyq.java_exam.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.common.ExamPhase;
import cn.lynu.lyq.java_exam.common.QuestionType;
import cn.lynu.lyq.java_exam.dao.BankQuestionDao;
import cn.lynu.lyq.java_exam.dao.ExamDao;
import cn.lynu.lyq.java_exam.dao.ExamQuestionDao;
import cn.lynu.lyq.java_exam.dao.ExamStrategyDao;
import cn.lynu.lyq.java_exam.dao.StudentDao;
import cn.lynu.lyq.java_exam.dao.StudentExamScoreDao;
import cn.lynu.lyq.java_exam.entity.BankBlankFillingQuestion;
import cn.lynu.lyq.java_exam.entity.BankChoiceQuestion;
import cn.lynu.lyq.java_exam.entity.BankJudgeQuestion;
import cn.lynu.lyq.java_exam.entity.Exam;
import cn.lynu.lyq.java_exam.entity.ExamQuestion;
import cn.lynu.lyq.java_exam.entity.ExamStrategy;
import cn.lynu.lyq.java_exam.entity.Student;
import cn.lynu.lyq.java_exam.entity.StudentExamScore;
@Component("examCompose")
@Scope("prototype")
public class ExamComposeAction extends ActionSupport {
	
	private static final long serialVersionUID = 625313130792094673L;
	@Resource
	private ExamDao examDao;
	@Resource
	private ExamQuestionDao examQuestionDao;
	@Resource 
	private BankQuestionDao bankQuestionDao;
	@Resource
	private ExamStrategyDao examStrategyDao;
	@Resource
	private StudentDao studentDao;
	@Resource
	private StudentExamScoreDao studentExamScoreDao;
	
	private List<Exam> examList;
	
	private int examSelect;
	
	private List<ExamStrategy> strategyList;
	
	private Map<String,Integer> eqCntMap;
	private int strategyId;//用于删除某个策略
	private int strategySelect;
	
	private List<Student> studentList;
	
	public List<Exam> getExamList() {
		return examList;
	}
	public void setExamList(List<Exam> examList) {
		this.examList = examList;
	}
	public int getExamSelect() {
		return examSelect;
	}
	public void setExamSelect(int examSelect) {
		this.examSelect = examSelect;
	}
	public List<ExamStrategy> getStrategyList() {
		return strategyList;
	}
	public void setStrategyList(List<ExamStrategy> strategyList) {
		this.strategyList = strategyList;
	}
	public Map<String, Integer> getEqCntMap() {
		return eqCntMap;
	}
	public void setEqCntMap(Map<String, Integer> eqCntMap) {
		this.eqCntMap = eqCntMap;
	}
	public int getStrategyId() {
		return strategyId;
	}
	public void setStrategyId(int strategyId) {
		this.strategyId = strategyId;
	}
	public int getStrategySelect() {
		return strategySelect;
	}
	public void setStrategySelect(int strategySelect) {
		this.strategySelect = strategySelect;
	}
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
			getAllExamList();
			if(examSelect>0){ //从学生选择action chain过来，已经选过了examSelect
				strategyList = examStrategyDao.findByExam(examDao.findById(examSelect));
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
	
	public String executeForSearchStrategy() throws Exception {
		strategyList = examStrategyDao.findByExam(examDao.findById(examSelect));
		return SUCCESS;
	}
	
	public String executeForCreateStrategy() throws Exception {
		ExamStrategy es = new ExamStrategy(examDao.findById(examSelect),"新建分数分配策略",0,0,0,null);
		examStrategyDao.save(es);
		getExamQuestionCnt();
		return SUCCESS;
	}
	public String executeForDeleteStrategy() throws Exception {
		ExamStrategy es = examStrategyDao.findById(strategyId);
		examStrategyDao.delete(es);
		getExamQuestionCnt();
		return SUCCESS;
	}
	
	public String executeForCreateStudentExam() throws Exception{
		//让画面仍然显示exam,strategy,student三个列表
		getAllExamList();
		Exam theExam = examDao.findById(examSelect);
		strategyList = examStrategyDao.findByExam(theExam);
		ExamStrategy theStrategy = examStrategyDao.findById(strategySelect);
		
		ActionContext ctx =ActionContext.getContext();
		int[] studentIds = (int[])ctx.getSession().get("EXAM_STUDENT_IDS");
		if(studentIds!=null){
			studentList=new ArrayList<>();
			for(int sid:studentIds){
				Student theStudent = studentDao.findById(sid);
				studentList.add(theStudent);
				StudentExamScore ses=new StudentExamScore(theStudent,theExam,theStrategy,0,ExamPhase.PAPER_INITIALIZED.getChineseName());
				studentExamScoreDao.save(ses);
			}
			this.clearMessages();
			this.addActionMessage("已经为这"+studentIds.length+"个学生生成了试卷, "
								+ "试卷名称："+theExam.getName()
								+ ", 请登录后进入试卷列表查看。");
		}
		return SUCCESS;
	}
	
	/*
	 * 重新计算当前exam包含的各类题目（填空题为空白）数
	 */
	private void getExamQuestionCnt(){
		Exam exam=examDao.findById(examSelect); 
		List<ExamQuestion> eqList = examQuestionDao.findByExam(exam);
        int choiceCnt=0, blankCnt=0, judgeCnt=0;
        for(ExamQuestion eq:eqList){
        	if(eq.getQuestionType()==QuestionType.CHOICE.ordinal()){
        		choiceCnt++;
        	}else if(eq.getQuestionType()==QuestionType.BLANK_FILLING.ordinal()){
        		BankBlankFillingQuestion bq =bankQuestionDao.findBlankFillingById(eq.getBankBlankFillingQuestion().getId());
        		blankCnt+=countBlank(bq.getContent());
        	}else if(eq.getQuestionType()==QuestionType.JUDGE.ordinal()){
        		judgeCnt++;
        	}
        }
        eqCntMap = new HashMap<>();
        eqCntMap.put("CHOICE_CNT", choiceCnt);
        eqCntMap.put("BLANK_CNT", blankCnt);
        eqCntMap.put("JUDGE_CNT", judgeCnt);
	}
	
	private void getAllExamList(){
		ActionContext ctx =ActionContext.getContext();
		examList = examDao.findAllFixedExam();
		
        for(Exam exam:examList){
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
