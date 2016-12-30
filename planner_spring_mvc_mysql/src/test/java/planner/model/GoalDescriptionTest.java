package planner.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import planner.dao.GenericDao;
import planner.model.goal.GoalDescription;
import planner.test.config.HibernateTestConfiguration;

@ContextConfiguration(classes = { HibernateTestConfiguration.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class GoalDescriptionTest // extends EntityDaoImplTest
{
	@Autowired
	@Qualifier("GoalDescriptionDao")
	private GenericDao<GoalDescription> dao;

	

	@Test(expected = IllegalArgumentException.class)
	public void goalDescriptionCanNotBeStoredIfNull() {
		GoalDescription description = null;

		dao.save(description);
	}

	@Test
	public void goalDescriptionShouldBeSuccessfullySaved() {
		GoalDescription description = new GoalDescription("test1 description");

		dao.save(description);

		GoalDescription inDatabase = (GoalDescription) dao.findByID(description.getId());

		assertEquals(description.getDescription(), inDatabase.getDescription());
		assertEquals(0, inDatabase.getComments().size());
	}

	@Test
	public void commentsShouldObtainedFromDescription() {
		
		List<String> comments = new ArrayList<String>();
		comments.add("In Erinnerung behalten");
		comments.add("einhalten");
		comments.add("Wir müssen die Regeln einhalten");

		GoalDescription description = new GoalDescription("test1 description");
		description.setComments(comments);

		dao.save(description);

		GoalDescription inDatabase = (GoalDescription) dao.findByID(description.getId());

		assertEquals(description.getDescription(), inDatabase.getDescription());
		assertEquals(description.getComments().size(), inDatabase.getComments().size());
	}

}
