package cn.lynu.lyq.java_exam.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.common.QuestionType;
import cn.lynu.lyq.java_exam.dao.BankQuestionDao;
import cn.lynu.lyq.java_exam.dao.ExamDao;
import cn.lynu.lyq.java_exam.dao.ExamQuestionAnswerDao;
import cn.lynu.lyq.java_exam.dao.ExamQuestionDao;
import cn.lynu.lyq.java_exam.dao.StudentDao;
import cn.lynu.lyq.java_exam.entity.BankBlankFillingQuestion;
import cn.lynu.lyq.java_exam.entity.BankChoiceQuestion;
import cn.lynu.lyq.java_exam.entity.BankJudgeQuestion;
import cn.lynu.lyq.java_exam.entity.Exam;
import cn.lynu.lyq.java_exam.entity.ExamQuestion;
import cn.lynu.lyq.java_exam.entity.ExamQuestionAnswer;
import cn.lynu.lyq.java_exam.entity.Student;
import cn.lynu.lyq.java_exam.utils.QuestionUtils;

@Component("downloadExportedFile")
@Scope("prototype")
public class DownloadExportedFileAction extends ActionSupport {

	private static final long serialVersionUID = 8248962048889948056L;
	private final static Logger logger = LoggerFactory.getLogger(DownloadExportedFileAction.class);
	
	private InputStream inputStream;
	private String fileName;
	
	@Resource
	private ExamDao examDao;
	@Resource
	private StudentDao studentDao;
	@Resource
	private ExamQuestionDao examQuestionDao;
	@Resource
	private ExamQuestionAnswerDao examQuestionAnswerDao;
	@Resource 
	private BankQuestionDao bankQuestionDao;
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	private List<BankChoiceQuestion> choiceList=new ArrayList<>();
	private List<BankBlankFillingQuestion> blankFillingList = new ArrayList<>();
	private List<BankJudgeQuestion> judgeList = new ArrayList<>();

