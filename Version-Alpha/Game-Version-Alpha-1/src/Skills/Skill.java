package Skills;

import Entities.Characters.DamageType;
import Entities.Characters.TargetType;
import Entities.Entity;

public abstract class Skill {
    protected String name;
    protected String description;
    protected double multiplier;
    protected int energyCost;
    protected DamageType damageType;
    protected TargetType targetType;

    public Skill(String name, String description, double multiplier, int energyCost, DamageType damageType, TargetType targetType) {
        this.name = name;
        this.description = description;
        this.multiplier = multiplier;
        this.energyCost = energyCost;
        this.damageType = damageType;
        this.targetType = targetType;
    }

    public abstract int execute(Entity caster); //You decide how you use this method zed

    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public int getEnergyCost(){
        return energyCost;
    }

}

