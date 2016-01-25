package com.iw.tracker.library.utils;

public class ThreadMaker {
    public static java.util.concurrent.ThreadFactory getThreadFactory(final String name) {
        return new java.util.concurrent.ThreadFactory() {
            private int count = 0;

            @Override
            public Thread newThread(Runnable runnable) {
                count++;
                return new Thread(runnable, name + "-" + count);
            }
        };
    }
}

