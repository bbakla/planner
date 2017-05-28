package planner.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import planner.dao.GenericDao;
import planner.dao.GenericPlanDao;
import planner.dao.ParentGoalDao;
import planner.dto.goal.DayPlanDto;
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
	
	@RequestMapping(value="/plan/week", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String planTheWeek(Model model){
		LocalDate currentDate = LocalDate.now();

		int year = currentDate.getYear();
		TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
		int weekNumber = currentDate.get(woy);


		List<Goal> dailyGoalsOfCurrentWeek = service.findDailyGoalsOfTheWeek(year, weekNumber);
		WeekPlan weekPlan = weekPlanService.getWeekPlan(year, weekNumber);
		
		model.addAttribute("dailyGoalsOfTheWeek", dailyGoalsOfCurrentWeek);
		model.addAttribute("weekPlan", weekPlan);
		return "weekplanner";
	}
	
	@RequestMapping(value="/plan/week", method= RequestMethod.POST)
	public String saveWeekPlan(Model model, HttpServletRequest request) throws IOException{
		
		String body = CharStreams.toString(request.getReader());
		System.out.println("body is " + body);
		
		LocalDate currentDate = LocalDate.now();

		int year = currentDate.getYear();
		TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
		int weekNumber = currentDate.get(woy);
		
		if(body != null && body.length() != 0){
			WeekPlan plan = weekPlanConverter.convertjsonToWeekPlan(body, year, weekNumber);
			weekPlanService.saveWeekPlan(plan);
			model.addAttribute("weekPlan", plan);
		}
		
		String viewName = "redirect:/planner/plan/week";
		
		return viewName;
	}
	
	public static Object fromJson(String jsonString, Type type) {
	    return new Gson().fromJson(jsonString, type);
	}

}
