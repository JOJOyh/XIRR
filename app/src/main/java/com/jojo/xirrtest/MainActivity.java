package com.jojo.xirrtest;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.jojo.xirr.XIRR;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testBisectionMethod();
    }

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
        long b = Runtime.getRuntime().totalMemory() / 1024 / 1024;
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