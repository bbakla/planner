package planner.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import planner.model.goal.Goal;
import planner.service.ParentGoalService;

@Controller
public class WeekPlanner {
	
	@Autowired
	private ParentGoalService service;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value="/plan/week", method= RequestMethod.GET)
	public String planTheWeek(Model model){
		Calendar calendar = Calendar.getInstance();
		
		List<Goal> dailyGoalsOfCurrentWeek = service.findDailyGoalsOfTheWeek(calendar.get(Calendar.YEAR), calendar.get(Calendar.WEEK_OF_YEAR));
		model.addAttribute("dailyGoalsOfTheWeek", dailyGoalsOfCurrentWeek);
		return "weekplanner";
	}

}
