package com.vitaviva.multipletheme.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.vitaviva.multipletheme.ThemeUiInterface;
import com.vitaviva.multipletheme.util.ViewAttributeUtil;


@SuppressWarnings("unused")
public class ThemedButton extends Button implements ThemeUiInterface {

    private int attr_background = -1;
    private int attr_textAppreance = -1;

    public ThemedButton(Context context) {
        super(context);
    }

    public ThemedButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_textAppreance = ViewAttributeUtil.getTextApperanceAttribute(attrs);
    }

    public ThemedButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_textAppreance = ViewAttributeUtil.getTextApperanceAttribute(attrs);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        ViewAttributeUtil.applyBackgroundDrawable(this, themeId, attr_background);
        ViewAttributeUtil.applyTextAppearance(this, themeId, attr_textAppreance);
    }
}
