package com.codexiaosheng.customviewone.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Decription:自定义view（1）
 * <p>
 * Created by codexiaosheng on 2017/7/25.
 */

public class CustomOne extends View {

    private Paint mPaint1, mPaint2;

    public CustomOne(Context context) {
        this(context, null);
    }

    public CustomOne(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint1 = new Paint(); // 默认黑色
//        mPaint1.setColor(Color.BLUE);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.RED);
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawCircle(150, 150, 100, mPaint1);
//        canvas.drawColor(Color.BLUE); // 自定义区域填充色

        canvas.drawArc(new RectF(), 140, 140, false, mPaint2);
    }

}
