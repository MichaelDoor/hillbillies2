package world;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import cube.Cube;
import faction.Faction;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import objects.Unit;
import position.PositionVector;


public class WorldTest {
	
	private World testWorld;
	//private static World world;
	
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
		//world = new World(matrix, new DefaultTerrainChangeListener());
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

//	@Test
//	public void constructor_LegalCase() {
//		int nbX = 10;
//		int nbY = 20;
//		int nbZ = 30;
//		int[][][] matrix = new int[nbX][nbY][nbZ];
//		matrix[0][0][0] = 1;
//		matrix[5][5][5] = 1;
//		matrix[4][5][5] = 1;
//		matrix[3][5][5] = 1;
//		matrix[2][5][5] = 1;
//		matrix[1][5][5] = 1;
//		matrix[0][5][5] = 1;
//
//		World world =  new World(matrix, new DefaultTerrainChangeListener());
//		assertEquals(nbX, world.getNbCubesX());
//		assertEquals(nbY, world.getNbCubesY());
//		assertEquals(nbZ, world.getNbCubesZ());
//		Cube solidCube = world.getCube(5, 5, 5);
//		assertEquals(true, solidCube.isSolid());
//		
//	}
//	
//	@Test
//	public void spawnUnit_worldFull() {
//		int i = 1;
//		while(i < 120){
//			testWorld.spawnUnit(false);
//			int nbOfUnits = testWorld.getUnitSet().size();
//			assertEquals(true, ((nbOfUnits == 100) || (nbOfUnits == i)));
//			i++;
//		}
//		assertEquals(100, testWorld.getUnitSet().size());
//	}
//	
//	@Test
//	public void randomStandingPosition() {
//		PositionVector position = testWorld.randomStandingPosition();
//		Cube cube = testWorld.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
//		boolean flag = testWorld.isValidStandingPosition(cube.getPosition());
//		assertEquals(true, flag);
//	}
//	
//	@Test
//	public void getAllAdjacents_LegalCase(){
//		PositionVector position = testWorld.randomStandingPosition();
//		int x = (int) position.getXArgument();
//		int y = (int) position.getYArgument();
//		int z = (int) position.getZArgument();
//		Set<PositionVector> adjacents = testWorld.getAllAdjacentPositions(position);
//		assertEquals(true, (adjacents.size() <= 26));
//		for(PositionVector adjacent : adjacents){
//			int x2 = (int) adjacent.getXArgument();
//			int y2 = (int) adjacent.getYArgument();
//			int z2 = (int) adjacent.getZArgument();
//			boolean flag = ((Math.abs(x-x2) <= 1) && (Math.abs(y-y2) <= 1) && (Math.abs(z-z2) <= 1));
//			assertEquals(true, flag);
//		}
//	}
//	
//	@Test
//	public void hasSolidAdjacent_True(){
//		Cube cube = null;
//		Random generator = new Random();
//		while((cube == null) || (! testWorld.isSolidConnectedToBorder(cube.getPosition()))){
//			int x = generator.nextInt(testWorld.getNbCubesX());
//			int y = generator.nextInt(testWorld.getNbCubesY());
//			int z = generator.nextInt(testWorld.getNbCubesZ());
//			cube = testWorld.getCube(x, y, z);
//		}
//		Cube solidCube = testWorld.getCube(5, 5, 5);
//		assertEquals(true, solidCube.isSolid());
//		assertEquals(true, testWorld.hasSolidAdjacent(solidCube));
//	}
//	
//	@Test
//	public void spawnUnit_LegalCase(){
//		Unit unit = world.spawnUnit(false);
//		assertEquals(true, world.hasAsUnit(unit));
//		assertEquals(true, world.getCube(unit.getCubePosition()[0], unit.getCubePosition()[1], 
//				unit.getCubePosition()[2]).hasAsContent(unit));
//	}
//	
//	public void fill(int[][][] matrix){
//		Random generator = new Random();
//		int x = matrix.length;
//		int y = matrix[0].length;
//		int z = matrix[0][0].length;
//		int types = 4;
//		int i = 0;
//		while(i < x){
//			int j = 0;
//			while(j < y){
//				int k = 0;
//				while(k < z){
//					matrix[i][j][k] = generator.nextInt(types);
//					k++;
//				}
//				j++;
//			}
//			i++;
//		}
//	}
//	
//	@Test @Ignore
//	public void constructor_80RandomFill(){
//		int nbX = 80;
//		int nbY = 80;
//		int nbZ = 80;
//		int[][][] matrix = new int[nbX][nbY][nbZ];
//
//		World world =  new World(matrix, new DefaultTerrainChangeListener());
//		assertEquals(nbX, world.getNbCubesX());
//		assertEquals(nbY, world.getNbCubesY());
//		assertEquals(nbZ, world.getNbCubesZ());
//	}
//	
//	@Test
//	public void spawnUnit_LegalCase2(){
//		Unit unit = testWorld.spawnUnit(false);
//		testWorld.advanceTime(0.19);
//	}
//	
//	@Test
//	public void randomSpawnPosition() {
//		testWorld.randomSpawnPosition();
//	}
	
	@Test @Ignore
	public void spawnUnit_RandomBehaviour() {
		testWorld.spawnUnit(true);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
	}
	
