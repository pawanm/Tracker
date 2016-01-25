package com.iw.tracker.library;

import com.iw.tracker.library.constants.Constants;
import com.iw.tracker.library.entity.EventItem;

import java.util.HashMap;
import java.util.Map;

class EventLogger implements IEventLogger {
    private final IDispatcher mDispatcher;

    public EventLogger(IDispatcher dispatcher) {
        mDispatcher = dispatcher;
    }

    public Boolean sendEvent(String key, String value) {
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.DEFAULT_KEY, value);
        return logEvent(key, map);
    }

    public Boolean sendEvent(String key, Map<String, Object> map) {
        return logEvent(key,map);
    }

    public Boolean sendCrash(Exception ex) {
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.DEFAULT_CRASH_KEY, ex.getMessage());
        return logEvent(Constants.DEFAULT_CRASH_KEY, map);
    }

    private boolean logEvent(String key, Map<String, Object> eventValues) {
        if(validateEvent(key,eventValues)) {
            //TODO: replace by adding item in Queue
            return mDispatcher.dispatchItem(new EventItem(key, eventValues));
        }
        return false;
    }

    private boolean validateEvent(String key, Map<String, Object> eventValues) {
        if(key==null) {
            return false;
        }

        for (Object eventValue : eventValues.keySet()) {
            if(eventValue==null) {
                return false;
            }
        }
        return true;
    }
}
