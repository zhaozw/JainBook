<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<title>Manage Unlocks Page</title>
	<meta content="width=device-width, initial-scale=1" name="viewport"/>
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
	<link rel="stylesheet" href="css/generic.css" type="text/css"/>
	
	<script type="text/javascript" src='js/jquery-1.11.0.min.js'></script>
	<script type="text/javascript" src='js/bootstrap.min.js'></script>
	
	<!-- js and css for date picker -->
	<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css" type="text/css"/>
	<script type="text/javascript" src='js/moment.min.js'></script>
	<script type="text/javascript" src='js/bootstrap-datetimepicker.min.js'></script>
	
	<script type="text/javascript">
            $(function () {
            	 $('#fromDateTimePicker').datetimepicker({
                     pick12HourFormat: false
                 });
            });
     </script>
     
     <script type="text/javascript">
            $(function () {
            	 $('#toDateTimePicker').datetimepicker({
                     pick12HourFormat: false
                 });
            });
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
					<h2 class="legend" style="margin-bottom:0px;">Manage Unlocks</h2>
					<span style="color: black;">*indicates mandatory fields</span>
				</div>
				
				<div class="form-addEditLogo">
				<form:form method="POST" commandName="manageUnlcokForm" action="manageUnlocks.htm" cssClass="form-horizontal"> 
					<div class="form-group">
						<label  class="control-label col-xs-4">From Date*</label>
						<div class="col-xs-4">
							<div class='input-group date' id='fromDateTimePicker'>
                    			<form:input cssClass="form-control" path="fromDate" id="fromDate"/>
                    			<span class="input-group-addon">
                    				<span class="glyphicon glyphicon-calendar"></span>
                   				 </span>
							</div>
							 <form:errors path="fromDate" cssClass="error" />
						</div>
					</div>
					
					<div class="form-group">
						<label  class="control-label col-xs-4">To Date*</label>
						<div class="col-xs-4">
							<div class='input-group date' id='toDateTimePicker'>
								<form:input cssClass="form-control" path="toDate" id="toDate"/>
                    			<span class="input-group-addon">
                    				<span class="glyphicon glyphicon-calendar"></span>
                   				 </span>
							</div>
							<form:errors path="toDate" cssClass="error" />
						</div>
					</div>
					
					<div class="form-group">
            			<div class="col-xs-offset-4 col-xs-8">
                			<button type="submit" class="btn btn-primary" value="Submit">Submit</button>
								&nbsp;<a class="btn btn-primary" href="adminHome.htm">Cancel</a>
            			</div>
        			</div>
					
				</form:form>
   				 </div>
   				 <%@ include file="footer.jsp" %>
		   </div>
	</div>
</body>
</html>