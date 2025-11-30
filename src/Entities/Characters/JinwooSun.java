package Entities.Characters;

import java.util.*;

public class JinwooSun extends Character {
    public JinwooSun(String name){
        //Values are only a placeholder
        super(name, 4000, ClassType.SUNJINWOO, 800, 800, 100, 0.38, 0.38, 40);
        this.level = 30;
        this.currency = 99999;
    }

    @Override
    public int attack(){return 0;}
}
