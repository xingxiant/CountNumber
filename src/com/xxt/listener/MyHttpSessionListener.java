package com.xxt.listener;

import java.util.ArrayList;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.xxt.entity.User;
import com.xxt.utils.SessionUtil;
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
    private int number;
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		number++;
		arg0.getSession().getServletContext().setAttribute("number", number);
		}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		number--;
		arg0.getSession().getServletContext().setAttribute("number", number);
		ArrayList<User> list=(ArrayList<User>) arg0.getSession().getServletContext().getAttribute("userList");
		SessionUtil.remove(list,arg0.getSession().getId());
		arg0.getSession().getServletContext().setAttribute("userList", list);

	}

}
