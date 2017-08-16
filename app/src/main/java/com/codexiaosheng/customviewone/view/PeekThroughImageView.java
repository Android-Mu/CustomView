package com.codexiaosheng.customviewone.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Decription：窗口半透明设置
 * <p>
 * Created by code-xiaosheng on 2017/8/15.
 */

@SuppressLint("AppCompatCustomView")
public class PeekThroughImageView extends ImageView {

    private final float radius = 55.0f;
    private Paint paint = null;

    private float x;
    private float y;
    private boolean shouldDrawOpening = false;

    public PeekThroughImageView(Context context) {
        this(context, null);
    }

    public PeekThroughImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (paint == null) {
            Bitmap original = Bitmap.createBitmap(
                    getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas originalCanvas = new Canvas(original);
            super.onDraw(originalCanvas);    // ImageView

            Shader shader = new BitmapShader(original,
                    Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

            paint = new Paint();
            paint.setShader(shader);
        }

        canvas.drawColor(Color.GRAY);
        if (shouldDrawOpening) {
            canvas.drawCircle(x, y - radius, radius, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        shouldDrawOpening = (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE);
        x = motionEvent.getX();
        y = motionEvent.getY();
        invalidate();
        return true;
    }

}
