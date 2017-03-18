package com.kubiappi.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kubiappi.game.GameMain;
import com.kubiappi.info.GameInfo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) GameInfo.WIDTH;
		config.height = (int) GameInfo.HEIGHT;
		config.resizable = true;
		new LwjglApplication(new GameMain(), config);
	}
}
