package com.jacob.picture.xyMatrix;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;

import com.jacob.picture.R;

/**
 * Package : com.jacob.picture
 * Author : jacob
 * Date : 15-3-24
 * Description : 这个类是用来xxx
 */
public class PictureMatrixDemo extends Activity implements View.OnClickListener {

    private MatrixImageView mImageView;
    private GridLayout mGridView;
    private float[] mMatrix = new float[9];
    private EditText[] mEdits = new EditText[9];

    private int mEdtWidth;
    private int mEdtHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_matrix);
        initMatrix();
        mImageView = (MatrixImageView) findViewById(R.id.image_view);
        mGridView = (GridLayout) findViewById(R.id.gridview);

        findViewById(R.id.button_change).setOnClickListener(this);
        findViewById(R.id.button_reset).setOnClickListener(this);


        mGridView.post(new Runnable() {
            @Override
            public void run() {
                mEdtWidth = mGridView.getWidth() / 3;
                mEdtHeight = mGridView.getHeight() / 3;
                addEditView();
            }
        });
    }

    private void addEditView() {
        for (int i = 0; i < 9; i++) {
            EditText editText = new EditText(PictureMatrixDemo.this);
            editText.setGravity(Gravity.CENTER);
            mEdits[i] = editText;
            editText.setText(mMatrix[i] + "");
            mGridView.addView(editText, mEdtWidth, mEdtHeight);
        }
    }

    public void initMatrix() {
        for (int i = 0; i < 9; i++) {
            mMatrix[i] = (i % 4 == 0 ? 1 : 0);
        }
    }

    private void getMatrix() {
        for (int i = 0; i < 9; i++) {
            mMatrix[i] = Float.parseFloat(mEdits[i].getText().toString());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_change:
                getMatrix();
                Matrix matrix = new Matrix();
                matrix.setValues(mMatrix);
//                matrix.setRotate(30);
//                matrix.postTranslate(50, 50);
                for (int i = 0; i < 9; i++) {
                    Log.e("wangjia:", mMatrix[i]+"");
                }
                mImageView.setMatrix(matrix);
                break;
            case R.id.button_reset:

                break;
        }
    }
}
