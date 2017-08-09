<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
    
<div style="padding: 5px;">
 
	<a href="${pageContext.request.contextPath}/">Home</a>
	|
	<a href="${pageContext.request.contextPath}/login">Login</a>
	
	<!-- Button to open the modal login form -->
	<button onclick="document.getElementById('id01').style.display='block'">Login</button>
    
</div>  