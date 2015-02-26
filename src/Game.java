import java.util.ArrayList;
import java.util.List;

public class Game {
	EventPool ePool;
	List<Entity> entities;
	Game(){
		entities = new ArrayList<Entity>();
	}
	public void addEntity(Entity ent){
		entities.add(ent);
	}
	public <E extends Event> void run(Class<E> clazz, E starter){
		ePool = new EventPool();
		for (Entity ent : entities){
			ent.init(ePool);
		}
		ePool.reactToEvent(clazz, starter);
	}
}
