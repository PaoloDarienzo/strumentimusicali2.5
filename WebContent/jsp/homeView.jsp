<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" import="java.util.List, java.util.Iterator" %>
  
<!DOCTYPE html>

<html>

	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="CSS/Styles.css" rel="stylesheet" type="text/css">
		<meta charset="UTF-8">
		
		<meta name="description" content="Negozio di strumenti musicali">
		<meta name="keywords" content="strumenti, musicali, negozio">
		<meta name="author" content="Paolo D'Arienzo">
		
		<link rel="icon" href="images/favicon-32x32.png" />
		
		<title>zumzum.it</title>
		
	</head>
	
	<body>
	
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		
		<h3>Home Page</h3>
		
		<br/>		
		
		<div style = "text-align: center">
		
			<div style = "text-align: center">
				
				<form action="${pageContext.request.contextPath}/search" method="get">
	
					Search <input name="search"> <input type="submit" value="Search"/>
					
					<br/>			
		
				    <%-- select value brand from drop-downlist --%>
				    <div style = "display: inline-block">
				    <%
						List<String> brands = dao.QueriesDAO.getBrands();
						Iterator<String> iterBrands = brands.iterator();
					%>
			    	Select brand:
			    	<select name="brand">
			    		<option value="All">All</option>
						<%  while(iterBrands.hasNext()){ %>
		           		<option><%= (String) iterBrands.next() %></option>
		      				 <% } %>
					</select>
					</div>
					
					<%-- select value of instrument type from drop-downlist --%>
				    <div style = "display: inline-block">
					<%
						List<String> instrumentTypes = dao.QueriesDAO.getInstrumentType();
						Iterator<String> iterInstrumentTypes = instrumentTypes.iterator();
					%>
			    	Select instrument type:
			    	<select name="instrumentType">
			    		<option value="All">All</option>
						<%  while(iterInstrumentTypes.hasNext()){ %>
		           		<option><%= (String) iterInstrumentTypes.next() %></option>
		      				 <% } %>
					</select>
					</div>
					
					<%-- select value used from drop-downlist --%>
				    <div style = "display: inline-block">
			    	Select used status:
			    	<select name="used">
			    		<option value="0">All</option>
			    		<option value="false">Not used</option>
						<option value="true">Used</option>
					</select>
					</div>
					
					<%-- select value product type from drop-downlist --%>
				    <div style = "display: inline-block">
			    	Select product type:
			    	<select name="productType">
			    		<option value="All">All</option>
						<option value="p">Professional product</option>
						<option value="s">Scholastic product</option>
						<option value="c">Classic product</option>
					</select>
					</div>
			
				</form>
				
			</div>
		
		</div>
				
		<jsp:include page="_footer.jsp"></jsp:include>
	
	</body>
	
</html>