package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VolatileSerialTest {
    static int x;
    static int y;
    public static void main(String[] args) throws InterruptedException {
        Set<String> resSet = new HashSet<>();
        Map<String,Integer> resMap = new HashMap<>();

        for (int i = 0; i < 1000000; i++) {
            x = 0; y = 0;
            resMap.clear();

            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    int a = y;
                    x = 1;
                    resMap.put("a", a);
                }
            });
            Thread other = new Thread(new Runnable() {
                @Override
                public void run() {
                    int b = x;
                    y = 1;
                    resMap.put("b", b);
                }
            });

            one.start();
            other.start();
            one.join();
            other.join();


            resSet.add("a=" + resMap.get("a") + "," + "b=" + resMap.get("b"));
            System.out.println(resSet);
        }
    }
}
