package com.iw.tracker.library;


interface IAccount {
    String getAccountToken();
    String getClientKey();
    long getDispatchTime();
    void setDispatchTime(long dispatchTime);
    Boolean getDebugFlag() ;
    void setDebugFlag(Boolean debugFlag);
}
