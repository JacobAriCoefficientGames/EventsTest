import java.util.Set;

public interface Entity {
	public Set<Event> makeEvents();
	public void update(EventPool pool);
}
