<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" import="model.User" %>
  
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
<div id="header">

	<div style="float: left;">
		<h1 style="float: left;">
		<a href="${pageContext.request.contextPath}/home">
			<img style="float: left; width: 42px; height: 42px;" src="images/duck-walking.gif" alt="Ducking around" align="middle">
		</a>
		zumzum.it, il miglior sito di strumenti musicali!
		<a href="${pageContext.request.contextPath}/home">
			<img style="float: right; width: 42px; height: 42px;" src="images/duck-walking.gif" alt="Ducking around" align="middle">
		</a>
		</h1>
	</div>
 
	<div style="float: right; padding: 10px; text-align: right;">
	
		<!-- User store in session with attribute: currentSessionUser -->
		<% 
		Boolean loggedStatus = null;
		User currentUser = (User) session.getAttribute("currentSessionUser");
		if (currentUser != null){
			loggedStatus = true;
		}
		else{
			loggedStatus = false;
		}
		String nomeUtente = null;
		nomeUtente = (loggedStatus) ? currentUser.getNomeUtente() : "";
		%>
		
		Welcome <b> <%= nomeUtente %> </b>!
		
		<% if(loggedStatus){ %>
			<br/>
			<a style="display:inline-block; float:left" href="${pageContext.request.contextPath}/cart">Carrello</a>
			<jsp:include page="_logoutButton.jsp"></jsp:include>
		<% } else if(!loggedStatus){ %>
			<jsp:include page="_loginButton.jsp"></jsp:include>
			<br/>
			<a style="display:inline-block; float:left" href="${pageContext.request.contextPath}/register">Not registered?</a>
		<% } %>
 
	</div>
 
</div>

<br/>