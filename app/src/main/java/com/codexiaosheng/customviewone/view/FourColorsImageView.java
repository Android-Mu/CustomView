package com.codexiaosheng.customviewone.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Decription：
 * <p>
 * Created by code-xiaosheng on 2017/8/16.
 */

@SuppressLint("AppCompatCustomView")
public class FourColorsImageView extends ImageView {

    private Bitmap bitmap = null;

    public FourColorsImageView(Context context) {
        this(context, null);
    }

    public FourColorsImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FourColorsImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmap == null) {
            Bitmap quarter = Bitmap.createBitmap(getWidth() / 2, getHeight() / 2, Bitmap.Config.ARGB_8888);
            Canvas quarterCanvas = new Canvas(quarter);
            quarterCanvas.scale(0.5f, 0.5f);
            super.onDraw(quarterCanvas);
            quarterCanvas.scale(2, 2);

            createBitmap(quarter);
            quarter.recycle();
        }

        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    private void createBitmap(Bitmap quarter) {
        bitmap = Bitmap.createBitmap(
                getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();

        // Top left
        paint.setColorFilter(new LightingColorFilter(Color.RED, 0));
        canvas.drawBitmap(quarter, 0, 0, paint);

        // Top right
        paint.setColorFilter(new LightingColorFilter(Color.YELLOW, 0));
        canvas.drawBitmap(quarter, getWidth() / 2, 0, paint);

        // Bottom left
        paint.setColorFilter(new LightingColorFilter(Color.BLUE, 0));
        canvas.drawBitmap(quarter, 0, getHeight() / 2, paint);

        // Bottom right
        paint.setColorFilter(new LightingColorFilter(Color.GREEN, 0));
        canvas.drawBitmap(quarter, getWidth() / 2, getHeight() / 2, paint);
    }

}
