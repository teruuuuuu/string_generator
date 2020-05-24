package jp.co.teruuu.stringgen;

import java.util.List;

public class SeqGen implements StrGen {
    List<StrGen> genList;

    public SeqGen(List<StrGen> genList) {
        this.genList = genList;
    }

    @Override
    public String genStr() {
        return this.genList.stream().map(StrGen::genStr)
                .reduce("", (sum, elm) -> sum + elm);
    }
}
