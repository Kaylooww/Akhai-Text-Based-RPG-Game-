package Skills;

import Entities.Characters.DamageType;
import Entities.Characters.TargetType;
import Entities.Entity;
import Entities.Characters.Character;

public class WeaponSkill extends Skill{
    public WeaponSkill(String name, String description, double multiplier, int energyCost, DamageType damageType, TargetType targetType){
        super(name, description, multiplier, energyCost, damageType, targetType);
    }

    @Override
    public int execute(Entity caster) {
        if (caster instanceof Character) {
            Character character = (Character) caster;

            String attackType = "BASIC";
            if (energyCost == 40) attackType = "SKILL";
            else if (energyCost == 80) attackType = "ULTIMATE";

            handleEnergyAndCounters(character, attackType);

            int baseDamage = (damageType == DamageType.PHYSICAL) ? character.getPhysicalDamage() : character.getMagicDamage();

            return (int)(baseDamage * multiplier);
        }
        return 0;
    }
}
