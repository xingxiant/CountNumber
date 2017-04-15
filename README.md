# CountNumber
> 本项目记录通过记录session来实现对在线用户的信息进行统计，主要用到的技术是web监听器


**效果展示**（页面丑陋见谅）：
![](http://i1.piimg.com/567571/2ae7b326d1730f38.png)
**页面结构**：

![](http://i4.buimg.com/567571/b49a8640bd369353.png)
## 思路讲解： ##

 - 需要什么样的web监听器？

    

>   首先我们头知道session表示一个回话，所以监听session可以很好的统计在线用户的数量，所以我们选择***HttpSessionListener***监听session的**创建**与**销毁**
>    同时要记录用户的ip地址等日志信息，所以要监听request请求，所以还要使用***ServletRequestListener***监听request的**创建**

## 代码讲解 ##

1.session监听器HttpSessionListener

 

```
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
```

2.request监听器ServletRequestListener

```
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
```

3.SessionUtil

```
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
```

4.index主页

```
<!-- 主页循环显示 -->
<%ArrayList<User>  userList =  (ArrayList<User>)request.getServletContext().getAttribute("userList"); 
	   if(userList!=null){
	       for(User u:userList){
	    	   
	    	   %>   <tr>
	    	        <td><%=u.getSessionIdString() %> </td>
					<td><%=u.getIpString() %></td>
					<td><%=u.getFirsttTimeString() %></td>
					</tr>
					<%}} %>
```
