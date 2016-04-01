package world;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.part2.listener.DefaultTerrainChangeListener;


public class WorldTest {
	
	private World testWorld;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		int nbX = 10;
		int nbY = 20;
		int nbZ = 30;

		this.testWorld = new World(new int[nbX][nbY][nbZ], new DefaultTerrainChangeListener());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void constructor_LegalCase() {
		int nbX = 10;
		int nbY = 20;
		int nbZ = 30;

		World world =  new World(new int[nbX][nbY][nbZ], new DefaultTerrainChangeListener());
		assertEquals(nbX, world.getNbCubesX());
		assertEquals(nbY, world.getNbCubesY());
		assertEquals(nbZ, world.getNbCubesZ());
	}
	
	@Test
	public void spawnUnit_worldFull() {
		int i = 1;
		while(i < 120){
			testWorld.spawnUnit(false);
			int nbOfUnits = testWorld.getUnitSet().size();
			assertEquals(true, ((nbOfUnits == 100) || (nbOfUnits == i)));
			i++;
		}
		assertEquals(100, testWorld.getUnitSet().size());
	}
	

}
