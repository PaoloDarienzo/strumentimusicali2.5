<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" import="model.User, dao.*" %>
  
<div id="header">
	
	<div style="float: left;">
		<div style="vertical-align: middle;">
			<a href="${pageContext.request.contextPath}/home" class="image-link">
				<img class="duck-size" src="images/duck-walking.gif" alt="Ducking around" style="float:left; align: center;">
			</a>
			<h1>zumzum.it, il miglior sito di strumenti musicali!</h1>
			<a href="${pageContext.request.contextPath}/home" class="image-link">
				<img class="duck-size" src="images/duck-walking.gif" alt="Ducking around" style="float:right; align: center;">
			</a>
			 
		</div>
	</div>
 
	<div style="float: right;">
	
		<!-- User store in session with attribute: currentSessionUser -->
		<% 
		java.util.Formatter formatter = new java.util.Formatter();
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
		
		<div style="float: left;">
		Welcome <b><%= nomeUtente %></b>!
		</div>
		<div id="clear"></div>
		<% if(loggedStatus){ %>
			<div style="float: left;">
			
			<a href="${pageContext.request.contextPath}/cart">
				<button type="button" onclick="">Carrello:<br/>
					<% String strItems =(currentUser.getShoppingCart().getNumberOfItems() == 1) ? "item" : "items"; %>
					<%=currentUser.getShoppingCart().getNumberOfItems()%> <%=strItems%> - <%=formatter.format("%.2f", currentUser.getShoppingCart().getTotalPrice())%> €
				</button>
			</a>
				
			</div>
			<div id="clear"></div>
			<div>
				<jsp:include page="_logoutButton.jsp"></jsp:include>
			</div>
			<div id="clear"></div>
		<% } else if(!loggedStatus){ %>
			<div style="float: left;">
				<jsp:include page="_loginButton.jsp"></jsp:include>
			</div>
			<div id="clear"></div>
			<a href="${pageContext.request.contextPath}/register">Not registered?</a>
		<% } 
		formatter.close(); %>
 
	</div>
	
	<div id="clear"></div>
 
</div>