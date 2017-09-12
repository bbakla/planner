<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Goal creation page</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link type="application/javascript" href="${pageContext.request.contextPath}/js/parentTable.js">
<link type="application/javascript" href="${pageContext.request.contextPath}/js/dateUtil.js">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/panel.css">


<script type="text/javascript">	$(document)
			.ready(function() {
				
				var progressPercentage = $('#percentage').text();
				
				var style = "width:" + progressPercentage +"%";
				$('div#innerProgressBar').attr("style", style);
			});
 </script>

<script type="text/javascript">	$(document)
 		.ready(function()
 				{
 			
 			var date = new Date();
 			var monthNumber = date.getMonth();
 			
 			var options = $('#timeframeSelect option');

 			var values = $.map(options ,function(option) {
 			    return option.value;
 			});
 			
 			var currentMonth = values[monthNumber]; 
 			
 			$("#timeframeSelect").val(currentMonth);
 			$("#timeframeSelectMonth").val(currentMonth);

 				});
 </script>

</head>
<body>
	<div class="container">
		<nav class="navbar navbar-light">
			<a class="navbar-brand" href="<c:url value='/new/year'/>">Yearly goals</a>
		 	<a class="navbar-brand" href="<c:url value='/new/month'/>">Monthly goals</a>
		 	<a class="navbar-brand" href="<c:url value='/new/week'/>">Weekly goals</a> 
		 	<a class="navbar-brand" href="<c:url value='/new/day'/>">Daily goals</a>
		 	<a class="navbar-brand" href="<c:url value='/plan/week'/>">Week planner</a>
		 </nav>
	</div>


	<div class="container">
		<div class="row">
			<div class="col-md-11">
				<div class="panel panel-default">
					<div class="panel-heading" id="goalTitle">Enter your monthly goal</div>
					<div class="panel-body">

						<form:form method="POST" modelAttribute="parent">
							<div class="form-group">

								<label for="title" class="col-2 col-form-label">Goal
									title</label>
								<form:input path="title" id="title" class="form-control" />
							</div>
							<div class="form-group">
								<label for="parentList" class="col-2 col-form-label">Select
									the related yearly goal</label>
								<form:select id="parentList" path="parentGoal.id"
									class="form-control">
									<form:options items="${yearlyGoals}" itemLabel="title"
										itemValue="id"></form:options>
								</form:select>

								<div class="form-group">
									<label for="timeframeSelect" class="col-2 col-form-label">Time
										label:</label>
									<form:select id="timeframeSelect" path="details.timeUnit"
										class="form-control">
										<form:options items="${months}" />
									</form:select>
								</div>

								<div class="form-group">
									<label for="description" class="col-2 col-form-label">Description:</label>
									<form:input path="details.description.description"
										id="timeLabel" class="form-control" />
								</div>
							</div>

							<form:button name="Create" class="btn btn-primary btn-lg  btn-block">Create</form:button>
						</form:form>

						<br />
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="container">
		<div class="row">
			<div class="col-md-11">
				<div class="panel panel-default">
					<div class="panel-heading" id="goalTitle">Your goals</div>
					<div class="panel-body">

						<form:select id="timeframeSelectMonth" path="months"
							class="form-control" items="${months}" />

						<div class="table-responsive">
							<table id="mytable" class="table table-bordred table-striped">
								<thead>

									<th><input type="checkbox" id="checkall" /></th>
									<th>Id</th>
									<th>Goal name</th>
									<th>Completed</th>
									<th>Progress</th>
									<th>Status</th>
									<th>Edit</th>
									<th>Delete</th>
								</thead>
								<tbody>
									<c:forEach items="${monthlyGoals}" var="goal">
										<label id="percentage" hidden>${goal.progress.progressPercentage}</label>
										<tr>
											<td><input type="checkbox" class="checkthis" /></td>
											<td>${goal.id}</td>
											<td><a href="<c:url value='/goal/${goal.id}'/>">${goal.title}</a></td>
											<td>${goal.progress.completed}</td>
											<td>
												<div id="progressBar" class="progress">

													<div id="innerProgressBar" style="width: 80%;"
														aria-valuemax="100" aria-valuemin="0" aria-valuenow="50"
														role="progressbar" class="progress-bar">
														<span>${goal.progress.progressPercentage}</span>
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
														data-toggle="modal" data-target="#deleteItem">
														<span class="glyphicon glyphicon-trash"></span>
													</button>
												</p></td>
										</tr>
										
											<div class="modal fade" id="deleteItem" tabindex="-1"
											role="dialog" aria-labelledby="Delete Item"
											aria-hidden="true">
											<div class="modal-dialog" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="title">Delete Item</h5>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<form method="POST"
														action="/planner_spring_mvc_mysql/goal/delete/${goal.id}">
														<div class="modal-body">

															<div class="form-group">
																<div class="alert alert-danger">
																	<span class="glyphicon glyphicon-warning-sign"></span>
																	Are you sure you want to delete the goal, ${goal.title}?
																</div>
															</div>
														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-secondary"
																data-dismiss="modal">Close</button>
															<input type="submit" name="Delete" value="Delete"
																class="btn btn-primary" />
														</div>
													</form>
												</div>
											</div>
										</div>
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
		</div>
	</div>

</body>

</html>