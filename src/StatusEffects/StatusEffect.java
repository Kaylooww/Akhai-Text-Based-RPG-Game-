package StatusEffects;

import Entities.Entity;

public abstract class StatusEffect {
    protected String name;
    protected String description;
    protected int duration;
    protected int currentDuration;
    protected StatusType type;
    protected boolean isExpired;

    public StatusEffect(String name, String description,  int duration, StatusType type) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.currentDuration = duration;
        this.type = type;
        this.isExpired = false;
    }

    public abstract void applyEffect(Entity target);
    public abstract void removeEffect(Entity target);
    public abstract void onTurnStart(Entity target);
    public abstract void onTurnEnd(Entity target);

    public void decrementDuration(){
        currentDuration--;
        if(currentDuration <= 0){
            isExpired = true;
        }
    }

    public String getName(){return name;}
    public String getDescription(){return description;}
    public boolean getIsExpired(){return isExpired;}
    public StatusType getType(){return type;}
    public int getDuration() {
        return duration;
    }

    public void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Re-interrupt the thread
            System.err.println("Thread was interrupted during sleep.");
        }
    }
}