	@Test @Ignore
	public void moveToAdjacent(){
		Unit unit = new Unit(new PositionVector(0,0,0), "Ikke", new Faction());
		unit.changeWorld(testWorld);
		testWorld.addUnit(unit);
		PositionVector destination = new PositionVector(1,0,0);
		unit.moveToAdjacent(new PositionVector(1,0,0));
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		assertEquals(true, unit.getCubePositionVector().equals(destination));
	}
	
	@Test @Ignore
	public void correctSpawnPosition(){
		int nbX = 3;
		int nbY = 3;
		int nbZ = 3;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 1; matrix[1][0][0] = 1; matrix[2][0][0] = 1;
		matrix[0][1][0] = 1; matrix[1][1][0] = 1; matrix[2][1][0] = 1;
		matrix[0][2][0] = 1; matrix[1][2][0] = 1; matrix[2][2][0] = 1;
		
		matrix[0][0][1] = 1; matrix[1][0][1] = 1; matrix[2][0][1] = 1;
		matrix[0][1][1] = 1; matrix[1][1][1] = 0; matrix[2][1][1] = 0;
		matrix[0][2][1] = 1; matrix[1][2][1] = 1; matrix[2][2][1] = 1;
		
		matrix[0][0][2] = 0; matrix[1][0][2] = 0; matrix[2][0][2] = 0;
		matrix[0][1][2] = 0; matrix[1][1][2] = 0; matrix[2][1][2] = 0;
		matrix[0][2][2] = 0; matrix[1][2][2] = 0; matrix[2][2][2] = 0;
		
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		Unit unit = new Unit(new PositionVector(1,1,1), "Ikke", new Faction());
		unit.changeWorld(world2);
		world2.addUnit(unit);
		int[] unitCubePosition = unit.getCubePosition();
		Cube underneath = world2.getCube(unitCubePosition[0], unitCubePosition[1], unitCubePosition[2]-1);
		// unit occupies a passable cube
		assertEquals(false,world2.isSolidPosition(unit.getCubePositionVector()));
		// cube underneath unit is solid
		assertEquals(true,underneath.isSolid());
		// the cube that is occupied by unit contan unit
		assertEquals(true,world2.getCube(unitCubePosition[0], unitCubePosition[1], unitCubePosition[2]).getContent().contains(unit));
		unit.moveToAdjacent(new PositionVector(1,0,0));
		world2.advanceTime(5);
		assertEquals(true,unit.getCubePositionVector().equals(new PositionVector(2,1,1)));
		assertEquals(false,world2.getCube(unitCubePosition[0], unitCubePosition[1], unitCubePosition[2]).getContent().contains(unit));
		int[] newUnitCubePosition = unit.getCubePosition();
		assertEquals(true,world2.getCube(newUnitCubePosition[0], 
				newUnitCubePosition[1], newUnitCubePosition[2]).getContent().contains(unit));
	}
	
	@Test
	public void unitFalls(){
		int nbX = 3;
		int nbY = 3;
		int nbZ = 4;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 1; matrix[1][0][0] = 0; matrix[2][0][0] = 0;
		matrix[0][1][0] = 1; matrix[1][1][0] = 0; matrix[2][1][0] = 1;
		matrix[0][2][0] = 1; matrix[1][2][0] = 0; matrix[2][2][0] = 0;
		
		matrix[0][0][1] = 1; matrix[1][0][1] = 0; matrix[2][0][1] = 0;
		matrix[0][1][1] = 1; matrix[1][1][1] = 0; matrix[2][1][1] = 0;
		matrix[0][2][1] = 1; matrix[1][2][1] = 0; matrix[2][2][1] = 0;
		
		matrix[0][0][2] = 1; matrix[1][0][2] = 0; matrix[2][0][2] = 0;
		matrix[0][1][2] = 1; matrix[1][1][2] = 0; matrix[2][1][2] = 0;
		matrix[0][2][2] = 1; matrix[1][2][2] = 0; matrix[2][2][2] = 0;
		
		matrix[0][0][3] = 0; matrix[1][0][3] = 0; matrix[2][0][3] = 0;
		matrix[0][1][3] = 0; matrix[1][1][3] = 0; matrix[2][1][3] = 0;
		matrix[0][2][3] = 0; matrix[1][2][3] = 0; matrix[2][2][3] = 0;
		
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		Unit unit = new Unit(new PositionVector(0,1,3), "Ikke", new Faction());
		unit.changeWorld(world2);
		world2.addUnit(unit);
		int[] unitCubePosition = unit.getCubePosition();
		Cube underneath = world2.getCube(unitCubePosition[0], unitCubePosition[1], unitCubePosition[2]-1);
		// unit occupies a passable cube
		assertEquals(false,world2.isSolidPosition(unit.getCubePositionVector()));
		// cube underneath unit is solid
		assertEquals(true,underneath.isSolid());
		// the cube that is occupied by unit contains unit
		assertEquals(true,world2.getCube(unitCubePosition[0], unitCubePosition[1], unitCubePosition[2]).getContent().contains(unit));
		unit.moveToAdjacent(new PositionVector(1,0,0));
		world2.advanceTime(5);
		unit.moveToAdjacent(new PositionVector(1,0,0));
		world2.advanceTime(5);
		// the unit fell to this position
		assertEquals(true,unit.getCubePositionVector().equals(new PositionVector(2,1,1)));
		assertEquals(true, unit.getActivityStatus().equals("default"));
		assertEquals(true, unit.getCurrentHP() < unit.getMaxHP());
	}
}
