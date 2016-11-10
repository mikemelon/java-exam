package cn.lynu.lyq.java_exam.actions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component("hello")
@Scope("prototype")
public class HelloAction extends ActionSupport{
	private static final long serialVersionUID = -1757764252054421890L;
	private String message ;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String execute() throws Exception {
		message  = "你好！！！！！测试！！";
		return "success";
	}
	
	

}
