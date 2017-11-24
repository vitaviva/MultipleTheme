package com.vitaviva.multipletheme.sample;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.vitaviva.multipletheme.util.ThemeManager;

public class PopDialog extends Dialog {


    public PopDialog(@NonNull Context context) {
        super(context);
        init();
    }

    private int getCurrentThemeResId(Context context) {
        if (ThemeManager.getCurrentThemeKey(
                MultiThemedApplication.MULTI_THEME_DAYNIGHT).equals(
                        MultiThemedApplication.MULTI_THMEM_DAYMODE)) {
            return R.style.DayModeTheme;
        } else {
            return R.style.NightModeTheme;
        }
    }

    private void init() {
        super.setTitle(null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog);
        findViewById(R.id.tv_to_day).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemeManager.changeTheme(
                        MultiThemedApplication.MULTI_THEME_DAYNIGHT, MultiThemedApplication.MULTI_THMEM_DAYMODE);

            }
        });
        findViewById(R.id.tv_to_night).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemeManager.changeTheme(
                        MultiThemedApplication.MULTI_THEME_DAYNIGHT, MultiThemedApplication.MULTI_THMEME_NIGHTMODE);
            }
        });

        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                ThemeManager.registerMultipleTheme(PopDialog.this);
            }
        });
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                ThemeManager.unregisterMultipleTheme(PopDialog.this);
            }
        });
    }


    static class Builder {

        static void show(Context context) {
            PopDialog dialog = new PopDialog(context);
            dialog.show();
        }

    }
}
