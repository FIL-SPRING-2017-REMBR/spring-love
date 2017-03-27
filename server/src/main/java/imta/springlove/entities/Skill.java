package imta.springlove.entities;

import java.io.Serializable;
import java.util.Map;

/**
 * Represents a Skill. For instance it can be Java.
 * 
 * A Skill can have one child skill
 */
public class Skill implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The skill id */
	private int id;

	/** The skill name. */
	private String name;
	
	/** Resources related to this Skill. It links a label to an URL. */
	private String url;
	
	/** A skill related to this one. */
	private Skill skill;


	public Skill() {
		// constructeur par défaut
	}
	
	public Skill(String name) {
		super();
		this.name = name;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", name=" + name + ", url=" + url + ", skill=" + skill + "]";
	}
	
}
