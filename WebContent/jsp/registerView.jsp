<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" %>
  
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
		
		<h2 style="text-align: center;">Register here!</h2>
		
		<form action="${pageContext.request.contextPath}/register" method="POST">
		
			<div class="container">
				<label><b>Email</b></label>
				<input type="text" placeholder="Enter Email" name="email" required>
				
				<label><b>Username</b></label>
				<input type="text" placeholder="Enter username" name="username" required>
	
				<label><b>Password</b></label>
				<input type="password" placeholder="Enter Password" name="psw" required>
	
				<label><b>Repeat Password</b></label>
				<input type="password" placeholder="Repeat Password" name="psw-repeat" required>
				
				<label><b>Name</b></label>
				<input type="text" placeholder="Enter name" name="uname" required>
				
				<label><b>Surname</b></label>
				<input type="text" placeholder="Enter surname" name="usurname" required>
				
				<label><b>Fiscal code</b></label>
				<input type="text" placeholder="Enter fiscal code" name="cf" required>
				
				<label><b>Telephone number</b></label>
				<input type="text" placeholder="Enter telephone number" name="ntelefono" required>
				
				<label><b>Personal cellphone number*</b></label>
				<input type="text" placeholder="Enter cellphone number" name="ncellulare">
				
				<label><b>City</b></label>
				<input type="text" placeholder="Enter city" name="city" required>
				
				<label><b>Select type of user</b></label>
				<select name="userType" required>
				<optgroup style="font-size:14px;">
						<option value="c">Casual user</option>
						<option value="p">Professional user</option>
						<option value="s">Scholastic user</option>
					</optgroup>					
				</select>
				
				<br/>
				<i>All the fields with the * can be left free.</i>
				<br/><br/>
	
				<div class="clearfix">
					<button type="button" class="cancelbtn">Cancel</button>
					<button type="submit" class="signupbtn">Register</button>
				</div>
			</div>
		
		</form>
		
		<jsp:include page="_footer.jsp"></jsp:include>
	
	</body>
	
</html>