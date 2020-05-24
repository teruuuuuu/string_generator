package jp.co.teruuu.stringgen;

public class StaticString implements StrGen{
    String str;
    public StaticString(String str) {
        this.str = str;
    }

    @Override
    public String genStr() {
        return this.str;
    }
}
