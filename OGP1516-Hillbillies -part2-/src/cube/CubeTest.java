package cube;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import position.PositionVector;

public class CubeTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void constructor_LegalCase() {
		Cube cube = new Cube(new PositionVector(0,0,0)) {
			
			@Override
			public int getTerrainType() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		assertEquals(true, cube.getPosition().equals(new PositionVector(0,0,0)));
		assertEquals(true, cube.getContent().isEmpty());

	}
	
	@Test (expected =  IllegalArgumentException.class)
	public void constructor_IllegalCase() throws IllegalArgumentException {
		new Cube(new PositionVector(0.5, 1.3, 2.6)) {
			
			@Override
			public int getTerrainType() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}

}
