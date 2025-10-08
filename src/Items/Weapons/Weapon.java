package Items.Weapons;

import Entities.Characters.Character;
import Items.Item;
import Items.Rarity;
import Skills.*;

public class Weapon extends Item {
    protected WeaponType weaponType;
    protected Skill basicAttack;
    protected Skill skillAttack;
    protected Skill ultimateAttack;

    public Weapon(){
        this.weaponType = null;
        this.basicAttack = null;
        this.skillAttack = null;
        this.ultimateAttack = null;
    }

    public Weapon(String itemId, String name, WeaponType weaponType, String description, Skill basicAttack, Skill skillAttack, Skill ultimateAttack, int value, Rarity rarity){
        super(itemId, name, description, 1, 1, value, rarity, false, true);
        this.weaponType = weaponType;
        this.basicAttack = basicAttack;
        this.skillAttack = skillAttack;
        this.ultimateAttack = ultimateAttack;
    }

    @Override
    public void use(Character player){
        if(!isUsable){
           System.out.println(name + " can't be used.");
        }
    }
    @Override
    public void displayInfo(){
        System.out.println(name+"\n"+description);
        System.out.println("Skills:");
        System.out.println(basicAttack.getName());
        System.out.println("\t"+basicAttack.getDescription());
        System.out.println(skillAttack.getName());
        System.out.println("\t"+skillAttack.getDescription());
        System.out.println(ultimateAttack.getName());
        System.out.println("\t"+ultimateAttack.getDescription());
    }

    public Skill getBasicAttack(){
        return basicAttack;
    }
    public Skill getSkillAttack(){
        return skillAttack;
    }
    public Skill getUltimateAttack(){
        return ultimateAttack;
    }
}
