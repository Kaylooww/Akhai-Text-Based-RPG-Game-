package StatusEffects;

import Entities.*;

public class StatModifyingEffect extends StatusEffect{
    protected StatType affectedStat;
    protected double modifierValue;
    protected boolean isPercentage;

    public StatModifyingEffect(String name, String description, int duration, StatType affectedStat, double value, boolean isPercentage) {
        super(name, description, duration, value > 0 ? StatusType.BUFF : StatusType.DEBUFF);
        this.affectedStat = affectedStat;
        this.modifierValue = value;
        this.isPercentage = isPercentage;
    }

    @Override
    public void applyEffect(Entity target){
        if(!target.hasEffect(this)){
            if(isPercentage){
                target.modifyStatPercentage(affectedStat, modifierValue);
            }else{
                target.modifyStatFlat(affectedStat, (int) modifierValue);
            }
            target.addStatusEffect(this);
            System.out.println(target.getName()+" has received a "+name+"!");
            delay(2000);
        }else{
            System.out.println(name+" effect has already been applied");
            delay(2000);
        }
    }
    @Override
    public void removeEffect(Entity target){
        if(isPercentage){
            target.modifyStatPercentage(affectedStat, -modifierValue);
        }else{
            target.modifyStatFlat(affectedStat, (int) -modifierValue);
        }
        target.removeStatusEffect(this);
        System.out.println(name+" boost has faded");
        delay(2000);
    }

    @Override
    public void onTurnStart(Entity target){
        //Something happens at the start of the turn (Very Optional/dk what to do with it lol)
    }
    @Override
    public void onTurnEnd(Entity target){
        decrementDuration();
        if(isExpired){
            removeEffect(target);
        }
    }
}
