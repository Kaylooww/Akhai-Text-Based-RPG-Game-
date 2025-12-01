package Items;

import Entities.Characters.Character;
import StatusEffects.PhysicalDamageBoost;
import TextFormat.ColorUtil;

import java.text.DecimalFormat;

public class PhysicalDamagePotion extends Consumable{
    double physicalDamageAmount;

    public PhysicalDamagePotion(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, double physicalDamageAmount){
        super(itemId, name, description, maxStack, quantity, value, rarity, true, false);
        this.physicalDamageAmount = physicalDamageAmount;
    }

    @Override
    public void use(Character player){
        if(!hasConsumed){
            PhysicalDamageBoost effect = new PhysicalDamageBoost(physicalDamageAmount);
            effect.applyEffect(player);
            player.addStatusEffect(effect);
            System.out.println(ColorUtil.brightYellowBold("💪 " + player.getName() + "'s physical damage increased by " + (physicalDamageAmount * 100) + "%!"));
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

        String damageLine = "> Increases physical damage by " + df.format(physicalDamageAmount * 100) + "%";

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