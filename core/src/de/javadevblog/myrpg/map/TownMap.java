package de.javadevblog.myrpg.map;

import com.badlogic.gdx.graphics.g2d.Batch;

public class TownMap extends Map {

	private static final String TAG = TownMap.class.getSimpleName();
	
	private static String mapPath = "new/maps/hometown.tmx";
	
	public TownMap() {
		super(MapFactory.MapType.TOWN,mapPath);
	}
	
	@Override
	public void updateMapEntities(MapManager mapMgr, Batch batch, float delta) {
		// TODO Auto-generated method stub
		
	}
}
