<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" import="model.User" %>
  
<div id="header">
	
	<div style="float: left; vertical-align: middle;">
			<a href="${pageContext.request.contextPath}/home" class="image-link">
				<img class="duck-size" src="images/duck-walking.gif" alt="Ducking around" style="float:left; align: center;">
			</a>
			<h1>zumzum.it, il miglior sito di strumenti musicali!</h1>
			<a href="${pageContext.request.contextPath}/home" class="image-link">
				<img class="duck-size" src="images/duck-walking.gif" alt="Ducking around" style="float:right; align: center;">
			</a>
	</div>
 
	<div style="float: right;">
	
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
		
		Welcome <b><%= nomeUtente %></b>!
		
		<% if(loggedStatus){ %>
			<a href="${pageContext.request.contextPath}/cart">Carrello</a>
			<jsp:include page="_logoutButton.jsp"></jsp:include>
		<% } else if(!loggedStatus){ %>
			<jsp:include page="_loginButton.jsp"></jsp:include>
			<br/>
			<a href="${pageContext.request.contextPath}/register">Not registered?</a>
		<% } %>
 
	</div>
	
	<div id="clear"></div>
 
</div>