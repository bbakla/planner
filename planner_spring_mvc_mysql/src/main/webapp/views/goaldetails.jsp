<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>${goal.title} details</h1>

	<table>
		<tr>
			<td><label>Description</label></td>
			<td><form:input path="goal.details.description.description"
					id="goalDescription" /></td>
		</tr>

		<tr>
			<td><label>Parent goal</label></td>
			<td><a href="<c:url value='/planner/goal/${goal.id}'/>">${goal.title}</a></td>
			<%-- 			<td><form:input path="goal.parentGoal.title" id = "parentGoal"/></td> --%>
		</tr>

		<tr>
			<td><label>Scope</label></td>
			<td><form:input path="goal.details.scope" id="scope" /></td>
		</tr>

		<tr>
			<td><label>Status</label></td>
			<td><form:input path="goal.details.status" id="status" /></td>
		</tr>

		<tr>
			<td><label>time frame</label></td>
			<td><form:input path="goal.details.timeUnit" id="timeLabel" /></td>
		</tr>
	</table>

	<br/>
	<div>
		<h3>Child goals</h3>
		
		<c:forEach items="${goal.childGoals}" var="child">
			<a href="<c:url value='/planner/goal/${child.id}'/>">${child.title}</a>
			<br />
		</c:forEach>
		<br /> <br />

	</div>

	<div>
		<h3>comments</h3>
		<br /> <br />

		<c:forEach items="${goal.details.description.comments}" var="comment">
			<label>${comment} </label>
		</c:forEach>
	</div>
</body>
</html>