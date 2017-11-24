package com.vitaviva.multipletheme.util;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.StyleRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;


import com.vitaviva.multipletheme.ThemeUiInterface;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThemeManager {

    private static List<Activity> sActivityList = new ArrayList<>();
    private static List<Dialog> sDialogList = new ArrayList<>();
    private static HashMap<String, HashMap<String, Integer>> sThemeResMap = new HashMap<>();

    public static void init(Context context) {
        SharedPreferencesMgr.init(context, "multi_theme");
    }

    public static void registerMultipleTheme(Activity activity) {
        if (!sActivityList.contains(activity)) {
            sActivityList.add(activity);
        }
        updateTheme(activity);
    }

    public static void updateTheme(Activity activity) {
        for (String group : sThemeResMap.keySet()) {
            int theme = getCurrentThemeRes(group);
            if (theme != -1) {
                activity.setTheme(theme);
            }
        }
    }

    public static void registerMultipleTheme(Dialog dialog) {
        if (!sDialogList.contains(dialog)) {
            sDialogList.add(dialog);
        }
        updateTheme(dialog);
    }

    public static void unregisterMultipleTheme(Dialog dialog) {
        if (sDialogList.contains(dialog)) {
            sDialogList.remove(dialog);
        }
    }


    public static void updateTheme(Dialog dialog) {
        for (String group : sThemeResMap.keySet()) {
            int theme = getCurrentThemeRes(group);
            if (theme != -1) {
                dialog.getContext().setTheme(theme);
            }
        }
    }

    public static void updateTheme(View view) {
        changeTheme(view, view.getContext().getTheme());
    }

    public static
    @StyleRes
    int getCurrentThemeRes(String group) {
        String key = getCurrentThemeKey(group);
        if (!TextUtils.isEmpty(key)) {
            return sThemeResMap.get(group).get(key);
        }
        return -1;
    }

    public static String getCurrentThemeKey(String group) {
        return SharedPreferencesMgr.getString(group, "");
    }

    public static void unregisterMultipleTheme(Activity activity) {
        if (sActivityList.contains(activity)) {
            sActivityList.remove(activity);
        }
    }

    public static void registerThemeRes(String group, String key, @StyleRes int resid, boolean isDefault) {
        if (sThemeResMap.get(group) == null) {
            sThemeResMap.put(group, new HashMap<String, Integer>());
        }
        sThemeResMap.get(group).put(key, resid);
        if (TextUtils.isEmpty(SharedPreferencesMgr.getString(group, "")) && isDefault) {
            SharedPreferencesMgr.setString(group, key);
        }
    }

    /**
     * 切换应用主题
     *
     * @param rootView
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static void changeTheme(View rootView, Resources.Theme theme) {
        if (rootView instanceof ThemeUiInterface) {
            ((ThemeUiInterface) rootView).setTheme(theme);
        }
        if (rootView instanceof ViewGroup) {
            int count = ((ViewGroup) rootView).getChildCount();
            for (int i = 0; i < count; i++) {
                changeTheme(((ViewGroup) rootView).getChildAt(i), theme);
            }
        }
        if (rootView instanceof AbsListView) {
            try {
                Field localField = AbsListView.class.getDeclaredField("mRecycler");
                localField.setAccessible(true);
                Method localMethod =
                        Class.forName("android.widget.AbsListView$RecycleBin")
                                .getDeclaredMethod("clear", new Class[0]);
                localMethod.setAccessible(true);
                localMethod.invoke(localField.get(rootView), new Object[0]);
            } catch (NoSuchFieldException | ClassNotFoundException | NoSuchMethodException
                    | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (rootView instanceof RecyclerView) {//ms不起作用？但目前不依赖此部分代码,所以暂时不管
            try {
                Field localField = RecyclerView.class.getDeclaredField("mRecycler");
                localField.setAccessible(true);
                Method localMethod =
                        Class.forName("android.support.v7.widget.RecyclerView$Recycler")
                                .getDeclaredMethod("getRecycledViewPool", new Class[0]);
                localMethod.setAccessible(true);
                ((RecyclerView.RecycledViewPool) localMethod.invoke(localField.get(rootView))).clear();

                localMethod =
                        Class.forName("android.support.v7.widget.RecyclerView$Recycler")
                                .getDeclaredMethod("clear", new Class[0]);
                localMethod.setAccessible(true);
                localMethod.invoke(localField.get(rootView), new Object[0]);
            } catch (NoSuchFieldException | ClassNotFoundException | NoSuchMethodException
                    | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void changeTheme(String group, String key) {
        HashMap<String, Integer> map = sThemeResMap.get(group);
        if (map == null || map.get(key) <= 0) return;
        SharedPreferencesMgr.setString(group, key);
        for (Activity activity : sActivityList) {
            changeTheme(activity, group, key);
        }
        for (Dialog dialog : sDialogList) {
            dialog.getContext().setTheme(sThemeResMap.get(group).get(key));
            updateTheme(dialog.getWindow().getDecorView());
        }
    }

    private static void changeTheme(final Activity activity, String group, String key) {
        activity.setTheme(sThemeResMap.get(group).get(key));
        final View rootView = activity.getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= 14) {
            rootView.setDrawingCacheEnabled(true);
            rootView.buildDrawingCache(true);
            final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
            rootView.setDrawingCacheEnabled(false);
            if (null != localBitmap && rootView instanceof ViewGroup) {
                final View localView = new View(activity);
                localView.setBackgroundDrawable(new BitmapDrawable(activity.getResources(), localBitmap));
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                ((ViewGroup) rootView).addView(localView, params);
                localView.animate().alpha(0).setDuration(400).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        ThemeManager.changeTheme(rootView, activity.getTheme());
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ((ViewGroup) rootView).removeView(localView);
                        localBitmap.recycle();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
            }
        } else {
            ThemeManager.changeTheme(rootView, activity.getTheme());
        }
    }

}
