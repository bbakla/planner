package planner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import planner.model.goal.ParentGoal;
import planner.service.ParentGoalService;

@Controller
public class GoalDetailDisplayController {

	@Autowired
	private ParentGoalService service;

	@RequestMapping(value = "/goal/{id}", method = RequestMethod.GET)
	public String displayGoalPage(@PathVariable Long id, ModelMap map) {
		ParentGoal goal = service.findById(id);
		map.addAttribute("goal", goal);

		return "goaldetails";
	}

	@RequestMapping(value = "/goal/{id}", method = RequestMethod.POST)
	public String update(@PathVariable Long id, @ModelAttribute ParentGoal updatedGoal, ModelMap map) {
		ParentGoal goal = service.findById(id);
		map.addAttribute("goal", goal);

		return "goaldetails";
	}

	@RequestMapping(value = "/goal/delete/{id}", method = RequestMethod.POST)
	public String deleteParentGoal(@PathVariable Long id) {

		ParentGoal goal = service.findById(id);

		service.deleteGoal(goal);

		return "redirect:/new/year";
	}

}
