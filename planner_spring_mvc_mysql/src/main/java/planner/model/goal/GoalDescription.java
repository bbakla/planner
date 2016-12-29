package planner.model.goal;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="goal_description")
public class GoalDescription {
	
	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="description_Id")
	private Long id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Description")
	private String description;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="Comments", joinColumns=@JoinColumn(name="Id", referencedColumnName="description_Id"))
	@Column(name="Comment")
	private List<String> comments;
	
	public GoalDescription() {
	}
	
	public GoalDescription(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
