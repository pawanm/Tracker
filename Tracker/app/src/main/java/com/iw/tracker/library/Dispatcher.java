package com.iw.tracker.library;


import com.iw.tracker.library.entity.EventItem;
import com.iw.tracker.library.entity.EventQueue;
import com.iw.tracker.library.service.EventLimiter;
import com.iw.tracker.library.service.device.IDevice;


class Dispatcher implements IDispatcher {

    private final IDevice mDevice;
    private IAccount mAccount;
    private EventLimiter mEventLimiter;
    private EventQueue mEventQueue;

    public Dispatcher(IAccount account, IDevice device) {
        mAccount = account;
        mDevice = device;
        mEventLimiter = new EventLimiter(getDispatchRunnable(), mAccount.getDispatchTime());
        mEventQueue = new EventQueue();
    }

    public Boolean dispatchItem(EventItem item) {
        logItem(mAccount.getDebugFlag());
        registerDeviceForAccount(mDevice);
        mEventQueue.add(item);
        mEventLimiter.schedule();
        return true;
    }

    private void registerDeviceForAccount(IDevice device) {
        //TODO: check whether its first event from this device for particular account
    }

    private void logItem(Boolean debugFlag) {
        //TODO: log item
    }

    private Runnable getDispatchRunnable() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //TODO: StoreItem (If Required Or No Network)
                //TODO: GetAllItems from Queue & remvoe
                //TODO: Transfer item(s) via HTTP client service in json format
                //TODO: Re-add items on exceptions
                //TODO: Re-schedule event limiter on queue size
                //TODO: Re-trial strategy
                mEventLimiter.schedule();
            }
        };
        return runnable;
    }


    public IAccount getAccount() {
        return mAccount;
    }
}
