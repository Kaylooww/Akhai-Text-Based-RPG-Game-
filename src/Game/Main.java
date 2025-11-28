package Game;

import AsciiArts.ASCII_ART;

public class Main {
    public static void main(String[] args) {
        //Starting the game
        //TODO add display ascii art during the start of the game
        //TODO add display during handleDefeat() method
        //TODO add display during handleVictory() method
        //Mb gaw ari lang nako i butang kay gipa dali ko I search lang na nga method mao mana ila mga pangan
//        Game game = new Game();
//        game.initializeGame();
//        game.startGame();

        ASCII_ART asciiArt = new ASCII_ART();

        //Start menu design
        asciiArt.startMenu();

        //Class arts
        //TODO implement these when choosing what class you want to play with (in the class file)
        asciiArt.hawkseyeArt();
        asciiArt.runeCasterArt();
        asciiArt.shinobiArt();

        //Character in the original world
        asciiArt.sageKhai();
        asciiArt.finalBoss();
    }
}