package Game;

import Entities.Characters.DamageType;
import Entities.Characters.TargetType;
import Items.*;
import Items.Weapons.Weapon;
import Items.Weapons.WeaponType;
import Skills.*;
import TextFormat.ColorUtil;

import java.util.*;

public class Initialization {
    //TODO fix this bs where it will error adding and the error is "IndexOutOfBounds"
    public static void initPotions(List<Item> items) {
        //Items
        items.add(new HealingPotion("HP001",ColorUtil.blueGreen("Lesser Healing Potion"), "<Empty>", 12, 0, 7, Rarity.COMMON, 30));
        items.add(new HealingPotion("HP002", ColorUtil.blueBright("Healing Potion"), "<Empty>", 12, 0, 14, Rarity.RARE, 50));
        items.add(new HealingPotion("HP003", ColorUtil.brightPurpleBold("Greater Healing Potion"), "<Empty>", 12, 1, 20, Rarity.EPIC, 70));
        items.add(new HealingPotion("HP004", ColorUtil.brightYellowBold("Legendary Healing Potion"), "<Empty>", 12, 1, 30, Rarity.LEGENDARY, 100));

        items.add(new EnergyPotion("EP001", ColorUtil.blueGreen("Lesser Energy Potion"), "<Empty>", 12, 0, 3, Rarity.COMMON, 20));
        items.add(new EnergyPotion("EP002", ColorUtil.blueBright("Energy Potion"), "<Empty>", 12, 0, 9, Rarity.RARE, 40));
        items.add(new EnergyPotion("EP003", ColorUtil.brightPurpleBold("Greater Energy Potion"), "<Empty>", 12, 0, 12, Rarity.EPIC, 60));
        items.add(new EnergyPotion("EP004", ColorUtil.brightYellowBold("Legendary Energy Potion"), "<Empty>", 12, 0, 18, Rarity.LEGENDARY, 80));

        items.add(new PhysicalDamagePotion("PDP001", ColorUtil.blueGreen("Lesser Physical Potion"), "<Empty>", 5, 0, 5, Rarity.COMMON, 0.24));
        items.add(new PhysicalDamagePotion("PDP002", ColorUtil.blueBright("Physical Potion"), "<Empty>", 5, 0, 10, Rarity.RARE, 0.36));
        items.add(new PhysicalDamagePotion("PDP003", ColorUtil.brightPurpleBold("Greater Physical Potion"), "<Empty>", 5, 0, 20, Rarity.EPIC, 0.48));
        items.add(new PhysicalDamagePotion("PDP004", ColorUtil.brightYellowBold("Legendary Physical Potion"), "<Empty>", 5, 0, 25, Rarity.LEGENDARY, 0.60));

        items.add(new MagicalDamagePotion("MDP001", ColorUtil.blueGreen("Lesser Magic Potion"), "<Empty>", 5, 0, 5, Rarity.COMMON, 0.24));
        items.add(new MagicalDamagePotion("MDP002", ColorUtil.blueBright("Magic Potion"), "<Empty>", 5, 0, 10, Rarity.RARE, 0.36));
        items.add(new MagicalDamagePotion("MDP003", ColorUtil.brightPurpleBold("Greater Magic Potion"), "<Empty>", 5, 0, 20, Rarity.EPIC, 0.48));
        items.add(new MagicalDamagePotion("MDP004", ColorUtil.brightYellowBold("Legendary Magic Potion"), "<Empty>", 5, 0, 25, Rarity.LEGENDARY, 0.60));

        items.add(new SpeedPotion("SP001", ColorUtil.blueGreen("Lesser Speed Potion"), "<Empty>", 5, 0, 5, Rarity.COMMON, 2));
        items.add(new SpeedPotion("SP002", ColorUtil.blueBright("Speed Potion"), "<Empty>", 5, 0, 10, Rarity.RARE, 4));
        items.add(new SpeedPotion("SP003", ColorUtil.brightPurpleBold("Greater Speed Potion"), "<Empty>", 5, 0, 15, Rarity.EPIC, 7));
        items.add(new SpeedPotion("SP004", ColorUtil.brightYellowBold("Legendary Speed Potion"), "<Empty>", 5, 0, 25, Rarity.LEGENDARY, 10));

        items.add(new EvasivenessPotion("EVP001", ColorUtil.blueGreen("Lesser Evasiveness Potion"), "Slightly reduces enemy accuracy", 5, 0, 5, Rarity.COMMON, 0.05));
        items.add(new EvasivenessPotion("EVP002", ColorUtil.blueBright("Evasiveness Potion"), "Reduces enemy accuracy", 5, 1, 10, Rarity.RARE, 0.8));
        items.add(new EvasivenessPotion("EVP003", ColorUtil.brightPurpleBold("Greater Evasiveness Potion"), "Greatly reduces enemy accuracy", 5, 0, 15, Rarity.EPIC, 0.11));
        items.add(new EvasivenessPotion("EVP004", ColorUtil.brightYellowBold("Legendary Evasiveness Potion"), "Massively reduces enemy accuracy", 5, 0, 25, Rarity.LEGENDARY, 0.15));
    }

