package com.vitaviva.multipletheme.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.vitaviva.multipletheme.ThemeUiInterface;
import com.vitaviva.multipletheme.util.ViewAttributeUtil;

@SuppressWarnings("unused")
public class ThemedEditText extends EditText implements ThemeUiInterface {

    private int attr_background = -1;
    private int attr_textApperance = -1;

    public ThemedEditText(Context context) {
        super(context);
    }

    public ThemedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_textApperance = ViewAttributeUtil.getTextApperanceAttribute(attrs);
    }

    public ThemedEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_textApperance = ViewAttributeUtil.getTextApperanceAttribute(attrs);
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
        if(attr_textApperance != -1) {
            ViewAttributeUtil.applyTextAppearance(this, themeId, attr_textApperance);
        }
    }
}
