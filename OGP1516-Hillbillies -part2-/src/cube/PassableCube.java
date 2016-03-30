package cube;

import java.util.ArrayList;

import objects.GameObject;
import position.PositionVector;

public abstract class PassableCube extends Cube {

	public PassableCube(PositionVector position) throws IllegalArgumentException {
		super(position);
	}
	
	public PassableCube(PositionVector position, ArrayList<GameObject> content) throws IllegalArgumentException {
		super(position, content);
	}
	
	@Override
	public boolean isSolid() {
		return false;
	}


}
