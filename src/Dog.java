public class Dog implements Entity{
	private int otherDogsSeen = 0;
	@Override
	public void init(EventPool pool) {
		pool.addReaction(BeginPetSimulation.class, this::beginPetSimulation);

		pool.addReaction(DogPresenceEvent.class, (DogPresenceEvent e, EventPool p) -> {
			if (this != e.dog){
				System.out.println("i'm a dog and I see another Dog. I have seen " + otherDogsSeen + " dogs before");
				otherDogsSeen++;
			}
			return true;
		});

		pool.addReaction(CatSpazzEvent.class, (CatSpazzEvent e, EventPool p) -> {
			System.out.println("i'm a dog and I see a cat spazzing. The cat says: " + e.spazzMessage);
			return true; //only do once
		});
	}
	
	private Boolean beginPetSimulation(BeginPetSimulation e, EventPool p){
		p.reactToEvent(DogPresenceEvent.class, new DogPresenceEvent(this));
		return false;
	}
}
