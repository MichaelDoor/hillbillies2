package world;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cube.Cube;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import objects.Unit;
import position.PositionVector;


public class WorldTest {
	
	private World testWorld;
	private static World world;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		int nbX = 10;
		int nbY = 20;
		int nbZ = 30;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[5][5][5] = 1;
		matrix[4][5][5] = 1;
		matrix[3][5][5] = 1;
		matrix[2][5][5] = 1;
		matrix[1][5][5] = 1;
		matrix[0][5][5] = 1;
		world = new World(matrix, new DefaultTerrainChangeListener());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		int nbX = 10;
		int nbY = 20;
		int nbZ = 30;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[5][5][5] = 1;
		matrix[4][5][5] = 1;
		matrix[3][5][5] = 1;
		matrix[2][5][5] = 1;
		matrix[1][5][5] = 1;
		matrix[0][5][5] = 1;
		this.testWorld = new World(matrix, new DefaultTerrainChangeListener());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void constructor_LegalCase() {
		int nbX = 10;
		int nbY = 20;
		int nbZ = 30;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 1;
		matrix[5][5][5] = 1;
		matrix[4][5][5] = 1;
		matrix[3][5][5] = 1;
		matrix[2][5][5] = 1;
		matrix[1][5][5] = 1;
		matrix[0][5][5] = 1;

		World world =  new World(matrix, new DefaultTerrainChangeListener());
		assertEquals(nbX, world.getNbCubesX());
		assertEquals(nbY, world.getNbCubesY());
		assertEquals(nbZ, world.getNbCubesZ());
		Cube solidCube = world.getCube(5, 5, 5);
		assertEquals(true, solidCube.isSolid());
		
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
	
	@Test
	public void randomStandingPosition() {
		PositionVector position = testWorld.randomStandingPosition();
		Cube cube = testWorld.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
		boolean flag = testWorld.isValidStandingPosition(cube.getPosition());
		assertEquals(true, flag);
	}
	
	@Test
	public void getAllAdjacents_LegalCase(){
		PositionVector position = testWorld.randomStandingPosition();
		int x = (int) position.getXArgument();
		int y = (int) position.getYArgument();
		int z = (int) position.getZArgument();
		Set<PositionVector> adjacents = testWorld.getAllAdjacentPositions(position);
		assertEquals(true, (adjacents.size() <= 26));
		for(PositionVector adjacent : adjacents){
			int x2 = (int) adjacent.getXArgument();
			int y2 = (int) adjacent.getYArgument();
			int z2 = (int) adjacent.getZArgument();
			boolean flag = ((Math.abs(x-x2) <= 1) && (Math.abs(y-y2) <= 1) && (Math.abs(z-z2) <= 1));
			assertEquals(true, flag);
		}
	}
	
	@Test
	public void hasSolidAdjacent_True(){
		Cube cube = null;
		Random generator = new Random();
		while((cube == null) || (! testWorld.isSolidConnectedToBorder(cube.getPosition()))){
			int x = generator.nextInt(testWorld.getNbCubesX());
			int y = generator.nextInt(testWorld.getNbCubesY());
			int z = generator.nextInt(testWorld.getNbCubesZ());
			cube = testWorld.getCube(x, y, z);
		}
		Cube solidCube = testWorld.getCube(5, 5, 5);
		assertEquals(true, solidCube.isSolid());
		assertEquals(true, testWorld.hasSolidAdjacent(solidCube));
	}
	
	@Test
	public void spawnUnit_LegalCase(){
		Unit unit = world.spawnUnit(false);
		assertEquals(true, world.hasAsUnit(unit));
		assertEquals(true, world.getCube(unit.getCubePosition()[0], unit.getCubePosition()[1], 
				unit.getCubePosition()[2]).hasAsContent(unit));
	}
	
	
}
