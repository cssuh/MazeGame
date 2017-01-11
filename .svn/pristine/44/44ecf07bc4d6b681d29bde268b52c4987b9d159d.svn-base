package falstad;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import generation.Cells;
import generation.Distance;
import generation.MazeConfiguration;
import generation.MazeFactory;
import generation.Order;

public class WizardTest {
	
	private BasicRobot robot;
	private int width;
	private int height;
	private Distance distance;
	private int pathLength;
	private MazeConfiguration mazeConfig;
	
	private Wizard driver;
	private Wizard driver1;
	private MazeController maze;
	private Cells cell;
	private Order stub;
	private MazeFactory factory;
	
	
	@Before
	/**
	 * setUp method instantiates objects so they can be tested.
	 */
	public void setUp() throws Exception {
		driver = new Wizard();
		driver1 = new Wizard(Order.Builder.Prim);
		maze = new MazeController(driver);
		robot = new BasicRobot();
		distance = new Distance(3, 3);
		factory = new MazeFactory();
		stub = new StubOrder();
	}

	/**
	 * tests the constructor for Wizard
	 */
	@Test
	public void testWizard() {
		assertNotNull(driver);
		
		assertNotNull(driver.width);
		assertNotNull(driver.height);
		assertNotNull(driver.robot);
		assertNull(driver.distance);
		assertEquals(0, driver.pathLength);		
	}

	/**
	 * Tests set dimensions to make sure the width and height are set
	 */
	@Test
	public void testSetDimensions() {
		this.driver.setDimensions(width, height);
		assertNotNull(driver.width);
		assertNotNull(driver.height);
	}

	/**
	 * tests set distance to make sure the distance is set
	 */
	@Test
	public void testSetDistance() {
		this.driver.setDistance(distance);
		assertNotNull(driver.distance);
	}

	/**
	 * tests drive2exit to see if the robot reaches the goal
	 */
	@Test
	public void testDrive2Exit() {
		factory.order(stub);
		factory.waitTillDelivered();
		mazeConfig = ((StubOrder) stub).getMazeConfiguration();
		cell = mazeConfig.getMazecells();
		
		driver.setDimensions(this.mazeConfig.getWidth(), this.mazeConfig.getHeight());
		driver.setDistance(this.mazeConfig.getMazedists());
		driver.setRobot(robot);
		driver.robot.setMaze(this.maze);
		driver.robot.setMazeCells(cell);
		
		maze.deliver(mazeConfig);
		assertTrue(this.driver.robot.isAtGoal());
	}

	/**
	 * tests the get energy consumption to see if it returns the correct number
	 */
	@Test
	public void testGetEnergyConsumption() {
		this.driver.setRobot(this.robot);
		float energy = 2500 - this.driver.robot.batteryLevel;
		assertEquals(energy, this.driver.getEnergyConsumption(), energy);
	}
	/**
	 * tests getPathLength to see if it returns the correct number
	 */
	@Test
	public void testGetPathLength() {
		assertEquals(this.driver.pathLength, this.driver.getPathLength());
	}
	
	/**
	 * tests setPathLength to see if it sets the pathLength correctly
	 */
	@Test
	public void testSetPathLength(){
		int path = 5;
		driver.setPathLength(path);
		assertEquals(path, driver.pathLength);
	}

}
