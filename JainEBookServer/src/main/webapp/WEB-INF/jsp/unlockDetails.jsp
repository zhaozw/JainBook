<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
<head>
	<title>Unlock Details Page</title>
	
	<meta content="width=device-width, initial-scale=1" name="viewport"/>
	
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
	<link rel="stylesheet" href="css/generic.css" type="text/css"/>
	<link rel="stylesheet" href="css/admin_home.css" type="text/css"/>
	
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
					<h2 class="legend" style="margin-bottom:0px;">Unlock Details</h2>
					<div style=" clear: both; text-align: left">
						<span style="color: #8AC43C;"><b>Time Period :</b></span> <b>${requestScope.fromDate} - ${requestScope.toDate}</b>
					</div>
					<div style=" clear: both; text-align: left">
						<span style="color: #8AC43C;"><b>Total unlocks :</b></span> <b>${requestScope.totalUnlocks}</b>
					</div>
				</div>
				
				<div class="table-responsive displaytable">
					<table class="table table-hover table-striped">
						<thead class="header_table">
        					<tr>
            					<th>User Name</th>
            					<th>Device ID</th>
            					<th>Number of Unlocks</th>
							</tr>
       					 </thead>
       					 <tbody>
       					 	<c:forEach var="item" items="${requestScope.userUnlocksList}">
       					 		<tr>
									<td>
										${item.email}
									</td>
									<td>
										${item.deviceId}
									</td>
									<td>
										${item.count}
									</td>
								</tr>
       					 	</c:forEach>
       					 </tbody>
					</table>
				</div>
				
   				 <%@ include file="footer.jsp" %>
		   </div>
	</div>
</body>
</html>