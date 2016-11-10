package cn.lynu.lyq.java_exam.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlankFillingTest1 {

	public static void main(String[] args) {
//		String str="填空_____号码，好的___，哈哈，____";
//		Pattern p=Pattern.compile("[_]{2,}");
//		Matcher m = p.matcher(str);
//		while(m.find()){
//			m.re
//		}
//		String str2 = str.replaceAll("[_]{2,}", "<input type=\"text\">");
//		System.out.println(str2);

		Pattern p = Pattern.compile("cat");
		 Matcher m = p.matcher("one cat two cats in the yard");
		 StringBuffer sb = new StringBuffer();
		 int i=1;
		 while (m.find()) {
		     m.appendReplacement(sb, "dog"+i);
		     i++;
		 }
		 m.appendTail(sb);
		 System.out.println(sb.toString());
	}

}
