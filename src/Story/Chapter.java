package Story;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a chapter in the game's story
 * Contains chapter information and dialogues
 */
public class Chapter {
    private int chapterNumber;
    private String chapterTitle;
    private String chapterDescription;
    private List<DialogueNode> dialogues;
    private boolean isCompleted;

    /**
     * Constructor for Chapter
     * @param chapterNumber The chapter number (1-6)
     * @param chapterTitle The title of the chapter
     */
    public Chapter(int chapterNumber, String chapterTitle, String chapterDescription) {
        this.chapterNumber = chapterNumber;
        this.chapterTitle = chapterTitle;
        this.dialogues = new ArrayList<>();
        this.isCompleted = false;
    }

    /**
     * Adds a dialogue node to this chapter
     * @param dialogue The DialogueNode to add
     */
    public void addDialogue(DialogueNode dialogue) {
        this.dialogues.add(dialogue);
    }

    /**
     * Displays the chapter introduction banner
     */
    public void displayChapterIntro() {
        int bannerWidth = 60; // Fixed width
        String header = "CHAPTER " + chapterNumber;
        String fullTitle = chapterTitle; // Or "CHAPTER " + chapterNumber + ": " + chapterTitle;

        // Header line
        int headerPadding = (bannerWidth - header.length() - 2) / 2;
        System.out.print("\n╔" + "═".repeat(headerPadding));
        System.out.print(" " + header + " ");
        System.out.println("═".repeat(bannerWidth - headerPadding - header.length() - 2) + "╗");

        // Center the title
        int titlePadding = (bannerWidth - fullTitle.length()) / 2;
        System.out.println(" ".repeat(titlePadding) + fullTitle);

        // Footer line
        System.out.println("╚" + "═".repeat(bannerWidth) + "╝\n");
    }

    /**
     * Plays all dialogues in sequence for this chapter
     * Monitors for ENTER key press to skip delays
     */
    public void playDialogues() {
        boolean skipDelay = false;

        // Create a thread to check for ENTER press
        Thread inputThread = new Thread(() -> {
            try {
                System.in.read();
            } catch (Exception e) {
                // Ignore exceptions
            }
        });
        inputThread.setDaemon(true);
        inputThread.start();

        for (int i = 0; i < dialogues.size(); i++) {
            // Check if ENTER was pressed by checking if input thread is still alive
            if (!inputThread.isAlive()) {
                skipDelay = true;
                if (i == 0) {
                    System.out.println("\n[Skipping delays - displaying all dialogue...]\n");
                }
            }

            dialogues.get(i).display(skipDelay);
        }
    }

    // Getters and Setters
    public int getChapterNumber() {
        return chapterNumber;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public String getChapterDescription() {
        return chapterDescription;
    }

    public List<DialogueNode> getDialogues() {
        return dialogues;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}