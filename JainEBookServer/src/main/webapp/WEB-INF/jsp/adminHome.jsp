<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%-- <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
 --%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta content="width=device-width, initial-scale=1" name="viewport"/>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
		<link rel="stylesheet" href="css/generic.css" type="text/css"/>
		<link rel="stylesheet" href="css/admin_home.css" type="text/css"/>
		<script type="text/javascript">
			function editLogo(logoId){
			   window.location.href="./showEditLogoForm.htm?logoId=" + logoId;  
		   	}
			
			function changeLogoStatus(logoId, status){
				window.location.href="./changeLogostatus.htm?logoId="+logoId+"&statusToSet="+status;  
			}
			
			function submitLogoStatusChangeForm(action) {
				var IsAtLeastOneChecked = $('input[type="checkbox"]:checked').length > 0
				$(".modal-dialog").hide();
				if(IsAtLeastOneChecked){
					$('#action').attr("value",action);
					$("#logoStatusChangeForm").submit();
				}else{
					$(".modal-dialog").show();
     			 	$("#mySmallModalLabel").html("Information");
     			 	$(".modal-body").html('Please Select Brand/Brands');
				}
			}
			
			function changeStatus() {
				var status = $('#selectAll').is(':checked');
				if(status) {
					$('.noClass').prop('checked', true);
				} else {
					$('.noClass').prop('checked', false);
				}
			}
		</script>
		<style>
			.custom-search-form{
    			margin-top:5px;
			}
			
			.btn-primary-custom{color:#fff;background-color:#428bca;border-color:#357ebd}
		</style>
	</head>
	
	<body>
		<div class="container">
			<div class="control-group background_image_header" align="center" >
				<img src="images/ic_launcher.png" alt="logoImg" class="img-circle img-responsive" style="height:auto; width:auto; max-width:150px; max-height:150px;"/>
			</div> 
			<div class="background_body">
			
				<%@ include file="header.jsp" %>
				
				<h2 class="legend">Manage Logos  
					<a title="Add Logo" href="showAddLogoForm.htm"> 
						<img class="img-thumbnail img-responsive" align="left" src="images/add_logo.png" style="height:auto; width:auto; max-width:80px; max-height:60px;margin-top: 5px;margin-left: 5px;"/>
					</a>
				</h2>
				
				<div class="table-responsive displaytable">
				
					<%-- <div style=" clear: both;">
						<form class="navbar-form navbar-right">
  							<div class="form-group">
    							<input type="text" class="form-control" placeholder="Brand Name or Logo Type"/>
  							</div>
  							<button type="submit" class="btn btn-default">Search</button>
						</form>
					</div> --%>
					 <div style="clear: both;">
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
					 </div> 
					 
					<form:form commandName="logoStatusChangeForm" action="changeLogostatus1.htm" method="POST" id="logoStatusChangeForm"> 
					 
					<table class="table table-hover table-striped">
						<thead class="header_table">
        					<tr>
        						<th><input type="checkbox" id="selectAll" onclick="changeStatus()"></input></th>
            					<th>Logo</th>
            					<th>Brand Name</th>
            					<th>Logo Type</th>
								<th>Status</th>
								<th><a class="btn btn-primary-custom" data-target=".bs-example-modal-sm" data-toggle="modal" href="#" onclick="submitLogoStatusChangeForm('activate')">Activate Logo</a></th>
								<th><a class="btn btn-primary-custom" data-target=".bs-example-modal-sm" data-toggle="modal" href="#" onclick="submitLogoStatusChangeForm('deactivate')">Deactivate Logo</a></th>
        					</tr>
       					 </thead>
       					 <tbody>
       					 	<c:forEach var="logo" items="${sessionScope.logoList}">
       					 		<tr>
       					 			<td>
       					 				 <form:checkbox path="logoList" value="${logo.id}" cssClass="noClass"/>
       					 				 <input id="action" type="hidden" value="" name="action"/>
       					 			</td>
									<td>
										<img src="${logo.imagePath}" class="img-thumbnail img-responsive" style="height:auto; width:auto; max-width:50px; max-height:50px;"/>
									</td>
									<td>
										${logo.name}
									</td>
									<td>
										${logo.logoType.type}
									</td>
									<td>
										<c:if test="${logo.active eq true}">
											Active
										</c:if>
										<c:if test="${logo.active eq false}">
											Not Active
										</c:if>
									</td>			
									<td>
    									<a class="btn btn-primary" href="#" onclick="editLogo(${logo.id})">Edit</a>
   									</td>
									<td>
										<c:if test="${logo.active eq true}">
    										<a class="btn btn-primary" href="manageOffers.htm?logoId=${logo.id}">Manage Offers</a>
    									</c:if>	
   									</td>
								</tr>
       					 	</c:forEach>
       					 </tbody>
					</table>
					</form:form>
				</div>
				<!--<div class="footer">
					<p>Copyright &copy; <a style="color:white;" target="_blank" href="http://www.qainfotech.com/">Your Key Lock </a>,2014. All rights reserved.</p>
				</div>
			-->
			    <%@ include file="footer.jsp" %>
			</div>
		</div>
		
	
								<%-- <display:table  name="${sessionScope.logoList}" id="logo" class="zebra-striped" style="width:500px">
									<display:column>
										<img src="${logo.imagePath}" width="78" height="77" style="margin-left:-10px;"/>
									</display:column>
     								<display:column property="name" title="Brand Name"  style="text-align: center;"/>
									<display:column property="logoType.type" title="Logo Type" style="text-align: center;" />
						
									<display:column >
			  							<a class="btn primary" href="#">Activate/Deactivate</a>
									</display:column>			
									<display:column>
    									<a class="btn primary" href="#">Edit</a>
   									</display:column>
									<display:column>
    									<a class="btn primary" href="#">Manage Brands</a>
   									</display:column>   
   								</display:table> --%>
   		<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  			<div class="modal-dialog modal-sm">
   				 <div class="modal-content">
      				<div class="modal-header">
						<button class="close" aria-hidden="true" data-dismiss="modal" type="button">×</button>
						<h4 id="mySmallModalLabel" class="modal-title"></h4>
					</div>
					<div class="modal-body"> ... </div>
    			</div>
  			</div>
		</div>						
		<script type="text/javascript" src='js/jquery-1.11.0.min.js'></script>
		<script type="text/javascript" src='js/bootstrap.min.js'></script>
	</body>
</html>
