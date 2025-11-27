package Game;

import java.util.*;
import Items.Rarity;
import Entities.Characters.Character;

public abstract class Chest{
    private String name;
    private int expYield;
    private int gold;
    private Rarity rarity;

    public Chest(String name, Rarity rarity, int baseExp, int baseGold){
        Random rnd = new Random();
        this.name = name;
        this.expYield = 2 * baseExp + rnd.nextInt(1, 6);
        this.gold = baseGold * rnd.nextInt(1, 4);
        this.rarity = rarity;
    }

    public String getName(){
        return name;
    }

    public void obtain(Character player){
        player.gainExperience(expYield);
        System.out.println("\uD83E\uDE99 Gained "+gold+" extra gold coins!");
        player.setCurrency(player.getCurrency() + gold);
    }

    //100% in chapter 1
    public static class CommonChest extends Chest{
        public CommonChest() {
            super("Common Chest", Rarity.COMMON, 1, 1);
        }
    }
    //90% in chapter 2
    public static class EliteChest extends Chest{
        public EliteChest(){
            super("Elite Chest", Rarity.RARE, 2, 2);
        }
    }
    //80% in chapter 3
    public static class EpicChest extends Chest{
        public EpicChest(){
            super("Epic Chest", Rarity.EPIC, 3, 3);
        }
    }
    //70% in chapter 4-5
    public static class LegendaryChest extends Chest{
        public LegendaryChest(){
            super("Legendary Chest", Rarity.LEGENDARY, 4, 4);
        }
    }
    //100% drop rate but only once (dropped by the miniboss)
    //TODO after defeating mini boss, it needs to drop this -for frank
    public static class MythicalChest extends Chest{
        public MythicalChest(){
            super("Mythical Chest", Rarity.MYTHICAL, 5, 5);
        }
    }
}
