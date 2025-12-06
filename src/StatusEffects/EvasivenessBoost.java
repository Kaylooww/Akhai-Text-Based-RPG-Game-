package StatusEffects;

import Entities.Entity;

public class EvasivenessBoost extends StatusEffect {
    private double evasiveness;

    public EvasivenessBoost(double accuracyReduction) {
        super("Evasiveness Boost", "Increases evasiveness", -1, StatusType.BUFF);
        this.evasiveness = accuracyReduction;
    }

    @Override
    public void applyEffect(Entity target) {
        target.setEvasiveness(target.getEvasiveness() - evasiveness);
    }

    @Override
    public void removeEffect(Entity target) {
        target.setEvasiveness(target.getMaxEvasiveness());
    }

    @Override
    public void onTurnStart(Entity target) {

    }

    @Override
    public void onTurnEnd(Entity target) {
    }
}