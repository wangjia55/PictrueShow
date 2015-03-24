package com.jacob.picture.lesson2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.jacob.picture.R;

/**
 * Package : com.jacob.picture.lesson2
 * Author : jacob
 * Date : 15-3-24
 * Description : 这个类是用来xxx
 */
public class XfermodeImageView extends ImageView {
    private Bitmap mBitmap;
    private Bitmap mBitmapMask;
    private Paint mPaint;

    public XfermodeImageView(Context context) {
        this(context, null);
    }

    public XfermodeImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XfermodeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_four);
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();

        mBitmapMask = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mBitmapMask);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawCircle(width*3 /4, height / 2, Math.min(width, height) / 4, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        mPaint.setXfermode(null);
    }


    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawBitmap(mBitmapMask, 0, 0, null);
    }
}
