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
    boolean EncounterZed = true;
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

        String[] bannerLines = {
                "░██       ░██ ░██████████ ░██           ░██████    ░██████   ░███     ░███ ░██████████",
                "░██       ░██ ░██         ░██          ░██   ░██  ░██   ░██  ░████   ░████ ░██         ",
                "░██  ░██  ░██ ░██         ░██         ░██        ░██     ░██ ░██░██ ░██░██ ░██         ",
                "░██ ░████ ░██ ░█████████  ░██         ░██        ░██     ░██ ░██ ░████ ░██ ░█████████  ",
                "░██░██ ░██░██ ░██         ░██         ░██        ░██     ░██ ░██  ░██  ░██ ░██         ",
                "░████   ░████ ░██         ░██          ░██   ░██  ░██   ░██  ░██       ░██ ░██         ",
                "░███     ░███ ░██████████ ░██████████   ░██████    ░██████   ░██       ░██ ░██████████ ",
        };

        for (String line : bannerLines) {
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
//        for(int i = 0; i <= 50; i++){
//            System.out.println();
//        }

        String[] bannerLines1 = {
                "",
                "                               ░██████████  ░██████   ",
                "                                   ░██     ░██   ░██  ",
                "                                   ░██    ░██     ░██ ",
                "                                   ░██    ░██     ░██ ",
                "                                   ░██    ░██     ░██ ",
                "                                   ░██     ░██   ░██  ",
                "                                   ░██      ░██████",
        };

        for (String line : bannerLines1) {
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

        String[] bannerLines2 = {
                "",
                "                 ░███    ░██     ░██ ░██     ░██    ░███    ░██████ ░██ ",
                "                ░██░██   ░██    ░██  ░██     ░██   ░██░██     ░██   ░██ ",
                "               ░██  ░██  ░██   ░██   ░██     ░██  ░██  ░██    ░██   ░██ ",
                "              ░█████████ ░███████    ░██████████ ░█████████   ░██   ░██ ",
                "              ░██    ░██ ░██   ░██   ░██     ░██ ░██    ░██   ░██   ░██ ",
                "              ░██    ░██ ░██    ░██  ░██     ░██ ░██    ░██   ░██      ",
                "              ░██    ░██ ░██     ░██ ░██     ░██ ░██    ░██ ░██████ ░██ ",
                "",
        };

        for (String line : bannerLines2) {
            String coloredLine = "";
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == '░') {
                    coloredLine += ColorUtil.brightCyanBold("░");
                } else if (c == '█') {
                    coloredLine += ColorUtil.brightCyanBold("█");
                } else {
                    coloredLine += ConsoleColors.RESET + c;
                }
            }
            coloredLine += ConsoleColors.RESET;
            System.out.println(coloredLine);
        }
        delay(1000);
        for(int i = 0; i < 29; i++){
            System.out.print(" ");
        }
        System.out.println(ColorUtil.brightGreenBold("[Press ENTER to Start Game]"));
        scan.nextLine();

        while(true){
            thyName = getNameInput(ColorUtil.brightBlueGreenBold(" ".repeat(33)+"What is thy name?: "));
            int choice = getIntInput(ColorUtil.brightCyanBold(" ".repeat(23)+"Is "+thyName+" your name? (Yes [1]| No [0]): "), 0, 1);
            if(choice == 1){
                break;
            }
        }

        int classChosen = 0;
        while(classChosen == 0){
            System.out.println(ColorUtil.brightBlueBold("╔═══════════════════════════════════════════════════════════════════════════════════╗"));
            System.out.println(ColorUtil.brightBlueBold("║")+ColorUtil.brightYellowBold("                                 Choose your class                                 ")+ColorUtil.brightBlueBold("║"));
            System.out.println(ColorUtil.brightBlueBold("╠═══════════════════════════╦═══════════════════════════╦═══════════════════════════╣"));
            System.out.println(ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("     [1]")+ColorUtil.greenBold(" Hawkseye          ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("    [2]")+ColorUtil.blueBright(" Blademaster        ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("    [3]")+ColorUtil.purpleBright(" Rune Caster        ")+ColorUtil.brightBlueBold("║"));
            System.out.println(ColorUtil.brightBlueBold("╠═══════════════════════════╬═══════════════════════════╬═══════════════════════════╣"));
            System.out.println(ColorUtil.brightBlueBold("║")+ColorUtil.brightYellowBold("  Marksman                 ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightYellowBold("  Fighter                  ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightYellowBold("  Mage                     ")+ColorUtil.brightBlueBold("║"));
            System.out.println(ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  HP: 245                  ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  HP: 250                  ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  HP: 205                  ")+ColorUtil.brightBlueBold("║"));
            System.out.println(ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Attack: 31               ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Attack: 24               ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Attack: 37               ")+ColorUtil.brightBlueBold("║"));
            System.out.println(ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Speed: 20                ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Speed: 22                ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Speed: 17                ")+ColorUtil.brightBlueBold("║"));
            System.out.println(ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Def: 14                  ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Def: 20                  ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Def: 13                  ")+ColorUtil.brightBlueBold("║"));
            System.out.println(ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Damage Res: 10%          ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Damage Res: 15%          ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Damage Res: 9%           ")+ColorUtil.brightBlueBold("║"));
            System.out.println(ColorUtil.brightBlueBold("╠═══════════════════════════╬═══════════════════════════╬═══════════════════════════╣"));
            System.out.println(ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("     [4]")+ColorUtil.orange(" Berserker         ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("      [5]")+ColorUtil.purple(" Shinobi          ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("    [6]")+ColorUtil.yellowBright(" Rune Knight        ")+ColorUtil.brightBlueBold("║"));
            System.out.println(ColorUtil.brightBlueBold("╠═══════════════════════════╬═══════════════════════════╬═══════════════════════════╣"));
            System.out.println(ColorUtil.brightBlueBold("║")+ColorUtil.brightYellowBold("  Tank                     ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightYellowBold("  Assassin                 ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightYellowBold("  Fighter                  ")+ColorUtil.brightBlueBold("║"));
            System.out.println(ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  HP: 350                  ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  HP: 245                  ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  HP: 290                  ")+ColorUtil.brightBlueBold("║"));
            System.out.println(ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Attack: 20               ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Attack: 22               ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Attack: 21               ")+ColorUtil.brightBlueBold("║"));
            System.out.println(ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Speed: 18                ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Speed: 37                ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Speed: 24                ")+ColorUtil.brightBlueBold("║"));
            System.out.println(ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Def: 15                  ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Def: 12                  ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Def: 20                  ")+ColorUtil.brightBlueBold("║"));
            System.out.println(ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Damage Res: 8%           ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Damage Res: 10%          ")+ColorUtil.brightBlueBold("║")+ColorUtil.brightCyanBold("  Damage Res: 12%          ")+ColorUtil.brightBlueBold("║"));
            System.out.println(ColorUtil.brightBlueBold("╚═══════════════════════════╩═══════════════════════════╩═══════════════════════════╝"));

        int choice = getIntInput(" ".repeat(15)+"Enter your choice (1-6): ", 1, 7);

            switch (choice) {
                case 1:
                    System.out.println(ColorUtil.brightBlueBold(" ".repeat(15)+"╔═════════════════════")+ColorUtil.brightCyanBold(" Hawkseye ")+ColorUtil.brightBlueBold("═════════════════════╗"));
                    System.out.println(ColorUtil.greenBold(" ".repeat(15)+"   Deals the highest physical damage from range, a\n"+" ".repeat(15)+"   precision marksman who never misses their mark."));
                    System.out.println(ColorUtil.brightBlueBold(" ".repeat(15)+"╚════════════════════════════════════════════════════╝"));
                    classChosen = getIntInput(" ".repeat(15)+"Select class? (Yes [1] | No [0]): ", 0, 1);
                    if(classChosen == 1){
                        player = new Hawkseye("Hawkseye " + thyName);
                        System.out.println(ColorUtil.brightCyanBold("\n"+" ".repeat(15)+"You have chosen the Hawkseye class!"));
                    }
                    break;
                case 2:
                    System.out.println(ColorUtil.brightBlueBold(" ".repeat(15)+"╔════════════════════")+ColorUtil.brightCyanBold(" Blademaster ")+ColorUtil.brightBlueBold("═══════════════════╗"));
                    System.out.println(ColorUtil.blueBright(" ".repeat(15)+"   A versatile all-rounder fighter, perfectly\n"+" ".repeat(15)+"   balanced stats for any combat situation."));
                    System.out.println(ColorUtil.brightBlueBold(" ".repeat(15)+"╚════════════════════════════════════════════════════╝"));
                    classChosen = getIntInput(" ".repeat(15)+"Select class? (Yes [1] | No [0]): ", 0, 1);
                    if(classChosen == 1){
                        player = new Blademaster(thyName + " the Blademaster");
                        System.out.println(ColorUtil.brightCyanBold("\n"+" ".repeat(15)+"You have chosen the Blademaster class!"));
                    }
                    break;
                case 3:
                    System.out.println(ColorUtil.brightBlueBold(" ".repeat(15)+"╔════════════════════")+ColorUtil.brightCyanBold(" Rune Caster ")+ColorUtil.brightBlueBold("═══════════════════╗"));
                    System.out.println(ColorUtil.purpleBright(" ".repeat(15)+"   Deals the highest damage output with devastating\n"+" ".repeat(15)+"   spells, sacrifices speed for overwhelming power."));
                    System.out.println(ColorUtil.brightBlueBold(" ".repeat(15)+"╚════════════════════════════════════════════════════╝"));
                    classChosen = getIntInput(" ".repeat(15)+"Select class? (Yes [1] | No [0]): ", 0, 1);
                    if(classChosen == 1){
                        player = new Runecaster("Rune Caster " + thyName);
                        System.out.println(ColorUtil.brightCyanBold("\n"+" ".repeat(15)+"You have chosen the Rune Caster class!"));
                    }
                    break;
                case 4:
                    System.out.println(ColorUtil.brightBlueBold(" ".repeat(15)+"╔════════════════════")+ColorUtil.brightCyanBold(" Berserker ")+ColorUtil.brightBlueBold("═════════════════════╗"));
                    System.out.println(ColorUtil.orange(" ".repeat(15)+"   Tanks through battles with the highest HP pool,\n"+" ".repeat(15)+"   a relentless force that outlasts all opponents."));
                    System.out.println(ColorUtil.brightBlueBold(" ".repeat(15)+"╚════════════════════════════════════════════════════╝"));
                    classChosen = getIntInput(" ".repeat(15)+"Select class? (Yes [1] | No [0]): ", 0, 1);
                    if(classChosen == 1){
                        player = new Berserker("Berserker " + thyName);
                        System.out.println(ColorUtil.brightCyanBold("\n"+" ".repeat(15)+"You have chosen the Berserker class!"));
                    }
                    break;
                case 5:
                    System.out.println(ColorUtil.brightBlueBold(" ".repeat(15)+"╔═════════════════════")+ColorUtil.brightCyanBold(" Shinobi ")+ColorUtil.brightBlueBold("══════════════════════╗"));
                    System.out.println(ColorUtil.purple(" ".repeat(15)+"   Overwhelms enemies with blinding speed and can\n"+" ".repeat(15)+"   dish out damage before any enemy can react."));
                    System.out.println(ColorUtil.brightBlueBold(" ".repeat(15)+"╚════════════════════════════════════════════════════╝"));
                    classChosen = getIntInput(" ".repeat(15)+"Select class? (Yes [1] | No [0]): ", 0, 1);
                    if(classChosen == 1){
                        player = new Shinobi("Shinobi " + thyName);
                        System.out.println(ColorUtil.brightCyanBold("\n"+" ".repeat(15)+"You have chosen the Shinobi class!"));
                    }
                    break;
                case 6:
                    System.out.println(ColorUtil.brightBlueBold(" ".repeat(15)+"╔════════════════════")+ColorUtil.brightCyanBold(" Rune Knight ")+ColorUtil.brightBlueBold("═══════════════════╗"));
                    System.out.println(ColorUtil.yellowBright(" ".repeat(15)+"   A magical warrior that blends sword and sorcery\n"+" ".repeat(15)+"   with both offensive & defensive capabilities."));
                    System.out.println(ColorUtil.brightBlueBold(" ".repeat(15)+"╚════════════════════════════════════════════════════╝"));
                    classChosen = getIntInput(" ".repeat(15)+"Select class? (Yes [1] | No [0]): ", 0, 1);
                    if(classChosen == 1){
                        player = new RuneKnight(thyName + " the Rune Knight");
                        System.out.println(ColorUtil.brightCyanBold("\n"+" ".repeat(15)+"You have chosen the Rune Knight class!"));
                    }
                    break;
                case 7:
                    System.out.println(ColorUtil.brightBlueBold(" ".repeat(15)+"╔══════════════════")+ ColorUtil.brightCyanBold(" The Chosen One ") +ColorUtil.brightBlueBold("══════════════════╗"));
                    System.out.println(ColorUtil.brightRedBold(" ".repeat(15)+"   Throughout Heaven and Earth, I Alone Am\n"+" ".repeat(15)+"   The Honored One."));
                    System.out.println(ColorUtil.brightBlueBold(" ".repeat(15)+"╚════════════════════════════════════════════════════╝"));
                    classChosen = getIntInput(" ".repeat(15)+"Select class? (Yes [1] | No [0]): ", 0, 1);
                    if(classChosen == 1){
                        player = new JinwooSun(thyName + " (Test)");
                        System.out.println(ColorUtil.brightCyanBold("\n"+" ".repeat(15)+"Selecting The Right Choice..."));
                    }
                    break;
            }
            delay(800);
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
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[1]")+ColorUtil.brightGreenBold(" Explore and Fight") +" ".repeat(29)+ ColorUtil.blueBright("║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[2] Check Stats and Resurrection status") + ColorUtil.blueBright("           ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[3] My Inventory") + ColorUtil.blueBright("                                  ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[4]")+ColorUtil.brightPurpleBold(" Kyle's Shop") + ColorUtil.blueBright("                                   ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[5] View Current Story") + ColorUtil.blueBright("                            ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[6]")+ColorUtil.brightYellowBold(" Proceed to Story") + ColorUtil.blueBright("                              ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[7]")+ColorUtil.greenBold(" What do I do?") + ColorUtil.blueBright("                                 ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[8]")+ColorUtil.redBold(" Quit game") + ColorUtil.blueBright("                                     ║"));
            System.out.println(ColorUtil.blueBright("╚════════════════════════════════════════════════════╝"));

            choice = getIntInput("Enter your choice: ", 1, 8);

            switch (choice) {
                case 1:
                    if(player.getEquippedWeapon().getName() == "None"){
                        int input = getIntInput(ColorUtil.brightRedBold("⚠️ Warning ⚠️ You have not equipped a weapon Continue?")+" (Yes [1] | No [0]): ", 0, 1);
                        if(input == 1) {
                            explore();
                        }
                    }else{
                        explore();
                    }
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
                    System.out.println(ColorUtil.brightYellowBold("In this world of Akhai, every victory earns you loot,\ngold, and strength! Explore, defeat monsters, and \nstrengthen your character to become the hero destined \nto reclaim the Lost Akhai!"));
                    System.out.println(ColorUtil.brightCyanBold("[Press ENTER to Continue Game]"));
                    scan.nextLine();
                    break;
                case 8:
                    int confirm = getIntInput(ColorUtil.brightRedBold("Are you sure you want to quit the game?")+" (Yes [1) | No [0]: ", 0, 1);
                    if(confirm == 1){
                        gameRunning = false;
                        System.out.println(ColorUtil.brightYellowBold("Thanks for playing Akhai!"));
                        break;
                    }
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
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[1]")+ColorUtil.brightGreenBold(" Explore and Fight") +" ".repeat(29)+ ColorUtil.blueBright("║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[2] Check Stats and Resurrection status") + ColorUtil.blueBright("           ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[3] My Inventory") + ColorUtil.blueBright("                                  ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[4]")+ColorUtil.brightPurpleBold(" Kyle's Shop") + ColorUtil.blueBright("                                   ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[5] View Current Story") + ColorUtil.blueBright("                            ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[6]")+ColorUtil.brightRedBold(" Enter the Final Battle") + ColorUtil.blueBright("                        ║"));
            System.out.println(ColorUtil.blueBright("║  ") + ColorUtil.brightCyanBold("[7]")+ColorUtil.redBold(" Quit game") + ColorUtil.blueBright("                                     ║"));
            System.out.println(ColorUtil.blueBright("╚════════════════════════════════════════════════════╝"));

            choice = getIntInput("Enter your choice: ", 1, 7);

            switch (choice) {
                case 1:
                    if(player.getEquippedWeapon().getName() == "None"){
                        int input = getIntInput(ColorUtil.brightRedBold("⚠️ Warning ⚠️ You have not equipped a weapon! Continue?")+" (Yes [1] | No [0]): ", 0, 1);
                        if(input == 1) {
                            explore();
                        }
                    }else{
                        explore();
                    }
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
                    int confirm = getIntInput(ColorUtil.brightRedBold("Are you sure you want to quit the game?")+" (Yes [1] | No [0]): ", 0, 1);
                    if(confirm == 1){
                        gameRunning = false;
                        System.out.println("Thanks for playing Akhai!");
                        break;
                    }
            }
        }
    }

    //TODO make sure to display a warning if they try to explore without a weapon equipped -for frank
    public void explore() {
        player.setIsExploring(true);

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
        player.setIsExploring(false);
    }
    private void obtainLoot(Character player, Enemy enemy){
        double chance = Math.random(); //Made a variable para di sigeg gamit ug Math.random ugh

        if(!(enemy instanceof MiniBoss)){
            switch(currentChapter) {
                case 1:
                    Chest CommonChest = new Chest.CommonChest();
                    System.out.println(ColorUtil.blueGreenBold("\t↓ 💎 You found a " + CommonChest.getName() + " and opened it! ↓"));
                    CommonChest.obtain(player, currentChapter);
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
                        EliteChest.obtain(player, currentChapter);
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
                        EpicChest.obtain(player, currentChapter);
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
                        LegendaryChest.obtain(player, currentChapter);
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
                        LegendaryChest.obtain(player, currentChapter);
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
            MythicalChest.obtain(player, currentChapter);
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
        player.gainExperience(expBonus, currentChapter);
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
            System.out.println(ColorUtil.brightYellowBold("\n⚔️ New challenges await in ") + getLevelName(currentChapter) + ColorUtil.brightYellowBold("!"));
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
                System.out.println(ColorUtil.brightYellowBold("\n⭐ " + player.getName() + " used basic attack after resurrection!"));
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
        Boss enemy = selectChapterBoss();

        System.out.println(ColorUtil.brightRedBold("\t\t\t\t\t‼️ WARNING ️‼️️"));
        System.out.println(ColorUtil.brightRedBold("\t\t\t🚨 BOSS " + enemy.getName() + " appears!"));
        delay(1000);

        int baseExp = 40;

        int playerCurrentSpeed = player.getSpeed();
        int playerOriginalSpeed = player.getSpeed();
        int enemyCurrentSpeed = enemy.getSpeed();
        int enemyOriginalSpeed = enemy.getSpeed();

        while (enemy.getHealth() > 0 && player.getHealth() > 0 && inBattle) {
            if(enemy.getEnteredNextPhase()){
                System.out.println(ColorUtil.brightRedBold("\t\t‼️BOSS "+enemy.getName()+" is entering its 2nd phase‼️"));
                enemy.setEnteredNextPhase(false);
                delay(2000);
            }
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
    public void battleMinionBoss(Character player, Boss enemy){
        inBattle = true;

        int turns = 1;

        System.out.println(ColorUtil.brightRedBold("\t\t🚨 BOSS " + enemy.getName() + " appears!"));
        delay(1000);

        int playerCurrentSpeed = player.getSpeed();
        int playerOriginalSpeed = player.getSpeed();
        int enemyCurrentSpeed = enemy.getSpeed();
        int enemyOriginalSpeed = enemy.getSpeed();

        while (enemy.getHealth() > 0 && player.getHealth() > 0 && inBattle) {
            if(enemy.getEnteredNextPhase()){
                System.out.println(ColorUtil.brightRedBold("\t\t‼️BOSS "+enemy.getName()+" is entering phase "+enemy.getPhase()+"‼️"));
                enemy.setEnteredNextPhase(false);
            }

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

        if (player.getHealth() <= 0) {
            handlePlayerDefeat();
        }else{
            System.out.println(ColorUtil.brightYellowBold("\t\t🎉 You defeated " + enemy.getName() + "!"));
            delay(1000);
        }

        inBattle = false;
    }

    public void battle(Character player, EliteEnemy enemy){
        inBattle = true;
        player.setInBattle(true);

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
        if (player.getHealth() <= 0) {
            handlePlayerDefeat();
        }else{
            System.out.println(ColorUtil.brightYellowBold("\t\t🎉 You defeated " + enemy.getName() + "!"));
            delay(1000);
        }
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
            if(enemy.getEnteredNextPhase()){
                System.out.println(ColorUtil.brightRedBold("\t\t‼️BOSS "+enemy.getName()+" is entering phase "+enemy.getPhase()+"‼️"));
                enemy.setEnteredNextPhase(false);
            }

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
            if (!isPlayerTurn && player.getHealth() <= 0 && !player.hasResurrected() && enemy instanceof MiniBoss) {
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
        player.setInBattle(false);
    }
    //Boss Din
    public void battle(Character player, Boss enemy, int maxWave/*min: 2 | max: 3*/){
        for(int wave = 1; wave <= maxWave; wave++){
            if(wave == maxWave){
                battle(player, enemy);
            }else if(wave == 1 && maxWave == 3){
                battle(player, randomizeEliteEnemy());
            }else{
                battleMinionBoss(player, new Boss.Kamish());
            }
        }
    }

    public static boolean playerHits(Enemy enemy){
        return Math.random() <= enemy.getEvasiveness();
    }
    public static boolean enemyHits(Character player){
        return Math.random() <= player.getEvasiveness();
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
                    if (playerHits(enemy)) {
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
                if (enemyHits(player)) {
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
        IntWrapper healingPotionUsed = new IntWrapper(0);

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
                    hasActed = openInventory(player, enemy, healingPotionUsed) == 1;
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
        String energy = getEnergySystemName();
        System.out.println(ColorUtil.blueBright("╔════════════════════════════════════════════════════╗"));
        System.out.println(ColorUtil.blueBright("║") + ColorUtil.brightCyanBold("                        FIGHT                       ") + ColorUtil.blueBright("║"));
        System.out.println(ColorUtil.blueBright("╠════════════════════════════════════════════════════╣"));
        System.out.println(ColorUtil.brightCyanBold("   [1]")+ColorUtil.brightYellowBold(" Basic Attack"));
        System.out.println(ColorUtil.brightCyanBold("\t\t(Generates 25 ")+energy+ColorUtil.brightCyanBold(" + 1 ultimate charge)  "));
        System.out.println(ColorUtil.brightCyanBold("   [2]")+ColorUtil.brightYellowBold(" Skill"));
        System.out.println(ColorUtil.brightCyanBold("\t\t(Costs 40 ")+energy+ColorUtil.brightCyanBold(" + 2 ultimate charges)    "));

        if (player.isUltimateReady()) {
            System.out.println(ColorUtil.brightCyanBold("   [3]")+ ColorUtil.brightYellowBold(" Ultimate"));
            System.out.println(ColorUtil.brightYellowBold("\t\t(Costs 80 ")+energy+ColorUtil.brightYellowBold(", consumes all charges) "));
        } else {
            System.out.println(ColorUtil.brightCyanBold("   [3] ")+ColorUtil.brightRedBold("Ultimate (Locked)"));
            System.out.println(ColorUtil.brightRedBold("\t\t(" + player.getUltimateCounter() + "/" + player.getMaxUltimateCounter() + " charges)"));
        }

        System.out.println(ColorUtil.brightCyanBold("   [4]")+ColorUtil.greenBold(" Back"));
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
                    System.out.println(ColorUtil.brightRedBold("You need 40 ")+energy+ColorUtil.brightRedBold(" before you can use this skill. Current: " + player.getEnergy()));
                    damage = handleFightAction(enemy); // Recursive call to try again
                }
                break;
            case 3:
                if (player.isUltimateReady()) {
                    if (player.getEnergy() >= player.getUltimateAttack().getEnergyCost()) {
                        damage = player.getUltimateAttack().execute(player);
                    } else {
                        System.out.println(ColorUtil.brightRedBold("You need 80 ")+energy+ColorUtil.brightRedBold(" before you can use the ultimate. Current: " + player.getEnergy()));
                        damage = handleFightAction(enemy); // Recursive call to try again
                    }
                } else {
                    System.out.println(ColorUtil.brightRedBold("Ultimate is locked! You need ") + player.getMaxUltimateCounter() + ColorUtil.brightRedBold(" charges. Current: " + player.getUltimateCounter() + "/" + player.getMaxUltimateCounter()));
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
            if(!(enemy instanceof MiniBoss)){
                handlePlayerDefeat();
            }else{
                handlePlayerDefeatFromMiniBoss();
            }
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
        System.out.println(ColorUtil.brightRedBold("\t\t💀 You have been slain..."));
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
        String[] bannerLines1 = {
                "  ▄████  ▄▄▄       ███▄ ▄███▓▓█████     ▒█████   ██▒   █▓▓█████  ██▀███",
                " ██▒ ▀█▒▒████▄    ▓██▒▀█▀ ██▒▓█   ▀    ▒██▒  ██▒▓██░   █▒▓█   ▀ ▓██ ▒ ██▒",
                "▒██░▄▄▄░▒██  ▀█▄  ▓██    ▓██░▒███      ▒██░  ██▒ ▓██  █▒░▒███   ▓██ ░▄█ ▒",
                "░▓█  ██▓░██▄▄▄▄██ ▒██    ▒██ ▒▓█  ▄    ▒██   ██░  ▒██ █░░▒▓█  ▄ ▒██▀▀█▄",
                "░▒▓███▀▒ ▓█   ▓██▒▒██▒   ░██▒░▒████▒   ░ ████▓▒░   ▒▀█░  ░▒████▒░██▓ ▒██▒",
                " ░▒   ▒  ▒▒   ▓▒█░░ ▒░   ░  ░░░ ▒░ ░   ░ ▒░▒░▒░    ░ ▐░  ░░ ▒░ ░░ ▒▓ ░▒▓░",
                "  ░   ░   ▒   ▒▒ ░░  ░      ░ ░ ░  ░     ░ ▒ ▒░    ░ ░░   ░ ░  ░  ░▒ ░ ▒░",
                "      ░       ░  ░       ░      ░  ░       ░ ░        ░     ░  ░   ░",
                "                                                     ░                   ",
        };

        for (String line : bannerLines1) {
            String coloredLine = "";
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if(c == '█'){
                    coloredLine += ColorUtil.redBright("█");
                } else if(c == '▄'){
                    coloredLine += ColorUtil.redBright("▄");
                }else if(c == '▐'){
                    coloredLine += ColorUtil.redBright("▐");
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
        gameRunning = false;
    }
    private void handlePlayerDefeatFromMiniBoss() {
        delay(1500);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(ColorUtil.brightRedBold("\t\t💀 You have been slain..."));
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
        String[] bannerLines1 = {
                "  ▄████  ▄▄▄       ███▄ ▄███▓▓█████     ▒█████   ██▒   █▓▓█████  ██▀███",
                " ██▒ ▀█▒▒████▄    ▓██▒▀█▀ ██▒▓█   ▀    ▒██▒  ██▒▓██░   █▒▓█   ▀ ▓██ ▒ ██▒",
                "▒██░▄▄▄░▒██  ▀█▄  ▓██    ▓██░▒███      ▒██░  ██▒ ▓██  █▒░▒███   ▓██ ░▄█ ▒",
                "░▓█  ██▓░██▄▄▄▄██ ▒██    ▒██ ▒▓█  ▄    ▒██   ██░  ▒██ █░░▒▓█  ▄ ▒██▀▀█▄",
                "░▒▓███▀▒ ▓█   ▓██▒▒██▒   ░██▒░▒████▒   ░ ████▓▒░   ▒▀█░  ░▒████▒░██▓ ▒██▒",
                " ░▒   ▒  ▒▒   ▓▒█░░ ▒░   ░  ░░░ ▒░ ░   ░ ▒░▒░▒░    ░ ▐░  ░░ ▒░ ░░ ▒▓ ░▒▓░",
                "  ░   ░   ▒   ▒▒ ░░  ░      ░ ░ ░  ░     ░ ▒ ▒░    ░ ░░   ░ ░  ░  ░▒ ░ ▒░",
                "      ░       ░  ░       ░      ░  ░       ░ ░        ░     ░  ░   ░",
                "                                                     ░                   ",
        };

        for (String line : bannerLines1) {
            String coloredLine = "";
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if(c == '█'){
                    coloredLine += ColorUtil.redBright("█");
                } else if(c == '▄'){
                    coloredLine += ColorUtil.redBright("▄");
                }else if(c == '▐'){
                    coloredLine += ColorUtil.redBright("▐");
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
    }
    private void handleVictory(Enemy enemy, int baseExp, Character player) {
        if(enemy.getHealth() <= 0){
            System.out.println(ColorUtil.brightYellowBold("\t\t🎉 You defeated " + enemy.getName() + "!"));
            delay(1000);

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

            if(enemy instanceof Boss){
                player.gainExperience(baseExp, currentChapter);
            }else{
                player.gainExperience(baseExp, currentChapter);
            }
            delay(500);

            obtainGold(player);
            delay(500);

            obtainLoot(player, enemy);
            delay(500);

            //Heal 25% Lost Hp after winning a battle
            player.afterBattleHeal();
            delay(500);
            System.out.println(ColorUtil.brightCyanBold("\t\t\t[Press ENTER to continue...]"));

            java.util.Scanner scanner = new java.util.Scanner(System.in);
            scanner.nextLine();
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
                        System.out.println(ColorUtil.brightRedBold("\t\tYou can't equip a weapon during battle!"));
                        confirm = 0;
                        delay(1000);
                    }else{
                        System.out.println(ColorUtil.brightYellowBold("\t"+player.getName()+" used "+item.getName()+"!"));
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
                System.out.println(ColorUtil.brightRedBold("\t\tPlease select another item."));
                isEmpty = false;
                delay(1000);
            }

        }
        return confirm;
    }
    private int openInventory(Character player, Entity enemy, IntWrapper uses){
        int confirm = 0;
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
                        System.out.println(ColorUtil.brightRedBold("\t\tYou can't equip a weapon during battle!"));
                        confirm = 0;
                        delay(1000);
                    }else{
                        System.out.println(ColorUtil.brightYellowBold("\t"+player.getName()+" used "+item.getName()+"!"));
                        delay(1000);
                        item.use(player);
                        if(inBattle){
                            if(item instanceof HealingPotion){
                                uses.value++;
                            }
                            if(uses.value < 3){
                                System.out.println(ColorUtil.brightYellowBold("\t\t\tYou can take another action!"));
                                delay(1000);
                                displayBattleHealth(player, enemy);
                                confirm = 0;
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
                System.out.println(ColorUtil.brightRedBold("\t\tPlease select another item."));
                isEmpty = false;
                delay(1000);
            }
        }
        return confirm;
    }
    class IntWrapper {
        int value;

        IntWrapper(int value){
            this.value = value;
        }
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
    private String getEnergySystemName() {
        if (player instanceof Hawkseye) {
            return ColorUtil.greenBold("Insight");
        } else if (player instanceof Blademaster) {
            return ColorUtil.blueBright("Energy");
        } else if (player instanceof Berserker) {
            return ColorUtil.orange("Fury");
        } else if (player instanceof Runecaster) {


            return ColorUtil.purpleBright("Mana");
        } else if (player instanceof RuneKnight) {
            return ColorUtil.yellowBright("Blessing");
        } else if (player instanceof Shinobi) {
            return ColorUtil.purple("Chakra");
        } else {
            return ColorUtil.blue("Aura");
        }
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
    public String getNameInput(String prompt){
        while(true){
            System.out.print(prompt);
            try{
                String userInput = scan.nextLine().trim();
                if(userInput.isEmpty()){
                    System.out.println(" ".repeat(32)+ColorUtil.brightRedBold("Please enter your name"));
                    continue;
                }
                if(userInput.length() > 15){
                    System.out.println(" ".repeat(22)+ColorUtil.brightRedBold("Please enter a maximum of 15 characters"));
                    continue;
                }
                return userInput;
            }catch(Exception e){
                System.out.println(" ".repeat(23)+ColorUtil.brightRedBold("Invalid input. Please enter a valid name"));
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