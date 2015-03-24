package com.jacob.picture.lesson2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
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
public class BitmapShaperImageView extends ImageView {

    private Bitmap mBitmap;
    private Paint mPaint;

    private int width;
    private int height;

    public BitmapShaperImageView(Context context) {
        this(context, null);
    }

    public BitmapShaperImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapShaperImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s_boy);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        BitmapShader bitmapShader =new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(bitmapShader);

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawCircle(mBitmap.getWidth()/2,mBitmap.getHeight()/2,mBitmap.getWidth()/4,mPaint);
    }
}
