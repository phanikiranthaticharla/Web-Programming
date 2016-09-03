<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="sharath.kart.LoginBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Grocery Kart</title>
</head>
<body>
	<%
		if (session == null) {
			String address = "/login-error.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
			dispatcher.forward(request, response);
		} else {
			String name = (String) session.getAttribute("user");
			LoginBean bean = (LoginBean) request.getAttribute("bean");
			if (name == null || bean == null || name.compareTo(bean.getName()) != 0) {
				String address = "/login-error.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
				dispatcher.forward(request, response);
			}
	%>
	Welcome
	<%=bean.getName()%>
	<br />
	Last Login: 
	<%=
		bean.getLastlogin()
	%>
	<br /> Number of Failed Attempts:
	<%=bean.getFailed_attempts()%>
	<br /> Last Failed:
	<%=bean.getLastfailed()%>
	<br />
	<a href='logout.jsp'>Log out</a>
	<%
		}
	%>
	<p>You are successfully logged in!</p>
</body>
</html>