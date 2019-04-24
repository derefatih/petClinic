<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="owner" method="post">
	First name:<form:input path="firstName"/>
	<form:errors path="firstName"/><br/>
	Last name:<form:input path="lastName"/>
	<form:errors path="lastName"/><br/>
	<form:button name="submit">Update</form:button>
	</form:form>
</body>
</html>