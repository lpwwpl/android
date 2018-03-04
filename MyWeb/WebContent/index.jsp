<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<%
//String type = request.getParameter("parameter");
//String result = new String(type.getBytes("iso-8859-1"),"UTF-8");
//out.println("result " + result);
%>

<form action="UserCheck" method="post">
	用户名：<input id="name" name="name" type="text">
	密     码：<input id="passwd" name="passwd" type="text">
	<input type="submit" value="post提交"/>
	</form>
	
	
	<form action="UserCheck" method="get">
	用户名：<input id="name" name="name" type="text">
	密     码：<input id="passwd" name="passwd" type="text">
	<input type="submit" value="get提交"/>
	
	
	<!--<form action="UserCheck" method="get">
	测试get：<input id="name" name="parameter" type="text" >
	<input type="submit" value="get提交"/>
	
	<form action="UserCheck" method="post">
	测试post：<input id="name" name="parameter" type="text">
	<input type="submit" value="post提交"/>
	</form>
--><body>

</body>
</html>