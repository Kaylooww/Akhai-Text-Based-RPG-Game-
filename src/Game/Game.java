package Game;

import Entities.Enemies.Enemy;
import Entities.Characters.*;
import Entities.Characters.Character;
import NPC.BossNPC;
import NPC.FortuneTellerNPC;
import NPC.GuideNPC;
import NPC.NPC;

import java.util.*;

public class Game {
    private Scanner scan = new Scanner(System.in);
    private Character player;
    private List<Enemy> enemies = new ArrayList<>();
    private List<NPC> npcs = new ArrayList<>();
    private int currentLevel = 1;
    private boolean gameRunning = true;
    private boolean inBattle = false;
    private final int MAX_LEVEL = 6;
    private boolean[] levelsCompleted = new boolean[MAX_LEVEL + 1];
    
    public void initializeGame() {
        //npcs and enemies need to be finalized
        npcs.add(new GuideNPC("Frank", "Companion"));
        npcs.add(new BossNPC("Zed", "Edge-Lord Math geek boss"));
        npcs.add(new FortuneTellerNPC("Kyle", "Shopkeeper"));

        //add enemies in relation to the current level of the game
        enemies.add(new Enemy("Desert Clause", 40, 20, 30, 30, 40.0, 10.0, 20));
        enemies.add(new Enemy("Sand Stalker", 30, 20, 30, 30, 34.0, 10.0, 20));
        enemies.add(new Enemy("Dune Crawler", 34, 20, 30, 30, 30.0, 10.0, 20));

        System.out.println("Welcome to Akhai!");
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
                player = new Hawkseye("Dexter");
                break;
            case 2:
                player = new Blademaster("Kimjie");
                break;
            case 3:
                player = new Runecaster("Jeff");
                break;
            case 4:
                player = new Berserker("Jomel");
                break;
            case 5:
                player = new Shinobi("Blanco");
                break;
            case 6:
                player = new RuneKnight("Geoff");
                break;
            case 7:
                player = new JinwooSun();
                break;
        }

