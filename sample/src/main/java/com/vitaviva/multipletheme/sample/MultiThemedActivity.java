package com.vitaviva.multipletheme.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vitaviva.multipletheme.util.ThemeManager;

public class MultiThemedActivity extends Activity {
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ThemeManager.unregisterMultipleTheme(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.DayModeTheme);
        ThemeManager.registerMultipleTheme(this);
    }
}
