package com.kubiappi.bonuses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.kubiappi.info.GameInfo;

import java.util.Random;

/**
 * Created by Kuba Szczepaniak on 2017-04-02.
 */
public class BonusHeal extends BonusMain {

    private Vector2 throwBonusVector;

    public BonusHeal(){
        texture = new Texture("bonus_heart.png");
        bonusType = BonusType.HEAL;
        selectThrowBonusVector();
    }

    @Override
    public void timer(){
        timer += Gdx.graphics.getDeltaTime();
        if(timer > 5){
            collisionCircle = null;
        }
        if (collisionCircle != null && collisionCircle.y > 36f) {
            try{
            System.out.println(collisionCircle.x +" "+throwBonusVector.x);
            System.out.println(collisionCircle.y +" "+throwBonusVector.y);
            collisionCircle.x += throwBonusVector.x * Gdx.graphics.getDeltaTime();
            collisionCircle.y -= throwBonusVector.y * Gdx.graphics.getDeltaTime();}
            catch (Exception e){
                collisionCircle = null;
            }
        }
    }

    public void selectThrowBonusVector() {
        Random rand = new Random();
        switch (rand.nextInt(5)){
            case 1:
                throwBonusVector = new Vector2(GameInfo.BONUS_THROW_1);
                break;
            case 2:
                throwBonusVector = new Vector2(GameInfo.BONUS_THROW_2);
                break;
            case 3:
                throwBonusVector = new Vector2(GameInfo.BONUS_THROW_3);
                break;
            case 4:
                throwBonusVector = new Vector2(GameInfo.BONUS_THROW_4);
                break;
            case 5:
                throwBonusVector = new Vector2(GameInfo.BONUS_THROW_5);
                break;
            default:

        }
    }
}
