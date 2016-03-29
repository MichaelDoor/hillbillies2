package objects;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;
import ogp.framework.util.Util;
import position.PositionVector;

/**
 * A class of game objects, having a position and weight.
 * @author Michaël
 *
 */
public abstract class GameObject {
	
	/**
	 * Create a new game object with a given position.
	 * @param position	The given position.
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position.
	 * @throws	NullPointerException
	 * 			The given position is not effective.
	 */
	public GameObject(PositionVector position) throws IllegalArgumentException, NullPointerException {
		this.setUnitPosition(position);
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
	 * @return 
	 *       | result == (Util.fuzzyGreaterThanOrEqualTo(position.getXArgument(),0) 
	 *       |				&& Util.fuzzyGreaterThanOrEqualTo(position.getYArgument(),0) 
	 *       |					&& Util.fuzzyGreaterThanOrEqualTo(position.getZArgument(),0)
	 *       |						&& (position.getXArgument() < 50) && (position.getYArgument() < 50) && (position.getZArgument() < 50))
	 * @throws	NullPointerException
	 * 			The position is not effective.
	 * 			| position == null
	*/
	protected static boolean isValidUnitPosition(PositionVector position) throws NullPointerException {
		return (Util.fuzzyGreaterThanOrEqualTo(position.getXArgument(),0) 
					&& Util.fuzzyGreaterThanOrEqualTo(position.getYArgument(),0) 
						&& Util.fuzzyGreaterThanOrEqualTo(position.getZArgument(),0)
				      		&& (position.getXArgument() < 50) && (position.getYArgument() < 50) && (position.getZArgument() < 50));
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

}
