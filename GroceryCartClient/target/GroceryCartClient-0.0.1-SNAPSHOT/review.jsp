<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Review</title>
</head>
<body>
	<form action="ReviewControllerServlet" method="post">
		Product Name:<input type="text" name="pname"><br>  <input
			type="submit" value="Review">
	</form>
	<div>
		<p>
			Not registered yet? <a href=reg.jsp>Register Here!</a>
		</p>
	</div>
</body>
</html>