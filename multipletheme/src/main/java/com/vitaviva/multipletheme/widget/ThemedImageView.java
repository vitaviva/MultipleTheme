package com.vitaviva.multipletheme.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.vitaviva.multipletheme.R;
import com.vitaviva.multipletheme.ThemeUiInterface;
import com.vitaviva.multipletheme.util.ViewAttributeUtil;

@SuppressWarnings("unused")
public class ThemedImageView extends ImageView implements ThemeUiInterface {

    private int attr_img = -1;
    private int attr_background = -1;
    private int attr_bright = -1;

    public ThemedImageView(Context context) {
        super(context);
    }

    public ThemedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attr_img = ViewAttributeUtil.getSrcAttribute(attrs);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_bright = ViewAttributeUtil.getAttributeValue(attrs, R.attr.bright);
    }

    public ThemedImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr_img = ViewAttributeUtil.getSrcAttribute(attrs);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_bright = ViewAttributeUtil.getAttributeValue(attrs, R.attr.bright);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        if (attr_img != -1) {
            ViewAttributeUtil.applyImageDrawable(this, themeId, attr_img);
        }
        if (attr_background != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this, themeId, attr_background);
        }
        if (attr_bright != -1) {
            applyBackgroundDrawable(this, themeId, attr_bright);
        }
    }

    private void applyBackgroundDrawable(ThemeUiInterface ci, Resources.Theme theme, int paramInt) {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        setBrightness(ta.getInt(R.styleable.ThemedImageView_bright, 255));
        ta.recycle();
    }

    private int brightness = 0; /* -255 ～ 255*/

    public void setBrightness(int brightness) {
        this.brightness = brightness;
        if (attr_img != -1) {
            setImageBitmap(brightnessLow(drawableToBitamp(getResources().getDrawable(attr_img))));
        }
    }

    //TODO(无效？)
    private Bitmap brightnessLow(Bitmap src) {
        Bitmap bmp = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
        ColorMatrix cMatrix = new ColorMatrix();
        cMatrix.set(new float[]{1, 0, 0, 0, brightness, 0, 1,
                0, 0, brightness,// 改变亮度
                0, 0, 1, 0, brightness, 0, 0, 0, 1, 0});
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(src, 0, 0, paint);
        return bmp;
    }

    private static Bitmap drawableToBitamp(Drawable drawable) {
        Bitmap bitmap;
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        bitmap = Bitmap.createBitmap(w, h, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    private static int getSrcId(AttributeSet attrs) {
        int count = attrs.getAttributeCount();
        for (int i = 0; i < count; i++) {
            if (attrs.getAttributeNameResource(i) == android.R.attr.src) {
                String str = attrs.getAttributeValue(i);
                return Integer.valueOf(str.substring(1, str.length()));
            }
        }
        return -1;
    }
}
