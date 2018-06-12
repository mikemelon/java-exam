package cn.lynu.lyq.java_exam.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class PropertyUtils {
	
	private static InputStream is;
	private static OutputStream os;
	
	static{
		try {
			File propFile = new File(new URL(PropertyUtils.class.getResource("/")+"mysetting.properties").toURI());
			System.out.println(propFile.getAbsolutePath());
			is = new FileInputStream(propFile);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static String getProperty(String key){
		Properties prop = new Properties();
		try {
			prop.load(is);
			return prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void setProperty(String key, String value){
		Properties prop = new Properties();
		try {
			prop.load(is);
			
			System.out.println(prop.keySet());
			prop.setProperty(key, value);
			File propFile = new File(new URL(PropertyUtils.class.getResource("/")+"mysetting.properties").toURI());
			os = new FileOutputStream(propFile);
			prop.store(os, "modified at "+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException, URISyntaxException {
//		InputStream is = ClassLoader.getSystemResourceAsStream("mysetting.properties");
//		Properties prop = new Properties();
//		prop.load(is);
//		prop.list(System.out);
//		
//		FileOutputStream fos = new FileOutputStream(new File(ClassLoader.getSystemResource("mysetting.properties").toURI()));
//		prop.setProperty("lyqtest1", "bbb");
//		prop.store(fos, "store something2");
//		prop.list(System.out);
		
		String test1 = getProperty("lyqtest1");
		System.out.println(test1);
		setProperty("lyqtest0000","mmmmmmmmmmmmm");

	}

}
