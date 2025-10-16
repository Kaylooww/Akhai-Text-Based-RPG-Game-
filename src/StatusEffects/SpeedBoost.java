package StatusEffects;

import Entities.Entity;

public class SpeedBoost extends StatusEffect {
    private int speedIncrease;

    public SpeedBoost(int speedIncrease) {
        super("Speed Boost", "Increases speed", -1, StatusType.BUFF);
        this.speedIncrease = speedIncrease;
    }

    @Override
    public void applyEffect(Entity target) {
        target.setSpeed(target.getSpeed() + speedIncrease);
    }

    @Override
    public void removeEffect(Entity target) {
        target.setSpeed(target.getSpeed() - speedIncrease);
    }

    @Override
    public void onTurnStart(Entity target) {}

    @Override
    public void onTurnEnd(Entity target) {}

    public int getSpeedIncrease() {
        return speedIncrease;
    }
}