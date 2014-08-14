<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta content="width=device-width, initial-scale=1" name="viewport"/>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
		<link rel="stylesheet" href="css/generic.css" type="text/css"/>
		<script type="text/javascript">
			window.onload = function(){
				function resetAddLogoForm() {
					window.location.href="./showAddLogoForm.htm";
				}}
		</script>
		
	</head>
	
	<body>
		<div class="container">
			<div class="control-group background_image_header" align="center" >
				<img src="images/ic_launcher.png" alt="logoImg" class="img-circle img-responsive" style="height:auto; width:auto; max-width:150px; max-height:150px;"/>
			</div>

			<div class="background_body">
				
				<%@ include file="header.jsp" %>
						
				<div style=" clear: both; text-align: right;margin-bottom:30px;">
					<h2 class="legend" style="margin-bottom:0px;">Edit Logo</h2>
					<span style="color: black;">*indicates mandatory fields</span>
				</div>
				<div class="form-addEditLogo">
						
					<form:form method="POST" commandName="editLogoForm" action="editLogo.htm" enctype="multipart/form-data" cssClass="form-horizontal"> 
							
					 	<div class="form-group">
           				 	<label for="logoType" class="control-label col-xs-5"><spring:message code="logo.type.label"/></label>
            				<div class="col-xs-3">
                				<form:select path="logoType" cssClass="form-control">
									<form:option value="0" label="--Select Logo Type--"/>  
                					<form:options itemValue="id" itemLabel="type" items="${editLogoForm.logoTypes}"/> 
								</form:select> 
								<form:errors path="logoType" cssClass="error" />
            				</div>
        				</div>
						<div class="form-group">
            				<label for="logoName" class="control-label col-xs-5"><spring:message code="logoname.label"/></label>
            				<div class="col-xs-3">
                				<form:input cssClass="form-control" path="logoName" id="logoName" placeholder="Logo Name"/>
                				<form:errors path="logoName" cssClass="error" />
            				</div>
        				</div>	
						<div class="form-group">
            				<label for="uploadLogo" class="control-label col-xs-5"><spring:message code="upload.image.label.edit" /></label>
            				<div class="col-xs-3">
                				<input type="file" size="20" name="file" accept="image/*" />
                				<form:errors path="file" cssClass="error" />
            				</div>
        				</div>	
        				
						<div class="form-group">
            				<div class="col-xs-offset-5 col-xs-8">
                				<button type="submit" class="btn btn-primary" value="Submit">Submit</button>
								&nbsp;<a class="btn btn-primary" href="adminHome.htm">Cancel</a>
            				</div>
        				</div>
						
						<form:hidden path="logoId" />
					</form:form>
				</div>
				<%@ include file="footer.jsp" %>
			</div>
		</div>
	<script type="text/javascript" src='js/jquery-1.11.0.min.js'></script>
	<script type="text/javascript" src='js/bootstrap.min.js'></script>
	</body>
</html>