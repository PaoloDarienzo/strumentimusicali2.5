<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="model.User"%>
    
<div style="padding: 5px;">
 
	<a href="${pageContext.request.contextPath}/">Home</a>
	
	<%
		User currentUser = (User) session.getAttribute("currentSessionUser");
	
		if (currentUser != null){ %> <!-- user is logged -->
			<a href="${pageContext.request.contextPath}/userProfile">Profile</a>
			<a href="${pageContext.request.contextPath}/cart">Cart</a>
		<% } else { %>
			<a href="${pageContext.request.contextPath}/register">Register</a>
			<a href="${pageContext.request.contextPath}/login">Login</a>
		<% } %>
    
</div>  