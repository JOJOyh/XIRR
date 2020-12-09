package com.jojo.xirr;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws ParseException {

    }

    @Test
    public void testBisectionMethod() {
        long a = Runtime.getRuntime().totalMemory() / 1024;
        System.out.println(a);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(0);
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.set(2018, 1, 1, 0, 0, 0);
        calendar2.set(2019, 1, 1, 0, 0, 0);
        double[] payments = new double[]{
                -10000, 11100
        };
        double[] days = new double[]{
                (double) getDateDiff(calendar1, calendar),
                (double) getDateDiff(calendar2, calendar)
        };
        double guess = 0.1;
        double result = XIRR.BisectionMethod(payments, days, guess);
        System.out.println(result);
        System.gc();
        long b = Runtime.getRuntime().totalMemory() / 1024/1024;
        System.out.println(b);
    }

    public static long getDateDiff(Calendar calendar1, Calendar calendar2) {
        return getDateDiff(calendar1.getTime(), calendar2.getTime(), TimeUnit.DAYS);
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}