package Skills;

import Entities.Characters.DamageType;
import Entities.Characters.TargetType;
import Entities.Entity;

public class WeaponSkill extends Skill{
    public WeaponSkill(String name, String description, double multiplier, int energyCost, DamageType damageType, TargetType targetType){
        super(name, description, multiplier, energyCost, damageType, targetType);
    }

    @Override
    public int execute(Entity caster){
        double skillDamage = multiplier * (damageType == DamageType.PHYSICAL ? caster.getPhysicalDamage() : caster.getMagicDamage());
        return (int) skillDamage;
    }
}
