package imta.springlove.entities;

/**
 * Represents a Skill. For instance it can be Java.
 * 
 * A Skill can have one child skill
 */
public class Skill {

	/**  The skill name. */
	private String name;
	
	/**  The URL of a web resource related to this Skill. */
	private String url;
	
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
	 * @param url the url of the web resource related to this Skill
	 */
	public Skill(String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	/**
	 * Instantiates a new Skill.
	 * 
	 * @param name the name of the Skill
	 * @param url the url of the web resource related to this Skill
	 * @param skill the Skill related to this Skill
	 */
	public Skill(String name, String url, Skill skill) {
		this.name = name;
		this.url = url;
		this.skill = skill;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	
}
