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
import TextFormat.ConsoleColors;

import java.awt.*;
import java.util.*;
import java.util.List;

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
    private Initialization initialize = new Initialization();

    //Game
    String thyName;
    public void initializeGame() {
        // Initialize story system
        storyManager = new StoryManager();
        
        initialize.initPotions(items);
        initialize.initWeaponT1(items);
        initialize.initWeaponT2(items);
        initialize.initWeaponT3(items);
        initialize.initWeaponT4(items);
        initialize.initWeaponHiddenTier(items);

        System.out.println("Welcome to Akhai!");
        delay(1000);

        while(true){
            thyName = getStringInput("What is thy name?: ");
            int choice = getIntInput("Is "+thyName+" your name?: (Yes [1]| No [0]): ", 0, 1);
            if(choice == 1){
                break;
            }
        }

        int classChosen = 0;
        while(classChosen == 0){
            System.out.println(ColorUtil.brightBlueBold("╔════════════════════════════════════════════════════╗"));
            System.out.println(ColorUtil.brightBlueBold("║") + ColorUtil.brightYellowBold("                 Choose your class!                 ") + ColorUtil.brightBlueBold("║"));
            System.out.println(ColorUtil.brightBlueBold("╠════════════════════════════════════════════════════╣"));
            System.out.println(ColorUtil.brightBlueBold("║  ") + ColorUtil.brightCyanBold("1.")+ColorUtil.greenBold(" Hawkseye") + ColorUtil.brightBlueBold("                                       ║"));
            System.out.println(ColorUtil.brightBlueBold("║  ") + ColorUtil.brightCyanBold("2.")+ColorUtil.blueBright(" Blademaster") + ColorUtil.brightBlueBold("                                    ║"));
            System.out.println(ColorUtil.brightBlueBold("║  ") + ColorUtil.brightCyanBold("3.")+ColorUtil.purpleBright(" Rune Caster") + ColorUtil.brightBlueBold("                                    ║"));
            System.out.println(ColorUtil.brightBlueBold("║  ") + ColorUtil.brightCyanBold("4.")+ColorUtil.orange(" Berserker") + ColorUtil.brightBlueBold("                                      ║"));
            System.out.println(ColorUtil.brightBlueBold("║  ") + ColorUtil.brightCyanBold("5.")+ColorUtil.purple(" Shinobi") + ColorUtil.brightBlueBold("                                        ║"));
            System.out.println(ColorUtil.brightBlueBold("║  ") + ColorUtil.brightCyanBold("6.")+ColorUtil.yellowBright(" Rune Knight") + ColorUtil.brightBlueBold("                                    ║"));
            System.out.println(ColorUtil.brightBlueBold("╚════════════════════════════════════════════════════╝"));

        int choice = getIntInput("Enter your choice (1-6): ", 1, 7);

            switch (choice) {
                case 1:
                    System.out.println(ColorUtil.brightBlueBold("╔═════════════════════")+ColorUtil.brightCyanBold(" Hawkseye ")+ColorUtil.brightBlueBold("═════════════════════╗"));
                    System.out.println(ColorUtil.greenBold("   Deals the highest physical damage from range, a\n   precision marksman who never misses their mark."));
                    System.out.println(ColorUtil.brightBlueBold("╚════════════════════════════════════════════════════╝"));
                    classChosen = getIntInput("Select class? (Yes [1] | No [0]): ", 0, 1);
                    if(classChosen == 1){
                        player = new Hawkseye("Hawkseye " + thyName);
                        System.out.println(ColorUtil.brightCyanBold("\nYou have chosen the Hawkseye class!"));
                    }
                    break;
                case 2:
                    System.out.println(ColorUtil.brightBlueBold("╔════════════════════")+ColorUtil.brightCyanBold(" Blademaster ")+ColorUtil.brightBlueBold("═══════════════════╗"));
                    System.out.println(ColorUtil.blueBright("   A versatile all-rounder fighter, perfectly\n   balanced stats for any combat situation."));
                    System.out.println(ColorUtil.brightBlueBold("╚════════════════════════════════════════════════════╝"));
                    classChosen = getIntInput("Select class? (Yes [1] | No [0]): ", 0, 1);
                    if(classChosen == 1){
                        player = new Blademaster(thyName + " the Blademaster");
                        System.out.println(ColorUtil.brightCyanBold("\nYou have chosen the Blademaster class!"));
                    }
                    break;
                case 3:
                    System.out.println(ColorUtil.brightBlueBold("╔════════════════════")+ColorUtil.brightCyanBold(" Rune Caster ")+ColorUtil.brightBlueBold("═══════════════════╗"));
                    System.out.println(ColorUtil.purpleBright("   Deals the highest damage output with devastating\n   spells, sacrifices speed for overwhelming power."));
                    System.out.println(ColorUtil.brightBlueBold("╚════════════════════════════════════════════════════╝"));
                    classChosen = getIntInput("Select class? (Yes [1] | No [0]): ", 0, 1);
                    if(classChosen == 1){
                        player = new Runecaster("Rune Caster " + thyName);
                        System.out.println(ColorUtil.brightCyanBold("\nYou have chosen the Rune Caster class!"));
                    }
                    break;
                case 4:
                    System.out.println(ColorUtil.brightBlueBold("╔════════════════════")+ColorUtil.brightCyanBold(" Berserker ")+ColorUtil.brightBlueBold("═════════════════════╗"));
                    System.out.println(ColorUtil.orange("   Tanks through battles with the highest HP pool,\n   a relentless force that outlasts all opponents."));
                    System.out.println(ColorUtil.brightBlueBold("╚════════════════════════════════════════════════════╝"));
                    classChosen = getIntInput("Select class? (Yes [1] | No [0]): ", 0, 1);
                    if(classChosen == 1){
                        player = new Berserker("Berserker " + thyName);
                        System.out.println(ColorUtil.brightCyanBold("\nYou have chosen the Berserker class!"));
                    }
                    break;
                case 5:
                    System.out.println(ColorUtil.brightBlueBold("╔═════════════════════")+ColorUtil.brightCyanBold(" Shinobi ")+ColorUtil.brightBlueBold("══════════════════════╗"));
                    System.out.println(ColorUtil.purple("   Overwhelms enemies with blinding speed and can\n   dish out damage before any enemy can react."));
                    System.out.println(ColorUtil.brightBlueBold("╚════════════════════════════════════════════════════╝"));
                    classChosen = getIntInput("Select class? (Yes [1] | No [0]): ", 0, 1);
                    if(classChosen == 1){
                        player = new Shinobi("Shinobi " + thyName);
                        System.out.println(ColorUtil.brightCyanBold("\nYou have chosen the Shinobi class!"));
                    }
                    break;
                case 6:
                    System.out.println(ColorUtil.brightBlueBold("╔════════════════════")+ColorUtil.brightCyanBold(" Rune Knight ")+ColorUtil.brightBlueBold("═══════════════════╗"));
                    System.out.println(ColorUtil.yellowBright("   A magical warrior that blends sword and sorcery\n   with both offensive & defensive capabilities."));
                    System.out.println(ColorUtil.brightBlueBold("╚════════════════════════════════════════════════════╝"));
                    classChosen = getIntInput("Select class? (Yes [1] | No [0]): ", 0, 1);
                    if(classChosen == 1){
                        player = new RuneKnight(thyName + " the Rune Knight");
                        System.out.println(ColorUtil.brightCyanBold("\nYou have chosen the Rune Knight class!"));
                    }
                    break;
                case 7:
                    System.out.println(ColorUtil.brightBlueBold("╔══════════════════")+ ColorUtil.brightCyanBold(" The Chosen One ") +ColorUtil.brightBlueBold("══════════════════╗"));
                    System.out.println(ColorUtil.brightRedBold("   Throughout Heaven and Earth, I Alone Am\n   The Honored One."));
                    System.out.println(ColorUtil.brightBlueBold("╚════════════════════════════════════════════════════╝"));
                    classChosen = getIntInput("Select class? (Yes [1] | No [0]): ", 0, 1);
                    if(classChosen == 1){
                        player = new JinwooSun(thyName + " (Test)");
                        System.out.println(ColorUtil.brightCyanBold("\nSelecting Test Class..."));
                    }
                    break;
            }
            delay(900);
        }

        System.out.println(ColorUtil.brightBlueBold("\n═════════════ Beginning your adventure in Akhai! ═════════════"));
        System.out.println(ColorUtil.cyanBold("You are on a quest to find the power to go back to your world.\n"));
        delay(800);
    }
    public void startGame() {
        addStarterPack(player, items);

        // Play Chapter 1 story automatically
        System.out.println(ColorUtil.brightBlueBold("\n🌟 Your adventure begins..."));
        delay(800);
        storyManager.playChapter(1);

        while(gameRunning) {
            displayLevelMap();

            //"What would you like to do?" || Main menu
            handleLevelActions(player);

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
        System.out.println(ColorUtil.blueBright("\n╔══════════════════") + ColorUtil.brightCyanBold("   PROGRESS   ") + ColorUtil.blueBright("════════════════════╗"));
        System.out.println(ColorUtil.brightCyanBold("   Current Chapter:") + ColorUtil.brightYellowBold(" " + currentChapter));
        System.out.println(ColorUtil.brightCyanBold("   Story Progress: ") + ColorUtil.brightYellowBold(getCompletionPercentage() + "% complete"));

        System.out.println(ColorUtil.blueBright(" ──────────────────") + ColorUtil.brightCyanBold("  CURRENT MAP  ") + ColorUtil.blueBright("────────────────── "));
        for (int i = 1; i <= MAX_LEVEL; i++) {
            String status = (i == currentChapter) ? ColorUtil.brightYellowBold("📍 CURRENT") :
                    (levelsCompleted[i]) ? ColorUtil.brightGreenBold("✅ COMPLETED") : ColorUtil.red("🔒 LOCKED");
            System.out.println(ColorUtil.brightCyanBold("   Chapter " + i + ": ") + getLevelName(i) + " - " + status);
        }
        System.out.println(ColorUtil.blueBright("╚════════════════════════════════════════════════════╝"));
        delay(500);
    }
    private String getLevelName(int level) {
        switch(level) {
            case 1: return ColorUtil.blueGreen("Whispering Crossroads");
            case 2: return ColorUtil.blueBright("Fungal Lumewood Forest");
            case 3: return ColorUtil.brightPurpleBold("Ruins of a Fallen Star");
            case 4: return ColorUtil.brightYellowBold("Crystal Caverns");
            case 5: return ColorUtil.brightRedBold("Root of All Worlds");
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
    public void handleLevelActions(Character player) {
        int choice;
        if(currentChapter < 5) {
            //Display stats on main menu
            //dynamic title with fixed width of 54
            String title = " " + thyName + " ";
            int totalWidth = 53; //53 since title length is -1
            int titleLength = title.length();

            // Calculate padding needed on each side
            int padding = (totalWidth - titleLength) / 2;
            String leftBorder = "╔" + "═".repeat(padding);
            String rightBorder = "═".repeat(totalWidth - padding - titleLength - 1) + "╗";
            delay(500);
            System.out.println(ColorUtil.blueBright(leftBorder) + ColorUtil.brightCyanBold(title) + ColorUtil.blueBright(rightBorder));
            String playerExpBar = createExperienceBar(player, 37);
            String playerHealthBar = createHealthBar(player, 37);
            String playerEnergyBar = createEnergyBar(player, 37);

            System.out.println(ColorUtil.green("   " + playerHealthBar + " HP\n   ") + playerEnergyBar + "\n   " + ColorUtil.cyan(playerExpBar + " Exp"));
            System.out.println(ColorUtil.blueBright("╚════════════════════════════════════════════════════╝"));
            delay(500);
            System.out.println(ColorUtil.blueBright("╔════════════════════════════════════════════════════╗"));
            System.out.println(ColorUtil.blueBright("║") + ColorUtil.brightCyanBold("             What would you like to do?             ") + ColorUtil.blueBright("║"));
            System.out.println(ColorUtil.blueBright("╠════════════════════════════════════════════════════╣"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[1] Explore") + ColorUtil.blueBright("                                       ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[2] Check Stats and Resurrection status") + ColorUtil.blueBright("           ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[3] Open Inventory") + ColorUtil.blueBright("                                ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[4] Shop") + ColorUtil.blueBright("                                          ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[5] View Current Story") + ColorUtil.blueBright("                            ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[6]")+ColorUtil.brightYellowBold(" Proceed to Story") + ColorUtil.blueBright("                              ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[7] Quit game") + ColorUtil.blueBright("                                     ║"));
            System.out.println(ColorUtil.blueBright("╚════════════════════════════════════════════════════╝"));

            choice = getIntInput("Enter your choice: ", 1, 7);

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
                    ShopNPC Kyle = new ShopNPC("Kyle", "Shopkeeper", items);
                    Kyle.interact(player, currentChapter);
                    break;
                case 5:
                    viewCurrentStory();  // NEW
                    break;
                case 6:
                    //TODO before next level must fight boss that correlates to the story
                    //add boss before next chapter and must win in order to proceed
                    if(attemptChapterProgression()){
                        if(battleChapterBoss()){
                            proceedToNextChapter();
                        }
                    }
                    break;
                case 7:
                    gameRunning = false;
                    System.out.println("Thanks for playing Akhai!");
                    break;
            }
        } else {
            //Display stats on main menu
            //dynamic title with fixed width of 54
            String title = " " + thyName + " ";
            int totalWidth = 53; //53 since title length is -1
            int titleLength = title.length();

            // Calculate padding needed on each side
            int padding = (totalWidth - titleLength) / 2;
            String leftBorder = "╔" + "═".repeat(padding);
            String rightBorder = "═".repeat(totalWidth - padding - titleLength - 1) + "╗";
            delay(500);
            System.out.println(ColorUtil.blueBright(leftBorder) + ColorUtil.brightCyanBold(title) + ColorUtil.blueBright(rightBorder));
            String playerHealthBar = createHealthBar(player, 40);
            String playerEnergyBar = createEnergyBar(player, 40);

            System.out.println("   " + ColorUtil.green(playerHealthBar) + "\n   " + playerEnergyBar);
            System.out.println(ColorUtil.blueBright("╚════════════════════════════════════════════════════╝"));
            delay(500);
            System.out.println(ColorUtil.blueBright("╔════════════════════════════════════════════════════╗"));
            System.out.println(ColorUtil.blueBright("║") + ColorUtil.brightCyanBold("             What would you like to do?             ") + ColorUtil.blueBright("║"));
            System.out.println(ColorUtil.blueBright("╠════════════════════════════════════════════════════╣"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[1] Explore") + ColorUtil.blueBright("                                       ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[2] Check Stats and Resurrection status") + ColorUtil.blueBright("           ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[3] Open Inventory") + ColorUtil.blueBright("                                ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[4] Shop") + ColorUtil.blueBright("                                          ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[5] View Current Story") + ColorUtil.blueBright("                            ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[6]")+ColorUtil.brightRedBold(" Enter the Final Battle") + ColorUtil.blueBright("                        ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[7] Quit game") + ColorUtil.blueBright("                                     ║"));
            System.out.println(ColorUtil.blueBright("╚════════════════════════════════════════════════════╝"));

            choice = getIntInput("Enter your choice: ", 1, 7);

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
                    ShopNPC Kyle = new ShopNPC("Kyle", "Shopkeeper", items);
                    Kyle.interact(player, currentChapter);
                    break;
                case 5:
                    viewCurrentStory();
                    break;
                case 6:
                    //add final boss
                    //TODO battle finale boss (Demon King Din) must correlate to the story
                    battle(player, new Boss.DemonKingDin(), 3);
                    //TODO story ends after winning the battle
                    proceedToNextChapter();
                    break;
                case 7:
                    gameRunning = false;
                    System.out.println("Thanks for playing Akhai!");
                    break;
            }
        }
    }

    //TODO make sure to display a warning if they try to explore without a weapon equipped -for frank
    boolean EncounterZed = true;
    public void explore() {
        System.out.println(ColorUtil.brightYellowBold("\n\t\tYou explore ") + getLevelName(currentChapter) + ColorUtil.brightYellowBold("..."));
        delay(1000);

        // Different encounter rates based on level
        double encounterRate = 0.75 + (currentChapter * 0.05);
        if (Math.random() <= encounterRate) {
            if(currentChapter < 3) {
                System.out.println(ColorUtil.darkRed("\t\t🚨 You encountered a Common enemy!"));
                battleCommon();
            } else if (currentChapter == 3) {
                if(Math.random() >= 0.75){
                    System.out.println(ColorUtil.darkRed("\t\t🚨 Warning! You encountered an Elite enemy!"));
                    battleElite();
                } else {
                    System.out.println(ColorUtil.darkRed("\t\t🚨 You encountered a Common enemy!"));
                    battleCommon();
                }
            } else if(currentChapter == 4) {
                if(Math.random() >= 0.10) {
                    System.out.println(ColorUtil.darkRed("\t\t🚨 Warning! You encountered an Elite enemy!"));
                    battleElite();
                } else {
                    if(EncounterZed) {
                        System.out.println("\t🚨" + ColorUtil.redBold(" WARNING!!!") + " You encountered a " + ColorUtil.redBold("BOSS") + " enemy!");
                        battle(player, new MiniBoss.EdgeLordZedjy());
                        EncounterZed = false;
                    } else {
                        System.out.println(ColorUtil.darkRed("\t\t🚨 Warning! You encountered an Elite enemy!"));
                        battleElite();
                    }
                }
            } else {
                if(Math.random() >= 0.40) {
                    System.out.println(ColorUtil.darkRed("\t\t🚨 Warning! You encountered an Elite enemy!"));
                    battleElite();
                } else {
                    if(EncounterZed) {
                        System.out.println("\t🚨" + ColorUtil.redBold(" WARNING!!!") + " You encountered a " + ColorUtil.redBold("BOSS") + " enemy!");
                        battle(player, new MiniBoss.EdgeLordZedjy());
                        EncounterZed = false;
                    } else {
                        System.out.println(ColorUtil.darkRed("\t\t🚨 Warning! You encountered an Elite enemy!"));
                        battleElite();
                    }
                }
            }
        } else {
            // Chance for finding items or hidden events
            if (Math.random() < 0.8) {
                findHiddenTreasure();
                delay(800);
            } else {
                System.out.println(ColorUtil.blueGreenBold("\t\tYou found nothing of interest."));
                delay(800);
            }
        }
    }
    private void obtainLoot(Character player, Enemy enemy){
        double chance = Math.random(); //Made a variable para di sigeg gamit ug Math.random ugh

        if(!(enemy instanceof MiniBoss)){
            switch(currentChapter) {
                case 1:
                    Chest CommonChest = new Chest.CommonChest();
                    System.out.println(ColorUtil.blueGreenBold("\t↓ 💎 You found a " + CommonChest.getName() + " and opened it! ↓"));
                    CommonChest.obtain(player);
                    //40% to obtain a weapon :P
                    if(Math.random() <= 0.5) {
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
                    if(Math.random() > 0.1){
                        Chest EliteChest = new Chest.EliteChest();
                        System.out.println(ColorUtil.blueBright("\t↓ 💎 You found a "+EliteChest.getName()+" and opened it! ↓"));
                        EliteChest.obtain(player);
                        //40% chance to obtain weap
                        if(Math.random() <= 0.5) {
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
                    if(Math.random() > 0.2){
                        Chest EpicChest = new Chest.EpicChest();
                        System.out.println(ColorUtil.brightPurpleBold("\t↓ 💎 You found a "+EpicChest.getName()+" and opened it! ↓"));
                        EpicChest.obtain(player);
                        //30% chance to obtain weap
                        if(Math.random() <= 0.5) {
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
                    if(Math.random() > 0.5){
                        Chest LegendaryChest = new Chest.LegendaryChest();
                        System.out.println(ColorUtil.brightYellowBold("\t↓ 💎 You found a "+LegendaryChest.getName()+" and opened it! ↓"));
                        LegendaryChest.obtain(player);
                        //20% chance to obtain weap
                        if(Math.random() <= 0.4) {
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
                    break;
                case 5:
                    if(Math.random() > 0.4){
                        Chest LegendaryChest = new Chest.LegendaryChest();
                        System.out.println(ColorUtil.brightYellowBold("\t↓ 💎 You found a "+LegendaryChest.getName()+" and opened it! ↓"));
                        LegendaryChest.obtain(player);
                        //20% chance to obtain weap
                        if(Math.random() <= 0.5) {
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
        }else{
            Chest MythicalChest = new Chest.MythicalChest();
            System.out.println(ColorUtil.brightRedBold("\t↓ 💎 You found a " + MythicalChest.getName() + " and opened it ↓!"));
            MythicalChest.obtain(player);
            switch (player.getClassType()) {
                case HAWKSEYE:
                    player.obtainItem(findItemId("BW005", items, 1));
                    break;
                case BLADEMASTER:
                    player.obtainItem(findItemId("SW005", items, 1));
                    break;
                case BERSERKER:
                    player.obtainItem(findItemId("BS005", items, 1));
                    break;
                case ASSASSIN:
                    player.obtainItem(findItemId("DR005", items, 1));
                    break;
                case RUNECASTER:
                    player.obtainItem(findItemId("MGS005", items, 1));
                    break;
                case RUNEKNIGHT:
                    player.obtainItem(findItemId("MGSW005", items, 1));
                    break;
            }
        }

    }
    private void findHiddenTreasure() {
        Random rnd = new Random();
        System.out.println(ColorUtil.brightCyanBold("\t\t\t💎 You found a hidden treasure!"));
        int expBonus = currentChapter * rnd.nextInt(10, 16);
        player.gainExperience(expBonus);
    }
    private boolean attemptChapterProgression() {
        if (currentChapter >= MAX_LEVEL) {
            System.out.println("🎉 Congratulations! You've completed all chapters!");

            if (storyManager.isStoryComplete()) {
                System.out.println("\n✨ You have witnessed the complete story of Akhai!");
                System.out.println("Thank you for playing!");
            }
            return true;
        }
        // Check if player is strong enough to progress
        if (player.getLevel() < currentChapter * 4) {
            System.out.println(ColorUtil.red("\n⚠️ You're not strong enough to face the challenges ahead!"));
            System.out.println(ColorUtil.brightCyanBold("Recommended level for next chapter:") + ColorUtil.brightYellowBold((" " + currentChapter * 4)));
            System.out.println(ColorUtil.brightCyanBold("Your current level:") + ColorUtil.brightYellowBold(" " + player.getLevel()));
            return false;
        }else{
            return true;
        }
    }
    private void proceedToNextChapter(){
        System.out.println("\n📖 Beginning Chapter " + (currentChapter + 1) + "...");
        delay(1000);

        // Mark current chapter as completed
        levelsCompleted[currentChapter] = true;

        // Move to next chapter
        currentChapter++;
        storyManager.setCurrentChapter(currentChapter);

        // Play the new chapter's story
        storyManager.playChapter(currentChapter);

        if(currentChapter < MAX_LEVEL){
            System.out.println("\n⚔️ New challenges await in " + getLevelName(currentChapter) + "!");
        }
    }

    private void viewCurrentStory() {
        System.out.println(ColorUtil.brightBlueBold("╔════════════════════")+ColorUtil.brightCyanBold(" STORY MENU ")+ColorUtil.brightBlueBold("════════════════════╗"));
        System.out.println(ColorUtil.brightBlueBold("║") + ColorUtil.brightCyanBold("  [1] View current chapter") + " ".repeat(26) + ColorUtil.brightBlueBold("║"));
        System.out.println(ColorUtil.brightBlueBold("║") + ColorUtil.brightCyanBold("  [2] View all unlocked chapters") + " ".repeat(20) + ColorUtil.brightBlueBold("║"));
        System.out.println(ColorUtil.brightBlueBold("║") + ColorUtil.brightCyanBold("  [3] Back") + " ".repeat(42) + ColorUtil.brightBlueBold("║"));
        System.out.println(ColorUtil.brightBlueBold("╚════════════════════════════════════════════════════╝"));

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
        System.out.println(ColorUtil.brightBlueBold("╔════════════════════════════════════════════════════╗"));
        System.out.println(" ".repeat(18) + ColorUtil.brightCyanBold("📚 ALL CHAPTERS"));
        System.out.println(ColorUtil.blueBright("  ──────────────────────────────────────────────────  "));
        for (int i = 1; i <= currentChapter; i++) {
            Chapter chapter = storyManager.getChapter(i);
            String status = chapter.isCompleted() ? "✅" : "🔒";
            System.out.println("  " + status + ColorUtil.brightCyanBold(" Chapter " + i + ": ") + chapter.getChapterTitle());
        }
        System.out.println(ColorUtil.brightBlueBold("╚════════════════════════════════════════════════════╝"));

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

        System.out.println(ColorUtil.darkRed("\t\t🚨 A wild " + enemy.getName() + " appears!"));
        delay(1000);

        int baseExp = 30;

        int playerCurrentSpeed = player.getSpeed();
        int playerOriginalSpeed = player.getSpeed();
        int enemyCurrentSpeed = enemy.getSpeed();
        int enemyOriginalSpeed = enemy.getSpeed();

        while (enemy.getHealth() > 0 && player.getHealth() > 0 && inBattle) {

            System.out.println(ColorUtil.brightBlueBold("\n═══════════════════════") + ColorUtil.brightYellowBold(" TURN "+(turns++)) + ColorUtil.brightBlueBold(" ═══════════════════════"));
            displayBattleHealth(player, enemy);

            boolean isPlayerTurn = checkSpeed(playerCurrentSpeed, enemyCurrentSpeed);
            System.out.println("\t\t\t\t" + (isPlayerTurn ? ColorUtil.brightGreenBold(player.getName() + "'s turn!") : ColorUtil.brightRedBold(enemy.getName() + "'s turn!")));

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
                player.resurrect();
                // Player gets a free turn after resurrection
                System.out.println(ColorUtil.brightYellowBold("\n⭐ " + player.getName() + " gets a free attack after resurrection!"));
                damage = player.getBasicAttack().execute(player);
                int actualDamage = enemy.takeDamage(damage, enemy.getDefense(), enemy.getPhysicalResistance(), enemy.getMagicResistance());
                System.out.println(ColorUtil.brightGreenBold("\t\tYou dealt " + actualDamage + " damage to " + enemy.getName()));

                //Reset player speed after resurrection free turn
                playerCurrentSpeed = playerOriginalSpeed;
            }
            player.checkStatusEffect();
            enemy.checkStatusEffect();
        }
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

        System.out.println(ColorUtil.darkRed("\t\t🚨 A wild " + enemy.getName() + " appears!"));
        delay(1000);

        int baseExp = 40;

        int playerCurrentSpeed = player.getSpeed();
        int playerOriginalSpeed = player.getSpeed();
        int enemyCurrentSpeed = enemy.getSpeed();
        int enemyOriginalSpeed = enemy.getSpeed();

        while (enemy.getHealth() > 0 && player.getHealth() > 0 && inBattle) {

            System.out.println(ColorUtil.brightBlueBold("\n═══════════════════════") + ColorUtil.brightYellowBold(" TURN "+(turns++)) + ColorUtil.brightBlueBold(" ═══════════════════════"));
            displayBattleHealth(player, enemy);

            boolean isPlayerTurn = checkSpeed(playerCurrentSpeed, enemyCurrentSpeed);
            System.out.println("\t\t\t\t" + (isPlayerTurn ? ColorUtil.brightGreenBold(player.getName() + "'s turn!") : ColorUtil.brightRedBold(enemy.getName() + "'s turn!")));

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
                player.resurrect();
                // Player gets a free turn after resurrection
                System.out.println(ColorUtil.brightYellowBold("\n⭐ " + player.getName() + " gets a free attack after resurrection!"));
                damage = player.getBasicAttack().execute(player);
                int actualDamage = enemy.takeDamage(damage, enemy.getDefense(), enemy.getPhysicalResistance(), enemy.getMagicResistance());
                System.out.println(ColorUtil.brightGreenBold("\t\tYou dealt " + actualDamage + " damage to " + enemy.getName()));

                //Reset player speed after resurrection free turn
                playerCurrentSpeed = playerOriginalSpeed;
            }
            player.checkStatusEffect();
            enemy.checkStatusEffect();
        }
        playerHealthCheck(enemy, baseExp, player);

        // Clear battle effects after combat
        clearBattleEffects(enemy);

        inBattle = false;
    }
    public boolean battleChapterBoss(){
        inBattle = true;
        int turns = 1;
        Enemy enemy = selectChapterBoss();

        System.out.println(ColorUtil.brightRedBold("\t\t\t\t\t‼️ WARNING ️‼️️"));
        System.out.println(ColorUtil.brightRedBold("\t\t\t🚨 BOSS " + enemy.getName() + " appears!"));
        delay(1000);

        int baseExp = 40;

        int playerCurrentSpeed = player.getSpeed();
        int playerOriginalSpeed = player.getSpeed();
        int enemyCurrentSpeed = enemy.getSpeed();
        int enemyOriginalSpeed = enemy.getSpeed();

        while (enemy.getHealth() > 0 && player.getHealth() > 0 && inBattle) {

            System.out.println(ColorUtil.brightBlueBold("\n═══════════════════════") + ColorUtil.brightYellowBold(" TURN "+(turns++)) + ColorUtil.brightBlueBold(" ═══════════════════════"));
            displayBattleHealth(player, enemy);

            boolean isPlayerTurn = checkSpeed(playerCurrentSpeed, enemyCurrentSpeed);
            System.out.println("\t\t\t\t" + (isPlayerTurn ? ColorUtil.brightGreenBold(player.getName() + "'s turn!") : ColorUtil.brightRedBold(enemy.getName() + "'s turn!")));

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
                player.resurrect();
                // Player gets a free turn after resurrection
                System.out.println(ColorUtil.brightYellowBold("\n⭐ " + player.getName() + " gets a free attack after resurrection!"));
                damage = player.getBasicAttack().execute(player);
                int actualDamage = enemy.takeDamage(damage, enemy.getDefense(), enemy.getPhysicalResistance(), enemy.getMagicResistance());
                System.out.println(ColorUtil.brightGreenBold("\t\tYou dealt " + actualDamage + " damage to " + enemy.getName()));

                //Reset player speed after resurrection free turn
                playerCurrentSpeed = playerOriginalSpeed;
            }
            player.checkStatusEffect();
            enemy.checkStatusEffect();
        }
        boolean hasWon = playerHealthCheck(enemy, baseExp, player);

        // Clear battle effects after combat
        clearBattleEffects(enemy);

        inBattle = false;
        return hasWon;
    }

    public void battle(Character player, EliteEnemy enemy){
        inBattle = true;
        int turns = 1;

        System.out.println(ColorUtil.brightRedBold("\t\t🚨 Careful! " + enemy.getName() + " appears!"));
        delay(1000);

        int baseExp = 30;

        int playerCurrentSpeed = player.getSpeed();
        int playerOriginalSpeed = player.getSpeed();
        int enemyCurrentSpeed = enemy.getSpeed();
        int enemyOriginalSpeed = enemy.getSpeed();

        while (enemy.getHealth() > 0 && player.getHealth() > 0 && inBattle) {
            System.out.println(ColorUtil.brightBlueBold("\n═══════════════════════") + ColorUtil.brightYellowBold(" TURN "+(turns++)) + ColorUtil.brightBlueBold(" ═══════════════════════"));
            displayBattleHealth(player, enemy);

            boolean isPlayerTurn = checkSpeed(playerCurrentSpeed, enemyCurrentSpeed);
            System.out.println("\t\t\t\t" + (isPlayerTurn ? ColorUtil.brightGreenBold(player.getName() + "'s turn!") : ColorUtil.brightRedBold(enemy.getName() + "'s turn!")));

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
                player.resurrect();
                // Player gets a free turn after resurrection
                System.out.println(ColorUtil.brightYellowBold("\n⭐ " + player.getName() + " gets a free attack after resurrection!"));
                damage = player.getBasicAttack().execute(player);
                int actualDamage = enemy.takeDamage(damage, enemy.getDefense(), enemy.getPhysicalResistance(), enemy.getMagicResistance());
                System.out.println(ColorUtil.brightGreenBold("\t\tYou dealt " + actualDamage + " damage to " + enemy.getName()));

                //Reset player speed after resurrection free turn
                playerCurrentSpeed = playerOriginalSpeed;
            }
            player.checkStatusEffect();
            enemy.checkStatusEffect();
        }
        playerHealthCheck(enemy, baseExp, player);

        // Clear battle effects after combat
        clearBattleEffects(enemy);

        inBattle = false;
    }
    public void battle(Character player, Boss enemy){
        inBattle = true;
        int turns = 1;

        System.out.println(ColorUtil.brightRedBold("\t\t🚨 BOSS " + enemy.getName() + " appears!"));
        delay(1000);

        int baseExp = 50;

        int playerCurrentSpeed = player.getSpeed();
        int playerOriginalSpeed = player.getSpeed();
        int enemyCurrentSpeed = enemy.getSpeed();
        int enemyOriginalSpeed = enemy.getSpeed();

        while (enemy.getHealth() > 0 && player.getHealth() > 0 && inBattle) {

            System.out.println(ColorUtil.brightBlueBold("\n═══════════════════════") + ColorUtil.brightYellowBold(" TURN "+(turns++)) + ColorUtil.brightBlueBold(" ═══════════════════════"));
            displayBattleHealth(player, enemy);

            boolean isPlayerTurn = checkSpeed(playerCurrentSpeed, enemyCurrentSpeed);
            System.out.println("\t\t\t\t" + (isPlayerTurn ? ColorUtil.brightGreenBold(player.getName() + "'s turn!") : ColorUtil.brightRedBold(enemy.getName() + "'s turn!")));

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
                player.resurrect();
                // Player gets a free turn after resurrection
                System.out.println(ColorUtil.brightYellowBold("\n⭐ " + player.getName() + " gets a free attack after resurrection!"));
                damage = player.getBasicAttack().execute(player);
                int actualDamage = enemy.takeDamage(damage, enemy.getDefense(), enemy.getPhysicalResistance(), enemy.getMagicResistance());
                System.out.println(ColorUtil.brightGreenBold("\t\tYou dealt " + actualDamage + " damage to " + enemy.getName()));

                //Reset player speed after resurrection free turn
                playerCurrentSpeed = playerOriginalSpeed;
            }
            player.checkStatusEffect();
            enemy.checkStatusEffect();
        }
        playerHealthCheck(enemy, baseExp, player);

        clearBattleEffects(enemy);

        inBattle = false;
    }
    //Boss Din
    public void battle(Character player, Boss enemy, int maxWave/*min: 2 | max: 3*/){
        int baseExp = 50;

        for(int wave = 1; wave <= maxWave; wave++){
            if(wave == maxWave){
                battle(player, enemy);
            }else if(wave == 1 && maxWave == 3){
                battle(player, randomizeEliteEnemy());
            }else if(wave == 1 && maxWave == 2){
                battle(player, randomizeEliteEnemy());
            }else{
                battle(player, new Boss.Kamish());
            }
        }
        playerHealthCheck(enemy, baseExp, player);
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

    private String createHealthBar(Entity entity, int length) {
        int filled = (entity.getHealth() * length) / entity.getMaxHealth();
        int empty = length - filled;

        String bar = "█".repeat(filled) + "░".repeat(empty);
        return String.format("%s %d/%d", bar, entity.getHealth(), entity.getMaxHealth());
    }
    private String createEnergyBar(Character player, int length) {
        int filled = (player.getEnergy() * length) / player.getMaxEnergy();
        int empty = length - filled;

        String bar = "█".repeat(filled) + "░".repeat(empty);

        if(player instanceof Hawkseye){
            return String.format(ColorUtil.greenBold("%s %d/%d\n\t\t\t\t\tInsight"), bar, player.getEnergy(), player.getMaxEnergy());
        }else if(player instanceof  Blademaster){
            return String.format(ColorUtil.blueBright("%s %d/%d\n\t\t\t\t\tEnergy"), bar, player.getEnergy(), player.getMaxEnergy());
        }else if(player instanceof Berserker){
            return String.format(ColorUtil.orange("%s %d/%d\n\t\t\t\t\tFury"), bar, player.getEnergy(), player.getMaxEnergy());
        }else if(player instanceof  Runecaster){
            return String.format(ColorUtil.purpleBright("%s %d/%d\n\t\t\t\t\tMana"), bar, player.getEnergy(), player.getMaxEnergy());
        }else if(player instanceof  RuneKnight){
            return String.format(ColorUtil.yellowBright("%s %d/%d\n\t\t\t\t\tBlessing"), bar, player.getEnergy(), player.getMaxEnergy());
        }else if(player instanceof Shinobi){
            return String.format(ColorUtil.purple("%s %d/%d\n\t\t\t\t\tChakra"), bar, player.getEnergy(), player.getMaxEnergy());
        }else{
            return String.format(ColorUtil.blue("%s %d/%d\n\t\t\t\t\tAura"), bar, player.getEnergy(), player.getMaxEnergy());
        }
    }
    private String createExperienceBar(Character player, int length) {
        int currentExp = player.getExperience();
        int maxExp = player.getExperienceNeeded();

        // Ensure we don't divide by zero and clamp values
        if (maxExp <= 0) maxExp = 1;
        if (currentExp < 0) currentExp = 0;
        if (currentExp > maxExp) currentExp = maxExp;

        int filled = (int) ((double) currentExp / maxExp * length);
        filled = Math.max(0, Math.min(filled, length)); // Clamp between 0 and length
        int empty = length - filled;

        String bar = "█".repeat(filled) + "░".repeat(Math.max(0, empty));
        return String.format("%s %d/%d", bar, currentExp, maxExp);
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
                        System.out.println(ColorUtil.brightGreenBold("\t\tYou dealt " + actualDamage + " damage to " + enemy.getName()));
                        delay(1000);
                    } else {
                        System.out.println(ColorUtil.orange("\t\t\t"+enemy.getName()+" avoided the attack!"));
                        damage = 0;
                        delay(1000);
                    }
                }
            } else {
                // Check enemy accuracy
                if (AccuracySystem.enemyHits()) {
                    int actualEnemyDamage = player.takeDamage(damage, player.getDefense(), player.getPhysicalResistance(), player.getMagicResistance());
                    System.out.println(ColorUtil.redBright("\t\t" + enemy.getName() + " dealt " + actualEnemyDamage + " damage to you!"));
                    delay(1000);
                } else {
                    System.out.println(ColorUtil.orange("\t\t\t" + player.getName() + " avoided the attack!"));
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
            System.out.println(ColorUtil.blueBright("╔════════════════════════════════════════════════════╗"));
            System.out.println(ColorUtil.blueBright("║") + ColorUtil.brightCyanBold("                   SELECT ACTION                    ") + ColorUtil.blueBright("║"));
            System.out.println(ColorUtil.blueBright("╠════════════════════════════════════════════════════╣"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[1] Fight") + ColorUtil.blueBright("                                         ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[2] Open Inventory") + ColorUtil.blueBright("                                ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[3] Run") + ColorUtil.blueBright("                                           ║"));
            System.out.println(ColorUtil.blueBright("╚════════════════════════════════════════════════════╝"));

            choice = getIntInput("Choose action: ", 1, 3);

            switch(choice) {
                case 1:
                    damage = handleFightAction(enemy);
                    hasActed = true;
                    break;
                case 2:
                    hasActed = openInventory(player, enemy) == 1;
                    break;
                case 3:
                    if(!(enemy instanceof Boss)){
                        if (Math.random() > 0.9) {
                            System.out.println("You successfully fled!");
                            inBattle = false;
                            return -1;
                        } else {
                            System.out.println("You failed to flee!");
                        }
                        hasActed = true;
                    }else{
                        System.out.println(ColorUtil.brightRedBold("\t\t\t⚠️ Cannot run from a boss!"));
                    }

                    break;
            }
        }
        return damage;
    }
    private int handleFightAction(Entity enemy) {
        System.out.println(ColorUtil.blueBright("╔════════════════════════════════════════════════════╗"));
        System.out.println(ColorUtil.blueBright("║") + ColorUtil.brightCyanBold("                        FIGHT                       ") + ColorUtil.blueBright("║"));
        System.out.println(ColorUtil.blueBright("╠════════════════════════════════════════════════════╣"));
        System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[1] Use Basic Attack") + ColorUtil.blueBright("                              ║"));
        System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("    (Generates 25 energy + 1 ultimate charge)  ") + ColorUtil.blueBright("   ║"));
        System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[2] Use Skill") + ColorUtil.blueBright("                                     ║"));
        System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("    (Costs 40 energy + 2 ultimate charges)    ") + ColorUtil.blueBright("    ║"));

        if (player.isUltimateReady()) {
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[3] Use Ultimate") + ColorUtil.blueBright("                                  ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("    (Costs 80 energy, consumes all charges) ") + ColorUtil.blueBright("      ║"));
        } else {
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[3] Use Ultimate (Locked)") + ColorUtil.blueBright("                         ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("    (" + player.getUltimateCounter() + "/" + player.getMaxUltimateCounter() + " charges)                ") + ColorUtil.blueBright("                 ║"));
        }

        System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[4] Back") + ColorUtil.blueBright("                                          ║"));
        System.out.println(ColorUtil.blueBright("╚════════════════════════════════════════════════════╝"));

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

    private boolean playerHealthCheck(Enemy enemy, int baseExp, Character player){
        boolean hasWon = false;
        if (player.getHealth() <= 0) {
            handlePlayerDefeat();
        } else {
            handleVictory(enemy, baseExp, player);
            hasWon = true;
        }
        resetHasConsumed(items);
        return hasWon;
    }
    private void handlePlayerDefeat() {
        delay(1500);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(ColorUtil.brightRedBold("\t\t💀 You have been defeated..."));
        delay(2500);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        String[] bannerLines = {
                "▓█████▄ ▓█████   █████▒▓█████ ▄▄▄     ▄▄▄█████▓",
                "▒██▀ ██▌▓█   ▀ ▓██   ▒ ▓█   ▀▒████▄   ▓  ██▒ ▓▒",
                "░██   █▌▒███   ▒████ ░ ▒███  ▒██  ▀█▄ ▒ ▓██░ ▒░",
                "░▓█▄   ▌▒▓█  ▄ ░▓█▒  ░ ▒▓█  ▄░██▄▄▄▄██░ ▓██▓ ░ ",
                "░▒████▓ ░▒████▒░▒█░    ░▒████▒▓█   ▓██▒ ▒██▒ ░ ",
                " ▒▒▓  ▒ ░░ ▒░ ░ ▒ ░    ░░ ▒░ ░▒▒   ▓▒█░ ▒ ░░   ",
                " ░ ▒  ▒  ░ ░  ░ ░       ░ ░  ░ ▒   ▒▒ ░   ░    ",
                " ░ ░  ░    ░    ░ ░       ░    ░   ▒    ░      ",
                "   ░       ░  ░           ░  ░     ░  ░        ",
                " ░                                             "
        };

        for (String line : bannerLines) {
            String coloredLine = "";
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if(c == '█'){
                    coloredLine += ColorUtil.redBright("█");
                } else if(c == '▄'){
                    coloredLine += ColorUtil.redBright("▄");
                } else if(c == '▀'){
                    coloredLine += ColorUtil.redBright("▀");
                } else if(c == '▌'){
                    coloredLine += ColorUtil.redBright("▌");
                } else if (c == '▓') {
                    coloredLine += ColorUtil.red("▓");
                } else if (c == '▒') {
                    coloredLine += ColorUtil.darkRed("▒");
                } else if (c == '░') {
                    coloredLine += ColorUtil.darkRed("░");
                } else {
                    coloredLine += ConsoleColors.RESET + c;
                }
            }
            coloredLine += ConsoleColors.RESET;
            System.out.println(coloredLine);
        }
        delay(2500);
        System.out.println(ColorUtil.brightRedBold("\t\t\t\tGame Over"));
        delay(2500);
        gameRunning = false;
    }
    private void handleVictory(Enemy enemy, int baseExp, Character player) {
        if(enemy.getHealth() <= 0){
            System.out.println(ColorUtil.brightYellowBold("\t\t🎉 You defeated " + enemy.getName() + "!"));
            delay(500);

            String[] bannerLines2 = {
                    "░██    ░██ ░██████  ░██████  ░██████████  ░██████   ░█████████  ░██     ░██ ░██ ",
                    "░██    ░██   ░██   ░██   ░██     ░██     ░██   ░██  ░██     ░██  ░██   ░██  ░██ ",
                    "░██    ░██   ░██  ░██            ░██    ░██     ░██ ░██     ░██   ░██ ░██   ░██ ",
                    "░██    ░██   ░██  ░██            ░██    ░██     ░██ ░█████████     ░████    ░██ ",
                    " ░██  ░██    ░██  ░██            ░██    ░██     ░██ ░██   ░██       ░██     ░██ ",
                    "  ░██░██     ░██   ░██   ░██     ░██     ░██   ░██  ░██    ░██      ░██         ",
                    "   ░███    ░██████  ░██████      ░██      ░██████   ░██     ░██     ░██     ░██ ",
            };

            for (String line : bannerLines2) {
                String coloredLine = "";
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (c == '░') {
                        coloredLine += ColorUtil.yellow("░");
                    } else if (c == '█') {
                        coloredLine += ColorUtil.brightYellowBold("█");
                    } else {
                        coloredLine += ConsoleColors.RESET + c;
                    }
                }
                coloredLine += ConsoleColors.RESET;
                System.out.println(coloredLine);
            }
            delay(1200);

            player.gainExperience(baseExp);
            delay(500);

            obtainGold(player);
            delay(500);

            obtainLoot(player, enemy);
            delay(500);

            //Heal 25% Lost Hp after winning a battle
            player.afterBattleHeal();
            delay(500);
        }
    }
    // Add a method to check resurrection status
//    public void checkResurrectionStatus() {
//        System.out.println("\n🔮 Resurrection Status:");
//        if (player.hasResurrected()) {
//            System.out.println("❌ Resurrection has been used and cannot be used again.");
//        } else {
//            System.out.println("✅ Resurrection is available! You will be given the option to resurrect once when defeated.");
//            System.out.println("This powerful ability can only be used once per playthrough.");
//        }
//    }

    //Character
    public void addStarterPack(Character player, List<Item> items){
        System.out.println(ColorUtil.brightYellowBold("Adding starter pack..."));
        delay(500);
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
        int uses = 0;
        boolean isEmpty = false;
        while(confirm == 0){
            player.displayInventory();
            System.out.println(ColorUtil.brightCyanBold("[0] Back"));
            int choice = getIntInput("Select an item: ", 0, 10);
            if(choice == 0){
                break;
            }
            Item item = player.getInventory().getItems()[--choice];
            if(item == null){
                isEmpty = true;
            }

            if(!isEmpty){
                System.out.println(ColorUtil.blueBright("═══════════════════")+ColorUtil.brightCyanBold(" SELECTING ITEM ")+ColorUtil.blueBright("═══════════════════"));
                item.displayInfo();
                System.out.println("Select "+item.getName()+"?: ");
                System.out.println(ColorUtil.brightCyanBold("[1] Select Item"));
                System.out.println(ColorUtil.brightCyanBold("[2] Back"));

                confirm = getIntInput("Choose action: ", 1, 2);
                if(confirm == 1){
                    if(inBattle && item.getItemType() == ItemType.WEAPON){
                        System.out.println("You can't equip a weapon during battle!");
                        confirm = 0;
                        delay(1000);
                    }else{
                        System.out.println(player.getName()+" used "+item.getName()+"!");
                        delay(1000);
                        item.use(player);
                        if(inBattle){
                            if(uses < 2){
                                System.out.println(ColorUtil.brightYellowBold("\t\t\tYou can take another action!"));
                                delay(1000);
                                if(item instanceof HealingPotion){
                                    uses++;
                                }
                            }else{
                                System.out.println(ColorUtil.brightYellowBold("\t\t\tEnding Turn!..."));
                                delay(1000);
                            }
                        }
                        confirm = 0;
                    }
                }else{
                    confirm = 0;
                }
            }else{
                System.out.println("Please select another item.");
                isEmpty = false;
                delay(1000);
            }

        }
        return confirm;
    }
    private int openInventory(Character player, Entity enemy){
        int confirm = 0;
        int uses = 0;
        boolean isEmpty = false;
        while(confirm == 0){
            player.displayInventory();
            System.out.println(ColorUtil.brightCyanBold("[0] Back"));
            int choice = getIntInput("Select an item: ", 0, 10);
            if(choice == 0){
                break;
            }
            Item item = player.getInventory().getItems()[--choice];
            if(item == null){isEmpty = true;}

            if(!isEmpty){
                System.out.println(ColorUtil.blueBright("═══════════════════")+ColorUtil.brightCyanBold(" SELECTING ITEM ")+ColorUtil.blueBright("═══════════════════"));
                System.out.println("Select "+item.getName()+"?: ");
                System.out.println(ColorUtil.brightCyanBold("[1] Select Item"));
                System.out.println(ColorUtil.brightCyanBold("[2] Back"));

                confirm = getIntInput("Choose action: ", 1, 2);
                if(confirm == 1){
                    if(inBattle && item.getItemType() == ItemType.WEAPON){
                        System.out.println("You can't equip a weapon during battle!");
                        confirm = 0;
                        delay(1000);
                    }else{
                        System.out.println(player.getName()+" used "+item.getName()+"!");
                        delay(1000);
                        item.use(player);
                        if(inBattle){
                            if(uses < 2){
                                System.out.println(ColorUtil.brightYellowBold("\t\t\tYou can take another action!"));
                                delay(1000);
                                displayBattleHealth(player, enemy);
                                confirm = 0;
                                if(item instanceof HealingPotion){
                                    uses++;
                                }
                            }else{
                                System.out.println(ColorUtil.brightYellowBold("\t\t\tEnding Turn!..."));
                                delay(1000);
                            }
                        }
                    }
                }else{
                    confirm = 0;
                }
            }else{
                System.out.println("Please select another item.");
                isEmpty = false;
                delay(1000);
            }
        }
        return confirm;
    }
    public void obtainGold(Character player){
        Random rnd = new Random();
        int goldYield = currentChapter * rnd.nextInt(15, 21);
        player.setCurrency(player.getCurrency() + goldYield);
        System.out.println(ColorUtil.brightYellowBold("\t\t\t\uD83E\uDE99 Gained "+goldYield+" gold coins!"));
    }
    public void displayBattleHealth(Character player, Entity enemy){
        String playerHealthBar = createHealthBar(player, 45);
        String playerEnergyBar = createEnergyBar(player, 45);
        String enemyHealthBar =  createHealthBar(enemy, 45);

        System.out.println("\t\t\t\t"+enemy.getName());
        System.out.println(ColorUtil.red(enemyHealthBar));
        System.out.println("\n\t\t\t\t"+player.getName());
        System.out.println(ColorUtil.green(playerHealthBar));
        System.out.println(playerEnergyBar);
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
                return new EliteEnemy.FrankDaniel();

        }
        return null;
    }
    public Boss selectChapterBoss(){
        switch(currentChapter){
            case 1:
                return new Boss.Frankenstein();
            case 2:
                return new Boss.MiningCable();
            case 3:
                return new Boss.Kamish();
            case 4:
                return new Boss.Abaddon();
        }
        return new Boss.DemonKingDin();
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
                    if(consumable.getHasConsumed()){
                        consumable.setHasConsumed(false);
                    }
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
    public String getStringInput(String prompt){
        while(true){
            System.out.print(prompt);
            try{
                String userInput = scan.nextLine().trim();
                if(userInput.isEmpty()){
                    System.out.println("Please enter your name");
                    continue;
                }
                return userInput;
            }catch(Exception e){
                System.out.println("Invalid input. Please enter a valid name");
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