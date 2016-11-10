package cn.lynu.lyq.java_exam.actions;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.dao.ExamStrategyDao;
import cn.lynu.lyq.java_exam.entity.ExamStrategy;

@Component("updateStrategyScore")
@Scope("prototype")
public class UpdateStrategyScoreAction extends ActionSupport{
	private static final long serialVersionUID = -6798232379261444727L;
	@Resource
	private ExamStrategyDao examStrategyDao;
	private int score;
	private int strategyId;
	private ExamStrategy updatedStrategy;
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public int getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(int strategyId) {
		this.strategyId = strategyId;
	}
	public ExamStrategy getUpdatedStrategy() {
		return updatedStrategy;
	}
	public void setUpdatedStrategy(ExamStrategy updatedStrategy) {
		this.updatedStrategy = updatedStrategy;
	}
	
	public String updateForChoice(){
		updatedStrategy = examStrategyDao.findById(strategyId);
		updatedStrategy.setChoicePerScore(score);
		examStrategyDao.update(updatedStrategy);
		return SUCCESS;
	}
	
	public String updateForBlank(){
		updatedStrategy = examStrategyDao.findById(strategyId);
		updatedStrategy.setBlankPerScore(score);
		examStrategyDao.update(updatedStrategy);
		return SUCCESS;
	}
	
	public String updateForJudge(){
		updatedStrategy = examStrategyDao.findById(strategyId);
		updatedStrategy.setJudgePerScore(score);
		examStrategyDao.update(updatedStrategy);
		return SUCCESS;
	}
}
