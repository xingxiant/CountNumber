package com.xxt.listener;

import java.util.ArrayList;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.xxt.entity.User;
import com.xxt.utils.SessionUtil;
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
	//域对象 number进行数量统计
    private int number;
	@Override
	//创建
	public void sessionCreated(HttpSessionEvent arg0) {
		number++;
		//在线用户的数量存储到域对象ServletContext的number中
		arg0.getSession().getServletContext().setAttribute("number", number);
		}

	@Override
	//销毁
	public void sessionDestroyed(HttpSessionEvent arg0) {
		number--;
		arg0.getSession().getServletContext().setAttribute("number", number);
		//list是存储在域对象ServletContext中，用于记录用户的的日志信息
		ArrayList<User> list=
				(ArrayList<User>) arg0.getSession().getServletContext().getAttribute("userList");
		//根据sessionid删除将要推出的用户信息
		SessionUtil.remove(list,arg0.getSession().getId());
		arg0.getSession().getServletContext().setAttribute("userList", list);
	}

}