	@Override
	public String execute() throws Exception {
		logger.info("下载导出的word文件(*.docx)");
		ActionContext ctx=ActionContext. getContext();
		String stuIds = ctx.getParameters().get("stu_id").getValue();
        String examIds=ctx.getParameters().get("exam_id").getValue();
        String examStrategyIds=ctx.getParameters().get("exam_strategy_id").getValue();
        int stuId=Integer.parseInt( stuIds.trim());
        int examId=Integer.parseInt( examIds.trim());
        int examStrategyId=Integer.parseInt( examStrategyIds.trim());
        logger.info("**********"+stuId);
        logger.info("**********"+examId);
        logger.info("**********"+examStrategyId);
		
		
		Student student = studentDao.findById(stuId);
        Exam exam = examDao.findById(examId);
        
        List<ExamQuestion> eqList = examQuestionDao.findByExam(exam);
        
        choiceList=new ArrayList<>();
        blankFillingList = new ArrayList<>();
        judgeList = new ArrayList<>();
        
        Map<QuestionType,List<Integer>> eqIdMap = new HashMap<>();
        Map<QuestionType,List<Object>> examAnswerMap = new HashMap<>();// 正确答案
        Map<QuestionType,List<Object>> examQuestionAnswerMap = new HashMap<>(); //学生的答案
        
        for(ExamQuestion eq:eqList){
        	if(eq.getQuestionType()==QuestionType.CHOICE.ordinal()){
        		BankChoiceQuestion choiceQ=bankQuestionDao.findChoiceById(eq.getBankChoiceQuestion().getId());
        		//
        		choiceQ.setChoiceA(QuestionUtils.deleteOptionLetter(choiceQ.getChoiceA()));
        		choiceQ.setChoiceB(QuestionUtils.deleteOptionLetter(choiceQ.getChoiceB()));
        		choiceQ.setChoiceC(QuestionUtils.deleteOptionLetter(choiceQ.getChoiceC()));
        		choiceQ.setChoiceD(QuestionUtils.deleteOptionLetter(choiceQ.getChoiceD()));
        		choiceQ.setChoiceE(QuestionUtils.deleteOptionLetter(choiceQ.getChoiceE()));
        		choiceQ.setChoiceF(QuestionUtils.deleteOptionLetter(choiceQ.getChoiceF()));
        		choiceQ.setChoiceG(QuestionUtils.deleteOptionLetter(choiceQ.getChoiceG()));
        		choiceQ.setChoiceH(QuestionUtils.deleteOptionLetter(choiceQ.getChoiceH()));
        		//
        		choiceList.add(choiceQ);
        		
    			List<Object> answersList = examAnswerMap.get(QuestionType.CHOICE);
    			List<Object> questionAnswersList = examQuestionAnswerMap.get(QuestionType.CHOICE);
    			List<Integer> eqIdList = eqIdMap.get(QuestionType.CHOICE);
    			if(answersList==null){
    				answersList = new ArrayList<Object>();
    				questionAnswersList = new ArrayList<Object>();
    				eqIdList = new ArrayList<Integer>();
    			}
    			answersList.add(choiceQ.getAnswer());
    			ExamQuestionAnswer eqa = examQuestionAnswerDao.findByStudentAndExamQuestion(student,eq);
    			questionAnswersList.add(eqa.getAnswer());
    			
    			examQuestionAnswerMap.put(QuestionType.CHOICE, questionAnswersList);
    			examAnswerMap.put(QuestionType.CHOICE, answersList);
    			eqIdList.add(eq.getId());
    			eqIdMap.put(QuestionType.CHOICE, eqIdList);
        	}else if(eq.getQuestionType()==QuestionType.BLANK_FILLING.ordinal()){
        		BankBlankFillingQuestion blankQ=bankQuestionDao.findBlankFillingById(eq.getBankBlankFillingQuestion().getId());
        		blankFillingList.add(blankQ);
        		
    			List<Object> answersList = examAnswerMap.get(QuestionType.BLANK_FILLING);
    			List<Object> questionAnswersList = examQuestionAnswerMap.get(QuestionType.BLANK_FILLING);
    			List<Integer> eqIdList = eqIdMap.get(QuestionType.BLANK_FILLING);
    			if(answersList==null){
    				answersList = new ArrayList<Object>();
    				questionAnswersList = new ArrayList<Object>();
    				eqIdList = new ArrayList<Integer>();
    			}
    			int blankCnt = countBlank(blankQ.getContent());
    			List<String> currentAnswerList = new ArrayList<>();
    			for(int i=0; i<blankCnt; i++){
    				String theAnswer = getBlankAnswerN(blankQ,i);
    				currentAnswerList.add(theAnswer);
    			}
    			answersList.add(currentAnswerList);
    			examAnswerMap.put(QuestionType.BLANK_FILLING, answersList);
    			
    			ExamQuestionAnswer eqa = examQuestionAnswerDao.findByStudentAndExamQuestion(student,eq);
    			questionAnswersList.add(getQuestionAnswerListForBlank(eqa.getAnswer()));
    			examQuestionAnswerMap.put(QuestionType.BLANK_FILLING, questionAnswersList);
    			
    			eqIdList.add(eq.getId());
    			eqIdMap.put(QuestionType.BLANK_FILLING, eqIdList);
        	}else if(eq.getQuestionType()==QuestionType.JUDGE.ordinal()){
        		BankJudgeQuestion judgeQ = bankQuestionDao.findJudgeById(eq.getBankJudgeQuestion().getId());
        		judgeList.add(judgeQ);
        		
    			List<Object> answersList = examAnswerMap.get(QuestionType.JUDGE);
    			List<Object> questionAnswersList = examQuestionAnswerMap.get(QuestionType.JUDGE);
    			List<Integer> eqIdList = eqIdMap.get(QuestionType.JUDGE);
    			if(answersList==null){
    				answersList = new ArrayList<Object>();
    				questionAnswersList = new ArrayList<Object>();
    				eqIdList = new ArrayList<Integer>();
    			}
    			answersList.add(judgeQ.getAnswer());
    			examAnswerMap.put(QuestionType.JUDGE, answersList);
    			
    			ExamQuestionAnswer eqa = examQuestionAnswerDao.findByStudentAndExamQuestion(student,eq);
    			questionAnswersList.add(eqa.getAnswer());
    			examQuestionAnswerMap.put(QuestionType.JUDGE, questionAnswersList);
    			
    			eqIdList.add(eq.getId());
    			eqIdMap.put(QuestionType.JUDGE, eqIdList);
        	}
        }
        
        String tmpFilePath = generateDocxFromPaper( choiceList, blankFillingList, judgeList, examQuestionAnswerMap );
        
        inputStream = new FileInputStream(tmpFilePath);
		logger.info("*******inputStream="+inputStream);
		fileName=new String((exam.getName()+".docx").getBytes(),"ISO8859-1");
		return SUCCESS;
	}
	
