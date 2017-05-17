package planner.model.goal;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
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
public class MonthlyGoalTest {

	@Autowired
	@Qualifier("ParentGoalDao")
	private GenericDao<ParentGoal> dao;
	
	
	@Test
	public void goalShouldBeSavedInDatabaseWithChildGoals(){
		GoalDescription description = new GoalDescription( "Wir müssen unserem Leben beherrschen");
		List<String> comments = new ArrayList<>();
		comments.add("erobern");
		comments.add("erben");
		comments.add("wir müssen die Regeln einhalten");
		description.setComments(comments);
		
		GoalDescription descriptionForChild = new GoalDescription( "Er will die Regime verklagen.");
		List<String> commentsForChilds = new ArrayList<>();
		commentsForChilds.add("erhalten");
		commentsForChilds.add("einhalten");
		commentsForChilds.add("in Erinnerung behalten");
		descriptionForChild.setComments(commentsForChilds);
		
		GoalDescription descriptionForChild2 = new GoalDescription( "Er will die Regime verklagen_rrrr.");
		List<String> commentsForChilds2 = new ArrayList<>();
		commentsForChilds2.add("erhalten_rrr");
		commentsForChilds2.add("einhalten_rrr");
		commentsForChilds2.add("in Erinnerung behalten_rrr");
		descriptionForChild2.setComments(commentsForChilds);
		
		
		ParentGoal yearlyGoal = new ParentGoal(description, 2016, GoalScopeNames.YEARLY, "parentGoal1");
	
		
		ParentGoal monthlyGoal = new ParentGoal(yearlyGoal, descriptionForChild, Calendar.APRIL, GoalScopeNames.MONTHLY, "childGoal1");
		ParentGoal monthlyGoal2 = new ParentGoal(yearlyGoal, descriptionForChild2, Calendar.MAY, GoalScopeNames.MONTHLY, "childGoal2");
		
		assertEquals(2, yearlyGoal.getChildGoals().size());
	
		
//		dao.save(monthlyGoal);
//		dao.save(monthlyGoal2);
		
		dao.save(yearlyGoal);
	}
	
	
}
