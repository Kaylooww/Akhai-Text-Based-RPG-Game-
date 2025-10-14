package StatusEffects;

import Entities.*;

public class StatModifyingEffect extends StatusEffect{
    protected StatType affectedStat;
    protected int modifierValue;
    protected boolean isPercentage;

    public StatModifyingEffect(String name, String description, int duration, StatType affectedStat, int value, boolean isPercentage) {
        super(name, description, duration, value > 0 ? StatusType.BUFF : StatusType.DEBUFF);
        this.affectedStat = affectedStat;
        this.modifierValue = value;
        this.isPercentage = isPercentage;
    }

    @Override
    public void applyEffect(Entity target){
        if(isPercentage){
            target.modifyStatPercentage(affectedStat, modifierValue);
        }else{
            target.modifyStatFlat(affectedStat, modifierValue);
        }
        target.addStatusEffect(this);
    }
    @Override
    public void removeEffect(Entity target){
        if(isPercentage){
            target.modifyStatPercentage(affectedStat, -modifierValue);
        }else{
            target.modifyStatFlat(affectedStat, -modifierValue);
        }
        target.removeStatusEffect(this);
    }

    @Override
    public void onTurnStart(Entity target){

    }
    @Override
    public void onTurnEnd(Entity target){
        decrementDuration();
        if(isExpired){
            removeEffect(target);
        }
    }
}
