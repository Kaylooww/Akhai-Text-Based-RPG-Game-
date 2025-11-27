package Entities.Characters;

import java.util.*;

public class JinwooSun extends Character {
    public JinwooSun(){
        //Values are only a placeholder
        super("Sung Jinwoo", 4000, ClassType.SUNJINWOO, 500, 500, 100, 0.38, 0.38, 40);
        this.level = 30;
    }

    @Override
    public int attack(){return 0;}
}
