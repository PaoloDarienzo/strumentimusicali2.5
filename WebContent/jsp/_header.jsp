<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" %>
  
<div style="background: #E0E0E0; height: 55px; padding: 5px;">

	<div style="float: left;">
		<img style="float: left;" src="images/duck-walking.gif" alt="Ducking around" align="bottom" height="42" width="42">
		<h1 style="float: left;">zumzum.it, il miglior sito di strumenti musicali!</h1>
		<img style="float: left;" src="images/duck-walking.gif" alt="Ducking around" align="bottom" height="42" width="42">
	</div>
 
	<div style="float: right; padding: 10px; text-align: right;">
 
		<!-- User store in session with attribute: loggedUser -->
		Hello <b>${loggedUser.nomeUtente}</b>!
 
	</div>
 
</div>

<br/>