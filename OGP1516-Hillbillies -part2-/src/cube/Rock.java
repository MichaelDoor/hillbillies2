package cube;

import java.util.ArrayList;

import objects.GameObject;
import position.PositionVector;

public class Rock extends SolidCube {

	public Rock(PositionVector position) throws IllegalArgumentException {
		super(position);
	}
	
	public Rock(PositionVector position, ArrayList<GameObject> content) throws IllegalArgumentException {
		super(position, content);
	}

	@Override
	public int getTerrainType() {
		return 1;
	}

}
