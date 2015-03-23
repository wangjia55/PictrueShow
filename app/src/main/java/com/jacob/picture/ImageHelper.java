package com.jacob.picture;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Package : com.jacob.picture
 * Author : jacob
 * Date : 15-3-23
 * Description : 这个类是用来xxx
 */
public class ImageHelper {

    public static Bitmap getNewBitmap(Bitmap bmp, float hue, float staturation, float lum) {
        Bitmap bitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);

        //调整色调
        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        //调整饱和度
        ColorMatrix staMatrix = new ColorMatrix();
        staMatrix.setSaturation(staturation);

        //调整亮度
        ColorMatrix lueMatrix = new ColorMatrix();
        lueMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix allMatrix = new ColorMatrix();
        allMatrix.postConcat(hueMatrix);
        allMatrix.postConcat(staMatrix);
        allMatrix.postConcat(lueMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(allMatrix));
        canvas.drawBitmap(bmp, 0, 0, paint);

        return bitmap;
    }
}
