package com.vitaviva.multipletheme.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.vitaviva.multipletheme.ThemeUiInterface;
import com.vitaviva.multipletheme.util.ViewAttributeUtil;


@SuppressWarnings("unused")
public class ThemedTextView extends TextView implements ThemeUiInterface {

    private int attr_drawable = -1;
    private int attr_textAppearance = -1;
    private int attr_textColor = -1;
    private int attr_textSize = -1;

    public ThemedTextView(Context context) {
        super(context);
    }

    public ThemedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        this.attr_textAppearance = ViewAttributeUtil.getTextApperanceAttribute(attrs);
        this.attr_drawable = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_textColor = ViewAttributeUtil.getTextColorAttribute(attrs);
        this.attr_textSize = ViewAttributeUtil.getTextSizeAttribute(attrs);
    }

    public ThemedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        this.attr_textAppearance = ViewAttributeUtil.getTextApperanceAttribute(attrs);
        this.attr_drawable = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_textColor = ViewAttributeUtil.getTextColorAttribute(attrs);
        this.attr_textSize = ViewAttributeUtil.getTextSizeAttribute(attrs);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        Log.d("COLOR", "id = " + getId());
        if (attr_drawable != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this, themeId, attr_drawable);
        }
//        if(attr_textAppearance != -1) {
//            ViewAttributeUtil.applyTextAppearance(this, themeId, attr_textAppearance);
//        }
        if (attr_textColor != -1) {
            ViewAttributeUtil.applyTextColor(this, themeId, attr_textColor);
        }
        if (attr_textSize != -1) {
            ViewAttributeUtil.applyTextSize(this, themeId, attr_textSize);
        }
    }
}
