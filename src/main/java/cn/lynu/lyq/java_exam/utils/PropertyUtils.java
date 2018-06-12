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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyUtils {
	private final static Logger logger = LoggerFactory.getLogger(PropertyUtils.class);
	private static InputStream is;
	private static OutputStream os;
	private static File propFile;
	
	public static String getProperty(String key){
		Properties prop = new Properties();
		try {
			propFile = new File(new URL(PropertyUtils.class.getResource("/")+"mysetting.properties").toURI());
			logger.info(propFile.getAbsolutePath());
			is = new FileInputStream(propFile);
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
			propFile = new File(new URL(PropertyUtils.class.getResource("/")+"mysetting.properties").toURI());
			is = new FileInputStream(propFile);
			prop.load(is);
			
			System.out.println(prop.keySet());
			prop.setProperty(key, value);
			os = new FileOutputStream(propFile);
			prop.store(os, "modified at "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
		setProperty("lyqtest1","bbbbb");

	}

}
