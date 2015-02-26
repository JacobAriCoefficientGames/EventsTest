/**
 * 
 * @author Jacob
 *
 * @param <E> the type of Event that this reacts to
 * 
 * this represents a function that gets run when the E event type happens
 */
public interface Reaction<E extends Event> {
	/**
	 * 
	 * @param event the event that triggered this reaction
	 * @return weather reaction should be kept in pool
	 * @return if false, then removed. if true, then kept.
	 * 
	 * This function gets run whenever there is an event of type E.
	 */
	public boolean react(E event, EventPool pool);
}
