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

 <title>Example- drag and drop li to div</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
    <script src="http://code.jquery.com/ui/1.8.20/jquery-ui.min.js" type="text/javascript"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/table.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
    
</head>
<body>

	<div class= "container">
	<div id="dragdiv">
	<div class = "col-md-10">
	 <h3><span>Drag from the ul list</span></h3>
		<c:forEach items = "${dailyGoalsOfTheWeek}" var = "goal">
			<a href="<c:url value='/planner/goal/${goal.id}'/>">${goal.title}</a><br/>
<%-- 			<c:forEach items = "${goal.childGoals}" var = "child"> --%>
<%-- 				<a href="<c:url value='/planner/goal/${child.id}'/>">${child.title}</a><br/> --%>
<%-- 			</c:forEach> --%>
		
		</c:forEach>
	</div>
	</div>
	</div>
	
	<div class= "container">
	 <div id="dropdiv" >
	 <div class = "col-md-10">
        <table  border="1" cellspacing="1" cellpadding="1">
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
                <td id="time">08:00-09:00</td>
                <td id="monday_9"></td>
                <td id="tuesday_9"></td>
                <td id="wednesday_9"></td>
                <td id="thursday_9"></td>
                <td id="friday_9"></td>
                <td id="saturday_9"></td>
                <td id="sunday_9"></td>
            </tr>
            
            <tr id="till10">
            	<td id="time">09:00-10:00</td>
                <td id="monday_10"></td>
                <td id="tuesday_10"></td>
                <td id="wednesday_10"></td>
                <td id="thursday_10"></td>
                <td id="friday_10"></td>
                <td id="saturday_10"></td>
                <td id="sunday_10"></td>
            </tr>
           
             <tr id="till11">
                <td id="time">10:00-11:00</td>
                <td id="monday_11"></td>
                <td id="tuesday_11"></td>
                <td id="wednesday_11"></td>
                <td id="thursday_11"></td>
                <td id="friday_11"></td>
                <td id="saturday_11"></td>
                <td id="sunday_11"></td>
            </tr>
            
              <tr id="till12">
                <td id="time">11:00-12:00</td>
                <td id="monday_12"></td>
                <td id="tuesday_12"></td>
                <td id="wednesday_12"></td>
                <td id="thursday_12"></td>
                <td id="friday_12"></td>
                <td id="saturday_12"></td>
                <td id="sunday_12"></td>
            </tr>
            
              <tr id="till13">
                <td id="time">12:00-13:00</td>
                <td id="monday_13"></td>
                <td id="tuesday_13"></td>
                <td id="wednesday_13"></td>
                <td id="thursday_13"></td>
                <td id="friday_13"></td>
                <td id="saturday_13"></td>
                <td id="sunday_13"></td>
            </tr>
            
              <tr id="till14">
                <td id="time">13:00-14:00</td>
                <td id="monday_14"></td>
                <td id="tuesday_14"></td>
                <td id="wednesday_14"></td>
                <td id="thursday_14"></td>
                <td id="friday_14"></td>
                <td id="saturday_14"></td>
                <td id="sunday_14"></td>
            </tr>
            
              <tr id="till15">
              	<td id="time">14:00-15:00</td>
                <td id="monday_15"></td>
                <td id="tuesday_15"></td>
                <td id="wednesday_15"></td>
                <td id="thursday_15"></td>
                <td id="friday_15"></td>
                <td id="saturday_15"></td>
                <td id="sunday_15"></td>
            </tr>
            
              <tr id="till16">
              	<td id="time">15:00-16:00</td>
                <td id="monday_16"></td>
                <td id="tuesday_16"></td>
                <td id="wednesday_16"></td>
                <td id="thursday_16"></td>
                <td id="friday_16"></td>
                <td id="saturday_16"></td>
                <td id="sunday_16"></td>
            </tr>
            
              <tr id="till17">
                <td id="time">16:00-17:00</td>
                <td id="monday_17"></td>
                <td id="tuesday_17"></td>
                <td id="wednesday_17"></td>
                <td id="thursday_17"></td>
                <td id="friday_17"></td>
                <td id="saturday_17"></td>
                <td id="sunday_17"></td>
            </tr>
            
              <tr id="after">
              	<td id="time"></td>
                <td id="monday_after"></td>
                <td id="tuesday_after"></td>
                <td id="wednesday_after"></td>
                <td id="thursday_after"></td>
                <td id="friday_after"></td>
                <td id="saturday_after"></td>
                <td id="sunday_after"></td>
            </tr>
            
            </tbody>
        </table>
    </div>
    </div>
    </div>

	<br/>
	<font color="red">${message}</font>



</body>
</html>