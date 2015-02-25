import java.util.Collection;
import java.util.Collections;


public class Main {
	public static void main(String[] args){
		EventPool pool = new EventPool();
		pool.addEvents(Collections.singletonList(new DogPresenceEvent()), DogPresenceEvent.class);
		System.out.println(pool.getEvents(DogPresenceEvent.class).size());
	}
}
