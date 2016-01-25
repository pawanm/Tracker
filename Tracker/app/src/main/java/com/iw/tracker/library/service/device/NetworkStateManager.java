package com.iw.tracker.library.service.device;

import android.content.Context;
import android.net.ConnectivityManager;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class NetworkStateManager {

    public enum NetworkState {
        CONNECTED, DISCONNECTED
    }

    private final Context mContext;
    private NetworkState mNetworkState;
    private final Set<INetworkStateListener> mStateListeners;

    public NetworkStateManager(Context context) {
        mContext = context;
        mNetworkState = NetworkState.CONNECTED;
        mStateListeners = Collections.synchronizedSet((new HashSet<INetworkStateListener>()));
    }

    public void addNetworkListener(INetworkStateListener stateListener) {
        mStateListeners.add(stateListener);
    }

    public void removeNetworkListener(INetworkStateListener networkStateListener) {
        mStateListeners.remove(networkStateListener);
    }

    public NetworkState getNetworkState() {
        mNetworkState = getCurrentNetworkState();
        return mNetworkState;
    }

    public void networkStateChanged(NetworkState newState) {
        NetworkState oldState = mNetworkState;
        if (oldState != newState) {
            mNetworkState = newState;
            synchronized (mStateListeners) {
                for (INetworkStateListener listener : mStateListeners) {
                    listener.onNetworkStateChanged(mNetworkState);
                }
            }
        }
    }

    private NetworkState getCurrentNetworkState() {
        ConnectivityManager conMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        final android.net.NetworkInfo wifi = conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final android.net.NetworkInfo mobile = conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifi != null && wifi.isConnectedOrConnecting()) {
            return NetworkState.CONNECTED;
        }
        if (mobile != null && mobile.isConnectedOrConnecting()) {
            return NetworkState.CONNECTED;
        }
        return NetworkState.DISCONNECTED;
    }

    public interface INetworkStateListener {
        public void onNetworkStateChanged(NetworkState newState);
    }

}

