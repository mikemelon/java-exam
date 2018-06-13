package cn.lynu.lyq.java_exam.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.common.Constants;
import cn.lynu.lyq.java_exam.entity.Student;
import cn.lynu.lyq.java_exam.utils.PropertyUtils;

@Component("modifySettings")
@Scope("prototype")
public class ModifySettingsAction extends ActionSupport {

	private static final long serialVersionUID = 793981621438306810L;
	private final static Logger logger = LoggerFactory.getLogger(ModifySettingsAction.class);

	private String defaultExamScheduledTime;
	private String examDetailAllowed;
	private String examTimeoutAutoSubmit;

	public String getDefaultExamScheduledTime() {
		return defaultExamScheduledTime;
	}

	public void setDefaultExamScheduledTime(String defaultExamScheduledTime) {
		this.defaultExamScheduledTime = defaultExamScheduledTime;
	}

	public String getExamDetailAllowed() {
		return examDetailAllowed;
	}

	public void setExamDetailAllowed(String examDetailAllowed) {
		this.examDetailAllowed = examDetailAllowed;
	}

	public String getExamTimeoutAutoSubmit() {
		return examTimeoutAutoSubmit;
	}

	public void setExamTimeoutAutoSubmit(String examTimeoutAutoSubmit) {
		this.examTimeoutAutoSubmit = examTimeoutAutoSubmit;
	}

	@Override
	public String execute(){
		logger.info("进入修改系统设置界面");
		ActionContext ctx =ActionContext.getContext();
		if(ctx.getSession().containsKey("USER_INFO")){
			Student s1=(Student)ctx.getSession().get("USER_INFO");
			if(s1.getRole()!=1){
				this.addActionError("您没有管理权限！");
				return ERROR;
			}else{				
				defaultExamScheduledTime = PropertyUtils.getProperty(Constants.DEFAULT_EXAM_SCHEDULED_TIME);
				examDetailAllowed = PropertyUtils.getProperty(Constants.EXAM_DETAIL_ALLOWED);
				examTimeoutAutoSubmit = PropertyUtils.getProperty(Constants.EXAM_TIMEOUT_AUTOSUBMIT);
				return SUCCESS;
			}
		}else{
			this.addActionError("您还没有登录，请登录后再点击进入");
			return ERROR;
		}

	}

	public String executeForUpdate() {
		logger.info("开始修改系统设置");
		PropertyUtils.setProperty(Constants.DEFAULT_EXAM_SCHEDULED_TIME, defaultExamScheduledTime);
		PropertyUtils.setProperty(Constants.EXAM_DETAIL_ALLOWED, examDetailAllowed);
		PropertyUtils.setProperty(Constants.EXAM_TIMEOUT_AUTOSUBMIT, examTimeoutAutoSubmit);
		this.addActionMessage("系统设置修改成功!");
		return SUCCESS;
	}
}
