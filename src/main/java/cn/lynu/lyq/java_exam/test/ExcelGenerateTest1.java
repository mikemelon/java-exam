package cn.lynu.lyq.java_exam.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.lynu.lyq.java_exam.common.ExamPhase;
import cn.lynu.lyq.java_exam.dao.ExamDao;
import cn.lynu.lyq.java_exam.dao.GradeDao;
import cn.lynu.lyq.java_exam.dao.StudentDao;
import cn.lynu.lyq.java_exam.dao.StudentExamScoreDao;
import cn.lynu.lyq.java_exam.entity.Exam;
import cn.lynu.lyq.java_exam.entity.Student;
import cn.lynu.lyq.java_exam.entity.StudentExamScore;

public class ExcelGenerateTest1 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		GradeDao gradeDao = (GradeDao) context.getBean("gradeDao");
		StudentDao studentDao = (StudentDao) context.getBean("studentDao");
		ExamDao examDao = (ExamDao) context.getBean("examDao");
		StudentExamScoreDao studentExamScoreDao = (StudentExamScoreDao) context.getBean("studentExamScoreDao");
		
		List<Student> stuList = studentDao.findByGrade(gradeDao.findById(7));
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("成绩表一");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("学号");
		row.createCell(1).setCellValue("姓名");
		row.createCell(2).setCellValue("成绩1");
		row.createCell(3).setCellValue("成绩2");
		row.createCell(4).setCellValue("成绩3");
		
		for (int i = 0; i < stuList.size(); i++) {
			HSSFRow row1 = sheet.createRow(i + 1);
			Student s1 = stuList.get(i);
			row1.createCell(0).setCellValue(s1.getRegisterNo());
			row1.createCell(1).setCellValue(s1.getName());
			
			int score1 = getStudentExamScore(s1,"2018年6月15日面向对象选择题20题乱序",examDao,studentExamScoreDao);
			if(score1 != -1){
				row1.createCell(2).setCellValue(score1);
			}
			
			int score2 = getStudentExamScore(s1,"2018年6月22日面向对象选择题乱序",examDao,studentExamScoreDao);
			if(score2 != -1){
				row1.createCell(3).setCellValue(score2);
			}
			int score3 = getStudentExamScore(s1,"2018年上半年期末测试",examDao,studentExamScoreDao);
			if(score3 != -1){
				row1.createCell(4).setCellValue(score3);
			}
			
		}
		
		
		//最后写入xls文件
		try {
            FileOutputStream fos = new FileOutputStream("c:\\test1.xls");
            workbook.write(fos);
            System.out.println("写入成功");
            fos.close();
            context.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static int getStudentExamScore(Student s1, String examNameAlike, ExamDao examDao, StudentExamScoreDao studentExamScoreDao){
		List<Exam> examList = examDao.findByStudentNameAndExamNameAlike(s1.getName(), examNameAlike);
		if (examList.size() != 1) {
			System.out.println(s1.getName() + ":该学生缺考，或考试次数不对！" + examList.size());
			return -1;
		}
		List<StudentExamScore> sesList = studentExamScoreDao.findByStudentAndExam(s1, examList.get(0));
		if (sesList.size() != 1) {
			System.out.println("系统出错。" + s1.getName() + " 该次考试(exam_id=" + examList.get(0).getId() + ")的成绩多于一个。");
			return -1;
		}
		StudentExamScore ses = sesList.get(0);
		if(ses.getExamPhase().equals(ExamPhase.FINAL_SCORED.getChineseName())){
			return ses.getScore();
		}else{
			System.out.println("系统出错。" + s1.getName() + " 该次考试(exam_id=" + examList.get(0).getId() + ")不是最终成绩。");
			return -1;
		}
	}

}
