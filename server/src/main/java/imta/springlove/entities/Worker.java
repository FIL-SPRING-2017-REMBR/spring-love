package imta.springlove.entities;

import java.util.Map;

public class Worker {

	/** The first name of the Worker. */
	private String firstName;
	
	/** The last name of the Worker. */
	private String lastName;
	
	/** Resources related to this Worker. It links a label to an URL. */
	private Map<String, String> resources;
	
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
	
	
	
}
