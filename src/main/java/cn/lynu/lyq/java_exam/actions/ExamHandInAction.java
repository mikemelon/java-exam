package cn.lynu.lyq.java_exam.actions;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.common.QuestionType;
import cn.lynu.lyq.java_exam.dao.ExamDao;
import cn.lynu.lyq.java_exam.dao.ExamQuestionAnswerDao;
import cn.lynu.lyq.java_exam.dao.ExamQuestionDao;
import cn.lynu.lyq.java_exam.dao.ExamStrategyDao;
import cn.lynu.lyq.java_exam.dao.StudentExamScoreDao;
import cn.lynu.lyq.java_exam.entity.Exam;
import cn.lynu.lyq.java_exam.entity.ExamQuestion;
import cn.lynu.lyq.java_exam.entity.ExamQuestionAnswer;
import cn.lynu.lyq.java_exam.entity.ExamStrategy;
import cn.lynu.lyq.java_exam.entity.Student;
import cn.lynu.lyq.java_exam.entity.StudentExamScore;

@Component("examHandIn")
@Scope("prototype")
public class ExamHandInAction extends ActionSupport {
	private static final long serialVersionUID = 952502008384628354L;
	
	int choiceScore = 0;
	int blankScore =0; 
	int judgeScore =0;
	int totalScore =0;
	
	@Resource
	private ExamDao examDao;
	@Resource
	private ExamStrategyDao examStrategyDao;
	@Resource
	private ExamQuestionDao examQuestionDao;
	@Resource
	private ExamQuestionAnswerDao examQuestionAnswerDao;
	@Resource
	private StudentExamScoreDao studentExamScoreDao;
	
	public int getChoiceScore() {
		return choiceScore;
	}

	public void setChoiceScore(int choiceScore) {
		this.choiceScore = choiceScore;
	}

	public int getBlankScore() {
		return blankScore;
	}

	public void setBlankScore(int blankScore) {
		this.blankScore = blankScore;
	}

	public int getJudgeScore() {
		return judgeScore;
	}

	public void setJudgeScore(int judgeScore) {
		this.judgeScore = judgeScore;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	@Override
	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		Map<String,Object> session =ctx.getSession();
		
		@SuppressWarnings("unchecked")
		Map<QuestionType,List<Object>> answerMap = (Map<QuestionType,List<Object>>)session.get("EXAM_ANSWER");
		@SuppressWarnings("unchecked")
		Map<QuestionType,List<Object>> submitMap=(Map<QuestionType,List<Object>>)session.get("EXAM_SUBMITTED_ANSWER");
		
		@SuppressWarnings("unchecked")
		Map<QuestionType,List<Integer>> eqIdMap = (Map<QuestionType,List<Integer>>)session.get("EXAM_QUESTION_ID_MAP");
		List<Integer> choiceIdList = eqIdMap.get(QuestionType.CHOICE);
		List<Integer> blankIdList = eqIdMap.get(QuestionType.BLANK_FILLING);
		List<Integer> judgeIdList = eqIdMap.get(QuestionType.JUDGE);
		Student s1=(Student)session.get("USER_INFO");
		String examIds = (String)session.get("EXAM_ID");
		Exam theExam = examDao.findById(Integer.parseInt(examIds));
		
		List<StudentExamScore> sesList=studentExamScoreDao.findByStudentAndExam(s1, theExam);
		if(sesList.size()>1){
			this.addActionError("系统出错。您有关该次考试(exam_id="+theExam.getId()+")的成绩多于一个。");
			return ERROR;
		}else if(sesList.get(0).getExamPhase().equals("最终得分")){
			this.addActionError("您已经交过卷子，并得到成绩了。不允许再次交卷！");
			return ERROR;
		}else{
	//		int scorePerChoice = 10, scorePerBlank = 5, scorePerJudge = 10;
			String examStrategyIds = (String)session.get("EXAM_STRATEGY_ID");
			int examStrategyId = Integer.parseInt(examStrategyIds);
			ExamStrategy strategy= examStrategyDao.findById(examStrategyId);
			int scorePerChoice = strategy.getChoicePerScore();
			int scorePerBlank = strategy.getBlankPerScore();
			int scorePerJudge = strategy.getJudgePerScore();
			
			List<Object> choiceAnswerList = answerMap.get(QuestionType.CHOICE);
			List<Object> choiceSubmittedList = submitMap.get(QuestionType.CHOICE);
			for(int i=0; choiceAnswerList!=null && i<choiceAnswerList.size(); i++){
				ExamQuestion eq = examQuestionDao.findById(choiceIdList.get(i));
				ExamQuestionAnswer eqa = new ExamQuestionAnswer(eq,s1,(String)choiceSubmittedList.get(i));
				examQuestionAnswerDao.save(eqa);
				if(choiceAnswerList.get(i).equals(choiceSubmittedList.get(i))){
					choiceScore += scorePerChoice;
				}
			}
			
			List<Object> blankAnswerList = answerMap.get(QuestionType.BLANK_FILLING);
			List<Object> blankSubmittedList = submitMap.get(QuestionType.BLANK_FILLING);
			for(int i=0; blankAnswerList!=null && i<blankAnswerList.size(); i++){
				@SuppressWarnings("unchecked")
				List<String> blankAnswerListForCurrentQ = (List<String>)blankAnswerList.get(i);
				@SuppressWarnings("unchecked")
				List<String> blankSubmittedListForCurrentQ = (List<String>)blankSubmittedList.get(i);
				ExamQuestion eq = examQuestionDao.findById(blankIdList.get(i));
				String blankAnswerString = "";
				for(int j=0; j<blankAnswerListForCurrentQ.size(); j++){
					String curAnswer = blankSubmittedListForCurrentQ.get(j);
					curAnswer = curAnswer!=null?curAnswer:"";
					blankAnswerString += "[[["+curAnswer+"]]],";
					if(blankAnswerListForCurrentQ.get(j).equals(blankSubmittedListForCurrentQ.get(j))){
						blankScore += scorePerBlank;
					}
				}
				blankAnswerString = blankAnswerString.substring(0,blankAnswerString.length()-1);
				ExamQuestionAnswer eqa = new ExamQuestionAnswer(eq,s1,blankAnswerString);
				examQuestionAnswerDao.save(eqa);
			}
			
			List<Object> judgeAnswerList = answerMap.get(QuestionType.JUDGE);
			List<Object> judgeSubmittedList = submitMap.get(QuestionType.JUDGE);
			for(int i=0; judgeAnswerList!=null && i<judgeAnswerList.size(); i++){
				ExamQuestion eq = examQuestionDao.findById(judgeIdList.get(i));
				ExamQuestionAnswer eqa = new ExamQuestionAnswer(eq,s1,(String)judgeSubmittedList.get(i));
				examQuestionAnswerDao.save(eqa);
				if(judgeAnswerList.get(i).equals(judgeSubmittedList.get(i))){
					judgeScore += scorePerJudge;
				}
			}
			totalScore = choiceScore+blankScore+judgeScore;
			System.out.println("总分="+(choiceScore+blankScore+judgeScore));
			
			for(StudentExamScore ses : sesList){
				ses.setExamPhase("最终得分");
				ses.setScore(totalScore);
				studentExamScoreDao.update(ses);
			}
			return SUCCESS;
		}
	}
	
}
