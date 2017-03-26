package imta.springlove.entities;

/**
 * This enum represents the maturity of a {@link Worker} for a given
 * {@link Skill}. These are the maturity levels of the Dreyfus model
 * (cf. https://en.wikipedia.org/wiki/Dreyfus_model_of_skill_acquisition)
 */
public enum Maturity {

	NOVICE    ,
	ADVANCED  ,
	COMPETENCE,
	PROFICIENT,
	EXPERT    ;
	
}
