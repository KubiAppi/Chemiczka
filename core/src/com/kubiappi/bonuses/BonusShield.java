package com.kubiappi.bonuses;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Kuba Szczepaniak on 2017-04-02.
 */
public class BonusShield extends BonusHeal{

    public BonusShield(){
        texture = new Texture("bonus_shield.png");
        selectThrowBonusVector();
        bonusType = BonusType.SHIELD;
    }
}
