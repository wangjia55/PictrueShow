package com.jacob.picture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * Package : com.jacob.picture
 * Author : jacob
 * Date : 15-3-23
 * Description : 这个类是用来xxx
 */
public class PictureDemoOne extends FragmentActivity implements SeekBar.OnSeekBarChangeListener {

    private ImageView mImageView;
    private SeekBar mSeekbarOne;
    private SeekBar mSeekbarTwo;
    private SeekBar mSeekbarThree;

    private int MAX_VALUE = 255;
    private int MID_VALUE = 127;

    private float hue;
    private float staturation;
    private float lum;

    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_first);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_four);
        mImageView = (ImageView) findViewById(R.id.image_view);


        mSeekbarOne = (SeekBar) findViewById(R.id.seekbar_one);
        mSeekbarTwo = (SeekBar) findViewById(R.id.seekbar_two);
        mSeekbarThree = (SeekBar) findViewById(R.id.seekbar_three);

        mSeekbarOne.setOnSeekBarChangeListener(this);
        mSeekbarTwo.setOnSeekBarChangeListener(this);
        mSeekbarThree.setOnSeekBarChangeListener(this);

        mSeekbarOne.setMax(MAX_VALUE);
        mSeekbarTwo.setMax(MAX_VALUE);
        mSeekbarThree.setMax(MAX_VALUE);

        mSeekbarOne.setProgress(MID_VALUE);
        mSeekbarTwo.setProgress(MID_VALUE);
        mSeekbarThree.setProgress(MID_VALUE);

        mImageView.setImageBitmap(mBitmap);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekbar_one:
                hue = (progress - MID_VALUE) * 1.0f / MID_VALUE * 180;
                break;
            case R.id.seekbar_two:
                staturation = (float) (progress * 1.0 / MID_VALUE);
                break;
            case R.id.seekbar_three:
                lum = (float) (progress * 1.0 / MID_VALUE);
                break;
        }

        mImageView.setImageBitmap(ImageHelper.getNewBitmap(mBitmap, hue, staturation, lum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
