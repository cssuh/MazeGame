package falstad;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import falstad.Robot.Direction;
import falstad.Robot.Turn;
import generation.CardinalDirection;
import generation.Cells;
import generation.MazeConfiguration;
import generation.MazeFactory;
import generation.Order;

public class BasicRobotTest {
	
	private BasicRobot robot;
	private MazeController maze;
	private CardinalDirection cardDir;
	private Cells cell;
	private MazeFactory factory;
	private Order stub;
	private MazeConfiguration mazeConfig;

	@Before
	public void setUp() throws Exception {
		robot = new BasicRobot();
		maze = new MazeController();
		cardDir = CardinalDirection.East;
		factory = new MazeFactory();
		stub = new StubOrder();
	}

	/**
	 * test contructor and make sure nothing is returning null
	 */
	@Test
	public void testBasicRobot() {
		assertNotNull(robot);
		
		assertNull(robot.cell);
		assertNull(robot.maze);
		assertFalse(robot.stopped);
		assertEquals(0, robot.currentPosition[0]);
		assertEquals(0, robot.currentPosition[1]);
	}
	
	/**
	 * Test the rotate method and each of the Turns possible
	 */

	@Test
	public void testRotate() {
		//if we sense forward and it is > 0 then 
		//test rotate
		this.robot.setMaze(this.maze);
		
		this.cardDir = robot.direction;
		this.robot.rotate(Turn.RIGHT);
		assertEquals(cardDir.rotateClockwise(), this.robot.direction);
		
		this.cardDir = robot.direction;
		this.robot.rotate(Turn.LEFT);
		assertEquals(cardDir.rotateCounterClockwise(), this.robot.direction);
		
		this.cardDir = robot.direction;
		this.robot.rotate(Turn.AROUND);
		assertEquals(cardDir.oppositeDirection(), this.robot.direction);
		
		this.cardDir = robot.direction;
		this.robot.setBatteryLevel(0);
		this.robot.rotate(Turn.RIGHT);
		assertTrue(this.robot.hasStopped());
		
		this.cardDir = robot.direction;
		this.robot.setBatteryLevel(0);
		this.robot.rotate(Turn.LEFT);
		assertTrue(this.robot.hasStopped());
		
		this.cardDir = robot.direction;
		this.robot.setBatteryLevel(0);
		this.robot.rotate(Turn.AROUND);
		assertTrue(this.robot.hasStopped());
	}
	
	@Test
	public void testMove(){
		this.robot.setMaze(this.maze);
		this.cardDir = robot.direction;
		factory.order(stub);
		factory.waitTillDelivered();
		mazeConfig = ((StubOrder) stub).getMazeConfiguration();
		cell = mazeConfig.getMazecells();
		robot.setMazeCells(cell);
		while (robot.isAtGoal() != true){
			int firstX = robot.currentPosition[0];
			int firstY = robot.currentPosition[1];
			
			if(this.robot.batteryLevel > 0){
				if(robot.distanceToObstacle(Direction.LEFT) == 0 && robot.distanceToObstacle(Direction.FORWARD) > 0){
					robot.move(1, false);
					cardDir = robot.getCurrentDirection();
				}
				else{
					if(robot.distanceToObstacle(Direction.LEFT) > 0){
						robot.rotate(Turn.LEFT);
						robot.move(1, false);
						cardDir = robot.getCurrentDirection();

					}
					else if(robot.distanceToObstacle(Direction.RIGHT) > 0){
						robot.rotate(Turn.RIGHT);
						robot.move(1, false);

					}
					else{
						robot.rotate(Turn.AROUND);
						robot.move(1, false);	
			
					}		
				}		
			}
			cardDir = robot.getCurrentDirection();
			switch(cardDir){
			case East:
				assertEquals(firstX + 1, robot.currentPosition[0]);
				assertEquals(firstY, robot.currentPosition[1]);
				break;
				
			case West:
				assertEquals(firstX - 1, robot.currentPosition[0]);
				assertEquals(firstY, robot.currentPosition[1]);
				break;
				
			case North:
				assertEquals(firstX, robot.currentPosition[0]);
				assertEquals(firstY - 1, robot.currentPosition[1]);
				break;
				
			case South:
				assertEquals(firstX, robot.currentPosition[0]);
				assertEquals(firstY + 1, robot.currentPosition[1]);
				break;
			}
		}
	}
	
	@Test
	public void testMoveNoBattery(){
		this.robot.setMaze(this.maze);
		this.cardDir = robot.direction;
		factory.order(stub);
		factory.waitTillDelivered();
		mazeConfig = ((StubOrder) stub).getMazeConfiguration();
		cell = mazeConfig.getMazecells();
		robot.setMazeCells(cell);
		
		robot.setBatteryLevel(0);
		robot.move(1, false);
		assertTrue(robot.stopped);
	}
	
	@Test
	public void testMoveRunIntoWall(){
		this.robot.setMaze(this.maze);
		this.cardDir = robot.direction;
		factory.order(stub);
		factory.waitTillDelivered();
		mazeConfig = ((StubOrder) stub).getMazeConfiguration();
		cell = mazeConfig.getMazecells();
		robot.setMazeCells(cell);
		
		robot.move(robot.distanceToObstacle(Direction.FORWARD) + 1, false);
		assertTrue(robot.stopped);
	}
	
