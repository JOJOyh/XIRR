package com.jojo.xirr;

import android.util.LruCache;

/**
 * ********************
 * project name：XIRR
 *
 * @Author jojo
 * email:281982108@qq.com
 * create time 2020/11/19 14:10
 * purpose：
 * ********************
 */
public class XIRR {
    public static class Bracket {
        public final double left;
        public final double right;

        public Bracket(double left, double right) {
            this.left = left;
            this.right = right;
        }
    }

    public static final double tol = 1E-8;
    public static final double epsilon = 1E-12;

    public static double BisectionMethod(double[] payments, double[] days, double guess) {
        Bracket bracket = findBracket(payments, days, guess);
        if (bracket == null) {
            return Double.NaN;
        }
        double left = bracket.left;
        double right = bracket.right;

        final int maxIteration = 216;
        int iteration = 0;
        double c = 0;
        do {
            c = (left + right) / 2;
            double resultC = total_F_XIRR(payments, days, c);
            if (Math.abs(resultC) <= epsilon || ((right - left) / 2.0) < tol) {
                break;
            }
            double resultLeft = total_F_XIRR(payments, days, left);
            if ((resultC * resultLeft) > 0) {
                left = c;
            } else {
                right = c;
            }
        }
        while (iteration++ < maxIteration);
        return c;
    }

    /**
     * @param payments
     * @param days
     * @param guess
     * @return 等级
     */
    public static Bracket findBracket(double[] payments, double[] days, double guess) {
        final int maxIteration = 109;
        final double step = 0.5;
        double left = guess;
        double right = guess;
        double resultLeft = total_F_XIRR(payments, days, left);
        double resultRight = resultLeft;
        int iteration = 0;
        while ((resultLeft * resultRight) > 0 && iteration++ < maxIteration) {
            left = left - step;
            resultLeft = total_F_XIRR(payments, days, left);
            if (resultLeft * resultRight <= 0) {
                break;
            }
            right = right + step;
            resultRight = total_F_XIRR(payments, days, right);
        }
        if ((resultLeft * resultRight) <= 0) {
            return new Bracket(left, right);
        }
        return null;

    }

    public static double total_F_XIRR(double[] payments, double[] days, double guess) {
        double result = 0.0;
        for (int i = 0; i < payments.length; i++) {
            result = composeFunctions(result, f_xirr(payments[i], days[i], days[0], guess));
        }
        return result;
    }

    public static double composeFunctions(double f1, double f2) {
        return f1 + f2;
    }

    public static double f_xirr(double p, double dt, double d0, double guess) {
        if (guess <= -1) {
            guess = -1 + epsilon;//check an IRR <= -100%
        }
        return p * Math.pow((1.0 + guess), ((d0 - dt) / 365.0));
    }
}
