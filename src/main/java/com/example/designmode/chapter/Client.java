package com.example.designmode.chapter;

/**
 * Created by liuhui on 2016/6/1.
 */
public class Client {

    public static void main(String[] args) {
        IPettyGirl yanYan = new PettyGirl1("妍妍");
        AbstractSearcher searcher = new Searcher(yanYan);
        searcher.show();
    }
}
