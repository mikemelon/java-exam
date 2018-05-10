package cn.lynu.lyq.java_exam.actions;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.dao.BankQuestionDao;
import cn.lynu.lyq.java_exam.entity.BankChoiceQuestion;

@Component("choiceDetail")
@Scope("prototype")
public class ChoiceDetailAction extends ActionSupport {
	
	private static final long serialVersionUID = 3023312680596200270L;
	private BankChoiceQuestion question;
	@Resource 
	private BankQuestionDao bankQuestionDao;
	
	public BankChoiceQuestion getQuestion() {
		return question;
	}
	public void setQuestion(BankChoiceQuestion question) {
		this.question = question;
	}

	@Override
	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		String qid = ctx.getParameters().get("qid").getValue();
		question = bankQuestionDao.findChoiceById(Integer.parseInt(qid.trim()));
		
		//显示前，如果有类似"A."的字符，就删掉
		String choiceA = question.getChoiceA();
		String choiceB = question.getChoiceB();
		String choiceC = question.getChoiceC();
		String choiceD = question.getChoiceD();
		question.setChoiceA(deleteOptionLetter(choiceA));
		question.setChoiceB(deleteOptionLetter(choiceB));
		question.setChoiceC(deleteOptionLetter(choiceC));
		question.setChoiceD(deleteOptionLetter(choiceD));
		
		return SUCCESS;
	}
	
	private String deleteOptionLetter(String choiceOption){
		if(choiceOption!=null && choiceOption.length()>1){
			choiceOption = choiceOption.trim();
			char optionLetter = choiceOption.charAt(0);
			char dotChar = choiceOption.charAt(1);
			if("ABCDEFGH".contains(String.valueOf(optionLetter)) )  
				choiceOption = choiceOption.substring(1);
			if(dotChar=='.' || dotChar=='．')
				choiceOption = choiceOption.substring(1);
			return choiceOption.trim();
		}else
			return "";
	}
}
