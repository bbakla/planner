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
import planner.model.goal.GoalDescription;
import planner.model.goal.scope.GoalScope;
import planner.model.goal.scope.GoalScopeNames;
import planner.test.config.HibernateTestConfiguration;

@ContextConfiguration(classes = { HibernateTestConfiguration.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class GoalDetailsTest {

	@Autowired
	@Qualifier("GoalDetailsDao")
	private GenericDao<GoalDetails> dao;
	
	@Autowired
	@Qualifier("GoalDescriptionDao")
	private GenericDao<GoalDescription> descriptionDao;

	
	@Test
	public void GoalDescriptionShouldBeAbleToHaveDetails(){
		
		GoalDescription description = new GoalDescription("Wir müssen unserem Leben beherrschen");
		GoalDetails details = new GoalDetails(description, new GoalScope(Calendar.APRIL, GoalScopeNames.MONTHLY), GoalStatus.IN_PROGRESS);
		
//		details.setDescription(description);
		
		dao.save(details);
		
		GoalDetails inDatabase = dao.findByID(details.getId());
		
		assertEquals(details.getTimeFrame(), inDatabase.getTimeFrame());
	}
	
	@Test
	public void GoalDescriptionShouldBeAbleToHaveDetailsWhichHasComments(){
		
		GoalDescription description = new GoalDescription("Wir müssen unserem Leben beherrschen");
		List<String> comments = new ArrayList<>();
		comments.add("erobern");
		comments.add("erben");
		comments.add("wir müssen die Regeln einhalten");
		description.setComments(comments);
		
		GoalDetails details = new GoalDetails(description, new GoalScope(Calendar.APRIL, GoalScopeNames.MONTHLY), GoalStatus.IN_PROGRESS);
		details.setDescription(description);
		dao.save(details);
		
		GoalDetails inDatabase = dao.findByID(details.getId());
		
		assertEquals(details.getTimeFrame(), inDatabase.getTimeFrame());
		
		GoalDescription descriptionInDatabase = descriptionDao.findByID(description.getId());
		assertEquals(description.getComments().size(), descriptionInDatabase.getComments().size());
		
		
	}
}
