import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EventPool {
	Map<Class<? extends Event>, Collection<? extends Event>> eventSets;
	public EventPool() {
		eventSets = new HashMap<Class<? extends Event>, Collection<? extends Event>>();
	}
	public <E extends Event> void addEvents(Collection<E> newEvents, Class<E> clazz) {
		Collection<E> events = getClassCollection(clazz);
		events.addAll(newEvents);
	}
	
	private <E extends Event> Collection<E> getClassCollection(Class<E> clazz){
		Collection<E> ret = (Collection<E>) eventSets.get(clazz);
		if (ret == null){
			eventSets.put(clazz, new ArrayList());
			ret = (Collection<E>) eventSets.get(clazz);
		}
		return ret;
	}
	
	
	@SuppressWarnings("unchecked")
	public <E extends Event> Collection<E> getEvents(Class<? extends Event> clazz){
		return (Collection<E>) eventSets.get(clazz);
	}
}
