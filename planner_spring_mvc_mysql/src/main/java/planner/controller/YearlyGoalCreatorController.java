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

import planner.model.goal.GoalScopeNames;
import planner.model.goal.GoalStatus;
import planner.model.goal.ParentGoal;
import planner.service.ParentGoalService;

@Controller
public class YearlyGoalCreatorController {

	@Autowired
	private ParentGoalService service;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = {"/new/year"}, method = RequestMethod.GET)
	public String createParentGoal(Model model){
		String currentYear = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		List<ParentGoal> yearlyGoals = service.findYearlyGoals(currentYear);
		
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
			
			int year = Calendar.getInstance().get(Calendar.YEAR);
			
			goal.setTimeUnit(Integer.toString(year));
			
			service.saveGoal(goal);
			viewName = "redirect:/new/year";
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
