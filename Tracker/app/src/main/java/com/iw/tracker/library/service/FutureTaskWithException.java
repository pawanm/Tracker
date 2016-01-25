package com.iw.tracker.library.service;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskWithException extends FutureTask<Object> {
    private static final String TAG = FutureTaskWithException.class.getSimpleName();

    public FutureTaskWithException(Runnable r) {
        super(r, null);
    }

    @Override
    protected void done() {
        try {
            if (!isCancelled()) {
                get();
            }
        } catch (final ExecutionException e) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    throw new RuntimeException(e.getMessage(), e);
                }
            });
            thread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

