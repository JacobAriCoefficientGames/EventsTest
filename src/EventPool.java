import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventPool {
	Map<Class<? extends Event>, Collection<Reaction<? extends  Event>>> eventSets;
	public EventPool() {
		eventSets = new HashMap<Class<? extends Event>, Collection<Reaction<? extends Event>>>();
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
		Collection<Reaction<E>> reactions = (Collection<Reaction<E>>) eventSets.get(clazz);
		if (reactions == null){
			reactions = new ArrayList();
			eventSets.put(clazz, reactions);
		}
		return reactions;
	}
}
