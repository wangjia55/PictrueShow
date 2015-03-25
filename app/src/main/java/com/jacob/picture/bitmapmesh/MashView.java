package com.jacob.picture.bitmapmesh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.jacob.picture.R;

/**
 * Package : com.jacob.picture.bitmapmash
 * Author : jacob
 * Date : 15-3-24
 * Description : 这个类是用来xxx
 */
public class MashView extends View {
    private Bitmap mBitmapSrc;

    //图像分割的像素块
    private int WIDTH = 200;
    private int HEIGHT = 200;

    //交集点的个数
    private int COUNT = (WIDTH + 1) * (HEIGHT + 1);

    //初始的交点的坐标数组
    private float[] origins = new float[COUNT * 2];

    //设置效果后的坐标数组
    private float[] mVerts = new float[COUNT * 2];

    //bitmap的尺寸
    private int mWidth;
    private int mHeight;

    private int index;

    private float k;

    public MashView(Context context) {
        super(context);
        initView();
    }

    public MashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MashView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mBitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.ic_four);
        mWidth = mBitmapSrc.getWidth();
        mHeight = mBitmapSrc.getHeight();


        //初始化坐标点
        for (int i = 0; i < HEIGHT + 1; i++) {
            float dy = mHeight * i / HEIGHT;
            for (int j = 0; j < WIDTH + 1; j++) {
                float dx = mWidth * j / WIDTH;
                origins[index * 2 + 0] = mVerts[index * 2 + 0] = dx;
                origins[index * 2 + 1] = mVerts[index * 2 + 1] = dy;
                index++;
            }
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        //改变原有的坐标点，使其有旗帜的效果
        index = 0;
        for (int i = 0; i < HEIGHT + 1; i++) {
            for (int j = 0; j < WIDTH + 1; j++) {
                float dx = mWidth * j / WIDTH;
                mVerts[index * 2 ] = dx;
                float offsetY = (float) Math.sin((j*1.0f / WIDTH+k) * Math.PI * 2);
                mVerts[index * 2 + 1] = origins[index * 2 + 1] + offsetY*50;
                index++;
            }
        }
        k+=0.1f;
        canvas.drawBitmapMesh(mBitmapSrc, WIDTH, HEIGHT, mVerts, 0, null, 0, null);
        invalidate();
    }
}
