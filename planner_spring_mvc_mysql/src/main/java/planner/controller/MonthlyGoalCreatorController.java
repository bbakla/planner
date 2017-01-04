package planner.controller;

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
public class MonthlyGoalCreatorController {

	@Autowired
	private ParentGoalService service;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = {"/new/month"}, method = RequestMethod.GET)
	public String createMonthlyGoal(Model model){
		model.addAttribute("parent", new ParentGoal());
		model.addAttribute("edit", "new");
		
		List<ParentGoal> yearlyGoalofTheYear = service.findYearlyGoals(Calendar.getInstance().get(Calendar.YEAR));
		
		model.addAttribute("yearlyGoals", yearlyGoalofTheYear);
		
		return "newmonthgoal";
	}
	
//	@RequestMapping(value="/creationFailed", method=RequestMethod.GET)
//	public String createFailed(){
//		return "newmonthgoal";
//	}
	
	@RequestMapping(value="/new/month", method = RequestMethod.POST)
	public String saveMonthlyGoal(@ModelAttribute ParentGoal goal, RedirectAttributes redirectAttributes, SessionStatus sessionStatus){
		String message = "";
		String viewName ="";
		System.out.println(goal.getParentGoal().getTitle());
		ParentGoal parentGoal = service.findById(goal.getParentGoal().getId());
		goal.setParentGoal(parentGoal);
		try{
			goal.setStatus(GoalStatus.NOT_STARTED);
			goal.setScope(GoalScopeNames.MONTHLY);
			
			service.updateGoal(goal);
			message = messageSource.getMessage("goal.created", new String[]{goal.getId().toString()}, Locale.getDefault());
			viewName = "redirect:/planner/years/goals";
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