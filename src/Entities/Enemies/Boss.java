package Entities.Enemies;

public class Boss extends Enemy{
    int phase = 1;
    int maxPhase;

    public Boss(String name, int health, int maxPhase, int attackDamage, int magicDamage, int defense, double physicalResistance, double magicResistance, int speed){
        super(name, health, attackDamage, magicDamage, defense, physicalResistance, magicResistance, speed);
        this.maxPhase = maxPhase;
    }

    @Override
    public int takeDamage(double damage, int defense, double physicalResistance, double magicResistance) {

        //physical damage type
        double actualDamage = damage * (1 - physicalResistance) - defense; // Simple damage reduction
        if (actualDamage < 1) actualDamage = 1; // Minimum 1 damage

        //magic damage type
        /*double actualDamage = damage * (1 - magicResistance) - defense;
        if (actualDamage < 1) actualDamage = 1;*/

        int oldHealth = health;
        health -= (int) actualDamage;

        if (health <= 0) {
            if(phase <= maxPhase){
                phase++;
                health = oldHealth;
                System.out.println("Entering phase "+phase+"!");
            }else{
                health = 0;
                System.out.println("🎯 " + name + " has been defeated!");
            }
        } else if (health < oldHealth * 0.3) {
            System.out.println("⚠️  " + name + " is looking weak!");
        }

        return (int) actualDamage;
    }
}
