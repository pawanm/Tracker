package com.iw.tracker.library.service;


import android.content.Context;
import com.iw.tracker.library.service.device.Device;
import com.iw.tracker.library.service.device.IDevice;

public class ServiceFactory {
    private static SchedulerService mSchedulerService;
    private static IHttpClient mHttpClient;
    private static IDevice mDevice;

    public static synchronized SchedulerService getSchedulerService() {
        if(mSchedulerService==null) {
            mSchedulerService = new SchedulerService();
        }
        return mSchedulerService;
    }

    public static synchronized IHttpClient getHttpService() {
        if(mHttpClient==null) {
            mHttpClient = new HttpClient();
        }
        return mHttpClient;
    }

    public static synchronized IDevice getDevice(Context context) {
        if(mDevice==null) {
            mDevice = Device.getInstance(context);
        }
        return mDevice;
    }
}
