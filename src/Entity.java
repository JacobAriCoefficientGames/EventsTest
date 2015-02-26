/**
 * 
 * @author Jacob
 * Entities represent anything in a game that can interact in many ways
 * with other game Entities.
 */
public interface Entity {
	/**
	 * 
	 * @param pool  where reactions and events are set up
	 * This is where the entity sets up the events that it is first able to respond to
	 */
	public void init(EventPool pool);
}
