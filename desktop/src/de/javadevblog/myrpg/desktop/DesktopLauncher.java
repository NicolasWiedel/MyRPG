package de.javadevblog.myrpg.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.javadevblog.myrpg.main.MyRPG;

public class DesktopLauncher {
	public static void main (String[] arg) {
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "MyRPG";
		config.useGL30 = false;
		config.width = 960;
		config.height = 720;
		
		Application app = new LwjglApplication(new MyRPG(), config);
		
		Gdx.app = app;
//		Gdx.app.setLogLevel(Application.LOG_INFO);
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
//		Gdx.app.setLogLevel(Application.LOG_ERROR);
//		Gdx.app.setLogLevel(Application.LOG_NONE);
	}
}