	private String generateDocxFromPaper(List<BankChoiceQuestion> choiceList,	List<BankBlankFillingQuestion> blankFillingList,
	 List<BankJudgeQuestion> judgeList,  Map<QuestionType,List<Object>> examQuestionAnswerMap){
		
		XWPFDocument xdoc = new XWPFDocument();
		XWPFParagraph xpara = xdoc.createParagraph();
		
		//选择题
		if(choiceList!=null && choiceList.size()>0){
			xpara = xdoc.createParagraph();
			XWPFRun run = xpara.createRun();
			run.setBold(true); // 加粗
			run.setText("选择题：");
			
			for(int i=0; i<choiceList.size(); i++){
				//题号
				xpara = xdoc.createParagraph();
				run = xpara.createRun();
				run.setBold(true); // 加粗
				run.setText((i+1)+".  ");
				//题干
				BankChoiceQuestion bq = choiceList.get(i);
				run = xpara.createRun();
				run.setText(bq.getContent());
				//选项A
				xpara = xdoc.createParagraph();
				run = xpara.createRun();
				run.setBold(true); // 加粗
				run.setText("A. ");
				run = xpara.createRun();
				run.setText(bq.getChoiceA());
				//选项B
				xpara = xdoc.createParagraph();
				run = xpara.createRun();
				run.setBold(true); // 加粗
				run.setText("B. ");
				run = xpara.createRun();
				run.setText(bq.getChoiceB());
				//选项C
				xpara = xdoc.createParagraph();
				run = xpara.createRun();
				run.setBold(true); // 加粗
				run.setText("C. ");
				run = xpara.createRun();
				run.setText(bq.getChoiceC());
				//选项D
				xpara = xdoc.createParagraph();
				run = xpara.createRun();
				run.setBold(true); // 加粗
				run.setText("D. ");
				run = xpara.createRun();
				run.setText(bq.getChoiceD());
				//答案
				String correctAnswer=bq.getAnswer();
				String yourAnswer = (String)(examQuestionAnswerMap.get(QuestionType.CHOICE).get(i));
				xpara = xdoc.createParagraph();
				run = xpara.createRun();
				if(yourAnswer==null || yourAnswer!=null && false==yourAnswer.trim().equals(correctAnswer.trim())){
					run.setColor("FF0000");
				}
				run.setText("你的答案是："+yourAnswer+"，正确答案是："+correctAnswer);
				xpara = xdoc.createParagraph();//一个空行
			}
			
		}
		
		//填空题
		if(blankFillingList!=null && blankFillingList.size()>0){
			xpara = xdoc.createParagraph();
			XWPFRun run = xpara.createRun();
			run.setBold(true); // 加粗
			run.setText("填空题：");
			
			for(int i=0; i<blankFillingList.size(); i++){
				//题号
				xpara = xdoc.createParagraph();
				run = xpara.createRun();
				run.setBold(true); // 加粗
				run.setText((i+1)+".  ");
				//题干
				BankBlankFillingQuestion bq = blankFillingList.get(i);
				run = xpara.createRun();
				run.setText(bq.getContent());
				//答案
				String correctAnswer=bq.getAnswer();
				@SuppressWarnings("unchecked")
				String yourAnswer =((List<String>)(examQuestionAnswerMap.get(QuestionType.BLANK_FILLING).get(i))).get(0);
				xpara = xdoc.createParagraph();
				run = xpara.createRun();
				if(yourAnswer==null || yourAnswer!=null && false==yourAnswer.trim().equals(correctAnswer.trim())){
					run.setColor("FF0000");
				}
				run.setText("你的答案是："+yourAnswer+"，正确答案是："+correctAnswer);
				xpara = xdoc.createParagraph();//一个空行
			}
		}
		
		//判断题
		if(judgeList!=null && judgeList.size()>0){
			xpara = xdoc.createParagraph();
			XWPFRun run = xpara.createRun();
			run.setBold(true); // 加粗
			run.setText("判断题：");
			
			for(int i=0; i<judgeList.size(); i++){
				//题号
				xpara = xdoc.createParagraph();
				run = xpara.createRun();
				run.setBold(true); // 加粗
				run.setText((i+1)+".");
				//题干
				BankJudgeQuestion bq = judgeList.get(i);
				run = xpara.createRun();
				run.setText(bq.getContent());
				//答案
				String correctAnswer=bq.getAnswer();
				String yourAnswer = (String)(examQuestionAnswerMap.get(QuestionType.JUDGE).get(i));
				xpara = xdoc.createParagraph();
				run = xpara.createRun();
				if(yourAnswer==null || yourAnswer!=null && false==yourAnswer.trim().equals(correctAnswer.trim())){
					run.setColor("FF0000");
				}
				run.setText("你的答案是："+yourAnswer+"，正确答案是："+correctAnswer);
				xpara = xdoc.createParagraph();//一个空行
			}
		}
		
		
		try {
			File tmpFile = File.createTempFile(Long.toString(new Date().getTime()),".docx");
			OutputStream os = new FileOutputStream(tmpFile);
			xdoc.write(os);
			xdoc.close();
			return tmpFile.getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
		
	}

	/*
	 * 统计填空题中空的个数
	 */
	private int countBlank(String content){
		Pattern p = Pattern.compile("[_]{2,}");//含有至少两个_符号表示空白
		Matcher m = p.matcher(content);
		int cnt=0;
		while (m.find()) {
		    cnt++;
		}
		return cnt;
	}
	
	/*
	 * 根据填空题的空白位置返回答案, idx从0开始
	 */
	private String getBlankAnswerN(BankBlankFillingQuestion q, int idx){
		String answer = null;
		switch(idx){
			case 0:
				answer = q.getAnswer();
				break;
			case 1:
				answer = q.getAnswer2();
				break;
			case 2:
				answer = q.getAnswer3();
				break;
			case 3:
				answer = q.getAnswer4();
				break;
			case 4:
				answer = q.getAnswer5();
				break;
			case 5:
				answer = q.getAnswer6();
				break;
			case 6:
				answer = q.getAnswer7();
				break;
			case 7:
				answer = q.getAnswer8();
				break;
			default:
				answer = q.getAnswer();
				break;
		}
		return answer;
	}
	
	private List<String> getQuestionAnswerListForBlank(String blankQuestionAnswerStr){
		List<String> answerList = new ArrayList<>();
		String[] answersArray = blankQuestionAnswerStr.split(",");
		for(String answer: answersArray){
			int endIdx = answer.indexOf("]]]");
			answerList.add(answer.trim().substring(3,endIdx));
		}
		return answerList;
	}
}
