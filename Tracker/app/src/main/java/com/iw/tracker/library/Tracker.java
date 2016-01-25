package com.iw.tracker.library;


import android.content.Context;

import com.iw.tracker.library.service.ServiceFactory;

import java.util.Map;

public class Tracker {

    private static Tracker mInstance;
    private IAccount mAccount;
    private IEventLogger mEventLogger;
    private IDispatcher mDispatcher;

    private Tracker() {
    }

    private Tracker(String clientKey, String accountToken, Context context) {
        mAccount = new Account(clientKey, accountToken);
        mDispatcher = new Dispatcher(mAccount, ServiceFactory.getDevice(context));
        mEventLogger = new EventLogger(mDispatcher);
    }

    public static synchronized Tracker getInstance(String clientKey, String accountToken, Context context) {
        if(mInstance==null)
        {
            mInstance = new Tracker(clientKey,accountToken, context);
        }
        return mInstance;
    }

    public void setDispatchInterval(long tsInMS) {
        mAccount.setDispatchTime(tsInMS);
    }

    public void setDebugging(Boolean value) {
        mAccount.setDebugFlag(value);
    }

    public void sendEvent(String key) {
        mEventLogger.sendEvent(key, "");
    }

    public void sendEvent(String key, String value) {
        mEventLogger.sendEvent(key, value);
    }

    public void sendEvent(String key, Map<String, Object> kvMap) {
        mEventLogger.sendEvent(key, kvMap);
    }

    public void sendCrash(Exception ex) {
        mEventLogger.sendCrash(ex);
    }

}
