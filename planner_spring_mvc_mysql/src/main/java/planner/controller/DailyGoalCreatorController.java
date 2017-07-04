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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import planner.model.goal.Goal;
import planner.model.goal.GoalScopeNames;
import planner.model.goal.GoalStatus;
import planner.model.goal.ParentGoal;
import planner.service.ParentGoalService;
import planner.service.TimeService;

@Controller
public class DailyGoalCreatorController {

	@Autowired
	private ParentGoalService service;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = {"/new/day"}, method = RequestMethod.GET)
	public String createDailyGoal(Model model){
		model.addAttribute("parent", new ParentGoal());
		model.addAttribute("edit", "new");

		LocalDate currentDate = LocalDate.now();

		int year = currentDate.getYear();
		TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
		int weekNumber = currentDate.get(woy);
//		String[] days = timeService.getDaysOfWeek();

		List<Goal> goalsOfCurrentWeek = service.findWeeklyGoals(Integer.toString(year), Integer.toString(weekNumber));
		List<Goal> dailyGoalsOfCurrentWeek = service.findDailyGoalsOfTheWeek(Integer.toString(year), Integer.toString(weekNumber));
		
		model.addAttribute("weeklyGoals", goalsOfCurrentWeek);
		model.addAttribute("dailyGoalsOfTheWeek", dailyGoalsOfCurrentWeek);
//		model.addAttribute("days", days);

		return "newdaily";
	}
	
	@RequestMapping(value={"/new/day"}, method = RequestMethod.POST)
	public String saveDailyGoal(@ModelAttribute ParentGoal goal, RedirectAttributes redirectAttributes, SessionStatus sessionStatus){
		String message = "";
		String viewName ="";
		
		try{
			goal.setStatus(GoalStatus.NOT_STARTED);
			goal.setScope(GoalScopeNames.DAILY);
			
			ParentGoal parentGoal = service.findById(goal.getParentGoal().getId());
			goal.setParentGoal(parentGoal);
			
			service.updateGoal(goal);
			message = messageSource.getMessage("goal.created", new String[]{goal.getId().toString()}, Locale.getDefault());
			viewName = "redirect:/new/day";
			
			sessionStatus.setComplete();
		} catch(Exception e){
			e.printStackTrace();
			message = "Goal creation is failed";
			viewName = "redirect:/creationFailed";
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return viewName;
	}
	
}
