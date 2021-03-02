package de.javadevblog.myrpg;

import com.badlogic.gdx.Game;

import de.javadevblog.myrpg.screens.MainGameScreen;

public class MyRPG extends Game {
	
	public static final MainGameScreen mainGameScreen = new MainGameScreen();

	@Override
	public void create() {
		setScreen(mainGameScreen);
	}

	@Override
	public void dispose() {
		mainGameScreen.dispose();
	}
}
