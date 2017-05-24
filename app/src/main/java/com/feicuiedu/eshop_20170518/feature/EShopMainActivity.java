package com.feicuiedu.eshop_20170518.feature;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.feicuiedu.eshop_20170518.R;
import com.feicuiedu.eshop_20170518.base.BaseActivity;
import com.feicuiedu.eshop_20170518.base.utils.TestFragment;
import com.feicuiedu.eshop_20170518.feature.category.CategoryFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;

public class EShopMainActivity extends BaseActivity {

    @BindView(R.id.layout_container)
    FrameLayout mLayoutContainer;
    @BindView(R.id.bottom_bar)
    BottomBar mBottomBar;
    private Fragment mHomeFragment;
    private CategoryFragment mCategoryFragment;
    private Fragment mCartFragment;
    private Fragment mMineFragment;
    private Fragment mCurrentFragment;

    @Override
    protected void paramInitialize() {
        retrieveFragment();
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_home:
                        if (mHomeFragment == null) {
                            mHomeFragment = TestFragment.newInstance("HomeFragment", null);
                        }
                        switchFragment(mHomeFragment);
                        break;
                    case R.id.tab_category:
                        if (mCategoryFragment == null) {
                            mCategoryFragment = CategoryFragment.newInstance("CategoryFragment", null);
                        }
                        switchFragment(mCategoryFragment);
                        break;
                    case R.id.tab_cart:
                        if (mCartFragment == null) {
                            mCartFragment = TestFragment.newInstance("CartFragment", null);
                        }
                        switchFragment(mCartFragment);
                        break;
                    case R.id.tab_mine:
                        if (mMineFragment == null) {
                            mMineFragment = TestFragment.newInstance("MineFragment", null);
                        }
                        switchFragment(mMineFragment);
                        break;
                    default:
                        throw new UnsupportedOperationException("unsupported operation");
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_eshop_main;
    }

    private void retrieveFragment() {
        FragmentManager manager = getSupportFragmentManager();
        mHomeFragment = manager.findFragmentByTag("HomeFragment");
        mCategoryFragment = (CategoryFragment) manager.findFragmentByTag(CategoryFragment.class.getName());
        mCartFragment = manager.findFragmentByTag("CartFragment");
        mMineFragment = manager.findFragmentByTag("MineFragment");
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment == mCurrentFragment) {
            return;
        }
        if (mCurrentFragment != null) {
            transaction.hide(mCurrentFragment);
        }
        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            String tag;
            if (fragment instanceof TestFragment) {
                tag = ((TestFragment) fragment).getArgParam1();
            } else {
                tag = fragment.getClass().getName();
            }
            transaction.add(R.id.layout_container, fragment, tag);
        }
        transaction.commit();
        mCurrentFragment = fragment;
    }

    @Override
    public void onBackPressed() {
        if (mCurrentFragment != mHomeFragment) {
            mBottomBar.selectTabWithId(R.id.tab_home);
            return;
        }
        moveTaskToBack(true);
    }
}
