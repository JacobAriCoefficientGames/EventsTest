import java.io.IOException;
import java.util.Scanner;

public class Main {
	static int a;
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.println("how many dogs do you want?");
		int dogCount = in.nextInt();
		
		System.out.println("how many cats do you want?");
		int catsCount = in.nextInt();


		Game game = new Game();
		for (int i = 0; i < dogCount; ++i){
			game.addEntity(new Dog());
		}
		for (int i = 0; i < catsCount; ++i){
			game.addEntity(new Cat());
		}
		game.run(BeginPetSimulation.class, new BeginPetSimulation());
	}

}
