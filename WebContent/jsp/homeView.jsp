<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" import="java.sql.*,java.lang.*" %>
  
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
			
			Search <input name="search">
		</div>
		
		<div style = "text-align: center">	
		
		    <%-- select value brand from drop-downlist --%>
		    <div style = "display: inline-block">
		    <form name="f1" method="get" action="#">
		    	<select name="brand">
					<option>brand</option>
				</select>
		     <input type="submit" name="submit" value="Select Brand"/>
		    </form>
		    <%-- To display selected value from dropdown list. --%>
		    <% 
				String s = request.getParameter("brand");
				if (s !=null)
		        {
					out.println("Selected Brand is : " +s);
				}
			%>
			</div>
			
			<%-- select value type from drop-downlist --%>
		    <div style = "display: inline-block">
		    <form name="f2" method="get" action="#">
		    	<select name="type">
					<option>type</option>
				</select>
		     <input type="submit" name="submit" value="Select type"/>
		    </form>
		    <%-- To display selected value from dropdown list. --%>
		    <% 
				String s1 = request.getParameter("type");
				if (s1 !=null)
		        {
					out.println("Selected Brand is : " +s1);
				}
			%>
			</div>
			
			<%-- select value brand from drop-downlist --%>
		    <div style = "display: inline-block">
		    <form name="f3" method="get" action="#">
		    	<select name="used">
					<option>used</option>
				</select>
		     <input type="submit" name="submit" value="Select used"/>
		    </form>
		    <%-- To display selected value from dropdown list. --%>
		    <% 
				String s2 = request.getParameter("used");
				if (s2 !=null)
		        {
					out.println("Selected Brand is : " +s2);
				}
			%>
			</div>
			
			<%-- select value brand from drop-downlist --%>
		    <div style = "display: inline-block">
		    <form name="f4" method="get" action="#">
		    	<select name="producttype">
					<option>producttype</option>
				</select>
		     <input type="submit" name="submit" value="Select producttype"/>
		    </form>
		    <%-- To display selected value from dropdown list. --%>
		    <% 
				String s3 = request.getParameter("producttype");
				if (s3 !=null)
		        {
					out.println("Selected Brand is : " +s3);
				}
			%>
			</div>
		
		</div>
		
		<jsp:include page="_footer.jsp"></jsp:include>
	
	</body>
	
</html>