<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
<head>
	<title>Forget Password Page</title>
	<meta content="width=device-width, initial-scale=1" name="viewport"/>
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
	<link rel="stylesheet" href="css/signin.css" type="text/css"/>
</head>
<body>
	<div class="container">
	<form:form action="forgetPassword.htm" commandName="forgetPasswordForm" method="post" cssClass="form-signin">
		<div class="control-group" align="center">
			<img src="images/ic_launcher_smaller.png" alt="logoImg" class="img-circle img-responsive"/>
		</div> 
		<div class="control-group"> 
		<label class="control-label" style="font-weight: normal;padding-top: 10px;">Please enter your Registered email id</label>
		</div>
			<div class="control-group"> 
			 	<label class="control-label" for="inputEmail" style="font-weight: normal;">Email</label>
				<div class="controls">
					<form:input path="email" type="text" id="inputEmail" placeholder="Email" cssClass = "form-control" autofocus="autofocus"/>
				</div>
				<form:errors path="email" cssClass="error"/>
			</div>
			
			<div class="control-group">
				<div class="controls" align="center">
					<c:if test="${not empty passwordSent}">
						<div class="info">Password Sent on Email</div>
					</c:if>
				</div>
			</div>
			
			<div class="control-group" style="padding-top: 18px;">
				<div class="controls">
					<input type="submit"  value="Submit" class="btn btn-lg btn-primary btn-block"/>
				</div>
			</div>
	</form:form>
	</div>
	
	<script type="text/javascript" src='js/jquery-1.11.0.min.js'></script>
	<script type="text/javascript" src='js/bootstrap.min.js'></script>
</body>
</html>