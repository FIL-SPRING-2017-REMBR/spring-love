package imta.springlove.entities;

import java.util.Date;
import java.util.List;

/**
 * Represents an Experience.
 */
public class Experience {
	
	/** The id of the experience */
	private int id;

	/** The name of the experience (e.g. the name of the project). */
	private String name;

	/** The name of the organization for which the project has been done. */
	private String organisation;

	/** The role, e.g. developer, database administrator, ... */
	private String role;

	/** The type of the experience : personal, work or education. */
	private TypeExperience type;

	/** URL concerning this experience. */
	private String url;

	/** The beginning and the end of this experience. */
	private Date[] temporalRange;

	/** The description of this experience. */
	private String description;

	
	/**
	 * Instantiates a new Experience.
	 * 
	 * @param name
	 *            The name of the experience (e.g. the name of the project).
	 * @param organisation
	 *            The name of the organisation for which the project has been
	 *            done.
	 * @param role
	 *            The role, e.g. developer, database administrator, ...
	 * @param type
	 *            The type of the experience : personal, work or education.
	 * @param url
	 *            URLs concerning this experience.
	 * @param beginning
	 * 			  The beginning of this experience
	 * @param end
	 * 			  The end of this experience. If null, is means it is ongoing.
	 * @param description
	 *            The description of this experience.
	 */
	public Experience(String name, String organisation, String role, TypeExperience type, String url,
			Date beginning, Date end, String description) {
		super();
		this.id = 0;
		this.name = name;
		this.organisation = organisation;
		this.role = role;
		this.type = type;
		this.url = url;
		this.temporalRange = new Date[] {beginning, end};
		this.description = description;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public TypeExperience getType() {
		return type;
	}

	public void setType(TypeExperience type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date[] getTemporalRange() {
		return temporalRange;
	}

	public void setTemporalRange(Date[] temporalRange) {
		this.temporalRange = temporalRange;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
