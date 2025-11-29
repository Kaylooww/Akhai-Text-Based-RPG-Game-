package Items;

import Entities.Characters.Character;
import StatusEffects.MagicalDamageBoost;
import StatusEffects.SpeedBoost;
import TextFormat.ColorUtil;

public class SpeedPotion extends Consumable{
    int speedAmount;

    public SpeedPotion(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, int speedAmount){
        super(itemId, name, description, maxStack, quantity, value, rarity, true, false);
        this.speedAmount = speedAmount;
    }

    @Override
    public void use(Character player){
        if(!hasConsumed){
            SpeedBoost effect = new SpeedBoost(speedAmount);
            effect.applyEffect(player);
            player.addStatusEffect(effect);
            System.out.println("⚡ " + player.getName() + "'s speed increased by " + speedAmount + "!");
            setQuantity(getQuantity() - 1);
            hasConsumed = true;
        }else{
            System.out.println("You can only consume this potion once!");
        }
    }

    @Override
    public void displayInfo(){
        int totalWidth = 54;
        String nameLine = name + " (x" + getQuantity() + ") ";
        String visibleNameLine = nameLine.replaceAll("\u001B\\[[;\\d]*m", "");

        String speedLine = "> Increases speed by " + speedAmount;

        //padding for each line
        int namePadding = totalWidth - visibleNameLine.length() - 4;
        int speedPadding = totalWidth - speedLine.length() - 3;

        // Ensure padding is at least 1
        namePadding = Math.max(1, namePadding);
        speedPadding = Math.max(1, speedPadding);

        System.out.println(ColorUtil.brightCyanBold("┌─ ") + nameLine + ColorUtil.brightCyanBold("─".repeat(namePadding)) + ColorUtil.brightCyanBold("┐"));
        System.out.println(ColorUtil.brightCyanBold("│ " + speedLine + " ".repeat(speedPadding) + "│"));
        System.out.println(ColorUtil.brightCyanBold("└" + "─".repeat(totalWidth - 2) + "┘"));
    }
}