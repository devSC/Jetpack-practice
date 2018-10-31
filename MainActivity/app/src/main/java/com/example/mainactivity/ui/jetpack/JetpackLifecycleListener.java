package com.example.mainactivity.ui.jetpack;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;

public class JetpackLifecycleListener implements LifecycleObserver {
    private boolean enabled = false;
    private Lifecycle lifecycle;
    private JetCallBack callBack;

    JetpackLifecycleListener(Context context, Lifecycle lifecycle, JetCallBack callBack) {
        this.lifecycle = lifecycle;
        this.callBack = callBack;
    }

    void enable() {
        enabled = true;
        if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            //connect if not connected
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart() {
        if (enabled) {
            // start
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {

    }

}
