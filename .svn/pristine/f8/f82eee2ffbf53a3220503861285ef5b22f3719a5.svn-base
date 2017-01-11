package falstad;

import generation.MazeConfiguration;
import generation.MazeContainer;
import generation.MazeFactory;
import generation.Order;
import generation.Factory;
import generation.Order.Builder;
import generation.MazeBuilder;


public class StubOrder extends MazeController implements Order {
	// what do I need in the order stub?
	// How does MazeController interact with MazeFactory?
	
	// instantiate a MazeFactory and give it a OrderStub that describes what kind of 
	// MazeConfiguration you want (which builder should be used, which skill level)
	// MazeFactory will call OrderStub.deliver(X) to give the reference for the
	// produced MazeConfiguration X to the OrderStub
	// JUnit code should have a reference to its stubOrder
	// Once I have a reference for X I can check its properties for correctness

	private int skill;
	private Builder builder;
	private MazeConfiguration mazeConfig;
	private boolean perfect;
	int percentDone;
	
	/**
	 * Default constructor for StubOrder
	 */
	
	public StubOrder(){
		this.skill = 1;
		this.builder = Builder.DFS;
		this.perfect = true;
	}
	
	/**
	 * Constructor for StubOrder
	 * @param skill (0-9, a-f)
	 * @param builder (DFS, Prim, Kruskal)
	 * @param perfect
	 */
	public StubOrder(int skill, Builder builder, boolean perfect){
		this.skill = skill;
		this.builder = builder;
		this.perfect = perfect;
	}
	
	/**
	 * returns the skill level, range of values 0,1,2,...,15
	 */
	@Override
	public int getSkillLevel(){
		return skill;
	}
	
	/**
	 * returns the builder: DFS, Prim, Kruskal
	 */
	@Override
	public Builder getBuilder(){
		return builder;
	}
	
	/**
	 * returns perfect (true/false)
	 * Describes if the ordered maze should be perfect, i.e. there are 
	 * no loops and no isolated areas, which also implies that 
	 * there are no rooms as rooms can imply loops
	 */
	@Override
	public boolean isPerfect(){
		return perfect;
	}

	/**
	 * Delivers the produced maze. 
	 * This method is called by the factory to provide the 
	 * resulting maze as a MazeConfiguration.
	 * @param the maze
	 */
	@Override
	public void deliver(MazeConfiguration mazeConfig){
		this.mazeConfig = mazeConfig;
	}
	
	/**
	 * Provides an update on the progress being made on 
	 * the maze production. This method is called occasionally
	 * during production, there is no guarantee on particular values.
	 * Percentage will be delivered in monotonously increasing order,
	 * the last call is with a value of 100 after delivery of product.
	 * @param current percentage of job completion
	 */
	@Override
	public void updateProgress(int percentage){
		this.percentDone = percentage;
	}
	
	/**
	 * returns the mazeConfiguration
	 */
	public MazeConfiguration getMazeConfiguration(){
		return mazeConfig;
	}
	
	
}



