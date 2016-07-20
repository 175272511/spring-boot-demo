package com.example.designmode.chapter;

/**
 * Created by liuhui on 2016/6/1.
 */
public class PettyGirl1 implements IPettyGirl {

    private String name;

    public PettyGirl1(String name){
        this.name = name;
    }

    @Override
    public void goodLooking() {

    }

    @Override
    public void niceFigure() {

    }

    @Override
    public void greatTemperament() {
        System.out.println(this.name + "---身材非常棒!");
    }
}
