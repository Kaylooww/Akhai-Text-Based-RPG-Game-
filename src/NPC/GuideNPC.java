package NPC;

import Entities.Characters.Character;

public class GuideNPC extends NPC {
    public GuideNPC(String name, String description) {
        super(name, description);
    }
    @Override
    public void interact(Character player, int currentChapter) {
        //TODO: ADD DIALOGUES
        //Dialogues will differ based on story progression (For Kyle)
        //Dialogues give hint what the player needs to do for the story to progress
        System.out.println(name+": Welcome to Akhai! Your journey will be challenging but rewarding.");
        System.out.println("How about we explore the area ahead of us later!");
    }
}
