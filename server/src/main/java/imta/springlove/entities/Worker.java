package imta.springlove.entities;

import java.util.List;
import java.util.Map;

/**
 * Represents a Worker, with his/her name, his/her experiences and resources
 * (URLs) related to him/her.
 */
public class Worker {

	/** The first name of the Worker. */
	private String firstName;

	/** The last name of the Worker. */
	private String lastName;

	/** Resources related to this Worker. It links a label to an URL. */
	private Map<String, String> resources;
	
	/** The experiences of the Worker. */
	private List<Experience> experiences;

	
	public Worker(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Worker(String firstName, String lastName, Map<String, String> resources) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.resources = resources;
	}

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Map<String, String> getResources() {
		return resources;
	}

	public void setResources(Map<String, String> resources) {
		this.resources = resources;
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

}
