package cube;

import position.PositionVector;

public class Rock extends SolidCube {

	public Rock(PositionVector position) throws IllegalArgumentException {
		super(position);
	}

	@Override
	public int getTerrainType() {
		return 1;
	}

}
