package com.design.mode.ioc.day01.pet;

import com.design.mode.ioc.day01.Pet;

/**
 * Created by fengye on 2017/9/13.
 */
public class Cat implements Pet {
    @Override
    public void name() {
        System.out.println("这是一只猫！");
    }
}
