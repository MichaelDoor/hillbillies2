package objects;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;
import ogp.framework.util.Util;
import position.PositionVector;
import world.World;

/**
 * A class of game objects, having a position and weight.
 * @invar  The unitPosition of each game object must be a valid unitPosition for any
 *         game object.
 *       | isValidUnitPosition(getUnitPosition())
 * @invar  The world of each unit must be a valid world for any
 *         game object.
 *       | isValidWorld(getWorld())
 * 
 * @author Michaël
 *
 */
public abstract class GameObject {
	
	/**
	 * Create a new game object with a given position.
	 * @param position	The given position.
	 * @effect 	The world of this new game object is set to null.
	 *       	| this.setWorld(null)
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position.
	 * @throws	NullPointerException
	 * 			The given position is not effective.
	 */
	public GameObject(PositionVector position) throws IllegalArgumentException, NullPointerException {
		this.setUnitPosition(position);
		this.setWorld(null);
	}
	
	/**
	 * Return the unitPosition of this game object.
	 */
	@Basic @Raw
	public PositionVector getUnitPosition() {
		return this.position;
	}
	
	/**
	 * Check whether the given unitPosition is a valid unitPosition for
	 * any game object.
	 *  
	 * @param  unitPosition
	 *         The unitPosition to check.
	 * @return True if and only if the components of the given position are equal to or greater than 0.
	 *       | result == (Util.fuzzyGreaterThanOrEqualTo(position.getXArgument(),0) 
	 *       |				&& Util.fuzzyGreaterThanOrEqualTo(position.getYArgument(),0) 
	 *       |					&& Util.fuzzyGreaterThanOrEqualTo(position.getZArgument(),0)
	 * @throws	NullPointerException
	 * 			The position is not effective.
	 * 			| position == null
	*/
	protected boolean isValidUnitPosition(PositionVector position) throws NullPointerException {
//		int maxX = this.getWorld().getNbCubesX();
//		int maxY = this.getWorld().getNbCubesY();
//		int maxZ = this.getWorld().getNbCubesZ();
		return (Util.fuzzyGreaterThanOrEqualTo(position.getXArgument(),0) 
					&& Util.fuzzyGreaterThanOrEqualTo(position.getYArgument(),0) 
						&& Util.fuzzyGreaterThanOrEqualTo(position.getZArgument(),0));
//				      		&& (position.getXArgument() < maxX) && (position.getYArgument() < maxY) 
//				      		&& (position.getZArgument() < maxZ));
	}
	
	/**
	 * Set the unitPosition of this game object to the given unitPosition.
	 * 
	 * @param  position
	 *         The new unitPosition for this game object.
	 * @post   The unitPosition of this game object is equal to
	 *         the given unitPosition.
	 *       	| new.getUnitPosition().equals(position)
	 * @throws IllegalArgumentException
	 *         The given unitPosition is not a valid unitPosition for any
	 *         game object.
	 *       	| ! isValidUnitPosition(getUnitPosition())
	 * @throws	NullPointerException
	 * 			The position is not effective.
	 * 			| position == null
	 */
	@Raw @Model
	protected void setUnitPosition(PositionVector position) 
			throws IllegalArgumentException, NullPointerException {
		if (! isValidUnitPosition(position))
			throw new IllegalArgumentException("Out of bounds!");
		this.position = new PositionVector (position.getXArgument(), position.getYArgument(), position.getZArgument());
	}
	
	/**
	 * Variable registering the unitPosition of this game object.
	 */
	protected PositionVector position;
	
	/**
	 * Return the cubePosition of this unit.
	 * @return	The position of the cube on which this unit is standing.
	 * 			| result == {(int) this.getUnitPosition().getXArgument(), (int) this.getUnitPosition().getYArgument(),
	 * 			|										(int) this.getUnitPosition().getZArgument()}
	 */
	public int[] getCubePosition() {
		int x = (int) this.getUnitPosition().getXArgument();
		int y = (int) this.getUnitPosition().getYArgument();
		int z = (int) this.getUnitPosition().getZArgument();
		int[] position = {x,y,z};
		return position;
	}
	
	/**
	 * Return the weight of this unit.
	 */
	@Basic @Raw
	public int getWeight() {
		return this.weight;
	}
	
	/**
	 * Set the weight of this game object to the given weight.
	 * @param weight	The given weight.
	 * @post	This game object's weight equals the given weight.
	 */
	@Raw
	protected void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * Variable registering the weight of this unit.
	 */
	protected int weight;
	
	/**
	 * Advance time for this game object by a given amount of time.
	 * @param dt	The given amount of time.
	 * @throws	IllegalArgumentException
	 * 			Time is either negative or equal to or greater then 0.2s.
	 */
	public void advanceTime(double dt)throws IllegalArgumentException {
		if ((dt < 0) || (dt >= 0.2)) {
			throw new IllegalArgumentException();
		}
		
	}
	
	/**
	 * Return the world of this game object.
	 */
	@Basic @Raw
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Check whether the given world is a valid world for
	 * any game object.
	 *  
	 * @param  world
	 *         The world to check.
	 * @return 
	 *       | result == (world == null) || (world.isValidStandingPosition(new PositionVector(x,y,z)))
	*/
	protected boolean isValidWorld(World world) {
		int[] position = this.getCubePosition();
		int x = position[0];
		int y = position[1];
		int z = position[2];
		return ((world == null) || (world.isValidStandingPosition(new PositionVector(x,y,z))));
	}
	
	/**
	 * Set the world of this game object to the given world.
	 * 
	 * @param  world
	 *         The new world for this game object.
	 * @post   The world of this new game object is equal to
	 *         the given world.
	 *       | new.getWorld() == world
	 * @throws IllegalArgumentException
	 *         The given world is not a valid world for this
	 *         game object.
	 *       | ! isValidWorld(getWorld())
	 */
	@Raw
	private void setWorld(World world) 
			throws NullPointerException, IllegalArgumentException {
		if (! isValidWorld(world))
			throw new IllegalArgumentException();
		this.world = world;
	}
	
	/**
	 * Variable registering the world of this game object.
	 */
	protected World world;
	
	/**
	 * Change this game object's current world to a given world.
	 * @param world	The given world.
	 * @effect	This game object's world is set to the given world.
	 * 			|	this.setWorld(world)
	 */
	public void changeWorld(World world){
		this.setWorld(world);
	}

}
