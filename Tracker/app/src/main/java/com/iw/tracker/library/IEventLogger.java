package com.iw.tracker.library;

import java.util.Map;

interface IEventLogger {
    Boolean sendEvent(String key, String value);
    Boolean sendEvent(String key, Map<String, Object> map);
    Boolean sendCrash(Exception ex);
}
