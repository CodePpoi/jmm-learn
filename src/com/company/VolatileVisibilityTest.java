package com.company;

public class VolatileVisibilityTest {

    public static boolean initFlag = false;

    public static void main(String[] args) throws Exception {
	// write your code here

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("waiting data...");
                while(!initFlag) {

                }
                System.out.println("success");
            }
        }).start();
        Thread.sleep(2000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                prepareData();
            }
        }).start();
    }

    public static void prepareData() {
        System.out.println("prepare start");
        initFlag = true;
        System.out.println("prepare end");
    }
}
