package world;

import java.util.ArrayList;
import java.util.Random;

import be.kuleuven.cs.som.annotate.*;
import cube.*;
import hillbillies.part2.listener.*;
import hillbillies.util.*;
import objects.*;
import position.PositionVector;

/**
 * A class of worlds.
 * 
 * @invar  The terrain matrix of each world must be a valid terrain matrix for any
 *         world.
 *       | isValidTerrainMatrix(getTerrainMatrix())
 * @invar  The cube matrix of this world must be a valid cube matrix for this
 *         world.
 *       | isValidCubeMatrix(getCubeMatrix())
 * @invar  Each cube can have its connected to border checker as connected to border checker.
 *       | canHaveAsConnectedToBorder(this.getConnectedToBorder())
 * @invar  The number of units of each world must be a valid number of units for any
 *         world.
 *       | isValidNumberOfUnits(getNumberOfUnits())
 * @invar  The unit list of each world must be a valid unit list for any
 *         world.
 *       | isValidUnitList(getUnitList())
 * @author Michaël
 * @version 0.6
 */
public class World {
	
	/**
	 * Initialize this new world with given terrain matrix.
	 *
	 * @param  terrainTypes
	 *         The terrain matrix for this new world.
	 * @param	modelListener 
	 * 			The terrainChangeListener for this new world.
	 * @effect The terrain matrix of this new world is set to
	 *         the given terrain matrix.
	 *       | this.setTerrainMatrix(terrainTypes)
	 * @post	The modelListener of this new world equals the given modelListener.
	 * 			| this.modelListener.equals(modelListener)
	 * @effect	The cube matrix of this world is initialized.
	 * @effect	Initializes this world's connected to border checker.
	 * @effect	This world's terrain is made valid.
	 * @effect The number of units of this new world is set to 0.
	 * @effect The unit list of this new world is set to an empty array list.
	 */
	public World(int[][][] terrainTypes, TerrainChangeListener modelListener)
			throws NullPointerException {
		this.setTerrainMatrix(terrainTypes);
		this.modelListener = modelListener;
		this.initializeCubeMatrix();
		this.connectedToBorder = new ConnectedToBorder(this.getNbCubesX(), this.getNbCubesY(), this.getNbCubesZ());
		this.initializeConnectedToBorder();
		this.makeValidTerrain();
		this.setNumberOfUnits(0);
		this.setUnitList(new ArrayList<Unit>());
	}
	
	
	/**
	 * Return the terrain matrix of this world.
	 */
	@Basic @Raw
	public int[][][] getTerrainMatrix() {
		return this.terrainTypes;
	}
	
	/**
	 * Check whether the given terrain matrix is a valid terrain matrix for
	 * any world.
	 *  
	 * @param  terrain matrix
	 *         The terrain matrix to check.
	 * @return 
	 *       | result == (terrainTypes != null)
	*/
	public static boolean isValidTerrainMatrix(int[][][] terrainTypes) {
		return (terrainTypes != null);
	}
	
	/**
	 * Set the terrain matrix of this world to the given terrain matrix.
	 * 
	 * @param  terrainTypes
	 *         The new terrain matrix for this world.
	 * @post   The terrain matrix of this new world is equal to
	 *         the given terrain matrix.
	 *       | new.getTerrainMatrix() == terrainTypes
	 * @throws NullPointerException
	 *         The given terrain matrix is not a valid terrain matrix for any
	 *         world.
	 *       | ! isValidTerrainMatrix(getTerrainMatrix())
	 */
	@Raw
	public void setTerrainMatrix(int[][][] terrainTypes) 
			throws NullPointerException {
		if (! isValidTerrainMatrix(terrainTypes))
			throw new NullPointerException();
		this.terrainTypes = terrainTypes;
	}
	
	/**
	 * Variable registering the terrain matrix of this world.
	 */
	private int[][][] terrainTypes;
	
	/**
	 * Return the number of cubes this world has in the x direction.
	 * @return	The x length of this world's terrain matrix.
	 */
	public int getNbCubesX() {
		return this.getTerrainMatrix().length;
	}
	
	/**
	 * Return the number of cubes this world has in the y direction.
	 * @return	The y length of this world's terrain matrix.
	 */
	public int getNbCubesY() {
		return this.getTerrainMatrix()[0].length;
	}
	
	/**
	 * Return the number of cubes this world has in the z direction.
	 * @return	The z length of this world's terrain matrix.
	 */
	public int getNbCubesZ() {
		return this.getTerrainMatrix()[0][0].length;
	}
	
	private TerrainChangeListener modelListener;
	
	/**
	 * Return the cube matrix of this world.
	 */
	@Basic @Raw
	public Cube[][][] getCubeMatrix() {
		return this.cubeMatrix;
	}
	
