package Entities.Enemies;

public class MiniBoss extends Boss{
    public MiniBoss(String name, int health, int maxPhase, int attackDamage, int magicDamage, int defense, double physicalResistance, double magicResistance, int speed){
        super(name, health, maxPhase, attackDamage, magicDamage, defense, physicalResistance, magicResistance, speed);
    }
}
