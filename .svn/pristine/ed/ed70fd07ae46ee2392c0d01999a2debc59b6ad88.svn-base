package generation;

import java.util.ArrayList;
import java.util.Random;

public class MazeBuilderKruskal extends MazeBuilder implements Runnable{
	
	int[][] cellvalues;
	
	public MazeBuilderKruskal() {
		super();
		System.out.println("MazeBuilderKruskal uses Kruskal's algorithm to generate maze.");
	}
	
	public MazeBuilderKruskal(boolean det) {
		super(det);
		System.out.println("MazeBuilderKruskal uses Kruskal's algorithm to generate maze.");
	}
	/**
	 * Kruskal's algorithm is implemented in a way such that it starts with a maze 
	 * where all walls are up and then selectively tears down walls
	 * generatePathways() creates a list of all the walls that can be torn down
	 * and then randomly chooses walls to be torn down.  
	 */
	
	@Override
	protected void generatePathways(){
		int x;
		int y;
		CardinalDirection[] directions = new CardinalDirection[2];
		
		directions[0] = CardinalDirection.East;
		directions[1] = CardinalDirection.North;
		
		final ArrayList<Wall> candidates = new ArrayList<Wall>();
		for (x = 0; x < width; x ++){
			for (y = 0; y < height; y ++){
				Wall wall = new Wall(x, y, CardinalDirection.East);
				for (CardinalDirection cd : directions){
					wall.setWall(x, y, cd);
					if (cells.canGo(wall)) // 
					{
						candidates.add(new Wall(x, y, cd));		
					}
				}
			}
		}
		
		int count = 1;
		cellvalues = new int[width][height];
		for (x = 0; x < width; x ++){
			for (y = 0; y < height; y ++){
				cellvalues[x][y] = count;
				count ++;
			}
		}

		// we need to consider each candidate wall and consider it only once
		while(!candidates.isEmpty()){
			int currentVal;
			Wall curWall;
			// in order to have a randomized algorithm,
			// we randomly select and extract a wall from our candidate set
			// this also reduces the set to make sure we terminate the loop
			curWall = extractWallFromCandidateSetRandomly(candidates);
			// check if wall leads to a new cell that is not connected to the spanning tree yet
			if (cellvalues[curWall.getX()][curWall.getY()] != cellvalues[curWall.getNeighborX()][curWall.getNeighborY()]){
				cells.deleteWall(curWall);
				currentVal = cellvalues[curWall.getNeighborX()][curWall.getNeighborY()];
				for (x = 0; x < width; x ++){
					for (y = 0; y < height; y ++){
						if (cellvalues[x][y] == currentVal){
							cellvalues[x][y] = cellvalues[curWall.getX()][curWall.getY()];
						}
								
					}
				}
			}
				
				
		}
		
	}
	
	/**
	 * chooses a random wall to try and tear down and removes it from the candidates list of walls 
	 * @param candidates
	 * @return
	 */

	private Wall extractWallFromCandidateSetRandomly(final ArrayList<Wall> candidates) {
		return candidates.remove(random.nextIntWithinInterval(0, candidates.size()-1)); 
	}
}