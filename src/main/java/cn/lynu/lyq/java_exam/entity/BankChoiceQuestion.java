package cn.lynu.lyq.java_exam.entity;

import javax.persistence.Entity;

/*
 * 选择题题库（包含单选和多选题）
 * 答案（该属性在父类里）：单选则用A,B,C,D表示
 *             多选用[A,B,...]表示
 */
@Entity
public class BankChoiceQuestion extends BankQuestion {
	private String choiceA;
	private String choiceB;
	private String choiceC;
	private String choiceD;
	private String choiceE;
	private String choiceF;
	private String choiceG;
	private String choiceH;
	
	public BankChoiceQuestion(){}
	
	public BankChoiceQuestion(String content,String choiceA, String choiceB, String choiceC, String choiceD,String answer,KnowledgePoint knowledgePoint, Course course) {
		setContent(content);
		this.choiceA = choiceA;
		this.choiceB = choiceB;
		this.choiceC = choiceC;
		this.choiceD = choiceD;
		setAnswer(answer);
		setKnowledgePoint(knowledgePoint);
		setComposeFlag(1); //默认允许题目参与随机抽题
		setCourse(course);

	}

	public BankChoiceQuestion(String content,String choiceA, String choiceB, String choiceC, String choiceD,
							  String choiceE, String choiceF, String choiceG, String choiceH,	String answer,KnowledgePoint knowledgePoint, Course course) {
		setContent(content);
		this.choiceA = choiceA;
		this.choiceB = choiceB;
		this.choiceC = choiceC;
		this.choiceD = choiceD;
		this.choiceE = choiceE;
		this.choiceF = choiceF;
		this.choiceG = choiceG;
		this.choiceH = choiceH;
		setAnswer(answer);
		setKnowledgePoint(knowledgePoint);
		setComposeFlag(1); //默认允许题目参与随机抽题
		setCourse(course);
	}
	public String getChoiceA() {
		return choiceA;
	}

	public void setChoiceA(String choiceA) {
		this.choiceA = choiceA;
	}

	public String getChoiceB() {
		return choiceB;
	}

	public void setChoiceB(String choiceB) {
		this.choiceB = choiceB;
	}

	public String getChoiceC() {
		return choiceC;
	}

	public void setChoiceC(String choiceC) {
		this.choiceC = choiceC;
	}

	public String getChoiceD() {
		return choiceD;
	}

	public void setChoiceD(String choiceD) {
		this.choiceD = choiceD;
	}

	public String getChoiceE() {
		return choiceE;
	}

	public void setChoiceE(String choiceE) {
		this.choiceE = choiceE;
	}

	public String getChoiceF() {
		return choiceF;
	}

	public void setChoiceF(String choiceF) {
		this.choiceF = choiceF;
	}

	public String getChoiceG() {
		return choiceG;
	}

	public void setChoiceG(String choiceG) {
		this.choiceG = choiceG;
	}

	public String getChoiceH() {
		return choiceH;
	}

	public void setChoiceH(String choiceH) {
		this.choiceH = choiceH;
	}

	@Override
	public String toString() {
		return String.valueOf(getId());
//		return "选择题 ["+ ", 题目：" + getContent() 
//				+ ", 答案：" + getAnswer() 
//				+ ", 选项A：" + choiceA 
//				+ ", 选项B：" + choiceB 
//				+ ", 选项C：" + choiceC 
//				+ ", 选项D：" + choiceD + "]";
	}
	
	
	
}
