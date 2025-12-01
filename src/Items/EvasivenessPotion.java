package Items;

import Entities.Characters.Character;
import StatusEffects.EvasivenessBoost;
import StatusEffects.SpeedBoost;
import TextFormat.ColorUtil;

import java.text.DecimalFormat;

public class EvasivenessPotion extends Consumable{
    double accuracyReduction;

    public EvasivenessPotion(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, double accuracyReduction) {
        super(itemId, name, description, maxStack, quantity, value, rarity, true, false);
        this.accuracyReduction = accuracyReduction;
    }

    @Override
    public void use(Character player){
        if(hasConsumed == false){
            EvasivenessBoost effect = new EvasivenessBoost(accuracyReduction);
            player.addStatusEffect(effect);
            System.out.println(ColorUtil.brightPurpleBold("🌪️ " + player.getName() + "'s evasiveness increased! Enemy accuracy reduced by " + (accuracyReduction * 100) + "%!"));
            setQuantity(getQuantity() - 1);
            hasConsumed = true;
        }else{
            System.out.println(ColorUtil.brightRedBold("\t\tYou can only consume this potion once!"));
        }
    }

    @Override
    public void displayInfo(){
        DecimalFormat df = new DecimalFormat("####");
        int totalWidth = 54;
        String nameLine = name + " (x" + getQuantity() + ") ";
        String visibleNameLine = nameLine.replaceAll("\u001B\\[[;\\d]*m", "");

        String accuracyLine = "> Reduces enemy accuracy by " + df.format(accuracyReduction * 100) + "%";

        //padding for each line
        int namePadding = totalWidth - visibleNameLine.length() - 4;
        int accuracyPadding = totalWidth - accuracyLine.length() - 3;

        // Ensure padding is at least 1
        namePadding = Math.max(1, namePadding);
        accuracyPadding = Math.max(1, accuracyPadding);

        System.out.println(ColorUtil.brightCyanBold("┌─ ") + nameLine + ColorUtil.brightCyanBold("─".repeat(namePadding)) + ColorUtil.brightCyanBold("┐"));
        System.out.println(ColorUtil.brightCyanBold("│ " + accuracyLine + " ".repeat(accuracyPadding) + "│"));
        System.out.println(ColorUtil.brightCyanBold("└" + "─".repeat(totalWidth - 2) + "┘"));
    }
}