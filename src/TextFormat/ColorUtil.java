package TextFormat;

public class ColorUtil {
    public static String colorize(String text, String color) {
        return color + text + ConsoleColors.RESET;
    }

    // normal colors
    public static String red(String text) {
        return ConsoleColors.RED + text + ConsoleColors.RESET;
    }

    public static String green(String text) {
        return ConsoleColors.GREEN + text + ConsoleColors.RESET;
    }

    public static String yellow(String text) {
        return ConsoleColors.YELLOW + text + ConsoleColors.RESET;
    }

    public static String blue(String text) {
        return ConsoleColors.BLUE + text + ConsoleColors.RESET;
    }

    public static String purple(String text) {
        return ConsoleColors.PURPLE + text + ConsoleColors.RESET;
    }

    public static String cyan(String text) {
        return ConsoleColors.CYAN + text + ConsoleColors.RESET;
    }

    // bright versions
    public static String blueBright(String text) {
        return ConsoleColors.BLUE_BRIGHT + text + ConsoleColors.RESET;
    }

    public static String yellowBright(String text) {
        return ConsoleColors.YELLOW_BRIGHT + text + ConsoleColors.RESET;
    }

    // Bold versions
    public static String redBold(String text) {
        return ConsoleColors.RED_BOLD + text + ConsoleColors.RESET;
    }

    public static String yellowBold(String text) {
        return ConsoleColors.YELLOW_BOLD + text + ConsoleColors.RESET;
    }

    public static String cyanBold(String text) {
        return ConsoleColors.CYAN_BOLD + text + ConsoleColors.RESET;
    }

    public static String greenBold(String text) {
        return ConsoleColors.GREEN_BOLD + text + ConsoleColors.RESET;
    }

    // Bright bold
    public static String brightBlueBold(String text) {
        return ConsoleColors.BLUE_BOLD_BRIGHT + text + ConsoleColors.RESET;
    }

    public static String brightCyanBold(String text) {
        return ConsoleColors.CYAN_BOLD_BRIGHT + text + ConsoleColors.RESET;
    }

    public static String brightYellowBold(String text) {
        return ConsoleColors.YELLOW_BOLD_BRIGHT + text + ConsoleColors.RESET;
    }

    public static String brightPurpleBold(String text) {
        return ConsoleColors.PURPLE_BOLD_BRIGHT + text + ConsoleColors.RESET;
    }

    public static String brightRedBold(String text) {
        return ConsoleColors.RED_BOLD_BRIGHT + text + ConsoleColors.RESET;
    }
}
