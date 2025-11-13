package Game;

import java.util.*;
import Items.Rarity;
import Entities.Characters.Character;

public abstract class Chest{
    String name;
    int expYield;
    Rarity rarity;
    public Chest(String name, Rarity rarity, int baseExp){
        Random rnd = new Random();
        this.name = name;
        //TODO adjust the expYield formula if necessary -For Zed
        this.expYield = baseExp * rnd.nextInt(1, 11);
        this.rarity = rarity;
    }

    public void obtain(Character player){
        player.gainExperience(expYield);
    }

    //100% in chapter 1
    public static class CommonChest extends Chest{
        public CommonChest() {
            super("Common Chest", Rarity.COMMON, 1);
        }
    }
    //90% in chapter 2
    public static class EliteChest extends Chest{
        public EliteChest(){
            super("Elite Chest", Rarity.RARE, 2);
        }
    }
    //80% in chapter 3
    public static class EpicChest extends Chest{
        public EpicChest(){
            super("Epic Chest", Rarity.EPIC, 3);
        }
    }
    //70% in chapter 4-5
    public static class LegendaryChest extends Chest{
        public LegendaryChest(){
            super("Legendary Chest", Rarity.LEGENDARY, 4);
        }
    }
    //100% drop rate but only once (dropped by the miniboss)
    public static class MythicalChest extends Chest{
        public MythicalChest(){
            super("Mythical Chest", Rarity.MYTHICAL, 5);
        }
    }
}
