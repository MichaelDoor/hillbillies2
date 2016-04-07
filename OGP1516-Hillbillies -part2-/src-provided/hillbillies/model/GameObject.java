package hillbillies.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;
import ogp.framework.util.Util;
import hillbillies.model.PositionVector;
import hillbillies.model.World;

/**
 * A class of game objects, having a position and weight.
 * @invar  The unitPosition of each game object must be a valid unitPosition for any
 *         game object.
 *       | isValidUnitPosition(getUnitPosition())
 * @invar  The world of each unit must be a valid world for any
 *         game object.
 *       | isValidWorld(getWorld())
 * 
 * @author Micha�l
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
	 * @effect	If this game object belongs to a world, this unit's world updates it's position of this gameObject.
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
		PositionVector oldCubePosition = null;
		if((this.getWorld() != null))
			oldCubePosition = this.getCubePositionVector();
		this.position = new PositionVector (position.getXArgument(), position.getYArgument(), position.getZArgument());
		
		if((oldCubePosition != null) && (! oldCubePosition.equals(new PositionVector((int) position.getXArgument(), 
				(int) position.getYArgument(), (int) position.getZArgument())))){
			this.getWorld().updateObjectPosition(this, oldCubePosition, this.getCubePositionVector());
		}
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
	 */
	public abstract void advanceTime(double dt);
	
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
	/**
	 * Return this game object's cube position as a position vector.
	 * @return	The first second and third component of this game object's cube position.
	 * 			| result == new PositionVector(this.getCubePosition()[0], this.getCubePosition()[1],
	 * 																						 this.getCubePosition()[2])
	 */
	public PositionVector getCubePositionVector(){
		int[] cubePosition = this.getCubePosition();
		return new PositionVector(cubePosition[0], cubePosition[1], cubePosition[2]);
	}
	
	/**
	 * Return the targeted adjacent position of this unit.
	 */
	@Basic @Raw
	public PositionVector getNextPosition() {
		return this.nextPosition;
	}
	
	/**
	 * Check whether the given targeted adjacent position is a valid targeted adjacent position for
	 * any unit.
	 *  
	 * @param  targeted adjacent position
	 *         The targeted adjacent position to check.
	 * @return 
	 *       | result == (this.isValidAdjacent(nextPosition)) ||  (this.getUnitPosition().equals(nextPosition))
	*/
	protected boolean isValidNextPosition(PositionVector nextPosition) {
		return ((this.isValidAdjacent(nextPosition)) ||  (this.getUnitPosition().equals(nextPosition)));
	}
	
	/**
	 * Set the targeted adjacent position of this unit to the given targeted adjacent position.
	 * 
	 * @param  nextPosition
	 *         The new targeted adjacent position for this unit.
	 * @post   The targeted adjacent position of this new unit is equal to
	 *         the given targeted adjacent position.
	 *       | new.getNextPosition() == new PositionVector(nextPosition.getXArgument(), nextPosition.getYArgument(),
	 *       | 																					 nextPosition.getZArgument())
	 * @throws IllegalArgumentException
	 *         The given targeted adjacent position is not a valid targeted adjacent position for any
	 *         unit.
	 *       | (! isValidNextPosition(getNextPosition()))
	 * @throws	NullPointerException
	 * 			The next position is not effective.
	 * 			| nextPosition == null
	 */
	@Raw @Model
	protected void setNextPosition(PositionVector nextPosition) 
			throws IllegalArgumentException, NullPointerException {
		if (! isValidNextPosition(nextPosition)) {
			throw new IllegalArgumentException();
		}
		this.nextPosition = new PositionVector(nextPosition.getXArgument(), nextPosition.getYArgument(), nextPosition.getZArgument());
	}
	
	/**
	 * Variable registering the targeted adjacent position of this unit.
	 */
	protected PositionVector nextPosition;
	
	/**
	 * Check whether a given position is located in an adjacent cube of the position of this game object.
	 * @param position	The position to check.
	 * @return	True if and only if the given position is in an adjacent cube of this game object's position 
	 * 			and is a valid unit position.
	 * 			| result == (((Math.abs(this.getCubePosition()[0] - (int) position.getXArgument()) == 1) 
	 * 			|				|| (Math.abs(this.getCubePosition()[0] - (int) position.getXArgument()) == 0) 
	 * 			|					|| (Math.abs(this.getCubePosition()[0] - (int) position.getXArgument()) == -1))
	 *			|		&& (((Math.abs(this.getCubePosition()[1] - (int) position.getYArgument()) == 1) 
	 *			|				|| (Math.abs(this.getCubePosition()[1] - (int) position.getYArgument()) == 0) 
	 *			|					|| (Math.abs(this.getCubePosition()[1] - (int) position.getYArgument()) == -1))
	 *			|			&& (((Math.abs(this.getCubePosition()[2] - (int) position.getZArgument()) == 1) 
	 *			|				|| (Math.abs(this.getCubePosition()[2] - (int) position.getZArgument()) == 0) 
	 *			|					|| (Math.abs(this.getCubePosition()[2] - (int) position.getZArgument()) == 1))
	 *			|				&& (this.isValidUnitPosition(position)))))
	 */
	public boolean isValidAdjacent(PositionVector position) {
		if(! this.isValidUnitPosition(position))
			return false;
		int positionX = (int) position.getXArgument();
		int positionY = (int) position.getYArgument();
		int positionZ = (int) position.getZArgument();
		int unitX = this.getCubePosition()[0];
		int unitY = this.getCubePosition()[1];
		int unitZ = this.getCubePosition()[2];
		int[] difference = {Math.abs(unitX-positionX), Math.abs(unitY-positionY), Math.abs(unitZ-positionZ)};
		if (((difference[0] == -1) || (difference[0] == 0) || (difference[0] == 1))
				&& ((difference[1] == -1) || (difference[1] == 0) || (difference[1] == 1))
				&& ((difference[2] == -1) || (difference[2] == 0) || (difference[2] == 1))){
			return true;
		}
		return false;
	}
	
	/**
	 * Variable registering the falling velocity of any game object.
	 */
	protected static PositionVector fallVelocity = new PositionVector(0, 0, -3);
	
	/**
	 * Makes this game object fall.
	 * @effect	This game object's activity status is set to 'fall', it's current velocity to the fall velocity of any game object.
	 * 			| this.setActivityStatus("fall")
	 * 			| this.setNextPosition(PositionVector.centrePosition(this.getWorld().getPositionUnderneath(this.getCubePosition())))
	 */
	protected void fall() {
		this.setActivityStatus("fall");
		this.setCurrentVelocity(fallVelocity);
	}
	
	/**
	 * Check whether this game object should fall.
	 */
	protected abstract boolean fallCheck();
	
	/**
	 * Return the current velocity of this game object.
	 */
	@Basic @Raw
	public PositionVector getCurrentVelocityBasic() {
		return this.currentVelocity;
	}
	
	/**
	 * Check whether the given current velocity is a valid current velocity for
	 * any game object.
	 *  
	 * @param  current velocity
	 *         The current velocity to check.
	 * @return 
	 *       | result == true
	*/
	protected static boolean isValidCurrentVelocity(PositionVector currentVelocity) {
		return true;
	}
	
	/**
	 * Set the current velocity of this game object to the given current velocity.
	 * 
	 * @param  currentVelocity
	 *         The new current velocity for this game object.
	 * @post   The current velocity of this game object is equal to
	 *         the given current velocity.
	 *       | new.getCurrentVelocity() == currentVelocity
	 * @throws IllegalArgumentException
	 *         The given current velocity is not a valid current velocity for any
	 *         game object.
	 *       | ! isValidCurrentVelocity(getCurrentVelocity())
	 * @throws	NullPointerException
	 * 			The given velocity is not effective.
	 * 			| currentVelocity == null
	 */
	@Raw @Model
	protected void setCurrentVelocity(PositionVector currentVelocity) 
			throws IllegalArgumentException, NullPointerException {
		if (! isValidCurrentVelocity(currentVelocity))
			throw new IllegalArgumentException();
		this.currentVelocity = currentVelocity;
	}
	
	/**
	 * Variable registering the current velocity of this game object.
	 */
	protected PositionVector currentVelocity;
	
	/**
	 * Terminate this game object.
	 * @effect	This game object's velocity, next position, position and world are set null.
	 * @throws IllegalStateException
	 * 			This game object is already terminated.
	 */
	protected void terminate() throws IllegalStateException {
		if(this.isTerminated())
			throw new IllegalStateException("Game object already terminated!");
		this.currentVelocity = null;
		this.nextPosition = null;
		this.position = null;
		this.world = null;
	}
	
	/**
	 * Check whether this game object is terminated.
	 * @return	True if and only if his game object's velocity, next position, position and world are null.
	 */
	public boolean isTerminated() {
		return ((this.getCurrentVelocityBasic() == null) && (this.getNextPosition() == null) && (this.getUnitPosition() == null)
				&& (this.getWorld() == null));
	}
	
	/**
	 * Return the activityStatus of this game object.
	 */
	@Basic @Raw
	public String getActivityStatus() {
		return this.activityStatus;
	}
	
	/**
	 * Check whether the given activityStatus is a valid activityStatus for
	 * this game object.
	 *  
	 * @param  activityStatus
	 *         The activityStatus to check.
	 * @return 
	 *       | result == (activityStatus.equals("fall")) || (activityStatus.equals("default"))
	 */
	protected boolean isValidActivityStatus(String activityStatus) {
		return ((activityStatus.equals("default")) || (activityStatus.equals("fall")));
	}
	
	/**
	 * Set the activityStatus of this game object to the given activityStatus.
	 * 
	 * @param  activityStatus
	 *         The new activityStatus for this game object.
	 * @post   The activityStatus of this game object is equal to
	 *         the given activityStatus.
	 *       | new.getActivityStatus() == activityStatus
	 * @throws IllegalArgumentException
	 *         The given activityStatus is not a valid activityStatus for any
	 *         game object.
	 *       | ! isValidActivityStatus(getActivityStatus())
	 * @throws	NullPointerException
	 * 			The activity status is not effective.
	 * 			| activityStatus == null
	 */
	@Raw @Model
	protected void setActivityStatus(String activityStatus) 
			throws IllegalArgumentException, NullPointerException {
		if (! this.isValidActivityStatus(activityStatus))
			throw new IllegalArgumentException();
		this.activityStatus = activityStatus;
	}
	
	/**
	 * Variable registering the activityStatus of this game object.
	 */
	protected String activityStatus;
	
	/**
	 * A method to make this game object move for a given time with a multiplied speed.
	 * @param dt	Time
	 * @param multiplier	The given multiplyer.
	 */
	protected abstract void miniMove(double dt, int multiplier);
}