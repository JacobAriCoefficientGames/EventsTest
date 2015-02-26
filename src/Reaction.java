public interface Reaction<E extends Event> {
	public boolean react(E event);
}
