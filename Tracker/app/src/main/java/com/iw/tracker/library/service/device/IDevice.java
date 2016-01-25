package com.iw.tracker.library.service.device;

import android.content.Context;


public interface IDevice {
    Context getContext();
    NetworkStateManager getNetworkStateManager();
    IStore getStore();
    String getAppVersionName();
    String getDeviceId();
    String getDeviceManufacturer();
    String getDeviceResolution();
}

