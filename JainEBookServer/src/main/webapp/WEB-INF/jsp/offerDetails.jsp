<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta content="width=device-width, initial-scale=1" name="viewport"/>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
		<link rel="stylesheet" href="css/generic.css" type="text/css"/>

		<script type="text/javascript" src='js/jquery-1.11.0.min.js'></script>
		<script type="text/javascript" src='js/bootstrap.min.js'></script>
		
	</head>
	
	<body>
		<div class="container">
			
			<div class="control-group background_image_header" align="center" >
				<img src="images/ic_launcher.png" alt="logoImg" class="img-circle img-responsive" style="height:auto; width:auto; max-width:150px; max-height:150px;"/>
			</div>

			<div class="background_body">
				
				<%@ include file="header.jsp" %>
						
				<div style=" clear: both; text-align: right;margin-bottom:30px;">
					<h2 class="legend" style="margin-bottom:0px;">Offer Details</h2>
				</div>
				
				<div class="form-addEditLogo">
				
				<form:form method="POST" commandName="offerDTO" action="" cssClass="form-horizontal"> 
				
        			<div class="form-group">
            			<label  class="control-label col-xs-5"><spring:message code="offer.description.image" /></label>
            			<div class="col-xs-4">
                			<img src="${offerDTO.offerPath}" class="img-thumbnail img-responsive" style="height:auto; width:auto; max-width:100px; max-height:100px;"/>
            			</div>
        			</div>
        			
        			<div class="form-group">
            			<label  class="control-label col-xs-5"><spring:message code="offer.coupon.image" /></label>
            			<div class="col-xs-4">
                			<img src="${offerDTO.couponPath}" class="img-thumbnail img-responsive" style="height:auto; width:auto; max-width:100px; max-height:100px;"/>
            			</div>
        			</div>
				
					<div class="form-group">
            			<label  class="control-label col-xs-5"><spring:message code="offer.video.label"/></label>
            			<div class="col-xs-5">
                			<form:input cssClass="form-control" path="videoPath" readonly="true"/>
            			</div>
        			</div>
				
					<div class="form-group">
            			<label  class="control-label col-xs-5"><spring:message code="offer.validity.date"/></label>
            			<div class="col-xs-3">
                			<form:input cssClass="form-control" path="validUpto" readonly="true"/>
            			</div>
        			</div>
        			
        			<div class="form-group">
            			<label  class="control-label col-xs-5"><spring:message code="offer.type"/></label>
            			<div class="col-xs-3">
                			<form:input cssClass="form-control" path="offerType" readonly="true"/>
            			</div>
        			</div>
        			
        			<div class="form-group">
           				 <label  class="control-label col-xs-5"><spring:message code="offer.status"/></label>
            			<div class="col-xs-3">
            				<c:if test="${offerDTO.active eq true}">
                				<input value="Active" class="form-control" disabled="disabled"/>
                			</c:if>
                			<c:if test="${offerDTO.active eq false}">
                				<input value="Not Active" class="form-control" disabled="disabled"/>
                			</c:if>
                			
            			</div>
        			</div>
        			
        			<div class="form-group">
            			<label  class="control-label col-xs-5"><spring:message code="offer.description.label"/></label>
            			<div class="col-xs-3">
                			<form:textarea cssClass="form-control"  path="description" maxlength="50" rows="3" readonly="true"/>
            			</div>
        			</div>
        	
        			<div class="form-group">
            			<div class="col-xs-offset-5 col-xs-8">
                			<a class="btn btn-primary" href="showEditOfferForm.htm">Edit Details</a>
                			<!-- ?offerId=${offerDTO.offerId}&logoId=${sessionScope.activeLogoId} -->
							&nbsp;<a class="btn btn-primary" href="manageOffers.htm?logoId=${sessionScope.activeLogoId}">Cancel</a>
            			</div>
        			</div>
        			<!-- <div id="myModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								dialog body
								<div class="modal-body">
									 <button type="button" class="close" data-dismiss="modal">&times;</button>
											Hello world!
								</div>
									dialog buttons
									<div class="modal-footer"><button type="button" class="btn btn-primary">OK</button></div>
								</div>
							</div>
						</div> -->
				</form:form>
				</div>
				<%@ include file="footer.jsp" %>
			</div>
		</div>
	</body>
</html>