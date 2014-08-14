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
		<script type="text/javascript" src='js/jquery-1.11.0.min.js'></script>
		<script type="text/javascript" src='js/bootstrap.min.js'></script>
		<script type="text/javascript">
			function enableDisableFields(id){
				var status = $("#"+id).val();
				if(status == 1){
	        		$('#input'+id).removeAttr('disabled');
	        		$('#btn'+id).removeAttr('disabled');
	    		}
			}

			function reloadPage(){
			  location.reload();
			}
			
			function updateDevice(id) {
				var deviceId = $("#deviceId"+id).html();
				var status = $("#"+id).val();
				var message = $('#input'+id).val();
				
				var data = { deviceId : deviceId , status : status , message : message};
	
	        	 $.ajax({
	         		   url : "updateDevice.htm",
	         		   dataType: "text",
	         		   data : data,
	         		   type : "POST",
	         		   beforeSend:function(jqXHR, settings){
	        		 	  $(".modal-dialog").hide();
	         			  $("#btn"+id).after('<img id="spinner1" src="./css/ajax-loader.gif" width="25" height="25" style="margin-left:10px;margin-bottom:-6px;"/>');         		   
	         		   },
	         		   success : function(response) {
	         			  $('#spinner1').remove();
	         			  $(".modal-dialog").show();
	         			  $("#mySmallModalLabel").html("Information");
	         			  $(".modal-body").html("Information updated successfully");
	         			  $('#myModal').modal({
	         				  keyboard: false
	         			  });
	         		   },
	         		   error : function(jqXHR, textStatus, errorThrown) {
	         			 	$('#spinner1').remove();
	         			 	$(".modal-dialog").show();
	         				$("#mySmallModalLabel").html("Error");
		         			$(".modal-body").html("Sorry, an error has occured,please try later");
		         			$('#myModal').modal({
		         				  keyboard: false
		         			});		         			
	         		   }
	         		  }); 
			}

			function sendPasscode(id){
				var deviceId = $("#deviceId"+id).html();
				var data = { deviceId : deviceId };
				
				 $.ajax({
	         		   url : "sendPasscode.htm",
	         		   dataType: "text",
	         		   data : data,
	         		   type : "POST",
	         		   beforeSend:function(jqXHR, settings){
       				  	  $(".modal-dialog").hide();
	         			  $("#getpasscodebtn"+id).after('<img id="spinner" src="./css/ajax-loader.gif" width="25" height="25" style="margin-left:10px;margin-bottom:-6px;"/>');
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
	</head>
	
	<body>
		<div class="container">
			<div class="control-group background_image_header" align="center" >
				<img src="images/ic_launcher.png" alt="logoImg" class="img-circle img-responsive" style="height:auto; width:auto; max-width:150px; max-height:150px;"/>
			</div> 
			<div class="background_body">
			
				<%@ include file="header.jsp" %>
				
				<!-- <h2 class="legend">Your Devices</h2> -->
				
				<div style=" clear: both; text-align: right;margin-bottom:30px;">
					<h2 class="legend" style="margin-bottom:0px;">Your Devices</h2>
				</div>
				
				<div class="table-responsive displaytable">
				
					<%-- <div style=" clear: both;">
						<form class="navbar-form navbar-right">
  							<div class="form-group">
    							<input type="text" class="form-control" placeholder="Brand Name or Logo Type"/>
  							</div>
  							<button type="submit" class="btn btn-default">Search</button>
						</form>
					</div> --%>
					 
					<table class="table table-hover table-striped">
						<thead class="header_table">
        					<tr>
            					<th>Device ID</th>
            					<th>Model</th>
            					<th>Registration Date</th>
								<th>Status</th>
								<th>Message</th>
								<th></th>
								<th></th>
        					</tr>
       					 </thead>
       					 <tbody>
       					 	<c:forEach var="device" items="${sessionScope.deviceList}" varStatus="counter">
       					 		<tr>
									<td id="deviceId${counter.count}">
										${device.deviceId} 
									</td>
									<td>
										${device.model}
									</td>
									<td>
										${device.created}
									</td>
									
									<c:if test="${device.status eq false}">
										<td>
											<select name = "deviceStatus" class="form-control" id="${counter.count}" onchange="enableDisableFields(${counter.count});">
                								<option value="0" selected="selected">Active</option>
                								<option value="1">Lost</option>
            								</select>
            							</td>
            							<td class="col-xs-3">
										 	<input id="input${counter.count}" type="text" class="form-control" id="message" placeholder="message" value="${device.message}" disabled/>
										</td>			
										<td>
    									<a id="btn${counter.count}" data-target=".bs-example-modal-sm" data-toggle="modal" onclick="updateDevice(${counter.count})" class="btn btn-primary" href="#" disabled>Update</a>
   										</td>
									</c:if>
									<c:if test="${device.status eq true}">
										<td>
											<select name = "deviceStatus" class="form-control" id="${counter.count}">
                								<option value="0">Active</option>
                								<option value="1" selected="selected">Lost</option>
            								</select>
            							</td>
            							<td class="col-xs-3">
										 	<input id="input${counter.count}" type="text" class="form-control" id="message" placeholder="message" value="${device.message}"/>
										</td>			
										<td>
    										<a id="btn${counter.count}" data-target=".bs-example-modal-sm" data-toggle="modal" onclick="updateDevice(${counter.count})" class="btn btn-primary" href="#">Update</a>
   										</td>
									</c:if>
									<td>
    									<a id="getpasscodebtn${counter.count}" data-target=".bs-example-modal-sm" data-toggle="modal" onclick="sendPasscode(${counter.count})" class="btn btn-primary" href="#">Get Passcode</a>
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
