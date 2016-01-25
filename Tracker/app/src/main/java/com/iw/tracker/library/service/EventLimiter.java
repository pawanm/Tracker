package com.iw.tracker.library.service;


import java.util.Date;
import java.util.concurrent.ScheduledFuture;

public class EventLimiter {
    private SchedulerService mSchedulerService;
    private long mPollingTimeMillis;
    private long mScheduledTime;
    private ScheduledFuture<?> mTask;
    private Runnable mRunnable;

    public EventLimiter(Runnable runnable, long pollingTimeMillis) {
        mPollingTimeMillis = pollingTimeMillis;
        mRunnable = runnable;
        mSchedulerService = ServiceFactory.getSchedulerService();
    }

    public synchronized void schedule() {
        long currentTime = new Date().getTime();

        if (mScheduledTime < currentTime && previousTaskDone()) {
            mTask = scheduleOnExecutor(mRunnable);
            mScheduledTime = currentTime + mPollingTimeMillis;
        }
    }

    public synchronized void forceSchedule() {
        scheduleOnExecutor(mRunnable);
    }

    private ScheduledFuture<?> scheduleOnExecutor(Runnable runnable) {
        return mSchedulerService.schedule(runnable, mPollingTimeMillis);
    }

    private boolean previousTaskDone() {
        return mTask == null || mTask.isDone();
    }

    public void cancel() {
        if (mTask != null) {
            mTask.cancel(true);
        }
    }
}

