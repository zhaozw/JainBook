<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<div style=" clear: both; text-align: right;">
	<span style="color: #8AC43C; font-weight: bold;">Welcome:</span> 
	<span style="font-weight: bold; color: black;">${sessionScope.user.name}</span> 
</div>

<div style="display: inline;">
    <c:if test="${sessionScope.user.role.roleType eq 'admin' }">
    	<span style="font-weight: bold;"><a href="adminHome.htm">Home</a></span>&nbsp;&nbsp;&nbsp;
    	<span style="font-weight: bold;"><a href="showManageUnlocksForm.htm">Manage Unlocks</a></span>
    </c:if>
	<span style="font-weight: bold; float:right"><a href="signOut.htm">Sign out</a></span>
</div>