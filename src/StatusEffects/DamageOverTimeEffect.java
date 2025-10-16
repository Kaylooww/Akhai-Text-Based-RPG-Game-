package StatusEffects;

import Entities.*;
import Entities.Characters.DamageType;

public class DamageOverTimeEffect extends StatusEffect{
    protected DamageType damageType;
    protected int damagePerTurn;

    public DamageOverTimeEffect(String name, String description, int duration, DamageType damageType, int damagePerTurn){
        super(name, description, duration, StatusType.DEBUFF);
        this.damageType = damageType;
        this.damagePerTurn = damagePerTurn;
    }

    @Override
    public void applyEffect(Entity target){
        target.addStatusEffect(this);
    }
    @Override
    public void removeEffect(Entity target){
        target.removeStatusEffect(this);
    }
    @Override
    public void onTurnStart(Entity target){

    }
    @Override
    public void onTurnEnd(Entity target){
        target.takeDamage(damagePerTurn, target.getDefense(), target.getPhysicalResistance(), target.getMagicResistance());
        System.out.println(target.getName()+" took "+damagePerTurn+" damage from "+name+"!");
        decrementDuration();
        if(isExpired){
            removeEffect(target);
        }
    }
}
