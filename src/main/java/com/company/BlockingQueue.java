package com.company;

import java.util.LinkedList;

/**
 * Created by jedaka on 19.08.2015.
 */
public class BlockingQueue<T> {

    private LinkedList<T> queue = new LinkedList<T>();
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void add(T element){
        try {
            while (queue.size() == capacity) {
                wait();
                System.out.println();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queue.add(element);
        notifyAll();
    }

    public synchronized T remove(){
        try {
            while (queue.size() == 0){
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        T element = queue.get(0);
        queue.remove(0);
        notifyAll();
        return element;
    }
}
