package cn.lynu.lyq.java_exam.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Exam {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int type;//0-->固定选择组卷, 1-->随机抽题组卷
	private String name;
	private String detail;
	private Date examDate;
	private Date createDate;
	private int scheduledTime;//规定考试用时（单位：秒）
	
	public Exam(){}
	public Exam(String name, String detail, int type){
		this.name = name;
		this.detail = detail;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getScheduledTime() {
		return scheduledTime;
	}
	public void setScheduledTime(int scheduledTime) {
		this.scheduledTime = scheduledTime;
	}
}
