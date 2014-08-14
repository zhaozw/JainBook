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
				function resetAddOfferForm() {
					window.location.href="./showAddOfferForm.htm";
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
					<h2 class="legend" style="margin-bottom:0px;">Add New Offer</h2>
					<span style="color: black;">*indicates mandatory fields</span>
				</div>
				<div class="form-addEditLogo">
				<form:form method="POST" commandName="addOfferForm" action="addOffer.htm" enctype="multipart/form-data" cssClass="form-horizontal"> 
				
					<%-- <form:hidden path="logoId" /> --%>
				
					<div class="form-group">
            			<label  class="control-label col-xs-5"><spring:message code="offer.description.image.label" /></label>
            			<div class="col-xs-3">
                			<input type="file" size="20" name="offerFile" accept="image/*" />
                			<form:errors path="offerFile" cssClass="error" />
            			</div>
        			</div>
        			
        			<div class="form-group">
            			<label  class="control-label col-xs-5"><spring:message code="offer.coupon.image.label" /></label>
            			<div class="col-xs-3">
                			<input type="file" size="20" name="couponFile" accept="image/*" />
                			<form:errors path="couponFile" cssClass="error" />
            			</div>
        			</div>
				
					<div class="form-group">
            			<label  class="control-label col-xs-5"><spring:message code="offer.video.label"/></label>
            			<div class="col-xs-5">
                			<form:input cssClass="form-control" path="videoPath" id="videoPath"/>
                			<form:errors path="videoPath" cssClass="error" />
            			</div>
        			</div>
				
					<div class="form-group">
            			<label  class="control-label col-xs-5"><spring:message code="offer.validity.date.label"/></label>
            			<div class="col-xs-3">
                			<form:input cssClass="form-control" placeholder="MM-dd-yyyy" path="validityDate" id="datepicker" />
                			<form:errors path="validityDate" cssClass="error" />
            			</div>
        			</div>
				
					<div class="form-group">
           				 <label  class="control-label col-xs-5"><spring:message code="offer.type.label"/></label>
            			<div class="col-xs-3">
                			<form:select path="offerType" cssClass="form-control">
								<form:option value="" label="--Select Offer Type--"/>  
                				<form:options items="${addOfferForm.offerTypes}"/> 
							</form:select> 
							<form:errors path="offerType" cssClass="error" />
            			</div>
        			</div>
        			
        			<div class="form-group">
            			<label  class="control-label col-xs-5"><spring:message code="offer.description.label"/></label>
            			<div class="col-xs-3">
                			<form:textarea cssClass="form-control"  path="description" maxlength="50" rows="3"/>
                			<form:errors path="description" cssClass="error" />
            			</div>
        			</div>
        	
        			<div class="form-group">
            			<div class="col-xs-offset-5 col-xs-8">
                			<button type="submit" class="btn btn-primary" value="Submit">Submit</button>
							&nbsp;<a class="btn btn-primary" href="manageOffers.htm?logoId=${sessionScope.activeLogoId}">Cancel</a>
            			</div>
        			</div>
        			
				</form:form>
				</div>
				<%@ include file="footer.jsp" %>
			</div>
		</div>
	<script type="text/javascript" src='js/jquery-1.11.0.min.js'></script>
	<script type="text/javascript" src='js/bootstrap.min.js'></script>
	</body>
</html>