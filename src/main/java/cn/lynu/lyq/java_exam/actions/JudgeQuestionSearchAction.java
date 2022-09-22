package cn.lynu.lyq.java_exam.actions;

import java.util.List;

import javax.annotation.Resource;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.dao.BankQuestionDao;
import cn.lynu.lyq.java_exam.entity.BankJudgeQuestion;

@Component("judgeQuestionSearch")
@Scope("prototype")
@Data
public class JudgeQuestionSearchAction extends ActionSupport{
	private static final long serialVersionUID = -1106576639902301220L;
	private final static Logger logger = LoggerFactory.getLogger(JudgeQuestionSearchAction.class);
	
	private String contentSearch;
	private String answerSearch;
	private String knowledgeSearch;
	private String CourseSearch;
	private List<BankJudgeQuestion> questionList;
	private int totalPage;
	private int pageIndex;
	private static final int PAGE_SIZE = 10;
	@Resource
	private BankQuestionDao bankQuestionDao;
	public int[] judgeChecked;
	@Override
	public String execute() throws Exception {//初始结果
//		questionList = bankQuestionDao.findAllJudge();
		int totalCnt = bankQuestionDao.countAllJudge();
		totalPage = (totalCnt%PAGE_SIZE > 0)?(totalCnt/PAGE_SIZE+1):(totalCnt/PAGE_SIZE);
		questionList = bankQuestionDao.findAllJudgeWithPage(pageIndex,PAGE_SIZE);
		return SUCCESS;
	}
	
	public String executeForSearch(){//搜索后结果
		logger.debug("contentSearch="+contentSearch);
		logger.debug("answerSearch="+answerSearch);
		logger.debug("knowledgeSearch="+knowledgeSearch);
		logger.debug("CourseSearch", CourseSearch);
		questionList = bankQuestionDao.findJudgeForSearch(contentSearch,answerSearch,knowledgeSearch);
		return SUCCESS;
	}

	public String deleteQuestion(){
		for (int cid:judgeChecked){
			logger.info("删除id为"+cid+"的判断题");
			bankQuestionDao.delete(bankQuestionDao.findJudgeById(cid));
		}
		return SUCCESS;
	}
}
