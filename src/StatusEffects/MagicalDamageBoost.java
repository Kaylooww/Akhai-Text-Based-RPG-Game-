package StatusEffects;

import Entities.Entity;

public class MagicalDamageBoost extends StatusEffect {
    private double damageMultiplier;

    public MagicalDamageBoost(double damageMultiplier) {
        super("Magical Damage Boost", "Increases magical damage", -1, StatusType.BUFF);
        this.damageMultiplier = damageMultiplier;
    }

    @Override
    public void applyEffect(Entity target) {
        int originalDamage = target.getMagicDamage();
        int bonusDamage = (int)(originalDamage * damageMultiplier);
        target.setMagicDamage(originalDamage + bonusDamage);
    }

    @Override
    public void removeEffect(Entity target) {
        target.setMagicDamage(target.getMagicDamage() - (int)(target.getMagicDamage() * damageMultiplier / (1 + damageMultiplier)));
    }

    @Override
    public void onTurnStart(Entity target) {}

    @Override
    public void onTurnEnd(Entity target) {}

    public double getDamageMultiplier() {
        return damageMultiplier;
    }
}