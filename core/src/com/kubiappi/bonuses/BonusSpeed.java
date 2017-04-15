package com.kubiappi.bonuses;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Kuba Szczepaniak on 2017-04-02.
 */
public class BonusSpeed extends BonusHeal {

    public BonusSpeed(){
        texture = new Texture("bonus_speed.png");
        selectThrowBonusVector();
        bonusType = BonusType.SPEED;
    }
}
