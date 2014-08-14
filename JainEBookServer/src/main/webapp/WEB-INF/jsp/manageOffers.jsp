<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta content="width=device-width, initial-scale=1" name="viewport"/>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
		<link rel="stylesheet" href="css/generic.css" type="text/css"/>
		<link rel="stylesheet" href="css/admin_home.css" type="text/css"/>
		
		<script type="text/javascript" src='js/jquery-1.11.0.min.js'></script>
		<script type="text/javascript" src='js/bootstrap.min.js'></script>
		
		<script type="text/javascript">
			function reloadPage(){
			  location.reload();
			}
		
			function changeOfferStatus(offerId, status, id) {

        	 $.ajax({
         		   url : "changeOfferStatus.htm?offerId="+offerId+"&statusToSet="+status,
         		   dataType: "text",
         		   data : "",
         		   type : "GET",
         		   beforeSend:function(jqXHR, settings){
        		 	  $(".modal-dialog").hide();
         			  $("#btn"+id).after('<img id="spinner" src="./css/ajax-loader.gif" width="25" height="25" style="margin-left:10px;margin-bottom:-6px;"/>');         		   
         		   },
         		   success : function(response) {
         			  $('#spinner').remove();
         			  $(".modal-dialog").show();
         			  $("#mySmallModalLabel").html("Information");
         			  $(".modal-body").html(response);
         			  $('#myModal').modal({
         				  keyboard: false
         			  });
         		   },
         		   error : function(jqXHR, textStatus, errorThrown) {
         			 	$('#spinner').remove();
         			 	$(".modal-dialog").show();
         				$("#mySmallModalLabel").html("Error");
	         			$(".modal-body").html("Sorry, an error has occured,please try later");
	         			$('#myModal').modal({
	         				  keyboard: false
	         			})
         		   }
         		  }); 
		}
		</script>
		
		<style>
			.custom-search-form{
    			margin-top:5px;
			}
		</style>
	</head>
	
	<body>
		<div class="container">
			<div class="control-group background_image_header" align="center" >
				<img src="images/ic_launcher.png" alt="logoImg" class="img-circle img-responsive" style="height:auto; width:auto; max-width:150px; max-height:150px;"/>
			</div> 
			<div class="background_body">
				
				<%@ include file="header.jsp" %>
				
				<h2 class="legend">Manage Offers  
					<%-- <a title="Add Offer" href="showAddOfferForm.htm?logoId=${requestScope.logoId}"> 
						<img class="img-thumbnail img-responsive" align="left" src="images/add_logo.png" style="height:auto; width:auto; max-width:80px; max-height:60px;margin-top: 5px;margin-left: 5px;"/>
					</a> --%>
					<a title="Add Offer" href="showAddOfferForm.htm"> 
						<img class="img-thumbnail img-responsive" align="left" src="images/add_offer.png" style="height:auto; width:auto; max-width:80px; max-height:60px;margin-top: 5px;margin-left: 5px;"/>
					</a>
				</h2>
				
				<div class="table-responsive displaytable">

					<%--  <div style="clear: both;">
						<div class="col-lg-4" style="float: right;padding-right: 0px;padding-bottom: 5px;">
							<form:form action="search.htm" commandName="searchForm" method="GET">
            					<div class="input-group custom-search-form">
            						<form:input type="text" path="searchString" cssClass="form-control" placeholder="Brand Name or Logo Type"/>
              						<span class="input-group-btn">
              							<button class="btn btn-default" type="submit">
              								<span class="glyphicon glyphicon-search" style="line-height: 1.435;"></span>
             							</button>
             						</span>
             					</div>
             				</form:form>
        				</div>
					 </div>  --%>
					 
					<table class="table table-hover table-striped">
						<thead class="header_table">
        					<tr>
            					<th>Offer</th>
            					<th>Validity</th>
            					<th>Offer Type</th>
								<th></th>
								<th></th>
        					</tr>
       					 </thead>
       					 <tbody>
       					 	<c:forEach var="offer" items="${offerList}" varStatus="counter">
       					 		<tr>
									<td>
										<img src="${offer.offerPath}" class="img-thumbnail img-responsive" style="height:auto; width:auto; max-width:100px; max-height:100px;"/>
									</td>
									<td>
										${offer.validUpto}
									</td>
									<td>
										${offer.offerType}
									</td>
									<td>
										<c:if test="${offer.active eq false}">
											<a id="btn${counter.count}" class="btn btn-primary" href="#" data-target=".bs-example-modal-sm" data-toggle="modal" onclick="changeOfferStatus(${offer.offerId},'1',${counter.count})">Activate</a>
										</c:if>
										<c:if test="${offer.active eq true}">
											<a id="btn${counter.count}" class="btn btn-primary" href="#" data-target=".bs-example-modal-sm" data-toggle="modal" onclick="changeOfferStatus(${offer.offerId},'0',${counter.count})">Deactivate</a>
										</c:if>
									</td>			
									<td>
    									<a class="btn btn-primary" href="offerDetails.htm?offerId=${offer.offerId}&logoId=${sessionScope.activeLogoId}">View</a>
   									</td>
								</tr>
       					 	</c:forEach>
       					 </tbody>
					</table>
				</div>
			    <%@ include file="footer.jsp" %>
			</div>
		</div>
		
		<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" onclick="reloadPage();">
  				<div class="modal-dialog modal-sm">
   				 <div class="modal-content">
      				<div class="modal-header">
						<button class="close" aria-hidden="true" data-dismiss="modal" type="button" onclick="reloadPage();">×</button>
						<h4 id="mySmallModalLabel" class="modal-title"></h4>
					</div>
					<div class="modal-body"> ... </div>
    			</div>
  				</div>
		</div>
		
	</body>
</html>
