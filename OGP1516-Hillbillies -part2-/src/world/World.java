package world;

import java.util.*;

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
 * @invar  The unit set of each world must be a valid unit set for any
 *         world.
 *       | isValidUnitSet(getUnitSet())
 * @invar  The object set of each world must be a valid object set for any
 *         world.
 *       | isValidObjectSet(getObjectSet())
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
	 * @effect The unit set of this new world is set to an empty hash set.
	 * @effect The material set of this new world is set to an empty hash set.
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
		this.setUnitSet(new HashSet<Unit>());
		this.setMaterialSet(new HashSet<Material>());
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
	 * Return a cube with a given position, content and terrain type, for a given position, content and terrain number.
	 * @param cubePosition	The given position.
	 * @param content	The given content.
	 * @param terrainNb	The given terrain number.
	 * @return	An air, rock, tree or workshop cube, depending on the given terrain number, having the given position as its position
	 * and the given content as it's content. When no valid terrain number is given, air is returned.
	 */
	@Raw @Model
	private Cube mapCube(PositionVector cubePosition, ArrayList<GameObject> content, int terrainNb) {
		Cube cube = null;
		if (terrainNb == 0)
			cube = new Air(cubePosition, content);
		if (terrainNb == 1)
			cube = new Rock(cubePosition, content);
		if (terrainNb == 2)
			cube = new Tree(cubePosition, content);
		if (terrainNb == 3)
			cube = new Workshop(cubePosition, content);
		else
			cube = new Air(cubePosition, content);
		return cube;
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
		return this.mapCube(cubePosition, new ArrayList<GameObject>(), terrainNb);
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
	 * Return the terrain type of the cube at a given position.
	 * @param x	The given x component of the targeted cube.
	 * @param y The given y component of the targeted cube.
	 * @param z The given z component of the targeted cube.
	 * @return	The terrain type of the cube at the given position.
	 */
	public int getCubeType(int x, int y, int z){
		return this.getCube(x, y, z).getTerrainType();
	}
	
	/**
	 * Set the cube of which the coordinates are given, to a given terrain type.
	 * @param x	The given x component of the targeted cube.
	 * @param y The given y component of the targeted cube.
	 * @param z The given z component of the targeted cube.
	 * @param terrainType	The given terrain type.
	 * @effect	The old cube is replaced by a new cube with the same position and the given terrain type.
	 * @throws IllegalArgumentException
	 * 			The given coordinates are outside of this world.
	 */
	public void setCubeType(int x, int y, int z, int terrainType) throws IllegalArgumentException {
		if(! this.isValidPosition(new PositionVector(x, y, z)))
			throw new IllegalArgumentException("Position not in this world!");
		Cube oldCube = this.getCube(x, y, z);
		this.replaceCube(this.mapCube(oldCube.getPosition(), oldCube.getContent(), terrainType));
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
	 * @note	By propagating the cave-in, than rather making the whole terrain of this world valid again, this method has a better
	 * 			performance.
	 */
	public void caveIn(int x, int y, int z) throws IllegalStateException, IllegalArgumentException {
		PositionVector position = new PositionVector(x, y, z);
		Cube cube = this.getCube(x,y,z);
		if(! cube.isSolid())
			throw new IllegalStateException("This cube is passable and thus cannot cave-in!");
		ArrayList<GameObject> content = cube.getContent();
		int terrainType = cube.getTerrainType();
		cube = new Air(position, content);
		if (caveInItemCheck() == true){
			Material item = this.caveInItem(position, terrainType);
			cube.addAsContent(item);
			this.addMaterial(item);
		}
		this.replaceCube(cube);
		this.propagateCaveIn(cube.getPosition());
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
	 * @return	A boulder with the given position as its position, if the given terrain type is 1 or 
	 * log with the given position as its position, if the given terrain type is 2.
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
	 * Makes a cave-in of a cube at a given position propagate to its direct adjacent cubes,
	 * resulting in possible cave-ins of directly adjacent solid cubes.
	 * @param position	The given position.
	 * @effect	Gets all directly adjacent cubes of the cube at the given position. Keeps the ones that are solid and checks them to
	 * verify if they's connected to a border of the game world through other solid cubes or by itself. If not, they're caved-in.
	 * @throws NullPointerException
	 * 			The given position is not effective.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	private void propagateCaveIn(PositionVector position) throws NullPointerException, IllegalArgumentException {
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("Position not located in this world!");
		Set<Cube> directAdjacents = this.getDirectAdjacentCubes(this.getCube((int) position.getXArgument(), 
				(int) position.getYArgument(), (int) position.getZArgument()));
		for(Cube adjacent : directAdjacents) {
			if(! adjacent.isSolid())
				directAdjacents.remove(adjacent);
			if(! this.connectedToBorder.isSolidConnectedToBorder((int) adjacent.getPosition().getXArgument(),
					(int) adjacent.getPosition().getYArgument(), (int) adjacent.getPosition().getZArgument()))
				this.caveIn((int) adjacent.getPosition().getXArgument(),
					(int) adjacent.getPosition().getYArgument(), (int) adjacent.getPosition().getZArgument());
		}
	}
	
	/**
	 * Return a set of all direct adjacent cubes of a given cube.
	 * @param cube	The given cube.
	 * @return	All the cubes that are directly adjacent to the given cube and that have a valid position for this world.
	 * @throws IllegalArgumentException
	 * 			The given cube is not located in this world.
	 * @throws NullPointerException
	 * 			The given cube is not effective.
	 */
	private Set<Cube> getDirectAdjacentCubes(Cube cube) throws IllegalArgumentException, NullPointerException {
		if(! this.isValidPosition(cube.getPosition()))
			throw new IllegalArgumentException("Given cube not located in this world");
		int x = (int) cube.getPosition().getXArgument();
		int y = (int) cube.getPosition().getYArgument();
		int z = (int) cube.getPosition().getZArgument();
		Set<Cube> directAdjacents = new HashSet<Cube>();
		PositionVector[] allDirectAdjacents = {new PositionVector(x+1,y,z), new PositionVector(x-1,y,z), new PositionVector(x,y+1,z),
				new PositionVector(x,y-1,z), new PositionVector(x,y,z+1), new PositionVector(x,y,z-1)};
		for(PositionVector position : allDirectAdjacents){
			if(this.isValidPosition(position))
				directAdjacents.add(this.getCube((int) position.getXArgument(), (int) position.getYArgument(),
						(int) position.getZArgument()));
		}
		return directAdjacents;
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
	public Set<Unit> getUnitSet() {
		return this.unitSet;
	}
	
	/**
	 * Check whether the given unit set is a valid unit set for
	 * any world.
	 *  
	 * @param  unitSet
	 *         The unit set to check.
	 * @return 
	 *       | result == (unitSet != null)	
	*/
	public static boolean isValidUnitSet(Set<Unit> unitSet) {
		return (unitSet != null);
	}
	
	/**
	 * Set the unit set of this world to the given unit set.
	 * 
	 * @param  unitSet
	 *         The new unit set for this world.
	 * @post   The unit set of this new world is equal to
	 *         the given unit set.
	 *       | new.getUnitSet() == unitSet
	 * @throws NullPointerException
	 *         The given unit list is not a valid unit set for any
	 *         world.
	 *       | ! isValidUnitSet(getUnitSet())
	 */
	@Raw
	public void setUnitSet(Set<Unit> unitSet) 
			throws NullPointerException {
		if (! isValidUnitSet(unitSet))
			throw new NullPointerException();
		this.unitSet = unitSet;
	}
	
	/**
	 * Variable registering the unit set of this world.
	 */
	private Set<Unit> unitSet;
	
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
			this.getUnitSet().add(unit);
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
		PositionVector position = Unit.centrePosition(this.randomStandingPosition());
		String name = "Unit" + this.getUnitSet().size();
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
	
	/**
	 * Return a position at which a game object can stand.
	 * @return	A position that is a valid standing position for a game object.
	 */
	public PositionVector randomStandingPosition() {
		PositionVector position = this.randomPosition();
		while(! isValidStandingPosition(position)){
			position = this.randomPosition();
		}
		return position;
	}
	
	/**
	 * Check whether a game object could stand at the given position.
	 * @param position	The given position.
	 * @return	True, if and only if the cube at the given position is passable and either the adjacent cube underneath it is solid
	 * or the cube has z = 0.
	 * @throws	NullPointerException
	 * 			The given position is not effective.
	 * @throws	IllegalArgumentException
	 * 			The given position is not located in this world.
	 */
	private boolean isValidStandingPosition(PositionVector position) throws NullPointerException, IllegalArgumentException {
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("position is not located in this world!");
		Cube cube = this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
		if(cube.isSolid())
			return false;
		if(position.getZArgument() == 0)
			return true;
		if(this.hasSolidUnderneath(cube))
			return true;
		else
			return false;
	}
	
	/**
	 * Check whether the cube underneath a given cube is solid.
	 * @param cube	The given cube.
	 * @return	True if and only if the cube directly underneath the given cube is solid.
	 * @throws NullPointerException
	 * 			The given cube is not effective.
	 * @throws IllegalArgumentException
	 * 			The given cube has a position outside of this world.
	 * @throws	IllegalArgumentException
	 * 			The given cube is located at z = 0.
	 */
	private boolean hasSolidUnderneath(Cube cube) throws NullPointerException, IllegalArgumentException {
		if(! this.isValidPosition(cube.getPosition()))
			throw new IllegalArgumentException("Position of cube is invalid!");
		if(! (cube.getPosition().getZArgument() == 0))
			throw new IllegalArgumentException("There is no cube underneath the given cube!");
		int x = (int) cube.getPosition().getXArgument();
		int y = (int) cube.getPosition().getYArgument();
		int z = (int) cube.getPosition().getZArgument();
		int z2 = z - 1;
		return this.getCube(x, y, z2).isSolid();
		
		
	}
	
	/**
	 * Check whether a given position is located within this world.
	 * @param position	The given position.
	 * @return	True is and only if all components are greater then or equal to zero and smaller than or equal to the number of cubes
	 * in this world along the respective component direction.
	 */
	private boolean isValidPosition(PositionVector position) {
		double x = position.getXArgument();
		double y = position.getYArgument();
		double z = position.getZArgument();
		if((x <= this.getNbCubesX()) && (y <= this.getNbCubesY()) && (z <= this.getNbCubesZ()) && (x >= 0) && (y >= 0) && (z >= 0))
			return true;
		else
			return false;
	}
	
	
	/**
	 * Return the material set of this world.
	 */
	@Basic @Raw
	public Set<Material> getMaterialSet() {
		return this.materialSet;
	}
	
	/**
	 * Check whether the given material set is a valid material set for
	 * any world.
	 *  
	 * @param  material set
	 *         The material set to check.
	 * @return 
	 *       | result == (materialSet != null)
	*/
	public static boolean isValidMaterialSet(Set<Material> materialSet) {
		return (materialSet != null);
	}
	
	/**
	 * Set the material set of this world to the given material set.
	 * 
	 * @param  materialSet
	 *         The new material set for this world.
	 * @post   The material set of this new world is equal to
	 *         the given material set.
	 *       | new.getMaterialSet() == materialSet
	 * @throws NullPointerException
	 *         The given material set is not a valid material set for any
	 *         world.
	 *       | ! isValidMaterialSet(getMaterialSet())
	 */
	@Raw
	public void setMaterialSet(Set<Material> materialSet) 
			throws NullPointerException {
		if (! isValidMaterialSet(materialSet))
			throw new NullPointerException();
		this.materialSet = materialSet;
	}
	
	/**
	 * Variable registering the material set of this world.
	 */
	private Set<Material> materialSet;
	
	/**
	 * Add a given material to this world.
	 * @param material	The given material.
	 * @effect	The given material is added to the material set of this world.
	 */
	private void addMaterial(Material material){
		this.getMaterialSet().add(material);
	}
	
	/**
	 * Advance the time for this world by a given amount of time.
	 * @param dt	The given amount of time.
	 * @effect	Time is advanced with the given amount of time for all units and and materials of this world.
	 * @throws	IllegalArgumentException
	 * 			Time is either negative or equal to or greater then 0.2s.
	 */
	public void advanceTime(double dt)throws IllegalArgumentException {
		if ((dt < 0) || (dt >= 0.2)) 
			throw new IllegalArgumentException();
		for(Unit unit : this.getUnitSet())
			unit.advanceTime(dt);
		for(Material material : this.getMaterialSet())
			material.advanceTime(dt);
	}
	
}
