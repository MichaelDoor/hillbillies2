package cube;

import position.PositionVector;

public class Air extends PassableCube {

	public Air(PositionVector position) throws IllegalArgumentException {
		super(position);
	}

	@Override
	public int getTerrainType() {
		return 0;
	}

}
