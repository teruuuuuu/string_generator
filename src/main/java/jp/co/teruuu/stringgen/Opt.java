package jp.co.teruuu.stringgen;

import java.util.Random;

public class Opt implements StrGen{
    StrGen strGen;
    double rate;
    Random random = new Random();

    /**
     * @param strGen
     * @param rate 0. ~ 1. String Generate Rate
     */
    public Opt(StrGen strGen, double rate) {
        this.strGen = strGen;
        this.rate = rate;
    }


    @Override
    public String genStr() {
        return random.nextDouble() < this.rate ? this.strGen.genStr() : "";
    }
}
