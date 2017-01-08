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

import planner.model.goal.GoalStatus;
import planner.model.goal.ParentGoal;
import planner.model.goal.scope.GoalScopeNames;
import planner.service.ParentGoalService;

@Controller
public class YearlyGoalCreatorController {

	@Autowired
	private ParentGoalService service;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = {"/new/year"}, method = RequestMethod.GET)
	public String createParentGoal(Model model){
		List<ParentGoal> yearlyGoals = service.findYearlyGoals(Calendar.getInstance().get(Calendar.YEAR));
		
		model.addAttribute("parent", new ParentGoal());
		model.addAttribute("edit", "new");
		model.addAttribute("yearlyGoals", yearlyGoals);
		
		return "newyeargoal";
	}
	
	@RequestMapping(value="/creationFailed", method=RequestMethod.GET)
	public String createFailed(){
		return "newyeargoal";
	}
	
	@RequestMapping(value="/new/year", method = RequestMethod.POST)
	public String saveParentGoal(@ModelAttribute ParentGoal goal, RedirectAttributes redirectAttributes, SessionStatus sessionStatus){
		String message = "";
		String viewName ="";
		
		try{
			goal.setStatus(GoalStatus.NOT_STARTED);
			goal.setScope(GoalScopeNames.YEARLY);
			goal.setTimeLabel(Calendar.getInstance().get(Calendar.YEAR));
			
			service.saveGoal(goal);
			System.out.println(goal.getParentGoal());
			ParentGoal g = service.findById(goal.getId());
			System.out.println(g.getParentGoal());
			message = messageSource.getMessage("goal.created", new String[]{goal.getId().toString()}, Locale.getDefault());
			viewName = "redirect:/planner/new/year";
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
