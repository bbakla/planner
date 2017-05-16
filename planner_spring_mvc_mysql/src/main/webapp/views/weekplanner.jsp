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

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"
	type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.8.20/jquery-ui.min.js"
	type="text/javascript"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/table.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/table.css">
	
<script type="text/javascript">
	$document.ready(function(){
		
		$('#weekTable').submit(function(event){
			
			var monday_9 = $('#monday_9').val();
			var tuesday_9 = $('#tuesday_9').val();
			var json = {"monday_9" : monday_9, "tuesday_9" : tuesday_9};
			
			$.ajax({
				url: $("weekTable").attr("action"),
				data: JSON.stringify(json); 
				type: "POST",
				
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
	        		xhr.setRequestHeader("Content-Type", "application/json");
	        		$(".error").remove();
				},
				
				success: function(plan) {
	        		var respContent = "";
	        		
			  		respContent += "<span class='success'>Smartphone was created: [";
			  		respContent += plan;
			  		respContent +=  "]</span>";
	        		
	        		$("#message_area").html(respContent);   		
	        	},
			});
		    event.preventDefault();
		});
	});
</script>


</head>
<body>

	<div class="container">
		<div class="row">
			<div class="">
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
								<li class="list-group-item"><a
									href="<c:url value='/planner/goal/${goal.id}'/>">${goal.title}</a>
									<label id="goal_id" hidden>${goal.id}</label></li>

							</c:forEach>
						</ul>
					</div>
					<div class="panel-footer">
						<div class="row">

							<div class="col-md-6">
								<ul class="pagination pagination-sm pull-right">
									<li class="disabled"><a href="javascript:void(0)">«</a></li>
									<li class="active"><a href="javascript:void(0)">1 <span
											class="sr-only">(current)</span></a></li>
									<li><a href="http://www.jquery2dotnet.com">2</a></li>
									<li><a href="http://www.jquery2dotnet.com">3</a></li>
									<li><a href="http://www.jquery2dotnet.com">4</a></li>
									<li><a href="http://www.jquery2dotnet.com">5</a></li>
									<li><a href="javascript:void(0)">»</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
	<form:form id="weekTable" action="${pageContext.request.contextPath}/planner/weekplan" commandName="weekPlanner">
		<div id="dropdiv" class="row">
			<div class="col-md-12">
				<table border="1" cellspacing="1"
					class="table table-striped table-bordered  table-hovered">
					<thead>
						<tr id="days">
							<td draggable="false"></td>
							<td draggable="false"><span>Monday</span></td>
							<td draggable="false"><span>Tuesday</span></td>
							<td draggable="false"><span>Wednesday</span></td>
							<td draggable="false"><span>Thursday</span></td>
							<td draggable="false"><span>Friday</span></td>
							<td draggable="false"><span>Saturday</span></td>
							<td draggable="false"><span>Sunday</span></td>
						</tr>
					</thead>
					<tbody>
						<tr id="till9">
							<td id="time_till_9">08:00-09:00</td>
							<td id="monday_9"><label id="goal_id_monday_9" hidden></label>
							</td>
							<td id="tuesday_9"><label id="goal_id_tuesday_9" hidden></label>
							</td>

							<td id="wednesday_9"><label id="goal_name_wednesday_9"
								hidden></label> <label id="goal_id_wednesday_9" hidden></label>
							</td>

							<td id="thursday_9"><label id="goal_id_thursday_9" hidden></label>
							</td>
							<td id="friday_9"><label id="goal_id_friday_9" hidden></label>
							</td>
							<td id="saturday_9"><label id="goal_name_saturday_9"></label>
								<label id="goal_id_saturday_9" hidden></label></td>
							<td id="sunday_9"><label id="goal_id_sunday_9" hidden></label>
							</td>
						</tr>

						<tr id="till10">
							<td id="time_till10">09:00-10:00</td>
							<td id="monday_10"><label id="goal_id_monday_10" hidden></label>
							</td>
							<td id="tuesday_10"><label id="goal_id_tuesday_10" hidden></label>
							</td>
							<td id="wednesday_10"><label id="goal_id_wednesday_10"
								hidden></label></td>
							<td id="thursday_10"><label id="goal_id_thursday_10" hidden></label>
							</td>
							<td id="friday_10"><label id="goal_id_friday_10" hidden></label>
							</td>
							<td id="saturday_10"><label id="goal_id_saturday_10" hidden></label>
							</td>
							<td id="sunday_10"><label id="goal_id_sunday_10" hidden></label>
							</td>
						</tr>

						<tr id="till11">
							<td id="time_till_11">10:00-11:00</td>
							<td id="monday_11"><label id="goal_id_monday_11" hidden></label>
							</td>
							<td id="tuesday_11"><label id="goal_id_tuesday_11" hidden></label>
							</td>
							<td id="wednesday_11"><label id="goal_id_wednesday_11"
								hidden></label></td>
							<td id="thursday_11"><label id="goal_id_thursday_11" hidden></label>
							</td>
							<td id="friday_11"><label id="goal_id_friday_11" hidden></label>
							</td>
							<td id="saturday_11"><label id="goal_id_saturday_11" hidden></label>
							</td>
							<td id="sunday_11"><label id="goal_id_sunday_11" hidden></label>
							</td>
						</tr>

						<tr id="till12">
							<td id="time_till12">11:00-12:00</td>
							<td id="monday_12"><label id="goal_id_monday_12" hidden></label>
							</td>
							<td id="tuesday_12"><label id="goal_id_tuesday_12" hidden></label>
							</td>
							<td id="wednesday_12"><label id="goal_id_wednesday_12"
								hidden></label></td>
							<td id="thursday_12"><label id="goal_id_thursday_12" hidden></label>
							</td>
							<td id="friday_12"><label id="goal_id_friday_12" hidden></label>
							</td>
							<td id="saturday_12"><label id="goal_id_saturday_12" hidden></label>
							</td>
							<td id="sunday_12"><label id="goal_id_sunday_12" hidden></label>
							</td>
						</tr>

						<tr id="till13">
							<td id="time_till13">12:00-13:00</td>
							<td id="monday_13"><label id="goal_id_monday_13" hidden></label>
							</td>
							<td id="tuesday_13"><label id="goal_id_tuesday_13" hidden></label>
							</td>
							<td id="wednesday_13"><label id="goal_id_wednesday_13"
								hidden></label></td>
							<td id="thursday_13"><label id="goal_id_thursday_13" hidden></label>
							</td>
							<td id="friday_13"><label id="goal_id_friday_13" hidden></label>
							</td>
							<td id="saturday_13"><label id="goal_id_saturday_13" hidden></label>
							</td>
							<td id="sunday_13"><label id="goal_id_sunday_13" hidden></label>
							</td>
						</tr>

						<tr id="till14">
							<td id="time_till14">13:00-14:00</td>
							<td id="monday_14"><label id="goal_id_monday_14" hidden></label>
							</td>
							<td id="tuesday_14"><label id="goal_id_tuesday_14" hidden></label>
							</td>
							<td id="wednesday_14"><label id="goal_id_wednesday_14"
								hidden></label></td>
							<td id="thursday_14"><label id="goal_id_thursday_14" hidden></label>
							</td>
							<td id="friday_14"><label id="goal_id_friday_14" hidden></label>
							</td>
							<td id="saturday_14"><label id="goal_id_saturday_14" hidden></label>
							</td>
							<td id="sunday_14"><label id="goal_id_sunday_14" hidden></label>
							</td>
						</tr>

						<tr id="till15">
							<td id="time_15">14:00-15:00</td>
							<td id="monday_15"><label id="goal_id_monday_15" hidden></label>
							</td>
							<td id="tuesday_15"><label id="goal_id_tuesday_15" hidden></label>
							</td>
							<td id="wednesday_15"><label id="goal_id_wednesday_15"
								hidden></label></td>
							<td id="thursday_15"><label id="goal_id_thursday_15" hidden></label>
							</td>
							<td id="friday_15"><label id="goal_id_friday_15" hidden></label>
							</td>
							<td id="saturday_15"><label id="goal_id_saturday_15" hidden></label>
							</td>
							<td id="sunday_15"><label id="goal_id_sunday_15" hidden></label>
							</td>
						</tr>

						<tr id="till16">
							<td id="time_16">15:00-16:00</td>
							<td id="monday_16"><label id="goal_id_monday_16" hidden></label>
							</td>
							<td id="tuesday_16"><label id="goal_id_tuesday_16" hidden></label>
							</td>
							<td id="wednesday_16"><label id="goal_id_wednesday_16"
								hidden></label></td>
							<td id="thursday_16"><label id="goal_id_thursday_16" hidden></label>
							</td>
							<td id="friday_16"><label id="goal_id_friday_16" hidden></label>
							</td>
							<td id="saturday_16"><label id="goal_id_saturday_16" hidden></label>
							</td>
							<td id="sunday_16"><label id="goal_id_sunday_16" hidden></label>
							</td>
						</tr>

						<tr id="till17">
							<td id="time_17">16:00-17:00</td>
							<td id="monday_17"><label id="goal_id_monday_17" hidden></label>
							</td>
							<td id="tuesday_17"><label id="goal_id_tuesday_17" hidden></label>
							</td>
							<td id="wednesday_17"><label id="goal_id_wednesday_17"
								hidden></label></td>
							<td id="thursday_17"><label id="goal_id_thursday_17" hidden></label>
							</td>
							<td id="friday_17"><label id="goal_id_friday_17" hidden></label>
							</td>
							<td id="saturday_17"><label id="goal_id_saturday_17" hidden></label>
							</td>
							<td id="sunday_17"><label id="goal_id_sunday_17" hidden></label>
							</td>
						</tr>

						<tr id="after">
							<td id="time_after"></td>
							<td id="monday_after"><label id="goal_id_monday_after_17"
								hidden></label></td>
							<td id="tuesday_after"><label id="goal_id_tuesday_after_17"
								hidden></label></td>
							<td id="wednesday_after"><label
								id="goal_id_wednesday_after_17" hidden></label></td>
							<td id="thursday_after"><label
								id="goal_id_thursday_after_17" hidden></label></td>
							<td id="friday_after"><label id="goal_id_friday_after_17"
								hidden></label></td>
							<td id="saturday_after"><label
								id="goal_id_saturday_after_17" hidden></label></td>
							<td id="sunday_after"><label id="goal_id_sunday_after_17"
								hidden></label></td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
 		<form:button name="Create your daily plan" class="btn btn-primary btn-lg btn-block">Save that daily plan</form:button> 
		</form:form>
	</div>
	
	<div id ="message_area">
		
	</div>

	<br />
	<font color="red">${message}</font>



</body>
</html>