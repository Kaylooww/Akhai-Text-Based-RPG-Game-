package Skills;

import Entities.Characters.*;
import Entities.Entity;

public class EnemySkill extends Skill{
    public EnemySkill(String name, String description, double multiplier, int energyCost, DamageType damageType, TargetType targetType){
        super(name, description, multiplier, energyCost, damageType, targetType);
    }

    //TODO executes the skill (damaging the caster) as an enemy -For Zed
    public int execute(Entity caster){
        return 1;
    }
}
