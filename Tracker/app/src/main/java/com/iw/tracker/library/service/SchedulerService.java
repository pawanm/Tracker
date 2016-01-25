package com.iw.tracker.library.service;


import com.iw.tracker.library.constants.Constants;
import com.iw.tracker.library.utils.ThreadMaker;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class SchedulerService {
    private static final String THREAD_FACTORY_NAME = "tracker_background_thread";
    private static ScheduledExecutorService sScheduledBackgroundExecutorService;

    public SchedulerService() {
        ThreadFactory threadFactory = ThreadMaker.getThreadFactory(THREAD_FACTORY_NAME);
        sScheduledBackgroundExecutorService = Executors.newScheduledThreadPool(Constants.THREAD_POOL_SIZE, threadFactory);
    }

    public ScheduledFuture<?> schedule(Runnable runnable, long timeInMilliSeconds) {
        FutureTaskWithException futureTask = new FutureTaskWithException(runnable);
        return sScheduledBackgroundExecutorService.schedule(futureTask, timeInMilliSeconds, TimeUnit.MILLISECONDS);
    }

}

