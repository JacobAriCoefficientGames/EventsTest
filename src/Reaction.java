public interface Reaction<E extends Event> {
	public void react(E event);
}
