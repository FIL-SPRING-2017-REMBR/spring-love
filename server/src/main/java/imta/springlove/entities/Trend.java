package imta.springlove.entities;

/**
 * Represents the trend for the evolution of a {@link Skill} for a
 * {@link Worker}.
 */
public enum Trend {

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
}
