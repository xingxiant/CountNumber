package com.xxt.listener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import com.xxt.entity.User;
import com.xxt.utils.SessionUtil;
@WebListener
public class MyServletRequestListener implements ServletRequestListener {
	private ArrayList<User> userList=new ArrayList<User>();  //在线用户Id
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		

	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		
		//创建用户list在servlectcontext域对象中
		userList=(ArrayList<User>) arg0.getServletRequest().getServletContext().getAttribute("userList");
		
		if(userList==null){
			userList=new ArrayList<User>();
		}
		
		//获得用户信息之sessionid
		HttpServletRequest request = (HttpServletRequest) arg0.getServletRequest();
		String id=request.getSession().getId();
		
		if(SessionUtil.getUserBySessionId(userList, id)==null){
			User u=new User();
			u.setSessionIdString(id);
			//获得ip地址
			u.setIpString(request.getRemoteAddr());
			//获得登录地址
			u.setFirsttTimeString(new SimpleDateFormat("yyyy-MM--dd HH:mm:ss").format(new Date()));
			userList.add(u);
		}
		request.getServletContext().setAttribute("userList", userList);
		
	}

}
