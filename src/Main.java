import java.util.Collections;


public class Main {
	static int a;
	public static void main(String[] args){
		EventPool pool = new EventPool();
		pool.addReaction(DogPresenceEvent.class, Collections.singletonList(

				new Reaction<DogPresenceEvent>() {
					@Override
					public void react(DogPresenceEvent event) {
						++a;
						System.out.println("dog event! yikes!");
					}
				})

				);
		pool.reactToEvent(DogPresenceEvent.class, new DogPresenceEvent());
		pool.reactToEvent(DogPresenceEvent.class, new DogPresenceEvent());
		System.out.println("num of dog events: " + a);
		//System.out.println(pool.getEvents(DogPresenceEvent.class).size());
	}
}
