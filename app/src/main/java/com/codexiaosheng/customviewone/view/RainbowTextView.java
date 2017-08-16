package com.codexiaosheng.customviewone.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Decription：LinearGradient extends Shader
 * <p>
 * Created by code-xiaosheng on 2017/8/14.
 */

@SuppressLint("AppCompatCustomView")
public class RainbowTextView extends TextView {

    public RainbowTextView(Context context) {
        this(context, null);
    }

    public RainbowTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int[] rainbow = getRainbowColors();
        Shader shader = new LinearGradient(0, 0, 0, w, rainbow,
                null, Shader.TileMode.MIRROR);

        Matrix matrix = new Matrix();
        matrix.setRotate(90);
        shader.setLocalMatrix(matrix);

        getPaint().setShader(shader);
    }

    private int[] getRainbowColors() {
        return new int[]{
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_green_dark),
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_purple)
        };
    }

}
