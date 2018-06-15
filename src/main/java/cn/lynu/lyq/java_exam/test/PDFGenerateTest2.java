package cn.lynu.lyq.java_exam.test;

import java.io.FileOutputStream;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import cn.lynu.lyq.java_exam.dao.BankQuestionDao;
import cn.lynu.lyq.java_exam.entity.BankBlankFillingQuestion;
import cn.lynu.lyq.java_exam.entity.BankChoiceQuestion;
import cn.lynu.lyq.java_exam.entity.BankJudgeQuestion;

public class PDFGenerateTest2 {

	public static void main(String[] args) throws Exception{
		System.out.println("\u2713");
		System.out.println("\u2717");
		Document doc = new Document(PageSize.A4);
		PdfWriter pdfWriter	= PdfWriter.getInstance(doc, new FileOutputStream("c:\\Problems.pdf"));
		doc.open();
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BankQuestionDao bankQuestionDao = (BankQuestionDao) context.getBean("bankQuestionDao");
		for(int i=1; i<=5; i++){
			BankChoiceQuestion bq = bankQuestionDao.findChoiceById(i);
			addChoiceQuestionAndAnswer(doc,bq);
		}
		
		for(int i=1; i<=5; i++){
			BankJudgeQuestion bq = bankQuestionDao.findJudgeById(i);
			addJudgeQuestionAndAnswer(doc,bq);
		}
		
		for(int i=1; i<=5; i++){
			BankBlankFillingQuestion bq = bankQuestionDao.findBlankFillingById(i);
			addBlankFillingQuestionAndAnswer(doc,bq);
		}
		doc.close();
		context.close();
	}
	
	public static void addChoiceQuestionAndAnswer(Document doc, BankChoiceQuestion bq) throws Exception{
		
		BaseFont bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/SIMKAI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  
		Font fontCn = new Font(bfChinese, 20, Font.NORMAL);
		Font fontCn2 = new Font(bfChinese, 12, Font.NORMAL);
		Font fontEn = new Font(Font.FontFamily.TIMES_ROMAN,19,Font.NORMAL);
		
		addParagraphForChineseAndEnglish(bq.getId()+"."+bq.getContent(),doc,fontCn,fontEn);
		addChunkForChineseAndEnglish(bq.getChoiceA(),doc,fontCn,fontEn);
		doc.add(Chunk.NEWLINE);
		addChunkForChineseAndEnglish(bq.getChoiceB(),doc,fontCn,fontEn);
		doc.add(Chunk.NEWLINE);
		addChunkForChineseAndEnglish(bq.getChoiceC(),doc,fontCn,fontEn);
		doc.add(Chunk.NEWLINE);
		addChunkForChineseAndEnglish(bq.getChoiceD(),doc,fontCn,fontEn);
		doc.add(Chunk.NEWLINE);
		addChunkForChineseAndEnglish("答案是："+bq.getAnswer(),doc,fontCn,fontEn);
		
		Chunk ck=new Chunk(bq.getKnowledgePoint().substring(0, bq.getKnowledgePoint().indexOf('*')),fontCn2);
		ck.setBackground(BaseColor.ORANGE, 1f,2f,2f,1f);
		ck.setTextRise(12.5f);
		doc.add(ck);
		
		doc.add(Chunk.NEWLINE);
		doc.add(Chunk.NEWLINE);
		
	}
	
	
	public static void addJudgeQuestionAndAnswer(Document doc, BankJudgeQuestion bq) throws Exception{
		
		BaseFont bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/SIMKAI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  
		Font fontCn = new Font(bfChinese, 20, Font.NORMAL);
		Font fontCn2 = new Font(bfChinese, 12, Font.NORMAL);
		Font fontEn = new Font(Font.FontFamily.TIMES_ROMAN,19,Font.NORMAL);
		
		addParagraphForChineseAndEnglish(bq.getId()+"."+bq.getContent(),doc,fontCn,fontEn);
		doc.add(new Chunk("答案是："+(bq.getAnswer().equals("T")?"正确":"错误"),fontCn));
		
		Chunk ck=new Chunk(bq.getKnowledgePoint().substring(0, bq.getKnowledgePoint().indexOf('*')),fontCn2);
		ck.setBackground(BaseColor.ORANGE, 1f,2f,2f,1f);
		ck.setTextRise(12.5f);
		doc.add(ck);
		
		doc.add(Chunk.NEWLINE);
		doc.add(Chunk.NEWLINE);
	}
	
