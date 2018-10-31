package com.example.mainactivity.ui.jetpack;

import android.arch.lifecycle.LiveData;

///extends Live Data
public class StockLiveData extends LiveData<String> {
    private static StockLiveData sInstance;
    private StockManager stockManager;

    private SimplePriceListener listener = new SimplePriceListener() {
        @Override
        public void onPriceChanged(String price) {
            setValue(price);
        }
    };

    StockLiveData(String symbol) {
        stockManager = new StockManager(symbol);
    }

    public static StockLiveData get(String symbol) {
        if (sInstance == null) {
            sInstance = new StockLiveData(symbol);
        }
        return  sInstance;
    }

    @Override
    protected void onActive() {
        stockManager.requestPriceUpdates(listener);
    }

    @Override
    protected void onInactive() {
        stockManager.removeUpdates(listener);
    }
}
