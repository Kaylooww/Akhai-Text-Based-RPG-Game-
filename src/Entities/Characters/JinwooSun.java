package Entities.Characters;

import java.util.*;

public class JinwooSun extends Character {
    //Test OP
    public JinwooSun(String name){
        //Values are only a placeholder
        super(name, 4000, ClassType.SUNJINWOO, 800, 800, 100, 0.38, 0.38, 40);
        this.level = 30;
        this.currency = 99999;
    }

    //Test weak
//    public JinwooSun(String name){
//        //Values are only a placeholder
//        super(name, 10, ClassType.SUNJINWOO, 5, 5, 0, 0, 0, 5);
//        this.level = 30;
//        this.currency = 99999;
//    }

    @Override
    public int attack(){return 0;}
}
