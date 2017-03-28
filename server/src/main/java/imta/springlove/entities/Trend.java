package imta.springlove.entities;

import java.io.Serializable;

/**
 * Represents the trend for the evolution of a {@link Skill} for a
 * {@link Worker}.
 */
public enum Trend implements Serializable {

	INCREASING(1),
	STABLE(2)    ,
	DECREASING(3);

	private final int value;
    private Trend(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static Trend getTrend(int value){
    	switch(value){
    	case 1:
    		return INCREASING;
    	case 2:
    		return STABLE;
    	default:
    		return DECREASING;
    	}
    }
}
