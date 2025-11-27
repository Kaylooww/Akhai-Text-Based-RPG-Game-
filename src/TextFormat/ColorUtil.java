package TextFormat;

public class ColorUtil {
    public static String colorize(String text, String color) {
        return color + text + ConsoleColors.RESET;
    }

    // Convenience methods
    public static String red(String text) {
        return ConsoleColors.RED + text + ConsoleColors.RESET;
    }

    public static String green(String text) {
        return ConsoleColors.GREEN + text + ConsoleColors.RESET;
    }

    public static String yellow(String text) {
        return ConsoleColors.YELLOW + text + ConsoleColors.RESET;
    }

    public static String yellowBold(String text) {
        return ConsoleColors.YELLOW_BOLD + text + ConsoleColors.RESET;
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

    public static String cyanBold(String text) {
        return ConsoleColors.CYAN_BOLD + text + ConsoleColors.RESET;
    }

    // Bold versions
    public static String redBold(String text) {
        return ConsoleColors.RED_BOLD + text + ConsoleColors.RESET;
    }

    public static String greenBold(String text) {
        return ConsoleColors.GREEN_BOLD + text + ConsoleColors.RESET;
    }
}
