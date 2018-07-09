package cn.lynu.lyq.java_exam.websocket;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.lynu.lyq.java_exam.entity.Student;

@Controller
@Scope("prototype")
@ServerEndpoint(value="/websocket/broadcast",configurator=GetHttpSessionConfigurator.class)
public class BroadcastServer {
	private Session session;
	private final static Logger logger = LoggerFactory.getLogger(BroadcastServer.class);
	
	private static List<BroadcastServer> broadcastList = new CopyOnWriteArrayList<>();
	
	@OnOpen
	public void onOpen(Session session, EndpointConfig config){
		logger.debug("BroadcastServer onOpen");
		this.session = session;
		HttpSession httpSession = (HttpSession)config.getUserProperties().get(HttpSession.class.getName());
		Student student = (Student)httpSession.getAttribute("USER_INFO");
		if(student!=null){
			broadcastList.add(this);
		}
	}
	
	@OnMessage
	public void onMessage(String message){
		logger.info("BroadcastServer onMessage");
//		broadcastMessage(message);
	}
	
	@OnClose
	public void onClose(){
		logger.debug("BroadcastServer onClose");
		broadcastList.remove(this);
	}
	
	@OnError
	public void onError(Throwable t){
		logger.debug("websocket onError:"+t.getMessage());
	}
	
	public static void broadcastMessage(String message){
		for(BroadcastServer item:broadcastList){
			item.session.getAsyncRemote().sendText(message);
		}
	}
	
	//
	public String execute(){
		return "success";
	}
}
