package com.kubiappi.bonuses;

/**
 * Created by Kuba Szczepaniak on 2017-04-02.
 */
public class BonusShield extends BonusHeal{

    public BonusShield(){
        selectThrowBonusVector();
        bonusType = BonusType.SHIELD;
    }
}
