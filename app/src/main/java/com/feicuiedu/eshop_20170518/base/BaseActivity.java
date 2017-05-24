package com.feicuiedu.eshop_20170518.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Superclass for any activities.Created by Kurone on 2017/5/23.
 */

public abstract class BaseActivity extends TransitionActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        paramInitialize();
    }

    abstract protected void paramInitialize();

    @LayoutRes
    abstract protected int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
