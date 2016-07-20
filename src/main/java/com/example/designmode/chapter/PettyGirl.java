package com.example.designmode.chapter;

/**
 * Created by liuhui on 2016/6/1.
 */
public class PettyGirl implements IPettyGirl {

    private String name;

    public PettyGirl(String name){
        this.name = name;
    }

    @Override
    public void goodLooking() {
        System.out.println(this.name + "---脸蛋很漂亮!");
    }

    @Override
    public void niceFigure() {
        System.out.println(this.name + "---气质非常好!");
    }

    @Override
    public void greatTemperament() {
        System.out.println(this.name + "---身材非常棒!");
    }
}
