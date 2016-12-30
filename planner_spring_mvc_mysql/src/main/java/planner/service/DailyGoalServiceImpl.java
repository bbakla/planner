package planner.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import planner.dao.GenericDao;
import planner.model.goal.DailyGoal;
import planner.model.goal.ParentGoal;


@Service("dailyGoalService")
@Transactional
public class DailyGoalServiceImpl  implements DailyGoalService{

	@Autowired
	@Qualifier("DailyGoalDao")
	private GenericDao<DailyGoal> dao; 
	
	@Override
	public DailyGoal findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public ParentGoal findParent(Long id) {
		return dao.findById(id).getParentGoal();
	}

	@Override
	public List<DailyGoal> findAll() {
		return dao.findAll();
	}

	@Override
	public void saveGoal(DailyGoal goal) {
		dao.save(goal);
	}

	@Override
	public void updateGoal(DailyGoal goal) {
		dao.update(goal);
	}

	@Override
	public void deleteGoal(DailyGoal goal) {
		dao.delete(goal);
	}

}
