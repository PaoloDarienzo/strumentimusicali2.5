<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" %>
  
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
<div id="header">

	<div style="float: left;">
		<img style="float: left;" src="images/duck-walking.gif" alt="Ducking around" align="bottom" height="42" width="42">
		<h1 style="float: left;">zumzum.it, il miglior sito di strumenti musicali!</h1>
		<img style="float: left;" src="images/duck-walking.gif" alt="Ducking around" align="bottom" height="42" width="42">
	</div>
 
	<div style="float: right; padding: 10px; text-align: right;">
 
		<!-- User store in session with attribute: loggedUser -->
		Hello <b>${loggedUser.nomeUtente}</b>!

		<jsp:include page="_loginButton.jsp"></jsp:include>
 
	</div>
 
</div>

<br/>