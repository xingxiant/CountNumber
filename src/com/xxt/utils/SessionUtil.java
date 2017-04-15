package com.xxt.utils;

import java.util.ArrayList;

import com.xxt.entity.User;

public class SessionUtil {
	/*
	 * 根据sessionID查找用户
	 */
	public static User getUserBySessionId(ArrayList<User> list,String id){
		for(int i=0;i<list.size();i++){
			if(list.get(i).getSessionIdString().equals(id)){
				return list.get(i);
			}
		}
		return null;
	}
	/*
	 * 根据sessionID删除用户
	 */
	public static void remove(ArrayList<User> list,String id) {
		for(int i=0;i<list.size();i++){
			if(list.get(i).getSessionIdString().equals(id)){
			   list.remove(list.get(i));
			}
		}
		
	}
}
