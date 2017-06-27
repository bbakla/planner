package planner.controller;

import java.time.LocalDate;
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
import planner.model.goal.GoalScopeNames;
import planner.service.ParentGoalService;
import planner.service.TimeService;

@Controller
public class MonthlyGoalCreatorController {

	@Autowired
	private ParentGoalService service;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	private TimeService timeService;
	
	@RequestMapping(value = {"/new/month"}, method = RequestMethod.GET)
	public String getMonthlyGoal(Model model){
	LocalDate currentDate = LocalDate.now(); 
		
		String currentMonth = currentDate.getMonth().toString();
		String currentYear = Integer.toString(currentDate.getYear());
		
		List<ParentGoal> yearlyGoalofTheYear = service.findYearlyGoals(currentYear);
		List<Goal> monthlyGoals = service.findMonthlyGoals(currentYear, currentMonth);
		String[] months = timeService.getMonthsOfYear();
		
		model.addAttribute("yearlyGoals", yearlyGoalofTheYear);
		model.addAttribute("monthlyGoals", monthlyGoals);
		model.addAttribute("parent", new ParentGoal());
		model.addAttribute("edit", "new");
		model.addAttribute("months", months);
		
		return "newmonthly";
	}
	
	@RequestMapping(value={"/new/month"}, method = RequestMethod.POST)
	public String saveMonthlyGoal(@ModelAttribute ParentGoal goal, RedirectAttributes redirectAttributes, SessionStatus sessionStatus){
		String message = "";
		String viewName ="";
		
		ParentGoal parentGoal = service.findById(goal.getParentGoal().getId());
		goal.setParentGoal(parentGoal);

		try{
			goal.setStatus(GoalStatus.NOT_STARTED);
			goal.setScope(GoalScopeNames.MONTHLY);
			
			service.updateGoal(goal);
			message = messageSource.getMessage("goal.created", new String[]{goal.getId().toString()}, Locale.getDefault());
			viewName = "redirect:/planner/new/month";
			
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
