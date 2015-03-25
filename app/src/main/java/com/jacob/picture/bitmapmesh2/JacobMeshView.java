package com.jacob.picture.bitmapmesh2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jacob.picture.R;

/**
 * Package : com.jacob.picture.bitmapmesh2
 * Author : jacob
 * Date : 15-3-25
 * Description : 这个类是用来xxx
 */
public class JacobMeshView extends View {

    private Bitmap mBitmapSrc;

    private int WIDTH = 200;
    private int HEIGHT = 200;
    private int COUNT = (WIDTH + 1) * (HEIGHT + 1);

    private float[] origin = new float[COUNT * 2];
    private float[] verts = new float[COUNT * 2];

    private int mBmpWidth;
    private int mBmpHeight;

    private int index;

    //触摸的坐标
    private float touchX;
    private float touchY;

    //振幅
    private float offsetY = 50;
    private int offsetX = 0;

    private int foldCount = 0;


    public JacobMeshView(Context context) {
        super(context);
        initView();
    }

    public JacobMeshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public JacobMeshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mBitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.ic_panda);
        mBmpWidth = mBitmapSrc.getWidth();
        mBmpHeight = mBitmapSrc.getHeight();

        for (int i = 0; i < HEIGHT + 1; i++) {
            float dy = mBmpHeight * i / HEIGHT;
            for (int j = 0; j < WIDTH + 1; j++) {
                float dx = mBmpWidth * j / WIDTH;
                origin[index * 2 + 0] = verts[index * 2 + 0] = dx;
                origin[index * 2 + 1] = verts[index * 2 + 1] = dy + 100;
                index++;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        index = 0;
        for (int i = 0; i < HEIGHT + 1; i++) {
            for (int j = 0; j < WIDTH + 1; j++) {
                float offY = (float) (offsetY * Math.sin((j * 1.0f * foldCount / WIDTH * Math.PI * 2)));
                verts[index * 2] = origin[index * 2] - (j * 1.0f / WIDTH * 1.0f) * Math.abs(offsetX);
                verts[index * 2 + 1] = origin[index * 2 + 1] + offY;
                index++;
            }
        }
        canvas.drawBitmapMesh(mBitmapSrc, WIDTH, HEIGHT, verts, 0, null, 0, null);
    }

    float startX = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX =  event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = (int) (event.getX() - startX);
                if (offsetX > 0) {
                    //在拉伸
                    foldCount = 0;
                    offsetY = 0;
                }else{
                    //收缩
                    foldCount = 4;
                    offsetY = 15 * (Math.abs(offsetX)*1.0f/200);
                }
                invalidate();
                break;
        }
        return true;
    }
}
