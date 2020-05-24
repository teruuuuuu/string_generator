package jp.co.teruuu.stringgen;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class RandomNum implements StrGen {
    BigDecimal center;
    BigDecimal range;
    BigDecimal halfRange;
    int scale;
    Random random = new Random();

    /**
     * @param min
     * @param max
     * @param scale
     */
    public RandomNum(BigDecimal min, BigDecimal max, int scale) {
        this. center = max.add(min).divide(new BigDecimal(2), scale + 1, RoundingMode.HALF_UP);
        this.range = max.subtract(min);
        this.halfRange = this.range.divide(new BigDecimal(2), scale + 1, RoundingMode.HALF_UP);
        this.scale = scale;
    }

    @Override
    public String genStr() {
        return this.center
                .add(new BigDecimal(this.random.nextDouble()).multiply(this.range).setScale(scale + 1, RoundingMode.HALF_UP))
                .subtract(this.halfRange).setScale(this.scale, RoundingMode.HALF_UP)
                .toString();
    }
}
