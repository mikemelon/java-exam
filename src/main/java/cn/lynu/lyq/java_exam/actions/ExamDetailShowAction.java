package cn.lynu.lyq.java_exam.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.common.QuestionType;
import cn.lynu.lyq.java_exam.dao.BankQuestionDao;
import cn.lynu.lyq.java_exam.dao.ExamDao;
import cn.lynu.lyq.java_exam.dao.ExamQuestionDao;
import cn.lynu.lyq.java_exam.entity.BankBlankFillingQuestion;
import cn.lynu.lyq.java_exam.entity.BankChoiceQuestion;
import cn.lynu.lyq.java_exam.entity.BankJudgeQuestion;
import cn.lynu.lyq.java_exam.entity.Exam;
import cn.lynu.lyq.java_exam.entity.ExamQuestion;

@Component("examDetailShow")
@Scope("prototype")
public class ExamDetailShowAction extends ActionSupport {
	private static final long serialVersionUID = 169605340756527528L;
	@Resource
	private ExamDao examDao;
	@Resource
	private ExamQuestionDao examQuestionDao;
	@Resource 
	private BankQuestionDao bankQuestionDao;
	
	List<BankChoiceQuestion> choiceList=new ArrayList<>();
    List<BankBlankFillingQuestion> blankFillingList = new ArrayList<>();
    List<BankJudgeQuestion> judgeList = new ArrayList<>();
	
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

	@Override
	public String execute() throws Exception {
		ActionContext ctx=ActionContext. getContext();
        String examIds=ctx.getParameters().get("exam_id").getValue();
        int examId=Integer.parseInt( examIds.trim());
//        System.out.println("**********"+examId);
        
        Exam exam = examDao.findById(examId);
        List<ExamQuestion> eqList = examQuestionDao.findByExam(exam);
        
        choiceList=new ArrayList<>();
        blankFillingList = new ArrayList<>();
        judgeList = new ArrayList<>();
        for(ExamQuestion eq:eqList){
        	if(eq.getQuestionType()==QuestionType.CHOICE.ordinal()){
        		choiceList.add(bankQuestionDao.findChoiceById(eq.getBankChoiceQuestion().getId()));
        	}else if(eq.getQuestionType()==QuestionType.BLANK_FILLING.ordinal()){
        		blankFillingList.add(bankQuestionDao.findBlankFillingById(eq.getBankBlankFillingQuestion().getId()));
        	}else if(eq.getQuestionType()==QuestionType.JUDGE.ordinal()){
        		judgeList.add(bankQuestionDao.findJudgeById(eq.getBankJudgeQuestion().getId()));
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
}
