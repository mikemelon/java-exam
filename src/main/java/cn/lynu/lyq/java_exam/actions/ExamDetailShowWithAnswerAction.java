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

import cn.lynu.lyq.java_exam.common.QuestionType;
import cn.lynu.lyq.java_exam.dao.BankQuestionDao;
import cn.lynu.lyq.java_exam.dao.ExamDao;
import cn.lynu.lyq.java_exam.dao.ExamQuestionAnswerDao;
import cn.lynu.lyq.java_exam.dao.ExamQuestionDao;
import cn.lynu.lyq.java_exam.dao.ExamStrategyDao;
import cn.lynu.lyq.java_exam.dao.StudentDao;
import cn.lynu.lyq.java_exam.entity.BankBlankFillingQuestion;
import cn.lynu.lyq.java_exam.entity.BankChoiceQuestion;
import cn.lynu.lyq.java_exam.entity.BankJudgeQuestion;
import cn.lynu.lyq.java_exam.entity.Exam;
import cn.lynu.lyq.java_exam.entity.ExamQuestion;
import cn.lynu.lyq.java_exam.entity.ExamQuestionAnswer;
import cn.lynu.lyq.java_exam.entity.ExamStrategy;
import cn.lynu.lyq.java_exam.entity.Student;

@Component("examDetailShowWithAnswer")
@Scope("prototype")
public class ExamDetailShowWithAnswerAction extends ActionSupport {
	private static final long serialVersionUID = 8639928264880549463L;
	private final static Logger logger = LoggerFactory.getLogger(ExamDetailShowWithAnswerAction.class);
	@Resource
	private ExamDao examDao;
	@Resource
	private StudentDao studentDao;
	@Resource
	private ExamStrategyDao examStrategyDao;
	@Resource
	private ExamQuestionDao examQuestionDao;
	@Resource
	private ExamQuestionAnswerDao examQuestionAnswerDao;
	@Resource 
	private BankQuestionDao bankQuestionDao;
	
	List<BankChoiceQuestion> choiceList=new ArrayList<>();
    List<BankBlankFillingQuestion> blankFillingList = new ArrayList<>();
    List<BankJudgeQuestion> judgeList = new ArrayList<>();
    
    Map<QuestionType,List<Integer>> eqIdMap = new HashMap<>();
    private Map<QuestionType,List<Object>> examAnswerMap = new HashMap<>();// 正确答案
    private Map<QuestionType,List<Object>> examQuestionAnswerMap = new HashMap<>(); //学生的答案
    private ExamStrategy examStrategy;
    private Exam exam;
    private Student student;
	
	public List<BankChoiceQuestion> getChoiceList() {
		return choiceList;
	}

	public void setChoiceList(List<BankChoiceQuestion> choiceList) {
		this.choiceList = choiceList;
	}

	public List<BankBlankFillingQuestion> getBlankFillingList() {
		return blankFillingList;
	}

	public void setBlankFillingList(List<BankBlankFillingQuestion> blankFillingList) {
		this.blankFillingList = blankFillingList;
	}

	public List<BankJudgeQuestion> getJudgeList() {
		return judgeList;
	}

	public void setJudgeList(List<BankJudgeQuestion> judgeList) {
		this.judgeList = judgeList;
	}
	
	public Map<QuestionType, List<Object>> getExamAnswerMap() {
		return examAnswerMap;
	}

	public void setExamAnswerMap(Map<QuestionType, List<Object>> examAnswerMap) {
		this.examAnswerMap = examAnswerMap;
	}

	public Map<QuestionType, List<Object>> getExamQuestionAnswerMap() {
		return examQuestionAnswerMap;
	}

	public void setExamQuestionAnswerMap(Map<QuestionType, List<Object>> examQuestionAnswerMap) {
		this.examQuestionAnswerMap = examQuestionAnswerMap;
	}

	public Map<QuestionType, List<Integer>> getEqIdMap() {
		return eqIdMap;
	}

