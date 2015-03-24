package com.jacob.picture.reflect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.jacob.picture.R;

/**
 * Package : com.jacob.picture.lesson2
 * Author : jacob
 * Date : 15-3-24
 * Description : 这个类是用来xxx
 */
public class ImageReflectView extends ImageView {
    private Bitmap mBitmapSource;
    private Bitmap mBitmapMask;
    private Matrix mMatrix;
    private Paint mPaint;
    private int width;
    private int height;

    public ImageReflectView(Context context) {
        super(context);
        initView();
    }

    public ImageReflectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ImageReflectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mBitmapSource = BitmapFactory.decodeResource(getResources(), R.drawable.ic_four);
        width = mBitmapSource.getWidth();
        height = mBitmapSource.getHeight();

        mMatrix = new Matrix();
        mMatrix.setScale(1, -1);
        mBitmapMask = Bitmap.createBitmap(mBitmapSource, 0, 0, width, height, mMatrix, true);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setShader(new LinearGradient(0, height, 0, height * 1.6f, 0x1fffffff, 0xAfffffff, Shader.TileMode.CLAMP));
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawBitmap(mBitmapSource, 0, 0, null);
        canvas.drawBitmap(mBitmapMask, 0, height, null);
        canvas.drawRect(0, height, width, height*2, mPaint);

    }
}
