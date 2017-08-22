package com.codexiaosheng.customviewone;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

/**
 * Decription: ColorFilter
 * <p>
 * Created by code-xiaosheng on 2017/8/15.
 */

public class ColorFilterActivity extends AppCompatActivity {

    private Spinner spinner;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colorfilter);

        spinner = (Spinner) findViewById(R.id.sp_colorFilter_item);
        imageView = (ImageView) findViewById(R.id.iv_img);
        spinner.setSelection(0, false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageView.setImageResource(R.drawable.ncxy);
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        imageView.setImageBitmap(getBitmap(bitmap, getColorMatrixGrayscale()));
                        break;
                    case 2:
                        imageView.setImageBitmap(getBitmap(bitmap, getColorMatrixSepia()));
                        break;
                    case 3:
                        imageView.setImageBitmap(getBitmap(bitmap, getColorMatrixBinary()));
                        break;
                    case 4:
                        imageView.setImageBitmap(getBitmap(bitmap, getColorMatrixInvert()));
                        break;
                    case 5:
                        imageView.setImageBitmap(getBitmap(bitmap, getColorMatrixAlphaBlue()));
                        break;
                    case 6:
                        imageView.setImageBitmap(getBitmap(bitmap, getColorMatrixAlphaPink()));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        findViewById(R.id.btn_color_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ColorFilterActivity.this, LightingColorFilterAct.class));
            }
        });
    }

    /**
     * 根据colorMaxtrix获取对应图片
     *
     * @param colorMatrix
     * @return
     */
    private Bitmap getBitmap(Bitmap original, ColorMatrix colorMatrix) {
        Bitmap bitmap = Bitmap.createBitmap(original.getWidth(), original.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(original, 0, 0, paint);

        return bitmap;
    }

    private ColorMatrix getColorMatrixGrayscale() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        return colorMatrix;
    }

    private ColorMatrix getColorMatrixSepia() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);

        ColorMatrix colorScale = new ColorMatrix();
        colorScale.setScale(1, 1, 0.8f, 1);

        // Convert to grayscale, then apply brown color
        colorMatrix.postConcat(colorScale);

        return colorMatrix;
    }

    private ColorMatrix getColorMatrixBinary() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);

        float m = 255f;
        float t = -255 * 128f;
        ColorMatrix threshold = new ColorMatrix(new float[]{
                m, 0, 0, 1, t,
                0, m, 0, 1, t,
                0, 0, m, 1, t,
                0, 0, 0, 1, 0
        });

        // Convert to grayscale, then scale and clamp
        colorMatrix.postConcat(threshold);

        return colorMatrix;
    }

    private ColorMatrix getColorMatrixInvert() {
        return new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0
        });
    }

    private ColorMatrix getColorMatrixAlphaBlue() {
        return new ColorMatrix(new float[]{
                0, 0, 0, 0, 0,
                0.3f, 0, 0, 0, 50,
                0, 0, 0, 0, 255,
                0.2f, 0.4f, 0.4f, 0, -30
        });
    }

    private ColorMatrix getColorMatrixAlphaPink() {
        return new ColorMatrix(new float[]{
                0, 0, 0, 0, 255,
                0, 0, 0, 0, 0,
                0.2f, 0, 0, 0, 50,
                0.2f, 0.2f, 0.2f, 0, -20
        });
    }

}
