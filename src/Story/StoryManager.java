package Story;

import Story.DialogueNode.DialogueType;
import TextFormat.ColorUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the complete story of Akhai
 * Initializes all chapters with dialogues
 * Controls story progression and playback
 */
public class StoryManager {
    private List<Chapter> chapters;
    private int currentChapter;

    /**
     * Constructor initializes the complete story
     */
    public StoryManager() {
        this.chapters = new ArrayList<>();
        this.currentChapter = 1;
        initializeStory();
    }

    /**
     * Initializes all 6 chapters with complete dialogues
     */
    private void initializeStory() {
        initChapter1();
        initChapter2();
        initChapter3();
        initChapter4();
        initChapter5();
        initChapter6();
    }

    /**
     * Initialize Chapter 1: The Summoned
     */
    private void initChapter1() {
        Chapter ch1 = new Chapter(1, "The Summoned",
                "The last thing you remembered was the screech of tires. Then, blinding green light.");

        ch1.addDialogue(new DialogueNode("",
                "The last thing I remembered was the screech of tires. Then, blinding green light.",
                DialogueType.NARRATION));

        ch1.addDialogue(new DialogueNode("",
                "I awoke on a bed of soft moss, under a sky with two moons. A world of impossible flora and fauna stretched before me.",
                DialogueType.NARRATION));

        ch1.addDialogue(new DialogueNode("You",
                "Where... where am I? This can't be real!",
                DialogueType.THOUGHT));

        ch1.addDialogue(new DialogueNode("",
                "Panic set in. I was no longer on Earth. As I stumbled through the vibrant, alien woods, a translucent, spectral figure materialized.",
                DialogueType.NARRATION));

        ch1.addDialogue(new DialogueNode("",
                "He was an old man with sad, kind eyes.",
                DialogueType.NARRATION));

        ch1.addDialogue(new DialogueNode("Khai",
                "I am Khai. I have brought you here to atone for my greatest failure.",
                DialogueType.KHAI_SPEECH));

        ch1.addDialogue(new DialogueNode("Khai",
                "You must find what I lost: the Akhai.",
                DialogueType.KHAI_SPEECH));

        ch1.addDialogue(new DialogueNode("You",
                "The Akhai? What is that? And why me?!",
                DialogueType.CHARACTER_SPEECH));

        ch1.addDialogue(new DialogueNode("Khai",
                "All will be revealed in time. For now, you must trust me. Our journey begins.",
                DialogueType.KHAI_SPEECH));

        ch1.addDialogue(new DialogueNode("",
                "⚠️ OBJECTIVE: Follow Khai and learn about the Akhai",
                DialogueType.SYSTEM_MESSAGE));

        chapters.add(ch1);
    }

    /**
     * Initialize Chapter 2: The Demon King's Grief
     */
    private void initChapter2() {
        Chapter ch2 = new Chapter(2, "The Demon King's Grief",
                "Khai reveals the tragic tale of his disciple, Din, and the beloved he lost.");

        ch2.addDialogue(new DialogueNode("",
                "Khai became my guide, a ghost tethered to this world by regret. As we traveled, he told me of his disciple, Din.",
                DialogueType.NARRATION));

        ch2.addDialogue(new DialogueNode("Khai",
                "He was once full of laughter. Let me show you...",
                DialogueType.KHAI_SPEECH));

        ch2.addDialogue(new DialogueNode("",
                "Khai conjured a vision of a cheerful young man with his beloved, Ellie. Their happiness was a stark contrast to the tale that followed.",
                DialogueType.NARRATION));

        ch2.addDialogue(new DialogueNode("You",
                "They look so happy together...",
                DialogueType.THOUGHT));

        ch2.addDialogue(new DialogueNode("Khai",
                "Yes... but happiness is fragile. Then came the day the humans attacked. They feared mages, called us evil.",
                DialogueType.KHAI_SPEECH));

        ch2.addDialogue(new DialogueNode("Khai",
                "They burned our town to the ground. Ellie... she perished in the flames.",
                DialogueType.KHAI_SPEECH));

        ch2.addDialogue(new DialogueNode("",
                "Khai showed me the smoking ruins of their town, destroyed by fearful humans. He showed me Din's return, his joy turning to ashes.",
                DialogueType.NARRATION));

        ch2.addDialogue(new DialogueNode("",
                "I could see Din's face in the vision, twisted with grief and rage as he discovered what remained.",
                DialogueType.NARRATION));

        ch2.addDialogue(new DialogueNode("Khai",
                "His heart hardened into a core of pure rage. I failed to save him from his darkness.",
                DialogueType.KHAI_SPEECH));

        ch2.addDialogue(new DialogueNode("Khai",
                "He now calls himself the Demon King, and he seeks the Akhai.",
                DialogueType.KHAI_SPEECH));

        ch2.addDialogue(new DialogueNode("You",
                "So we're racing against him? What does he want with this Akhai?",
                DialogueType.CHARACTER_SPEECH));

        ch2.addDialogue(new DialogueNode("Khai",
                "That... is what you must understand next.",
                DialogueType.KHAI_SPEECH));

        ch2.addDialogue(new DialogueNode("",
                "⚠️ OBJECTIVE: Understand Din's motivation and continue the journey",
                DialogueType.SYSTEM_MESSAGE));

        chapters.add(ch2);
    }

