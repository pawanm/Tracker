package com.iw.tracker.library;


import com.iw.tracker.library.constants.Constants;

class Account implements IAccount {
    private final String mClientKey;
    private final String mAccountToken;
    private long mDispatchTime;
    private long mLastDispatchTime;
    private Boolean mDebugFlag;

    public Account(String clientKey, String accountToken)
    {
        mClientKey = clientKey;
        mAccountToken = accountToken;
        mDispatchTime = Constants.tsInMills;
        mLastDispatchTime = 0;
        mDebugFlag = false;
    }

    public String getAccountToken() {
        return mAccountToken;
    }

    public String getClientKey() {
        return mClientKey;
    }

    public long getDispatchTime() {
        return mDispatchTime;
    }

    public void setDispatchTime(long dispatchTime) {
        mDispatchTime = dispatchTime;
    }

    public Boolean getDebugFlag() {
        return mDebugFlag;
    }

    public void setDebugFlag(Boolean debugFlag) {
        mDebugFlag = debugFlag;
    }

    public long getLastDispatchTime() {
        return mLastDispatchTime;
    }

    public void setLastDispatchTime(long lastDispatchTime) {
        mLastDispatchTime = lastDispatchTime;
    }
}
