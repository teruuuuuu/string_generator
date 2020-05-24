package jp.co.teruuu.stringgen;

import java.util.List;
import java.util.Random;

public class RandomGen implements StrGen{
    List<StrGen> genList;
    Random random = new Random();

    public RandomGen(List<StrGen> genList) {
        this.genList = genList;
    }

    @Override
    public String genStr() {
        return this.genList.get(random.nextInt(genList.size())).genStr();
    }
}
