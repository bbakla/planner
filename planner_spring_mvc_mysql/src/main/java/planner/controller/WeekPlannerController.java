package planner.controller;


import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.google.common.io.CharStreams;
import planner.model.goal.Goal;
import planner.model.timeframe.WeekPlan;
import planner.service.ParentGoalService;
import planner.service.WeekPlanConverterService;
import planner.service.WeekPlanDatabaseService;

@Controller
public class WeekPlannerController {
	
	@Autowired
	private ParentGoalService service;
	
	@Autowired
	private WeekPlanConverterService weekPlanConverter;
	
	@Autowired
	private WeekPlanDatabaseService weekPlanService;
	
	
	@Autowired
	MessageSource messageSource;
	
	private static final Logger logger = Logger.getLogger(WeekPlannerController.class);
	
	@RequestMapping(value= {"/"}, method= RequestMethod.GET)
	public String initialPage() {
		return "redirect:/plan/week";
	}
	
	@RequestMapping(value="/plan/week", method= RequestMethod.GET)
	public String planTheWeek(Model model){
		LocalDate currentDate = LocalDate.now();

		String year = Integer.toString(currentDate.getYear());
		TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
		String weekNumber = Integer.toString(currentDate.get(woy));

		List<Goal> dailyGoalsOfCurrentWeek = service.findDailyGoalsOfTheWeek(year, weekNumber);
		WeekPlan weekPlan = weekPlanService.getSortedWeekPlan(year, weekNumber);
		
		model.addAttribute("weekPlan", weekPlan);
		model.addAttribute("dailyGoalsOfTheWeek", dailyGoalsOfCurrentWeek);
		
		return "weekplanner";
	}
	
	@RequestMapping(value="/plan/week", method= RequestMethod.POST)
	public String saveWeekPlan(HttpServletRequest request, SessionStatus sessionStatus) throws IOException{
		
		String body = CharStreams.toString(request.getReader());
		logger.info("body is " + body);
		
		LocalDate currentDate = LocalDate.now();

		String year = Integer.toString(currentDate.getYear());
		TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
		String weekNumber = Integer.toString(currentDate.get(woy));
		
//		if(body != null && body.length() != 0){
			WeekPlan plan = weekPlanConverter.convertjsonToWeekPlan(body, year, weekNumber);
			weekPlanService.saveWeekPlan(plan);
			
//		}
		
		String viewName = "redirect:/plan/week";
		sessionStatus.setComplete();
		
		return viewName;
	}

}
