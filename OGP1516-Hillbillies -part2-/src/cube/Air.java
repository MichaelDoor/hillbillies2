package cube;

import java.util.ArrayList;

import objects.GameObject;
import position.PositionVector;

public class Air extends PassableCube {

	public Air(PositionVector position) throws IllegalArgumentException {
		super(position);
	}
	
	public Air(PositionVector position, ArrayList<GameObject> content) throws IllegalArgumentException {
		super(position, content);
	}

	@Override
	public int getTerrainType() {
		return 0;
	}

}
