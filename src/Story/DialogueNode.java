package Story;

//By Kyle

import TextFormat.ColorUtil;
import java.util.Scanner;

/**
 * Represents a single dialogue or narration node in the story
 * Supports different dialogue types with color formatting
 */
public class DialogueNode {
    private String speaker;
    private String text;
    private DialogueType type;
    private int delay;
    private static Scanner scan = new Scanner(System.in);

    /**
     * Enum for different types of dialogue
     */
    public enum DialogueType {
        NARRATION,          // Story narration
        CHARACTER_SPEECH,   // Player character speaking
        KHAI_SPEECH,        // Khai (spirit guide) speaking
        DIN_SPEECH,         // Din (demon king) speaking
        SYSTEM_MESSAGE,     // Game system messages
        THOUGHT             // Character's internal thoughts
    }

    /**
     * Full constructor with custom delay
     * @param speaker The speaker's name
     * @param text The dialogue text
     * @param type The type of dialogue
     * @param delay Delay in milliseconds before displaying
     */
    public DialogueNode(String speaker, String text, DialogueType type, int delay) {
        this.speaker = speaker;
        this.text = text;
        this.type = type;
        this.delay = delay;
    }

    /**
     * Constructor with default delay of 2000ms
     * @param speaker The speaker's name
     * @param text The dialogue text
     * @param type The type of dialogue
     */
    public DialogueNode(String speaker, String text, DialogueType type) {
        this(speaker, text, type, 2000);
    }

    /**
     * Displays the dialogue node with appropriate formatting
     * @param skipDelay If true, skips the delay between dialogues
     */
    public void display(boolean skipDelay) {
        switch (type) {
            case NARRATION:
                displayNarration();
                break;
            case CHARACTER_SPEECH:
                displayCharacterSpeech();
                break;
            case KHAI_SPEECH:
                displayKhaiSpeech();
                break;
            case DIN_SPEECH:
                displayDinSpeech();
                break;
            case SYSTEM_MESSAGE:
                displaySystemMessage();
                break;
            case THOUGHT:
                displayThought();
                break;
        }

        if (!skipDelay) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    //Displays narration text in cyan
    private void displayNarration() {
        System.out.println(ColorUtil.cyan("\n" + text));
    }

    //Displays player character speech in green
    private void displayCharacterSpeech() {
        System.out.println(ColorUtil.green("\n[" + speaker + "]: " + text));
    }

    //Displays Khai's speech in purple
    private void displayKhaiSpeech() {
        System.out.println(ColorUtil.purple("\n[Khai - The Wandering Spirit]: " + text));
    }

    //Displays Din's speech in red
    private void displayDinSpeech() {
        System.out.println(ColorUtil.red("\n[Din - The Demon King]: " + text));
    }

    //Displays system messages in yellow
    private void displaySystemMessage() {
        System.out.println(ColorUtil.yellow("\n>>> " + text + " <<<"));
    }

    //Displays character thoughts in blue
    private void displayThought() {
        System.out.println(ColorUtil.blue("\n(Thought) " + text));
    }

    // Getters
    public String getSpeaker() {
        return speaker;
    }

    public String getText() {
        return text;
    }

    public DialogueType getType() {
        return type;
    }

    public int getDelay() {
        return delay;
    }
}