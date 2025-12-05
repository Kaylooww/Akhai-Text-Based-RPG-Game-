package Entities.Characters;

import java.util.*;

public class JinwooSun extends Character {
    //Test OP
    public JinwooSun(String name){
        super(name, 4000, ClassType.SUNJINWOO, 800, 800, 100, 0.38, 0.38, 40);
        this.level = 30;
        this.currency = 99999;
    }

    //Test Custom
//    public JinwooSun(String name){
//        super(name, 305, ClassType.SUNJINWOO, 77, 77, 33, 0.29, 0.29, 17);
//        this.level = 30;
//        this.currency = 99999;
//    }


    @Override
    public int attack(){return 0;}
}
