package com.example.mainactivity.ui.jetpack;

import android.icu.math.BigDecimal;

public class StockManager {
    private String symbol;
    private SimplePriceListener listener;

    StockManager(String symbol) {
        this.symbol = symbol;
    }

    void updatePrice(String price) {
        listener.onPriceChanged(price);
    }

    void requestPriceUpdates(SimplePriceListener listener) {
        this.listener = listener;
        updatePrice("Hello-88888888");
    }

    void removeUpdates(SimplePriceListener listener) {
        this.listener = null;
    }
}
