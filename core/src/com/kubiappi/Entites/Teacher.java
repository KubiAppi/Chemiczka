package com.kubiappi.Entites;

import com.badlogic.gdx.math.Rectangle;
import com.kubiappi.info.GameInfo;

/**
 * Created by Kuba Szczepaniak on 2017-04-03.
 */
public class Teacher extends Ground {

    public Teacher(){
        collisionGroundRectangle = new Rectangle(0,5, GameInfo.PLAYER_WIDTH,GameInfo.PLAYER_HEIGHT);
    }
}
