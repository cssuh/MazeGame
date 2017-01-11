/**
 * 
 */
package falstad;

import generation.Order;

import java.awt.event.KeyListener;
import java.io.File;
import java.sql.Driver;

import javax.swing.JFrame;


/**
 * This class is a wrapper class to startup the Maze game as a Java application
 * 
 *
 * This code is refactored code from Maze.java by Paul Falstad, www.falstad.com, Copyright (C) 1998, all rights reserved
 * Paul Falstad granted permission to modify and use code for teaching purposes.
 * Refactored by Peter Kemper
 * 
 * MazeApplication
 *
 * Responsibilities: This class is used to start the Mazegame as a Java application. 
 * Parses arguments to determine which algorithm is used to build maze and 
 * whether other arguments have been used (i.e. Wallfollower).
 *
 * Collaborators: Basic Robot, MazeController, RobotDriver,
 * WallFollower, SimpleKeyListener
 * @author cssuh
*/
public class MazeApplication extends JFrame {

	// not used, just to make the compiler, static code checker happy
	private static final long serialVersionUID = 1L;

	private KeyListener kl ;

	private MazeController controller ;
	
	private RobotDriver robotDriver ;
	private BasicRobot robot ;

	private WallFollower wallDriver;
	private Wizard wizardDriver;
	
	/**
	 * Constructor
	 */
	public MazeApplication() {
		super() ;
		System.out.println("MazeApplication: maze will be generated with a randomized algorithm.");
		controller = new MazeController() ;
		init() ;
	}

	/**
	 * Constructor that loads a maze from a given file or uses a particular method to generate a maze
	 */
	public MazeApplication(String parameter) {
		super() ;
		// scan parameters
		// Case 1: Prim
		if ("Prim".equalsIgnoreCase(parameter))
		{
			System.out.println("MazeApplication: generating random maze with Prim's algorithm");
			controller = new MazeController(Order.Builder.Prim) ;
			init() ;
			return ;
		}
		// Case 2: Kruskal
		// TODO: for P2 assignment, please add code for Kruskal's algorithm here
		if ("Kruskal".equalsIgnoreCase(parameter)){
			System.out.println("MazeApplication: generating random maze with Kruskal's algorithm");
			controller = new MazeController (Order.Builder.Kruskal);
			init() ;
			return ;
		}
		
		// Case 3: a file
		File f = new File(parameter) ;
		if (f.exists() && f.canRead())
		{
			System.out.println("MazeApplication: loading maze from file: " + parameter);
			controller = new MazeController(parameter) ;
			init();
			return ;
		}
		
		// Case 4: WallFollower
		if ("WallFollower".equalsIgnoreCase(parameter))
		{
			System.out.println("Using WallFollower algorithm to solve maze");
			robotDriver = new WallFollower() ;
			controller = new MazeController(robotDriver) ;
			init() ;
			return ;
		}
		// Case 5: Wizard
		if ("Wizard".equalsIgnoreCase(parameter))
		{
			System.out.println("Using Wizard algorithm to solve maze");
			robotDriver = new Wizard() ;
			controller = new MazeController(robotDriver) ;
			//TODO un-comment wizard
			init() ;
			return ;
		} 
		// Default case: 
		System.out.println("MazeApplication: unknown parameter value: " + parameter + " ignored, operating in default mode.");
		controller = new MazeController() ;
		init() ;
	}
	/**
	 * Constructor that takes a builder and a maze driver as parameters
	 * @param builder
	 * @param solver
	 */
	public MazeApplication(String builder, String solver){
		//Prim & WallFollower or Wizard
		if("Prim".equalsIgnoreCase(builder)){
			System.out.println("MazeApplication: generating random maze with Prim's algorithm");

			if ("WallFollower".equalsIgnoreCase(solver)){
				System.out.println("MazeApplication: solving maze with WallFollower algorithm");
				wallDriver = new WallFollower(Order.Builder.Prim);
				controller = new MazeController(Order.Builder.Prim, wallDriver) ;
			}
			if("Wizard".equalsIgnoreCase(solver)){
				wizardDriver = new Wizard(Order.Builder.Prim);
				controller = new MazeController(Order.Builder.Prim, wizardDriver) ;
			}
			init() ;
			return ;
		}
		
		//Kruskal & WallFollower or Wizard
		if(builder.equalsIgnoreCase("Kruskal")){
			System.out.println("MazeApplication: generating random maze with Kruskal's algorithm");
			
			if ("WallFollower".equalsIgnoreCase(solver)){
				System.out.println("MazeApplication: solving maze with WallFollower algorithm");
				wallDriver = new WallFollower(Order.Builder.Kruskal);
				controller = new MazeController(Order.Builder.Kruskal, wallDriver) ;
			}
			if("Wizard".equalsIgnoreCase(solver)){
				wizardDriver = new Wizard(Order.Builder.Kruskal);
				controller = new MazeController(Order.Builder.Kruskal, wizardDriver);
			}
			init() ;
		}
		
		//File & Wizard
		else{
			File f = new File(builder) ;
			if (f.exists() && f.canRead())
			{
				System.out.println("MazeApplication: loading maze from file: " + builder);
				controller = new MazeController(builder) ;
				if("WallFlower".equalsIgnoreCase(solver))
				if ("Wizard".equalsIgnoreCase(solver)){
					
				}
				
				init();
				return ;
			}
		}

		
	}

	/**
	 * Initializes some internals and puts the game on display.
	 */
	private void init() {
		add(controller.getPanel()) ;
		
		kl = new SimpleKeyListener(this, controller) ;
		addKeyListener(kl) ;
		
		setSize(400, 400) ;
		setVisible(true) ;
		
		// focus should be on the JFrame of the MazeApplication and not on the maze panel
		// such that the SimpleKeyListener kl is used
		setFocusable(true) ;
		
		controller.init();
	}
	
	/**
	 * Main method to launch Maze as a java application.
	 * The application can be operated in two ways. The intended normal operation is to provide no parameters
	 * and the maze will be generated by a particular algorithm. If a filename is given, the maze will be loaded
	 * from that file. The latter option is useful for development to check particular mazes.
	 * @param args is optional, first parameter is a filename with a given maze
	 */
	public static void main(String[] args) {
		MazeApplication a ; 
		switch (args.length) {
		case 4 : a = new MazeApplication(args[1], args[3]);
		break ;
		case 3 : a = new MazeApplication(args[0], args[2]);
		break ;
		case 2 : a = new MazeApplication(args[1]);
		break ;
		case 1 : a = new MazeApplication(args[0]);
		break ;
		case 0 : 
		default : a = new MazeApplication() ;
		break ;
		}
		a.repaint() ;
	}

}
