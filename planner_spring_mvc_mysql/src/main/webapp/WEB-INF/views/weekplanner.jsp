<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
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

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.8.20/jquery-ui.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/table.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
<link href="${pageContext.request.contextPath}/js/dateUtil.js">

<script type="text/javascript">
	$(document)
			.ready(function() {

				$('#weekTable').submit(
						function(event) {
							var week = {};
							var monday = {};
							var tuesday = {};
							var wednesday = {};
							var thursday = {};
							var friday = {};
							var saturday = {};
							var sunday = {};

							var till_9 = 
							monday["till_9"] = $('#goal_id_MONDAY_TILL_9').text();
							monday["till_10"] = $('#goal_id_MONDAY_TILL_10').text();
							monday["till_11"] = $('#goal_id_MONDAY_TILL_11').text();
							monday["till_12"] = $('#goal_id_MONDAY_TILL_12').text();
							monday["till_13"] = $('#goal_id_MONDAY_TILL_13').text();
							monday["till_14"] = $('#goal_id_MONDAY_TILL_14').text();
							monday["till_15"] = $('#goal_id_MONDAY_TILL_15').text();
							monday["till_16"] = $('#goal_id_MONDAY_TILL_16').text();
							monday["till_17"] = $('#goal_id_MONDAY_TILL_17').text();
							monday["after_17"] = $('#goal_id_MONDAY_AFTER_17').text();

							tuesday["till_9"] = $('#goal_id_TUESDAY_TILL_9').text();
							tuesday["till_10"] = $('#goal_id_TUESDAY_TILL_10').text();
							tuesday["till_11"] = $('#goal_id_TUESDAY_TILL_11').text();
							tuesday["till_12"] = $('#goal_id_TUESDAY_TILL_12').text();
							tuesday["till_13"] = $('#goal_id_TUESDAY_TILL_13').text();
							tuesday["till_14"] = $('#goal_id_TUESDAY_TILL_14').text();
							tuesday["till_15"] = $('#goal_id_TUESDAY_TILL_15').text();	
							tuesday["till_16"] = $('#goal_id_TUESDAY_TILL_16').text();
							tuesday["till_17"] = $('#goal_id_TUESDAY_TILL_17').text();
							tuesday["after_17"] = $('#goal_id_TUESDAY_AFTER_17').text();
							
							wednesday["till_9"] = $('#goal_id_WEDNESDAY_TILL_9').text();
							wednesday["till_10"] = $('#goal_id_WEDNESDAY_TILL_10').text();
							wednesday["till_11"] = $('#goal_id_WEDNESDAY_TILL_11').text();
							wednesday["till_12"] = $('#goal_id_WEDNESDAY_TILL_12').text();
							wednesday["till_13"] = $('#goal_id_WEDNESDAY_TILL_13').text();
							wednesday["till_14"] = $('#goal_id_WEDNESDAY_TILL_14').text();
							wednesday["till_15"] = $('#goal_id_WEDNESDAY_TILL_15').text();	
							wednesday["till_16"] = $('#goal_id_WEDNESDAY_TILL_16').text();
							wednesday["till_17"] = $('#goal_id_WEDNESDAY_TILL_17').text();
							wednesday["after_17"] = $('#goal_id_WEDNESDAY_AFTER_17').text();
							
							thursday["till_9"] = $('#goal_id_THURSDAY_TILL_9').text();
							thursday["till_10"] = $('#goal_id_THURSDAY_TILL_10').text();
							thursday["till_11"] = $('#goal_id_THURSDAY_TILL_11').text();
							thursday["till_12"] = $('#goal_id_THURSDAY_TILL_12').text();
							thursday["till_13"] = $('#goal_id_THURSDAY_TILL_13').text();
							thursday["till_14"] = $('#goal_id_THURSDAY_TILL_14').text();
							thursday["till_15"] = $('#goal_id_THURSDAY_TILL_15').text();	
							thursday["till_16"] = $('#goal_id_THURSDAY_TILL_16').text();
							thursday["till_17"] = $('#goal_id_THURSDAY_TILL_17').text();
							thursday["after_17"] = $('#goal_id_THURSDAY_AFTER_17').text();
							
							friday["till_9"] = $('#goal_id_FRIDAY_TILL_9').text();
							friday["till_10"] = $('#goal_id_FRIDAY_TILL_10').text();
							friday["till_11"] = $('#goal_id_FRIDAY_TILL_11').text();
							friday["till_12"] = $('#goal_id_FRIDAY_TILL_12').text();
							friday["till_13"] = $('#goal_id_FRIDAY_TILL_13').text();
							friday["till_14"] = $('#goal_id_FRIDAY_TILL_14').text();
							friday["till_15"] = $('#goal_id_FRIDAY_TILL_15').text();	
							friday["till_16"] = $('#goal_id_FRIDAY_TILL_16').text();
							friday["till_17"] = $('#goal_id_FRIDAY_TILL_17').text();
							friday["after_17"] = $('#goal_id_FRIDAY_AFTER_17').text();
							
							saturday["till_9"] = $('#goal_id_SATURDAY_TILL_9').text();
							saturday["till_10"] = $('#goal_id_SATURDAY_TILL_10').text();
							saturday["till_11"] = $('#goal_id_SATURDAY_TILL_11').text();
							saturday["till_12"] = $('#goal_id_SATURDAY_TILL_12').text();
							saturday["till_13"] = $('#goal_id_SATURDAY_TILL_13').text();
							saturday["till_14"] = $('#goal_id_SATURDAY_TILL_14').text();
							saturday["till_15"] = $('#goal_id_SATURDAY_TILL_15').text();	
							saturday["till_16"] = $('#goal_id_SATURDAY_TILL_16').text();
							saturday["till_17"] = $('#goal_id_SATURDAY_TILL_17').text();
							saturday["after_17"] = $('#goal_id_SATURDAY_AFTER_17').text();
							
							sunday["till_9"] = $('#goal_id_SUNDAY_TILL_9').text();
							sunday["till_10"] = $('#goal_id_SUNDAY_TILL_10').text();
							sunday["till_11"] = $('#goal_id_SUNDAY_TILL_11').text();
							sunday["till_12"] = $('#goal_id_SUNDAY_TILL_12').text();
							sunday["till_13"] = $('#goal_id_SUNDAY_TILL_13').text();
							sunday["till_14"] = $('#goal_id_SUNDAY_TILL_14').text();
							sunday["till_15"] = $('#goal_id_SUNDAY_TILL_15').text();	
							sunday["till_16"] = $('#goal_id_SUNDAY_TILL_16').text();
							sunday["till_17"] = $('#goal_id_SUNDAY_TILL_17').text();
							sunday["after_17"] = $('#goal_id_SUNDAY_AFTER_17').text();

							week["monday"] = monday;
							week["tuesday"] = tuesday;
							week["wednesday"] = wednesday;
							week["thursday"] = thursday;
							week["friday"] = friday;
							week["saturday"] = saturday;
							week["sunday"] = sunday;
							
							$.ajax({
								url: "${pageContext.request.contextPath}/plan/week",
								headers : {
										"Accept" : "application/json",
										"Content-Type" : "application/json"
				        					},
								data : JSON.stringify({
								dto : week
									}),
								type : "POST",
								dataType : 'json',

													});
						event.preventDefault();
										});
					});
