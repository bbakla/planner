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
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> -->
<!-- <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script> -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
	<link type="text/css" rel="stylesheet" 	href="${pageContext.request.contextPath}/js/parentTable.js">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<h2>Enter your goal</h2>

				<form:form method="POST" modelAttribute="parent">
					<div class="form-group">

						<label for="title" class="col-2 col-form-label">Enter your weekly goal </label>
						<form:input path="title" id="title" class="form-control"/>
					</div>
					<!-- 			<tr> -->
					<!-- 				<td><label for="months">Month</label></td> -->
					<!-- 				<td> -->
					<%-- 				<form:select id="months" path="parentGoal"> --%>
					<%-- 						<form:options items="${months}" itemLabel="monthName" --%>
					<%--  							itemValue="monthNumber"></form:options>  --%>
					<%--  					</form:select> --%>
					<!-- 				</td> -->
					<div class="form-group">
						<label for="parentList" class="col-2 col-form-label">Select the related monthly goal</label>
						<form:select id="parentList" path="parentGoal.id" class="form-control">
							<form:options items="${parentMontlhyGoals}" itemLabel="title"
								itemValue="id"></form:options>
						</form:select>
					</div>
					<div class="form-group">
						<label for="timeLabel" class="col-2 col-form-label">Time label:</label>
						<form:input path="details.timeLabel" id="timeLabel" class="form-control"/>
					</div>
					<div class="form-group">
						<label for="description" class="col-2 col-form-label">Description:</label>
						<form:input path="details.description.description" id="timeLabel" class="form-control"/>
					</div>
					<form:button name="Create">Create</form:button>
				</form:form>

			</div>
		</div>
	</div>

	<br/>
	
	<div class="container">
		<div class="row">


			<div class="col-md-12">
				<h4>Your goals</h4>
				<div class="table-responsive">


					<table id="mytable" class="table table-bordred table-striped">

						<thead>

							<th><input type="checkbox" id="checkall" /></th>
							<th>Id</th>
							<th>Goal name</th>
							<th>Edit</th>

							<th>Delete</th>
						</thead>
						<tbody>
							<c:forEach items="${weeklyGoals}" var="goal">
								<tr>
									<td><input type="checkbox" class="checkthis" /></td>
								    <td>${goal.id}</td>
									<td><a href="<c:url value='/planner/goal/${goal.id}'/>">${goal.title}</a></td>
									
									<td>
										<div class="progress">
											<div style="width: 60%;" aria-valuemax="100" aria-valuemin="0" aria-valuenow="60" role="progressbar" class="red progress-bar">
												<span>60%</span>
									       </div>
									 	</div>
									</td>
									<td><span class="label label-warning">${goal.details.status }</span></td>
									
									<td><p title="Edit">
										<button class="btn btn-primary btn-xs" data-title="Edit"
											data-toggle="modal" data-target="#edit">
											<span class="glyphicon glyphicon-pencil"></span>
										</button>
									</p></td>
									
								<td><p data-placement="top" data-toggle="tooltip"
										title="Delete">
										<button class="btn btn-danger btn-xs" data-title="Delete"
											data-toggle="modal" data-target="#delete">
											<span class="glyphicon glyphicon-trash"></span>
										</button>
									</p></td>
								</tr>
<%-- 										<a href="<c:url value='/planner/goal/${goal.id}'/>">${goal.title}</a> --%>
										<%-- 			<c:forEach items="${goal.childGoals}" var="child"> --%>
										<%-- 				<a href="<c:url value='/planner/goal/${child.id}'/>">${child.title}</a> --%>
										<!-- 				<br /> -->
										<%-- 			</c:forEach> --%>
							</c:forEach>

						
						</tbody>

					</table>

					<div class="clearfix"></div>
					<ul class="pagination pull-right">
						<li class="disabled"><a href="#"><span
								class="glyphicon glyphicon-chevron-left"></span></a></li>
						<li class="active"><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#"><span
								class="glyphicon glyphicon-chevron-right"></span></a></li>
					</ul>

				</div>

			</div>
		</div>
	</div>
	
	<font color="red">${message}</font>



</body>
</html>