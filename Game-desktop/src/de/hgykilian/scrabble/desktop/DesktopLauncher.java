package de.hgykilian.scrabble.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.hgykilian.scrabble.GameMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Platform Game";
		config.width= 800;
		config.height = 600;
		config.useHDPI = true;
		config.vSyncEnabled = true;
		new LwjglApplication(new GameMain(), config);
	}
}