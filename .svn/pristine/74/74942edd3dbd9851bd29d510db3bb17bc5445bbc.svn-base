package falstad;

import falstad.Robot.Direction;
import falstad.Robot.Turn;
import generation.Distance;
import generation.MazeConfiguration;
import generation.Order;
import generation.Order.Builder;

/**
* WallFollower
*
* Responsibilities: implement drive2Exit to navigate the
* robot through the maze using the WallFollower algorithm.
*
* Collaborators: Robot, MazeController, BasicRobot, RobotDriver
* @author cssuh
*/

public class WallFollower implements RobotDriver {
	
	protected BasicRobot robot;
	protected int width;
	protected int height;
	protected Distance distance;
	protected int pathLength;
	protected MazeController controller;
	protected MazeConfiguration mazeConfig;

	public WallFollower(){
		this.robot = null;
		this.pathLength = 0;
		this.distance = null;
		this.width = 0;
		this.height = 0;
		controller = new MazeController();
	}
	
	public WallFollower(Order.Builder builder){
		this.robot = null;
		this.pathLength = 0;
		this.distance = null;
		this.width = 0;
		this.height = 0;
		controller = new MazeController(builder);
	}
	
	@Override
	public void setRobot(Robot r) {
		robot = ((BasicRobot) r);
	}

	@Override
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	@Override
	public boolean drive2Exit() throws Exception {
		robot.maze.showMaze = true;
		robot.maze.showSolution = true;
		robot.maze.mapMode = true;
		
		mazeConfig = robot.maze.getMazeConfiguration();
		mazeConfig.getMazecells();
		
		while (robot.isAtGoal() != true){
			if(this.robot.batteryLevel > 0){
				if(robot.distanceToObstacle(Direction.LEFT) == 0 && robot.distanceToObstacle(Direction.FORWARD) > 0){
					robot.move(1, false);
					robot.maze.notifyViewerRedraw();
					Thread.sleep(75);
				}
				else{
					if(robot.distanceToObstacle(Direction.LEFT) > 0){
						robot.rotate(Turn.LEFT);
						robot.move(1, false);
						robot.maze.notifyViewerRedraw();
						Thread.sleep(75);
					}
					else if(robot.distanceToObstacle(Direction.RIGHT) > 0){
						robot.rotate(Turn.RIGHT);
						robot.move(1, false);
						robot.maze.notifyViewerRedraw();
						Thread.sleep(75);
					}
					else{
						robot.rotate(Turn.AROUND);
						robot.move(1, false);
						robot.maze.notifyViewerRedraw();
						Thread.sleep(75);
					}
				}
				pathLength ++;
			}
			else{
				return false;
			}
		}
		
		if(this.robot.canSeeGoal(Direction.LEFT)){
			((BasicRobot) robot).maze.state = Constants.StateGUI.STATE_FINISH;
			((BasicRobot) robot).maze.notifyViewerRedraw();
		}
		else if(this.robot.canSeeGoal(Direction.RIGHT)){
			((BasicRobot) robot).maze.state = Constants.StateGUI.STATE_FINISH;
			((BasicRobot) robot).maze.notifyViewerRedraw();
		}
		else if(this.robot.canSeeGoal(Direction.BACKWARD)){
			((BasicRobot) robot).maze.state = Constants.StateGUI.STATE_FINISH;
			((BasicRobot) robot).maze.notifyViewerRedraw();
		}
		else if(this.robot.canSeeGoal(Direction.FORWARD)){
			((BasicRobot) robot).maze.state = Constants.StateGUI.STATE_FINISH;
			((BasicRobot) robot).maze.notifyViewerRedraw();
		}
		
		return this.robot.isAtGoal();
	}

	@Override
	public float getEnergyConsumption() {
		return 2500 - this.robot.batteryLevel;
	}
	
	public void setPathLength(int pathLength){
		this.pathLength = pathLength;
	}

	@Override
	public int getPathLength() {
		return pathLength;
	}

}
