package imta.springlove.entities;

/**
 * This enum represents the maturity of a {@link Worker} for a given
 * {@link Skill}. These are the maturity levels of the Dreyfus model
 * (cf. https://en.wikipedia.org/wiki/Dreyfus_model_of_skill_acquisition)
 */
public enum Maturity {

	NOVICE(1)    ,
	ADVANCED(2)  ,
	COMPETENCE(3),
	PROFICIENT(4),
	EXPERT(5)    ;
	
	private final int value;
    private Maturity(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
	
}
