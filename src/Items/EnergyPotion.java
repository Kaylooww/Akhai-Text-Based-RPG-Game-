package Items;

import Entities.Characters.Character;
import TextFormat.ColorUtil;

public class EnergyPotion extends Consumable{
    int energyAmount;

    public EnergyPotion(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, int energyAmount){
        super(itemId, name, description, maxStack, quantity, value, rarity, true, false);
        this.energyAmount = energyAmount;
    }

    @Override
    public void use(Character player){
        if(player.getEnergy() < player.getMaxEnergy()){
            player.setEnergy(player.getEnergy() + energyAmount);
            if(player.getEnergy() >= player.getMaxEnergy()){
                player.setEnergy(player.getMaxEnergy());
            }
            System.out.println(ColorUtil.brightCyanBold("⚡ " + player.getName() + " restored " + energyAmount + " energy!"));
            setQuantity(getQuantity() - 1);
        }else{
            System.out.println(ColorUtil.brightRedBold("Energy is full!"));
        }
    }

    @Override
    public void displayInfo(){
        int totalWidth = 54;
        String nameLine = name + " (x" + getQuantity() + ") ";
        String visibleNameLine = nameLine.replaceAll("\u001B\\[[;\\d]*m", "");

        String energyLine = "> Restores " + energyAmount + " energy";

        //padding for each line
        int namePadding = totalWidth - visibleNameLine.length() - 4;
        int energyPadding = totalWidth - energyLine.length() - 3;

        // Ensure padding is at least 1
        namePadding = Math.max(1, namePadding);
        energyPadding = Math.max(1, energyPadding);

        System.out.println(ColorUtil.brightCyanBold("┌─ ") + nameLine + ColorUtil.brightCyanBold("─".repeat(namePadding)) + ColorUtil.brightCyanBold("┐"));
        System.out.println(ColorUtil.brightCyanBold("│ " + energyLine + " ".repeat(energyPadding) + "│"));
        System.out.println(ColorUtil.brightCyanBold("└" + "─".repeat(totalWidth - 2) + "┘"));
    }
}