package Items;

import Entities.Characters.Character;

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
        System.out.println(name+" (x"+quantity+")");
        System.out.println("\"Heals up to "+healingAmount+" HP. Can be used outside the battle\"");
    }
}
