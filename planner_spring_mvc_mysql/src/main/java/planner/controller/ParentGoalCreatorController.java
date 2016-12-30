package planner.controller;

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

import planner.model.goal.ParentGoal;
import planner.service.ParentGoalService;

@Controller
public class ParentGoalCreatorController {

	@Autowired
	private ParentGoalService service;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = {"/new"}, method = RequestMethod.GET)
	public String createParentGoal(Model model){
		model.addAttribute("parent", new ParentGoal());
		model.addAttribute("edit", "new");
		
		return "createParent";
	}
	
	@RequestMapping(value="/creationFailed", method=RequestMethod.GET)
	public String createFailed(){
		return "createParent";
	}
	
	@RequestMapping(value="/new", method = RequestMethod.POST)
	public String saveParentGoal(@ModelAttribute ParentGoal goal, RedirectAttributes redirectAttributes, SessionStatus sessionStatus){
		String message = "";
		String viewName ="";
		
		try{
			service.saveGoal(goal);
			message = messageSource.getMessage("goal.created", new String[]{goal.getId().toString()}, Locale.getDefault());
			viewName = "redirect:/planner/years/goals";
			sessionStatus.setComplete();
		} catch(Exception e){
			message = "Goal creation is failed";
			viewName = "redirect:/planner/creationFailed";
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return viewName;
	}
}
