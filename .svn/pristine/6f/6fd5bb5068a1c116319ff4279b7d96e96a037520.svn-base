package generation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import falstad.MazeApplication;
import falstad.StubOrder;
import generation.Order.Builder;
import generation.MazeBuilderKruskal;
import generation.MazeBuilder;

public class MazeBuilderKruskalTest extends MazeFactoryTest {
	
	int x;
	int y;
	int value;
	int counter;
	boolean testcondition;
	MazeBuilderKruskal kruskal;
	MazeBuilderKruskal kruskalTrue;
	MazeBuilderKruskal kruskalFalse;
	
	@Before
	public void setUp() throws Exception {
		factory = new MazeFactory();
		stub = new StubOrder(1, Builder.Kruskal, true);
		kruskal = new MazeBuilderKruskal();

		kruskalTrue = new MazeBuilderKruskal(true);
		kruskalFalse = new MazeBuilderKruskal(false);
	}
	
	/**
	 * Tests the constructor for MazeBuilderKruskal
	 */
	@Test
	public void testMazeBuilderKruskal() {
		assertNotNull(kruskal);
		assertNotNull(kruskalTrue);
		assertNotNull(kruskalFalse);
	}

	/**
	 * Tests to see if the cell values assigned in MazeBuilderKruskal
	 * are all the same.  They should be the same if the whole maze is connected.
	 */
	@Test
	public void testGeneratePathways() {
		kruskal.buildOrder(stub);
		kruskal.run();
		
		testcondition = false;
		counter = 0;
		value = kruskal.cellvalues[0][0];
		for(x = 0; x < kruskal.cellvalues.length; x ++){
			for(y = 0; y < kruskal.cellvalues.length; y ++){
				if (value != kruskal.cellvalues[x][y]){
					counter ++;
				}
			}
		}
		if (counter == 0){
			testcondition = true;
		}
		
		assertTrue(testcondition);
	}



}
