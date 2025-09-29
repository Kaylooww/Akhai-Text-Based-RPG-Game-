import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Scanner scan = new Scanner(System.in);
    private Player player;
    private List<Enemy> enemies = new ArrayList<>();
    private List<NPC> npcs = new ArrayList<>();
    private int currentLevel = 1;
    private boolean gameRunning = true;
    
    public void initializeGame() {
        //npcs and enemies need to be finalized
        npcs.add(new GuideNPC("Frank", "Player guide"));
        npcs.add(new BossNPC("Zed", "Math geek boss"));
        npcs.add(new FortuneTellerNPC("Kyle", "Fortune teller"));

        enemies.add(new Enemy("Desert Clause", 60, 30, 70, 80, 40));
        enemies.add(new Enemy("Pain", 70, 40, 60, 70, 50));
        enemies.add(new Enemy("Sprain", 50, 20, 80, 60, 30));

        System.out.println("Welcome to Akhai!");
        System.out.println("Choose your character:");
        System.out.println("1. Jamal (Berserker, Archer)");
        System.out.println("2. Baek (Knight, Magic)");
        System.out.println("3. Almond (Archer, Summon, Assassin)");
        System.out.println("4. Jinwoo Sun (All classes) - Test character");

        int choice = getIntInput("Enter your choice (1-4): ", 1, 4);

        switch (choice) {
            case 1:
                player = new Jamal();
                break;
            case 2:
                player = new Baek();
                break;
            case 3:
                player = new Almond();
                break;
            case 4:
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
        System.out.println("==== CURRENT MAP ====");
        switch(currentLevel) {
            case 1:
                System.out.println("Level 1: Whispering Crossroads");
                break;
            case 2:
                System.out.println("Level 2: Fungal Lumewood");
                break;
            case 3:
                System.out.println("Level 3: Ruins of a Fallen Star");
                break;
            case 4:
                System.out.println("Level 4: Root of All Worlds");
                break;
        }
        System.out.println("==================");
    }

    public void handleLevelActions() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Explore");
        System.out.println("2. Check stats");
        System.out.println("3. Talk to NPC");
        System.out.println("4. Move to next level");
        System.out.println("5. Quit game");

        int choice = getIntInput("Enter your choice: ", 1, 5);

        switch (choice) {
            case 1:
                explore();
                break;
            case 2:
                player.displayStats();
                break;
            case 3:
                talkToNPC();
                break;
            case 4:
                currentLevel++;
                System.out.println("Moving to level " + currentLevel);
                // Add enemies for new level
                if (currentLevel == 2) {
                    enemies.add(new Enemy("Level 2 Enemy 1", 70, 40, 75, 85, 50));
                    enemies.add(new Enemy("Level 2 Enemy 2", 80, 50, 65, 75, 60));
                }
                break;
            case 5:
                gameRunning = false;
                System.out.println("Thanks for playing Akhai!");
                break;
        }
    }

    public void explore() {
        System.out.println("You explore the area...");
        // Random encounter logic
        if (Math.random() > 0.5) {
            System.out.println("You encountered an enemy!");
            battle();
        } else {
            System.out.println("You found nothing of interest.");
        }
    }

    public void battle() {
        Enemy enemy = enemies.get((int)(Math.random() * enemies.size()));
        System.out.println("A wild " + enemy.getName() + " appears!");

        while (enemy.getHealth() > 0 && player.getHealth() > 0) {
            System.out.println("\n" + player.getName() + " HP: " + player.getHealth());
            System.out.println(enemy.getName() + " HP: " + enemy.getHealth());
            System.out.println("1. Attack");
            System.out.println("2. Use Skill");
            System.out.println("3. Try to flee");

            int choice = getIntInput("Choose action: ", 1, 3);

            switch(choice) {
                case 1:
                    int damage = player.attack();
                    int actualDamage = enemy.takeDamage(damage);
                    System.out.println("You dealt " + actualDamage + " damage to " + enemy.getName());
                    break;
                case 2:
                    player.useSpecialAbility();
                    break;
                case 3:
                    if (Math.random() > 0.7) {
                        System.out.println("You successfully fled!");
                        return;
                    } else {
                        System.out.println("You failed to flee!");
                    }
                    break;
            }

            // Enemy turn
            if (enemy.getHealth() > 0) {
                int enemyDamage = enemy.attack();
                int actualEnemyDamage = player.takeDamage(enemyDamage);
                System.out.println(enemy.getName() + " dealt " + actualEnemyDamage + " damage to you!");
            }
        }

        if (player.getHealth() <= 0) {
            System.out.println("You were defeated...");
            gameRunning = false;
        } else {
            System.out.println("You defeated " + enemy.getName() + "!");
            // Gain experience
            player.gainExperience(25);
        }
    }

    public void talkToNPC() {
        System.out.println("Which NPC would you like to talk to?");
        for (int i = 0; i < npcs.size(); i++) {
            System.out.println((i+1) + ". " + npcs.get(i).getName() + " - " + npcs.get(i).getDescription());
        }

        int choice = getIntInput("Enter your choice: ", 1, npcs.size());
        NPC selectedNPC = npcs.get(choice-1);

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

