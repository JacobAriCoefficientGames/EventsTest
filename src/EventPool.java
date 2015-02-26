import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public <E extends Event> void addReaction(Class<E> clazz, Collection<Reaction<E>> reactions){
		Collection<Reaction<E>> reactList = getClassReactions(clazz);
		reactList.addAll(reactions);
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
