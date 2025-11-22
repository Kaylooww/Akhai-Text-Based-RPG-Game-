package Items;

import Entities.Characters.Character;

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
            System.out.println("⚡ " + player.getName() + " restored " + energyAmount + " energy!");
            setQuantity(getQuantity() - 1);
        }else{
            System.out.println("Energy is full!");
        }
    }

    @Override
    public void displayInfo(){
        System.out.println(name+" (x"+getQuantity()+")");
        System.out.println("Restores " + energyAmount + " energy");
    }
}