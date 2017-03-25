package imta.springlove.entities;

/**
 * Represents the appetence of a {@link Worker} for a given {@link Skill}. An
 * appetence is composed of the current level of the Worker for the Skill and
 * its trend of evolution.
 */
public class Appetence {

	/** The current level of competence. */
	private Maturity maturity;

	/** The trend of evolution for the level. */
	private Trend trend;

	
	/**
	 * Instantiates a new Appetence.
	 * 
	 * @param maturity The current level of competence.
	 * @param trend The trend of evolution for the level.
	 */
	public Appetence(Maturity maturity, Trend trend) {
		super();
		this.maturity = maturity;
		this.trend = trend;
	}
	

	public Maturity getMaturity() {
		return maturity;
	}

	public void setMaturity(Maturity maturity) {
		this.maturity = maturity;
	}

	public Trend getTrend() {
		return trend;
	}

	public void setTrend(Trend trend) {
		this.trend = trend;
	}
	
}
