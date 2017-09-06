Endpoints 
------------------------------------
http://localhost:8080/planner_spring_mvc_mysql/new/year
http://localhost:8080/planner_spring_mvc_mysql/plan/week
http://localhost:8080/planner_spring_mvc_mysql/new/month
http://localhost:8080/planner_spring_mvc_mysql/new/week
http://localhost:8080/planner_spring_mvc_mysql/new/day

TODOS
-----------------------------------------
* dailyplan should be vertical not horizantal
* page doesnt refresh after saving. not a problem anymore
* table should be filtered based on month, weekNumbers
* copy goal to create a new one
* optimize database
* delete task
* parent goal is wrong on edit page

drop table parent_child;
drop table goal_details;
drop table comments;
drop table parent_goal;
drop table goal;
drop table goal_description;
drop table day_plan_goals;
drop table week_day_plan;
drop table progress;
drop table week_plan;
drop table day_plan;