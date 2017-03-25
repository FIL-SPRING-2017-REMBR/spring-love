package imta.springlove.entities;

import java.util.Map;

/**
 * Represents a Skill. For instance it can be Java.
 * 
 * A Skill can have one child skill
 */
public class Skill {

	/** The skill name. */
	private String name;
	
	/** Resources related to this Skill. It links a label to an URL. */
	private Map<String, String> resources;
	
	/** A skill related to this one. */
	private Skill skill;

	
	/**
	 * Instantiates a new Skill.
	 *
	 * @param name the name of the Skill
	 */
	public Skill(String name) {
		this.name = name;
	}

	/**
	 * Instantiates a new Skill.
	 *
	 * @param name the name of the Skill
	 * @param resources the resources (key : label, value : URL)
	 */
	public Skill(String name, Map<String, String> resources) {
		super();
		this.name = name;
		this.resources = resources;
	}

	/**
	 * Instantiates a new Skill.
	 *
	 * @param name the name of the Skill
	 * @param resources the resources (key : label, value : URL)
	 * @param skill a skill related to this skill 
	 */
	public Skill(String name, Map<String, String> resources, Skill skill) {
		super();
		this.name = name;
		this.resources = resources;
		this.skill = skill;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Map<String, String> getResources() {
		return resources;
	}

	public void setResources(Map<String, String> resources) {
		this.resources = resources;
	}
	
}
