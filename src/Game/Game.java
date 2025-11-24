package Game;

import Entities.Enemies.*;
import Entities.Characters.*;
import Entities.Characters.Character;
import Entities.Entity;
import Items.*;
import Items.Weapons.*;
import NPC.*;
import Skills.WeaponSkill;
import StatusEffects.DamageOverTimeEffects.*;
import StatusEffects.StatusEffect;
import Story.*;
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
    private StoryManager storyManager;
    private final int MAX_LEVEL = 5;
    private boolean[] levelsCompleted = new boolean[MAX_LEVEL + 1];

    //Game
    public void initializeGame() {
        // Initialize story system
        storyManager = new StoryManager();

        //Items (LEGENDARY Items should be very rare)
        items.add(new HealingPotion("HP001", "Lesser Healing Potion", "<Empty>", 12, 1, 5, Rarity.COMMON, 50));
        items.add(new HealingPotion("HP002", "Healing Potion", "<Empty>", 12, 1, 10, Rarity.RARE, 75));
        items.add(new HealingPotion("HP003", "Greater Healing Potion", "<Empty>", 12, 1, 20, Rarity.EPIC, 100));
        items.add(new HealingPotion("HP004", "Legendary Healing Potion", "<Empty>", 12, 1, 40, Rarity.LEGENDARY, 200));

        items.add(new EnergyPotion("EP001", "Lesser Energy Potion", "<Empty>", 12, 1, 3, Rarity.COMMON, 40));
        items.add(new EnergyPotion("EP002", "Energy Potion", "<Empty>", 12, 1, 9, Rarity.RARE, 60));
        items.add(new EnergyPotion("EP003", "Greater Energy Potion", "<Empty>", 12, 1, 15, Rarity.EPIC, 80));
        items.add(new EnergyPotion("EP004", "Legendary Energy Potion", "<Empty>", 12, 1, 35, Rarity.LEGENDARY, 100));

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
        //1
        items.add(new Weapon("BW001.1", "Wooden Bow", WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires a single arrow at the target.", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Shoots a more forceful arrow for increased damage.", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Releases a fully drawn shot that strikes with maximum power.", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                20, Rarity.COMMON));
        items.add(new Weapon("SW001.1", "Bronze Sword", WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "Delivers a simple forward slash.", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Unleashes a stronger, committed sword strike.", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Executes a heavy overhead slash that deals high damage.", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                20, Rarity.COMMON));
        items.add(new Weapon("MGS001.1", "Wooden Staff", WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Releases a small bolt of beginner-level magic.", 1.15, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Casts a focused burst of magic at the target.", 1.3, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes a concentrated wave of raw magical energy.", 1.7, 80, DamageType.MAGICAL, TargetType.SINGLE),
                20, Rarity.COMMON));
        items.add(new Weapon("BS001.1", "Iron Broadsword", WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "Strikes the target with a heavy blade swing.", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Uses the sword's weight to deliver a powerful sweeping attack.", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Brings the broadsword down in a slow but crushing blow.", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                20, Rarity.COMMON));
        items.add(new Weapon("DR001.1", "Rusty Dagger", WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "Quickly stabs the target.", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Strikes with a faster, deeper thrust.", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Performs a rapid forward lunge aimed at weak points.", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                20, Rarity.COMMON));
        items.add(new Weapon("MGSW001.1", "Rookie's Magic Sword", WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Slashes with the blade while releasing a small magical spark.", 1.15, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Channels magic through the sword for a stronger enchanted slash.", 1.3, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Releases a burst of beginner magic during a powerful swing.", 1.7, 80, DamageType.MAGICAL, TargetType.SINGLE),
                20, Rarity.COMMON));
        //2
        items.add(new Weapon("BW001.2", "Simple Bow", WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires a light arrow straight at the target.", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Shoots a stronger, more focused arrow.", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Releases a fully drawn shot for maximum impact.", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                20, Rarity.COMMON));
        items.add(new Weapon("SW001.2", "Traveler’s Sword", WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "Strikes the target with a straightforward slash.", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Delivers a stronger, more committed sword swing.", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Executes a heavy downward slash for high damage.", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                20, Rarity.COMMON));
        items.add(new Weapon("MGS001.2", "Apprentice’s Staff", WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Releases a small spark of basic magic.", 1.15, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Casts a focused burst of low-level magic.", 1.3, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes a concentrated wave of unstable magical energy.", 1.7, 80, DamageType.MAGICAL, TargetType.SINGLE),
                20, Rarity.COMMON));
        items.add(new Weapon("BS001.2", "Worn Broadsword", WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "Swings the heavy blade in a simple attack.", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Uses the sword’s weight to deliver a stronger sweep.", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Brings the broadsword down in a slow but crushing blow.", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                20, Rarity.COMMON));
        items.add(new Weapon("DR001.2", "Rusty Knife", WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "Quickly stabs the target.", 1.15, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Delivers a sharper, faster thrust.", 1.3, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Lunges forward with a rapid strike aimed at weak spots.", 1.7, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                20, Rarity.COMMON));
        items.add(new Weapon("MGSW001.2", "Faintblade", WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Strikes with a faint magical slash.", 1.15, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Channels a small amount of magic into a stronger slash.", 1.3, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Releases a brief magical burst during a forceful swing.", 1.7, 80, DamageType.MAGICAL, TargetType.SINGLE),
                20, Rarity.COMMON));

        //Tier 2 (RARE) Weapons and Varieties
        //1
        items.add(new Weapon("BW002.1", "Iron Bow", WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires a clean, straight shot for solid ranged damage.", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Shoots a reinforced arrow with greater force and penetration.", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes a powerful, fully-drawn shot that hits with maximum impact.", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("SW002.1", "Steel Sword", WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "Delivers a sharp, controlled slash for reliable melee damage.", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Strikes with enhanced strength to break through guard or armor.", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes a heavy, committed slash that maximizes cutting power.", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("MGS002.1", "Crystal Staff", WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Channels a focused burst of magic for clean single-target damage.", 1.25, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Releases a stronger arcane blast with improved magical output.", 1.5, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes a concentrated magical surge for maximum arcane impact.", 1.9, 80, DamageType.MAGICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("BS002.1", "Steel Broadsword", WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "Delivers a firm, weighty strike that deals solid melee damage.", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Swings with increased force to push through enemy defenses.", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes a powerful, momentum-driven slash for heavy impact.", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("DR002.1", "Iron Dagger", WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "Delivers a quick jab for fast, precise damage.", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Strikes with increased speed to pierce weak points.", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Executes a rapid, lethal thrust aimed at maximum damage.", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("MGSW002.1", "Apprentice's Magic Sword", WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Releases a light-edged magical slash for clean damage output.", 1.25, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Unleashes a stronger magic-infused strike with improved potency.", 1.5, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Delivers a focused magical blade surge for maximum impact.", 1.9, 80, DamageType.MAGICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        //2
        items.add(new Weapon("BW002.2", "Hunter’s Bow", WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "A standard arrow shot with good accuracy and velocity for consistent damage.", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Fires a heavy arrow with enhanced penetration that deals increased damage to armored targets.", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Fully draws the bow to release an arrow with maximum kinetic energy for critical damage.", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("SW002.2", "Soldier’s Sword", WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "A balanced slash attack that maintains defensive positioning while dealing damage.", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "A powerful thrust that concentrates force on a small area to bypass enemy defenses.", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Commits full body weight into a finishing strike that can break through armor and guard.", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("MGS002.2", "Mystic Staff", WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Projects a concentrated bolt of arcane energy that travels straight to the target.", 1.25, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Channels additional mana to create a more powerful and destructive magical projectile.", 1.5, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Releases the staff's stored magical energy in a single, overwhelming burst of force.", 1.9, 80, DamageType.MAGICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("BS002.2", "Battleforged Broadsword", WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "A wide-arcing slash that utilizes the blade's weight and momentum for solid impact.", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "A downward chop that amplifies striking power through gravity and muscle force.", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "A spinning attack that builds centrifugal force for a massively powerful final strike.", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("DR002.2", "Swiftstrike Dagger", WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "A quick thrust or slash that exploits openings in the enemy's defense.", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "A rapid lunge attack that targets vulnerable areas for increased damage potential.", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Executes multiple precise strikes in rapid succession to overwhelm the target.", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("MGSW002.2", "Arcsteel Blade", WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "A magical-enhanced slash that projects arcane energy along the cutting edge.", 1.25, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Charges the blade with elemental magic that discharges upon impact with the target.", 1.5, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Overloads the blade with magical energy, releasing it in a devastating arcane wave.", 1.9, 80, DamageType.MAGICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        //3
        items.add(new Weapon("BW002.3", "Reinforced Bow", WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires a standard arrow using the bow's reinforced limbs for consistent power and accuracy.", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Utilizes the bow's enhanced draw weight to launch armor-piercing arrows with greater velocity.", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Engages the bow's full reinforced potential to deliver maximum arrow speed and impact force.", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("SW002.3", "Tempered Sword", WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "Executes a precise cutting motion using the sword's tempered edge for clean, reliable damage.", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Leverages the blade's enhanced durability to deliver powerful strikes that can chip through armor.", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Focuses the sword's tempered strength into a single, perfectly balanced finishing strike.", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("MGS002.3", "Enchanter's Staff", WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Channels ambient mana through the staff's focus crystal to project a basic arcane bolt.", 1.25, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Activates embedded enchantments to amplify magical output for a more potent energy projectile.", 1.5, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes all stored enchantments simultaneously for a catastrophic magical discharge.", 1.9, 80, DamageType.MAGICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("BS002.3", "Knight's Broadsword", WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "Delivers a controlled sweeping strike optimized for the broadsword's weight distribution.", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Employs knightly combat techniques to put full body weight behind a shield-breaking blow.", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Executes a master-level combat maneuver that combines momentum, precision, and overwhelming force.", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("DR002.3", "Steelfang Dagger", WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "Utilizes the dagger's razor-sharp edge for quick, precise cuts to exposed areas.", 1.25, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Employs the dagger's reinforced point for deep, penetrating stabs to vital organs.", 1.5, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Combines multiple attack angles in rapid succession to overwhelm the target's defenses.", 1.9, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                80, Rarity.RARE));
        items.add(new Weapon("MGSW002.3", "Spellforged Blade", WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Projects a wave of magical energy along the blade's spell-etched edge during each swing.", 1.25, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Activates runic inscriptions to charge the blade with elemental energy that detonates on impact.", 1.5, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Triggers all embedded spells simultaneously, creating a devastating magical explosion with the strike.", 1.9, 80, DamageType.MAGICAL, TargetType.SINGLE),
                80, Rarity.RARE));

        //Tier 3 (EPIC) Weapons including Varieties
        //1
        items.add(new Weapon("BW003.1", "Elven Bow", WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires arrows with supernatural accuracy, guided by elven craftsmanship to strike weak points.", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Channels wind magic into arrows, granting them hypersonic velocity and enhanced penetration.", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Simultaneously fires multiple arrows that converge on the target with perfect magical coordination.", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("SW003.1", "Mythril Sword", WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "Utilizes mythril's perfect balance for lightning-fast strikes that seem to ignore air resistance.", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Leverages mythril's magical conductivity to enhance cutting power beyond physical limits.", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Releases stored kinetic energy in a single strike that bypasses conventional durability.", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("MGS003.1", "Arcane Staff", WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Draws directly from the staff's arcane core to project highly concentrated mana bolts.", 1.4, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Taps into dimensional energies to launch projectiles that phase through magical defenses.", 1.7, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes the staff's full arcane potential, creating a localized reality distortion on impact.", 2.2, 80, DamageType.MAGICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("BS003.1", "Runed Broadsword", WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "Activates strength-enhancing runes to multiply the force behind each swing without added effort.", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Engages impact runes that release stored energy upon contact, amplifying damage exponentially.", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Triggers all runic inscriptions simultaneously for a strike that shatters defenses magically and physically.", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("DR003.1", "Shadow Dagger", WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "Moves with unnatural speed, striking from angles that defy conventional defense.", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Phases briefly through solid matter to bypass armor and strike internal organs directly.", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Creates multiple temporal afterimages that all strike simultaneously from different directions.", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("MGSW003.1", "Runic Magic Sword", WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Projects rune-empowered arcane energy that seeks weak points in the target's magical defenses.", 1.4, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Activates elemental runes to coat the blade in destructive energies that disrupt magical constructs.", 1.7, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Synchronizes all runic patterns to create a cascading magical failure in the target upon impact.", 2.2, 80, DamageType.MAGICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        //2
        items.add(new Weapon("BW003.2", "Phoenix Bow", WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires arrows tipped with phoenix feathers that ignite upon impact, dealing additional thermal damage.", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Launches arrows wreathed in rebirth flames that seek the target and explode on contact.", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Channels the phoenix's full power to create a solar flare arrow that incinerates everything in its path.", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("SW003.2", "Tempest Sword", WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "Generates razor-sharp wind currents along the blade that extend its cutting range and speed.", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Creates a vortex of compressed air that amplifies striking force and creates vacuum damage.", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Summons a miniature storm around the blade, releasing lightning and hurricane-force winds upon impact.", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("MGS003.2", "Soulbinder Staff", WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Projects spectral energy that temporarily disrupts the target's spiritual cohesion.", 1.4, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Fires soul-shard projectiles that fragment the target's life essence upon impact.", 1.7, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes a spirit-rending blast that directly attacks the target's metaphysical existence.", 2.2, 80, DamageType.MAGICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("BS003.2", "Frostbane Broadsword", WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "Coats the blade in permafrost that deals cryogenic damage and slows the target's movements.", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Releases a glacial shockwave that freezes the air around the blade, creating ice shrapnel on impact.", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Channels absolute zero temperatures into a single strike that flash-freezes the target solid.", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("DR003.2", "Bloodthorn Dagger", WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "The dagger's thorn-like edge causes wounds that continue to bleed and resist natural healing.", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Strikes that connect create magical thorns inside the wound that expand and cause internal damage.", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Causes the target's blood to crystallize into sharp thorns that erupt from within their body.", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("MGSW003.2", "Lunaris Blade", WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Projects lunar energy that phases through physical armor and deals direct magical damage.", 1.4, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Charges the blade with gravitational forces that increase impact mass and create crushing pressure.", 1.7, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes concentrated moonlight that disintegrates matter at the molecular level upon contact.", 2.2, 80, DamageType.MAGICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        //3
        items.add(new Weapon("BW003.3", "Moonpiercer Bow", WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires arrows infused with lunar energy that ignore partial armor and maintain perfect trajectory.", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Launches crystalline arrows that fragment into moonbeam shards upon impact, dealing multi-layered damage.", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Charges an arrow with concentrated moonlight that phases through all barriers before detonating inside the target.", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("SW003.3", "Flameheart Sword", WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "The blade continuously generates intense heat, causing thermal shock with each strike that weakens structural integrity.", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Superheats the metal to plasma state, creating a cutting edge that melts through armor and flesh alike.", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Releases the sword's core temperature in a solar flare strike that vaporizes matter along the blade's path.", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("MGS003.3", "Crimson Staff", WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Projects hemomancy bolts that convert the target's blood into crystalline shards that cause internal damage.", 1.4, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Creates life-force syphoning projectiles that drain vitality and transfer it as kinetic impact energy.", 1.7, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes a cascade of blood magic that overwhelms the target's circulatory system, causing catastrophic internal pressure.", 2.2, 80, DamageType.MAGICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("BS003.3", "Earthshatter Broadsword", WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "Channels seismic vibrations through the blade, creating micro-fractures in the target's armor with each impact.", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Amplifies gravitational force during the swing, multiplying the blade's effective mass and striking power exponentially.", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Focuses tectonic pressure into a single strike that releases shockwaves capable of splitting the ground beneath the target.", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("DR003.3", "Nightshade Dagger", WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "The blade secretes neurotoxic compounds that disrupt motor functions and amplify pain reception upon contact.", 1.4, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Injects magical toxins that temporarily sever the connection between the target's nervous system and muscles.", 1.7, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Delivers a concentrated dose of spectral venom that attacks the target's life force directly, bypassing physical durability.", 2.2, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                120, Rarity.EPIC));
        items.add(new Weapon("MGSW003.3", "Aetherfang Blade", WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Projects raw aetheric energy that disrupts magical constructs and bypasses conventional magical defenses.", 1.4, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Charges the blade with dimensional energy, allowing it to cut through space-time to strike from unexpected angles.", 1.7, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Tears open a temporary rift in reality, unleashing primordial aether that erases matter from existence upon contact.", 2.2, 80, DamageType.MAGICAL, TargetType.SINGLE),
                120, Rarity.EPIC));

        //Tier 4 (LEGENDARY) Weapons including Varieties
        //1
        items.add(new Weapon("BW004.1", "Seraphic Longbow", WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires arrows of solidified light that purify targets on impact, dealing divine damage that ignores all mortal resistances.", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Summons celestial arrows that home with absolute precision and strike with the force of falling stars.", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Channels the full power of the heavens to launch the Arrow of Judgment, which erases evil from existence upon contact.", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                150, Rarity.LEGENDARY));
        items.add(new Weapon("SW004.1", "Demon King's Sword", WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "The blade constantly emits infernal energy that corrupts and decays anything it touches, bypassing divine protections.", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Unleashes hellfire waves that burn across dimensions, damaging the target's soul simultaneously with physical strikes.", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Opens a gateway to the abyss, dragging the target through multiple hell dimensions with a single reality-sundering slash.", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                150, Rarity.LEGENDARY));
        items.add(new Weapon("MGS004.1", "Abyssal Staff", WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Projects spheres of antimatter that annihilate conventional matter upon contact, leaving only void in their wake.", 1.7, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Creates localized reality collapses that compress space-time around the target, dealing existential damage.", 2.2, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Summons a primordial singularity that consumes the target's very concept from the fabric of reality.", 3.0, 80, DamageType.MAGICAL, TargetType.SINGLE),
                150, Rarity.LEGENDARY));
        items.add(new Weapon("BS004.1", "Crimson Eclipse Greatsword", WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "Each swing manipulates gravitational forces, increasing the blade's mass to planetary levels while maintaining wieldability.", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Channels solar flare energy into the edge, creating a cutting surface hot enough to split atoms upon impact.", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes the sword's dormant black hole core, creating an event horizon around the blade that consumes all matter it touches.", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                150, Rarity.LEGENDARY));
        items.add(new Weapon("DR004.1", "Reaper's Fang", WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "The blade cuts directly through the soul-severing life threads, dealing damage that cannot be healed by any mortal or divine means.", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Phases through all dimensions simultaneously to strike the target's past, present, and future selves in a single motion.", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Executes the Final Cut that permanently severs the target from the cycle of life and death, erasing their existence across all timelines.", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                150, Rarity.LEGENDARY));
        items.add(new Weapon("MGSW004.1", "Voidbreaker Blade", WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Projects quantum-disruption fields that cause targeted matter to simultaneously exist in multiple contradictory states until collapse.", 1.7, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Creates cascading reality fractures that propagate through the target's molecular structure, turning stability into chaos.", 2.2, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Shatters the boundary between existence and nothingness, unleashing pure void energy that unmakes creation at the fundamental level.", 3.0, 80, DamageType.MAGICAL, TargetType.SINGLE),
                150, Rarity.LEGENDARY));
        //2
        items.add(new Weapon("BW004.2", "Dawnseeker", WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires arrows woven from the first light of creation that ignore conventional physics and always find their mark.", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Launches solar prominence arrows that burn with the intensity of a newborn star, vaporizing all matter they contact.", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Channels the Big Bang's primordial energy to create an arrow that rewrites reality's laws upon impact, erasing targets from the timeline.", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                200, Rarity.LEGENDARY));
        items.add(new Weapon("SW004.2", "Oblivion's Edge", WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "The blade exists in a state of quantum superposition, simultaneously striking and having already struck its target.", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Creates localized entropy fields that accelerate time around the target to instant decay while the blade remains timeless.", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes the sword's true nature as a fragment of nothingness, cutting the very concept of the target's existence from the cosmic record.", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                200, Rarity.LEGENDARY));
        items.add(new Weapon("MGS004.2", "Vermilion Aegis", WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Projects crimson reality anchors that lock targets in place while simultaneously unraveling their molecular integrity.", 1.7, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Creates protective-destructive paradox fields that convert all defensive energy into offensive power against the target.", 2.2, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Activates the staff's ultimate protocol: rewriting causality to make the target's destruction an immutable fact of the universe.", 3.0, 80, DamageType.MAGICAL, TargetType.SINGLE),
                200, Rarity.LEGENDARY));
        items.add(new Weapon("BS004.2", "Draconic Cleaver", WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "Channels primordial dragon fury into each swing, dealing damage that scales exponentially with the target's own power.", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Unleashes the collective wrath of all dragonkind in a single strike that shatters mountains and splits the sky.", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Awakens the World-Ender protocol, focusing the energy that destroyed continents into a single, civilization-ending blow.", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                200, Rarity.LEGENDARY));
        items.add(new Weapon("DR004.2", "Silent Requiem", WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "Moves between heartbeats of time, striking in the spaces between moments where defenses cannot exist.", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Plays the target's death as a musical composition, with each note representing a fatal strike across multiple dimensions.", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Composes and performs the Final Symphony - a series of strikes that systematically erase the target from all possible realities.", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                200, Rarity.LEGENDARY));
        items.add(new Weapon("MGSW004.2", "Celestia's Wrath", WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Channels the anger of dying stars into each swing, projecting supernova energy that consumes all in its path.", 1.7, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Unleashes galactic core energy that creates miniature black holes along the blade's trajectory, warping space-time itself.", 2.2, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Becomes a conduit for the universe's fundamental forces, delivering a strike that can reset local reality to its pre-creation state.", 3.0, 80, DamageType.MAGICAL, TargetType.SINGLE),
                200, Rarity.LEGENDARY));
        //3
        items.add(new Weapon("BW004.3", "Heaven Piercer", WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires arrows that transcend dimensional barriers, striking targets regardless of their position in the celestial hierarchy.", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Launches divine judgment arrows that scale their damage based on the target's sins, with no upper limit to potential damage.", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Channels the collective power of the heavenly host to create an arrow that can pierce through divine realms and strike down gods.", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                200, Rarity.LEGENDARY));
        items.add(new Weapon("SW004.3", "Sky Splitter", WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "Each swing creates spatial fractures that extend infinitely upward, dealing damage that scales with the height of the target's ambitions.", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Unleashes a crescent of energy that travels along the fabric of reality, cutting through all barriers physical, magical, or conceptual.", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Focuses the sword's true power to perform a strike that literally divides heaven from earth, creating a permanent scar in reality.", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                200, Rarity.LEGENDARY));
        items.add(new Weapon("MGS004.3", "Orb of Avarice", WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Projects energy streams that permanently steal a portion of the target's power and add it to the wielder's capabilities.", 1.7, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Creates a power vortex that drains the target's life force, memories, and abilities simultaneously, storing them within the orb.", 2.2, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Activates the orb's ultimate function: temporarily claiming ownership of the target's very soul, rendering them completely powerless.", 3.0, 80, DamageType.MAGICAL, TargetType.SINGLE),
                200, Rarity.LEGENDARY));
        items.add(new Weapon("BS004.3", "Demon Dweller", WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "The blade houses captive demons that manifest with each strike, dealing additional spiritual damage that bypasses holy protections.", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Unleashes the trapped demons in a coordinated assault that attacks the target's body, mind, and soul simultaneously.", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Opens a permanent gateway to the abyss within the target, allowing an endless stream of demons to pour forth from their body.", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                200, Rarity.LEGENDARY));
        items.add(new Weapon("DR004.3", "Death's Gaze", WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "Strikes that connect impose a death countdown, with each subsequent attack exponentially reducing the time until instant death.", 1.7, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "The dagger temporarily becomes an extension of Death itself, ensuring the next strike will be fatal regardless of the target's defenses.", 2.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Forces the target to witness their own death across infinite timelines simultaneously, causing catastrophic existential collapse.", 3.0, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                200, Rarity.LEGENDARY));
        items.add(new Weapon("MGSW004.3", "Archangel's Judgement", WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Projects blades of divine light that automatically seek out and strike the most vulnerable aspects of the target's existence.", 1.7, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Channels celestial justice into the blade, dealing damage proportional to the target's crimes against cosmic order.", 2.2, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Unleashes the Final Judgment, a strike that permanently seals the target's fate across all dimensions and timelines.", 3.0, 80, DamageType.MAGICAL, TargetType.SINGLE),
                200, Rarity.LEGENDARY));

        //Hidden Tier (MYTHICAL) Weapons [Hehe I may or may not have made this a bit too Over-Powered] -zed
        items.add(new Weapon("BW005", "Recurve of Eternal Fate", WeaponType.BOW, "",
                new WeaponSkill("Basic Attack", "Fires arrows that rewrite probability, ensuring each shot was always destined to strike true while making all possible dodges impossible.", 2.5, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Launches arrows that travel backward through causality, striking the target before they're fired while ensuring the shot always happened.", 3.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Fires the Arrow of Finality that permanently closes all possible timelines where the target continues to exist, enforcing absolute death.", 4.5, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                200, Rarity.MYTHICAL));
        items.add(new Weapon("SW005", "Dawn of the Era Nova", WeaponType.SWORD, "",
                new WeaponSkill("Basic Attack", "Each swing carries the weight of a universe's birth, dealing damage that scales with the number of stars that will ever exist.", 2.5, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Unleashes the concentrated light of a billion big bangs in a single strike that resets local physics to pre-creation states.", 3.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Becomes the focal point for the heat death of the universe, delivering all remaining time's energy in one civilization-ending moment.", 4.5, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                200, Rarity.MYTHICAL));
        items.add(new Weapon("MGS005", "Tome of the Celestial Codex", WeaponType.MAGIC_STAFF, "",
                new WeaponSkill("Basic Attack", "Projects words from reality's source code that temporarily overwrite the target's existence parameters with fatal errors.", 2.5, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Reads aloud the cosmic laws of entropy and applies them directly to the target, accelerating their existence to heat death.", 3.2, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Accesses the universe's administrative privileges to execute 'TARGET.DELETE()' - permanently removing the target from creation's database.", 4.5, 80, DamageType.MAGICAL, TargetType.SINGLE),
                200, Rarity.MYTHICAL));
        items.add(new Weapon("BS005", "Cleave of the Oblivion's Edge", WeaponType.BROADSWORD, "",
                new WeaponSkill("Basic Attack", "The blade exists as a permanent wound in reality that spreads with each swing, making the concept of 'wholeness' impossible near it.", 2.5, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Cuts so deeply it severs the target's connection to fundamental forces, making them unable to interact with reality itself.", 3.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Performs the Final Division that separates the target from the concept of existence, leaving only the perfect void where they once were.", 4.5, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                200, Rarity.MYTHICAL));
        items.add(new Weapon("DR005", "Void of the Forsaken Rift", WeaponType.DAGGER, "",
                new WeaponSkill("Basic Attack", "Strikes from the spaces between dimensions where causality doesn't apply, dealing damage that cannot be prevented or undone.", 2.5, 0, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Creates permanent holes in the target's reality that slowly drain their existence into the absolute nothingness between universes.", 3.2, 40, DamageType.PHYSICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Opens a gateway to the pre-creation void within the target, collapsing their existence into the infinite silence that predates time.", 4.5, 80, DamageType.PHYSICAL, TargetType.SINGLE),
                200, Rarity.MYTHICAL));
        items.add(new Weapon("MGSW005", "Chronoblade of the Severed Realm", WeaponType.MAGIC_SWORD, "",
                new WeaponSkill("Basic Attack", "Cuts across all temporal instances simultaneously, striking the target's past, present, and future in a single timeless motion.", 2.5, 0, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Skill Attack", "Severs the target from the flow of time, making them a fixed point that cannot interact with or be perceived by the moving universe.", 3.2, 40, DamageType.MAGICAL, TargetType.SINGLE),
                new WeaponSkill("Ultimate Attack", "Executes the Temporal Finality Strike that collapses all of the target's possible timelines into a single moment of absolute cessation.", 4.5, 80, DamageType.MAGICAL, TargetType.SINGLE),
                200, Rarity.MYTHICAL));

        //npcs and enemies need to be finalized
        npcs.add(new GuideNPC("Frank", "Guide Companion"));
        npcs.add(new ShopNPC("Kyle", "Shopkeeper", items));

        System.out.println("Welcome to Akhai!");
        delay(1000);
        System.out.println("Choose your class:");
        System.out.println("1. Hawkseye");
        System.out.println("2. Blademaster");
        System.out.println("3. Runecaster");
        System.out.println("4. Berserker");
        System.out.println("5. Shinobi");
        System.out.println("6. Rune Knight");

        int choice = getIntInput("Enter your choice (1-6): ", 1, 7);

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
        delay(1200);

        System.out.println("\n=== Beginning your adventure in Akhai ===");
        System.out.println("You are on a quest to find the power to go back to your world.");
        delay(1500);
    }
    public void startGame() {
        addStarterPack(player, items);

        // Play Chapter 1 story automatically
        System.out.println("\n🌟 Your adventure begins...");
        delay(1200);
        storyManager.playChapter(1);

        while(gameRunning) {
            displayLevelMap();
            handleLevelActions();

            if (currentChapter > MAX_LEVEL) {
                System.out.println("\n🎊 Congratulations! You have completed the game!");

                if (storyManager.isStoryComplete()) {
                    System.out.println("✨ You have experienced the full story of Akhai!");
                    System.out.println("From a lost traveler to Khai's true successor...");
                    System.out.println("Your journey has saved an entire world!");
                }

                gameRunning = false;
            }
        }
    }

    //Exploration
    public void displayLevelMap() {
        System.out.println("\n🗺️  === CURRENT MAP ===");
        System.out.println("Current Chapter: " + currentChapter);
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
        int choice;
        if(currentChapter < 5) {
            System.out.println("\n🎯 What would you like to do?");
            System.out.println("[1] Explore");
            System.out.println("[2] Check stats");
            System.out.println("[3] Open Inventory");
            System.out.println("[4] Check resurrection status");
            System.out.println("[5] Talk to NPC");
            System.out.println("[6] View Current Story");
            System.out.println("[7] Proceed to Story");
            System.out.println("[8] Quit game");

            choice = getIntInput("Enter your choice: ", 1, 8);

            switch (choice) {
                case 1:
                    explore();
                    break;
                case 2:
                    player.displayStats();
                    break;
                case 3:
                    openInventory(player);
                    break;
                case 4:
                    checkResurrectionStatus();
                    break;
                case 5:
                    talkToNPC();
                    break;
                case 6:
                    viewCurrentStory();  // NEW
                    break;
                case 7:
                    //TODO before next level must fight boss that correlates to the story
                    //add boss before next chapter and must win in order to proceed
                    attemptChapterProgression();
                    break;
                case 8:
                    gameRunning = false;
                    System.out.println("Thanks for playing Akhai!");
                    break;
            }
        } else {
            System.out.println("\n🎯 What would you like to do?");
            System.out.println("[1] Enter the Finale");
            System.out.println("[2] Check stats");
            System.out.println("[3] Open Inventory");
            System.out.println("[4] Check resurrection status");
            System.out.println("[5] Talk to NPC");
            System.out.println("[6] View Current Story");
            System.out.println("[7] Quit game");

            choice = getIntInput("Enter your choice: ", 1, 7);

            switch (choice) {
                case 1:
                    //add final boss
                    //TODO battle finale boss (Demon King Din) must correlate to the story
                    battle(player, new Boss.DemonKingDin(), 2);
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
                    viewCurrentStory();  // NEW
                    break;
                case 7:
                    gameRunning = false;
                    System.out.println("Thanks for playing Akhai!");
                    break;
            }
        }
    }
    boolean EncounterZed = true;
    public void explore() {
        System.out.println("\nYou explore " + getLevelName(currentChapter) + "...");
        delay(1000);

        // Different encounter rates based on level
        double encounterRate = 0.75 + (currentChapter * 0.05);
        if (Math.random() <= encounterRate) {
            if(currentChapter < 3) {
                System.out.println("🚨 You encountered a Common enemy!");
                battleCommon();
            } else if (currentChapter == 3) {
                System.out.println("🚨 Warning! You encountered an Elite enemy!");
                battleElite();
            } else if(currentChapter == 4) {
                if(Math.random() >= 0.10) {
                    System.out.println("🚨 Warning! You encountered an Elite enemy!");
                    battleElite();
                } else {
                    if(EncounterZed) {
                        System.out.println("🚨" + ColorUtil.redBold(" WARNING!!!") + " You encountered a " + ColorUtil.redBold("BOSS") + " enemy!");
                        battle(player, new MiniBoss.EdgeLordZedjy());
                        EncounterZed = false;
                    } else {
                        System.out.println("🚨 Warning! You encountered an Elite enemy!");
                        battleElite();
                    }
                }
            } else {
                if(Math.random() >= 0.50) {
                    System.out.println("🚨 Warning! You encountered an Elite enemy!");
                    battleElite();
                } else {
                    if(EncounterZed) {
                        System.out.println("🚨" + ColorUtil.redBold(" WARNING!!!") + " You encountered a " + ColorUtil.redBold("BOSS") + " enemy!");
                        battle(player, new MiniBoss.EdgeLordZedjy());
                        EncounterZed = false;
                    } else {
                        System.out.println("🚨 Warning! You encountered an Elite enemy!");
                        battleElite();
                    }
                }
            }


            //battle(player, new CommonEnemy.Sludge());
            //battle(player, new Boss.DemonKingDin());
            //battle(player, new Boss.Abaddon());
            //battle(player, new MiniBoss.EdgeLordZedjy());

            //battle(player, new Boss.DemonKingDin(), 3);
        } else {
            // Chance for finding items or hidden events
            if (Math.random() > 0.7) {
                findHiddenTreasure();
                delay(800);
            } else {
                System.out.println("You found nothing of interest.");
                delay(800);
            }
        }
    }
    //TODO make sure that if they already own the weapon they dont get another copy of it -for frank
    private void obtainLoot(Character player){
        double chance = Math.random(); //Made a variable para di sigeg gamit ug Math.random ugh
        switch(currentChapter) {
            case 1:
                Chest CommonChest = new Chest.CommonChest();
                System.out.println("💎 You found a " + CommonChest.getName() + "!");
                CommonChest.obtain(player);
                //40% to obtain a weapon :P
                if(Math.random() <= 0.4) {
                    switch (player.getClassType()) {
                        case HAWKSEYE:
                            if (Math.random() <= 0.5) {
                                player.obtainItem(findItemId("BW001.1", items, 1));
                            } else {
                                player.obtainItem(findItemId("BW001.2", items, 1));
                            }
                            break;
                        case BLADEMASTER:
                            if (Math.random() <= 0.5) {
                                player.obtainItem(findItemId("SW001.1", items, 1));
                            } else {
                                player.obtainItem(findItemId("SW001.2", items, 1));
                            }
                            break;
                        case BERSERKER:
                            if (Math.random() <= 0.5) {
                                player.obtainItem(findItemId("BS001.1", items, 1));
                            } else {
                                player.obtainItem(findItemId("BS001.2", items, 1));
                            }
                            break;
                        case ASSASSIN:
                            if (Math.random() <= 0.5) {
                                player.obtainItem(findItemId("DR001.1", items, 1));
                            } else {
                                player.obtainItem(findItemId("DR001.2", items, 1));
                            }
                            break;
                        case RUNECASTER:
                            if (Math.random() <= 0.5) {
                                player.obtainItem(findItemId("MGS001.1", items, 1));
                            } else {
                                player.obtainItem(findItemId("MGS001.2", items, 1));
                            }
                            break;
                        case RUNEKNIGHT:
                            if (Math.random() <= 0.5) {
                                player.obtainItem(findItemId("MGSW001.1", items, 1));
                            } else {
                                player.obtainItem(findItemId("MGSW001.2", items, 1));
                            }
                            break;
                    }
                }
                break;
            case 2:
                if(Math.random() > 0.9){
                    Chest EliteChest = new Chest.EliteChest();
                    System.out.println("💎 You found a "+EliteChest.getName()+"!");
                    EliteChest.obtain(player);
                    //40% chance to obtain weap
                    if(Math.random() <= 0.4) {
                        switch (player.getClassType()) {
                            case HAWKSEYE:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("BW002.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("BW002.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("BW002.3", items, 1));
                                }
                                break;
                            case BLADEMASTER:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("SW002.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("SW002.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("SW002.3", items, 1));
                                }
                                break;
                            case BERSERKER:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("BS002.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("BS002.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("BS002.3", items, 1));
                                }
                                break;
                            case ASSASSIN:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("DR002.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("DR002.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("DR002.3", items, 1));
                                }
                                break;
                            case RUNECASTER:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("MGS002.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("MGS002.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("MGS002.3", items, 1));
                                }
                                break;
                            case RUNEKNIGHT:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("MGSW002.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("MGSW002.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("MGSW002.3", items, 1));
                                }
                                break;
                        }
                    }
                }
                break;
            case 3:
                if(Math.random() > 0.8){
                    Chest EpicChest = new Chest.EpicChest();
                    System.out.println("💎 You found a "+EpicChest.getName()+"!");
                    EpicChest.obtain(player);
                    //30% chance to obtain weap
                    if(Math.random() <= 0.3) {
                        switch (player.getClassType()) {
                            case HAWKSEYE:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("BW003.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("BW003.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("BW003.3", items, 1));
                                }
                                break;
                            case BLADEMASTER:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("SW003.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("SW003.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("SW003.3", items, 1));
                                }
                                break;
                            case BERSERKER:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("BS003.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("BS003.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("BS003.3", items, 1));
                                }
                                break;
                            case ASSASSIN:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("DR003.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("DR003.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("DR003.3", items, 1));
                                }
                                break;
                            case RUNECASTER:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("MGS003.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("MGS003.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("MGS003.3", items, 1));
                                }
                                break;
                            case RUNEKNIGHT:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("MGSW003.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("MGSW003.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("MGSW003.3", items, 1));
                                }
                                break;
                        }
                    }
                }
                break;
            case 4:
                if(Math.random() > 0.8){
                    Chest EpicChest = new Chest.EpicChest();
                    System.out.println("💎 You found a "+EpicChest.getName()+"!");
                    EpicChest.obtain(player);
                    //30% chance to obtain weap
                    if(Math.random() <= 0.3) {
                        switch (player.getClassType()) {
                            case HAWKSEYE:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("BW003.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("BW003.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("BW003.3", items, 1));
                                }
                                break;
                            case BLADEMASTER:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("SW003.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("SW003.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("SW003.3", items, 1));
                                }
                                break;
                            case BERSERKER:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("BS003.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("BS003.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("BS003.3", items, 1));
                                }
                                break;
                            case ASSASSIN:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("DR003.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("DR003.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("DR003.3", items, 1));
                                }
                                break;
                            case RUNECASTER:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("MGS003.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("MGS003.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("MGS003.3", items, 1));
                                }
                                break;
                            case RUNEKNIGHT:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("MGSW003.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("MGSW003.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("MGSW003.3", items, 1));
                                }
                                break;
                        }
                    }
                }
                break;
            case 5:
                if(Math.random() > 0.7){
                    Chest LegendaryChest = new Chest.LegendaryChest();
                    System.out.println("💎 You found a "+LegendaryChest.getName()+"!");
                    LegendaryChest.obtain(player);
                    //20% chance to obtain weap
                    if(Math.random() <= 0.2) {
                        switch (player.getClassType()) {
                            case HAWKSEYE:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("BW004.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("BW004.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("BW004.3", items, 1));
                                }
                                break;
                            case BLADEMASTER:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("SW004.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("SW004.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("SW004.3", items, 1));
                                }
                                break;
                            case BERSERKER:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("BS004.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("BS004.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("BS004.3", items, 1));
                                }
                                break;
                            case ASSASSIN:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("DR004.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("DR004.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("DR004.3", items, 1));
                                }
                                break;
                            case RUNECASTER:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("MGS004.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("MGS004.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("MGS004.3", items, 1));
                                }
                                break;
                            case RUNEKNIGHT:
                                if (chance <= 0.33) {
                                    player.obtainItem(findItemId("MGSW004.1", items, 1));
                                } else if (chance <= 0.66) {
                                    player.obtainItem(findItemId("MGSW004.2", items, 1));
                                } else {
                                    player.obtainItem(findItemId("MGSW004.3", items, 1));
                                }
                                break;
                        }
                    }
                }
        }
    }
    private void findHiddenTreasure() {
        Random rnd = new Random();
        System.out.println("💎 You found a hidden treasure!");
        int expBonus = currentChapter * rnd.nextInt(5, 11);
        player.gainExperience(expBonus);
    }
    private void attemptChapterProgression() {
        if (currentChapter >= MAX_LEVEL) {
            System.out.println("🎉 Congratulations! You've completed all chapters!");

            if (storyManager.isStoryComplete()) {
                System.out.println("\n✨ You have witnessed the complete story of Akhai!");
                System.out.println("Thank you for playing!");
            }
            return;
        }

        // Check if player is strong enough to progress
        if (player.getLevel() < currentChapter * 4) {
            System.out.println("⚠️  You're not strong enough to face the challenges ahead!");
            System.out.println("Recommended level for next chapter: " + (currentChapter * 4));
            System.out.println("Your current level: " + player.getLevel());
            return;
        }

        System.out.println("\n📖 Beginning Chapter " + (currentChapter + 1) + "...");
        delay(1000);

        // Mark current chapter as completed
        levelsCompleted[currentChapter] = true;

        // Move to next chapter
        currentChapter++;
        storyManager.setCurrentChapter(currentChapter);

        // Play the new chapter's story
        storyManager.playChapter(currentChapter);

        // Scale enemies for new chapter
        //scaleEnemiesForCurrentLevel();

        System.out.println("\n⚔️ New challenges await in " + getLevelName(currentChapter) + "!");
    }
    public void talkToNPC() {
        System.out.println("Which NPC would you like to talk to?");
        for (int i = 0; i < npcs.size(); i++) {
            System.out.println((i + 1) + ". " + npcs.get(i).getName() + " - " + npcs.get(i).getDescription());
        }

        int choice = getIntInput("Enter your choice: ", 1, npcs.size());
        NPC selectedNPC = npcs.get(choice - 1);

        System.out.println("You approach " + selectedNPC.getName() + "...");
        selectedNPC.interact(player, currentChapter);
    }

    private void viewCurrentStory() {
        System.out.println("\n📖 STORY MENU");
        System.out.println("[1] View current chapter");
        System.out.println("[2] View all unlocked chapters");
        System.out.println("[3] Back");

        int choice = getIntInput("Choose: ", 1, 3);

        switch(choice) {
            case 1:
                storyManager.playChapter(currentChapter);
                break;
            case 2:
                viewAllChapters();
                break;
            case 3:
                return;
        }
    }

    private void viewAllChapters() {
        System.out.println("\n📚 ALL CHAPTERS");
        for (int i = 1; i <= currentChapter; i++) {
            Chapter chapter = storyManager.getChapter(i);
            String status = chapter.isCompleted() ? "✅" : "🔒";
            System.out.println(status + " Chapter " + i + ": " + chapter.getChapterTitle());
        }

        System.out.println("\nEnter chapter number to replay (or 0 to go back): ");
        int choice = getIntInput("Choose: ", 0, currentChapter);

        if (choice > 0) {
            storyManager.playChapter(choice);
        }
    }

    //Random Battle (Common Enemies)
    public void battleCommon() {
        inBattle = true;
        int turns = 1;
        Enemy enemy = randomizeCommonEnemy();

        System.out.println("🚨 A wild " + enemy.getName() + " appears!");
        delay(1000);

        int baseExp = 30;

        int playerCurrentSpeed = player.getSpeed();
        int playerOriginalSpeed = player.getSpeed();
        int enemyCurrentSpeed = enemy.getSpeed();
        int enemyOriginalSpeed = enemy.getSpeed();

        while (enemy.getHealth() > 0 && player.getHealth() > 0 && inBattle) {
            String playerHealthBar = createHealthBar(player.getHealth(), player.getMaxHealth(), 20);
            String playerEnergyBar = createEnergyBar(player.getEnergy(), player.getMaxEnergy(), 20);
            String enemyHealthBar =  createHealthBar(enemy.getHealth(), enemy.getMaxHealth(), 20);

            System.out.println("\n══════════ TURN "+(turns++)+" ══════════");
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
        //TODO This needs to be executed outside the battle method due to the wave system utilizing 2 or more battle methods.
        playerHealthCheck(enemy, baseExp, player);

        // Clear battle effects after combat
        clearBattleEffects(enemy);

        inBattle = false;
    }
    //Random Battle (Elite Enemies)
    public void battleElite() {
        inBattle = true;
        int turns = 1;
        Enemy enemy = randomizeEliteEnemy();

        System.out.println("🚨 A wild " + enemy.getName() + " appears!");
        delay(1000);

        int baseExp = 30;

        int playerCurrentSpeed = player.getSpeed();
        int playerOriginalSpeed = player.getSpeed();
        int enemyCurrentSpeed = enemy.getSpeed();
        int enemyOriginalSpeed = enemy.getSpeed();

        while (enemy.getHealth() > 0 && player.getHealth() > 0 && inBattle) {
            String playerHealthBar = createHealthBar(player.getHealth(), player.getMaxHealth(), 20);
            String playerEnergyBar = createEnergyBar(player.getEnergy(), player.getMaxEnergy(), 20);
            String enemyHealthBar =  createHealthBar(enemy.getHealth(), enemy.getMaxHealth(), 20);

            System.out.println("\n══════════ TURN "+(turns++)+" ══════════");
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
        //TODO This needs to be executed outside the battle method due to the wave system utilizing 2 or more battle methods.
        playerHealthCheck(enemy, baseExp, player);

        // Clear battle effects after combat
        clearBattleEffects(enemy);

        inBattle = false;
    }

    //Fixed Battle
    public void battle(Character player, CommonEnemy enemy) {
        inBattle = true;
        int turns = 1;

        System.out.println("🚨 " + enemy.getName() + " appears!");
        delay(1000);

        int baseExp = 25 + (currentChapter * 5);

        int playerCurrentSpeed = player.getSpeed();
        int playerOriginalSpeed = player.getSpeed();
        int enemyCurrentSpeed = enemy.getSpeed();
        int enemyOriginalSpeed = enemy.getSpeed();

        //Reset Ult charges
        //player.resetUltimateCounter();

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
            resetHasConsumed(items);
        }
        //TODO This needs to be executed outside the battle method due to the wave system utilizing 2 or more battle methods.
        playerHealthCheck(enemy, baseExp, player);

        // Clear battle effects after combat
        clearBattleEffects(enemy);

        inBattle = false;
    }
    public void battle(Character player, EliteEnemy enemy){
        inBattle = true;
        int turns = 1;

        System.out.println("🚨 Careful! " + enemy.getName() + " appears!");
        delay(1000);

        int baseExp = 30;

        int playerCurrentSpeed = player.getSpeed();
        int playerOriginalSpeed = player.getSpeed();
        int enemyCurrentSpeed = enemy.getSpeed();
        int enemyOriginalSpeed = enemy.getSpeed();

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
            resetHasConsumed(items);
        }
        //TODO This needs to be executed outside the battle method due to the wave system utilizing 2 or more battle methods.

        playerHealthCheck(enemy, baseExp, player);

        // Clear battle effects after combat
        clearBattleEffects(enemy);

        inBattle = false;
    }
    public void battle(Character player, Boss enemy){
        inBattle = true;
        int turns = 1;

        System.out.println("🚨 BOSS " + enemy.getName() + " appears!");
        delay(1000);

        int baseExp = 30;

        int playerCurrentSpeed = player.getSpeed();
        int playerOriginalSpeed = player.getSpeed();
        int enemyCurrentSpeed = enemy.getSpeed();
        int enemyOriginalSpeed = enemy.getSpeed();

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
            resetHasConsumed(items);
        }
        //TODO This needs to be executed outside the battle method due to the wave system utilizing 2 or more battle methods.
        playerHealthCheck(enemy, baseExp, player);

        clearBattleEffects(enemy);

        inBattle = false;
    }
    public void battle(Character player, Boss enemy, int maxWave/*min: 2 | max: 3*/){
        for(int wave = 1; wave <= maxWave; wave++){
            if(wave == maxWave){
                battle(player, enemy);
            }else if(wave == 1 && maxWave == 3){
                battle(player, randomizeEliteEnemy());
            }else{
                battle(player, randomizeBoss());
            }
        }

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

    private void playerHealthCheck(Enemy enemy, int baseExp, Character player){
        if (player.getHealth() <= 0) {
            handlePlayerDefeat();
        } else {
            handleVictory(enemy, baseExp, player);
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
    private void handleVictory(Enemy enemy, int baseExp, Character player) {
        if(enemy.getHealth() <= 0){
            System.out.println("💀 " + enemy.getName() + " has been defeated!");
            System.out.println("🎉 You defeated " + enemy.getName() + "!");
            delay(500);
            //Commented this out cuz it already prints this in the gainExperience method
            //System.out.println("💰 Gained " + baseExp + " experience!");
            player.gainExperience(baseExp);
            delay(500);
            obtainLoot(player);
            delay(500);

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

    //Character
    public void addStarterPack(Character player, List<Item> items){
        System.out.println("Adding starter pack...");
        delay(800);
        if(player instanceof Hawkseye || player instanceof Blademaster || player instanceof Berserker || player instanceof Shinobi) {
            player.obtainItem(findItemId("PDP001", items, 5));
            player.obtainItem(findItemId("HP001", items, 5));
            player.obtainItem(findItemId("EP001", items, 5));
            player.obtainItem(findItemId("SP001", items, 5));
            switch(player.getClassType()){
                case HAWKSEYE:
                    player.obtainItem(findItemId("BW001.1", items, 1));
                    break;
                case BLADEMASTER:
                    player.obtainItem(findItemId("SW001.1", items, 1));
                    break;
                case BERSERKER:
                    player.obtainItem(findItemId("BS001.1", items, 1));
                    break;
                case ASSASSIN:
                    player.obtainItem(findItemId("DR001.1", items, 1));
                    break;
            }
        }else if(player instanceof Runecaster || player instanceof RuneKnight){
            player.obtainItem(findItemId("MDP001", items, 5));
            player.obtainItem(findItemId("HP001", items, 5));
            player.obtainItem(findItemId("EP001", items, 5));
            player.obtainItem(findItemId("SP001", items, 5));
            switch(player.getClassType()) {
                case RUNECASTER:
                    player.obtainItem(findItemId("MGS001.1", items, 1));
                    break;
                case RUNEKNIGHT:
                    player.obtainItem(findItemId("MGSW001.1", items, 1));
                    break;
            }
        }else{ // JinwooSun
            player.obtainItem(findItemId("DR001.1", items, 1));
            player.obtainItem(findItemId("DR002.1", items, 1));
            player.obtainItem(findItemId("DR003.2", items, 1));
            player.obtainItem(findItemId("DR004.1", items, 1));
            player.obtainItem(findItemId("DR005", items, 1));
            player.obtainItem(findItemId("HP004", items, 99));
            player.obtainItem(findItemId("EP004", items, 99));
            player.obtainItem(findItemId("PDP004", items, 99));
            player.obtainItem(findItemId("SP004", items, 99));
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
                item.displayInfo();
                System.out.println("\nUse " + item.getName() + "?");
                System.out.println("[1] Use Item");
                System.out.println("[2] Back");

                confirm = getIntInput("Choose action: ", 1, 2);
                if(confirm == 1){
                    item.use(player);
                    delay(1000);

                    if(((Consumable) item).getHasConsumed() == true){
                        confirm = 0;
                    }else{
                        // For consumable potions in battle, don't end the turn
                        if(item.getQuantity() > 0) {
                            System.out.println("Item used! You can take another action.");
                            openInventory(player);
                            result = 0; // Item used, continue turn
                        } else {
                            // Item was consumed completely
                            player.getInventory().removeItem(item);
                            System.out.println("Item used! You can take another action.");
                            openInventory(player);
                            result = 0; // Item used, continue turn
                        }
                    }
                }else{
                    confirm = 0;
                    result = -1;
                }
            } else {
                // For non-consumables or non-battle usage
                System.out.println("========== SELECTING ITEM ==========");
                item.displayInfo();
                System.out.println("\nSelect "+item.getName()+"?: ");
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
    public void obtainGold(Character player){
        Random rnd = new Random();
        int goldYield = currentChapter * rnd.nextInt(20, 26);
        player.setCurrency(goldYield);
        System.out.println("\uD83E\uDE99 Gained "+goldYield+" gold coins!");
    }

    //Enemy
    public CommonEnemy randomizeCommonEnemy(){
        Random rand = new Random();
        int choice = rand.nextInt(10);

        switch(choice){
            case 0:
                return new CommonEnemy.Gloomfang();
            case 1:
                return new CommonEnemy.Mireling();
            case 2:
                return new CommonEnemy.CrossroadSneak();
            case 3:
                return new CommonEnemy.WhisperChimera();
            case 4:
                return new CommonEnemy.Mistwing();
            case 5:
                return new CommonEnemy.CrossroadCutpurse();
            case 6:
                return new CommonEnemy.Murkgut();
            case 7:
                return new CommonEnemy.RoadbreakerBrute();
            case 8:
                return new CommonEnemy.CrossroadFiend();
            case 9:
                return new CommonEnemy.Frank();
        }
        return null;
    }
    public EliteEnemy randomizeEliteEnemy(){
        Random rand = new Random();
        int choice = rand.nextInt(10);

        switch(choice){
            case 0:
                return new EliteEnemy.PrimordialVishap();
            case 1:
                return new EliteEnemy.BathysmalHunter();
            case 2:
                return new EliteEnemy.AbyssMage();
            case 3:
                return new EliteEnemy.WildernessExile();
            case 4:
                return new EliteEnemy.ShadowHusk();
            case 5:
                return new EliteEnemy.RuinGuard();
            case 6:
                return new EliteEnemy.AbyssRogue();
            case 7:
                return new EliteEnemy.MirrorMaiden();
            case 8:
                return new EliteEnemy.AbyssHerald();
            case 9:
                return new EliteEnemy.Daniel();

        }
        return null;
    }
    //unnecessary but why not lol | Bruh HAHAHAHAHA instead of random boss ato butangan each chap refer to the boss class -zed
    public Boss randomizeBoss(){
        Random rand = new Random();
        int choice = rand.nextInt(3);

        switch(choice){
            case 0:
                return new Boss.Kamish();
            case 1:
                return new Boss.Kamish();
            case 2:
                return new Boss.Kamish();
        }
        return null;
    }

    //Item
    public Item findItemId(String itemId, List<Item> item, int quantity){
        Item foundItem = items.get(0);
        for(int i = 0; i < item.size(); i++){
            if(itemId.equals(item.get(i).getItemId())) {
                item.get(i).setQuantity(quantity);
                foundItem = item.get(i);
            }
        }
        return foundItem;
    }
    public void resetHasConsumed(List<Item> items){
        for(Item item : items){
            if(item instanceof Consumable consumable){
                if(consumable instanceof PhysicalDamagePotion || consumable instanceof MagicalDamagePotion || consumable instanceof SpeedPotion || consumable instanceof EvasivenessPotion){
                    consumable.setHasConsumed(false);
                }
            }
        }
    }

    public int getIntInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                String userInput = scan.nextLine().trim();
                int input = Integer.parseInt(userInput);

                if (input < min || input > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                    continue;
                }

                return input;

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid whole number.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Please try again.");
            }
        }
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