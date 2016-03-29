package cube;
import objects.GameObject;
import java.util.*;
import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.Util;
import position.PositionVector;

/**
 * A class of cubes, having a position and holding objects.
 * 
 * @invar  The position of each cube must be a valid position for any
 *         cube.
 *       | isValidPosition(getPosition())
 * @invar  The content of each cube must be a valid content for any
 *         cube.
 *       | isValidContent(getContent())
 *       
 * @author Michaël Dooreman
 * @version	0.2
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
	 * @effect The content of this new cube is set empty.
	 *       | this.setContent(new ArrayList<GameObject>())
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position for this cube.
	 */
	public Cube(PositionVector position)
			throws IllegalArgumentException {
		this.setPosition(position);
		this.setContent(new ArrayList<GameObject>());
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
	private void setPosition(PositionVector position) 
			throws IllegalArgumentException {
		if (! isValidPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}
	
	/**
	 * Variable registering the position of this cube.
	*/
	private PositionVector position;
	
	/**
	 * Return the terrain type of this cube.
	 */
	@Basic @Raw @Immutable
	public abstract int getTerrainType();
	
	
	/**
	 * Return the content of this cube.
	 */
	@Basic @Raw
	public List<GameObject> getContent() {
		return this.content;
	}
	
	/**
	 * Check whether the given content is a valid content for
	 * any cube.
	 *  
	 * @param  content
	 *         The content to check.
	 * @return 
	 *       | result == (content != null)
	*/
	public static boolean isValidContent(List<GameObject> content) {
		return (content != null);
	}
	
	/**
	 * Set the content of this cube to the given content.
	 * 
	 * @param  content
	 *         The new content for this cube.
	 * @post   The content of this new cube is equal to
	 *         the given content.
	 *       | new.getContent() == content
	 * @throws NullPointerException
	 *         The given content is not a valid content for any
	 *         cube.
	 *       | ! isValidContent(getContent())
	 */
	@Raw
	private void setContent(List<GameObject> content) 
			throws NullPointerException {
		if (! isValidContent(content))
			throw new NullPointerException();
		this.content = content;
	}
	
	/**
	 * Variable registering the content of this cube.
	 */
	private List<GameObject> content;
	
	/**
	 * Add an object to the content of this cube.
	 * @param object	The given object.
	 * @effect	The given object is added to the content of this cube.
	 * @throws IllegalArgumentException
	 * 			The given object is not a valid content for this cube.
	 */
	public void addAsContent(GameObject object) throws IllegalArgumentException{
		if (! this.canHaveAsContent(object))
			throw new IllegalArgumentException();
		this.content.add(object);
	}
	
	/**
	 * Remove an object from the content of this cube.
	 * @param object	The given object.
	 * @effect	The given object is deleted from the content of this cube.
	 * @throws	IllegalArgumentException
	 * 			This cube does not contain the given object.
	 * @throws	NullPointerException
	 * 			The given object is not effective.
	 */
	public void removeAsContent(GameObject object) throws IllegalArgumentException, NullPointerException {
		if (! this.hasAsContent(object))
			throw new IllegalArgumentException("This cube does not contain the given object!");
		this.content.remove(object);
	}
	
	/**
	 * Check whether the given object can belong to the content of this cube.
	 * @param object	The given object.
	 * @return	True if and only if the object is effective and is located in this cube.
	 */
	public boolean canHaveAsContent(GameObject object) {
		return (object != null) && (object.getCubePosition().equals(this.getPosition()));
	}
	
	/**
	 * Check whether a given position is in the content of this cube.
	 * @param object	The given object.
	 * @return	True if and only if this cube's content contains the object.
	 * @throws	NullPointerException
	 * 			The object is not effective.
	 */
	public boolean hasAsContent(GameObject object) {
		if (object == null)
			throw new NullPointerException();
		return this.content.contains(object);
	}
	
	
	public boolean hasProperContent() {
		return false;
	}
}
