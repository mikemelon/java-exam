package cn.lynu.lyq.java_exam.actions;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.dao.BankQuestionDao;
import cn.lynu.lyq.java_exam.entity.BankChoiceQuestion;
@Component("choiceQuestionSearch")
@Scope("prototype")
@Data
public class ChoiceQuestionSearchAction extends ActionSupport{
	private static final long serialVersionUID = -1106576639902301220L;
	private final static Logger logger = LoggerFactory.getLogger(ChoiceQuestionSearchAction.class);
	private String contentSearch;
	private String choiceSearch;
	private String answerSearch;
	private String answerSearch2;//对于checkbox不选时，不提交的问题，做判断修正
	private String knowledgeSearch;
	private String CourseSearch;
	private List<BankChoiceQuestion> questionList;
	private int totalPage;
	private int pageIndex;
	private static final int PAGE_SIZE = 10;
	@Resource
	private BankQuestionDao bankQuestionDao;
	public int[] choiceChecked;
	@Override
	public String execute() throws Exception {//初始结果

		int totalCnt = bankQuestionDao.countAllChoice();
		totalPage = (totalCnt%PAGE_SIZE > 0)?(totalCnt/PAGE_SIZE+1):(totalCnt/PAGE_SIZE);
		questionList = bankQuestionDao.findAllChoiceWithPage(pageIndex,PAGE_SIZE);
		return SUCCESS;
	}
	
	public String executeForSearch(){//搜索后结果
		if(answerSearch2!=null && answerSearch2.equals("")){//对于checkbox不选时，不提交的问题，做判断修正
			answerSearch = "";
		}
		logger.debug("contentSearch="+contentSearch);
		logger.debug("choiceSearch="+choiceSearch);
		logger.debug("answerSearch="+answerSearch);
		logger.debug("answerSearch2="+answerSearch2);
		logger.debug("knowledgeSearch="+knowledgeSearch);
		logger.debug("CourseSearch", CourseSearch);
		questionList = bankQuestionDao.findChoiceForSearch(contentSearch,choiceSearch,answerSearch,knowledgeSearch);
		return SUCCESS;
	}

	public String deleteQuestion(){
//		bankQuestionDao.delete(bankQuestionDao.findJudgeById(1))
		for (int cid:choiceChecked){
			logger.info("删除id为"+cid+"的选择题");
			bankQuestionDao.delete(bankQuestionDao.findChoiceById(cid));
		}
		return SUCCESS;
	}

}
