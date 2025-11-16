package StatusEffects;

import Entities.Entity;

public class PhysicalDamageBoost extends StatusEffect {
    private double damageMultiplier;

    public PhysicalDamageBoost(double damageMultiplier) {
        super("Physical Damage Boost", "Increases physical damage", -1, StatusType.BUFF);
        this.damageMultiplier = damageMultiplier;
    }

    @Override
    public void applyEffect(Entity target) {
        int originalDamage = target.getPhysicalDamage();
        int bonusDamage = (int)(originalDamage * damageMultiplier);
        target.setPhysicalDamage(originalDamage + bonusDamage);
    }

    @Override
    public void removeEffect(Entity target) {
        target.setPhysicalDamage(target.getPhysicalDamage() - (int)(target.getPhysicalDamage() * damageMultiplier / (1 + damageMultiplier)));
    }

    @Override
    public void onTurnStart(Entity target) {}

    @Override
    public void onTurnEnd(Entity target) {}

    public double getDamageMultiplier() {
        return damageMultiplier;
    }
}