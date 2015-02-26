public class Dog implements Entity{
	private int otherDogsSeen = 0;
	@Override
	public void init(EventPool pool) {
		pool.addReactFunc(DogPresenceEvent.class, (DogPresenceEvent e) -> {
			System.out.println("i'm a dog and I see another Dog. I have seen " + otherDogsSeen + " dogs before");
			otherDogsSeen++;
			return Boolean.FALSE;
		});
	}
}
