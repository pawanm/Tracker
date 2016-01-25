package com.iw.tracker.library;


import com.iw.tracker.library.entity.EventItem;

interface IDispatcher {
    Boolean dispatchItem(EventItem item);
    IAccount getAccount();
}
