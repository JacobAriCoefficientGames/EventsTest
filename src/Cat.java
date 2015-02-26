
public class Cat implements Entity{
	int dogsSeen;
	Cat(){
		dogsSeen = 0;
	}
	@Override
	public void init(EventPool pool) {
		pool.addReaction(DogPresenceEvent.class, (DogPresenceEvent e, EventPool p) -> {
			dogsSeen++;
			if (dogsSeen > 3){
				System.out.println("SO MANY DOGS!!!! SPAZZZZ");
				p.reactToEvent(CatSpazzEvent.class, new CatSpazzEvent("I'm spazzing because there are " + dogsSeen + "dogs."));
			}
			return true; //repeat always
		});
	}
	
}
