package NPC;

import java.util.Scanner;
import Entities.Characters.Character;

public abstract class NPC {
    protected Scanner scan = new Scanner(System.in);
    protected String name;
    protected String description;

    public NPC(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void interact(Character player,  int currentChapter);

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
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
