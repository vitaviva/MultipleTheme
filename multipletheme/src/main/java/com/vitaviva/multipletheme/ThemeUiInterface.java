package com.vitaviva.multipletheme;

import android.content.res.Resources;
import android.view.View;

/**
 * 换肤接口
 *
 */
public interface ThemeUiInterface {

    View getView();
    void setTheme(Resources.Theme themeId);
}
