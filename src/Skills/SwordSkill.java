package Skills;

import Entities.Characters.Character;
import Entities.Characters.DamageType;
import Entities.Characters.TargetType;
import Entities.Entity;

public class SwordSkill extends Skill{
    public SwordSkill(String name, String description, double multiplier, int energyCost, DamageType damageType, TargetType targetType){
        super(name, description, multiplier, energyCost, damageType, targetType);
    }

    @Override
    public int execute(Entity caster) {
        if (caster instanceof Entities.Characters.Character) {
            Entities.Characters.Character character = (Character) caster;

            String attackType = "BASIC";
            if (energyCost == 40) attackType = "SKILL";
            else if (energyCost == 80) attackType = "ULTIMATE";

            handleEnergyAndCounters(character, attackType);

            int baseDamage = (damageType == DamageType.PHYSICAL) ? character.getPhysicalDamage() : character.getMagicDamage();

            if(Math.random() <= 0.6){
                //TODO format this
                System.out.println(caster.getName()+" dealt a critical damage!");
                delay(1000);
                return (int)(baseDamage * multiplier * 2);
            }else{
                return (int)(baseDamage * multiplier);
            }
        }
        return 0;
    }
}
