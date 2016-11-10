package cn.lynu.lyq.java_exam.actions;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.dao.StudentDao;
@Component("studentImport")
@Scope("prototype")
public class StudentImportAction extends ActionSupport {
	
	private static final long serialVersionUID = 3132888016394172355L;
	private File studentImportFile;
	private String studentImportFileFileName;//文件名
	private String studentImportFileContentType;//文件类型
	private String studentFilePath;
	
	@Resource
	private StudentDao studentDao;
	
	public File getStudentImportFile() {
		return studentImportFile;
	}
	public void setStudentImportFile(File studentImportFile) {
		this.studentImportFile = studentImportFile;
	}
	public String getStudentImportFileFileName() {
		return studentImportFileFileName;
	}
	public void setStudentImportFileFileName(String studentImportFileFileName) {
		this.studentImportFileFileName = studentImportFileFileName;
	}
	public String getStudentImportFileContentType() {
		return studentImportFileContentType;
	}
	public void setStudentImportFileContentType(String studentImportFileContentType) {
		this.studentImportFileContentType = studentImportFileContentType;
	}
	public String getStudentFilePath() {
		return studentFilePath;
	}
	public void setStudentFilePath(String studentFilePath) {
		this.studentFilePath = studentFilePath;
	}

	@Override
	public String execute() throws Exception {  
		int cnt = studentDao.importFromTxt(studentImportFile);
		System.out.println("studentImportFile="+studentImportFile);
		System.out.println("studentFilePath="+studentFilePath);
		this.addActionMessage(cnt+"条学生信息已经导入");
		return SUCCESS;
	}
	
}
