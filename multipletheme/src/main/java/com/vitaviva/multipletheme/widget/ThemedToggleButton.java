package com.vitaviva.multipletheme.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ToggleButton;

import com.vitaviva.multipletheme.ThemeUiInterface;
import com.vitaviva.multipletheme.util.ViewAttributeUtil;

@SuppressWarnings("unused")
public class ThemedToggleButton extends ToggleButton implements ThemeUiInterface {

    private int attr_textAppearance = -1;
    private int attr_background = -1;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ThemedToggleButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_textAppearance = ViewAttributeUtil.getTextApperanceAttribute(attrs);
    }

    public ThemedToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_textAppearance = ViewAttributeUtil.getTextApperanceAttribute(attrs);
    }

    public ThemedToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        if(attr_textAppearance != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this, themeId, attr_background);
        }
        if(attr_background != -1) {
            ViewAttributeUtil.applyTextAppearance(this, themeId, attr_textAppearance);
        }

    }
}
