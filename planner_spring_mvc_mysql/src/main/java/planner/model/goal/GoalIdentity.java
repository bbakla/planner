package planner.model.goal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GoalIdentity {

	@Column
	private String name;
	
	@Column
	private Long id;
	
	public GoalIdentity(){
		
	}
	
	public GoalIdentity(String name, Long id) {
		super();
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
