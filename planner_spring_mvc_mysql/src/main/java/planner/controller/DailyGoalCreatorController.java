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
import planner.model.goal.GoalStatus;
import planner.model.goal.ParentGoal;
import planner.model.goal.scope.GoalScopeNames;
import planner.service.ParentGoalService;

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

		int month = currentDate.getMonth().getValue();
		int year = currentDate.getYear();
		TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
		int weekNumber = currentDate.get(woy);

		List<Goal> goalsOfCurrentWeek = service.findWeeklyGoals(year, weekNumber);
		List<Goal> dailyGoalsOfCurrentWeek = service.findDailyGoalsOfTheWeek(year, weekNumber);
		model.addAttribute("weeklyGoals", goalsOfCurrentWeek);
		model.addAttribute("dailyGoalsOfTheWeek", dailyGoalsOfCurrentWeek);

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
			viewName = "redirect:/planner/new/day";
			
			sessionStatus.setComplete();
		} catch(Exception e){
			e.printStackTrace();
			message = "Goal creation is failed";
			viewName = "redirect:/planner/creationFailed";
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return viewName;
	}
	
}
