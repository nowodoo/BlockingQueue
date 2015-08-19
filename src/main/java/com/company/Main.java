package com.company;

public class Main {

    public static void main(String[] args) {

        final BlockingQueue<String> queue = new BlockingQueue<String>(5);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Element removed: " + queue.remove());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue.add(String.valueOf(i));
                    System.out.println("Element added");
                }
            }
        }).start();
    }
}
