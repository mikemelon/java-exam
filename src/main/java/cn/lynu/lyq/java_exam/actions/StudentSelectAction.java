package cn.lynu.lyq.java_exam.actions;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.dao.StudentDao;
import cn.lynu.lyq.java_exam.entity.Student;

@Component("studentSelect")
@Scope("prototype")
public class StudentSelectAction extends ActionSupport{
	private static final long serialVersionUID = -6429237140130313949L;
	private int examSelect;
	private int strategySelect;
	
	private String regNoSearch;
	private String nameSearch;
	private Boolean genderSearch;
	private String gradeSearch;
	
	private List<Student> studentList;
	private int[] studentChecked;
	
	@Resource
	private StudentDao studentDao;
	
	public int getExamSelect() {
		return examSelect;
	}
	public void setExamSelect(int examSelect) {
		this.examSelect = examSelect;
	}
	public int getStrategySelect() {
		return strategySelect;
	}
	public void setStrategySelect(int strategySelect) {
		this.strategySelect = strategySelect;
	}
	public String getRegNoSearch() {
		return regNoSearch;
	}
	public void setRegNoSearch(String regNoSearch) {
		this.regNoSearch = regNoSearch;
	}
	public String getNameSearch() {
		return nameSearch;
	}
	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}
	public Boolean getGenderSearch() {
		return genderSearch;
	}
	public void setGenderSearch(Boolean genderSearch) {
		this.genderSearch = genderSearch;
	}
	public String getGradeSearch() {
		return gradeSearch;
	}
	public void setGradeSearch(String gradeSearch) {
		this.gradeSearch = gradeSearch;
	}
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public int[] getStudentChecked() {
		return studentChecked;
	}
	public void setStudentChecked(int[] studentChecked) {
		this.studentChecked = studentChecked;
	}
	@Override
	public String execute() throws Exception {
		System.out.println("examSelect="+examSelect+",strategySelect="+strategySelect);
		studentList=studentDao.findAll();
		return SUCCESS;
	}
	
	public String executeForStudentList() throws Exception{
		System.out.println("examSelect="+examSelect+",strategySelect="+strategySelect);
		System.out.println("genderSearch="+genderSearch);
		studentList=studentDao.findStudentForSearch(regNoSearch,nameSearch,genderSearch,gradeSearch);
		return SUCCESS;
	}
	public String executeForGetStudentList(){
		ActionContext ctx=ActionContext.getContext();
		System.out.println(">>>>>>>>>>>>>>>>studentChecked="+Arrays.toString(studentChecked));
		ctx.getSession().put("EXAM_STUDENT_IDS", studentChecked);
		return SUCCESS;
	}
}