	public void setEqIdMap(Map<QuestionType, List<Integer>> eqIdMap) {
		this.eqIdMap = eqIdMap;
	}

	public ExamStrategy getExamStrategy() {
		return examStrategy;
	}

	public void setExamStrategy(ExamStrategy examStrategy) {
		this.examStrategy = examStrategy;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String execute() throws Exception {
		ActionContext ctx=ActionContext. getContext();
		String stuIds = ctx.getParameters().get("stu_id").getValue();
        String examIds=ctx.getParameters().get("exam_id").getValue();
        String examStrategyIds=ctx.getParameters().get("exam_strategy_id").getValue();
        int stuId=Integer.parseInt( stuIds.trim());
        int examId=Integer.parseInt( examIds.trim());
        int examStrategyId=Integer.parseInt( examStrategyIds.trim());
        logger.debug("**********"+examId);
        
        student = studentDao.findById(stuId);
        exam = examDao.findById(examId);
        
        List<ExamQuestion> eqList = examQuestionDao.findByExam(exam);
        examStrategy = examStrategyDao.findById(examStrategyId);
        
        choiceList=new ArrayList<>();
        blankFillingList = new ArrayList<>();
        judgeList = new ArrayList<>();
        
        for(ExamQuestion eq:eqList){
        	if(eq.getQuestionType()==QuestionType.CHOICE.ordinal()){
        		BankChoiceQuestion choiceQ=bankQuestionDao.findChoiceById(eq.getBankChoiceQuestion().getId());
        		choiceList.add(choiceQ);
        		
    			List<Object> answersList = examAnswerMap.get(QuestionType.CHOICE);
    			List<Object> questionAnswersList = examQuestionAnswerMap.get(QuestionType.CHOICE);
    			List<Integer> eqIdList = eqIdMap.get(QuestionType.CHOICE);
    			if(answersList==null){
    				answersList = new ArrayList<Object>();
    				questionAnswersList = new ArrayList<Object>();
    				eqIdList = new ArrayList<Integer>();
    			}
    			answersList.add(choiceQ.getAnswer());
    			logger.debug("***********"+stuId+"******************");
    			logger.debug("***********"+eq.getId()+"******************");
    			ExamQuestionAnswer eqa = examQuestionAnswerDao.findByStudentAndExamQuestion(student,eq);
    			logger.debug("**********************eqa_answer=****"+eqa.getAnswer());
    			questionAnswersList.add(eqa.getAnswer());
    			
    			examQuestionAnswerMap.put(QuestionType.CHOICE, questionAnswersList);
    			examAnswerMap.put(QuestionType.CHOICE, answersList);
    			eqIdList.add(eq.getId());
    			eqIdMap.put(QuestionType.CHOICE, eqIdList);
        	}else if(eq.getQuestionType()==QuestionType.BLANK_FILLING.ordinal()){
        		BankBlankFillingQuestion blankQ=bankQuestionDao.findBlankFillingById(eq.getBankBlankFillingQuestion().getId());
        		blankFillingList.add(blankQ);
        		
    			List<Object> answersList = examAnswerMap.get(QuestionType.BLANK_FILLING);
    			List<Object> questionAnswersList = examQuestionAnswerMap.get(QuestionType.BLANK_FILLING);
    			List<Integer> eqIdList = eqIdMap.get(QuestionType.BLANK_FILLING);
    			if(answersList==null){
    				answersList = new ArrayList<Object>();
    				questionAnswersList = new ArrayList<Object>();
    				eqIdList = new ArrayList<Integer>();
    			}
    			int blankCnt = countBlank(blankQ.getContent());
    			List<String> currentAnswerList = new ArrayList<>();
    			for(int i=0; i<blankCnt; i++){
    				String theAnswer = getBlankAnswerN(blankQ,i);
    				currentAnswerList.add(theAnswer);
    			}
    			answersList.add(currentAnswerList);
    			examAnswerMap.put(QuestionType.BLANK_FILLING, answersList);
    			
    			ExamQuestionAnswer eqa = examQuestionAnswerDao.findByStudentAndExamQuestion(student,eq);
    			questionAnswersList.add(getQuestionAnswerListForBlank(eqa.getAnswer()));
    			examQuestionAnswerMap.put(QuestionType.BLANK_FILLING, questionAnswersList);
    			
    			eqIdList.add(eq.getId());
    			eqIdMap.put(QuestionType.BLANK_FILLING, eqIdList);
        	}else if(eq.getQuestionType()==QuestionType.JUDGE.ordinal()){
        		BankJudgeQuestion judgeQ = bankQuestionDao.findJudgeById(eq.getBankJudgeQuestion().getId());
        		judgeList.add(judgeQ);
        		
    			List<Object> answersList = examAnswerMap.get(QuestionType.JUDGE);
    			List<Object> questionAnswersList = examQuestionAnswerMap.get(QuestionType.JUDGE);
    			List<Integer> eqIdList = eqIdMap.get(QuestionType.JUDGE);
    			if(answersList==null){
    				answersList = new ArrayList<Object>();
    				questionAnswersList = new ArrayList<Object>();
    				eqIdList = new ArrayList<Integer>();
    			}
    			answersList.add(judgeQ.getAnswer());
    			examAnswerMap.put(QuestionType.JUDGE, answersList);
    			
    			ExamQuestionAnswer eqa = examQuestionAnswerDao.findByStudentAndExamQuestion(student,eq);
    			questionAnswersList.add(eqa.getAnswer());
    			examQuestionAnswerMap.put(QuestionType.JUDGE, questionAnswersList);
    			
    			eqIdList.add(eq.getId());
    			eqIdMap.put(QuestionType.JUDGE, eqIdList);
        	}
        }
		return SUCCESS;
	}
	
	/**
	 * 将填空题中的____空替换成文本控件，且文本控件的id和name命名为q1_blank2的形式。
	 * （前面的1表示是第一道填空题，后面的2表示是该题目的第二个空白）
	 * 
	 * @param content 该题内容字符串（含有____的空）
	 * @param quesitonNo 填空题编号
	 * @return 替换后的字符串
	 */
	public static String replaceBlank(String content, int quesitonNo){
		Pattern p = Pattern.compile("[_]{2,}");
		Matcher m = p.matcher(content);
		StringBuffer sb = new StringBuffer();
		int i=1;
		while (m.find()) {
			String idStr="'q"+quesitonNo+"_blank"+i+"'";
		    m.appendReplacement(sb, "<input type='text' id="+idStr
					+ " name="+idStr+ " style='width:200px' placeholder='输入答案'/>");
		    i++;
		}
		m.appendTail(sb);
		return sb.toString();
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
	
	/*
	 * 根据填空题的空白位置返回答案, idx从0开始
	 */
	private String getBlankAnswerN(BankBlankFillingQuestion q, int idx){
		String answer = null;
		switch(idx){
			case 0:
				answer = q.getAnswer();
				break;
			case 1:
				answer = q.getAnswer2();
				break;
			case 2:
				answer = q.getAnswer3();
				break;
			case 3:
				answer = q.getAnswer4();
				break;
			case 4:
				answer = q.getAnswer5();
				break;
			case 5:
				answer = q.getAnswer6();
				break;
			case 6:
				answer = q.getAnswer7();
				break;
			case 7:
				answer = q.getAnswer8();
				break;
			default:
				answer = q.getAnswer();
				break;
		}
		return answer;
	}
	
	private List<String> getQuestionAnswerListForBlank(String blankQuestionAnswerStr){
		List<String> answerList = new ArrayList<>();
		String[] answersArray = blankQuestionAnswerStr.split(",");
		for(String answer: answersArray){
			int endIdx = answer.indexOf("]]]");
			answerList.add(answer.trim().substring(3,endIdx));
		}
		return answerList;
	}
}
