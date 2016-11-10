package cn.lynu.lyq.java_exam.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.lynu.lyq.java_exam.common.QuestionType;

@Entity
public class ExamQuestion {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="exam_id")
	private Exam exam;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bank_choice_qid")
	private BankChoiceQuestion bankChoiceQuestion;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bank_blankfilling_qid")
	private BankBlankFillingQuestion bankBlankFillingQuestion;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bank_judge_qid")
	private BankJudgeQuestion bankJudgeQuestion;
	
	private int questionType;
	
	public ExamQuestion(){}

	public ExamQuestion(Exam exam, BankChoiceQuestion bankChoiceQuestion) {
		this.exam = exam;
		this.bankChoiceQuestion = bankChoiceQuestion;
		this.questionType = QuestionType.CHOICE.ordinal();
	}
	public ExamQuestion(Exam exam, BankBlankFillingQuestion bankBlankFillingQuestion) {
		this.exam = exam;
		this.bankBlankFillingQuestion = bankBlankFillingQuestion;
		this.questionType = QuestionType.BLANK_FILLING.ordinal();
	}
	public ExamQuestion(Exam exam, BankJudgeQuestion bankJudgeQuestion) {
		this.exam = exam;
		this.bankJudgeQuestion = bankJudgeQuestion;
		this.questionType = QuestionType.JUDGE.ordinal();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public BankChoiceQuestion getBankChoiceQuestion() {
		return bankChoiceQuestion;
	}

	public void setBankChoiceQuestion(BankChoiceQuestion bankChoiceQuestion) {
		this.bankChoiceQuestion = bankChoiceQuestion;
	}

	public BankBlankFillingQuestion getBankBlankFillingQuestion() {
		return bankBlankFillingQuestion;
	}

	public void setBankBlankFillingQuestion(BankBlankFillingQuestion bankBlankFillingQuestion) {
		this.bankBlankFillingQuestion = bankBlankFillingQuestion;
	}

	public BankJudgeQuestion getBankJudgeQuestion() {
		return bankJudgeQuestion;
	}

	public void setBankJudgeQuestion(BankJudgeQuestion bankJudgeQuestion) {
		this.bankJudgeQuestion = bankJudgeQuestion;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

}