    /**
     * Initialize Chapter 3: The Terrible Price
     */
    private void initChapter3() {
        Chapter ch3 = new Chapter(3, "The Terrible Price",
                "The true horror of the Akhai's power is revealed.");

        ch3.addDialogue(new DialogueNode("You",
                "Why does he want it? What is the Akhai exactly?",
                DialogueType.CHARACTER_SPEECH));

        ch3.addDialogue(new DialogueNode("",
                "We sheltered from a crimson rain. Khai's form flickered with ancient sorrow.",
                DialogueType.NARRATION));

        ch3.addDialogue(new DialogueNode("Khai",
                "The Akhai is a key. It can transport its user to any location they desire.",
                DialogueType.KHAI_SPEECH));

        ch3.addDialogue(new DialogueNode("You",
                "That sounds useful! Why is that dangerous?",
                DialogueType.CHARACTER_SPEECH));

        ch3.addDialogue(new DialogueNode("Khai",
                "Because... to cross between worlds, to move from one reality to another...",
                DialogueType.KHAI_SPEECH));

        ch3.addDialogue(new DialogueNode("Khai",
                "The price is the utter annihilation of the world you leave behind.",
                DialogueType.KHAI_SPEECH));

        ch3.addDialogue(new DialogueNode("",
                "The horror of it settled in my bones. My hands trembled.",
                DialogueType.NARRATION));

        ch3.addDialogue(new DialogueNode("You",
                "He... he wants to destroy this entire world?!",
                DialogueType.THOUGHT));

        ch3.addDialogue(new DialogueNode("Khai",
                "Din doesn't want to use it to travel. He wants to trigger the cost deliberately.",
                DialogueType.KHAI_SPEECH));

        ch3.addDialogue(new DialogueNode("Khai",
                "He would obliterate this entire world in a cataclysmic toll, hoping to find a reality where Ellie could be revived.",
                DialogueType.KHAI_SPEECH));

        ch3.addDialogue(new DialogueNode("You",
                "We have to stop him. We can't let him destroy everything!",
                DialogueType.CHARACTER_SPEECH));

        ch3.addDialogue(new DialogueNode("Khai",
                "Yes. That is why I brought you here. We must reach the Akhai before Din does.",
                DialogueType.KHAI_SPEECH));

        ch3.addDialogue(new DialogueNode("",
                "⚠️ OBJECTIVE: Stop Din before he reaches the Akhai",
                DialogueType.SYSTEM_MESSAGE));

        chapters.add(ch3);
    }

    /**
     * Initialize Chapter 4: The Race to the Summit
     */
    private void initChapter4() {
        Chapter ch4 = new Chapter(4, "The Race to the Summit",
                "A desperate race against time and Din's corrupted army.");

        ch4.addDialogue(new DialogueNode("",
                "Our quest became a desperate race. Din's legions of corrupted beasts and fallen mages scoured the land, leaving a trail of destruction.",
                DialogueType.NARRATION));

        ch4.addDialogue(new DialogueNode("",
                "Whispers spoke of his army converging on the Sky-Splitter Peak, where the Akhai was said to be hidden.",
                DialogueType.NARRATION));

        ch4.addDialogue(new DialogueNode("Khai",
                "We must hurry! If Din reaches the peak before us, all will be lost!",
                DialogueType.KHAI_SPEECH));

        ch4.addDialogue(new DialogueNode("You",
                "I'm going as fast as I can! These cliffs are impossible!",
                DialogueType.CHARACTER_SPEECH));

        ch4.addDialogue(new DialogueNode("",
                "We fought through blighted forests and scaled treacherous cliffs, always one step behind.",
                DialogueType.NARRATION));

        ch4.addDialogue(new DialogueNode("",
                "The air grew thin and cold. My lungs burned with every breath.",
                DialogueType.NARRATION));

        ch4.addDialogue(new DialogueNode("Khai",
                "Look! There! At the summit!",
                DialogueType.KHAI_SPEECH));

        ch4.addDialogue(new DialogueNode("",
                "At the peak, we saw him—Din, his face a mask of sorrow and fury, standing before a simple stone altar.",
                DialogueType.NARRATION));

        ch4.addDialogue(new DialogueNode("",
                "Upon it rested a small, unadorned compass: the Akhai.",
                DialogueType.NARRATION));

        ch4.addDialogue(new DialogueNode("You",
                "We made it... but so did he!",
                DialogueType.THOUGHT));

        ch4.addDialogue(new DialogueNode("",
                "⚠️ OBJECTIVE: Confront Din at the summit",
                DialogueType.SYSTEM_MESSAGE));

        chapters.add(ch4);
    }

