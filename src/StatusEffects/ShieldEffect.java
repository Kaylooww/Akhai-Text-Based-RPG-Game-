package StatusEffects;

import Entities.Entity;

public class ShieldEffect extends StatusEffect {
    private int shieldAmount;

    public ShieldEffect(int shieldAmount) {
        super("Shield", "Absorbs damage", -1, StatusType.BUFF);
        this.shieldAmount = shieldAmount;
    }

    @Override
    public void applyEffect(Entity target) {
        target.setShield(target.getShield() + shieldAmount);
    }

    @Override
    public void removeEffect(Entity target) {
        target.setShield(target.getShield() - shieldAmount);
    }

    @Override
    public void onTurnStart(Entity target) {}

    @Override
    public void onTurnEnd(Entity target) {}

    public int getShieldAmount() {
        return shieldAmount;
    }
}