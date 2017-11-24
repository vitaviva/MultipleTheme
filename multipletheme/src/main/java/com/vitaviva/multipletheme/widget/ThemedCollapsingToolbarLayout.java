package com.vitaviva.multipletheme.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.AttributeSet;
import android.view.View;

import com.vitaviva.multipletheme.ThemeUiInterface;
import com.vitaviva.multipletheme.util.ViewAttributeUtil;

@SuppressWarnings("unused")
public class ThemedCollapsingToolbarLayout extends CollapsingToolbarLayout implements ThemeUiInterface {

    private int attr_backgound = -1;
    private int attr_expandedTitleTextAppearance = -1;
    private int attr_collapsedTitleTextApperance = -1;

    public ThemedCollapsingToolbarLayout(Context context) {
        super(context);
    }

    public ThemedCollapsingToolbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attr_backgound = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_expandedTitleTextAppearance = ViewAttributeUtil.getExpandedTitleTextAppearance(attrs);
        this.attr_collapsedTitleTextApperance = ViewAttributeUtil.getCollapsedTitleTextAppearance(attrs);
    }

    public ThemedCollapsingToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr_backgound = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_expandedTitleTextAppearance = ViewAttributeUtil.getExpandedTitleTextAppearance(attrs);
        this.attr_collapsedTitleTextApperance = ViewAttributeUtil.getCollapsedTitleTextAppearance(attrs);
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
        if(attr_expandedTitleTextAppearance != -1){
            ViewAttributeUtil.applyExpandedTextAppearance(this, themeId, attr_expandedTitleTextAppearance);
        }
        if(attr_collapsedTitleTextApperance != -1){
            ViewAttributeUtil.applyCollapsedTextAppearance(this, themeId, attr_collapsedTitleTextApperance);
        }
    }
}
