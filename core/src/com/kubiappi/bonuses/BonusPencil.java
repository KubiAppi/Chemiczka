package com.kubiappi.bonuses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kubiappi.flasks.FlaskMain;

/**
 * Created by Kuba Szczepaniak on 2017-04-03.
 */
public class BonusPencil extends BonusMain {

    public BonusPencil(){
        texture = new Texture("bonus_pencil.png");
        bonusType = BonusType.PENCIL;
        bonusThrow = false;
    }

    @Override
    public void timer() {
        timer += Gdx.graphics.getDeltaTime();
        if(timer > 5 && !bonusThrow){
            collisionCircle = null;
        }else if(bonusThrow){
            collisionCircle.x -= 300f * Gdx.graphics.getDeltaTime();
            collisionCircle.setY(150f);
        }
    }


}
