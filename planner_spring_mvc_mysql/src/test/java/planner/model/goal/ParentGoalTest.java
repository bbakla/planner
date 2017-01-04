package planner.model.goal;

import java.time.Month;
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
import planner.model.goal.scope.GoalScopeNames;
import planner.test.config.HibernateTestConfiguration;

@ContextConfiguration(classes = { HibernateTestConfiguration.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class ParentGoalTest {

	@Autowired
	@Qualifier("ParentGoalDao")
	private GenericDao<Goal> dao;
	
	@Test
	public void goalShouldBeSavedInDatabase(){
		GoalDescription description = new GoalDescription( "Wir müssen unserem Leben beherrschen");
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
		
		Goal monthlyGoal = new DailyGoal(yearlyGoal, descriptionForChild, Calendar.APRIL, GoalScopeNames.MONTHLY, "childGoal1");
		Goal monthlyGoal2 = new DailyGoal(yearlyGoal, descriptionForChild2,  Calendar.MAY, GoalScopeNames.MONTHLY, "childGoal2");
	
		
		yearlyGoal.addChildGoal(monthlyGoal);
		yearlyGoal.addChildGoal(monthlyGoal2);
		dao.save(yearlyGoal);
	}
	
	@Test
	public void goalShouldBeSavedInDatabaseWithChildGoals2(){
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
		
		
		ParentGoal yearlyGoal = new ParentGoal(description, 2016, GoalScopeNames.YEARLY, "yearly_parentGoal1");
		
		ParentGoal monthlyGoal = new ParentGoal(descriptionForChild, Calendar.APRIL, GoalScopeNames.MONTHLY, "monthly_parentGoal1");
		monthlyGoal.setParentGoal(yearlyGoal);
		
		Goal dailyGoal = new DailyGoal(monthlyGoal, descriptionForChild2,  Calendar.MONDAY, GoalScopeNames.MONTHLY, "dailychildGoal1");
		
		
		yearlyGoal.addChildGoal(monthlyGoal);
//		yearlyGoal.addChildGoal(weeklyGoal);
		dao.save(yearlyGoal);
	}
	
	
}
