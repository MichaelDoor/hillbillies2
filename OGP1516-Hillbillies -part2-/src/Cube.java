import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.Util;

/**
 * A class of cubes, having a position and holding objects.
 * 
 * @invar  The position of each cube must be a valid position for any
 *         cube.
 *       | isValidPosition(getPosition())
 *       
 * @author Michaël Dooreman
 * @version	0.1
 *
 */
public abstract class Cube {
	
	
	
	/**
	 * Initialize this new cube with given position.
	 *
	 * @param  position
	 *         The position for this new cube.
	 * @effect The position of this new cube is set to
	 *         the given position.
	 *       | this.setPosition(position)
	 */
	public Cube(PositionVector position)
			throws IllegalArgumentException {
		this.setPosition(position);
	}
	
	
	/**
	 * Return the position of this cube.
	 */
	@Basic @Raw
	public PositionVector getPosition() {
		return this.position;
	}
	
	/**
	 * Check whether the given position is a valid position for
	 * any cube.
	 *  
	 * @param  position
	 *         The position to check.
	 * @return 
	 *       | result == (position != null) && (Util.fuzzyEquals(position.getXArgument(),(int)position.getXArgument()))
	 *       |					&& (Util.fuzzyEquals(position.getYArgument(),(int)position.getYArgument()))
	 *       |						&& (Util.fuzzyEquals(position.getZArgument(),(int)position.getZArgument()))
	*/
	public static boolean isValidPosition(PositionVector position) {
		return ((position != null) && (Util.fuzzyEquals(position.getXArgument(),(int)position.getXArgument()))
				&& (Util.fuzzyEquals(position.getYArgument(),(int) position.getYArgument()))
					&& (Util.fuzzyEquals(position.getZArgument(),(int)position.getZArgument())));
	}
	
	/**
	 * Set the position of this cube to the given position.
	 * 
	 * @param  position
	 *         The new position for this cube.
	 * @post   The position of this new cube is equal to
	 *         the given position.
	 *       | new.getPosition() == position
	 * @throws IllegalArgumentException
	 *         The given position is not a valid position for any
	 *         cube.
	 *       | ! isValidPosition(getPosition())
	 */
	@Raw
	public void setPosition(PositionVector position) 
			throws IllegalArgumentException {
		if (! isValidPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}
	
	/**
	 * Variable registering the position of this cube.
	 */
	private PositionVector position;
	
	
	

}
