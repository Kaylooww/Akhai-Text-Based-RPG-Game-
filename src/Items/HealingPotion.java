package Items;

import Entities.Characters.Character;
import TextFormat.ColorUtil;

public class HealingPotion extends Consumable{
    int healingAmount;

    public HealingPotion(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, int healingAmount) {
        super(itemId, name, description, maxStack, quantity, value, rarity, true, false);
        this.healingAmount = healingAmount;
    }

    @Override
    public void use(Character player){
        if(player.getHealth() < player.getMaxHealth()){
            int currentHealth = player.getHealth();
            int maxHealth = player.getMaxHealth();
            int potentialHealth = currentHealth + healingAmount;

            if(potentialHealth > maxHealth){
                int actualHeal = maxHealth - currentHealth;
                player.setHealth(maxHealth);
                System.out.println(player.getName() + " recovered " + actualHeal + " HP! (HP is now full)");
                delay(1000);
            } else {
                player.setHealth(potentialHealth);
                System.out.println(player.getName() + " recovered " + healingAmount + " HP!");
                delay(1000);
            }
            quantity--;
        } else {
            System.out.println("HP is full!");
        }
    }
    @Override
    public void displayInfo(){
        int totalWidth = 54;
        String nameLine = name + " (x" + quantity + ") ";
        String visibleNameLine = nameLine.replaceAll("\u001B\\[[;\\d]*m", "");

        String healLine = "> Heals " + healingAmount + " HP";

        //padding for each line
        int namePadding = totalWidth - visibleNameLine.length() - 4;
        int healPadding = totalWidth - healLine.length() - 3;

        // Ensure padding is at least 1
        namePadding = Math.max(1, namePadding);
        healPadding = Math.max(1, healPadding);

        System.out.println(ColorUtil.brightCyanBold("┌─ ") + nameLine + ColorUtil.brightCyanBold("─".repeat(namePadding)) + ColorUtil.cyanBold("┐"));
        System.out.println(ColorUtil.brightCyanBold("│ " + healLine + " ".repeat(healPadding) + "│"));
        System.out.println(ColorUtil.brightCyanBold("└" + "─".repeat(totalWidth - 2) + "┘"));
    }
}
