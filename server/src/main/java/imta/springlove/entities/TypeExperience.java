package imta.springlove.entities;

import java.io.Serializable;

/**
 * This enum contains the different types of Experiences.
 * There are three types : personal, education and work.
 */
public enum TypeExperience implements Serializable {

	PERSONAL(1),
	EDUCATION(2),
	WORK(3);
	
	private final int value;
    private TypeExperience(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static TypeExperience getTypeExperience(int value){
    	switch(value){
    	case 1:
    		return PERSONAL;
    	case 2:
    		return EDUCATION;
    	default:
    		return WORK;
    	}
    }
}