	@Test
	public void testMoveWizard(){
		factory.order(stub);
		factory.waitTillDelivered();
		mazeConfig = ((StubOrder) stub).getMazeConfiguration();
		cell = mazeConfig.getMazecells();
		robot.setMaze(maze);
		robot.setMazeCells(cell);
		
		int firstX = robot.currentPosition[0];
		int firstY = robot.currentPosition[1];
		robot.moveWizard(2, 3);
		assertEquals(firstX + 2, robot.currentPosition[0]);
		assertEquals(firstY + 3, robot.currentPosition[1]);
	}

	/**
	 * test current position method and make sure it is in the correct position
	 * @throws Exception
	 */
	@Test
	public void testGetCurrentPosition() throws Exception {
		int[] array = robot.currentPosition;
		assertEquals(array, robot.getCurrentPosition());
	}

	/**
	 * test to make sure if the robot is at the goal/exit position
	 */
	@Test
	public void testIsAtGoal() {
		factory.order(stub);
		factory.waitTillDelivered();
		mazeConfig = ((StubOrder) stub).getMazeConfiguration();
		cell = mazeConfig.getMazecells();
		robot.setMazeCells(cell);
		
		int x = 0;
		int y = 0;
		for(int i = 0; i < cell.width; i++){
			for(int j = 0; j < cell.height; j++){
				if (cell.isExitPosition(i, j)){
					x = i;
					y = j;
				}
			}
		}
		this.robot.currentPosition[0] = x;
		this.robot.currentPosition[1] = y;
		assertTrue(this.robot.isAtGoal());
	}

	/**
	 * make sure getCurrentDirection is correct
	 */
	@Test
	public void testGetCurrentDirection() {
		this.robot.setMaze(this.maze);
		cardDir = robot.direction;
		assertEquals(cardDir, robot.getCurrentDirection());
	}

	/**
	 * make sure getBatteryLevel is correct
	 */
	@Test
	public void testGetBatteryLevel() {
		float batteryLvl = robot.batteryLevel;
		assertEquals(batteryLvl, robot.getBatteryLevel(), batteryLvl);
		
	}

	/**
	 * make sure getEnergyForFullRotation is correct
	 */
	@Test
	public void testGetEnergyForFullRotation() {
		assertEquals(12, robot.getEnergyForFullRotation(), 12);
	}

	/**
	 * make sure getEnergyForStepForward is correct
	 */
	@Test
	public void testGetEnergyForStepForward() {
		assertEquals(5, robot.getEnergyForStepForward(), 5);
	}

	/**
	 * make sure the has stopped is correct if the battery level is zero
	 */
	@Test
	public void testHasStopped() {
		this.cardDir = robot.direction;
		this.robot.setBatteryLevel(0);
		this.robot.rotate(Turn.RIGHT);
		assertTrue(this.robot.hasStopped());
	}
	/**
	 * make sure the robot has a distance sensor
	 */
	@Test
	public void testHasDistanceSensor() {
		assertTrue(robot.hasDistanceSensor(Direction.LEFT));
		assertTrue(robot.hasDistanceSensor(Direction.RIGHT));
		assertTrue(robot.hasDistanceSensor(Direction.FORWARD));
		assertTrue(robot.hasDistanceSensor(Direction.BACKWARD));
	}
	
//	@Test
//	public void testCanSeeGoal(){
//		//getdirection to exit in maze configuration
//		factory.order(stub);
//		factory.waitTillDelivered();
//		mazeConfig = ((StubOrder) stub).getMazeConfiguration();
//		cell = mazeConfig.getMazecells();
//		robot.setMazeCells(cell);
//		robot.setMaze(maze);
//		
//		if(!this.robot.canSeeGoal(Direction.LEFT)){
//			assertFalse(robot.canSeeGoal(Direction.LEFT));
//		}
//		if(!this.robot.canSeeGoal(Direction.RIGHT)){
//			assertFalse(robot.canSeeGoal(Direction.RIGHT));
//		}
//		if(!this.robot.canSeeGoal(Direction.FORWARD)){
//			assertFalse(robot.canSeeGoal(Direction.FORWARD));
//		}
//		if(!this.robot.canSeeGoal(Direction.BACKWARD)){
//			assertFalse(robot.canSeeGoal(Direction.BACKWARD));
//		}
//		
//		int i;
//		int j;
//		int[] exitPosition = new int[2];
//		
//		for (i = 0; i < cell.width; i++){
//			for(j = 0; j < cell.height; j++){
//				if(cell.isExitPosition(i, j)){
//					exitPosition[0] = i;
//					exitPosition[1] = j;
//				}
//			}
//		}
//		
//		robot.maze.setCurrentPosition(exitPosition[0], exitPosition[1]);
//		if (robot.canSeeGoal(Direction.LEFT)){
//			assertTrue(robot.canSeeGoal(Direction.LEFT));
//		}
//		if (robot.canSeeGoal(Direction.RIGHT)){
//			assertTrue(robot.canSeeGoal(Direction.RIGHT));
//		}
//		if (robot.canSeeGoal(Direction.FORWARD)){
//			assertTrue(robot.canSeeGoal(Direction.FORWARD));
//		}
//		if (robot.canSeeGoal(Direction.BACKWARD)){
//			assertTrue(robot.canSeeGoal(Direction.BACKWARD));
//		}
//		
//		
//		
//	}
//	
//	

}
