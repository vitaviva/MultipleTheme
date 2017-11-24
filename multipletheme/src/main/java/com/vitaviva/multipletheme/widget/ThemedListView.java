package com.vitaviva.multipletheme.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import com.vitaviva.multipletheme.ThemeUiInterface;
import com.vitaviva.multipletheme.util.ViewAttributeUtil;

@SuppressWarnings("unused")
public class ThemedListView extends ListView implements ThemeUiInterface {

    private int attr_background = -1;
    private int attr_divider = -1;
    private int divider_height = 0;

    public ThemedListView(Context context) {
        super(context);
    }

    public ThemedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_divider = ViewAttributeUtil.getDividerAttribute(attrs);
    }

    public ThemedListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_divider = ViewAttributeUtil.getDividerAttribute(attrs);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        this.divider_height = getDividerHeight();
        ViewAttributeUtil.applyBackgroundDrawable(this, themeId, attr_background);
        int res_divider = this.attr_divider;
        if(res_divider > 0) {
            TypedArray ta = themeId.obtainStyledAttributes(new int[]{res_divider});
            Drawable drawable = ta.getDrawable(0);
            ((ListView)getView()).setDivider(drawable);
            ta.recycle();
        }
        setDividerHeight(divider_height);
    }
}
