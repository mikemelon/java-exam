package cn.lynu.lyq.java_exam.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class WordGernerateTest1 {

	public static void main(String[] args) throws IOException {
		File file = new File("c:\\test001.docx");
//		InputStream is = new FileInputStream();
		XWPFDocument xdoc = new XWPFDocument();
		XWPFParagraph xpara = xdoc.createParagraph();
		 
		// 一个XWPFRun代表具有相同属性的一个区域。
		XWPFRun run = xpara.createRun();
		run.setBold(true); // 加粗
		run.setText("加粗的内容");
		
		run = xpara.createRun();
		run.setColor("FF0000");
		run.setFontSize(15);
		run.setText("\n\n插入内容。");
		
		xpara = xdoc.createParagraph();
		xpara.setFirstLineIndent(20);
		run = xpara.createRun();
		run.setText("aaaaa");
		
		OutputStream os = new FileOutputStream(file);
		xdoc.write(os);
		
		xdoc.close();
		os.close();

	}

}
