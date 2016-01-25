package com.iw.tracker.library.service.device;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

public class Device implements IDevice {

    private static Context mContext;
    private static Device mInstance;
    private static String mAppVersionName;
    private static String mDeviceId;
    private static String mDeviceManufacturer;
    private static NetworkStateManager mNetworkStateManager;
    private static IStore mStore;


    private Device(Context context) {
        mContext = context;
        mDeviceId = "";
        mDeviceManufacturer = "";
        initDeviceDetails();
    }

    public static Device getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Device(context);
        }
        return mInstance;
    }

    public Context getContext() {
        return mContext;
    }

    public NetworkStateManager getNetworkStateManager() {
        if (mNetworkStateManager == null) {
            mNetworkStateManager = new NetworkStateManager(mContext);
        }
        return mNetworkStateManager;
    }

    public IStore getStore() {
        if(mStore == null) {
            mStore = new Store();
        }
        return mStore;
    }

    public String getAppVersionName() {
        return mAppVersionName;
    }

    public String getDeviceId() {
        return mDeviceId;
    }

    public String getDeviceManufacturer() {
        return mDeviceManufacturer;
    }

    public String getDeviceResolution() {
        int density = mContext.getResources().getDisplayMetrics().densityDpi;
        switch (density) {
            case DisplayMetrics.DENSITY_MEDIUM:
                return "MDPI";
            case DisplayMetrics.DENSITY_HIGH:
                return "HDPI";
            case DisplayMetrics.DENSITY_LOW:
                return "LDPI";
            case DisplayMetrics.DENSITY_XHIGH:
                return "XHDPI";
            case DisplayMetrics.DENSITY_TV:
                return "TV";
            case DisplayMetrics.DENSITY_XXHIGH:
                return "XXHDPI";
            case DisplayMetrics.DENSITY_XXXHIGH:
                return "XXXHDPI";
            default:
                return "Unknown";
        }

    }

    private void initDeviceDetails() {
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        mDeviceId = telephonyManager.getDeviceId();
        mDeviceManufacturer = android.os.Build.MANUFACTURER;
        if (mDeviceId == null) {
            mDeviceId = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        }

        initAppVersion();
    }

    private void initAppVersion() {
        try {
            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            mAppVersionName = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            mAppVersionName = "";
        }
    }



}


