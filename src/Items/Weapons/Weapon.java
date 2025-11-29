package Items.Weapons;

import Entities.Characters.Character;
import Items.Item;
import Items.ItemType;
import Items.Rarity;
import Skills.*;
import StatusEffects.StatusEffect;
import TextFormat.ColorUtil;

import java.text.DecimalFormat;

public class Weapon extends Item {
    protected WeaponType weaponType;
    protected boolean isEquipped = false;
    protected Skill basicAttack;
    protected Skill skillAttack;
    protected Skill ultimateAttack;

    public Weapon(){
        itemType = ItemType.WEAPON;
        this.weaponType = null;
        this.basicAttack = null;
        this.skillAttack = null;
        this.ultimateAttack = null;
    }

    //Without Debuffs
    public Weapon(String itemId, String name, WeaponType weaponType, String description, Skill basicAttack, Skill skillAttack, Skill ultimateAttack, int value, Rarity rarity){
        super(itemId, name, description, 1, 0, value, rarity, false, true);
        itemType = ItemType.WEAPON;
        this.weaponType = weaponType;
        this.basicAttack = basicAttack;
        this.skillAttack = skillAttack;
        this.ultimateAttack = ultimateAttack;
    }

    //With Debuffs
    public Weapon(String itemId, String name, WeaponType weaponType, String description, Skill basicAttack, Skill skillAttack, Skill ultimateAttack, StatusEffect statusEffect, int value, Rarity rarity){
        super(itemId, name, description, 1, 1, value, rarity, false, true);
        itemType = ItemType.WEAPON;
        this.weaponType = weaponType;
        this.basicAttack = basicAttack;
        this.skillAttack = skillAttack;
        this.ultimateAttack = ultimateAttack;
    }

    @Override
    public void use(Character player){
        if(isEquippable){
            if(!isEquipped){
                player.equipWeapon(this);
            }else{
                player.unequipWeapon();
            }
        }

    }
    @Override
    public void displayInfo(){
        DecimalFormat df = new DecimalFormat("####");

        System.out.println(ColorUtil.blueBright("╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗"));

        // Center the name
        int totalWidth = 164;
        String visibleName = name.replaceAll("\u001B\\[[;\\d]*m", "");
        int padding = (totalWidth - visibleName.length()) / 2;
        int extraPadding = (totalWidth - visibleName.length()) % 2;

        System.out.println(" ".repeat(padding) + name + " ".repeat(padding + extraPadding));

        System.out.println(ColorUtil.blueBright(" ────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────── "));
        System.out.println(ColorUtil.brightYellowBold("   Skills:"));

        // Basic Attack
        System.out.println(ColorUtil.brightCyanBold("     "+basicAttack.getName() + " (" + df.format(basicAttack.getDamageMultiplier() * 100) + "% DMG)"));
        System.out.println(ColorUtil.cyanBold("\t\t\t" + basicAttack.getDescription()));

        // Skill Attack
        System.out.println(ColorUtil.brightCyanBold("     "+skillAttack.getName() + " (" + df.format(skillAttack.getDamageMultiplier() * 100) + "% DMG)"));
        System.out.println(ColorUtil.cyanBold("\t\t\t" + skillAttack.getDescription()));

        // Ultimate Attack
        System.out.println(ColorUtil.brightCyanBold("     "+ultimateAttack.getName() + " (" + df.format(ultimateAttack.getDamageMultiplier() * 100) + "% DMG)"));
        System.out.println(ColorUtil.cyanBold("\t\t\t" + ultimateAttack.getDescription()));

        System.out.println(ColorUtil.blueBright("╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝\n"));
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

    public boolean getIsEquipped(){
        return isEquipped;
    }

    public void setIsEquipped(boolean isEquipped){
        this.isEquipped = isEquipped;
    }
}
