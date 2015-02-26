import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 
 * @author Jacob
 * this class deals with controlling the interactions of Entities
 */
public class EventPool {
	/**
	 * @author Jacob
	 *
	 * @param <E> the type of event
	 * 
	 * This class is just a wrapper around a list of Reaction objects, but is
	 * necessary because of the weird way that casting Java generic's works
	 */
	class ReactionCollection<E extends Event>{
		Collection<Reaction<E>> reactions;

		ReactionCollection(){
			reactions = new ArrayList<Reaction<E>>();
		}

		public Collection<Reaction<E>> getReactions() {
			return reactions;
		}

	}
	/**
	 * holds all of the reactions that happen to various sorts of events
	 */
	Map<Class<? extends Event>, ReactionCollection<? extends Event>> eventSets;
	
	public EventPool() {
		eventSets = new HashMap<Class<? extends Event>, ReactionCollection<? extends Event>>();
	}

	/**
	 * 
	 * @param clazz the class of the Event type
	 * @param event the actual event that gets reacted to
	 */
	public <E extends Event> void reactToEvent(Class<E> clazz, E event) {
		Iterator<Reaction<E>> it = getClassReactions(clazz).iterator();
		for (;it.hasNext();){
			Reaction<E> react = it.next();
			if (!react.react(event, this)){
				it.remove();
			}
		}
	}

	/**
	 * 
	 * @param clazz the class of event that the Reaction reacts to
	 * @param reaction the reaction object
	 */
	public <E extends Event> void addReaction(Class<E> clazz, Reaction<E> reaction){
		Collection<Reaction<E>> reactList = getClassReactions(clazz);
		reactList.add(reaction);
	}
	
	/**
	 * 
	 * @param clazz the class of event that the Reaction reacts to
	 * @param reactions Collection of reactions to be added at once
	 */
	public <E extends Event> void addReactions(Class<E> clazz, Collection<Reaction<E>> reactions){
		Collection<Reaction<E>> reactList = getClassReactions(clazz);
		reactList.addAll(reactions);
	}
	
	/**
	 * Allows the reaction to be added as a Lambda expression
	 * @param clazz the class of event that the Reaction reacts to
	 * @param func the function that gets run when event happens
	 */
	public <E extends Event> void addReactFunc(Class<E> clazz, BiFunction<E, EventPool, Boolean> func){
		addReaction(clazz, new Reaction<E>(){

			@Override
			public boolean react(Event event, EventPool pool) {
				return func.apply((E) event, pool);
			}

		});
	}

	/**
	 * 
	 * @param clazz the Event class whose reactions are being searched for
	 * @return Collection of the relevant type of Reaction
	 */
	private <E extends Event> Collection<Reaction<E>> getClassReactions(Class<E> clazz){
		ReactionCollection<E> rc = (ReactionCollection<E>) eventSets.get(clazz);
		if (rc == null){
			rc = new ReactionCollection<E>();
			eventSets.put(clazz, rc);
		}
		Collection<Reaction<E>> reactions = rc.getReactions();
		return reactions;
	}
}
