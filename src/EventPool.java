import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class EventPool {
	class ReactionCollection<E extends Event>{
		Collection<Reaction<E>> reactions;

		ReactionCollection(){
			reactions = new ArrayList<Reaction<E>>();
		}

		public Collection<Reaction<E>> getReactions() {
			return reactions;
		}

	}
	Map<Class<? extends Event>, ReactionCollection<? extends Event>> eventSets;
	public EventPool() {
		eventSets = new HashMap<Class<? extends Event>, ReactionCollection<? extends Event>>();
	}

	public <E extends Event> void reactToEvent(Class<E> clazz, E event) {
		for (Reaction<E> reaction : getClassReactions(clazz)){
			reaction.react(event);
		}
	}

	public <E extends Event> void addReactions(Class<E> clazz, Collection<Reaction<E>> reactions){
		Collection<Reaction<E>> reactList = getClassReactions(clazz);
		reactList.addAll(reactions);
	}
	
	public <E extends Event> void addReaction(Class<E> clazz, Reaction<E> reaction){
		Collection<Reaction<E>> reactList = getClassReactions(clazz);
		reactList.add(reaction);
	}

	public <E extends Event> void addReactFunc(Class<E> clazz, Consumer<E> func){
		addReaction(clazz, new Reaction<E>(){

			@Override
			public void react(Event event) {
				func.accept((E) event);
			}

		});
	}

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
