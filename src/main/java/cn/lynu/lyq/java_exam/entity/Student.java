package cn.lynu.lyq.java_exam.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String registerNo;
	private String name;
	private boolean gender;
	private String password;
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="grade_id")
	private Grade grade;
	private int role; //权限  role=0表示学生， role=1表示“教师或管理员”
	
	public Student(){}
	
	public Student(String name, String registerNo, boolean gender, String password) {
		this.name = name;
		this.gender = gender;
		this.registerNo = registerNo;
		this.password = password;
	}
	public Student(String name, String registerNo, boolean gender, String password, Grade grade) {
		this.name = name;
		this.gender = gender;
		this.registerNo = registerNo;
		this.password = password;
		this.grade = grade;
	}

	public Student(String name, String registerNo, boolean gender, String password, int role, Grade grade){
		this.name = name;
		this.registerNo = registerNo;
		this.gender = gender;
		this.password = password;
		this.role = role;
		this.grade = grade;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegisterNo() {
		return registerNo;
	}
	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}

	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", "
				+ "name=" + name 
				+ "registerNo=" + registerNo 
				+ ", gender=" + gender 
				+ ", password=" + password 
				+ ", grade=" + grade 
				+ ", role="+ role + "]";
	}
	
}
