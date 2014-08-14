<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
	<head>
	<title>Your Key Lock Login</title>
	<meta content="width=device-width, initial-scale=1" name="viewport"/>
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
	<link rel="stylesheet" href="css/signin.css" type="text/css"/>
	</head>
	<body>
		<div class="container">
		<form:form action="userLogin.htm" commandName="userLoginForm" method="post" cssClass="form-signin">
			<div class="control-group" align="center">
				<img src="images/ic_launcher_smaller.png" alt="logoImg" class="img-circle img-responsive"/>
			</div> 
	        <h2 class="form-signin-heading">Please sign in</h2>
			<div class="control-group"> 
			 	<label class="control-label" for="inputEmail" style="font-weight: normal;">Email</label>
				<div class="controls">
					<form:input path="email" type="text" id="inputEmail" placeholder="Email" cssClass = "form-control" autofocus="autofocus"/>
				</div>
				<form:errors path="email" cssClass = "error"/>
			</div>
			
			 <div class="control-group"><br/></div> 
			 
			<div class="control-group">
				<div class="controls">
					<input type="submit"  value="Sign In" class="btn btn-lg btn-primary btn-block"/>
				</div>
			</div>
			
			<div class="control-group">
				<div class="controls">
					<a href="login.htm">Sign in as administrator</a>
				</div>
			</div>
	
		</form:form>
		</div>
		<script type="text/javascript" src='js/jquery-1.11.0.min.js'></script>
		<script type="text/javascript" src='js/bootstrap.min.js'></script>
	</body>
</html>
