<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.xxt.entity.*"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线人数统计</title>
</head>

</head>

<body>
当前在线人数：${number }<br>
<table border="1" cellspacing="0" width="500px">
	<tr>
		<td>id</td>
		<td>ip</td>
		<td>firstTime</td>
		
	</tr>
	 <%-- <c:choose>
		<c:when test=" ${not empty userList }" >
			<c:forEach var="list" items="${applicationScope.userList }">
				<tr>
					<td>${list.ssessionIdString }</td>
					<td>${list.ipString }</td>
					<td>${list.firsttTimeString }</td>
		
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
		<tr>
			<td colspan="3">无在线用户</td>
		</tr>
		</c:otherwise>
	</c:choose> --%>
	<%ArrayList<User>  userList =  (ArrayList<User>)request.getServletContext().getAttribute("userList"); 
	   if(userList!=null){
	       for(User u:userList){
	    	   
	    	   %>   <tr>
	    	        <td><%=u.getSessionIdString() %> </td>
					<td><%=u.getIpString() %></td>
					<td><%=u.getFirsttTimeString() %></td>
					</tr>
					<%}} %>
</table>
</body>
</html>