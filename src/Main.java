import java.util.*;

public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static Character player;
    private static List<Enemy> enemies = new ArrayList<>();
    private static List<NPC> npcs = new ArrayList<>();
    private static int currentLevel = 1;
    private static boolean gameRunning = true;

    public static void main(String[] args) {
        initializeGame();
        startGame();
    }

    private static void pressAnyKeyToContinue() {
        System.out.println("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            // Handle exception
        }
    }
    private static void initializeGame() {
        npcs.add(new GuideNPC("Frank", "Player guide"));
        npcs.add(new BossNPC("Zed", "Math geek boss"));
        npcs.add(new FortuneTellerNPC("Kyle", "Fortune teller"));

        enemies.add(new Enemy("Desert Clause", 60, 30, 70, 80, 40));
        enemies.add(new Enemy("Pain", 70, 40, 60, 70, 50));
        enemies.add(new Enemy("Sprain", 50, 20, 80, 60, 30));

        System.out.println("Welcome to Akhai!");
        pressAnyKeyToContinue();

        boolean characterSelected = false;

        while(!characterSelected){
            System.out.println("Choose your character:");
            System.out.println("[1] Jamal (Berserker, Archer)");
            System.out.println("[2] Baek (Knight, Magic)");
            System.out.println("[3] Almond (Archer, Summon, Assassin)");
            System.out.println("[4] Jinwoo Sun (All classes) - Test character");

            int choice = getIntInput("Enter your choice: ", 1, 4);

            switch (choice) {
                case 1:
                    characterSelected = confirmCharacterSelection("Jamal", "A competitive athlete with anger management issues. His intense focus during competitions sometimes triggers uncontrollable rage episodes. His athletic precision translates to Archer abilities, while his rage issues manifest as Berserker powers.");
                    if(characterSelected){
                        player = new Jamal();
                    }
                    break;
                case 2:
                    characterSelected = confirmCharacterSelection("Baek", "A history graduate student specializing in medieval warfare and folklore. Spends weekends in historical reenactments and studying occult manuscripts. His knowledge of armor and combat tactics enables Knight abilities, while his study of occult works unlocks Mage potential.");
                    if(characterSelected){
                        player = new Baek();
                    }
                    break;
                case 3:
                    characterSelected = confirmCharacterSelection("Almond", "An animal rights activist and competitive archer who runs a wildlife sanctuary. Known for moving silently through forests to observe animals. Archery skills directly translate, while their deep connection with animals enables summoning abilities, and stealth experience enables assassin techniques.");
                    if(characterSelected){
                        player = new Almond();
                    }
                    break;
                case 4:
                    characterSelected = confirmCharacterSelection("Jinwoo Sun", "This is a test character");
                    if(characterSelected){
                        player = new JinwooSun();
                    }
                    break;
            }
        }

        System.out.println("You have chosen " + player.getName() + "!");
        pressAnyKeyToContinue();
    }

    private static boolean confirmCharacterSelection(String characterName, String description){
        System.out.println("\n=== "+characterName+" ===");
        System.out.println(description);
        System.out.println("\n[1] Select Character\n[2] Back");

        int confirm = getIntInput("Enter your choice: ", 1, 2);
        return confirm == 1;
    }

    private static void startGame() {
        System.out.println("\n=== Beginning your adventure in Akira ===");
        switch(player.getName()){
            case "Jamal":
                System.out.println("[STORY] During a championship match you channeled your fury into a winning shot and transported you to the world of Akhaidemia.\n" +
                        "Now, you are on a quest to find the treasure of \"Akhai\" to go back to the new world!");
                break;
            case "Baek":
                System.out.println("[STORY] While examining an ancient artifact during a museum night event and transported you to the world of Akhaidemia.\n" +
                        "Now, you are on a quest to find the treasure of \"Akhai\" to go back to the new world!");
                break;
            case "Almond":
                System.out.println("[STORY] While protecting animals from poachers during a night patrol, you have been transported to the world of Akhaidemia.\n" +
                        "Now, you are on a quest to find the treasure of \"Akhai\" to go back to the new world!");
                break;
            default:
                System.out.println("[STORY] You are on a quest to find the treasure of \"Akhai\" to go back to the new world!");
        }
        pressAnyKeyToContinue();

        while(gameRunning) {
            displayLevelMap();
            handleLevelActions();

            if (currentLevel > 6) { // Assuming 6 levels based on the map
                System.out.println("Congratulations! You have completed the game!");
                pressAnyKeyToContinue();
                gameRunning = false;
            }
        }
    }

    private static void displayLevelMap() {
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
        pressAnyKeyToContinue();
    }

    private static void handleLevelActions() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("[1] Explore");
        System.out.println("[2] Check stats");
        System.out.println("[3] Talk to NPC");
        System.out.println("[4] Move to next level");
        System.out.println("[5] Quit game");

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
                System.out.println("Thanks for playing Akira!");
                break;
        }
    }

    private static void explore() {
        System.out.println("You explore the area...");
        // Random encounter logic
        if (Math.random() > 0.5) {
            System.out.println("You encountered an enemy!");
            battle();
        } else {
            System.out.println("You found nothing of interest.");
        }
    }

    private static void battle() {
        int damage;
        int actualDamage;

        Enemy enemy = enemies.get((int)(Math.random() * enemies.size()));

        System.out.println("A wild " + enemy.getName() + " appears!");

        //We can do a boolean check here
        while (enemy.getHealth() > 0 && player.getHealth() > 0) {
            System.out.println("\n" + player.getName() + " HP: " + player.getHealth());
            System.out.println(enemy.getName() + " HP: " + enemy.getHealth());
            System.out.println("[1] Attack");
            System.out.println("[2] Use Skill");
            System.out.println("[3] Use Ultimate Skill");
            System.out.println("[4] Try to flee");

            int choice = getIntInput("Choose action: ", 1, 4);

            switch(choice) {
                case 1:
                    damage = player.useBasicAttack();
                    actualDamage = enemy.takeDamage(damage);
                    System.out.println("You dealt " + actualDamage + " damage to " + enemy.getName());
                    break;
                case 2:
                    damage = player.useSkillAttack();
                    actualDamage = enemy.takeDamage(damage);
                    System.out.println("You dealt " + actualDamage + " damage to " + enemy.getName());
                    break;
                case 3:
                    damage = player.useUltimateAttack(); //Must be activated based on a condition (eg. 3 skills or pina energy similar to genshin)
                    actualDamage = enemy.takeDamage(damage);
                    System.out.println("You dealt " + actualDamage + " damage to " + enemy.getName());
                    break;
                case 4:
                    if (Math.random() > 0.7) {
                        System.out.println("You successfully fled!");
                        return;
                    } else {
                        System.out.println("You failed to flee!");
                    }
                    break;
            }

            // Enemy turn
            //We can do a boolean check here
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

    private static void talkToNPC() {
        System.out.println("Which NPC would you like to talk to?");
        for (int i = 0; i < npcs.size(); i++) {
            System.out.println((i+1) + ". " + npcs.get(i).getName() + " - " + npcs.get(i).getDescription());
        }

        int choice = getIntInput("Enter your choice: ", 1, npcs.size());
        NPC selectedNPC = npcs.get(choice-1);

        System.out.println("You approach " + selectedNPC.getName() + "...");
        selectedNPC.interact();
    }

    private static int getIntInput(String prompt, int min, int max) {
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