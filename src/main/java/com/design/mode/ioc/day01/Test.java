package com.design.mode.ioc.day01;

import com.design.mode.ioc.day01.pet.Cat;
import com.design.mode.ioc.day01.pet.Dog;

/**
 * Created by fengye on 2017/9/13.
 */
public class Test {
    public static void main(String[] args) {
        ManWhoLovesPet man = new ManWhoLovesPet("Ko", 30, "f", new Dog());
        ManWhoLovesPet man1 = new ManWhoLovesPet("Lo", 30, "f", new Cat());
        ManWhoLovesPet man2 = new ManWhoLovesPet("No", 30, "f", null);

    }
}
