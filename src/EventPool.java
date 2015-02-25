import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventPool {
	static class EventsAndReactions<E extends Event>{
		Collection<E> events;
		List<Reaction<E>> reactions;
		
		public EventsAndReactions() {
			events = new ArrayList<E>();
			reactions = new ArrayList<Reaction<E>>();
		}

		public Collection<E> getEvents() {
			return events;
		}

		public List<Reaction<E>> getReactions() {
			return reactions;
		}
		
		
	}
	
	Map<Class<? extends Event>, EventsAndReactions<? extends Event>> eventSets;
	public EventPool() {
		eventSets = new HashMap<Class<? extends Event>, EventsAndReactions<? extends Event>>();
	}
	public <E extends Event> void addEvents(Class<E> clazz, Collection<E> newEvents) {
		Collection<E> events = getClassCollection(clazz).getEvents();
		events.addAll(newEvents);
		
		//maybe do this somewhere else?
		for (Reaction<E> reaction : getClassCollection(clazz).getReactions()){
			for (E event : newEvents){
				reaction.react(event);
			}
		}
	}
	
	public <E extends Event> void notifyFor(Class<E> clazz, Collection<Reaction<E>> reactions){
		List<Reaction<E>> reactList = getClassCollection(clazz).getReactions();
		reactList.addAll(reactions);
	}
	
	private <E extends Event> EventsAndReactions<E> getClassCollection(Class<E> clazz){
		EventsAndReactions<E> ear = (EventsAndReactions<E>) eventSets.get(clazz);
		if (ear == null){
			ear = new EventsAndReactions<E>();
			eventSets.put(clazz, ear);
		}
		return ear;
	}
	
	public <E extends Event> Collection<E> getEvents(Class<? extends Event> clazz){
		return (Collection<E>) eventSets.get(clazz).getEvents();
	}
	
	public void react(){
		
	}
}
