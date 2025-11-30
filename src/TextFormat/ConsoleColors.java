package TextFormat;

public class ConsoleColors {
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE
    public static final String ORANGE = "\u001B[38;2;255;165;0m"; // ORANGE
    public static final String BLUE_GREEN = "\u001B[38;2;0;165;165m"; // BLUE_GREEN

    // Dark Colors
    public static final String DARK_RED = "\033[0;31;2m";     // DARK RED
    public static final String DARK_GREEN = "\033[0;32;2m";   // DARK GREEN
    public static final String DARK_YELLOW = "\033[0;33;2m";  // DARK YELLOW
    public static final String DARK_BLUE = "\033[0;34;2m";    // DARK BLUE
    public static final String DARK_PURPLE = "\033[0;35;2m";  // DARK PURPLE
    public static final String DARK_CYAN = "\033[0;36;2m";    // DARK CYAN
    public static final String DARK_WHITE = "\033[0;37;2m";   // DARK WHITE
    public static final String DARK_ORANGE = "\u001B[38;2;165;85;0m"; // DARK ORANGE
    public static final String DARK_BLUE_GREEN = "\u001B[38;2;0;85;85m"; // DARK BLUE_GREEN

    // Bold (brighter versions)
    public static final String BLACK_BOLD = "\033[1;30m";   // BLACK
    public static final String RED_BOLD = "\033[1;31m";     // RED
    public static final String GREEN_BOLD = "\033[1;32m";   // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m";  // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";    // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m";  // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";    // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";   // WHITE
    public static final String BLUE_GREEN_BOLD = "\u001B[1;38;2;0;165;165m"; // BLUE_GREEN BOLD

    // Bright Colors (even brighter - 90-97 range)
    public static final String BLACK_BRIGHT = "\033[0;90m";   // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";     // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";   // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m";  // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";    // BLUE - This is your brighter blue!
    public static final String PURPLE_BRIGHT = "\033[0;95m";  // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";    // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";   // WHITE
    public static final String BLUE_GREEN_BRIGHT = "\u001B[0;38;2;0;200;200m"; // BRIGHTER BLUE_GREEN

    // Bold Bright Colors
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m";   // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";     // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m";   // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";  // YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";    // BLUE BOLD BRIGHT - Even brighter!
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";  // PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";    // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m";   // WHITE
    public static final String BLUE_GREEN_BOLD_BRIGHT = "\u001B[1;38;2;0;230;230m"; // BOLD BRIGHT BLUE_GREEN

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String BLUE_GREEN_BACKGROUND = "\u001B[48;2;0;165;165m"; // BLUE_GREEN BACKGROUND

    // Dark Backgrounds
    public static final String DARK_RED_BACKGROUND = "\033[41;2m";    // DARK RED BACKGROUND
    public static final String DARK_GREEN_BACKGROUND = "\033[42;2m";  // DARK GREEN BACKGROUND
    public static final String DARK_BLUE_BACKGROUND = "\033[44;2m";   // DARK BLUE BACKGROUND
    public static final String DARK_PURPLE_BACKGROUND = "\033[45;2m"; // DARK PURPLE BACKGROUND
    public static final String DARK_CYAN_BACKGROUND = "\033[46;2m";   // DARK CYAN BACKGROUND
    // ... and more background colors
}