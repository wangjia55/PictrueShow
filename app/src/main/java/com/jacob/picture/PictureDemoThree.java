package com.jacob.picture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

/**
 * Package : com.jacob.picture
 * Author : jacob
 * Date : 15-3-23
 * Description : 这个类是用来xxx
 */
public class PictureDemoThree extends FragmentActivity {

    private ImageView mImageViewOne;
    private ImageView mImageViewTwo;
    private ImageView mImageViewThree;
    private ImageView mImageViewFour;

    private Bitmap mBitmapSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_three);

        mImageViewOne = (ImageView) findViewById(R.id.image_view_one);
        mImageViewTwo = (ImageView) findViewById(R.id.image_view_two);
        mImageViewThree = (ImageView) findViewById(R.id.image_view_three);
        mImageViewFour = (ImageView) findViewById(R.id.image_view_four);

        mBitmapSource = BitmapFactory.decodeResource(getResources(),R.drawable.ic_four);
        mImageViewOne.setImageBitmap(mBitmapSource);

        mImageViewTwo.setImageBitmap(ImageHelper.getFilmPhoto(mBitmapSource));
    }
}
