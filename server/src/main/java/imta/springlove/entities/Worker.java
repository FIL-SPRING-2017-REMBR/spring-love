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
	
	/** The Skills that this Worker knows (key) and the Maturity for each skill */
	private Map<Skill, Maturity> skills;

	/**
	 * Instantiates a new Worker.
	 * @param firstName The first name of the Worker.
	 * @param lastName The last name of the Worker.
	 * @param resources Resources related to this Worker. It links a label to an URL.
	 * @param experiences The experiences of the Worker.
	 * @param skills The Skills that this Worker knows (key) and the
	 */
	public Worker(String firstName, String lastName, Map<String, String> resources, List<Experience> experiences,
			Map<Skill, Maturity> skills) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.resources = resources;
		this.experiences = experiences;
		this.skills = skills;
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

	public Map<Skill, Maturity> getSkills() {
		return skills;
	}

	public void setSkills(Map<Skill, Maturity> skills) {
		this.skills = skills;
	}

}
