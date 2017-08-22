<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" import="java.util.List, java.util.Iterator" %>
  
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		
		<meta name="description" content="Negozio di strumenti musicali">
		<meta name="keywords" content="strumenti, musicali, negozio">
		<meta name="author" content="Paolo D'Arienzo">
			
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<link rel="icon" href="images/favicon-32x32.png" />
		
		<title>zumzum.it</title>
		
	</head>
	
	<body
	 style = "background-color: #cce6ff">
	
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		
		<h3>Home Page</h3>
		
		<br/>
		
		<div
			style = "text-align: center">
			
			Search <input name="search"> <input type="submit" name="submit" value="Search"/>
			
		</div>
		
		<div style = "text-align: center">
		
		    <%-- select value brand from drop-downlist --%>
		    <div style = "display: inline-block">
		    <%
				List<String> brands = dao.QueriesDAO.getBrands();
				Iterator<String> iterBrands = brands.iterator();
			%>
		    <form name="f1" method="get" action="#">
		    	Select brand:
		    	<select name="brand">
		    		<option>All</option>
					<%  while(iterBrands.hasNext()){ %>
            		<option> <%= iterBrands.next() %></option>
       				 <% } %>
				</select>
		    </form>
		    <%-- To display selected value from dropdown list. --%>
		    <% 
				String brandSelected = request.getParameter("brand");
			%>
			</div>
			
			<%-- select value of instrument type from drop-downlist --%>
		    <div style = "display: inline-block">
			<%
				List<String> instrumentTypes = dao.QueriesDAO.getInstrumentType();
				Iterator<String> iterInstrumentTypes = instrumentTypes.iterator();
			%>
		    <form name="f2" method="get" action="#">
		    	Select instrument type:
		    	<select name="instrumentType">
		    		<option>All</option>
					<%  while(iterInstrumentTypes.hasNext()){ %>
            		<option> <%= iterInstrumentTypes.next() %></option>
       				 <% } %>
				</select>
		    </form>
		    <%-- To display selected value from dropdown list. --%>
		    <% 
				String selectedInstrumentType = request.getParameter("instrumentType");
			%>
			</div>
			
			<%-- select value used from drop-downlist --%>
		    <div style = "display: inline-block">
		    <form name="f3" method="get" action="#">
		    	Select used status:
		    	<select name="used">
		    		<option>All</option>
		    		<option>Not used</option>
					<option>used</option>
				</select>
		    </form>
		    <%-- To display selected value from dropdown list. --%>
		    <% 
				String selectedUsedStatus = request.getParameter("used");
			%>
			</div>
			
			<%-- select value product type from drop-downlist --%>
		    <div style = "display: inline-block">
		    <form name="f4" method="get" action="#">
		    	Select product type:
		    	<select name="productType">
		    		<option>All</option>
					<option>Professional product</option>
					<option>Scholastic product</option>
					<option>Classic product</option>
				</select>
		    </form>
		    <%-- To display selected value from dropdown list. --%>
		    <% 
				String selectedProductType = request.getParameter("productType");
			%>
			</div>
		
		</div>
		
		<% 
		     out.println("Searching: " + selectedProductType + ", " + 
		    		 brandSelected + ", " + selectedInstrumentType + ", " + selectedUsedStatus);
		%>
		
		<jsp:include page="_footer.jsp"></jsp:include>
	
	</body>
	
</html>