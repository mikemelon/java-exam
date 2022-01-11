package cn.lynu.lyq.java_exam.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.lynu.lyq.java_exam.dao.CourseDao;
import cn.lynu.lyq.java_exam.entity.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.lynu.lyq.java_exam.dao.BankQuestionDao;

@Component("bankQuestionDao")
@Transactional
public class BankQuestionDaoImpl implements BankQuestionDao {
	private final static Logger logger = LoggerFactory.getLogger(BankQuestionDaoImpl.class);
	@Resource
	protected SessionFactory sessionFactory;
	@Resource
	private CourseDao courseDao;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<BankQuestion> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List<BankChoiceQuestion> list1=(List<BankChoiceQuestion>)(session.createQuery("from BankChoiceQuestion").list());
		List<BankBlankFillingQuestion> list2=(List<BankBlankFillingQuestion>)(session.createQuery("from BankBlankFillingQuestion").list());
		List<BankJudgeQuestion> list3=(List<BankJudgeQuestion>)(session.createQuery("from BankJudgeQuestion").list());
		List<BankQuestion> resultList = new ArrayList<BankQuestion>();
		resultList.addAll(list1);
		resultList.addAll(list2);
		resultList.addAll(list3);
		return resultList;
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public BankChoiceQuestion findChoiceById(int id){
		return sessionFactory.getCurrentSession().get(BankChoiceQuestion.class, id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public BankBlankFillingQuestion findBlankFillingById(int id){
		return sessionFactory.getCurrentSession().get(BankBlankFillingQuestion.class, id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public BankJudgeQuestion findJudgeById(int id){
		return sessionFactory.getCurrentSession().get(BankJudgeQuestion.class, id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int countAllChoice() {
		Object obj = sessionFactory.getCurrentSession().createQuery("select count(*) from BankChoiceQuestion").uniqueResult();
		return ((Number)obj).intValue();
	}
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int countAllBlankFilling() {
		Object obj = sessionFactory.getCurrentSession().createQuery("select count(*) from BankBlankFillingQuestion").uniqueResult();
		return ((Number)obj).intValue();
	}
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int countAllJudge() {
		Object obj = sessionFactory.getCurrentSession().createQuery("select count(*) from BankJudgeQuestion").uniqueResult();
		return ((Number)obj).intValue();
	}

	@Override
	public Course queryCourse(String name) {
		List<Course> courses = courseDao.findAll();
		if (courses.size()>0){
			return courses.get(0);
		}else {
			Course course = new Course(name);
			courseDao.save(course);
			return course;
		}
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<BankChoiceQuestion> findAllChoice() {
		@SuppressWarnings("unchecked")
		List<BankChoiceQuestion> list=(List<BankChoiceQuestion>)(sessionFactory.getCurrentSession().createQuery("from BankChoiceQuestion").list());
		return list;
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<BankBlankFillingQuestion> findAllBlankFilling() {
		@SuppressWarnings("unchecked")
		List<BankBlankFillingQuestion> list=(List<BankBlankFillingQuestion>)(sessionFactory.getCurrentSession().createQuery("from BankBlankFillingQuestion").list());
		return list;
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<BankJudgeQuestion> findAllJudge() {
		@SuppressWarnings("unchecked")
		List<BankJudgeQuestion> list=(List<BankJudgeQuestion>)(sessionFactory.getCurrentSession().createQuery("from BankJudgeQuestion").list());
		return list;
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<BankChoiceQuestion> findChoiceWithComposeFlag(int composeFlag) {
		Query q = sessionFactory.getCurrentSession().createQuery("from BankChoiceQuestion where composeFlag=:c0");
		q.setInteger("c0", composeFlag);
		@SuppressWarnings("unchecked")
		List<BankChoiceQuestion> list=(List<BankChoiceQuestion>)(q.list());
		return list;
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<BankBlankFillingQuestion> findBlankFillingWithComposeFlag(int composeFlag) {
		Query q = sessionFactory.getCurrentSession().createQuery("from BankBlankFillingQuestion where composeFlag=:c0");
		q.setInteger("c0", composeFlag);
		@SuppressWarnings("unchecked")
		List<BankBlankFillingQuestion> list=(List<BankBlankFillingQuestion>)(q.list());
		return list;
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<BankJudgeQuestion> findJudgeWithComposeFlag(int composeFlag) {
		Query q = sessionFactory.getCurrentSession().createQuery("from BankJudgeQuestion where composeFlag=:c0");
		q.setInteger("c0", composeFlag);
		@SuppressWarnings("unchecked")
		List<BankJudgeQuestion> list=(List<BankJudgeQuestion>)(q.list());
		return list;
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<BankChoiceQuestion> findAllChoiceWithPage(int pageIndex, int pageSize) {
		Query q=sessionFactory.getCurrentSession().createQuery("from BankChoiceQuestion");
		int firstResult = pageIndex*pageSize;
		q.setFirstResult(firstResult);
		q.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<BankChoiceQuestion> list=(List<BankChoiceQuestion>)q.list();
		return list;
	}
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<BankBlankFillingQuestion> findAllBlankFillingWithPage(int pageIndex, int pageSize) {
		Query q=sessionFactory.getCurrentSession().createQuery("from BankBlankFillingQuestion");
		int firstResult = pageIndex*pageSize;
		q.setFirstResult(firstResult);
		q.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<BankBlankFillingQuestion> list=(List<BankBlankFillingQuestion>)q.list();
		return list;
	}
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<BankJudgeQuestion> findAllJudgeWithPage(int pageIndex, int pageSize) {
		Query q=sessionFactory.getCurrentSession().createQuery("from BankJudgeQuestion");
		int firstResult = pageIndex*pageSize;
		q.setFirstResult(firstResult);
		q.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<BankJudgeQuestion> list=(List<BankJudgeQuestion>)q.list();
		return list;
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<BankChoiceQuestion> findChoiceForSearch(String content, String choice, String answer,
			String knowledge) {
		StringBuffer qStrSb =  new StringBuffer("from BankChoiceQuestion where content like :c0 ");
		boolean choiceNotNull=false;
		if(choice!=null && !choice.equals("")){
			qStrSb.append(" and (choiceA like :c1 or choiceB like :c1  or choiceC like :c1 or choiceD like :c1 ");
			qStrSb.append(" or choiceE like :c1 or choiceF like :c1 or choiceG like :c1 or choiceH like :c1 ) ");
			choiceNotNull=true;
		}
		boolean answerNotNull=false;
		if(answer!=null && !answer.equals("")){
			qStrSb.append(" and answer like :a ");
			answerNotNull=true;
		}
		boolean knowledgeNotNull=false;
		if(knowledge!=null && !knowledge.equals("")){
			qStrSb.append(" and knowledgePoint like :k ");
			knowledgeNotNull=true;
		}
		Query query = sessionFactory.getCurrentSession().createQuery(qStrSb.toString());
		query.setString("c0", "%"+content+"%");
		if(answerNotNull){
			String answerQuery="";
			if(answer.length()>1){//多选的答案
				String[] answers=answer.split(",");
				for(String a:answers){
					if(a!=null && !a.equals(""))
					answerQuery+=a.trim()+",";
				}
				answerQuery=answerQuery.substring(0,answerQuery.length()-1);
			}else{
				answerQuery=answer;
			}
			logger.debug("answerQuery=["+answerQuery+"]");
			logger.debug("answerQuery.length()="+answerQuery.length());
			query.setString("a", "%"+answerQuery+"%");
		}
		if(choiceNotNull){
			query.setString("c1","%"+ choice+"%");
		}
		if(knowledgeNotNull){
			query.setString("k", "%"+knowledge+"%");
		}
		
		@SuppressWarnings("unchecked")
		List<BankChoiceQuestion> list=(List<BankChoiceQuestion>)query.list();
		return list;
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<BankBlankFillingQuestion> findBlankForSearch(String content, String answer, String knowledge) {
		StringBuffer qStrSb =  new StringBuffer("from BankBlankFillingQuestion where content like :c0 ");
		boolean answerNotNull=false;
		if(!answer.equals("")){
			qStrSb.append(" and (answer like :a or answer2 like :a or answer3 like :a or answer4 like :a ");
			qStrSb.append(" or answer5 like :a or answer6 like :a or answer7 like :a or answer8 like :a ) ");
			answerNotNull=true;
		}
		boolean knowledgeNotNull=false;
		if(!knowledge.equals("")){
			qStrSb.append(" and knowledgePoint like :k ");
			knowledgeNotNull=true;
		}
		Query query = sessionFactory.getCurrentSession().createQuery(qStrSb.toString());
		query.setString("c0", "%"+content+"%");
		
		if(answerNotNull){
			query.setString("a", "%"+answer+"%");
		}
		if(knowledgeNotNull){
			query.setString("k", "%"+knowledge+"%");
		}
		@SuppressWarnings("unchecked")
		List<BankBlankFillingQuestion> list=(List<BankBlankFillingQuestion>)query.list();
		return list;
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<BankJudgeQuestion> findJudgeForSearch(String content, String answer, String knowledge) {
		StringBuffer qStrSb =  new StringBuffer("from BankJudgeQuestion where content like :c0 ");
		boolean answerNotNull=false;
		if(answer!=null && !answer.equals("")){
			qStrSb.append(" and answer=:a ");
			answerNotNull=true;
		}
		boolean knowledgeNotNull=false;
		if(!knowledge.equals("")){
			qStrSb.append(" and knowledgePoint like :k ");
			knowledgeNotNull=true;
		}
		Query query = sessionFactory.getCurrentSession().createQuery(qStrSb.toString());
		query.setString("c0", "%"+content+"%");
		
		if(answerNotNull){
			query.setString("a", answer);
		}
		if(knowledgeNotNull){
			query.setString("k", "%"+knowledge+"%");
		}
		@SuppressWarnings("unchecked")
		List<BankJudgeQuestion> list=(List<BankJudgeQuestion>)query.list();
		return list;
	}
	
	@Override
	public void save(BankQuestion bq){
		sessionFactory.getCurrentSession().save(bq);
	}
	
	@Override
	public void update(BankQuestion bq){
		sessionFactory.getCurrentSession().update(bq);
	}
	
	@Override
	public void delete(BankQuestion bq){
		sessionFactory.getCurrentSession().delete(bq);
	}
	
	private enum ImportStringLineTypeForChoice{CONTENT,OPTION_A,OPTION_B,OPTION_C,OPTION_D,OPTION_E,OPTION_F,
		OPTION_G,OPTION_H,ANSWER,KNOWLEDGE_POINT};//题干、选项(A~H)、答案、知识点
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public int importChoiceFromTxt(File txtFile) {
		BufferedReader br= null;
		int cnt = 0;
		try {
			br=new BufferedReader(new InputStreamReader(new FileInputStream(txtFile),"UTF-8"));
			String line="";
			String content = "";//题干
			String[] choices=new String[8];//选项
			String answer = "";//答案
			String knowledgePoint="";//知识点
			String CourseName = "";
			ImportStringLineTypeForChoice type=null; 
			
			while((line=br.readLine())!=null){
				line=line.replace(" ", "");//将UTF-8的特殊空格替换掉，编码c2a0
				line=line.replace((char)65279, ' ');//对有BOM头的第一行做处理
				line=line.trim();
				if(line.equals("")){
					continue;  //空行被忽略
				}else if(line.startsWith("######")) {
					if(content==null || content.equals("")){
						continue; //每题开始行的######被忽略
					}else{
//						logger.debug("题干："+content);
//						logger.debug("选项:"+Arrays.toString(choices));
//						logger.debug("答案："+answer);
//						logger.debug("知识点："+knowledgePoint);
//						logger.debug();
						
						BankChoiceQuestion q=new BankChoiceQuestion(content,choices[0],choices[1],choices[2],choices[3],
								choices[4],choices[5],choices[6],choices[7],answer,knowledgePoint, queryCourse(CourseName));
						
						sessionFactory.getCurrentSession().save(q);
						
						content = "";//题干
						choices=new String[8];//选项
						answer = "";//答案
						knowledgePoint="";//知识点
						cnt++;
					}
				}else if(line.startsWith("***")){
					knowledgePoint = line.substring(3).trim();
					type=ImportStringLineTypeForChoice.KNOWLEDGE_POINT;
				}else if(line.startsWith(">>>")){
					answer = line.substring(3).trim();
					type=ImportStringLineTypeForChoice.ANSWER;
				} else if (line.startsWith("%%%")){
					CourseName = line.substring(3).trim();
				}else{
					char firstChar = line.charAt(0);
					if(Character.isDigit(firstChar)){
						content = firstStringLineProcess(line);
						type=ImportStringLineTypeForChoice.CONTENT;
					}else if(Character.isUpperCase(firstChar)){ //不能用isLetter()，因汉字也会为true
						switch(firstChar){
							case 'A':
								choices[0]=firstStringLineProcess(line);
								type=ImportStringLineTypeForChoice.OPTION_A;
								break;
							case 'B':
								choices[1]=firstStringLineProcess(line);
								type=ImportStringLineTypeForChoice.OPTION_B;
								break;
							case 'C':
								choices[2]=firstStringLineProcess(line);
								type=ImportStringLineTypeForChoice.OPTION_C;
								break;
							case 'D':
								choices[3]=firstStringLineProcess(line);
								type=ImportStringLineTypeForChoice.OPTION_D;
								break;
							case 'E':
								choices[4]=firstStringLineProcess(line);
								type=ImportStringLineTypeForChoice.OPTION_E;
								break;
							case 'F':
								choices[5]=firstStringLineProcess(line);
								type=ImportStringLineTypeForChoice.OPTION_F;
								break;
							case 'G':
								choices[6]=firstStringLineProcess(line);
								type=ImportStringLineTypeForChoice.OPTION_G;
								break;
							case 'H':
								choices[7]=firstStringLineProcess(line);
								type=ImportStringLineTypeForChoice.OPTION_H;
								break;
						}
					}else{  //不属于上述任何一种情况，则说明题干、选项、答案、知识点占用了多行的情况。
//						logger.debug("第二行："+line);
						switch(type){
							case CONTENT:
								content+="\n"+line;
								break;
							case ANSWER:
								answer+="\n"+line;
								break;
							case KNOWLEDGE_POINT:
								knowledgePoint+="\n"+line;
								break;
							case OPTION_A:
								choices[0]+="\n"+line;
								break;
							case OPTION_B:
								choices[1]+="\n"+line;
								break;
							case OPTION_C:
								choices[2]+="\n"+line;
								break;
							case OPTION_D:
								choices[3]+="\n"+line;
								break;
							case OPTION_E:
								choices[4]+="\n"+line;
								break;
							case OPTION_F:
								choices[5]+="\n"+line;
								break;
							case OPTION_G:
								choices[6]+="\n"+line;
								break;
							case OPTION_H:
								choices[7]+="\n"+line;
								break;
						}
					}
				}
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		logger.debug("共导入了"+cnt+"道选择题！");
		return cnt;
	}
	
	private enum ImportStringLineTypeForBlank{CONTENT,ANSWER,KNOWLEDGE_POINT};//题干、答案、知识点

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public int importBlankFromTxt(File txtFile) {
		BufferedReader br= null;
		int cnt = 0;
		try {
			br=new BufferedReader(new InputStreamReader(new FileInputStream(txtFile),"UTF-8"));
			String line="";
			String content = "";//题干
			String answer = "";//答案
			String knowledgePoint="";//知识点
			String CourseName = "";
			ImportStringLineTypeForBlank type=null; 
			
			while((line=br.readLine())!=null){
				line=line.replace(" ", "");//将UTF-8的特殊空格替换掉，编码c2a0
				line=line.replace((char)65279, ' ');//对有BOM头的第一行做处理
				line=line.trim();
				if(line.equals("")){
					continue;  //空行被忽略
				}else if(line.startsWith("######")) {
					if(content==null || content.equals("")){
						continue; //每题开始行的######被忽略
					}else{
//						logger.debug("题干："+content);
//						logger.debug("答案："+answer);
//						logger.debug("知识点："+knowledgePoint);
//						logger.debug();
						
						BankBlankFillingQuestion q=null;
						Course course = queryCourse(CourseName);
						if(answer!=null && !answer.equals("")){
							if(answer.contains(",")){
								String[] answers = answer.split(",");
								switch(answers.length){
									case 2:
										q=new BankBlankFillingQuestion(content,answers[0],answers[1],knowledgePoint, course);
										break;
									case 3:
										q=new BankBlankFillingQuestion(content,answers[0],answers[1],answers[2],knowledgePoint, course);
										break;	
									case 4:
										q=new BankBlankFillingQuestion(content,answers[0],answers[1],answers[2],answers[3],knowledgePoint, course);
										break;
								}
							}else{
								q=new BankBlankFillingQuestion(content,answer,knowledgePoint, course);
							}
						}
						
						sessionFactory.getCurrentSession().save(q);
						
						content = "";//题干
						answer = "";//答案
						knowledgePoint="";//知识点
						cnt++;
					}
				}else if(line.startsWith("***")){
					knowledgePoint = line.substring(3).trim();
					type=ImportStringLineTypeForBlank.KNOWLEDGE_POINT;
				} else if (line.startsWith("%%%")){
					CourseName = line.substring(3).trim();
				}else if(line.startsWith(">>>")){
					answer = line.substring(3).trim();
					type=ImportStringLineTypeForBlank.ANSWER;
				}else{
					char firstChar = line.charAt(0);
					if(Character.isDigit(firstChar)){
						content = firstStringLineProcess(line);
						type=ImportStringLineTypeForBlank.CONTENT;
					}else{  //不属于上述任何一种情况，则说明题干、答案、知识点占用了多行的情况。
//						logger.debug("第二行："+line);
						switch(type){
							case CONTENT:
								content+="\n"+line;
								break;
							case ANSWER:
								answer+="\n"+line;
								break;
							case KNOWLEDGE_POINT:
								knowledgePoint+="\n"+line;
								break;
						}
					}
				}
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		logger.debug("共导入了"+cnt+"道填空题！");
		return cnt;
	}
	
	private enum ImportStringLineTypeForJudge{CONTENT,ANSWER,KNOWLEDGE_POINT};//题干、答案、知识点

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public int importJudgeFromTxt(File txtFile) {
		BufferedReader br= null;
		int cnt = 0;
		try {
			br=new BufferedReader(new InputStreamReader(new FileInputStream(txtFile),"UTF-8"));
			String line="";
			String content = "";//题干
			String answer = "";//答案
			String knowledgePoint="";//知识点
			String CourseName = "";
			ImportStringLineTypeForJudge type=null; 
			
			while((line=br.readLine())!=null){
				line=line.replace(" ", "");//将UTF-8的特殊空格替换掉，编码c2a0
				line=line.replace((char)65279, ' ');//对有BOM头的第一行做处理
				line=line.trim();
				if(line.equals("")){
					continue;  //空行被忽略
				}else if(line.startsWith("######")) {
					if(content==null || content.equals("")){
						continue; //每题开始行的######被忽略
					}else{
//						logger.debug("题干："+content);
//						logger.debug("答案："+answer);
//						logger.debug("知识点："+knowledgePoint);
//						logger.debug();
//						CourseName
						BankJudgeQuestion q=new BankJudgeQuestion(content,answer,knowledgePoint, queryCourse(CourseName));
						
						sessionFactory.getCurrentSession().save(q);
						
						content = "";//题干
						answer = "";//答案
						knowledgePoint="";//知识点
						cnt++;
					}
				}else if(line.startsWith("***")){
					knowledgePoint = line.substring(3).trim();
					type=ImportStringLineTypeForJudge.KNOWLEDGE_POINT;
				}else if(line.startsWith(">>>")){
					answer = line.substring(3).trim();
					type=ImportStringLineTypeForJudge.ANSWER;
				} else if (line.startsWith("%%%")){
					CourseName = line.substring(3).trim();
				}else{
					char firstChar = line.charAt(0);
					if(Character.isDigit(firstChar)){
						content = firstStringLineProcess(line);
						type=ImportStringLineTypeForJudge.CONTENT;
					}else{  //不属于上述任何一种情况，则说明题干、答案、知识点占用了多行的情况。
//						logger.debug("第二行："+line);
						switch(type){
							case CONTENT:
								content+="\n"+line;
								break;
							case ANSWER:
								answer+="\n"+line;
								break;
							case KNOWLEDGE_POINT:
								knowledgePoint+="\n"+line;
								break;
						}
					}
				}
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		logger.debug("共导入了"+cnt+"道判断题！");
		return cnt;
	}
	
	/*
	 * 对题干、选项等的第一行，先去掉第一个字符（字母或数字后），如果还有逗号或句号等分隔符，也去掉。
	 */
	private static String firstStringLineProcess(String line){
		String result = line;
		while( Character.isDigit(result.charAt(0)) ){
			result = result.substring(1);
		}
		
		char firstNoLetterChar=result.charAt(0);
		if(firstNoLetterChar=='.' || firstNoLetterChar=='．' || firstNoLetterChar=='。' ||
		   firstNoLetterChar==',' || firstNoLetterChar=='，' ){
			result = result.substring(1).trim();
		}
		return result;
	}

}
