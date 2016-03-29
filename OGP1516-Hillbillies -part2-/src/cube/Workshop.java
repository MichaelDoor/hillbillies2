package cube;

import position.PositionVector;

public class Workshop extends PassableCube {

	public Workshop(PositionVector position) throws IllegalArgumentException {
		super(position);
	}

	@Override
	public int getTerrainType() {
		return 3;
	}

}
