package Items;

import Entities.Characters.Character;
import StatusEffects.MagicalDamageBoost;
import StatusEffects.PhysicalDamageBoost;
import TextFormat.ColorUtil;

import java.text.DecimalFormat;

public class MagicalDamagePotion extends Consumable{
    double magicalDamageAmount;

    public MagicalDamagePotion(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, double magicalDamageAmount){
        super(itemId, name, description, maxStack, quantity, value, rarity, true, false);
        this.magicalDamageAmount = magicalDamageAmount;
    }

    @Override
    public void use(Character player){
        if(!hasConsumed){
            MagicalDamageBoost effect = new MagicalDamageBoost(magicalDamageAmount);
            effect.applyEffect(player);
            player.addStatusEffect(effect);
            System.out.println("✨ " + player.getName() + "'s magical damage increased by " + (magicalDamageAmount * 100) + "%!");
            setQuantity(getQuantity() - 1);
            hasConsumed = true;
        }else{
            System.out.println("You can only consume this potion once!");
        }
    }

    @Override
    public void displayInfo(){
        DecimalFormat df = new DecimalFormat("####");
        int totalWidth = 54;
        String nameLine = name + " (x" + getQuantity() + ") ";
        String visibleNameLine = nameLine.replaceAll("\u001B\\[[;\\d]*m", "");

        String damageLine = "> Increases magical damage by " + df.format(magicalDamageAmount * 100) + "%";

        //padding for each line
        int namePadding = totalWidth - visibleNameLine.length() - 4;
        int damagePadding = totalWidth - damageLine.length() - 3;

        // Ensure padding is at least 1
        namePadding = Math.max(1, namePadding);
        damagePadding = Math.max(1, damagePadding);

        System.out.println(ColorUtil.brightCyanBold("┌─ ") + nameLine + ColorUtil.brightCyanBold("─".repeat(namePadding)) + ColorUtil.brightCyanBold("┐"));
        System.out.println(ColorUtil.brightCyanBold("│ " + damageLine + " ".repeat(damagePadding) + "│"));
        System.out.println(ColorUtil.brightCyanBold("└" + "─".repeat(totalWidth - 2) + "┘"));
    }
}