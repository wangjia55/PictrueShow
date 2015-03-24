package com.jacob.picture.lesson2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.jacob.picture.R;

/**
 * Package : com.jacob.picture.lesson2
 * Author : jacob
 * Date : 15-3-24
 * Description : 这个类是用来xxx
 */
public class MatrixImageView extends ImageView {

    private Bitmap mBitmap;

    private Paint mPaint;

    private Bitmap mNewBitmap;

    private Matrix mMatrix;

    public MatrixImageView(Context context) {
        this(context, null);
    }

    public MatrixImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatrixImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mMatrix = new Matrix();
    }

    private void getMaskBitmap() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mNewBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mNewBitmap);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);
    }

    public void setMatrix(Matrix matrix) {
        mMatrix = matrix;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(500,300);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, 0, 0, null);
        getMaskBitmap();
        canvas.drawBitmap(mNewBitmap, 0, 0, null);
    }
}
