package com.jacob.picture;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
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

    public static Bitmap getNewBitmap(Bitmap bmp, float hue, float saturation, float lum) {
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
        staMatrix.setSaturation(saturation);

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

    /**
     * 底片效果的图片
     */
    public static Bitmap getFilmPhoto(Bitmap bitmapSource) {
        int width = bitmapSource.getWidth();
        int height = bitmapSource.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        int[] oldPix = new int[width*height];
        int[] newPix = new int[width*height];
        int color;
        int r, g, b, a;
        bitmapSource.getPixels(oldPix, 0, width, 0, 0, width, height);

        for (int i = 0; i < width * height; i++) {
            color = oldPix[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);


            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

            if (r > 255) {
                r = 255;
            } else if (r < 0) {
                r = 0;
            }

            if (g > 255) {
                g = 255;
            } else if (g < 0) {
                g = 0;
            }


            if (b > 255) {
                b = 255;
            } else if (b < 0) {
                b = 0;
            }
            newPix[i] = Color.argb(a, r, g, b);

        }
        bitmap.setPixels(newPix, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