	/**
	 * Check whether the given cube matrix is a valid cube matrix for
	 * any world.
	 *  
	 * @param  cube matrix
	 *         The cube matrix to check.
	 * @return 
	 *       | result == (cubeMatrix != null) && (cubeMatrix.length == this.getNbCubesX()
	 *       				&& (cubeMatrix[0].length == this.getNbCubesY() && (cubeMatrix[0][0].length == this.getNbCubesZ()))
	*/
	public boolean isValidCubeMatrix(Cube[][][] cubeMatrix) {
		return (cubeMatrix != null) && (cubeMatrix.length == this.getNbCubesX()
				&& (cubeMatrix[0].length == this.getNbCubesY()) && (cubeMatrix[0][0].length == this.getNbCubesZ()));
	}
	
	/**
	 * Set the cube matrix of this world to the given cube matrix.
	 * 
	 * @param  cubeMatrix
	 *         The new cube matrix for this world.
	 * @post   The cube matrix of this new world is equal to
	 *         the given cube matrix.
	 *       | new.getCubeMatrix() == cubeMatrix
	 * @throws NullPointerException
	 *         The given cube matrix is not a valid cube matrix for any
	 *         world.
	 *       | ! isValidCubeMatrix(getCubeMatrix())
	 */
	@Raw
	public void setCubeMatrix(Cube[][][] cubeMatrix) 
			throws NullPointerException {
		if (! isValidCubeMatrix(cubeMatrix))
			throw new NullPointerException();
		this.cubeMatrix = cubeMatrix;
	}
	
	/**
	 * Initialize the cube matrix of this world.
	 * @effect	This world's cube matrix is created and a type of cube is mapped to each cell of it, according to each element 
	 * of this world's terrain matrix.
	 */
	@Raw
	private void initializeCubeMatrix() {
		int[][][] terrainMatrix = this.getTerrainMatrix();
		Cube[][][] cubeMatrix = 
				new Cube[this.getNbCubesX()][this.getNbCubesY()][this.getNbCubesZ()];
		int x = 0;
		while (x < this.getNbCubesX()) {
			int y = 0;
			while (y < this.getNbCubesY()) {
				int z = 0;
				while (z < this.getNbCubesZ()) {
					cubeMatrix[x][y][z] = this.mapCube(new PositionVector(x,y,z), terrainMatrix[x][y][z]);
					z++;
				}
				y++;
			}
			x++;
		}
		this.setCubeMatrix(cubeMatrix);
	}
	
	/**
	 * Return a cube with a given position, being a specific terrain type cube, for a given position and terrain number.
	 * @param cubePosition	The given position.
	 * @param terrainNb	The given terrain number.
	 * @return	An air, rock, tree or workshop cube, depending on the given terrain number, having the given position as its position.
	 * When no valid terrain number is given, air is returned.
	 */
	@Raw @Model
	private Cube mapCube(PositionVector cubePosition, int terrainNb) {
		Cube cube = null;
		if (terrainNb == 0)
			cube = new Air(cubePosition);
		if (terrainNb == 1)
			cube = new Rock(cubePosition);
		if (terrainNb == 2)
			cube = new Tree(cubePosition);
		if (terrainNb == 3)
			cube = new Workshop(cubePosition);
		else
			cube = new Air(cubePosition);
		return cube;
	}
	
	/**
	 * Variable registering the cube matrix of this world.
	 */
	private Cube[][][] cubeMatrix;
	
	/**
	 * Return the cube at a given position.
	 * @param x	The given x coordinate of the targeted cube.
	 * @param y	The given y coordinate of the targeted cube.
	 * @param z	The given z coordinate of the targeted cube.
	 * @return	The cube at the give position.
	 * @throws	IllegalArgumentException
	 * 			The given coordinates are out of the bounds of this world.
	 */
	private Cube getCube(int x, int y, int z) throws IllegalArgumentException {
		if ((x < 0) || (y < 0) || (z <0) || (x > this.getNbCubesX()) || (y > this.getNbCubesY()) || (z > this.getNbCubesZ()))
				throw new IllegalArgumentException("Coordinates out of bounds!");
		return this.getCubeMatrix()[x][y][z];
	}
	
	/**
	 * Initialize this world's connected to border checker, making it match this world's cube matrix on the aspect of solid 
	 * and non-solid cubes.
	 * @effect	Iterates over this world's cube matrix and changes all cells in connectedToBorder to passable, where the respective
	 * cube is passable.
	 */
	private void initializeConnectedToBorder() {
		Cube[][][] cubeMatrix = this.getCubeMatrix();
		int x = 0;
		while(x < this.getNbCubesX()){
			int y = 0;
			while(y < this.getNbCubesY()){
				int z = 0;
				while(z < this.getNbCubesZ()){
					if (! cubeMatrix[x][y][z].isSolid())
						this.connectedToBorder.changeSolidToPassable(x, y, z);
					z++;
				}
				y++;
			}
			x++;
		}
	}
	
