package com.vitaviva.multipletheme.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.vitaviva.multipletheme.ThemeUiInterface;
import com.vitaviva.multipletheme.util.ViewAttributeUtil;

@SuppressWarnings("unused")
public class ThemedLinerLayout extends LinearLayout implements ThemeUiInterface {

    private int attr_backgound = -1;

    public ThemedLinerLayout(Context context) {
        super(context);
    }

    public ThemedLinerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attr_backgound = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }

    public ThemedLinerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr_backgound = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        if(attr_backgound != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this, themeId, attr_backgound);
        }
    }
}
