package objects;

import java.util.Random;

import be.kuleuven.cs.som.annotate.*;
import position.PositionVector;

/**
 * A class of materials, having a position and weight.
 * @author Michaël
 *
 */
public abstract class Material extends GameObject {

	/**
	 * Create a material, with a given position and a random weight.
	 * @param position	The given position.
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position.
	 * @throws	NullPointerException
	 * 			The given position is not effective.
	 */
	public Material(PositionVector position){
		super(position);
		this.setWeight();
	}
	
	/**
	 * Set the weight of this material.
	 * @effect	The weight of this material is set to a random value between 10 and 50.
	 */
	@Raw
	private void setWeight() {
		Random generator = new Random();
		super.setWeight(generator.nextInt(41)+10);
	}

}
