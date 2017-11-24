package com.vitaviva.multipletheme.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsoluteLayout;

import com.vitaviva.multipletheme.ThemeUiInterface;
import com.vitaviva.multipletheme.util.ViewAttributeUtil;


@SuppressWarnings("unused")
public class ThemedAbsoluteLayout extends AbsoluteLayout implements ThemeUiInterface {

    private int attr_background = -1;

    public ThemedAbsoluteLayout(Context context) {
        super(context);
    }

    public ThemedAbsoluteLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }

    public ThemedAbsoluteLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
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
