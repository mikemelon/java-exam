package cn.lynu.lyq.java_exam.test;

import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerateTest1 {

	public static void main(String[] args) throws Exception{
		Document doc = new Document(PageSize.A4);
		PdfWriter pdfWriter	= PdfWriter.getInstance(doc, new FileOutputStream("c:\\HelloWorld.pdf"));
		doc.open();
		
		Font font = new Font(Font.FontFamily.HELVETICA,18,Font.BOLD);
		BaseFont bfChinese = BaseFont.createFont("STSong-Light,Bold", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		//也可以使用Windows系统字体(TrueType)  
        bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/SIMKAI.TTF", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);  
		Font fontChinese = new Font(bfChinese, 20, Font.NORMAL);
		
		Chunk ck1 = new Chunk("China中国",fontChinese);
		ck1.setTextRise(9.8f);
		doc.add(ck1);
//		doc.add(Chunk.NEWLINE);
		
		Chunk ck2= new Chunk("USA");
		ck2.setBackground(BaseColor.ORANGE,1f,0.5f,1f,5.5f);
		doc.add(ck2);
		
		doc.add(new Paragraph("Hello World!你好，世界",fontChinese));
		doc.add(new Paragraph("You are Welcome!"));
		
//		Image img = Image.getInstance("C:\\Users\\mikemelon\\Pictures\\1.png");
//		doc.add(img);
		
		doc.newPage();    
		doc.add(new Phrase("Phrase page"));    
		doc.add(Chunk.NEWLINE);
		
		Phrase director = new Phrase();    
		Chunk name = new Chunk("China");    
		name.setUnderline(0.2f, -2f);    
		director.add(name);    
		director.add(new Chunk(","));    
		director.add(new Chunk(" "));    
		director.add(new Chunk("chinese"));    
		director.setLeading(24);    
		doc.add(director); 
		
		doc.add(Chunk.NEWLINE);
		addChoiceQuestionAndAnswer(doc);
		
		doc.close();
	}
	
	public static void addChoiceQuestionAndAnswer(Document doc) throws Exception{
		BaseFont bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/SIMKAI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  
		Font fontCn = new Font(bfChinese, 20, Font.NORMAL);
		Font fontEn = new Font(Font.FontFamily.TIMES_ROMAN,19,Font.NORMAL);
		
		doc.add(new Chunk("1.",fontCn));
		doc.add(new Chunk("下面不是Java的三大平台是：\t\t\t( )",fontCn));
		doc.add(Chunk.NEWLINE);
		doc.add(new Chunk("A. Java SE",fontEn));
		doc.add(Chunk.NEWLINE);
		doc.add(new Chunk("B. Java EE",fontEn));
		doc.add(Chunk.NEWLINE);
		doc.add(new Chunk("C. Java ME",fontEn));
		doc.add(Chunk.NEWLINE);
		doc.add(new Chunk("D. Java BE",fontEn));
		doc.add(Chunk.NEWLINE);
		doc.add(new Chunk("答案:B",fontCn));
		doc.add(Chunk.NEWLINE);
		addChunkForChineseAndEnglish("这是一个Java语言的关键字main、abstract，false的测试！数组int[] k={1,2,3,4}",doc,fontCn,fontEn);
		
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
