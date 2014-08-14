<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
	<body>
 		<h3>Welcome ${user.name}</h3><br/>
 		<%@ include file="adminNavigationHeader.jsp"%>
	</body>
	
	<br/><br/>
	
	<form:form method="POST" commandName="deleteLogoForm" action="deleteLogo.htm">  
	<table>  
    	<tbody>
    		<tr>  
    			<td>
    				<label>Logo Type: </label>
    			</td>
    			<td>  
       				 <ul>  
            			<form:select path="logoType">  
                			<form:option value="0" label="--Select Logo Type--"/>  
                			<form:options itemValue="id" itemLabel="type" items="${addLogoForm.logoTypes}"/>  
            			</form:select>  
            			<form:errors path="logoType"/>
        			</ul>  
   				 </td>  
    		</tr> 
    		<tr>
    			<td>
    				<label>Logo Name: </label>
    			</td>
    			<td>
    				<form:input path="logoName" type="text" size="15"/>
					<form:errors path="logoName"/>
    			</td>
    		</tr> 
    		<tr>
    			<td>
    				<label>Select File: </label>
    			</td>
    			<td>
    				<input type="file" size="15" name="file" accept="image/*" />
    				<form:errors path="file"/>
    			</td>
    		</tr> 
    		<tr>  
        		<td>  
            		<input value="Add" type="submit"/>  
        		</td>  
    		</tr>  
		</tbody>
	</table>    
	<div>
		<c:if test="${not empty uploadFalied}">
			<div>Error in uploading file</div>
		</c:if>
	</div>
	</form:form>  
	
</html>