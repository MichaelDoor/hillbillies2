package hillbillies.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.part2.listener.*;
import hillbillies.util.*;





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
 * @invar  The faction set of each world must be a valid faction set for any
 *         world.
 *       | isValidFactionSet(getFactionSet())
 * @author Micha�l
 * @version 0.7
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
	 * @effect The faction set of this new world is set to a new hash set.
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
		this.setFactionSet(new HashSet<>());
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
	private Cube mapCube(PositionVector cubePosition, HashSet<GameObject> content, int terrainNb) {
		Cube cube = null;
		if (terrainNb == 0)
			cube = new Air(cubePosition, content);
		else if (terrainNb == 1)
			cube = new Rock(cubePosition, content);
		else if (terrainNb == 2)
			cube = new Tree(cubePosition, content);
		else if (terrainNb == 3)
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
		return this.mapCube(cubePosition, new HashSet<GameObject>(), terrainNb);
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
	public Cube getCube(int x, int y, int z) throws IllegalArgumentException {
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
	 * @throws	IllegalArgumentException
	 * 			The given coordinates are out of the bounds of this world.
	 */
	public int getCubeType(int x, int y, int z) throws IllegalArgumentException {
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
					if (! cubeMatrix[x][y][z].isSolid()){
						this.connectedToBorder.changeSolidToPassable(x, y, z);
					}
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
						if(! this.connectedToBorder.isSolidConnectedToBorder(x, y, z))
							this.caveIn(x,y,z);
					z++;
				}
				y++;
			}
			x++;
		}
	}
	
	/**
	 * Collapses the cube at a given position, letting it change in air and dropping a material.
	 * @param position	The given position.
	 * @effect	The cube at the given position caves-in, if there is no material spawned by the cave-in, a material is added in the
	 * 			the center of the cube, a log if the cube was a tree, a boulder if the cube was a rock.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position or the cube at the given position is not solid.
	 */
	public void collapse(PositionVector	position) throws IllegalArgumentException {
		if((! this.isValidPosition(position)) || (! this.isSolidPosition(position)))
			throw new IllegalArgumentException("The given position is not a valid position for this world "
					+ "or the cube at it is not solid!");
		int x = (int) position.getXArgument();
		int y = (int) position.getYArgument();
		int z = (int) position.getZArgument();
		int terrain = this.getCubeType(x,y,z);
		this.caveIn(x, y, z);
		Cube newCube = this.getCube(x, y, z);
		if((terrain == 1) &&(! newCube.containsBoulder()))
			newCube.addAsContent(new Boulder(PositionVector.centrePosition(new PositionVector(x, y, z))));
		if((terrain == 2) &&(! newCube.containsLog()))
			newCube.addAsContent(new Log(PositionVector.centrePosition(new PositionVector(x, y, z))));	
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
		HashSet<GameObject> content = cube.getContent();
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
	 * @effect	If the unit is from a faction that does not exist in this world, this faction is added to this world. the unit is added
	 * 			to this world's unit set and to the cube corresponding to its position.
	 * @throws	IllegalArgumentException
	 * 			This world can not have the given unit as one of it's units.
	 * @throws IllegalStateException
	 * 			The given unit's faction is not a valid faction for this world.
	 */
	public void addUnit(Unit unit) throws IllegalArgumentException {
		try{
			if(! this.canHaveAsUnit(unit))
				throw new IllegalArgumentException();
			if(! this.hasAsFaction(unit.getFaction()))
				this.addFaction(unit.getFaction());
			this.getUnitSet().add(unit);
			int[] cubePosition = unit.getCubePosition();
			this.getCube(cubePosition[0], cubePosition[1], cubePosition[2]).addAsContent(unit);
		}
		catch (IllegalArgumentException exc){
			
		}
		catch (IllegalStateException exc){
			
		}
	}
	
	/**
	 * Remove a given unit from this world.
	 * @param unit	The given unit.
	 * @effect	The given unit is removed from this world's unit set and from this world's cube that had it as content.
	 * @throws	IllegalArgumentException
	 * 			This world does not have the given unit in it's unit set.
	 * @throws	NullPointerException
	 * 			The given unit is not effective.
	 */
	public void removeUnit(Unit unit) throws NullPointerException {
		if(unit == null)
			throw new NullPointerException();
		if(! this.hasAsUnit(unit))
			throw new IllegalArgumentException("This world does not have the given unit as one of its units");
		PositionVector unitCubePosition = unit.getCubePositionVector();
		this.getUnitSet().remove(unit);
		this.getCube((int) unitCubePosition.getXArgument(), (int) unitCubePosition.getYArgument(),
				(int) unitCubePosition.getZArgument()).removeAsContent(unit);
	}
	
	/**
	 * Check whether this world has the given unit.
	 * @param unit	The given unit.
	 * @return	True if and only if this world's unit set contains the given unit.
	 * @throws	NullPointerException
	 * 			The given unit is not effective.
	 */
	public boolean hasAsUnit(Unit unit) throws NullPointerException {
		if(unit == null)
			throw new NullPointerException();
		return this.getUnitSet().contains(unit);
	}
	
	/**
	 * Check whether this world can have the given unit as a unit.
	 * @param unit	The given unit.
	 * @return	True if and only if the unit's position is in this world and the unit is not already in this world and this world
	 * 			has not yet reached it's maximum amount of allowed units and when this world has reached its maximum amount
	 * 			of allowed faction, the given unit does not have a faction that's not in this world, and the given unit's
	 * 			cube position refers to a passable cube in this world.
	 * @throws 	NullPointerException
	 * 			The given unit is not effective.
	 */
	public boolean canHaveAsUnit(Unit unit) throws NullPointerException {
		if(unit == null)
			throw new NullPointerException();
		boolean flag1 = (this.isValidPosition(unit.getUnitPosition()));
		boolean flag2 = (! this.hasAsUnit(unit));
		boolean flag3 = (this.getUnitSet().size() < maxNumberOfUnits);
		boolean flag4 = (!((this.getFactionSet().size() > maxNbOfFactions - 1) && (! this.hasAsFaction(unit.getFaction()))));
		boolean flag5 = (! this.getCube(unit.getCubePosition()[0],unit.getCubePosition()[1],unit.getCubePosition()[2]).isSolid());
		return (flag1 && flag2 && flag3 && flag4 && flag5);
	}
	
	/**
	 * Spawn a unit with a random position and random attribute values in this world.
	 * @param enableDefaultBehaviour	The status of the default behaviour of the to be spawned unit.
	 * @result	A random center cube position is generated, a name according to the number units in this world is generated.
	 * 			A unit with this generated name and position and with random attribute values is initialized and added to this world
	 * 			and is returned.
	 */
	public Unit spawnUnit(boolean enableDefaultBehaviour) {
		PositionVector position = PositionVector.centrePosition(this.randomStandingPosition());
		String name = "Unit";
		Unit unit = new Unit(position, name, this.autoFaction());
		if(enableDefaultBehaviour == true)
			unit.startDefaultBehaviour();
		this.addUnit(unit);
		return unit;
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
	public boolean isValidStandingPosition(PositionVector position) throws NullPointerException, IllegalArgumentException {
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("position is not located in this world!");
		Cube cube = this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
		if(cube.isSolid())
			return false;
		if(position.getZArgument() == 0)
			return true;
		if(this.hasSolidAdjacent(cube))
			return true;
		else
			return false;
	}
	
	/**
	 * Check whether a given cube has a solid adjacent cube.
	 * @param cube	The given cube.
	 * @return	True if and only if there are positions that are solid in the collection of the given cube's adjacent positions.
	 * @throws	NullPointerException
	 * 			The given cube is not effective.
	 * @throws	IllegalArgumentException
	 * 			The given cube is not from this world.
	 */
	public boolean hasSolidAdjacent(Cube cube) throws NullPointerException, IllegalArgumentException {
		PositionVector cubePosition = cube.getPosition();
		Set<PositionVector> allAdjacents = this.getAllAdjacentPositions(cubePosition);
		return (! this.getSolidsFromPositionSet(allAdjacents).isEmpty());
	}
	
	/**
	 * Check whether a given position is located within this world.
	 * @param position	The given position.
	 * @return	True is and only if all components are greater then or equal to zero and smaller than the number of cubes
	 * in this world along the respective component direction and the position is effective.
	 */
	public boolean isValidPosition(PositionVector position) {
		if(position == null)
			return false;
		double x = position.getXArgument();
		double y = position.getYArgument();
		double z = position.getZArgument();
		if((x < this.getNbCubesX()) && (y < this.getNbCubesY()) && (z < this.getNbCubesZ()) && (x >= 0) && (y >= 0) && (z >= 0))
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
	 * @effect	The given material is added to the material set of this world and to the cube in which it is located.
	 */
	public void addMaterial(Material material){
		this.getMaterialSet().add(material);
		this.getCube(material.getCubePosition()[0], material.getCubePosition()[1], 
				material.getCubePosition()[2]).addAsContent(material);
	}
	
	/**
	 * Remove a given material from this world's material set.
	 * @param material	The given material.
	 * @effect	The given material is removed from this world's material set and from the cube in which it was located.
	 * @throws	NullPointerException
	 * 			The given material is not effective.
	 * @throws	IllegalArgumentException
	 * 			The given material is not in this world's material set.
	 */
	public void removeMaterial(Material material){
		if(material == null)
			throw new NullPointerException();
		if(! this.hasAsMaterial(material))
			throw new IllegalArgumentException();
		this.getMaterialSet().remove(material);
		this.getCube(material.getCubePosition()[0], material.getCubePosition()[1], 
				material.getCubePosition()[2]).removeAsContent(material);
	}
	
	/**
	 * Check whether a given material is in this world.
	 * @param material	The given material.
	 * @return	True if and only if this world's material set contains the given material.
	 * @throws NullPointerException
	 * 			The given material is not effective.
	 */
	public boolean hasAsMaterial(Material material) throws NullPointerException{
		if(material == null)
			throw new NullPointerException();
		return this.getMaterialSet().contains(material);
	}
	
//	/**
//	 * Check whether this world's material set is a proper material set for this world.
//	 * @return	true if and only if each material in this world's material set has a valid position.
//	 */
//	private boolean hasProperMaterialSet(){
//		for(Material material : this.getMaterialSet()){
//			if(! this.isValidPosition(material.getUnitPosition()))
//				return false;
//		}
//		return true;
//	}
	
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
	
	/**
	 * Return the faction set of this world.
	 */
	@Basic @Raw @Model
	private Set<Faction> getFactionSet() {
		return this.factionSet;
	}
	
	/**
	 * Check whether the given faction set is a valid faction set for
	 * any world.
	 *  
	 * @param  faction set
	 *         The faction set to check.
	 * @return 
	 *       | result == (factionSet != null)
	*/
	private static boolean isValidFactionSet(Set<Faction> factionSet) {
		return (factionSet != null);
	}
	
	/**
	 * Set the faction set of this world to the given faction set.
	 * 
	 * @param  factionSet
	 *         The new faction set for this world.
	 * @post   The faction set of this new world is equal to
	 *         the given faction set.
	 *       | new.getFactionSet() == factionSet
	 * @throws NullPointerException
	 *         The given faction set is not a valid faction set for any
	 *         world.
	 *       | ! isValidFactionSet(getFactionSet())
	 */
	@Raw @Model
	private void setFactionSet(Set<Faction> factionSet) 
			throws NullPointerException {
		if (! isValidFactionSet(factionSet))
			throw new NullPointerException();
		this.factionSet = factionSet;
	}
	
	/**
	 * Variable registering the faction set of this world.
	 */
	private Set<Faction> factionSet;
	
	/**
	 * Add a given faction to this world.
	 * @param faction	The given faction.
	 * @effect	The given faction is added to this world's faction set.
	 * @throws IllegalStateException
	 * 			This world already reached it's maximum amount of factions.
	 * @throws	NullPointerException
	 * 			The given faction is not effective.
	 */
	public void addFaction(Faction faction) throws  IllegalStateException, NullPointerException{
		if(faction == null)
			throw new NullPointerException();
		try{if(! this.canHaveAsFaction(faction))
				throw new IllegalStateException();
			this.getFactionSet().add(faction);
		}
		catch (IllegalStateException exc) {
			
		}	
	}
	
	/**
	 * Remove the given faction from this world.
	 * @param faction	The given faction.
	 * @effect	Remove the given faction from this world's faction set.
	 * @throws NullPointerException
	 * 			The given faction is not effective.
	 * @throws IllegalArgumentException
	 * 			This world does not have the given faction in it's faction set.
	 */
	public void removeFaction(Faction faction) throws NullPointerException, IllegalArgumentException{
		if(faction == null)
			throw new NullPointerException();
		if(! this.hasAsFaction(faction))
			throw new IllegalArgumentException("Faction does not exist!");
		this.getFactionSet().remove(faction);
	}
	
	/**
	 * Check whether this world can have a given faction.
	 * @param faction	The given faction.	
	 * @return	True if and only if the given faction is effective, is not already a faction of this world, this world has not yet
	 * 			reached its maximum allowed amount of active factions or it has reached it and the given faction does not contain
	 * 			any units (not active).
	 */
	private boolean canHaveAsFaction(Faction faction){
		boolean flag1 = (faction != null);
		boolean flag2 = (! this.getFactionSet().contains(faction));
		boolean flag3 = ((this.getFactionSet().size()) < maxNbOfFactions) || (((this.getFactionSet().size()) == maxNbOfFactions)
				&& (faction.getUnitSet().isEmpty()));
		return (flag1 && flag2 && flag3);
	}
	
	/**
	 * Check whether this world has a given faction as one of its factions.
	 * @param faction	The given faction.
	 * @return	True if and only if this world's faction set contains the given faction.
	 * @throws NullPointerException
	 * 			The given faction is not effective.
	 */
	private boolean hasAsFaction(Faction faction) throws NullPointerException {
		if(faction == null)
			throw new NullPointerException();
		return this.getFactionSet().contains(faction);
	}
	
	/**
	 * Get all active factions of this world.
	 * @return	All factions from this world's faction set that are not empty.
	 */
	public Set<Faction> getActiveFactions() {
		Set<Faction> result = new HashSet<Faction>();
		for(Faction faction : this.getFactionSet()){
			if(! faction.getUnitSet().isEmpty())
				result.add(faction);
		}
		return result;
	}
	
	/**
	 * Variable registering the maximum amount of factions allowed in any world.
	 */
	private static int maxNbOfFactions = 5;
	
	/**
	 * Return an automatically chosen faction for a unit.
	 * @return	A new faction if this world has not yet reached its maximum amount of allowed factions, or the faction with the least
	 * 			amount of units, when this world has already reached its maximum amount of factions.
	 */
	private Faction autoFaction() throws IllegalStateException{
		if(this.getFactionSet().size() < maxNbOfFactions)
			return new Faction();
		else{
			Faction smallestFaction = null;
			int smallestNbOfunits = Faction.getMaxNbOfUnits();
			for(Faction faction : this.getFactionSet()){
				int factionSize = faction.getUnitSet().size();
				if(factionSize < smallestNbOfunits)
					smallestFaction = faction;
					smallestNbOfunits = factionSize;
			}
			if(smallestFaction == null)
				throw new IllegalStateException("Max amount of factions reached and all full!");
			return smallestFaction;
		}
	}
	
	/**
	 * Return the position of the cube under the cube of the given position in this world.
	 * @param position	The given position.
	 * @return	The position of the cube directly under the cube of the given position.
	 * @throws IllegalArgumentException
	 * 			The given position is at the bottom of this world.
	 * @throws	NullPointerException
	 * 			The given position is not effective.
	 */
	public PositionVector getCubePositionUnderneath(PositionVector position) throws IllegalArgumentException, NullPointerException {
		if(position.getZArgument() == 0)
			throw new IllegalArgumentException("Given position is at the bottom of this world, nothing underneath!");
		int x = (int) position.getXArgument();
		int y = (int) position.getYArgument();
		int z = (int) position.getZArgument() - 1;
		return this.getCube(x, y, z).getPosition();
	}
	
	/**
	 * Return a set with all adjacent cube positions in this world of a given position in this world.
	 * @param position	The given position.
	 * @return	A set with all the adjacent positions of the given position, that are valid positions of this world.
	 * @throws	NullPointerException
	 * 			The given position is not effective.
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public Set<PositionVector> getAllAdjacentPositions(PositionVector position) throws NullPointerException, IllegalArgumentException {
		if(position == null)
			throw new NullPointerException();
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("The given position is not a valid position for this world!");
		int x = (int) position.getXArgument();
		int y = (int) position.getYArgument();
		int z = (int) position.getZArgument();
		PositionVector[] allPossibilities = {new PositionVector(x-1,y,z), new PositionVector(x+1, y, z),
				new PositionVector(x, y-1, z), new PositionVector(x, y+1, z), new PositionVector(x, y, z-1),
				new PositionVector(x,y,z+1), new PositionVector(x+1,y+1,z+1), new PositionVector(x-1,y+1,z+1),
				new PositionVector(x+1,y-1,z+1), new PositionVector(x+1,y+1,z-1), new PositionVector(x-1,y+1,z-1),
				new PositionVector(x-1,y-1,z+1), new PositionVector(x+1,y-1,z-1), new PositionVector(x-1,y-1,z-1),
				new PositionVector(x+1,y+1,z), new PositionVector(x-1,y-1,z), new PositionVector(x+1,y-1,z),
				new PositionVector(x-1,y+1,z), new PositionVector(x+1,y,z+1), new PositionVector(x-1,y,z+1),
				new PositionVector(x+1,y,z-1), new PositionVector(x-1,y,z-1), new PositionVector(x+1,y+1,z), 
				new PositionVector(x-1,y+1,z), new PositionVector(x+1,y-1,z), new PositionVector(x-1,y-1,z)};
		HashSet<PositionVector> validAdjacents = new HashSet<PositionVector>();
		for(PositionVector adjacent : allPossibilities){
			if(this.isValidPosition(adjacent))
				validAdjacents.add(new PositionVector((int) adjacent.getXArgument(), 
						(int) adjacent.getYArgument(), (int) adjacent.getZArgument()));
		}
		return validAdjacents;
	}
	
	/**
	 * Check whether the cube at a given position is solid.
	 * @param position	The given position.
	 * @return	True if and only if the cube at the given position is solid.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position.
	 * @throws NullPointerException
	 * 			The given position is not effective.
	 */
	public boolean isSolidPosition(PositionVector position) throws IllegalArgumentException, NullPointerException {
		if(position == null)
			throw new NullPointerException();
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("Not a valid position for this world!");
		return this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument()).isSolid();
	}
	
	/**
	 * Return the positions of solid cubes from a given set of positions.
	 * @param positionSet	The given set of positions.
	 * @return	A subset of the given position set, containing only the positions that refer to solid cubes.
	 * @throws	NullPointerException
	 * 			The given set of positions is not effective.
	 * @throws	IllegalArgumentException
	 * 			The given set of positions contains positions that are not valid positions for this world.
	 */
	private Set<PositionVector> getSolidsFromPositionSet(Set<PositionVector> positionSet) 
			throws NullPointerException, IllegalArgumentException {
		if(positionSet == null)
			throw new NullPointerException();
		Set<PositionVector> solidSet = new HashSet<PositionVector>();
		for(PositionVector position : positionSet)	{
			if(this.isSolidPosition(position))
				solidSet.add(position);
		}
		return solidSet;
	}
	
	/**
	 * Return whether the cube at a given position is solid and is connected to the border through adjacent solid cubes.
	 * @param position	The given position.
	 * @return	True if and only if the cube at the given position is solid and connected to the border.
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public boolean isSolidConnectedToBorder(PositionVector position){
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("The given position is not a valid position for this world!");
		return this.getConnectedToBorder().isSolidConnectedToBorder((int) position.getXArgument(), (int) position.getYArgument()
				, (int) position.getZArgument());
	}
	
	/**
	 * Update the cubes in this world, so the given unit is in the cube corresponding to its current position.
	 * @param unit	The given unit.
	 * @param oldCubePosition	The old cube position of this unit.
	 * @param newCubePosition	The new cube position of this unit.
	 * @throws NullPointerException
	 * 			The given unit or one of the given positions is not effective.
	 * @throws IllegalArgumentException
	 * 			This world does not contain the given unit or the given new cube position does not correspond to the given unit's
	 * 			current position.
	 */
	public void updateObjectPosition(GameObject gameObject, PositionVector oldCubePosition, PositionVector newCubePosition) 
			throws NullPointerException, IllegalArgumentException{
		if(((gameObject.getClass().equals(Unit.class)) && (! this.hasAsUnit((Unit) gameObject))) ||
				((gameObject.getClass().equals(Material.class)) && (! this.hasAsMaterial((Material) gameObject))))
			throw new IllegalArgumentException("This world does not contain the given unit!");
		Cube oldCube = this.getCube((int) oldCubePosition.getXArgument(), (int) oldCubePosition.getYArgument(),
				(int) oldCubePosition.getZArgument());
		oldCube.removeAsContent(gameObject);
		Cube newCube = this.getCube((int) newCubePosition.getXArgument(), (int) newCubePosition.getYArgument(),
				(int) newCubePosition.getZArgument());
		newCube.addAsContent(gameObject);
	}
	
	/**
	 * Check whether the cube that is referred to by the given position is a workshop.
	 * @param position	The given position.
	 * @return	True if and only if the terrain type of the cube of the given position equals 3.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public boolean isWorkshop(PositionVector position) throws IllegalArgumentException {
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("The given position is not a valid position for this world.");
		Cube cube = this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
		return (cube.getTerrainType() == 3);
	}
	
	/**
	 * Check whether the cube that is referred to by the given position is a tree.
	 * @param position	The given position.
	 * @return	True if and only if the terrain type of the cube of the given position equals 2.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public boolean isWood(PositionVector position) throws IllegalArgumentException {
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("The given position is not a valid position for this world.");
		Cube cube = this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
		return (cube.getTerrainType() == 2);
	}
	
	/**
	 * Check whether the cube that is referred to by the given position is a rock.
	 * @param position	The given position.
	 * @return	True if and only if the terrain type of the cube of the given position equals 1.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public boolean isRock(PositionVector position) throws IllegalArgumentException {
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("The given position is not a valid position for this world.");
		Cube cube = this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
		return (cube.getTerrainType() == 1);
	}
	
	/**
	 * Check whether the cube that is referred to by a given position contains a boulder.
	 * @param position	The given position.
	 * @return	True if and only if the cube at the given position contains a boulder.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public boolean containsBoulder(PositionVector position) throws IllegalArgumentException {
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("The given position is not a valid position for this world.");
		Cube cube = this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
		return cube.containsBoulder();
	}
	
	/**
	 * Check whether the cube that is referred to by a given position contains a log.
	 * @param position	The given position.
	 * @return	True if and only if the cube at the given position contains a log.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public boolean containsLog(PositionVector position) throws IllegalArgumentException {
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("The given position is not a valid position for this world.");
		Cube cube = this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
		return cube.containsBoulder();
	}
	
	
	/**
	 * Get a boulder from the cube at a given position.
	 * @param position	The given position.
	 * @return	A boulder from the cube that is referred to by the given position.
	 * @throws IllegalArgumentException
	 * 			The cube that is referred to by the given position does not contain a boulder.
	 */
	public Boulder getABoulder(PositionVector position) throws IllegalArgumentException {
		if(! this.containsBoulder(position))
			throw new IllegalArgumentException("The given position does not contain a boulder!");
		Cube cube = this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
		return cube.getABoulder();
	}
	
	/**
	 * Get a log from the cube at a given position.
	 * @param position	The given position.
	 * @return	A log from the cube that is referred to by the given position.
	 * @throws IllegalArgumentException
	 * 			The cube that is referred to by the given position does not contain a log.
	 */
	public Log getALog(PositionVector position) throws IllegalArgumentException {
		if(! this.containsLog(position))
			throw new IllegalArgumentException("The given position does not contain a log!");
		Cube cube = this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
		return cube.getALog();
	}
	
	/**
	 * Return a set of all adjacent standing positions of a given position.
	 * @param position	The given position.
	 * @return	A set of positions that are standing positions and adjacent positions of the given position.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public Set<PositionVector> getAdjacentStandingPositions(PositionVector position) throws IllegalArgumentException {
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("The given position is not a valid position!");
		Set<PositionVector> allAdjacents = this.getAllAdjacentPositions(position);
		for(PositionVector adjacent : allAdjacents){
			if(! this.isValidStandingPosition(adjacent)){
				allAdjacents.remove(adjacent);
			}
		}
		return allAdjacents;
	}
	
	/**
	 * Return a set of all units in the cube of the given position and all units in adjacent cubes of the cube of the given position.
	 * @param position	The given position.
	 * @return	A set containing all the units of the cube of the given position and its adjacent cubes.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public Set<Unit> getAdjacentUnits(PositionVector position) throws IllegalArgumentException {
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("Not a valid position for this world!");
		Set<PositionVector> adjacentStandingPositions = this.getAdjacentStandingPositions(position);
		Set<Unit> adjacentUnits = new HashSet<Unit>();
		adjacentStandingPositions.add(position);
		for(PositionVector adjacent : adjacentStandingPositions){
			Cube cube = this.getCube((int) adjacent.getXArgument(), (int) adjacent.getYArgument(), (int) adjacent.getZArgument());
			adjacentUnits.addAll(cube.getUnits());
		}
		return adjacentUnits;
	}
	
	/**
	 * Get the enemies in neighboring cubes of this unit, including the unit's cube.
	 * @param unit	The given unit.
	 * @return	All adjacent units, that are not from this unit's faction.
	 * @throws IllegalArgumentException
	 * 			The given world does not have the given unit as one of it's units.
	 * @throws NullPointerException
	 * 			The given unit is not effective.
	 */
	public Set<Unit> getAdjacentEnemies(Unit unit) throws IllegalArgumentException, NullPointerException {
		if(! this.hasAsUnit(unit))
			throw new IllegalArgumentException("This world does not have the given unit as one of its units!");
		Faction allyFaction = unit.getFaction();
		Set<Unit> adjacentUnits = this.getAdjacentUnits(unit.getCubePositionVector());
		for(Unit adjacentUnit : adjacentUnits){
			if(adjacentUnit.getFaction().equals(allyFaction))
				adjacentUnits.remove(adjacentUnit);
		}
		return adjacentUnits;
	}
	
	/**
	 * Get all boulders in this world.
	 * @return	A set of all boulders that are in this unit's material set.
	 */
	public Set<Boulder> getBoulders() {
		Set<Boulder> result = new HashSet<Boulder>();
		for(Material material : this.getMaterialSet()){
			if(material.getClass().equals(Boulder.class))
				result.add((Boulder) material);
		}
		return result;
	}
	
	/**
	 * Get all logs in this world.
	 * @return	A set of all logs that are in this unit's material set.
	 */
	public Set<Log> getLogs() {
		Set<Log> result = new HashSet<Log>();
		for(Material material : this.getMaterialSet()){
			if(material.getClass().equals(Log.class))
				result.add((Log) material);
		}
		return result;
	}
	
}