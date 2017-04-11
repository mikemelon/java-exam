package cn.lynu.lyq.java_exam.actions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Component("scoreStats")
@Scope("prototype")
public class ScoreStatsAction extends ActionSupport {
	private static final long serialVersionUID = -1980837224909869714L;
	
	@Override
	public String execute() throws Exception {
		ActionContext ctx =ActionContext.getContext();
		if(ctx.getSession().containsKey("USER_INFO")){
			return SUCCESS;
		}else{
			this.addActionError("您还没有登录，请登录后再点击进入");
			return ERROR;
		}
		
	}

}
