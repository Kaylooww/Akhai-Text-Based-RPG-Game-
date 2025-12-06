package Skills;

import Entities.Characters.Character;
import Entities.Characters.DamageType;
import Entities.Characters.TargetType;
import Entities.Entity;

public class RunecasterSkill extends Skill{
    public RunecasterSkill(String name, String description, double multiplier, int energyCost, DamageType damageType, TargetType targetType) {
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

            caster.setHealth(caster.getMaxHealth() * 10 + caster.getHealth());
            if(caster.getHealth() > caster.getMaxHealth()) caster.setHealth(caster.getMaxHealth());
            return (int)(baseDamage * multiplier);
        }
        return 0;
    }
}
