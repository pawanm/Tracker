package com.iw.tracker.library.entity;


import java.util.Calendar;
import java.util.Map;

public class EventItem {
    private final String mKey;
    private final Map mEventMap;
    private final long mEventTime;

    public EventItem(String key, Map<String, Object> eventMap) {
        mKey=key;
        mEventMap = eventMap;
        mEventTime = Calendar.getInstance().getTimeInMillis();
    }

    public String getKey() {
        return mKey;
    }

    public Map getEventMap() {
        return mEventMap;
    }

    public long getEventTime() {
        return mEventTime;
    }
}
