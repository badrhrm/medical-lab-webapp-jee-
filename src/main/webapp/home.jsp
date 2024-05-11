<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.mycompany.models.Patient"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME PAGE</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/signup"><button>Sign UP</button></a>
<a href="${pageContext.request.contextPath}/login"><button>Login</button></a>
<a href="${pageContext.request.contextPath}/logout"><button>Logout</button></a>

<%
	Patient patient = (Patient) request.getSession().getAttribute("patient");
    if (patient != null) {
%>
    <p>Hello <%= patient.getFName() %></p>
<% 
    } 
%>
</body>
</html>