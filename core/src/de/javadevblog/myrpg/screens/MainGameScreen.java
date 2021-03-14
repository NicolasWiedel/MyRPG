package de.javadevblog.myrpg.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Json;

import de.javadevblog.myrpg.map.Map;
import de.javadevblog.myrpg.map.MapManager;
import de.javadevblog.myrpg.model.Entity;
import de.javadevblog.myrpg.model.PlayerController;
import de.javadevblog.myrpg.screens.MainGameScreen;
import de.javadevblog.myrpg.screens.MainGameScreen.VIEWPORT;

public class MainGameScreen implements Screen {

	public static final String TAG = MainGameScreen.class.getSimpleName();

	private OrthogonalTiledMapRenderer mapRenderer;
	private OrthographicCamera camera;
	private MapManager mapMgr;
	private Json json;

	public MainGameScreen() {
		mapMgr = new MapManager();
		json = new Json();
	}

	@Override
	public void show() {
//		Kamera setup
		setupViewport(10, 10);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIEWPORT.viewportWidth, VIEWPORT.viewportHeight);
		
		mapRenderer = new OrthogonalTiledMapRenderer(mapMgr.getCurrentTiledMap(), Map.UNIT_SCALE);
		mapRenderer.setView(camera);
		
		Gdx.app.debug(TAG, "UnitScale: " + mapRenderer.getUnitScale());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(100 / 255.0f, 149 / 255.0f, 237 / 255.0f, 255 / 255.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mapRenderer.setView(camera);
		
		if(mapMgr.hasMapChanged()) {
			mapRenderer.setMap(mapMgr.getCurrentTiledMap());
			
			camera.update();
			
			mapMgr.setMapChanged(false);
		}
		
		mapRenderer.render();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		mapRenderer.dispose();
	}

	private void setupViewport(int width, int height) {
//		Einstellung des Vieports auf einen Anteil am gesamten Bildschirm
		VIEWPORT.virtualWidth = width;
		VIEWPORT.virtualHeight = height;

//		Viewport Dimension
		VIEWPORT.viewportWidth = VIEWPORT.virtualWidth;
		VIEWPORT.viewportHeight = VIEWPORT.virtualHeight;

//		Dimension des Bildschirms in Pixeln
		VIEWPORT.physicalWidth = Gdx.graphics.getWidth();
		VIEWPORT.physicalHeight = Gdx.graphics.getHeight();

//		Verhältnis des aktuellen Viewports
		VIEWPORT.aspectRatio = VIEWPORT.virtualWidth / VIEWPORT.virtualHeight;

//		Anpassen an Bildschirm
		if (VIEWPORT.physicalWidth / VIEWPORT.physicalHeight >= VIEWPORT.aspectRatio) {
			VIEWPORT.viewportWidth = VIEWPORT.viewportHeight * (VIEWPORT.physicalWidth / VIEWPORT.physicalHeight);
			VIEWPORT.viewportHeight = VIEWPORT.virtualHeight;
		} else {
			VIEWPORT.viewportWidth = VIEWPORT.virtualWidth;
			VIEWPORT.viewportHeight = VIEWPORT.viewportWidth * (VIEWPORT.physicalHeight / VIEWPORT.physicalWidth);
		}

		Gdx.app.debug(TAG, "WorldRenderer: virtuel: (" + VIEWPORT.virtualWidth + "," + VIEWPORT.virtualHeight + ")");
		Gdx.app.debug(TAG, "WorldRenderer: viewport: (" + VIEWPORT.viewportWidth + "," + VIEWPORT.viewportHeight + ")");
		Gdx.app.debug(TAG, "WorldRenderer: physical: (" + VIEWPORT.physicalWidth + "," + VIEWPORT.physicalHeight + ")");
	}

	public static class VIEWPORT {
		static float viewportWidth;
		static float viewportHeight;
		static float virtualWidth;
		static float virtualHeight;
		static float physicalWidth;
		static float physicalHeight;
		static float aspectRatio;
	}
}
