package com.example.mainactivity.ui.jetpack;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

public class AdvancedUser {
    public final ObservableField<String> firstName = new ObservableField<>();
    public final ObservableField<String> lastName = new ObservableField<>();
    public final ObservableInt age = new ObservableInt();
}
