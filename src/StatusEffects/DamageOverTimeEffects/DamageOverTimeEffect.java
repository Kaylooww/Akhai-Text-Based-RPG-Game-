package StatusEffects.DamageOverTimeEffects;

import Entities.*;
import Entities.Characters.DamageType;
import StatusEffects.StatusEffect;
import StatusEffects.StatusType;

public class DamageOverTimeEffect extends StatusEffect {
    protected DamageType damageType;
    protected int damagePerTurn;

    public DamageOverTimeEffect(String name, String description, int duration, DamageType damageType, int damagePerTurn){
        super(name, description, duration, StatusType.DEBUFF);
        this.damageType = damageType;
        this.damagePerTurn = damagePerTurn;
    }

    @Override
    public void applyEffect(Entity target){
        if(!target.hasEffect(this)){
            target.addStatusEffect(this);
            System.out.println(target.getName()+" has been inflicted by "+name+"!");
            delay(2000);
        }else{
            System.out.println(name+" effect has already been applied");
            delay(2000);
        }
    }
    @Override
    public void removeEffect(Entity target){
        target.removeStatusEffect(this);
        System.out.println(name+" effect has faded");
        delay(2000);
    }
    @Override
    public void onTurnStart(Entity target){
        //Something happens at the start of the turn (Very Optional/dk what to do with it lol)
    }
    @Override
    public void onTurnEnd(Entity target){
        int actualDamage = target.takeDamage(damagePerTurn, target.getDefense(), target.getPhysicalResistance(), target.getMagicResistance());
        System.out.println(target.getName()+" took "+actualDamage+" damage from "+name+"!");
        decrementDuration();
        if(isExpired){
            removeEffect(target);
        }
    }
}
