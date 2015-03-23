package com.jacob.picture;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

public class PictureDemoTwo extends Activity implements View.OnClickListener {

    private ImageView mImageView;
    private GridView mGridview;
    private Bitmap mBitmap;
    private float[] mColorMatrix = new float[20];
    private EditText[] mEditTextArray = new EditText[20];
    private PicAdapter picAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_two);
        initMatrix();
        mImageView = (ImageView) findViewById(R.id.image_view);
        mGridview = (GridView) findViewById(R.id.gridview);
        picAdapter = new PicAdapter();
        mGridview.setAdapter(picAdapter);

        findViewById(R.id.button_change).setOnClickListener(this);
        findViewById(R.id.button_reset).setOnClickListener(this);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_four);
        mImageView.setImageBitmap(mBitmap);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_change:
                getColorMatrix();
                setImageMatrix();
                break;
            case R.id.button_reset:
                initMatrix();
                setImageMatrix();
                mGridview.setAdapter(null);
                picAdapter = new PicAdapter();
                mGridview.setAdapter(picAdapter);
                break;
        }
    }

    public void getColorMatrix() {
        for (int i = 0; i < 20; i++) {
            mColorMatrix[i] = Float.valueOf(mEditTextArray[i].getText().toString());
        }
    }

    public void setImageMatrix() {
        Bitmap bitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ColorMatrix colorMatrix = new ColorMatrix();
        getColorMatrix();
        colorMatrix.set(mColorMatrix);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        mImageView.setImageBitmap(bitmap);
    }

    public void initMatrix() {
        for (int i = 0; i < 20; i++) {
            mColorMatrix[i] = (i % 6 == 0) ? 1 : 0;
        }
    }

    private class PicAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            EditText editText = new EditText(PictureDemoTwo.this);
            mEditTextArray[position] = editText;
            editText.setText("" + mColorMatrix[position]);
            return editText;
        }
    }


}
