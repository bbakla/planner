package planner.controller;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
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
public class WeeklyGoalCreatorController {

	@Autowired
	private ParentGoalService service;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = {"/new/week"}, method = RequestMethod.GET)
	public String createMonthlyGoal(Model model){
		
		LocalDate currentDate = LocalDate.now(); 
		
		int month = currentDate.getMonth().getValue();
		int year = currentDate.getYear();
		TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear(); 
		int weekNumber = currentDate.get(woy);
		
		List<Goal> goalsOfCurrentMonth = service.findMonthlyGoals(year, month);
		List<Goal> weeklyGoals = service.findWeeklyGoals(year, weekNumber);
		
//		model.addAttribute("months", Arrays.asList(Month.buildMonths()));
		model.addAttribute("parentMontlhyGoals", goalsOfCurrentMonth);
		model.addAttribute("weeklyGoals", weeklyGoals);
		model.addAttribute("parent", new ParentGoal());
		model.addAttribute("edit", "new");
		
		return "newweekly";
	}
	
	@RequestMapping(value={"/new/week"}, method = RequestMethod.POST)
	public String saveMonthlyGoal(@ModelAttribute ParentGoal goal, RedirectAttributes redirectAttributes, SessionStatus sessionStatus){
		String message = "";
		String viewName ="";
		
		try{
			goal.setStatus(GoalStatus.NOT_STARTED);
			goal.setScope(GoalScopeNames.WEEKLY);
			
			LocalDate currentDate = LocalDate.now(); 
			
			TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear(); 
			int weekNumber = currentDate.get(woy);
			
			goal.setTimeLabel(weekNumber);
			
			ParentGoal parentGoal = service.findById(goal.getParentGoal().getId());
			goal.setParentGoal(parentGoal);
			
			service.updateGoal(goal);
			message = messageSource.getMessage("goal.created", new String[]{goal.getId().toString()}, Locale.getDefault());
			viewName = "redirect:/planner/new/week";
			
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
