package com.company;

import java.util.LinkedList;

/**
 * Created by jedaka on 19.08.2015.
 */
public class BlockingQueue<T> {

    private LinkedList<T> queue = new LinkedList<>();
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void add(T element){
        try {
            while (queue.size() == capacity) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (queue.size() == 0){
            notifyAll();
        }
        queue.add(element);
    }

    public synchronized T remove(){
        try {
            while (queue.size() == 0){
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (queue.size() == capacity) {
            notifyAll();
        }
        return queue.remove(0);
    }
}