    public void initWeaponT1(List<Item> items) {
//Tier 1 (COMMON) Weapons and Varieties
//1
        items.add(new Weapon("BW001.1", ColorUtil.blueGreen("Wooden Bow"), WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires a single arrow at the target.", 1.15, 0,DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Shoots a more forceful arrow for increased damage.", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Releases a fully drawn shot that strikes with maximum power.", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                20, Rarity.COMMON));
        items.add(new Weapon("SW001.1", ColorUtil.blueGreen("Bronze Sword"), WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "Delivers a simple forward slash.", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new SwordSkill("Skill Attack", "Unleashes a stronger, committed sword strike.", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Executes a heavy overhead slash that deals high damage.", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                20, Rarity.COMMON));
        items.add(new Weapon("MGS001.1", ColorUtil.blueGreen("Wooden Staff"), WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Releases a small bolt of beginner-level magic.", 1.15, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterSkill("Skill Attack", "Casts a focused burst of magic at the target.", 1.3, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterUltimate("Ultimate Attack", "Unleashes a concentrated wave of raw magical energy.", 1.7, 80, DamageType.MAGICAL, TargetType.SINGLE),
                20, Rarity.COMMON));
        items.add(new Weapon("BS001.1", ColorUtil.blueGreen("Iron Broadsword"), WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "Strikes the target with a heavy blade swing.", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Uses the sword's weight to deliver a powerful sweeping attack.", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Brings the broadsword down in a slow but crushing blow.", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                20, Rarity.COMMON));
        items.add(new Weapon("DR001.1", ColorUtil.blueGreen("Rusty Dagger"), WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "Quickly stabs the target.", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Strikes with a faster, deeper thrust.", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Performs a rapid forward lunge aimed at weak points.", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                20, Rarity.COMMON));
        items.add(new Weapon("MGSW001.1", ColorUtil.blueGreen("Rookie's Magic Sword"), WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Slashes with the blade while releasing a small magical spark.", 1.15, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RuneKnightSkill("Skill Attack", "Channels magic through the sword for a stronger enchanted slash.", 1.3, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Releases a burst of beginner magic during a powerful swing.", 1.7, 80, DamageType.MAGICAL, TargetType.SINGLE),
                20, Rarity.COMMON));
//2
        items.add(new Weapon("BW001.2", ColorUtil.blueGreen("Simple Bow"), WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires a light arrow straight at the target.", 1.17, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Shoots a stronger, more focused arrow.", 1.32, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Releases a fully drawn shot for maximum impact.", 1.72, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                25, Rarity.COMMON));
        items.add(new Weapon("SW001.2", ColorUtil.blueGreen("Traveler's Sword"), WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "Strikes the target with a straightforward slash.", 1.17, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new SwordSkill("Skill Attack", "Delivers a stronger, more committed sword swing.", 1.32, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Executes a heavy downward slash for high damage.", 1.72, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                25, Rarity.COMMON));
        items.add(new Weapon("MGS001.2", ColorUtil.blueGreen("Apprentice's Staff"), WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Releases a small spark of basic magic.", 1.17, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterSkill("Skill Attack", "Casts a focused burst of low-level magic.", 1.32, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterUltimate("Ultimate Attack", "Unleashes a concentrated wave of unstable magical energy.", 1.72, 80, DamageType.MAGICAL, TargetType.SINGLE),
                25, Rarity.COMMON));
        items.add(new Weapon("BS001.2", ColorUtil.blueGreen("Worn Broadsword"), WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "Swings the heavy blade in a simple attack.", 1.17, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Uses the sword's weight to deliver a stronger sweep.", 1.32, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Brings the broadsword down in a slow but crushing blow.", 1.72, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                25, Rarity.COMMON));
        items.add(new Weapon("DR001.2", ColorUtil.blueGreen("Rusty Knife"), WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "Quickly stabs the target.", 1.17, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Delivers a sharper, faster thrust.", 1.32, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Lunges forward with a rapid strike aimed at weak spots.", 1.72, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                25, Rarity.COMMON));
        items.add(new Weapon("MGSW001.2", ColorUtil.blueGreen("Faintblade"), WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Strikes with a faint magical slash.", 1.17, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RuneKnightSkill("Skill Attack", "Channels a small amount of magic into a stronger slash.", 1.32, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Releases a brief magical burst during a forceful swing.", 1.72, 80, DamageType.MAGICAL, TargetType.SINGLE),
                25, Rarity.COMMON));
    }

    public void initWeaponT2(List<Item> items) {
//Tier 2 (RARE) Weapons and Varieties
//1
        items.add(new Weapon("BW002.1", ColorUtil.blueBright("Iron Bow"), WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires a clean, straight shot for solid ranged damage.", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Shoots a reinforced arrow with greater force and penetration.", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes a powerful, fully-drawn shot that hits with maximum impact.", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("SW002.1", ColorUtil.blueBright("Steel Sword"), WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "Delivers a sharp, controlled slash for reliable melee damage.", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new SwordSkill("Skill Attack", "Strikes with enhanced strength to break through guard or armor.", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes a heavy, committed slash that maximizes cutting power.", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("MGS002.1", ColorUtil.blueBright("Crystal Staff"), WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Channels a focused burst of magic for clean single-target damage.", 1.25, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterSkill("Skill Attack", "Releases a stronger arcane blast with improved magical output.", 1.5, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterUltimate("Ultimate Attack", "Unleashes a concentrated magical surge for maximum arcane impact.", 1.9, 80, DamageType.MAGICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("BS002.1", ColorUtil.blueBright("Steel Broadsword"), WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "Delivers a firm, weighty strike that deals solid melee damage.", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Swings with increased force to push through enemy defenses.", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes a powerful, momentum-driven slash for heavy impact.", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("DR002.1", ColorUtil.blueBright("Iron Dagger"), WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "Delivers a quick jab for fast, precise damage.", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Strikes with increased speed to pierce weak points.", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Executes a rapid, lethal thrust aimed at maximum damage.", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("MGSW002.1", ColorUtil.blueBright("Apprentice's Magic Sword"), WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Releases a light-edged magical slash for clean damage output.", 1.25, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RuneKnightSkill("Skill Attack", "Unleashes a stronger magic-infused strike with improved potency.", 1.5, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Delivers a focused magical blade surge for maximum impact.", 1.9, 80, DamageType.MAGICAL, TargetType.SINGLE),
                80, Rarity.RARE));
//2
        items.add(new Weapon("BW002.2", ColorUtil.blueBright("Hunter's Bow"), WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "A standard arrow shot with good accuracy and velocity for consistent damage.", 1.22, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Fires a heavy arrow with enhanced penetration that deals increased damage to armored targets.", 1.52, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Fully draws the bow to release an arrow with maximum kinetic energy for critical damage.", 1.92, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                90, Rarity.RARE));
        items.add(new Weapon("SW002.2", ColorUtil.blueBright("Soldier's Sword"), WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "A balanced slash attack that maintains defensive positioning while dealing damage.", 1.27, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new SwordSkill("Skill Attack", "A powerful thrust that concentrates force on a small area to bypass enemy defenses.", 1.52, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Commits full body weight into a finishing strike that can break through armor and guard.", 1.92, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                90, Rarity.RARE));
        items.add(new Weapon("MGS002.2", ColorUtil.blueBright("Mystic Staff"), WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Projects a concentrated bolt of arcane energy that travels straight to the target.", 1.27, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterSkill("Skill Attack", "Channels additional mana to create a more powerful and destructive magical projectile.", 1.52, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterUltimate("Ultimate Attack", "Releases the staff's stored magical energy in a single, overwhelming burst of force.", 1.92, 80, DamageType.MAGICAL, TargetType.SINGLE),
                90, Rarity.RARE));
        items.add(new Weapon("BS002.2", ColorUtil.blueBright("Battleforged Broadsword"), WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "A wide-arcing slash that utilizes the blade's weight and momentum for solid impact.", 1.27, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "A downward chop that amplifies striking power through gravity and muscle force.", 1.52, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "A spinning attack that builds centrifugal force for a massively powerful final strike.", 1.92, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                90, Rarity.RARE));
        items.add(new Weapon("DR002.2", ColorUtil.blueBright("Swiftstrike Dagger"), WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "A quick thrust or slash that exploits openings in the enemy's defense.", 1.27, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "A rapid lunge attack that targets vulnerable areas for increased damage potential.", 1.52, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Executes multiple precise strikes in rapid succession to overwhelm the target.", 1.92, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                90, Rarity.RARE));
        items.add(new Weapon("MGSW002.2", ColorUtil.blueBright("Arcsteel Blade"), WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "A magical-enhanced slash that projects arcane energy along the cutting edge.", 1.27, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RuneKnightSkill("Skill Attack", "Charges the blade with elemental magic that discharges upon impact with the target.", 1.52, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Overloads the blade with magical energy, releasing it in a devastating arcane wave.", 1.92, 80, DamageType.MAGICAL, TargetType.SINGLE),
                90, Rarity.RARE));
//3
        items.add(new Weapon("BW002.3", ColorUtil.blueBright("Reinforced Bow"), WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires a standard arrow using the bow's reinforced limbs for consistent power and accuracy.", 1.29, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Utilizes the bow's enhanced draw weight to launch armor-piercing arrows with greater velocity.", 1.54, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Engages the bow's full reinforced potential to deliver maximum arrow speed and impact force.", 1.94, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                100, Rarity.RARE));
        items.add(new Weapon("SW002.3", ColorUtil.blueBright("Tempered Sword"), WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "Executes a precise cutting motion using the sword's tempered edge for clean, reliable damage.", 1.29, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new SwordSkill("Skill Attack", "Leverages the blade's enhanced durability to deliver powerful strikes that can chip through armor.", 1.54, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Focuses the sword's tempered strength into a single, perfectly balanced finishing strike.", 1.94, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                100, Rarity.RARE));
        items.add(new Weapon("MGS002.3", ColorUtil.blueBright("Enchanter's Staff"), WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Channels ambient mana through the staff's focus crystal to project a basic arcane bolt.", 1.29, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterSkill("Skill Attack", "Activates embedded enchantments to amplify magical output for a more potent energy projectile.", 1.54, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterUltimate("Ultimate Attack", "Unleashes all stored enchantments simultaneously for a catastrophic magical discharge.", 1.94, 80, DamageType.MAGICAL, TargetType.SINGLE),
                100, Rarity.RARE));
        items.add(new Weapon("BS002.3", ColorUtil.blueBright("Knight's Broadsword"), WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "Delivers a controlled sweeping strike optimized for the broadsword's weight distribution.", 1.29, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Employs knightly combat techniques to put full body weight behind a shield-breaking blow.", 1.54, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Executes a master-level combat maneuver that combines momentum, precision, and overwhelming force.", 1.94, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                100, Rarity.RARE));
        items.add(new Weapon("DR002.3", ColorUtil.blueBright("Steelfang Dagger"), WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "Utilizes the dagger's razor-sharp edge for quick, precise cuts to exposed areas.", 1.29, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Employs the dagger's reinforced point for deep, penetrating stabs to vital organs.", 1.54, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Combines multiple attack angles in rapid succession to overwhelm the target's defenses.", 1.94, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                100, Rarity.RARE));
        items.add(new Weapon("MGSW002.3", ColorUtil.blueBright("Spellforged Blade"), WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Projects a wave of magical energy along the blade's spell-etched edge during each swing.", 1.29, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RuneKnightSkill("Skill Attack", "Activates runic inscriptions to charge the blade with elemental energy that detonates on impact.", 1.54, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Triggers all embedded spells simultaneously, creating a devastating magical explosion with the strike.", 1.94, 80, DamageType.MAGICAL, TargetType.SINGLE),
                100, Rarity.RARE));
    }

    public void initWeaponT3(List<Item> items) {
//Tier 3 (EPIC) Weapons including Varieties
//1
        items.add(new Weapon("BW003.1", ColorUtil.brightPurpleBold("Elven Bow"), WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires arrows with supernatural accuracy, guided by elven craftsmanship to strike weak points.", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Channels wind magic into arrows, granting them hypersonic velocity and enhanced penetration.", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Simultaneously fires multiple arrows that converge on the target with perfect magical coordination.", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("SW003.1", ColorUtil.brightPurpleBold("Mythril Sword"), WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "Utilizes mythril's perfect balance for lightning-fast strikes that seem to ignore air resistance.", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new SwordSkill("Skill Attack", "Leverages mythril's magical conductivity to enhance cutting power beyond physical limits.", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Releases stored kinetic energy in a single strike that bypasses conventional durability.", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("MGS003.1", ColorUtil.brightPurpleBold("Arcane Staff"), WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Draws directly from the staff's arcane core to project highly concentrated mana bolts.", 1.4, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterSkill("Skill Attack", "Taps into dimensional energies to launch projectiles that phase through magical defenses.", 1.7, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterUltimate("Ultimate Attack", "Unleashes the staff's full arcane potential, creating a localized reality distortion on impact.", 2.2, 80, DamageType.MAGICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("BS003.1", ColorUtil.brightPurpleBold("Runed Broadsword"), WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "Activates strength-enhancing runes to multiply the force behind each swing without added effort.", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Engages impact runes that release stored energy upon contact, amplifying damage exponentially.", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Triggers all runic inscriptions simultaneously for a strike that shatters defenses magically and physically.", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("DR003.1", ColorUtil.brightPurpleBold("Shadow Dagger"), WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "Moves with unnatural speed, striking from angles that defy conventional defense.", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Phases briefly through solid matter to bypass armor and strike internal organs directly.", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Creates multiple temporal afterimages that all strike simultaneously from different directions.", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("MGSW003.1", ColorUtil.brightPurpleBold("Runic Magic Sword"), WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Projects rune-empowered arcane energy that seeks weak points in the target's magical defenses.", 1.4, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RuneKnightSkill("Skill Attack", "Activates elemental runes to coat the blade in destructive energies that disrupt magical constructs.", 1.7, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Synchronizes all runic patterns to create a cascading magical failure in the target upon impact.", 2.2, 80, DamageType.MAGICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
//2
        items.add(new Weapon("BW003.2", ColorUtil.brightPurpleBold("Phoenix Bow"), WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires arrows tipped with phoenix feathers that ignite upon impact, dealing additional thermal damage.", 1.42, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Launches arrows wreathed in rebirth flames that seek the target and explode on contact.", 1.72, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Channels the phoenix's full power to create a solar flare arrow that incinerates everything in its path.", 2.22, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                130, Rarity.EPIC));
        items.add(new Weapon("SW003.2", ColorUtil.brightPurpleBold("Tempest Sword"), WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "Generates razor-sharp wind currents along the blade that extend its cutting range and speed.", 1.42, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new SwordSkill("Skill Attack", "Creates a vortex of compressed air that amplifies striking force and creates vacuum damage.", 1.72, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Summons a miniature storm around the blade, releasing lightning and hurricane-force winds upon impact.", 2.22, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                130, Rarity.EPIC));
        items.add(new Weapon("MGS003.2", ColorUtil.brightPurpleBold("Soulbinder Staff"), WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Projects spectral energy that temporarily disrupts the target's spiritual cohesion.", 1.42, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterSkill("Skill Attack", "Fires soul-shard projectiles that fragment the target's life essence upon impact.", 1.72, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterUltimate("Ultimate Attack", "Unleashes a spirit-rending blast that directly attacks the target's metaphysical existence.", 2.22, 80, DamageType.MAGICAL, TargetType.SINGLE),
                130, Rarity.EPIC));
        items.add(new Weapon("BS003.2", ColorUtil.brightPurpleBold("Frostbane Broadsword"), WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "Coats the blade in permafrost that deals cryogenic damage and slows the target's movements.", 1.42, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Releases a glacial shockwave that freezes the air around the blade, creating ice shrapnel on impact.", 1.72, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Channels absolute zero temperatures into a single strike that flash-freezes the target solid.", 2.22, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                130, Rarity.EPIC));
        items.add(new Weapon("DR003.2", ColorUtil.brightPurpleBold("Bloodthorn Dagger"), WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "The dagger's thorn-like edge causes wounds that continue to bleed and resist natural healing.", 1.42, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Strikes that connect create magical thorns inside the wound that expand and cause internal damage.", 1.72, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Causes the target's blood to crystallize into sharp thorns that erupt from within their body.", 2.22, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                130, Rarity.EPIC));
        items.add(new Weapon("MGSW003.2", ColorUtil.brightPurpleBold("Lunaris Blade"), WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Projects lunar energy that phases through physical armor and deals direct magical damage.", 1.42, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RuneKnightSkill("Skill Attack", "Charges the blade with gravitational forces that increase impact mass and create crushing pressure.", 1.72, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes concentrated moonlight that disintegrates matter at the molecular level upon contact.", 2.22, 80, DamageType.MAGICAL, TargetType.SINGLE),
                130, Rarity.EPIC));
//3
        items.add(new Weapon("BW003.3", ColorUtil.brightPurpleBold("Moonpiercer Bow"), WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires arrows infused with lunar energy that ignore partial armor and maintain perfect trajectory.", 1.44, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Launches crystalline arrows that fragment into moonbeam shards upon impact, dealing multi-layered damage.", 1.74, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Charges an arrow with concentrated moonlight that phases through all barriers before detonating inside the target.", 2.24, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                140, Rarity.EPIC));
        items.add(new Weapon("SW003.3", ColorUtil.brightPurpleBold("Flameheart Sword"), WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "The blade continuously generates intense heat, causing thermal shock with each strike that weakens structural integrity.", 1.44, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new SwordSkill("Skill Attack", "Superheats the metal to plasma state, creating a cutting edge that melts through armor and flesh alike.", 1.74, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Releases the sword's core temperature in a solar flare strike that vaporizes matter along the blade's path.", 2.24, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                140, Rarity.EPIC));
        items.add(new Weapon("MGS003.3", ColorUtil.brightPurpleBold("Crimson Staff"), WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Projects hemomancy bolts that convert the target's blood into crystalline shards that cause internal damage.", 1.44, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterSkill("Skill Attack", "Creates life-force syphoning projectiles that drain vitality and transfer it as kinetic impact energy.", 1.74, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterUltimate("Ultimate Attack", "Unleashes a cascade of blood magic that overwhelms the target's circulatory system, causing catastrophic internal pressure.", 2.24, 80, DamageType.MAGICAL, TargetType.SINGLE),
                140, Rarity.EPIC));
        items.add(new Weapon("BS003.3", ColorUtil.brightPurpleBold("Earthshatter Broadsword"), WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "Channels seismic vibrations through the blade, creating micro-fractures in the target's armor with each impact.", 1.44, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Amplifies gravitational force during the swing, multiplying the blade's effective mass and striking power exponentially.", 1.74, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Focuses tectonic pressure into a single strike that releases shockwaves capable of splitting the ground beneath the target.", 2.24, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                140, Rarity.EPIC));
        items.add(new Weapon("DR003.3", ColorUtil.brightPurpleBold("Nightshade Dagger"), WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "The blade secretes neurotoxic compounds that disrupt motor functions and amplify pain reception upon contact.", 1.44, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Injects magical toxins that temporarily sever the connection between the target's nervous system and muscles.", 1.74, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Delivers a concentrated dose of spectral venom that attacks the target's life force directly, bypassing physical durability.", 2.24, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                140, Rarity.EPIC));
        items.add(new Weapon("MGSW003.3", ColorUtil.brightPurpleBold("Aetherfang Blade"), WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Projects raw aetheric energy that disrupts magical constructs and bypasses conventional magical defenses.", 1.44, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RuneKnightSkill("Skill Attack", "Charges the blade with dimensional energy, allowing it to cut through space-time to strike from unexpected angles.", 1.74, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Tears open a temporary rift in reality, unleashing primordial aether that erases matter from existence upon contact.", 2.24, 80, DamageType.MAGICAL, TargetType.SINGLE),
                140, Rarity.EPIC));
    }

    public void initWeaponT4(List<Item> items) {
//Tier 4 (LEGENDARY) Weapons including Varieties
//1
        items.add(new Weapon("BW004.1", ColorUtil.brightYellowBold("Seraphic Longbow"), WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires arrows of solidified light that purify targets on impact, dealing divine damage that ignores all mortal resistances.", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Summons celestial arrows that home with absolute precision and strike with the force of falling stars.", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Channels the full power of the heavens to launch the Arrow of Judgment, which erases evil from existence upon contact.", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                180, Rarity.LEGENDARY));
        items.add(new Weapon("SW004.1", ColorUtil.brightYellowBold("Demon King's Sword"), WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "The blade constantly emits infernal energy that corrupts and decays anything it touches, bypassing divine protections.", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new SwordSkill("Skill Attack", "Unleashes hellfire waves that burn across dimensions, damaging the target's soul simultaneously with physical strikes.", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Opens a gateway to the abyss, dragging the target through multiple hell dimensions with a single reality-sundering slash.", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                180, Rarity.LEGENDARY));
        items.add(new Weapon("MGS004.1", ColorUtil.brightYellowBold("Abyssal Staff"), WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Projects spheres of antimatter that annihilate conventional matter upon contact, leaving only void in their wake.", 1.7, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterSkill("Skill Attack", "Creates localized reality collapses that compress space-time around the target, dealing existential damage.", 2.2, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterUltimate("Ultimate Attack", "Summons a primordial singularity that consumes the target's very concept from the fabric of reality.", 3.0, 80, DamageType.MAGICAL, TargetType.SINGLE),
                180, Rarity.LEGENDARY));
        items.add(new Weapon("BS004.1", ColorUtil.brightYellowBold("Crimson Eclipse Greatsword"), WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "Each swing manipulates gravitational forces, increasing the blade's mass to planetary levels while maintaining wieldability.", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Channels solar flare energy into the edge, creating a cutting surface hot enough to split atoms upon impact.", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes the sword's dormant black hole core, creating an event horizon around the blade that consumes all matter it touches.", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                180, Rarity.LEGENDARY));
        items.add(new Weapon("DR004.1", ColorUtil.brightYellowBold("Reaper's Fang"), WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "The blade cuts directly through the soul-severing life threads, dealing damage that cannot be healed by any mortal or divine means.", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Phases through all dimensions simultaneously to strike the target's past, present, and future selves in a single motion.", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Executes the Final Cut that permanently severs the target from the cycle of life and death, erasing their existence across all timelines.", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                180, Rarity.LEGENDARY));
        items.add(new Weapon("MGSW004.1", ColorUtil.brightYellowBold("Voidbreaker Blade"), WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Projects quantum-disruption fields that cause targeted matter to simultaneously exist in multiple contradictory states until collapse.", 1.7, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RuneKnightSkill("Skill Attack", "Creates cascading reality fractures that propagate through the target's molecular structure, turning stability into chaos.", 2.2, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Shatters the boundary between existence and nothingness, unleashing pure void energy that unmakes creation at the fundamental level.", 3.0, 80, DamageType.MAGICAL, TargetType.SINGLE),
                180, Rarity.LEGENDARY));
//2
        items.add(new Weapon("BW004.2", ColorUtil.brightYellowBold("Dawnseeker"), WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires arrows woven from the first light of creation that ignore conventional physics and always find their mark.", 1.72, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Launches solar prominence arrows that burn with the intensity of a newborn star, vaporizing all matter they contact.", 2.22, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Channels the Big Bang's primordial energy to create an arrow that rewrites reality's laws upon impact, erasing targets from the timeline.", 3.02, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                190, Rarity.LEGENDARY));
        items.add(new Weapon("SW004.2", ColorUtil.brightYellowBold("Oblivion's Edge"), WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "The blade exists in a state of quantum superposition, simultaneously striking and having already struck its target.", 1.72, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new SwordSkill("Skill Attack", "Creates localized entropy fields that accelerate time around the target to instant decay while the blade remains timeless.", 2.22, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes the sword's true nature as a fragment of nothingness, cutting the very concept of the target's existence from the cosmic record.", 3.02, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                190, Rarity.LEGENDARY));
        items.add(new Weapon("MGS004.2", ColorUtil.brightYellowBold("Vermilion Aegis"), WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Projects crimson reality anchors that lock targets in place while simultaneously unraveling their molecular integrity.", 1.72, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterSkill("Skill Attack", "Creates protective-destructive paradox fields that convert all defensive energy into offensive power against the target.", 2.22, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterUltimate("Ultimate Attack", "Activates the staff's ultimate protocol: rewriting causality to make the target's destruction an immutable fact of the universe.", 3.02, 80, DamageType.MAGICAL, TargetType.SINGLE),
                190, Rarity.LEGENDARY));
        items.add(new Weapon("BS004.2", ColorUtil.brightYellowBold("Draconic Cleaver"), WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "Channels primordial dragon fury into each swing, dealing damage that scales exponentially with the target's own power.", 1.72, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Unleashes the collective wrath of all dragonkind in a single strike that shatters mountains and splits the sky.", 2.22, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Awakens the World-Ender protocol, focusing the energy that destroyed continents into a single, civilization-ending blow.", 3.02, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                190, Rarity.LEGENDARY));
        items.add(new Weapon("DR004.2", ColorUtil.brightYellowBold("Silent Requiem"), WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "Moves between heartbeats of time, striking in the spaces between moments where defenses cannot exist.", 1.72, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Plays the target's death as a musical composition, with each note representing a fatal strike across multiple dimensions.", 2.22, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Composes and performs the Final Symphony - a series of strikes that systematically erase the target from all possible realities.", 3.02, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                190, Rarity.LEGENDARY));
        items.add(new Weapon("MGSW004.2", ColorUtil.brightYellowBold("Celestia's Wrath"), WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Channels the anger of dying stars into each swing, projecting supernova energy that consumes all in its path.", 1.72, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RuneKnightSkill("Skill Attack", "Unleashes galactic core energy that creates miniature black holes along the blade's trajectory, warping space-time itself.", 2.22, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Becomes a conduit for the universe's fundamental forces, delivering a strike that can reset local reality to its pre-creation state.", 3.02, 80, DamageType.MAGICAL, TargetType.SINGLE),
                190, Rarity.LEGENDARY));
//3
        items.add(new Weapon("BW004.3", ColorUtil.brightYellowBold("Heaven Piercer"), WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires arrows that transcend dimensional barriers, striking targets regardless of their position in the celestial hierarchy.", 1.74, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Launches divine judgment arrows that scale their damage based on the target's sins, with no upper limit to potential damage.", 2.24, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Channels the collective power of the heavenly host to create an arrow that can pierce through divine realms and strike down gods.", 3.04, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                200, Rarity.LEGENDARY));
        items.add(new Weapon("SW004.3", ColorUtil.brightYellowBold("Sky Splitter"), WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "Each swing creates spatial fractures that extend infinitely upward, dealing damage that scales with the height of the target's ambitions.", 1.74, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new SwordSkill("Skill Attack", "Unleashes a crescent of energy that travels along the fabric of reality, cutting through all barriers physical, magical, or conceptual.", 2.24, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Focuses the sword's true power to perform a strike that literally divides heaven from earth, creating a permanent scar in reality.", 3.04, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                200, Rarity.LEGENDARY));
        items.add(new Weapon("MGS004.3", ColorUtil.brightYellowBold("Orb of Avarice"), WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Projects energy streams that permanently steal a portion of the target's power and add it to the wielder's capabilities.", 1.74, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterSkill("Skill Attack", "Creates a power vortex that drains the target's life force, memories, and abilities simultaneously, storing them within the orb.", 2.24, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterUltimate("Ultimate Attack", "Activates the orb's ultimate function: temporarily claiming ownership of the target's very soul, rendering them completely powerless.", 3.04, 80, DamageType.MAGICAL, TargetType.SINGLE),
                200, Rarity.LEGENDARY));
        items.add(new Weapon("BS004.3", ColorUtil.brightYellowBold("Demon Dweller"), WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "The blade houses captive demons that manifest with each strike, dealing additional spiritual damage that bypasses holy protections.", 1.74, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Unleashes the trapped demons in a coordinated assault that attacks the target's body, mind, and soul simultaneously.", 2.24, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Opens a permanent gateway to the abyss within the target, allowing an endless stream of demons to pour forth from their body.", 3.04, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                200, Rarity.LEGENDARY));
        items.add(new Weapon("DR004.3", ColorUtil.brightYellowBold("Death's Gaze"), WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "Strikes that connect impose a death countdown, with each subsequent attack exponentially reducing the time until instant death.", 1.74, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "The dagger temporarily becomes an extension of Death itself, ensuring the next strike will be fatal regardless of the target's defenses.", 2.24, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Forces the target to witness their own death across infinite timelines simultaneously, causing catastrophic existential collapse.", 3.04, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                200, Rarity.LEGENDARY));
        items.add(new Weapon("MGSW004.3", ColorUtil.brightYellowBold("Archangel's Judgement"), WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Projects blades of divine light that automatically seek out and strike the most vulnerable aspects of the target's existence.", 1.74, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RuneKnightSkill("Skill Attack", "Channels celestial justice into the blade, dealing damage proportional to the target's crimes against cosmic order.", 2.24, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes the Final Judgment, a strike that permanently seals the target's fate across all dimensions and timelines.", 3.04, 80, DamageType.MAGICAL, TargetType.SINGLE),
                200, Rarity.LEGENDARY));
    }

    public void initWeaponHiddenTier(List<Item> items) {
//Hidden Tier (MYTHICAL) Weapons [Hehe I may or may not have made this a bit too Over-Powered] -zed
        items.add(new Weapon("BW005", ColorUtil.brightRedBold("Recurve of Eternal Fate"), WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires arrows that rewrite probability, ensuring each shot was always destined to strike true while making all possible dodges impossible.", 2.5, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Launches arrows that travel backward through causality, striking the target before they're fired while ensuring the shot always happened.", 3.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Fires the Arrow of Finality that permanently closes all possible timelines where the target continues to exist, enforcing absolute death.", 4.5, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                300, Rarity.MYTHICAL));
        items.add(new Weapon("SW005", ColorUtil.brightRedBold("Dawn of the Era Nova"), WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "Each swing carries the weight of a universe's birth, dealing damage that scales with the number of stars that will ever exist.", 2.5, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new SwordSkill("Skill Attack", "Unleashes the concentrated light of a billion big bangs in a single strike that resets local physics to pre-creation states.", 3.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Becomes the focal point for the heat death of the universe, delivering all remaining time's energy in one civilization-ending moment.", 4.5, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                300, Rarity.MYTHICAL));
        items.add(new Weapon("MGS005", ColorUtil.brightRedBold("Tome of the Celestial Codex"), WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Projects words from reality's source code that temporarily overwrite the target's existence parameters with fatal errors.", 2.5, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterSkill("Skill Attack", "Reads aloud the cosmic laws of entropy and applies them directly to the target, accelerating their existence to heat death.", 3.2, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new RunecasterUltimate("Ultimate Attack", "Accesses the universe's administrative privileges to execute 'TARGET.DELETE()' - permanently removing the target from creation's database.", 4.5, 80, DamageType.MAGICAL, TargetType.SINGLE),
                300, Rarity.MYTHICAL));
        items.add(new Weapon("BS005", ColorUtil.brightRedBold("Cleave of the Oblivion's Edge"), WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "The blade exists as a permanent wound in reality that spreads with each swing, making the concept of 'wholeness' impossible near it.", 2.5, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Cuts so deeply it severs the target's connection to fundamental forces, making them unable to interact with reality itself.", 3.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Performs the Final Division that separates the target from the concept of existence, leaving only the perfect void where they once were.", 4.5, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                300, Rarity.MYTHICAL));
        items.add(new Weapon("DR005", ColorUtil.brightRedBold("Void of the Forsaken Rift"), WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "Strikes from the spaces between dimensions where causality doesn't apply, dealing damage that cannot be prevented or undone.", 2.5, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Creates permanent holes in the target's reality that slowly drain their existence into the absolute nothingness between universes.", 3.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Opens a gateway to the pre-creation void within the target, collapsing their existence into the infinite silence that predates time.", 4.5, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                300, Rarity.MYTHICAL));
        items.add(new Weapon("MGSW005", ColorUtil.brightRedBold("Chronoblade of the Severed Realm"), WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Cuts across all temporal instances simultaneously, striking the target's past, present, and future in a single timeless motion.", 2.5, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new RuneKnightSkill("Skill Attack", "Severs the target from the flow of time, making them a fixed point that cannot interact with or be perceived by the moving universe.", 3.2, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Executes the Temporal Finality Strike that collapses all of the target's possible timelines into a single moment of absolute cessation.", 4.5, 80, DamageType.MAGICAL, TargetType.SINGLE),
                300, Rarity.MYTHICAL));
    }
}
