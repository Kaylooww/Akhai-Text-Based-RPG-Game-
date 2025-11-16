package StatusEffects;

import Entities.Entity;

public class EvasivenessBoost extends StatusEffect {
    private double accuracyReduction;

    public EvasivenessBoost(double accuracyReduction) {
        super("Evasiveness Boost", "Reduces enemy accuracy", -1, StatusType.BUFF);
        this.accuracyReduction = accuracyReduction;
    }

    @Override
    public void applyEffect(Entity target) {}

    @Override
    public void removeEffect(Entity target) {}

    @Override
    public void onTurnStart(Entity target) {}

    @Override
    public void onTurnEnd(Entity target) {}

    public double getAccuracyReduction() {
        return accuracyReduction;
    }
}