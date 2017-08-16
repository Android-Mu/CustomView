package com.codexiaosheng.customviewone;

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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                        imageView.setImageBitmap(getBitmap(bitmap, getColorMatrixGrayscale()));
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

}