	/**
	 * Return the connected to border checker of this cube.
	 */
	@Basic @Raw @Immutable
	private ConnectedToBorder getConnectedToBorder() {
		return this.connectedToBorder;
	}
	
	/**
	 * Check whether this cube can have the given connected to border checker as its connected to border checker.
	 *  
	 * @param  connectedToBorder
	 *         The connected to border checker to check.
	 * @return 
	 *       | result == (connectedToBorder != null)
	*/
	@Raw
	private boolean canHaveAsConnectedToBorder(ConnectedToBorder connectedToBorder) {
		return (connectedToBorder != null);
	}
	
	/**
	 * Variable registering the connected to border checker of this cube.
	 */
	private final ConnectedToBorder connectedToBorder;
	
	/**
	 * Check this world's terrain for solid cubes that are not connected to a border and make the cave-in.
	 * @effect	Every solid cube in this world's cube matrix is checked to see if it's connected to a border. If not, it's caves-in.
	 */
	private void makeValidTerrain() {
		Cube[][][] cubeMatrix = this.getCubeMatrix();
		int x = 0;
		while(x < this.getNbCubesX()){
			int y = 0;
			while(y < this.getNbCubesY()){
				int z = 0;
				while(z < this.getNbCubesZ()){
					if(cubeMatrix[x][y][z].isSolid())
						if(this.connectedToBorder.isSolidConnectedToBorder(x, y, z))
							this.caveIn(x,y,z);
					z++;
				}
				y++;
			}
			x++;
		}
	}
	
	/**
	 * Makes the cube at the given position cave-in.
	 * @param x	The x coordinate of the targeted cube.
	 * @param y	The y coordinate of the targeted cube.
	 * @param z	The z coordinate of the targeted cube.
	 * @effect	Creates a new air cube, injects the content of the old cube into it and adds the item that spawns by the cave-in
	 * (if any is spawned) to it's content and finally replaces the old cube with the new air cube and let's this world make its
	 * terrain a valid terrain.
	 * @throws	IllegalStateException
	 * 			The targeted cube is passable.
	 * @throws	IllegalArgumentException
	 * 			The given position is out of bounds.
	 */
	public void caveIn(int x, int y, int z) throws IllegalStateException, IllegalArgumentException {
		PositionVector position = new PositionVector(x, y, z);
		Cube cube = this.getCube(x,y,z);
		if(! cube.isSolid())
			throw new IllegalStateException("This cube is passable and thus cannot cave-in!");
		ArrayList<GameObject> content = cube.getContent();
		int terrainType = cube.getTerrainType();
		cube = new Air(position, content);
		if (caveInItemCheck() == true)
			cube.addAsContent(this.caveInItem(position, terrainType));
		this.replaceCube(cube);
		this.makeValidTerrain();
	}
	
	/**
	 * Return whether by chance an item is spawned as a result of a cave-in.
	 * @return	0.25 chance for true.
	 */
	@Model @Raw
	private boolean caveInItemCheck() {
		boolean flag = false;
		Random generator = new Random();
		if (generator.nextInt(4) == 1)
			flag = true;
		return flag;
	}
	
	/**
	 * Return an item with a given position, the kind of item depends on the given terrain type.
	 * @param position	The given position.
	 * @param terrainType	The given terrain type.
	 * @return	A boulder with the given position as its position, if the given terrain type is 1.
	 * @return	A log with the given position as its position, if the given terrain type is 2.
	 * @throws IllegalArgumentException
	 * 			The given terrain type is neither 1 or 2.
	 * @throws	NullPointerException
	 * 			The given position is not effective.
	 */
	@Model @Raw
	private Material caveInItem(PositionVector position, int terrainType) throws IllegalArgumentException, NullPointerException {
		if(terrainType == 1)
			return new Boulder(position);
		if(terrainType == 2)
			return new Log(position);
		else
			throw new IllegalArgumentException("This terrain type doesn't spawn anything, when it collapses!");
	}
	
	/**
	 * Let the given new cube replace the old cube that is now occupying the new cube's position.
	 * @param newCube	The given new cube.
	 * @effect	The old cube at the new cube's position is replaced by the new cube in this world's cube matrix.
	 * @effect	The terrain type of the old cube is replaced by that of the new cube in this world's terrain matrix.
	 * @effect	Notifies this world's model listener that the terrain has changed.
	 * @throws NullPointerException
	 * 			The given new cube is not effective.
	 */
	@Model
	private void replaceCube(Cube newCube) throws NullPointerException {
		int x = (int) newCube.getPosition().getXArgument();
		int y = (int) newCube.getPosition().getYArgument();
		int z = (int) newCube.getPosition().getZArgument();
		this.getCubeMatrix()[x][y][z] = newCube;
		this.getTerrainMatrix()[x][y][z] = newCube.getTerrainType();
		this.modelListener.notifyTerrainChanged(x, y, z);
	}
	
	
	/**
	 * Return the number of units of this world.
	 */
	@Basic @Raw
	public int getNumberOfUnits() {
		return this.numberOfUnits;
	}
	
