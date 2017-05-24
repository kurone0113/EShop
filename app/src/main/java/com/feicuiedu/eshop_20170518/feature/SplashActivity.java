package com.feicuiedu.eshop_20170518.feature;

import android.animation.Animator;
import android.content.Intent;
import android.widget.ImageView;

import com.feicuiedu.eshop_20170518.R;
import com.feicuiedu.eshop_20170518.base.BaseActivity;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.image_splash)
    ImageView mImageSplash;
    @Override
    protected void paramInitialize() {
        mImageSplash.setAlpha(0.3f);
        mImageSplash.animate()
                .alpha(1.0f)
                .setDuration(2000)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startActivity(new Intent(SplashActivity.this,EShopMainActivity.class));
                        finish();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
}
