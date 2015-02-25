import java.util.Collections;


public class Main {
	static int a;
	public static void main(String[] args){
		EventPool pool = new EventPool();
		pool.notifyFor(DogPresenceEvent.class, Collections.singletonList(

				new Reaction<DogPresenceEvent>() {
					@Override
					public void react(DogPresenceEvent event) {
						++a;
						System.out.println("dog event! yikes!");
					}
				})

				);
		pool.addEvents(DogPresenceEvent.class, Collections.singletonList(new DogPresenceEvent()));
		pool.addEvents(DogPresenceEvent.class, Collections.singletonList(new DogPresenceEvent()));
		System.out.println("num of dog events: " + a);
		//System.out.println(pool.getEvents(DogPresenceEvent.class).size());
	}
}
