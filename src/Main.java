import java.util.Collections;


public class Main {
	static int a;
	public static void main(String[] args){
		EventPool pool = new EventPool();
		pool.addReaction(DogPresenceEvent.class,
				new Reaction<DogPresenceEvent>() {
					@Override
					public boolean react(DogPresenceEvent event) {
						++a;
						System.out.println("dog event! yikes!");
						return false;
					}
				});
		pool.addReactFunc(DogPresenceEvent.class, (DogPresenceEvent e) -> {
			System.out.println("this is also a reaction");
			return new Boolean(false);
		});
		Dog murphy = new Dog();
		murphy.init(pool);
		pool.reactToEvent(DogPresenceEvent.class, new DogPresenceEvent());
		pool.reactToEvent(DogPresenceEvent.class, new DogPresenceEvent());
		System.out.println("num of dog events: " + a);
		//System.out.println(pool.getEvents(DogPresenceEvent.class).size());
	}
}
