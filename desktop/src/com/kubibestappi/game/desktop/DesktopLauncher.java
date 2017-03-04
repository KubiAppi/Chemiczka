package com.kubibestappi.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kubibestappi.game.GameMain;
import com.kubibestappi.info.GameInfo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameInfo.WIDTH;
		config.height = GameInfo.HEIGHT;
		config.resizable = GameInfo.RESIZABLE;
		config.title = GameInfo.TITLE;
		new LwjglApplication(new GameMain(), config);
	}
}
