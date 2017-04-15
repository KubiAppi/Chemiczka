package com.kubiappi.bonuses;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Kuba Szczepaniak on 2017-04-04.
 */
public class BonusBoots extends BonusPencil {

    public BonusBoots(){
        texture = new Texture("bonus_boots.png");
        bonusType = BonusType.BOOTS;
        bonusThrow = false;
    }
}