	/**
	 * Check whether the given number of units is a valid number of units for
	 * any world.
	 *  
	 * @param  number of units
	 *         The number of units to check.
	 * @return 
	 *       | result == ((numberOfUnits <= maxNumberOfUnits) && (numberOfUnits >= 0))
	*/
	public static boolean isValidNumberOfUnits(int numberOfUnits) {
		return ((numberOfUnits <= maxNumberOfUnits) && (numberOfUnits >= 0));
	}
	
	/**
	 * Set the number of units of this world to the given number of units.
	 * 
	 * @param  numberOfUnits
	 *         The new number of units for this world.
	 * @post   The number of units of this new world is equal to
	 *         the given number of units.
	 *       | new.getNumberOfUnits() == numberOfUnits
	 * @throws IllegalArgumentException
	 *         The given number of units is not a valid number of units for any
	 *         world.
	 *       | ! isValidNumberOfUnits(getNumberOfUnits())
	 */
	@Raw
	public void setNumberOfUnits(int numberOfUnits) 
			throws IllegalArgumentException {
		if (! isValidNumberOfUnits(numberOfUnits))
			throw new IllegalArgumentException();
		this.numberOfUnits = numberOfUnits;
	}
	
	/**
	 * Variable registering the number of units of this world.
	 */
	private int numberOfUnits;
	
	/**
	 * Variable registering the maximum number of units allowed in any world.
	 */
	private static int maxNumberOfUnits = 100;
	
	
	/**
	 * Return the unit list of this world.
	 */
	@Basic @Raw
	public ArrayList<Unit> getUnitList() {
		return this.unitList;
	}
	
	/**
	 * Check whether the given unit list is a valid unit list for
	 * any world.
	 *  
	 * @param  unit list
	 *         The unit list to check.
	 * @return 
	 *       | result == (unitList != null)	
	*/
	public static boolean isValidUnitList(ArrayList<Unit> unitList) {
		return (unitList != null);
	}
	
	/**
	 * Set the unit list of this world to the given unit list.
	 * 
	 * @param  unitList
	 *         The new unit list for this world.
	 * @post   The unit list of this new world is equal to
	 *         the given unit list.
	 *       | new.getUnitList() == unitList
	 * @throws NullPointerException
	 *         The given unit list is not a valid unit list for any
	 *         world.
	 *       | ! isValidUnitList(getUnitList())
	 */
	@Raw
	public void setUnitList(ArrayList<Unit> unitList) 
			throws NullPointerException {
		if (! isValidUnitList(unitList))
			throw new NullPointerException();
		this.unitList = unitList;
	}
	
	/**
	 * Variable registering the unit list of this world.
	 */
	private ArrayList<Unit> unitList;
	
	/**
	 * Add a given unit to this world.
	 * @param unit	The given unit.
	 * @throws IllegalStateException
	 * 			This world can't contain any more units.
	 */
	public void addUnit(Unit unit) throws IllegalStateException {
		try{
			if(maxNumberOfUnits == 100)
				throw new IllegalStateException();
			this.getUnitList().add(unit);
			int[] cubePosition = unit.getCubePosition();
			this.getCube(cubePosition[0], cubePosition[1], cubePosition[2]).addAsContent(unit);
		}
		catch (IllegalStateException exc) {
			
		}
	}
	
	/**
	 * Spawn a unit with a random position and random attribute values.
	 * @param enableDefaultBehaviour	The status of the default behaviour of the to be spawned unit.
	 * @effect	A random center cube position is generated, a name according to the number units in this world is generated.
	 * 			A unit with this generated name and position and with random attribute values is initialized and added to this world.
	 */
	public void spawnUnit(boolean enableDefaultBehaviour) {
		PositionVector position = Unit.centrePosition(this.randomPosition());
		String name = "Unit" + this.getUnitList().size();
		Unit unit = new Unit(position, name);
		if(enableDefaultBehaviour == true)
			unit.startDefaultBehaviour();
		this.addUnit(unit);
	}
	
	/**
	 * Return a random position (integer coordinates) in this world.
	 * @return	A random position in integer coordinates, located in this world.
	 */
	public PositionVector randomPosition() {
		Random generator = new Random();
		int x = generator.nextInt(this.getNbCubesX());
		int y = generator.nextInt(this.getNbCubesY());
		int z = generator.nextInt(this.getNbCubesZ());
		return new PositionVector(x,y,z);
	}
	
}
