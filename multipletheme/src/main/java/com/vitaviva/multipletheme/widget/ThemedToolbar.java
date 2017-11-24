package com.vitaviva.multipletheme.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.vitaviva.multipletheme.ThemeUiInterface;
import com.vitaviva.multipletheme.util.ViewAttributeUtil;


@SuppressWarnings("unused")
public class ThemedToolbar extends Toolbar implements ThemeUiInterface {

    private int attr_backgound = -1;
    private int attr_titleTextColor = -1;
    private int attr_navigationIcon = -1;

    public ThemedToolbar(Context context) {
        super(context);
    }

    public ThemedToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attr_backgound = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_titleTextColor = ViewAttributeUtil.getTitleTextColorAttribute(attrs);
        this.attr_navigationIcon = ViewAttributeUtil.getNavigationIconAttribute(attrs);
    }

    public ThemedToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr_backgound = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_titleTextColor = ViewAttributeUtil.getTitleTextColorAttribute(attrs);
        this.attr_navigationIcon = ViewAttributeUtil.getNavigationIconAttribute(attrs);
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
        if(attr_titleTextColor != -1) {
            ViewAttributeUtil.applyTitleTextColor(this, themeId, attr_titleTextColor);
        }
        if(attr_navigationIcon != -1) {
            ViewAttributeUtil.applyNavigationIcon(this, themeId, attr_navigationIcon);
        }
    }
}
