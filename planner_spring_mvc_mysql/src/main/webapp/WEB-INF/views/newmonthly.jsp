<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Goal creation page</title>

<style>
.error {
	color: #ff0000;
}
</style>

</head>
<body>
	<h2>Enter your goal</h2>
	
	<form:form method="POST" modelAttribute="parent">
		
		<table>
			<tr>
				<td><label for="title">Enter your monthly goal </label></td>
				<td><form:input path="title" id="title"/></td>
			</tr>
			<tr>
			<td><label for= "parentList">Select the related yearly goal</label></td>
			<td>
				<form:select id="parentList" path="parentGoal.id">
					<form:options   items= "${yearlyGoals}" itemLabel="title" itemValue="id"></form:options>
				</form:select>
				</td>
			</tr>
				
			<tr>
				<td><label for="timeLabel">Time label:</label></td>
				<td><form:input path="details.timeLabel" id="timeLabel"/> </td>
			</tr>
			
			<tr>
				<td><label for="description">Description:</label></td>
				<td><form:input path="details.description.description" id="timeLabel"/> </td>
			</tr>
			
			<tr>
				
			</tr>
		</table>
		
		<form:button name="Create">Create</form:button>
	</form:form>
	
	<br/>
	<font color="red">${message}</font>
	
	

</body>
</html>