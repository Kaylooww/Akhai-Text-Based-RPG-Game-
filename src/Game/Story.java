package Game;

import TextFormat.ColorUtil;

public class Story {
    private Game game;

    public Story(Game game) {
        this.game = game;
    }

    public void displayChapterStory(int chapter) {
        System.out.println("\n" + ColorUtil.cyan("=".repeat(60)));
        System.out.println(ColorUtil.yellow("CHAPTER " + chapter + " - " + getChapterTitle(chapter)));
        System.out.println(ColorUtil.cyan("=".repeat(60)));

        switch(chapter) {
            case 1:
                displayChapter1();
                break;
            case 2:
                displayChapter2();
                break;
            case 3:
                displayChapter3();
                break;
            case 4:
                displayChapter4();
                break;
            case 5:
                displayChapter5();
                break;
            case 6:
                displayChapter6();
                break;
        }

        System.out.println(ColorUtil.cyan("=".repeat(60)));
    }

    private String getChapterTitle(int chapter) {
        switch(chapter) {
            case 1: return "The Whispering Crossroads";
            case 2: return "Fungal Lumewood Forest";
            case 3: return "Ruins of a Fallen Star";
            case 4: return "Crystal Caverns";
            case 5: return "Root of All Worlds";
            case 6: return "Final Confrontation";
            default: return "Unknown Journey";
        }
    }
    //Need Story
    private void displayChapter1() {
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
    }

    private void displayChapter2() {
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
    }

    private void displayChapter3() {
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
    }

    private void displayChapter4() {
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
    }

    private void displayChapter5() {
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
    }

    private void displayChapter6() {
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
        printDelay("", 1500);
        printDelay("", 2000);
    }

    public void displayBossIntroduction(int chapter) {
        System.out.println("\n" + ColorUtil.red("!" .repeat(50)));
        System.out.println(ColorUtil.red("BOSS BATTLE INCOMING!"));
        System.out.println(ColorUtil.red("!" .repeat(50)));

        switch(chapter) {
            case 1: //need boss introduction
                printDelay("", 1500);
                printDelay("", 1000);
                break;
            case 2: //need boss introduction
                printDelay("", 1500);
                printDelay("", 1000);
                break;
            case 3: //need boss introduction
                printDelay("", 1500);
                printDelay("", 1000);
                break;
            case 4: //need boss introduction
                printDelay("", 1500);
                printDelay("", 1000);
                break;
            case 5: //need boss introduction
                printDelay("", 1500);
                printDelay("", 1000);
                break;
            case 6: //need boss introduction
                printDelay("", 1500);
                printDelay("", 1000);
                break;
        }
    }

    public void displayVictoryDialogue(int chapter) {
        System.out.println("\n" + ColorUtil.green("✓".repeat(50)));
        System.out.println(ColorUtil.green("VICTORY ACHIEVED!"));
        System.out.println(ColorUtil.green("✓".repeat(50)));

        switch(chapter) {
            case 1: //need dialogue
                printDelay("", 1500);
                printDelay("", 1000);
                break;
            case 2: //need dialogue
                printDelay("", 1500);
                printDelay("", 1000);
                break;
            case 3: //need dialogue
                printDelay("", 1500);
                printDelay("", 1000);
                break;
            case 4: //need dialogue
                printDelay("", 1500);
                printDelay("", 1000);
                break;
            case 5: //need dialogue
                printDelay("", 1500);
                printDelay("", 1000);
                break;
            case 6: //need dialogue
                printDelay("Khai: You did it! Zed is defeated!", 2000);
                printDelay("The balance between worlds is preserved.", 1500);
                printDelay("The gateway is yours to command now.", 1500);
                printDelay("You can finally go home... or wherever you wish.", 2000);
                break;
        }
    }

    public void displayFinalEnding() {
        System.out.println("\n" + ColorUtil.yellow("☆".repeat(60)));
        System.out.println(ColorUtil.yellow("CONGRATULATIONS!"));
        System.out.println(ColorUtil.yellow("☆".repeat(60)));

        printDelay("As you stand before the World Tree, its branches shimmer with infinite possibilities.", 2000);
        printDelay("Khai: You've saved Akhai.", 1500);
        printDelay("The power to return home is now in your hands.", 2000);
        printDelay("But know this - you'll always have a place here in Akhai.", 1500);
        printDelay("...The tree's light envelops you, showing you paths to countless worlds...", 2000);
        printDelay("Your journey through Akhai may be ending, but new adventures await.", 1500);
        printDelay("Where will your path lead next?", 2000);
    }

    private void printDelay(String text, int delay) {
        try {
            // Simulate typing effect for longer dialogues
            if (text.length() > 30) {
                for (char c : text.toCharArray()) {
                    System.out.print(c);
                    Thread.sleep(30);
                }
                System.out.println();
            } else {
                System.out.println(text);
            }
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(text);
        }
    }
}