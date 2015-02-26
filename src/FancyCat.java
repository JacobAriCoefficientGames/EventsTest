
public class FancyCat extends FancyEntity{
	int dogsSeen = 0;
	
	@FancyEntity.React
	public boolean dogPresence(DogPresenceEvent e, EventPool p){
		dogsSeen++;
		if (dogsSeen > 3){
			System.out.println("SO MANY DOGS!!!! SPAZZZZ");
			p.reactToEvent(CatSpazzEvent.class, new CatSpazzEvent("I'm spazzing because there are " + dogsSeen + "dogs."));
		}
		return true; //repeat always
	}
}
