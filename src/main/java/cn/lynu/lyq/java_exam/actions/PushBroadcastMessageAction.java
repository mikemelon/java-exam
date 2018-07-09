package cn.lynu.lyq.java_exam.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.entity.Student;
import cn.lynu.lyq.java_exam.websocket.BroadcastServer;

@Component("pushBroadcastMessage")
@Scope("prototype")
public class PushBroadcastMessageAction extends ActionSupport {

	private static final long serialVersionUID = 3979239568384667934L;

	private final static Logger logger = LoggerFactory.getLogger(PushBroadcastMessageAction.class);

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String execute(){
		logger.info("进入发送广播消息");
		ActionContext ctx =ActionContext.getContext();
		if(ctx.getSession().containsKey("USER_INFO")){
			Student s1=(Student)ctx.getSession().get("USER_INFO");
			if(s1.getRole()!=1){
				this.addActionError("您没有管理权限！");
				return ERROR;
			}else{				
				return SUCCESS;
			}
		}else{
			this.addActionError("您还没有登录，请登录后再点击进入");
			return ERROR;
		}

	}

	public String executeForSend() {
		logger.info("开始发送广播消息");
		BroadcastServer.broadcastMessage(message);
		return SUCCESS;
	}
}