        System.out.println("You have chosen " + player.getName() + "!");
    }

    public void startGame() {
        System.out.println("\n=== Beginning your adventure in Akhai ===");
        System.out.println("You are on a quest to find the power to go back to your world.");

        while(gameRunning) {
            displayLevelMap();
            handleLevelActions();

            if (currentLevel > 6) { // Assuming 6 levels based on the map
                System.out.println("Congratulations! You have completed the game!");
                gameRunning = false;
            }
        }
    }
    //Tarongon nato map
    public void displayLevelMap() {
        System.out.println("\n🗺️  === CURRENT MAP ===");
        System.out.println("Progress: " + getCompletionPercentage() + "% complete");

        for (int i = 1; i <= MAX_LEVEL; i++) {
            String status = (i == currentLevel) ? "📍 CURRENT" :
                    (levelsCompleted[i]) ? "✅ COMPLETED" : "🔒 LOCKED";
            System.out.println("Level " + i + ": " + getLevelName(i) + " - " + status);
        }
        System.out.println("======================");
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
        System.out.println("1. Explore");
        System.out.println("2. Check stats");
        System.out.println("3. Check resurrection status");
        System.out.println("4. Talk to NPC");
        System.out.println("5. Move to next level");
        System.out.println("6. Quit game");

        int choice = getIntInput("Enter your choice: ", 1, 6);

        switch (choice) {
            case 1:
                explore();
                break;
            case 2:
                player.displayStats();
                break;
            case 3:
                checkResurrectionStatus();
                break;
            case 4:
                talkToNPC();
                break;
            case 5:
                attemptLevelProgression();
                break;
            case 6:
                gameRunning = false;
                System.out.println("Thanks for playing Akhai!");
                break;
        }
    }

    public void explore() {
        System.out.println("\nYou explore " + getLevelName(currentLevel) + "...");

        // Different encounter rates based on level
        double encounterRate = 0.6 + (currentLevel * 0.05);
        if (Math.random() <= encounterRate) {
            System.out.println("🚨 You encountered an enemy!");
            battle();
        } else {
            // Chance for finding items or hidden events
            if (Math.random() > 0.7) {
                findHiddenTreasure();
            } else {
                System.out.println("You found nothing of interest.");
            }
        }
    }

    private void findHiddenTreasure() {
        System.out.println("💎 You found a hidden treasure!");
        int expBonus = currentLevel * 10;
        player.gainExperience(expBonus);
        System.out.println("Gained " + expBonus + " bonus experience!");
    }

    private void attemptLevelProgression() {
        if (currentLevel >= MAX_LEVEL) {
            System.out.println("🎉 Congratulations! You've completed all levels!");
            return;
        }

        // Check if player is strong enough to progress
        if (player.getLevel() < currentLevel * 2) {
            System.out.println("⚠️  You're not strong enough to face the challenges ahead!");
            System.out.println("Recommended level for next area: " + (currentLevel * 2));
            System.out.println("Your current level: " + player.getLevel());
            return;
        }

        System.out.println("🚀 Moving to " + getLevelName(currentLevel + 1) + "...");
        levelsCompleted[currentLevel] = true;
        currentLevel++;

        // Scale enemies for new level
        scaleEnemiesForCurrentLevel();
        System.out.println("New challenges await in " + getLevelName(currentLevel) + "!");
    }

    private void scaleEnemiesForCurrentLevel() {
        enemies.clear();

        // Create appropriately scaled enemies for current level
        int baseStats = 50 + (currentLevel * 10);
        String[] enemyNames = getEnemyNamesForLevel(currentLevel);

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

    public void battle() {
        inBattle = true;
        Enemy enemy = enemies.get((int)(Math.random() * enemies.size()));
        System.out.println("🚨 A wild " + enemy.getName() + " appears!");

        int baseExp = 25 + (currentLevel * 5);

        while (enemy.getHealth() > 0 && player.getHealth() > 0) {
            System.out.println("\n" + player.getName() + " HP: " + player.getHealth() + "/" + player.getMaxHealth());
            System.out.println(enemy.getName() + " HP: " + enemy.getHealth());
            System.out.println("1. Basic Attack");
            System.out.println("2. Use Skill");
            System.out.println("3. Use Ultimate");
            System.out.println("4. Try to flee");

            int choice = getIntInput("Choose action: ", 1, 4);

            int damage = 0;
            switch(choice) {
                case 1:
                    damage = player.useBasicAttack();
                    break;
                case 2:
                    damage = player.useSkillAttack();
                    break;
                case 3:
                    damage = player.useUltimateAttack();
                    break;
                case 4:
                    if (Math.random() > 0.7) {
                        System.out.println("You successfully fled!");
                        inBattle = false;
                        return;
                    } else {
                        System.out.println("You failed to flee!");
                    }
                    break;
            }

            if (choice != 4) {
                int actualDamage = enemy.takeDamage(damage, enemy.getDefense(), enemy.getPhysicalResistance(), enemy.getMagicResistance());
                System.out.println("You dealt " + actualDamage + " damage to " + enemy.getName());
            }

            // Entities.Enemies.Enemy turn only if still alive and player didn't flee
            if (enemy.getHealth() > 0 && choice != 4) {
                int enemyDamage = enemy.attack();
                int actualEnemyDamage = player.takeDamage(enemyDamage, player.getDefense(), player.getPhysicalResistance(), player.getMagicResistance());
                System.out.println(enemy.getName() + " dealt " + actualEnemyDamage + " damage to you!");

                // Check if player died and can resurrect
                if (player.getHealth() <= 0 && !player.hasResurrected()) {
                    System.out.println("\n💫 The power of resurrection is available...");
                    System.out.println("Would you like to use your one-time resurrection?");
                    System.out.println("1. Yes, resurrect and continue fighting!");
                    System.out.println("2. No, accept defeat");

                    int resurrectChoice = getIntInput("Choose: ", 1, 2);
                    if (resurrectChoice == 1) {
                        player.resurrect();
                        // Player gets a free turn after resurrection
                        System.out.println("\n⭐ " + player.getName() + " gets a free attack after resurrection!");
                        damage = player.useBasicAttack();
                        int actualDamage = enemy.takeDamage(damage, enemy.getDefense(), enemy.getPhysicalResistance(), enemy.getMagicResistance());
                        System.out.println("You dealt " + actualDamage + " damage to " + enemy.getName());
                    } else {
                        System.out.println("You accept your fate...");
                    }
                }
            }
        }

        inBattle = false;

        if (player.getHealth() <= 0) {
            handlePlayerDefeat();
        } else {
            handleVictory(enemy, baseExp);
        }
    }

    private void handlePlayerDefeat() {
        System.out.println("\n💀 You have been defeated...");

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
        System.out.println("🎉 You defeated " + enemy.getName() + "!");
        System.out.println("💰 Gained " + baseExp + " experience!");
        player.gainExperience(baseExp);

        // Bonus for not using resurrection
        if (!player.hasResurrected()) {
            int bonusExp = (int)(baseExp * 0.2);
            System.out.println("⭐ Bonus " + bonusExp + " experience for completing the battle without resurrection!");
            player.gainExperience(bonusExp);
        }

        // Chance for energy restoration
        if (Math.random() > 0.5) {
            System.out.println("✨ Energy partially restored!");
            player.setEnergy(Math.min(100, player.getEnergy() + 25));
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
}