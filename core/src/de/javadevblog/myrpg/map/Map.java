package de.javadevblog.myrpg.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Json;

import de.javadevblog.myrpg.utils.Utility;

public abstract class Map {

	public static final String TAG = Map.class.getSimpleName();
	
	public final static float UNIT_SCALE  = 1/32f;
	
	// Map Layers
	protected final static String COLLISION_LAYER = "MAP_COLLISION_LAYER";
    protected final static String SPAWNS_LAYER = "MAP_SPAWNS_LAYER";
    protected final static String PORTAL_LAYER = "MAP_PORTAL_LAYER";
    
  //Starting locations
    protected final static String PLAYER_START = "PLAYER_START";
    protected final static String NPC_START = "NPC_START";
    
    protected Json json;
    
    protected TiledMap currentMap;
    
    protected MapLayer collisionLayer = null;
    protected MapLayer portalLayer = null;
    protected MapLayer spawnsLayer = null;
    
    protected MapFactory.MapType currentMapType;
    
    public Map(MapFactory.MapType mapType, String mapPath) {
    	json = new Json();
    	currentMapType = mapType;
    	
    	if( mapPath == null || mapPath.isEmpty() ) {
            Gdx.app.debug(TAG, "Map is invalid");
            return;
        }
    	
    	Utility.LoadMapAsset(mapPath);
    	if(Utility.isAssetLoaded(mapPath)) {
    		currentMap = Utility.getMapAsset(mapPath);
    	}
    	else {
    		Gdx.app.debug(TAG, "Map not loaded");
            return;
    	}
    	
    	collisionLayer = currentMap.getLayers().get(COLLISION_LAYER);
        if( collisionLayer == null ){
            Gdx.app.debug(TAG, "No collision layer!");
        }

        portalLayer = currentMap.getLayers().get(PORTAL_LAYER);
        if( portalLayer == null ){
            Gdx.app.debug(TAG, "No portal layer!");
        }

        spawnsLayer = currentMap.getLayers().get(SPAWNS_LAYER);
        if( spawnsLayer == null ){
            Gdx.app.debug(TAG, "No spawn layer!");
        }else{
            
        }
    }
    
    public abstract void updateMapEntities(MapManager mapMgr, Batch batch, float delta);
}
