package com.cyberhck.slappy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cyberhck.slappy.Slappy;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Slappy.WIDTH;
		config.height = Slappy.HEIGHT;
		config.title = Slappy.TITLE;
		new LwjglApplication(new Slappy(), config);
	}
}
