package planner.controller;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
		LocalDate currentDate = LocalDate.now();

		int year = currentDate.getYear();
		TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
		int weekNumber = currentDate.get(woy);


		List<Goal> dailyGoalsOfCurrentWeek = service.findDailyGoalsOfTheWeek(year, weekNumber);
		model.addAttribute("dailyGoalsOfTheWeek", dailyGoalsOfCurrentWeek);
		return "weekplanner";
	}

}
