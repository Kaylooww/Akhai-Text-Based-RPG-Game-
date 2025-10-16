package Entities;//Disregarded

import Entities.Characters.Character;
import Entities.Characters.ClassType;

import java.util.List;
public abstract class Player extends Character {
    public Player(String name, int health, ClassType classType, double basicAttack, double skillAttack, double ultimateAttack) {
        super(name, health, classType, 20, 20, 50, 50.0, 50.0, 50);
    }
}