    /**
     * Initialize Chapter 5: The Heart of the Storm
     */
    private void initChapter5() {
        Chapter ch5 = new Chapter(5, "The Heart of the Storm",
                "The final confrontation with the Demon King begins.");

        ch5.addDialogue(new DialogueNode("Din",
                "Step aside, old master. I will have my peace.",
                DialogueType.DIN_SPEECH));

        ch5.addDialogue(new DialogueNode("Khai",
                "Din, please! This is not the way! Ellie would not—",
                DialogueType.KHAI_SPEECH));

        ch5.addDialogue(new DialogueNode("Din",
                "You know NOTHING of what she would want! You weren't there!",
                DialogueType.DIN_SPEECH));

        ch5.addDialogue(new DialogueNode("",
                "I stood my ground, a simple Earthling against a demigod of grief.",
                DialogueType.NARRATION));

        ch5.addDialogue(new DialogueNode("You",
                "This isn't what Ellie would have wanted!",
                DialogueType.CHARACTER_SPEECH));

        ch5.addDialogue(new DialogueNode("",
                "Her name was a key that unlocked a fresh wave of agony in his eyes.",
                DialogueType.NARRATION));

        ch5.addDialogue(new DialogueNode("Din",
                "DO NOT SPEAK HER NAME!",
                DialogueType.DIN_SPEECH));

        ch5.addDialogue(new DialogueNode("",
                "The storm of his magic erupted. Lightning crackled, the ground shook.",
                DialogueType.NARRATION));

        ch5.addDialogue(new DialogueNode("",
                "It was not a battle of strength, but of will.",
                DialogueType.NARRATION));

        ch5.addDialogue(new DialogueNode("Khai",
                "I lend you my power! Stand strong!",
                DialogueType.KHAI_SPEECH));

        ch5.addDialogue(new DialogueNode("",
                "Khai created shields of light against Din's lances of shadow.",
                DialogueType.NARRATION));

        ch5.addDialogue(new DialogueNode("",
                "But even with Khai's help, we were losing. Din's power was overwhelming.",
                DialogueType.NARRATION));

        ch5.addDialogue(new DialogueNode("You",
                "We can't hold much longer!",
                DialogueType.CHARACTER_SPEECH));

        ch5.addDialogue(new DialogueNode("",
                "⚠️ OBJECTIVE: Survive Din's onslaught and reach the Akhai",
                DialogueType.SYSTEM_MESSAGE));

        chapters.add(ch5);
    }

