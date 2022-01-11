package cn.lynu.lyq.java_exam.entity;

import javax.persistence.Entity;

/*
 * 判断题题库
 * 答案（该属性在父类里）：直接用true或false的字符串表示
 */
@Entity
public class BankJudgeQuestion extends BankQuestion {
	
	public BankJudgeQuestion(){}
	
	public BankJudgeQuestion(String content,String answer,String knowledgePoint, Course course) {
		setContent(content);
		setAnswer(answer);
		setKnowledgePoint(knowledgePoint);
		setComposeFlag(1); //默认允许题目参与随机抽题
		setCourse(course);
	}

	@Override
	public String toString() {
		return String.valueOf(getId());
//		return "判断题 [题目：" + getContent() 
//				+ ", 答案：" + getAnswer() + "]";
	}
	
	

}
