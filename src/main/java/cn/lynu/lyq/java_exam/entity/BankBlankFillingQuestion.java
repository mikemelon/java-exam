package cn.lynu.lyq.java_exam.entity;

import javax.persistence.Entity;

/*
 * 填空题题库
 * 答案（该属性在父类里）：只有一个空则直接用answer属性表示
 *             多个空的答案分别用answer2~answer8表示
 */
@Entity
public class BankBlankFillingQuestion extends BankQuestion {
	private String answer2;
	private String answer3;
	private String answer4;
	private String answer5;
	private String answer6;
	private String answer7;
	private String answer8;
	
	public BankBlankFillingQuestion(){}
	
	public BankBlankFillingQuestion(String content,String answer,String knowledgePoint) {
		setContent(content);
		setAnswer(answer);
		setKnowledgePoint(knowledgePoint);
	}
	
	public BankBlankFillingQuestion(String content,String answer, String answer2,String knowledgePoint) {
		setContent(content);
		setAnswer(answer);
		this.answer2 = answer2;
		setKnowledgePoint(knowledgePoint);
	}
	
	public BankBlankFillingQuestion(String content,String answer, String answer2, String answer3,String knowledgePoint) {
		setContent(content);
		setAnswer(answer);
		this.answer2 = answer2;
		this.answer3 = answer3;
		setKnowledgePoint(knowledgePoint);
	}
	
	public BankBlankFillingQuestion(String content,String answer, String answer2, String answer3, String answer4,String knowledgePoint) {
		setContent(content);
		setAnswer(answer);
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		setKnowledgePoint(knowledgePoint);
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public String getAnswer5() {
		return answer5;
	}

	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}

	public String getAnswer6() {
		return answer6;
	}

	public void setAnswer6(String answer6) {
		this.answer6 = answer6;
	}

	public String getAnswer7() {
		return answer7;
	}

	public void setAnswer7(String answer7) {
		this.answer7 = answer7;
	}

	public String getAnswer8() {
		return answer8;
	}

	public void setAnswer8(String answer8) {
		this.answer8 = answer8;
	}

	@Override
	public String toString() {
		return String.valueOf(getId());
//		return "填空题 [题目：" + getContent() 
//				+ ", 答案："	+ getAnswer() 
//				+ "答案2=" + answer2 + "]";
	}
	
	

}
