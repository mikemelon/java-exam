package cn.lynu.lyq.java_exam.utils;

public class QuestionUtils {
	/**
	 * 数据库表中，选择题的选项，前面有可能已经有"A.","B."这样的字符串了，该方法检测到有的话就去掉。
	 * @param choiceOption
	 * @return
	 */
	public static String deleteOptionLetter(String choiceOption){
		if(choiceOption!=null && choiceOption.length()>1){
			choiceOption = choiceOption.trim();
			char optionLetter = choiceOption.charAt(0);
			char dotChar = choiceOption.charAt(1);
			if("ABCDEFGH".contains(String.valueOf(optionLetter)) )  
				choiceOption = choiceOption.substring(1);
			if(dotChar=='.' || dotChar=='．')
				choiceOption = choiceOption.substring(1);
			return choiceOption.trim();
		}else
			return "";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
