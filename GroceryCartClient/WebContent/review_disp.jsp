<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.json.simple.*"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Grocery Kart</title>
</head>
<body>
	<%
		JSONParser parser = new JSONParser();
		JSONArray json = (JSONArray) parser.parse(request.getAttribute("json").toString());
		for(int i=0; i < json.size(); i++){
			JSONObject j = (JSONObject)json.get(i);
			if(j.get("success").equals("true")){ request.setAttribute("purchased", "true");}
			%>User:
	<%=j.get("user") %><br />Review:
	<%=j.get("review") %><br />
	<%
		}
	%>

	<p><p id="pname"><%=request.getAttribute("pname")%></p><P>
	Review Page</p>
	<br/><%=request.getAttribute("user") %>
	<br/><%=request.getAttribute("purchased") %>
	<% if( (request.getAttribute("user").toString()!=null) && (request.getAttribute("purchased").toString()!="false")){ %>
		<form action="AddReviewControllerServlet" method="post">
			<table border="0" width="35%" align="center">
				<caption>
					<h2>Add a review</h2>
				</caption>
				<tr>
					<td width="50%">User Name</td>
					<td><input type="text" name="user" size="50" /></td>
				</tr>

				<tr>
					<td>Review</td>
					<td><textarea rows="10" cols="39" name="review"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Send" /></td>
				</tr>
			</table>
		</form>
	<%} %>
</body>
</html>