    /**
     * Initialize Chapter 6: A New Purpose
     * also the Ending
     */
    private void initChapter6() {
        Chapter ch6 = new Chapter(6, "A New Purpose",
                "The final choice that will determine the fate of two worlds.");

        ch6.addDialogue(new DialogueNode("",
                "As Din's power overwhelmed us, Khai made his choice.",
                DialogueType.NARRATION));

        ch6.addDialogue(new DialogueNode("Khai",
                "I am sorry, my boy... Forgive me for bringing you into this.",
                DialogueType.KHAI_SPEECH));

        ch6.addDialogue(new DialogueNode("You",
                "Khai? What are you—",
                DialogueType.CHARACTER_SPEECH));

        ch6.addDialogue(new DialogueNode("",
                "His spirit flared, not with attack, but with a final, immense memory.",
                DialogueType.NARRATION));

        ch6.addDialogue(new DialogueNode("",
                "He projected the pure, untainted memory of Ellie's laugh, her smile, the love she had for the man Din used to be.",
                DialogueType.NARRATION));

        ch6.addDialogue(new DialogueNode("Din",
                "Ellie... I... I can see her...",
                DialogueType.DIN_SPEECH));

        ch6.addDialogue(new DialogueNode("",
                "Din faltered, the hatred in his eyes cracking. Tears streamed down his face.",
                DialogueType.NARRATION));

        ch6.addDialogue(new DialogueNode("",
                "In that moment of vulnerability, I lunged, not for Din, but for the Akhai on the altar.",
                DialogueType.NARRATION));

        ch6.addDialogue(new DialogueNode("",
                "My hand closed around the cool metal of the compass.",
                DialogueType.NARRATION));

        ch6.addDialogue(new DialogueNode("You",
                "I held the power to go home, to save myself and doom this world...",
                DialogueType.THOUGHT));

        ch6.addDialogue(new DialogueNode("",
                "I looked at Din, now weeping on his knees, and at Khai's fading spirit.",
                DialogueType.NARRATION));

        ch6.addDialogue(new DialogueNode("You",
                "No... I won't let anyone else use this to destroy everything.",
                DialogueType.CHARACTER_SPEECH));

        ch6.addDialogue(new DialogueNode("",
                "I threw the Akhai to the ground, shattering it into a thousand harmless shards.",
                DialogueType.NARRATION));

        ch6.addDialogue(new DialogueNode("Din",
                "What... what have you done...?",
                DialogueType.DIN_SPEECH));

        ch6.addDialogue(new DialogueNode("You",
                "The way home is gone... but this world is safe.",
                DialogueType.CHARACTER_SPEECH));

        ch6.addDialogue(new DialogueNode("Khai",
                "You have become... my true successor. You chose compassion over power, sacrifice over selfishness.",
                DialogueType.KHAI_SPEECH));

        ch6.addDialogue(new DialogueNode("Khai",
                "Thank you... and farewell...",
                DialogueType.KHAI_SPEECH));

        ch6.addDialogue(new DialogueNode("",
                "Khai's spirit began to fade, finally at peace.",
                DialogueType.NARRATION));

        ch6.addDialogue(new DialogueNode("Din",
                "Ellie... I'm so sorry... I almost...",
                DialogueType.DIN_SPEECH));

        ch6.addDialogue(new DialogueNode("",
                "My isekai story was over. My new life, as Khai's true successor, had just begun...",
                DialogueType.NARRATION));

        ch6.addDialogue(new DialogueNode("",
                "🎉 CONGRATULATIONS! YOU HAVE COMPLETED THE STORY OF AKHAI! 🎉",
                DialogueType.SYSTEM_MESSAGE));

        chapters.add(ch6);
    }

    /**
     * Plays the specified chapter including intro and all dialogues
     * Allows user to press ENTER to skip delays and display all dialogue immediately
     * @param chapterNumber The chapter to play (1-6)
     */
    public void playChapter(int chapterNumber) {
        if (chapterNumber < 1 || chapterNumber > chapters.size()) {
            System.out.println("Invalid chapter number!");
            return;
        }

        Chapter chapter = chapters.get(chapterNumber - 1);
        chapter.displayChapterIntro();

        System.out.println(ColorUtil.brightCyanBold("[Press ENTER at any time to skip delays and display all dialogue]\n"));

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        chapter.playDialogues();
        chapter.setCompleted(true);

        System.out.println(ColorUtil.brightYellowBold("\n✅ Chapter " + chapterNumber + " completed!"));
        System.out.println(ColorUtil.brightCyanBold("[Press ENTER to continue...]"));

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        scanner.nextLine();
    }

    /**
     * Gets a specific chapter
     * @param chapterNumber The chapter number to retrieve
     * @return The Chapter object or null if invalid
     */
    public Chapter getChapter(int chapterNumber) {
        if (chapterNumber < 1 || chapterNumber > chapters.size()) {
            return null;
        }
        return chapters.get(chapterNumber - 1);
    }

    /**
     * Gets the current chapter number
     * @return Current chapter number
     */
    public int getCurrentChapter() {
        return currentChapter;
    }

    /**
     * Sets the current chapter number
     * @param chapterNumber New chapter number
     */
    public void setCurrentChapter(int chapterNumber) {
        this.currentChapter = chapterNumber;
    }

    /**
     * Gets all chapters
     * @return List of all chapters
     */
    public List<Chapter> getChapters() {
        return chapters;
    }

    /**
     * Checks if the entire story is complete
     * @return true if all chapters are completed
     */
    public boolean isStoryComplete() {
        for (Chapter chapter : chapters) {
            if (!chapter.isCompleted()) {
                return false;
            }
        }
        return true;
    }
}