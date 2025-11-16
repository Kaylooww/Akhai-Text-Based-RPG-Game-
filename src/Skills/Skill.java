package Skills;

import Entities.Characters.DamageType;
import Entities.Characters.TargetType;
import Entities.Entity;
import Entities.Characters.Character;
import StatusEffects.StatusEffect;

import java.util.ArrayList;
import java.util.List;

public abstract class Skill {
    protected String name;
    protected String description;
    protected double multiplier;
    protected int energyCost;
    protected DamageType damageType;
    protected TargetType targetType;
    StatusEffect statusEffect;

    public Skill(String name, String description, double multiplier, int energyCost, DamageType damageType, TargetType targetType) {
        this.name = name;
        this.description = description;
        this.multiplier = multiplier;
        this.energyCost = energyCost;
        this.damageType = damageType;
        this.targetType = targetType;
    }

    public abstract int execute(Entity caster);

    protected void handleEnergyAndCounters(Character caster, String attackType) {
        switch (attackType) {
            case "BASIC":
                //generates 25 energy and 1 ultimate charge
                caster.generateEnergy(25);
                caster.addUltimateCounter(1);
                break;

            case "SKILL":
                //costs 40 energy and generates 2 ultimate charges
                if (caster.consumeEnergy(40)) {
                    caster.addUltimateCounter(2);
                }
                break;

            case "ULTIMATE":
                //costs 80 energy and consumes all charges
                if (caster.consumeEnergy(80) && caster.isUltimateReady()) {
                    caster.resetUltimateCounter();
                }
                break;
        }
    }

    //energy generation from damage
    public static void generateEnergyFromDamage(Character target) {
        target.generateEnergyFromDamage();
    }
    //UI display (optional)
    public static boolean isUltimateReady(Character caster) {
        return caster.isUltimateReady();
    }
    public static int getUltimateCounter(Character caster) {
        return caster.getUltimateCounter();
    }
    public static int getMaxUltimateCounter() {
        return 8;
    }

    public double getDamageMultiplier() { return multiplier; }
    public DamageType getDamageType() { return damageType; }

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

