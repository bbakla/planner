package planner.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import planner.dao.GenericDao;
import planner.model.goal.DailyGoal;
import planner.model.goal.Goal;
import planner.model.goal.GoalDescription;
import planner.model.goal.ParentGoal;
import planner.model.goal.scope.GoalScopeNames;
import planner.test.config.HibernateTestConfiguration;

@ContextConfiguration(classes = { HibernateTestConfiguration.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class ParentGoalDaoTest {

	@Autowired
	@Qualifier("ParentGoalDao")
	private GenericDao<Goal> dao;
	
	public void set(){
		GoalDescription description = new GoalDescription( "Wir müssen unserem Leben beherrschen");
		List<String> comments = new ArrayList<>();
		comments.add("erobern");
		comments.add("erben");
		comments.add("wir müssen die Regeln einhalten");
		description.setComments(comments);
		
		GoalDescription descriptionForMonth = new GoalDescription( "Er will die Regime verklagen.");
		List<String> commentsForChilds = new ArrayList<>();
		commentsForChilds.add("erhalten");
		commentsForChilds.add("einhalten");
		commentsForChilds.add("in Erinnerung behalten");
		descriptionForMonth.setComments(commentsForChilds);
		
		GoalDescription descriptionForDaily = new GoalDescription( "Er will die Regime verklagen_rrrr.");
		List<String> commentsForChilds2 = new ArrayList<>();
		commentsForChilds2.add("erhalten_rrr");
		commentsForChilds2.add("einhalten_rrr");
		commentsForChilds2.add("in Erinnerung behalten_rrr");
		descriptionForDaily.setComments(commentsForChilds2);
		
		
		ParentGoal yearlyGoal = new ParentGoal(description, 2016, GoalScopeNames.YEARLY, "yearly_parentGoal1");
		
		ParentGoal monthlyGoal = new ParentGoal(descriptionForMonth, Calendar.APRIL, GoalScopeNames.MONTHLY, "monthly_parentGoal1");
		monthlyGoal.setParentGoal(yearlyGoal);
		
		Goal dailyGoal = new DailyGoal(monthlyGoal, descriptionForDaily,  Calendar.MONDAY, GoalScopeNames.MONTHLY, "dailychildGoal1");
		
		
		yearlyGoal.addChildGoal(monthlyGoal);
//		yearlyGoal.addChildGoal(weeklyGoal);
		dao.save(yearlyGoal);
	}
	
	@Test
	public void daoShouldBeAbleToFindOnlyYearlyGoals(){
		GoalDescription description = new GoalDescription( "Alles muss ausreichen sein.");
		List<String> commentsForChilds2 = new ArrayList<>();
		commentsForChilds2.add("Er hat unseres Raum ausgestatt");
		description.setComments(commentsForChilds2);
		
		ParentGoal yearlyGoal = new ParentGoal(description, 2017, GoalScopeNames.YEARLY, "yearly_parentGoal_2017");
		ParentGoal yearlyGoal2 = new ParentGoal(new GoalDescription("Ých will mich mit andere Fremdsprache austatten"), 
				2016, GoalScopeNames.YEARLY, "yearly_parentGoal_2016");
		
		dao.save(yearlyGoal);
		dao.save(yearlyGoal2);
		
		List<Goal> yearlyGoalsInDatabase = dao.findByTimeLabel(yearlyGoal.getTimeLabel()); 
		assertEquals(1, yearlyGoalsInDatabase.size());
	}
	
	
}
