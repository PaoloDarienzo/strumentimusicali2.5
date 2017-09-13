<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" import="model.User" %>
  
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
<div id="header">

	<div style="float: left;">
		<h1 style="float: left;">
		<img style="float: left; width: 42px; height: 42px;" src="images/duck-walking.gif" alt="Ducking around" align="bottom">
		zumzum.it, il miglior sito di strumenti musicali!
		<img style="float: right; width: 42px; height: 42px;" src="images/duck-walking.gif" alt="Ducking around" align="bottom">
		</h1>
	</div>
 
	<div style="float: right; padding: 10px; text-align: right;">
 
		<!-- User store in session with attribute: currentSessionUser -->
		<% User currentUser = (User) session.getAttribute("currentSessionUser"); %>
		<% String nomeUtente = null;
		nomeUtente = (currentUser == null) ? "" : currentUser.getNomeUtente(); %>
		
		Welcome <b> <%= nomeUtente %> </b>!

		<jsp:include page="_loginButton.jsp"></jsp:include>
 
	</div>
 
</div>

<br/>