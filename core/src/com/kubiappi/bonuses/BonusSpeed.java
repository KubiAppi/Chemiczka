package com.kubiappi.bonuses;

/**
 * Created by Kuba Szczepaniak on 2017-04-02.
 */
public class BonusSpeed extends BonusHeal {

    public BonusSpeed(){
        selectThrowBonusVector();
        bonusType = BonusType.SPEED;
    }
}