</script>

<script type="text/javascript">
$(document).ready(function()
		{
	
	var date = new Date();
	var onejan = new Date(date.getFullYear(), 0, 1);
	var weekNumber = Math.ceil((((date - onejan) / 86400000) + onejan.getDay() + 1) / 7);

	var d1 = new Date(date);
	var index = d1.getDay();

	if (index == 0) {
		date.setDate(date.getDate() - 6);
		d1.setDate(d1.getDate() - 2);

	} else if (index == 1) {
		date.setDate(date.getDate());
		d1.setDate(d1.getDate() + 4);

	} else if (index != 1 && index > 0) {
		date.setDate(date.getDate() - (index - 1));
		d1.setDate(d1.getDate() + (index + 3));
	}

	var startDate = date.getDate() + "." + (date.getMonth() + 1) + "." + date.getYear();
	var endDate = d1.getDate() + "." + (d1.getMonth() + 1) + "." + d1.getYear();

	var goalTitle = "Goals of " + weekNumber + ". week (" + startDate + " - " + endDate + ")";

	$('#goalTitle')[0].innerHTML = goalTitle;
	
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
			<div>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-list"></span>Daily goals
						<div class="pull-right action-buttons">

							<div class="btn-group pull-right">
								<button type="button"
									class="btn btn-default btn-xs dropdown-toggle"
									data-toggle="dropdown">
									<span class="glyphicon glyphicon-cog"
										style="margin-right: 0px;"></span>
								</button>
								<ul class="dropdown-menu slidedown">
									<li><a href="http://www.jquery2dotnet.com"><span
											class="glyphicon glyphicon-pencil"></span>Edit</a></li>
									<li><a href="http://www.jquery2dotnet.com"><span
											class="glyphicon glyphicon-trash"></span>Delete</a></li>
									<li><a href="http://www.jquery2dotnet.com"><span
											class="glyphicon glyphicon-flag"></span>Flag</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div id="dragdiv" class="panel-body">
						<ul class="list-group">
							<c:forEach items="${dailyGoalsOfTheWeek}" var="goal">
								<li class="list-group-item">
								<label id="goal_id" hidden>${goal.id}</label>
								<a href="<c:url value='/goal/${goal.id}'/>">${goal.title}</a>
									</li>

							</c:forEach>
						</ul>
					</div>
					<div class="panel-footer">
						<div class="row">

							<div class="col-md-6">
								<ul class="pagination pagination-sm pull-right">
									<li class="disabled"><a href="javascript:void(0)">�</a></li>
									<li class="active"><a href="javascript:void(0)">1 <span
											class="sr-only">(current)</span></a></li>
									<li><a href="http://www.jquery2dotnet.com">2</a></li>
									<li><a href="http://www.jquery2dotnet.com">3</a></li>
									<li><a href="http://www.jquery2dotnet.com">4</a></li>
									<li><a href="http://www.jquery2dotnet.com">5</a></li>
									<li><a href="javascript:void(0)">�</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="panel panel-default">
		  <div class="panel-heading" id="goalTitle">
  			</div>
  		<div class="panel-body">
		
		<form id="weekTable" method="post">
			<div id="dropdiv" class="row">
				<div class="col-md-12">
					<table border="1" cellspacing="1" class="table table-striped table-bordered  table-hovered">
						<thead>
							<tr id="days">
								<th draggable="false"></th>
								<th draggable="false"><span>8:00-9:00</span></th>
								<th draggable="false"><span>9:00-10:00</span></th>
								<th draggable="false"><span>10:00-11:00</span></th>
								<th draggable="false"><span>11:00-12:00</span></th>
								<th draggable="false"><span>12:00-13:00</span></th>
								<th draggable="false"><span>13:00-14:00</span></th>
								<th draggable="false"><span>14:00-15:00</span></th>
								<th draggable="false"><span>15:00-16:00</span></th>
								<th draggable="false"><span>16:00-17:00</span></th>
								<th draggable="false"><span>After 17:00</span></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${weekPlan.weekPlan}" var="plan">
								<tr id="${plan.day}">
									<th scope="row" draggable="false"><span>${plan.day}</span></th>
									<c:forEach items="${plan.goals}" var="dailyGoal">
										<c:set var="goal_id" value="goal_id_${plan.day}_${dailyGoal.key}"/>
										<c:set var="goal_name" value="goal_name_${plan.day}_${dailyGoal.key}"/>
										<c:set var="td_id" value="${plan.day}_${dailyGoal.key}"/>
										
										<td id="${td_id}">
											<label id="${goal_id}" hidden>${dailyGoal.value.id}</label>
											<label id="${goal_name}">${dailyGoal.value.name}</label>
										</td>
									</c:forEach>
								</tr>

							</c:forEach>
						</tbody>
					</table>
					<button type="submit" class="btn btn-primary btn-lg btn-block">Save that daily plan</button>
				</div>
			</div>
		</form>
		</div>
</div>
	</div>

	<div id="message_area"></div>

	<br />

</html>