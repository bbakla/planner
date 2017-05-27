package planner.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.io.CharStreams;

import planner.dto.goal.DayPlanDto;
import planner.model.goal.Goal;
import planner.service.ParentGoalService;

@Controller
public class WeekPlannerController {
	
	@Autowired
	private ParentGoalService service;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value="/plan/week", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	@RequestMapping(value="/plan/week", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE
	        )
	@ResponseBody
//	public String saveWeekPlan(@RequestParam(value="monday[]", required = false) String[] monday){
//	public String saveWeekPlan(@RequestParam(value="nine") String nine){
	public String saveWeekPlan( HttpServletRequest request) throws IOException{
		
//		System.out.println(nine.getIdList().size());
		String body = CharStreams.toString(request.getReader());
		System.out.println(body);
		
		
		
		String viewName = "redirect:/planner/plan/week";
		
		return viewName;
	}

}