	public static void addBlankFillingQuestionAndAnswer(Document doc, BankBlankFillingQuestion bq) throws Exception{
		
		BaseFont bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/SIMKAI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  
		Font fontCn = new Font(bfChinese, 20, Font.NORMAL);
		Font fontCn2 = new Font(bfChinese, 12, Font.NORMAL);
		Font fontEn = new Font(Font.FontFamily.TIMES_ROMAN,19,Font.NORMAL);
		
		addParagraphForChineseAndEnglish(bq.getId()+"."+bq.getContent(),doc,fontCn,fontEn);
		addChunkForChineseAndEnglish("答案是："+bq.getAnswer(),doc,fontCn,fontEn);
		
		Chunk ck=new Chunk(bq.getKnowledgePoint().substring(0, bq.getKnowledgePoint().indexOf('*')),fontCn2);
		ck.setBackground(BaseColor.ORANGE, 1f,2f,2f,1f);
		ck.setTextRise(12.5f);
		doc.add(ck);
		
		doc.add(Chunk.NEWLINE);
		doc.add(Chunk.NEWLINE);
	}
	
	public static void addChunkForChineseAndEnglish(String str, Document doc, Font fontCn, Font fontEn) throws DocumentException{
		StringBuilder sb = new StringBuilder();
		char[] chars = str.toCharArray();
		for(int i=0; i<chars.length; i++){
			if(i==0){
				sb.append(chars[i]);
			}else{
				if(isChinese(chars[i]) && !isChinese(chars[i-1])){
					doc.add(new Chunk(sb.toString(),fontEn));
					sb = new StringBuilder();
					sb.append(chars[i]);
				}else if(!isChinese(chars[i]) && isChinese(chars[i-1])){
					doc.add(new Chunk(sb.toString(),fontCn));
					sb = new StringBuilder();
					sb.append(chars[i]);
				}else{
					sb.append(chars[i]);
				}
			}
			
			if(i==chars.length-1){
				if(isChinese(chars[i])){
					doc.add(new Chunk(sb.toString(),fontCn));
				}else{
					doc.add(new Chunk(sb.toString(),fontEn));
				}
			}
		}
		
	}
	
	public static void addParagraphForChineseAndEnglish(String str, Document doc, Font fontCn, Font fontEn) throws DocumentException{
		StringBuilder sb = new StringBuilder();
		Paragraph para = new Paragraph();
		char[] chars = str.toCharArray();
		for(int i=0; i<chars.length; i++){
			if(i==0){
				sb.append(chars[i]);
			}else{
				if(isChinese(chars[i]) && !isChinese(chars[i-1])){
					para.add(new Chunk(sb.toString(),fontEn));
					sb = new StringBuilder();
					sb.append(chars[i]);
				}else if(!isChinese(chars[i]) && isChinese(chars[i-1])){
					para.add(new Chunk(sb.toString(),fontCn));
					sb = new StringBuilder();
					sb.append(chars[i]);
				}else{
					sb.append(chars[i]);
				}
			}
			
			if(i==chars.length-1){
				if(isChinese(chars[i])){
					para.add(new Chunk(sb.toString(),fontCn));
				}else{
					para.add(new Chunk(sb.toString(),fontEn));
				}
			}
		}
		para.setLeading(25f);
		doc.add(para);
	}
	
	private static final boolean isChinese(char c) {  
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
            return true;  
        }  
        return false;  
    }  

}
