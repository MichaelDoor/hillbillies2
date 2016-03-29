package cube;

import position.PositionVector;

public class Tree extends SolidCube {

	public Tree(PositionVector position) throws IllegalArgumentException {
		super(position);
	}

	@Override
	public int getTerrainType() {
		return 2;
	}

}
