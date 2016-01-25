package com.iw.tracker.library.entity;


import java.util.LinkedList;
import java.util.Queue;

public class EventQueue {
    private Queue<EventItem> mQueue;

    public EventQueue() {
        mQueue = new LinkedList<>();
    }

    public void add(EventItem item) {
        mQueue.add(item);
    }

    public EventItem remove() {
        return mQueue.remove();
    }
}
