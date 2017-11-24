package com.vitaviva.multipletheme.sample;

import android.app.Application;

import com.vitaviva.multipletheme.util.ThemeManager;

public class MultiThemedApplication extends Application {
    public static final String MULTI_THEME_DAYNIGHT = "daynight";
    public static final String MULTI_THMEM_DAYMODE = "day";
    public static final String MULTI_THMEME_NIGHTMODE = "night";

    @Override
    public void onCreate() {
        super.onCreate();
        ThemeManager.init(this);
        ThemeManager.registerThemeRes(MULTI_THEME_DAYNIGHT, MULTI_THMEM_DAYMODE, R.style.DayModeTheme, true);
        ThemeManager.registerThemeRes(MULTI_THEME_DAYNIGHT, MULTI_THMEME_NIGHTMODE, R.style.NightModeTheme, false);
    }
}
