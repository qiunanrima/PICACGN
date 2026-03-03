package io.socket.a;

import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: picacg_1.jar:io/socket/a/a.class */
public class a {
    private double wy;
    private int wz;
    private long ww = 100;
    private long wx = 10000;
    private int factor = 2;

    public a a(double d) {
        this.wy = d;
        return this;
    }

    public a e(long j) {
        this.ww = j;
        return this;
    }

    public long et() {
        BigInteger valueOf = BigInteger.valueOf(this.ww);
        BigInteger valueOf2 = BigInteger.valueOf(this.factor);
        int i = this.wz;
        this.wz = i + 1;
        BigInteger multiply = valueOf.multiply(valueOf2.pow(i));
        BigInteger bigInteger = multiply;
        if (this.wy != 0.0d) {
            double random = Math.random();
            BigInteger bigInteger2 = BigDecimal.valueOf(random).multiply(BigDecimal.valueOf(this.wy)).multiply(new BigDecimal(multiply)).toBigInteger();
            bigInteger = (((int) Math.floor(random * 10.0d)) & 1) == 0 ? multiply.subtract(bigInteger2) : multiply.add(bigInteger2);
        }
        return bigInteger.min(BigInteger.valueOf(this.wx)).longValue();
    }

    public int eu() {
        return this.wz;
    }

    public a f(long j) {
        this.wx = j;
        return this;
    }

    public void reset() {
        this.wz = 0;
    }
}
