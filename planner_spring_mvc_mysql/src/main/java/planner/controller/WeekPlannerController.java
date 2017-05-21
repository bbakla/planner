package planner.controller;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import planner.model.goal.Goal;
import planner.service.ParentGoalService;

@Controller
public class WeekPlannerController {
	
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
		model.addAttribute("weekPlanner", "sefsdf");
		return "weekplanner";
	}
	
	@RequestMapping(value="/plan/week", method= RequestMethod.POST, headers= "application/json",
			produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
//	public String saveWeekPlan(@RequestParam(value="array[]" ) String[] array){
	public String saveWeekPlan(@RequestBody String[] plans){
		
		System.out.println(plans);
		
		
		
		String viewName = "redirect:/planner/plan/week";
		
		return viewName;
	}

}
