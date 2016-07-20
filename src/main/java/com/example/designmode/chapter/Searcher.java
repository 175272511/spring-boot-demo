package com.example.designmode.chapter;

/**
 * Created by liuhui on 2016/6/1.
 */
public class Searcher extends AbstractSearcher {

    public Searcher(IPettyGirl _pettyGirl) {
        super(_pettyGirl);
    }

    @Override
    public void show() {
        super.pettyGirl.niceFigure();
        super.pettyGirl.greatTemperament();
        super.pettyGirl.goodLooking();
    }
}
