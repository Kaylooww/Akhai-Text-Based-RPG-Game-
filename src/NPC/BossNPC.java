package NPC;

import java.util.Scanner;
import Entities.Characters.Character;

public class BossNPC extends NPC {
    public BossNPC(String name, String description) {
        super(name, description);
    }
//
    @Override
    public void interact(Character player, int currentChapter) {
        System.out.println("Zed: I am the math gett boss! Solve this: 2+2*2 = ?");
        Scanner scanner = new Scanner(System.in);
        int answer = scanner.nextInt();
        if (answer == 6) {
            System.out.println("Zed: Correct! You may proceed.");
        } else {
            System.out.println("Zed: Wrong! The answer is 6. Study more and return.");
        }
    }
}
