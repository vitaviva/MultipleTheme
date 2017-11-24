package com.vitaviva.multipletheme.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.vitaviva.multipletheme.ThemeUiInterface;
import com.vitaviva.multipletheme.util.ViewAttributeUtil;

@SuppressWarnings("unused")
public class ThemedRelativeLayout extends RelativeLayout implements ThemeUiInterface {

    private int attr_background = -1;

    public ThemedRelativeLayout(Context context) {
        super(context);
    }

    public ThemedRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }

    public ThemedRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        if(attr_background != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this, themeId, attr_background);
        }
    }
}
