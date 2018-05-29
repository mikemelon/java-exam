package cn.lynu.lyq.java_exam.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component("simpleTest")
@Scope("prototype")
public class SimpleTestAction extends ActionSupport {
	
	private static final long serialVersionUID = 4075728253429075870L;
	private final static Logger logger = LoggerFactory.getLogger(SimpleTestAction.class);
	private String answerSearch;
	private String answerSearch2;
	public String getAnswerSearch() {
		return answerSearch;
	}
	public void setAnswerSearch(String answerSearch) {
		this.answerSearch = answerSearch;
	}
	public String getAnswerSearch2() {
		return answerSearch2;
	}
	public void setAnswerSearch2(String answerSearch2) {
		this.answerSearch2 = answerSearch2;
	}
	@Override
	public String execute() throws Exception {
		logger.debug(">>>>>>>>>>>>>answerSearch========"+answerSearch);
		logger.debug(">>>>>>>>>>>>>answerSearch2========"+answerSearch2);
		return SUCCESS;
	}

}
