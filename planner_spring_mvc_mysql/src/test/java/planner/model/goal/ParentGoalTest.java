package planner.model.goal;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import planner.dao.GenericDao;
import planner.test.config.HibernateTestConfiguration;

@ContextConfiguration(classes = { HibernateTestConfiguration.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class ParentGoalTest {

	@Autowired
	@Qualifier("ParentGoalDao")
	private GenericDao<Goal> dao;
	
	@Test
	public void goalShouldBeSavedInDatabase(){
		GoalDescription description = new GoalDescription("detilsTest", "Wir müssen unserem Leben beherrschen");
		List<String> comments = new ArrayList<>();
		comments.add("erobern");
		comments.add("erben");
		comments.add("wir müssen die Regeln einhalten");
		description.setComments(comments);
		
//		Goal yearlyGoal = new ParentGoal(GoalScopeNames.YEARLY, description);
//		dao.save(yearlyGoal);
	}
	
	@Test
	public void goalShouldBeSavedInDatabaseWithChildGoals(){
		GoalDescription description = new GoalDescription("detilsTest", "Wir müssen unserem Leben beherrschen");
		List<String> comments = new ArrayList<>();
		comments.add("erobern");
		comments.add("erben");
		comments.add("wir müssen die Regeln einhalten");
		description.setComments(comments);
		
		Goal yearlyGoal = new ParentGoal(description, GoalScopeNames.YEARLY);
		
//		Goal monthlyGoal = new ChildGoal(yearlyGoal, GoalScopeNames.YEARLY, description);
		
//		yearlyGoal.addChildGoal(monthlyGoal);
		dao.save(yearlyGoal);
	}
	
	
}
