package de.javadevblog.myrpg.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class MapManager {

public static final String TAG = MapManager.class.getSimpleName();
	
	private Camera camera;
	private boolean mapChanged = false;
	private Map currentMap;
	
	public MapManager() {
		
	}
	
	public void loadMap(MapFactory.MapType mapType) {
		Map map = MapFactory.getMap(mapType);
		
		if(map == null) {
			Gdx.app.debug(TAG, "Die Karte existiert nicht!");
			return;
		}
		
		currentMap = map;
		mapChanged = true; 
	}
	
	public MapLayer getCollisionLayer(){
        return currentMap.getCollisionLayer();
    }

    public MapLayer getPortalLayer(){
        return currentMap.getPortalLayer();
    }
    
    public TiledMap getCurrentTiledMap(){
        if( currentMap == null ) {
            loadMap(MapFactory.MapType.TOWN);
        }
        return currentMap.getCurrentMap();
    }
    
    public void setCamera(Camera camera){
        this.camera = camera;
    }

    public Camera getCamera(){
        return camera;
    }
    
    public boolean hasMapChanged(){
        return mapChanged;
    }

    public void setMapChanged(boolean hasMapChanged){
        this.mapChanged = hasMapChanged;
    }
}
