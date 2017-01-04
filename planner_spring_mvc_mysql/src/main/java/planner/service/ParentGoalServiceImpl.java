package planner.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import planner.dao.GenericDao;
import planner.model.goal.Goal;
import planner.model.goal.ParentGoal;


@Service("parentGoalService")
@Transactional
public class ParentGoalServiceImpl implements ParentGoalService{

	@Autowired
	@Qualifier("ParentGoalDao")
	private GenericDao<ParentGoal> dao; 
	
	@Override
	public ParentGoal findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Goal> findChilds(Long id) {
		ParentGoal goal = dao.findById(id);
		
		return goal.getChildGoals();
	}

	@Override
	public ParentGoal findParent(Long id) {
		return dao.findById(id).getParentGoal();
	}

	@Override
	public List<ParentGoal> findAll() {
		return dao.findAll();
	}
	
	@Override
	public List<ParentGoal> findYearlyGoals(int timeLabel) {
		return dao.findByTimeLabel(timeLabel);
	}

	@Override
	public List<Goal> findMonthlyGoals(int timeLabel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveGoal(ParentGoal goal) {
		dao.save(goal);
	}

	@Override
	public void updateGoal(ParentGoal goal) {
		dao.update(goal);
	}

	@Override
	public void deleteGoal(ParentGoal goal) {
		dao.delete(goal);
	}

}
