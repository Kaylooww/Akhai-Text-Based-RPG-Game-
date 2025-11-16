package Skills;

import Entities.Characters.*;
import Entities.Entity;

public class EnemySkill extends Skill {
    public EnemySkill(String name, String description, double multiplier, int energyCost, DamageType damageType, TargetType targetType) {
        super(name, description, multiplier, energyCost, damageType, targetType);
    }

    public int execute(Entity caster) {
        int baseDamage = (getDamageType() == DamageType.PHYSICAL) ?
                caster.getPhysicalDamage() : caster.getMagicDamage();

        double damage = baseDamage * getDamageMultiplier();
        System.out.println(caster.getName() + " uses " + getName() + "!");
        System.out.println(getDescription());

        return (int) damage;
    }
}