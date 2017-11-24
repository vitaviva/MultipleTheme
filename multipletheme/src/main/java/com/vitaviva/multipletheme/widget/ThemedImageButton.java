package com.vitaviva.multipletheme.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

import com.vitaviva.multipletheme.ThemeUiInterface;
import com.vitaviva.multipletheme.util.ViewAttributeUtil;


public class ThemedImageButton extends ImageButton implements ThemeUiInterface {

    private int attr_drawable = -1;
    private int attr_background = -1;

    public ThemedImageButton(Context context) {
        super(context);
    }

    public ThemedImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attr_drawable = ViewAttributeUtil.getSrcAttribute(attrs);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }

    public ThemedImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr_drawable = ViewAttributeUtil.getSrcAttribute(attrs);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        if(attr_drawable != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this, themeId, attr_drawable);
        }
        if(attr_background != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this, themeId, attr_background);
        }
    }
}
