import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Experimental class to make Creating of entity classes involve less repetition
 * @author Jacob
 *
 */
public class FancyEntity implements Entity{

	@Retention(RetentionPolicy.RUNTIME)
	static @interface React{	}
	
	@Override
	public void init(EventPool pool) {
		for (Method m : this.getClass().getMethods()){
			if (m.isAnnotationPresent(FancyEntity.React.class)){
				addReaction(this, m, pool);
			}
		}
	}
	
	private <E extends Event> void addReaction(FancyEntity obj, Method m, EventPool pool){
		Class<? extends Event> clazz = (Class<? extends Event>) m.getParameterTypes()[0];
		pool.addReaction((Class<E>)clazz, new Reaction<E>(){

			@Override
			public boolean react(E event, EventPool pool) {
				boolean ret = false;
				try {
					ret = (boolean) m.invoke(obj, event, pool);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
					System.out.println("shit went down");
				}
				return ret;
			}
			
		});
	}

}
