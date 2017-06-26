package planner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import planner.model.goal.ParentGoal;
import planner.service.ParentGoalService;

@Controller
public class ParentListController {

	@Autowired
	private ParentGoalService service;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = {"/goals/yearly/{year}"}, method = RequestMethod.GET)
	public String listYearlyGoals(ModelMap modelMap, @PathVariable int year){
		List<ParentGoal> yearlyGoals = service.findYearlyGoals(Integer.toString(year)); 
		
		modelMap.addAttribute("yearlyGoals", yearlyGoals);
		
		return "goals"; 
	}
}
