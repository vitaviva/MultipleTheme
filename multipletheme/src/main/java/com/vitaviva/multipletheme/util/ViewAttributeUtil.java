package com.vitaviva.multipletheme.util;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;

import com.vitaviva.multipletheme.ThemeUiInterface;


public class ViewAttributeUtil {

    public static int getAttributeValue(AttributeSet attr, int paramInt) {
        int value = -1;
        int count = attr.getAttributeCount();
        for(int i = 0; i <count;i++) {
            if(attr.getAttributeNameResource(i) == paramInt) {
                String str = attr.getAttributeValue(i);
                if(null != str && str.startsWith("?")) {
                    value = Integer.valueOf(str.substring(1,str.length())).intValue();
                    return value;
                }
            }
        }
        return value;
    }

    public static int getAttributeValue(AttributeSet attr, String paramStr) {
        int value = -1;
        int count = attr.getAttributeCount();
        for(int i = 0; i <count;i++) {
            if(attr.getAttributeName(i).equals(paramStr)) {
                String str = attr.getAttributeValue(i);
                if(null != str && str.startsWith("?")) {
                    value = Integer.valueOf(str.substring(1,str.length())).intValue();
                    return value;
                }
            }
        }
        return value;
    }

    public static int getBackgroundAttibute(AttributeSet attr) {
        return getAttributeValue(attr , android.R.attr.background);
    }

    public static int getTitleTextColorAttribute(AttributeSet attr) {
        return getAttributeValue(attr, android.R.attr.titleTextColor);
    }

    public static int getNavigationIconAttribute(AttributeSet attr) {
        return getAttributeValue(attr, android.R.attr.navigationIcon);
    }

    public static int getExpandedTitleTextAppearance(AttributeSet attr) {
        return getAttributeValue(attr, "expandedTitleTextAppearance");
    }

    public static int getCollapsedTitleTextAppearance(AttributeSet attr) {
        return getAttributeValue(attr, "collapsedTitleTextAppearance");
    }

    public static int getCheckMarkAttribute(AttributeSet attr) {
        return getAttributeValue(attr, android.R.attr.checkMark);
    }

    public static int getSrcAttribute(AttributeSet attr) {
        return getAttributeValue(attr, android.R.attr.src);
    }

    public static int getTextApperanceAttribute(AttributeSet attr) {
        return getAttributeValue(attr, android.R.attr.textAppearance);
    }

    public static int getDividerAttribute(AttributeSet attr) {
        return getAttributeValue(attr, android.R.attr.divider);
    }

    public static int getTextColorAttribute(AttributeSet attr) {
        return getAttributeValue(attr, android.R.attr.textColor);
    }

    public static int getTextSizeAttribute(AttributeSet attr) {
        return getAttributeValue(attr, android.R.attr.textSize);
    }

    public static void applyBackgroundDrawable(ThemeUiInterface ci, Resources.Theme theme, int paramInt) {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        Drawable drawable = ta.getDrawable(0);
        if(null != ci) {
            (ci.getView()).setBackgroundDrawable(drawable);
        }
        ta.recycle();
    }

    public static void applyExpandedTextAppearance(ThemeUiInterface ci, Resources.Theme theme, int paramInt) {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        int id = ta.getResourceId(0, 0);
        if(null != ci) {
            ((CollapsingToolbarLayout)ci.getView()).setExpandedTitleTextAppearance(id);
        }
        ta.recycle();
    }

    public static void applyCollapsedTextAppearance(ThemeUiInterface ci, Resources.Theme theme, int paramInt) {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        int id = ta.getResourceId(0, 0);
        if(null != ci) {
            ((CollapsingToolbarLayout)ci.getView()).setCollapsedTitleTextAppearance(id);
        }
        ta.recycle();
    }


    public static void applyTitleTextColor(ThemeUiInterface ci, Resources.Theme theme, int paramInt) {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        int color = ta.getColor(0, 0);
        if(null != ci) {
            try {
                ((Toolbar)ci.getView()).setTitleTextColor(color);
            } catch (ClassCastException e){
                e.printStackTrace();
            }
        }
        ta.recycle();
    }


    public static void applyNavigationIcon(ThemeUiInterface ci, Resources.Theme theme, int paramInt) {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        int id = ta.getResourceId(0, 0);
        if(null != ci) {
            try {
                ((Toolbar)ci.getView()).setNavigationIcon(id);
            } catch (ClassCastException e){
                e.printStackTrace();
            }
        }
        ta.recycle();
    }


    public static void applyImageDrawable(ThemeUiInterface ci, Resources.Theme theme, int paramInt) {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        Drawable drawable = ta.getDrawable(0);
        if(null != ci && ci instanceof ImageView) {
            ((ImageView)ci.getView()).setImageDrawable(drawable);
        }
        ta.recycle();
    }

    public static void applyTextAppearance(ThemeUiInterface ci, Resources.Theme theme, int paramInt) {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        int resourceId = ta.getResourceId(0, 0);
        if(null != ci && ci instanceof TextView) {
            ((TextView)ci.getView()).setTextAppearance(ci.getView().getContext(), resourceId);
        }
        ta.recycle();
    }

    public static void applyTextColor(ThemeUiInterface ci, Resources.Theme theme, int paramInt) {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        int resourceId = ta.getColor(0, 0);
        if(null != ci && ci instanceof TextView) {
            ((TextView)ci.getView()).setTextColor(resourceId);
        }
        ta.recycle();
    }

    public static void applyTextSize(ThemeUiInterface ci, Resources.Theme theme, int paramInt) {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        int size = (int) ta.getDimension(0, 0);
        if(null != ci && ci instanceof TextView) {
            ((TextView)ci.getView()).setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        }
        ta.recycle();
    }

    public static @ColorInt
    int getThemedColor(Context context, int paramInt){
        TypedArray ta = context.getTheme().obtainStyledAttributes(new int[]{paramInt});
        int color = ta.getColor(0, 0);
        ta.recycle();
        return color;
    }

    public static int getThemedRrc(Context context, int paramInt){
        TypedArray ta = context.getTheme().obtainStyledAttributes(new int[]{paramInt});
        int id = ta.getResourceId(0, 0);
        ta.recycle();
        return id;
    }

}
