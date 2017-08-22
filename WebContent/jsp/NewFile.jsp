<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" import="java.util.List, java.util.Iterator" %>
  
<!DOCTYPE html>

<html>

	<head></head>
	
	<body>
	
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		
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
		    <form name="f1" method="post" action="controller/SearchServlet.java">
		    	Select brand:
		    	<select name="brand">
		    		<option value="All">All</option>
					<%  while(iterBrands.hasNext()){ %>
            		<option><%= (String) iterBrands.next() %></option>
       				 <% } %>
				</select>
		    </form>
			</div>
			
			<%-- select value of instrument type from drop-downlist --%>
		    <div style = "display: inline-block">
			<%
				List<String> instrumentTypes = dao.QueriesDAO.getInstrumentType();
				Iterator<String> iterInstrumentTypes = instrumentTypes.iterator();
			%>
		    <form name="f2" method="post" action="controller/SearchServlet.java">
		    	Select instrument type:
		    	<select name="instrumentType">
		    		<option value="All">All</option>
					<%  while(iterInstrumentTypes.hasNext()){ %>
            		<option><%= (String) iterInstrumentTypes.next() %></option>
       				 <% } %>
				</select>
		    </form>
			</div>
			
			<%-- select value used from drop-downlist --%>
		    <div style = "display: inline-block">
		    <form name="f3" method="post" action="controller/SearchServlet.java">
		    	Select used status:
		    	<select name="used">
		    		<option value="0">All</option>
		    		<option value="false">Not used</option>
					<option value="true">used</option>
				</select>
		    </form>
			</div>
			
			<%-- select value product type from drop-downlist --%>
		    <div style = "display: inline-block">
		    <form name="f4" method="post" action="controller/SearchServlet.java">
		    	Select product type:
		    	<select name="productType">
		    		<option value="All">All</option>
					<option value="2">Professional product</option>
					<option value="1">Scholastic product</option>
					<option value="0">Classic product</option>
				</select>
		    </form>
			</div>
		
		</div>
		
		<input type="submit" value="Submit">
		
		<jsp:include page="_footer.jsp"></jsp:include>
	
	</body>
	
</html>