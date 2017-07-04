<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>Goal Details</title>
</head>
<body>
	<h2>${goal.title}details</h2>

	<form method="POST" class="form-horizontal">
	<div class="form-group">
		<label class="control-label col-sm-2">Title</label>
		<div class="col-sm-5">
			<form:input path="goal.title" id="goalTitle" class="form-control" />
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-sm-2">Description</label>
		<div class="col-sm-5">
			<form:input path="goal.details.description.description"
			class="form-control " id="goalDescription" />
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-sm-2">Parent goal</label> 
		<div class="col-sm-5">
		<a href="<c:url value='/planner/goal/${goal.id}'/>"
			
			class="form-control">${goal.title}</a>
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-sm-2">Scope</label>
		<div class="col-sm-5">
		<form:input path="goal.details.scope" id="scope"
			class="form-control" />
			</div>
	</div>

	<div class="form-group">
		<label class="control-label col-sm-2">Status</label>
		<div class="col-sm-5">
		<form:input path="goal.details.status" id="status"
			class="form-control" />
			</div>
	</div>

	<br />
	<div class="form-group">
		<label class="control-label col-sm-2">Child goals</label>

		<c:forEach items="${goal.childGoals}" var="child">
			<a href="<c:url value='/planner/goal/${child.id}'/>"
				class="form-control">${child.title}</a>
			<br/>
		</c:forEach>
		<br /> <br />

	</div>

	<div class="form-group">
		<label class="control-label col-sm-2">Comments</label>
		<br /> <br />

		<c:forEach items="${goal.details.description.comments}" var="comment">
			<label>${comment} </label>
		</c:forEach>
	</div>
	
	  <div class="form-group"> 
    <div class="col-sm-offset-2 col-sm-10">
       <spring:url value="/planner/goal/${goal.id}" var="updateUrl" />
   	<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
    </div>
  </div>
  
  
	
</form>
<%-- 	<form:button name="Create" class="btn btn-primary">Create</form:button> --%>
	
<%-- 			</form> --%>
</body>
</html>