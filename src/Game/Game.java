package Game;

import Entities.Enemies.Enemy;
import Entities.Characters.*;
import Entities.Characters.Character;
import Entities.Entity;
import Items.*;
import Items.Weapons.*;
import NPC.*;
import Skills.WeaponSkill;
import StatusEffects.DamageOverTimeEffects.*;
import StatusEffects.StatusEffect;
import TextFormat.ColorUtil;

import java.util.*;

public class Game {
    private Scanner scan = new Scanner(System.in);
    private Character player;
    private List<Enemy> enemies = new ArrayList<>();
    private List<NPC> npcs = new ArrayList<>();
    private List<Item>  items = new ArrayList<>();
    private List<StatusEffect> status = new ArrayList<>();
    private int currentChapter = 1;
    private boolean gameRunning = true;
    private boolean inBattle = false;
    private final int MAX_LEVEL = 6;
    private boolean[] levelsCompleted = new boolean[MAX_LEVEL + 1];

    //Game
    public void initializeGame() {
        //npcs and enemies need to be finalized
        npcs.add(new GuideNPC("Frank", "Companion"));
        npcs.add(new BossNPC("Zed", "Edge-Lord Math geek boss"));
        npcs.add(new FortuneTellerNPC("Kyle", "Shopkeeper"));

        //add enemies in relation to the current level of the game
        enemies.add(new Enemy("Desert Clause", 220, 20, 30, 10, 0.10, 0.10, 20));
        enemies.add(new Enemy("Sand Stalker", 210, 20, 30, 10, 0.10, 0.10, 20));
        enemies.add(new Enemy("Dune Crawler", 200, 20, 30, 10, 0.10, 0.10, 20));

        //Status Effects
        status.add(new DamageOverTimeEffect("Poison", "Deals poison damage", 3,  DamageType.MAGICAL, 30));
        status.add(new DamageOverTimeEffect("Burn", "Deals burn damage", 3,  DamageType.MAGICAL, 30));

        //Items (LEGENDARY Items should be very rare)
        items.add(new HealingPotion("HP001", "Lesser Healing Potion", "<Empty>", 12, 1, 5, Rarity.COMMON, 50));
        items.add(new HealingPotion("HP002", "Healing Potion", "<Empty>", 12, 1, 10, Rarity.RARE, 75));
        items.add(new HealingPotion("HP003", "Greater Healing Potion", "<Empty>", 12, 1, 20, Rarity.EPIC, 100));
        items.add(new HealingPotion("HP004", "Legendary Healing Potion", "<Empty>", 12, 1, 40, Rarity.LEGENDARY, 200));

        items.add(new EnergyPotion("EP001", "Lesser Energy Potion", "<Empty>", 12, 1, 3, Rarity.COMMON, 40));
        items.add(new EnergyPotion("EP002", "Energy Potion", "<Empty>", 12, 1, 9, Rarity.RARE, 60));
        items.add(new EnergyPotion("EP003", "Greater Energy Potion", "<Empty>", 12, 1, 15, Rarity.EPIC, 80));
        items.add(new EnergyPotion("EP004", "Legendary Energy Potion", "<Empty>", 12, 1, 35, Rarity.LEGENDARY, 100));

        items.add(new ShieldPotion("SHP001", "Lesser Shield Potion", "<Empty>", 12, 1, 8, Rarity.COMMON, 60));
        items.add(new ShieldPotion("SHP002", "Shield Potion", "<Empty>", 12, 1, 18, Rarity.RARE, 80));
        items.add(new ShieldPotion("SHP003", "Greater Shield Potion", "<Empty>", 12, 1, 25, Rarity.EPIC, 120));
        items.add(new ShieldPotion("SHP004", "Legendary Shield Potion", "<Empty>", 12, 1, 50, Rarity.LEGENDARY, 200));

        items.add(new PhysicalDamagePotion("PDP001", "Lesser Physical Potion", "<Empty>", 12, 1, 4, Rarity.COMMON, 0.24));
        items.add(new PhysicalDamagePotion("PDP002", "Physical Potion", "<Empty>", 12, 1, 9, Rarity.RARE, 0.36));
        items.add(new PhysicalDamagePotion("PDP003", "Greater Physical Potion", "<Empty>", 12, 1, 18, Rarity.EPIC, 0.48));
        items.add(new PhysicalDamagePotion("PDP004", "Legendary Physical Potion", "<Empty>", 12, 1, 35, Rarity.LEGENDARY, 0.60));

        items.add(new MagicalDamagePotion("MDP001", "Lesser Magic Potion", "<Empty>", 12, 1, 4, Rarity.COMMON, 0.24));
        items.add(new MagicalDamagePotion("MDP002", "Magic Potion", "<Empty>", 12, 1, 9, Rarity.RARE, 0.36));
        items.add(new MagicalDamagePotion("MDP003", "Greater Magic Potion", "<Empty>", 12, 0, 18, Rarity.EPIC, 0.48));
        items.add(new MagicalDamagePotion("MDP004", "Legendary Magic Potion", "<Empty>", 12, 0, 35, Rarity.LEGENDARY, 0.60));

        items.add(new SpeedPotion("SP001", "Lesser Speed Potion", "<Empty>", 12, 1, 5, Rarity.COMMON, 4));
        items.add(new SpeedPotion("SP002", "Speed Potion", "<Empty>", 12, 1, 10, Rarity.RARE, 8));
        items.add(new SpeedPotion("SP003", "Greater Speed Potion", "<Empty>", 12, 1, 20, Rarity.EPIC, 12));
        items.add(new SpeedPotion("SP004", "Legendary Speed Potion", "<Empty>", 12, 1, 40, Rarity.LEGENDARY, 20));

        items.add(new EvasivenessPotion("EVP001", "Lesser Evasiveness Potion", "Slightly reduces enemy accuracy", 12, 1, 5, Rarity.COMMON, 0.05));
        items.add(new EvasivenessPotion("EVP002", "Evasiveness Potion", "Reduces enemy accuracy", 12, 1, 10, Rarity.RARE, 0.10));
        items.add(new EvasivenessPotion("EVP003", "Greater Evasiveness Potion", "Greatly reduces enemy accuracy", 12, 1, 20, Rarity.EPIC, 0.15));
        items.add(new EvasivenessPotion("EVP004", "Legendary Evasiveness Potion", "Massively reduces enemy accuracy", 12, 1, 40, Rarity.LEGENDARY, 0.25));

        //Tier 1 (COMMON) Weapons and Varieties
        items.add(new Weapon("BW001.1", "Wooden Bow", WeaponType.BOW, "", new WeaponSkill("Basic Attack", "", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE), 20, Rarity.COMMON));
        items.add(new Weapon("SW001.1", "Bronze Sword", WeaponType.SWORD, "", new WeaponSkill("Basic Attack", "", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE), 20, Rarity.COMMON));
        items.add(new Weapon("MGS001.1", "Wooden Staff", WeaponType.MAGIC_STAFF, "", new WeaponSkill("Basic Attack", "", 1.15, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.3, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.7, 80, DamageType.MAGICAL, TargetType.SINGLE), 20, Rarity.COMMON));
        items.add(new Weapon("BS001.1", "Iron Broadsword", WeaponType.BROADSWORD, "", new WeaponSkill("Basic Attack", "", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE), 20, Rarity.COMMON));
        items.add(new Weapon("DR001.1", "Rusty Dagger", WeaponType.DAGGER, "", new WeaponSkill("Basic Attack", "", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE), 20, Rarity.COMMON));
        items.add(new Weapon("MGSW001.1", "Rookie's Magic Sword", WeaponType.MAGIC_SWORD, "", new WeaponSkill("Basic Attack", "", 1.15, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.3, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.7, 80, DamageType.MAGICAL, TargetType.SINGLE), 20, Rarity.COMMON));

        items.add(new Weapon("BW001.2", "Simple Bow", WeaponType.BOW, "", new WeaponSkill("Basic Attack", "", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE), 20, Rarity.COMMON));
        items.add(new Weapon("SW001.2", "Traveler’s Sword", WeaponType.SWORD, "", new WeaponSkill("Basic Attack", "", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE), 20, Rarity.COMMON));
        items.add(new Weapon("MGS001.2", "Apprentice’s Staff", WeaponType.MAGIC_STAFF, "", new WeaponSkill("Basic Attack", "", 1.15, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.3, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.7, 80, DamageType.MAGICAL, TargetType.SINGLE), 20, Rarity.COMMON));
        items.add(new Weapon("BS001.2", "Worn Broadsword", WeaponType.BROADSWORD, "", new WeaponSkill("Basic Attack", "", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE), 20, Rarity.COMMON));
        items.add(new Weapon("DR001.2", "Rusty Knife", WeaponType.DAGGER, "", new WeaponSkill("Basic Attack", "", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE), 20, Rarity.COMMON));
        items.add(new Weapon("MGSW001.2", "Faintblade", WeaponType.MAGIC_SWORD, "", new WeaponSkill("Basic Attack", "", 1.15, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.3, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.7, 80, DamageType.MAGICAL, TargetType.SINGLE), 20, Rarity.COMMON));

        //Tier 2 (RARE) Weapons and Varieties
        items.add(new Weapon("BW002.1", "Iron Bow", WeaponType.BOW, "", new WeaponSkill("Basic Attack", "", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE), 80, Rarity.RARE));
        items.add(new Weapon("SW002.1", "Steel Sword", WeaponType.SWORD, "", new WeaponSkill("Basic Attack", "", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE), 80, Rarity.RARE));
        items.add(new Weapon("MGS002.1", "Crystal Staff", WeaponType.MAGIC_STAFF, "", new WeaponSkill("Basic Attack", "", 1.25, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.5, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.9, 80, DamageType.MAGICAL, TargetType.SINGLE), 80, Rarity.RARE));
        items.add(new Weapon("BS002.1", "Steel Broadsword", WeaponType.BROADSWORD, "", new WeaponSkill("Basic Attack", "", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE), 80, Rarity.RARE));
        items.add(new Weapon("DR002.1", "Iron Dagger", WeaponType.DAGGER, "", new WeaponSkill("Basic Attack", "", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE), 80, Rarity.RARE));
        items.add(new Weapon("MGSW002.1", "Apprentice's Magic Sword", WeaponType.MAGIC_SWORD, "", new WeaponSkill("Basic Attack", "", 1.25, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.5, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.9, 80, DamageType.MAGICAL, TargetType.SINGLE), 80, Rarity.RARE));

        items.add(new Weapon("BW002.2", "Hunter’s Bow", WeaponType.BOW, "", new WeaponSkill("Basic Attack", "", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE), 80, Rarity.RARE));
        items.add(new Weapon("SW002.2", "Soldier’s Sword", WeaponType.SWORD, "", new WeaponSkill("Basic Attack", "", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE), 80, Rarity.RARE));
        items.add(new Weapon("MGS002.2", "Mystic Staff", WeaponType.MAGIC_STAFF, "", new WeaponSkill("Basic Attack", "", 1.25, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.5, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.9, 80, DamageType.MAGICAL, TargetType.SINGLE), 80, Rarity.RARE));
        items.add(new Weapon("BS002.2", "Battleforged Broadsword", WeaponType.BROADSWORD, "", new WeaponSkill("Basic Attack", "", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE), 80, Rarity.RARE));
        items.add(new Weapon("DR002.2", "Swiftstrike Dagger", WeaponType.DAGGER, "", new WeaponSkill("Basic Attack", "", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE), 80, Rarity.RARE));
        items.add(new Weapon("MGSW002.2", "Arcsteel Blade", WeaponType.MAGIC_SWORD, "", new WeaponSkill("Basic Attack", "", 1.25, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.5, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.9, 80, DamageType.MAGICAL, TargetType.SINGLE), 80, Rarity.RARE));

        items.add(new Weapon("BW002.3", "Reinforced Bow", WeaponType.BOW, "", new WeaponSkill("Basic Attack", "", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE), 80, Rarity.RARE));
        items.add(new Weapon("SW002.3", "Tempered Sword", WeaponType.SWORD, "", new WeaponSkill("Basic Attack", "", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE), 80, Rarity.RARE));
        items.add(new Weapon("MGS002.3", "Enchanter’s Staff", WeaponType.MAGIC_STAFF, "", new WeaponSkill("Basic Attack", "", 1.25, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.5, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.9, 80, DamageType.MAGICAL, TargetType.SINGLE), 80, Rarity.RARE));
        items.add(new Weapon("BS002.3", "Knight’s Broadsword", WeaponType.BROADSWORD, "", new WeaponSkill("Basic Attack", "", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE), 80, Rarity.RARE));
        items.add(new Weapon("DR002.3", "Steelfang Dagger", WeaponType.DAGGER, "", new WeaponSkill("Basic Attack", "", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE), 80, Rarity.RARE));
        items.add(new Weapon("MGSW002.3", "Spellforged Blade", WeaponType.MAGIC_SWORD, "", new WeaponSkill("Basic Attack", "", 1.25, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.5, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 1.9, 80, DamageType.MAGICAL, TargetType.SINGLE), 80, Rarity.RARE));

        //Tier 3 (EPIC) Weapons including Varieties
        items.add(new Weapon("BW003.1", "Elven Bow", WeaponType.BOW, "", new WeaponSkill("Basic Attack", "", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("SW003.1", "Mythril Sword", WeaponType.SWORD, "", new WeaponSkill("Basic Attack", "", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("MGS003.1", "Arcane Staff", WeaponType.MAGIC_STAFF, "", new WeaponSkill("Basic Attack", "", 1.4, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.7, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 2.2, 80, DamageType.MAGICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("BS003.1", "Runed Broadsword", WeaponType.BROADSWORD, "", new WeaponSkill("Basic Attack", "", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("DR003.1", "Shadow Dagger", WeaponType.DAGGER, "", new WeaponSkill("Basic Attack", "", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("MGSW003.1", "Runic Magic Sword", WeaponType.MAGIC_SWORD, "", new WeaponSkill("Basic Attack", "", 1.4, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.7, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 2.2, 80, DamageType.MAGICAL, TargetType.SINGLE), 200, Rarity.EPIC));

        items.add(new Weapon("BW003.2", "Phoenix Bow", WeaponType.BOW, "", new WeaponSkill("Basic Attack", "", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("SW003.2", "Tempest Sword", WeaponType.SWORD, "", new WeaponSkill("Basic Attack", "", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("MGS003.2", "Soulbinder Staff", WeaponType.MAGIC_STAFF, "", new WeaponSkill("Basic Attack", "", 1.4, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.7, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 2.2, 80, DamageType.MAGICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("BS003.2", "Frostbane Broadsword", WeaponType.BROADSWORD, "", new WeaponSkill("Basic Attack", "", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("DR003.2", "Bloodthorn Dagger", WeaponType.DAGGER, "", new WeaponSkill("Basic Attack", "", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("MGSW003.2", "Lunaris Blade", WeaponType.MAGIC_SWORD, "", new WeaponSkill("Basic Attack", "", 1.4, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.7, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 2.2, 80, DamageType.MAGICAL, TargetType.SINGLE), 200, Rarity.EPIC));

        items.add(new Weapon("BW003.3", "Moonpiercer Bow", WeaponType.BOW, "", new WeaponSkill("Basic Attack", "", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("SW003.3", "Flameheart Sword", WeaponType.SWORD, "", new WeaponSkill("Basic Attack", "", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("MGS003.3", "Crimson Staff", WeaponType.MAGIC_STAFF, "", new WeaponSkill("Basic Attack", "", 1.4, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.7, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 2.2, 80, DamageType.MAGICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("BS003.3", "Earthshatter Broadsword", WeaponType.BROADSWORD, "", new WeaponSkill("Basic Attack", "", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("DR003.3", "Nightshade Dagger", WeaponType.DAGGER, "", new WeaponSkill("Basic Attack", "", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("MGSW003.3", "Aetherfang Blade", WeaponType.MAGIC_SWORD, "", new WeaponSkill("Basic Attack", "", 1.4, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 1.7, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 2.2, 80, DamageType.MAGICAL, TargetType.SINGLE), 200, Rarity.EPIC));

        //Tier 4 (LEGENDARY) Weapons including Varieties
        items.add(new Weapon("BW004.1", "Seraphic Longbow", WeaponType.BOW, "", new WeaponSkill("Basic Attack", "", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("SW004.1", "Demon King’s Sword", WeaponType.SWORD, "", new WeaponSkill("Basic Attack", "", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("MGS004.1", "Abyssal Staff", WeaponType.MAGIC_STAFF, "", new WeaponSkill("Basic Attack", "", 1.7, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 2.2, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 3.0, 80, DamageType.MAGICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("BS004.1", "Crimson Eclipse Greatsword", WeaponType.BROADSWORD, "", new WeaponSkill("Basic Attack", "", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("DR004.1", "Reaper’s Fang", WeaponType.DAGGER, "", new WeaponSkill("Basic Attack", "", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("MGSW004.1", "Voidbreaker Blade", WeaponType.MAGIC_SWORD, "", new WeaponSkill("Basic Attack", "", 1.7, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 2.2, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 3.0, 80, DamageType.MAGICAL, TargetType.SINGLE), 200, Rarity.EPIC));

        items.add(new Weapon("BW004.2", "Dawnseeker", WeaponType.BOW, "", new WeaponSkill("Basic Attack", "", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("SW004.2", "Oblivion’s Edge", WeaponType.SWORD, "", new WeaponSkill("Basic Attack", "", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("MGS004.2", "Vermilion Aegis", WeaponType.MAGIC_STAFF, "", new WeaponSkill("Basic Attack", "", 1.7, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 2.2, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 3.0, 80, DamageType.MAGICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("BS004.2", "Draconic Cleaver", WeaponType.BROADSWORD, "", new WeaponSkill("Basic Attack", "", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("DR004.2", "Silent Requiem", WeaponType.DAGGER, "", new WeaponSkill("Basic Attack", "", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("MGSW004.2", "Celestia’s Wrath", WeaponType.MAGIC_SWORD, "", new WeaponSkill("Basic Attack", "", 1.7, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 2.2, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 3.0, 80, DamageType.MAGICAL, TargetType.SINGLE), 200, Rarity.EPIC));

        items.add(new Weapon("BW004.3", "Heaven Piercer", WeaponType.BOW, "", new WeaponSkill("Basic Attack", "", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("SW004.3", "Sky Splitter", WeaponType.SWORD, "", new WeaponSkill("Basic Attack", "", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("MGS004.3", "Orb of Avarice", WeaponType.MAGIC_STAFF, "", new WeaponSkill("Basic Attack", "", 1.7, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 2.2, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 3.0, 80, DamageType.MAGICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("BS004.3", "Demon Dweller", WeaponType.BROADSWORD, "", new WeaponSkill("Basic Attack", "", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("DR004.3", "Death's Gaze", WeaponType.DAGGER, "", new WeaponSkill("Basic Attack", "", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("MGSW004.3", "Archangel's Judgement", WeaponType.MAGIC_SWORD, "", new WeaponSkill("Basic Attack", "", 1.7, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 2.2, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 3.0, 80, DamageType.MAGICAL, TargetType.SINGLE), 200, Rarity.EPIC));

        //Hidden Tier (MYTHICAL) Weapons [Hehe I may or may not have made this a bit too Over-Powered] -zed
        items.add(new Weapon("BW005", "Recurve of Eternal Fate", WeaponType.BOW, "", new WeaponSkill("Basic Attack", "", 2.5, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 3.2, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 4.5, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("SW005", "Dawn of the Era Nova", WeaponType.SWORD, "", new WeaponSkill("Basic Attack", "", 2.5, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 3.2, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 4.5, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("MGS005", "Tome of the Celestial Codex", WeaponType.MAGIC_STAFF, "", new WeaponSkill("Basic Attack", "", 2.5, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 3.2, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 4.5, 80, DamageType.MAGICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("BS005", "Cleave of the Oblivion's Edge", WeaponType.BROADSWORD, "", new WeaponSkill("Basic Attack", "", 2.5, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 3.2, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 4.5, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("DR005", "Void of the Forsaken Rift", WeaponType.DAGGER, "", new WeaponSkill("Basic Attack", "", 2.5, 0, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 3.2, 40, DamageType.PHYSICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 4.5, 80, DamageType.PHYSICAL, TargetType.SINGLE), 200, Rarity.EPIC));
        items.add(new Weapon("MGSW005", "Chronoblade of the Severed Realm", WeaponType.MAGIC_SWORD, "", new WeaponSkill("Basic Attack", "", 2.5, 0, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Skill Attack", "", 3.2, 40, DamageType.MAGICAL, TargetType.SINGLE), new WeaponSkill("Ultimate Attack", "", 4.5, 80, DamageType.MAGICAL, TargetType.SINGLE), 200, Rarity.EPIC));

        System.out.println("Welcome to Akhai!");
        delay(1000);
        System.out.println("Choose your class:");
        System.out.println("1. Hawkseye");
        System.out.println("2. Blademaster");
        System.out.println("3. Runecaster");
        System.out.println("4. Berserker");
        System.out.println("5. Shinobi");
        System.out.println("6. Rune Knight");
        System.out.println("7. Jinwoo Sun (All classes) - Test Class");

        int choice = getIntInput("Enter your choice (1-7): ", 1, 7);

        switch (choice) {
            case 1:
                player = new Hawkseye("Hawkseye");
                break;
            case 2:
                player = new Blademaster("Blademaster");
                break;
            case 3:
                player = new Runecaster("Runecaster");
                break;
            case 4:
                player = new Berserker("Berserker");
                break;
            case 5:
                player = new Shinobi("Shinobi");
                break;
            case 6:
                player = new RuneKnight("RuneKnight");
                break;
            case 7:
                player = new JinwooSun();
                break;
        }

        System.out.println("You have chosen " + player.getName() + "!");
        delay(2000);

        System.out.println("\n=== Beginning your adventure in Akhai ===");
        System.out.println("You are on a quest to find the power to go back to your world.");
        delay(3000);
    }
    public void startGame() {
        addStarterPack(player, items);

        while(gameRunning) {
            displayLevelMap();
            handleLevelActions();

            if (currentChapter > 6) { // Assuming 6 levels based on the map
                System.out.println("Congratulations! You have completed the game!");
                gameRunning = false;
            }
        }
    }

    //Exploration
    //Tarongon nato map
    public void displayLevelMap() {
        System.out.println("\n🗺️  === CURRENT MAP ===");
        System.out.println("Progress: " + getCompletionPercentage() + "% complete");

        for (int i = 1; i <= MAX_LEVEL; i++) {
            String status = (i == currentChapter) ? "📍 CURRENT" :
                    (levelsCompleted[i]) ? "✅ COMPLETED" : "🔒 LOCKED";
            System.out.println("Level " + i + ": " + getLevelName(i) + " - " + status);
        }
        System.out.println("======================");
        delay(1000);
    }
    private String getLevelName(int level) {
        switch(level) {
            case 1: return "Whispering Crossroads";
            case 2: return "Fungal Lumewood Forest";
            case 3: return "Ruins of a Fallen Star";
            case 4: return "Crystal Caverns";
            case 5: return "Root of All Worlds";
            case 6: return "Final Showdown";
            default: return "Unknown Territory";
        }
    }
    private String getCompletionPercentage() {
        int completed = 0;
        for (int i = 1; i <= MAX_LEVEL; i++) {
            if (levelsCompleted[i]) completed++;
        }
        return String.format("%.1f", (completed / (double)MAX_LEVEL) * 100);
    }
    public void handleLevelActions() {
        System.out.println("\n🎯 What would you like to do?");
        System.out.println("[1] Explore");
        System.out.println("[2] Check stats");
        System.out.println("[3] Open Inventory");
        System.out.println("[4] Check resurrection status");
        System.out.println("[5] Talk to NPC");
        System.out.println("[6] Proceed to Story");
        System.out.println("[7] Quit game");

        int choice = getIntInput("Enter your choice: ", 1, 7);

        switch (choice) {
            case 1:
                explore();
                break;
            case 2:
                player.displayStats();
                break;
            case 3:
                //player.displayInventory();
                openInventory(player);
                break;
            case 4:
                checkResurrectionStatus();
                break;
            case 5:
                talkToNPC();
                break;
            case 6:
                attemptLevelProgression();
                break;
            case 7:
                gameRunning = false;
                System.out.println("Thanks for playing Akhai!");
                break;
        }
    }
    public void explore() {
        System.out.println("\nYou explore " + getLevelName(currentChapter) + "...");
        delay(1000);

        // Different encounter rates based on level
        double encounterRate = 0.6 + (currentChapter * 0.05);
        if (Math.random() <= encounterRate) {
            System.out.println("🚨 You encountered an enemy!");
            delay(1000);
            battle();
        } else {
            // Chance for finding items or hidden events
            if (Math.random() > 0.7) {
                findHiddenTreasure();
                delay(1000);
            } else {
                System.out.println("You found nothing of interest.");
                delay(1000);
            }
        }
    }
    private void findHiddenTreasure() {
        Random rnd = new Random();
        System.out.println("💎 You found a hidden treasure!");
        int expBonus = currentChapter * rnd.nextInt(5, 11);
        player.gainExperience(expBonus);
        System.out.println("Gained " + expBonus + " bonus experience!");
    }
    private void attemptLevelProgression() {
        if (currentChapter >= MAX_LEVEL) {
            System.out.println("🎉 Congratulations! You've completed all levels!");
            return;
        }

        // Check if player is strong enough to progress
        if (player.getLevel() < currentChapter * 2) {
            System.out.println("⚠️  You're not strong enough to face the challenges ahead!");
            System.out.println("Recommended level for next area: " + (currentChapter * 2));
            System.out.println("Your current level: " + player.getLevel());
            return;
        }

        System.out.println("🚀 Moving to " + getLevelName(currentChapter + 1) + "...");
        levelsCompleted[currentChapter++] = true;
        //currentLevel++;

        // Scale enemies for new level
        scaleEnemiesForCurrentLevel();
        System.out.println("New challenges await in " + getLevelName(currentChapter) + "!");
    }
    public void talkToNPC() {
        System.out.println("Which NPC would you like to talk to?");
        for (int i = 0; i < npcs.size(); i++) {
            System.out.println((i + 1) + ". " + npcs.get(i).getName() + " - " + npcs.get(i).getDescription());
        }

        int choice = getIntInput("Enter your choice: ", 1, npcs.size());
        NPC selectedNPC = npcs.get(choice - 1);

        System.out.println("You approach " + selectedNPC.getName() + "...");
        selectedNPC.interact();
    }

    //Battle
    public void battle() {
        inBattle = true;
        int turns = 1;
        Enemy enemy = enemies.get((int)(Math.random() * enemies.size()));
        System.out.println("🚨 A wild " + enemy.getName() + " appears!");
        delay(1000);

        int baseExp = 25 + (currentChapter * 5);

        int playerCurrentSpeed = player.getSpeed();
        int playerOriginalSpeed = player.getSpeed();
        int enemyCurrentSpeed = enemy.getSpeed();
        int enemyOriginalSpeed = enemy.getSpeed();

        //Reset Ult charges
        player.resetUltimateCounter();

        while (enemy.getHealth() > 0 && player.getHealth() > 0 && inBattle) {
            String playerHealthBar = createHealthBar(player.getHealth(), player.getMaxHealth(), 20);
            String playerEnergyBar = createEnergyBar(player.getEnergy(), player.getMaxEnergy(), 20);
            String enemyHealthBar =  createHealthBar(enemy.getHealth(), enemy.getMaxHealth(), 20);

            System.out.println("\n========== TURN "+(turns++)+" ==========");
            System.out.println("\t\t"+enemy.getName());
            System.out.println(ColorUtil.red(enemyHealthBar));
            System.out.println("\n\t\t"+player.getName());
            System.out.println(ColorUtil.green(playerHealthBar));
            System.out.println(playerEnergyBar);

            boolean isPlayerTurn = checkSpeed(playerCurrentSpeed, enemyCurrentSpeed);
            System.out.println((isPlayerTurn ? player.getName() : enemy.getName()) + "'s turn!");

            //Take action based on who's acting
            int damage = takeAction(isPlayerTurn, enemy);

            //Update speed counters after action
            if (damage != -1) {
                if (isPlayerTurn) {
                    playerCurrentSpeed -= enemyCurrentSpeed;
                    if (enemyCurrentSpeed < enemyOriginalSpeed) {
                        enemyCurrentSpeed += enemyOriginalSpeed;
                    }
                } else {
                    enemyCurrentSpeed -= playerCurrentSpeed;
                    if (playerCurrentSpeed < playerOriginalSpeed) {
                        playerCurrentSpeed += playerOriginalSpeed;
                    }

                    if (damage > 0) {
                        player.generateEnergyFromDamage();
                    }
                }
            }

            //Check if player died and can resurrect
            if (!isPlayerTurn && player.getHealth() <= 0 && !player.hasResurrected()) {
                System.out.println("\n💫 The power of resurrection is available...");
                delay(1000);
                System.out.println("Would you like to use your one-time resurrection?");
                delay(500);
                System.out.println("[1] Yes, resurrect and continue fighting!");
                System.out.println("[2] No, accept defeat");

                int resurrectChoice = getIntInput("Choose: ", 1, 2);
                if (resurrectChoice == 1) {
                    player.resurrect();
                    // Player gets a free turn after resurrection
                    System.out.println("\n⭐ " + player.getName() + " gets a free attack after resurrection!");
                    damage = player.getBasicAttack().execute(player);
                    int actualDamage = enemy.takeDamage(damage, enemy.getDefense(), enemy.getPhysicalResistance(), enemy.getMagicResistance());
                    System.out.println("You dealt " + actualDamage + " damage to " + enemy.getName());

                    //Reset player speed after resurrection free turn
                    playerCurrentSpeed = playerOriginalSpeed;
                } else {
                    System.out.println("You accept your fate...");
                }
            }
            player.checkStatusEffect();
            enemy.checkStatusEffect();
        }
        playerHealthCheck(enemy, baseExp);

        // Clear battle effects after combat
        clearBattleEffects(enemy);

        inBattle = false;
    }

    public class AccuracySystem {
        public static final double PLAYER_BASE_ACCURACY = 0.95;
        public static final double ENEMY_BASE_ACCURACY = 0.90;

        public static boolean playerHits() {
            return Math.random() <= PLAYER_BASE_ACCURACY;
        }

        public static boolean enemyHits() {
            return Math.random() <= ENEMY_BASE_ACCURACY;
        }
    }

    private String createHealthBar(int currentHealth, int maxHealth, int length) {
        int filled = (currentHealth * length) / maxHealth;
        int empty = length - filled;

        String bar = "█".repeat(filled) + "░".repeat(empty);
        return String.format("%s %d/%d", bar, currentHealth, maxHealth);
    }
    private String createEnergyBar(int currentEnergy, int maxEnergy, int length) {
        int filled = (currentEnergy * length) / maxEnergy;
        int empty = length - filled;

        String bar = "█".repeat(filled) + "░".repeat(empty);
        return String.format(ColorUtil.purple("%s %d/%d"), bar, currentEnergy, maxEnergy);
    }
    private boolean checkSpeed(int playerCurrentSpeed, int enemyCurrentSpeed){
        if (playerCurrentSpeed >= enemyCurrentSpeed) {
            return true;
        } else {
            return false;
        }
    }
    private int takeAction(boolean isPlayerTurn, Enemy enemy) {
        int damage = 0;
        if (isPlayerTurn) {
            damage = playerAction(enemy);
        } else {
            damage = enemyAction(enemy);
        }
        delay(1000);

        if (damage != -1) {
            if (isPlayerTurn) {
                if(damage != 0){
                    // Check player accuracy
                    if (AccuracySystem.playerHits()) {
                        int actualDamage = enemy.takeDamage(damage, enemy.getDefense(), enemy.getPhysicalResistance(), enemy.getMagicResistance());
                        System.out.println("You dealt " + actualDamage + " damage to " + enemy.getName());
                        delay(1000);
                    } else {
                        System.out.println("Your attack missed!");
                        damage = 0;
                        delay(1000);
                    }
                }
            } else {
                // Check enemy accuracy
                if (AccuracySystem.enemyHits()) {
                    int actualEnemyDamage = player.takeDamage(damage, player.getDefense(), player.getPhysicalResistance(), player.getMagicResistance());
                    System.out.println(enemy.getName() + " dealt " + actualEnemyDamage + " damage to you!");
                    delay(1000);
                } else {
                    System.out.println(enemy.getName() + "'s attack missed!");
                    damage = 0;
                    delay(1000);
                }
            }
        }
        return damage;
    }
    private int playerAction(Entity enemy) {
        boolean hasActed = false;
        int damage = 0;
        int choice;

        while(!hasActed) {
            System.out.println("========== SELECT ACTION ==========");
            System.out.println("[1] Fight");
            System.out.println("[2] Inventory");
            System.out.println("[3] Run");

            choice = getIntInput("Choose action: ", 1, 3);

            switch(choice) {
                case 1:
                    damage = handleFightAction(enemy);
                    hasActed = true;
                    break;
                case 2:
                    int inventoryResult = openInventory(player);
                    if (inventoryResult == 1) {
                        // Player chose to attack after using items
                        damage = handleFightAction(enemy);
                        hasActed = true;
                    } else if (inventoryResult == 0) {
                        // Player used an item but turn continues
                        System.out.println("You can take another action...");
                        // Continue the loop without setting hasActed to true
                    } else {
                        // Player cancelled inventory, continue loop
                        System.out.println("Returning to action selection...");
                    }
                    break;
                case 3:
                    if (Math.random() > 0.75) {
                        System.out.println("You successfully fled!");
                        inBattle = false;
                        return -1;
                    } else {
                        System.out.println("You failed to flee!");
                    }
                    hasActed = true;
                    break;
            }
        }
        return damage;
    }
    private int handleFightAction(Entity enemy) {
        System.out.println("========== FIGHT ==========");
        System.out.println("[1] Use Basic Attack (Generates 25 energy + 1 ultimate charge)");
        System.out.println("[2] Use Skill (Costs 40 energy + 2 ultimate charges)");

        if (player.isUltimateReady()) {
            System.out.println("[3] Use Ultimate (Costs 80 energy, consumes all charges)");
        } else {
            System.out.println("[3] Use Ultimate (Locked - " + player.getUltimateCounter() + "/" + player.getMaxUltimateCounter() + " charges)");
        }

        System.out.println("[4] Back");

        int choice = getIntInput("Choose action: ", 1, 4);
        int damage = 0;

        switch(choice) {
            case 1:
                damage = player.getBasicAttack().execute(player);
                break;
            case 2:
                if (player.getEnergy() >= player.getSkillAttack().getEnergyCost()) {
                    damage = player.getSkillAttack().execute(player);
                } else {
                    System.out.println("You need 40 energy before you can use this skill. Current: " + player.getEnergy());
                    damage = handleFightAction(enemy); // Recursive call to try again
                }
                break;
            case 3:
                if (player.isUltimateReady()) {
                    if (player.getEnergy() >= player.getUltimateAttack().getEnergyCost()) {
                        damage = player.getUltimateAttack().execute(player);
                    } else {
                        System.out.println("You need 80 energy before you can use the ultimate. Current: " + player.getEnergy());
                        damage = handleFightAction(enemy); // Recursive call to try again
                    }
                } else {
                    System.out.println("Ultimate is locked! You need " + player.getMaxUltimateCounter() + " charges. Current: " + player.getUltimateCounter() + "/" + player.getMaxUltimateCounter());
                    damage = handleFightAction(enemy); // Recursive call to try again
                }
                break;
            case 4:
                damage = playerAction(enemy); // Go back to main action menu
                break;
        }

        return damage;
    }

    private int enemyAction(Enemy enemy) {
        int enemyDamage = enemy.attack();
        return enemyDamage;
    }
    private void clearBattleEffects(Enemy enemy) {
        // Remove all battle-only status effects from player
        List<StatusEffect> playerEffectsToRemove = new ArrayList<>();
        for (StatusEffect effect : player.getStatusEffects()) {
            if (effect.getDuration() == -1) { // Battle-only effects
                effect.removeEffect(player);
                playerEffectsToRemove.add(effect);
            }
        }
        player.getStatusEffects().removeAll(playerEffectsToRemove);

        // Remove all battle-only status effects from enemy
        List<StatusEffect> enemyEffectsToRemove = new ArrayList<>();
        for (StatusEffect effect : enemy.getStatusEffects()) {
            if (effect.getDuration() == -1) { // Battle-only effects
                effect.removeEffect(enemy);
                enemyEffectsToRemove.add(effect);
            }
        }
        enemy.getStatusEffects().removeAll(enemyEffectsToRemove);

        // Reset player shield
        player.setShield(0);
    }

    private void playerHealthCheck(Enemy enemy, int baseExp){
        if (player.getHealth() <= 0) {
            handlePlayerDefeat();
        } else {
            handleVictory(enemy, baseExp);
        }
    }
    private void handlePlayerDefeat() {
        System.out.println("\n💀 You have been defeated...");
        delay(1000);

        if (!player.hasResurrected()) {
            System.out.println("Remember: You still have your resurrection available for next time!");
        }

        // Partial recovery but lose some progress
        player.setHealth(player.getMaxHealth() / 4);
        System.out.println("You wake up with " + player.getHealth() + " HP, having lost some items during the escape.");

        // Lose some experience but not below current level
        int expLoss = (int)(player.getExperience() * 0.1);
        player.setExperience(Math.max(0, player.getExperience() + expLoss));
        System.out.println("You lost " + expLoss + " experience points during the retreat.");
    }
    private void handleVictory(Enemy enemy, int baseExp) {
        if(enemy.getHealth() <= 100){
            System.out.println("🎉 You defeated " + enemy.getName() + "!");
            delay(500);
            System.out.println("💰 Gained " + baseExp + " experience!");
            delay(500);
            player.gainExperience(baseExp);

            // Bonus for not using resurrection
            if (!player.hasResurrected()) {
                int bonusExp = (int)(baseExp * 0.2);
                System.out.println("⭐ Bonus " + bonusExp + " experience for completing the battle without resurrection!");
                delay(500);
                player.gainExperience(bonusExp);
            }

            // Chance for energy restoration
            if (Math.random() > 0.5) {
                System.out.println("✨ Energy partially restored!");
                delay(500);
                player.setEnergy(Math.min(100, player.getEnergy() + 25));
            }
        }
    }
    // Add a method to check resurrection status
    public void checkResurrectionStatus() {
        System.out.println("\n🔮 Resurrection Status:");
        if (player.hasResurrected()) {
            System.out.println("❌ Resurrection has been used and cannot be used again.");
        } else {
            System.out.println("✅ Resurrection is available! You will be given the option to resurrect once when defeated.");
            System.out.println("This powerful ability can only be used once per playthrough.");
        }
    }
    private void scaleEnemiesForCurrentLevel() {
        enemies.clear();

        // Create appropriately scaled enemies for current level
        int baseStats = 50 + (currentChapter * 10);
        String[] enemyNames = getEnemyNamesForLevel(currentChapter);

        for (String enemyName : enemyNames) {
            int scaledAttack = baseStats + (int)(Math.random() * 20);
            int scaledMagic = baseStats - 10 + (int)(Math.random() * 20);
            int scaledDefense = baseStats + (int)(Math.random() * 15);

            /*enemies.add(new Entities.Enemies.Enemy(enemyName, scaledAttack, scaledMagic,
                    scaledDefense, baseStats, scaledDefense - 10));*/
        }
    }
    private String[] getEnemyNamesForLevel(int level) {
        switch(level) {
            case 1: return new String[]{"Desert Clause", "Sand Stalker", "Dune Crawler"};
            case 2: return new String[]{"Fungal Spore", "Lume Beast", "Glow Worm"};
            case 3: return new String[]{"Star Fragment", "Cosmic Horror", "Void Walker"};
            case 4: return new String[]{"Crystal Golem", "Gem Eater", "Shard Warrior"};
            case 5: return new String[]{"Root Guardian", "Ancient Protector", "World Eater"};
            case 6: return new String[]{"Final Boss", "Ultimate Challenge", "Destiny's End"};
            default: return new String[]{"Mysterious Foe"};
        }
    }

    //Character
    public void addStarterPack(Character player, List<Item> items){
        System.out.println("Adding starter pack...");
        delay(1500);
        if(player instanceof Hawkseye || player instanceof Blademaster || player instanceof Berserker || player instanceof Shinobi) {
            player.obtainItem(findItemId("PDP001", items, 5));
            player.obtainItem(findItemId("HP001", items, 5));
            player.obtainItem(findItemId("EP001", items, 5));
            player.obtainItem(findItemId("SHP001", items, 5));
            player.obtainItem(findItemId("SP001", items, 5));
            switch(player.getClassType()){
                case ClassType.HAWKSEYE:
                    player.obtainItem(findItemId("BW001.1", items, 1));
                    break;
                case ClassType.BLADEMASTER:
                    player.obtainItem(findItemId("SW001.1", items, 1));
                    break;
                case ClassType.BERSERKER:
                    player.obtainItem(findItemId("BS001.1", items, 1));
                    break;
                case ClassType.ASSASSIN:
                    player.obtainItem(findItemId("DR001.1", items, 1));
                    break;
            }
        }else if(player instanceof Runecaster || player instanceof RuneKnight){
            player.obtainItem(findItemId("MDP001", items, 5));
            player.obtainItem(findItemId("HP001", items, 5));
            player.obtainItem(findItemId("EP001", items, 5));
            player.obtainItem(findItemId("SHP001", items, 5));
            player.obtainItem(findItemId("SP001", items, 5));
            switch(player.getClassType()) {
                case ClassType.RUNECASTER:
                    player.obtainItem(findItemId("MGS001.1", items, 1));
                    break;
                case ClassType.RUNEKNIGHT:
                    player.obtainItem(findItemId("MGSW001.1", items, 1));
                    break;
            }
        }else{ //JinwooSun
            player.obtainItem(findItemId("PDP004", items, 99));
            player.obtainItem(findItemId("MDP004", items, 99));
            player.obtainItem(findItemId("HP004", items, 99));
            player.obtainItem(findItemId("EP004", items, 99));
            player.obtainItem(findItemId("SHP004", items, 99));
            player.obtainItem(findItemId("SP004", items, 99));
            player.obtainItem(findItemId("EVP004", items, 1));
            player.obtainItem(findItemId("SW005", items, 1));
            player.obtainItem(findItemId("BS005", items, 1));
            player.obtainItem(findItemId("DR005", items, 1));
        }
    }

    private int openInventory(Character player){
        int confirm = 0;
        int result = -1;

        while(confirm == 0 && result == -1){
            player.displayInventory();
            System.out.println("[11] Back");
            int choice = getIntInput("Select an item: ", 1, 11);

            if(choice == 11){
                result = -1;
                break;
            }

            Item item = player.getInventory().getItems()[--choice];

            // Check if item is a consumable potion
            if(item instanceof Consumable && inBattle) {
                System.out.println("========== SELECTING ITEM ==========");
                System.out.println("Use " + item.getName() + "?");
                System.out.println("[1] Use Item");
                System.out.println("[2] Back");

                confirm = getIntInput("Choose action: ", 1, 2);
                if(confirm == 1){
                    item.use(player);
                    delay(1000);

                    // For consumable potions in battle, don't end the turn
                    if(item.getQuantity() > 0) {
                        System.out.println("Item used! You can take another action.");
                        result = 0; // Item used, continue turn
                    } else {
                        // Item was consumed completely
                        player.getInventory().removeItem(item);
                        System.out.println("Item used! You can take another action.");
                        result = 0; // Item used, continue turn
                    }
                }else{
                    confirm = 0;
                    result = -1;
                }
            } else {
                // For non-consumables or non-battle usage
                System.out.println("========== SELECTING ITEM ==========");
                System.out.println("Select "+item.getName()+"?: ");
                System.out.println("[1] Select Item");
                System.out.println("[2] Back");

                confirm = getIntInput("Choose action: ", 1, 2);
                if(confirm == 1){
                    if(inBattle && item.getItemType() == ItemType.WEAPON){
                        System.out.println("You can't equip a weapon during battle!");
                        confirm = 0;
                        delay(1000);
                        result = -1;
                    }else{
                        item.use(player);
                        delay(1000);
                        result = 0;
                    }
                }else{
                    confirm = 0;
                    result = -1;
                }
            }
        }
        return result;
    }

    //Item
    public Item findItemId(String itemId, List<Item> item, int quantity){
        Item foundItem = items.getFirst();
        for(int i = 0; i < item.size(); i++){
            if(itemId.equals(item.get(i).getItemId())) {
                item.get(i).setQuantity(quantity);
                foundItem = item.get(i);
            }
        }
        return foundItem;
    }
    public StatusEffect findStatus(String name, List<StatusEffect> statusEffects){
        StatusEffect foundStatus = statusEffects.getFirst();
        for(StatusEffect statusEffect : statusEffects){
            if(statusEffect.getName().equals(name)){
                foundStatus = statusEffect;
            }
        }
        return foundStatus;
    }

    //Misc
    public int getIntInput(String prompt, int min, int max) {
        int input = -1;
        while (input < min || input > max) {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        return input;
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