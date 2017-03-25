package com.xxt.utils;

import java.util.ArrayList;

import com.xxt.entity.User;

public class SessionUtil {

	public static User getUserBySessionId(ArrayList<User> list,String id){
		for(int i=0;i<list.size();i++){
			if(list.get(i).getSessionIdString().equals(id)){
				return list.get(i);
			}
		}
		return null;
	}

	public static void remove(ArrayList<User> list,String id) {
		for(int i=0;i<list.size();i++){
			if(list.get(i).getSessionIdString().equals(id)){
			   list.remove(list.get(i));
			}
		}
		
	}
}
