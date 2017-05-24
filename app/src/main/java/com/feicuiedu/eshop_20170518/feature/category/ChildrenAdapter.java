package com.feicuiedu.eshop_20170518.feature.category;

import android.view.View;
import android.widget.TextView;

import com.feicuiedu.eshop_20170518.R;
import com.feicuiedu.eshop_20170518.base.BaseListAdapter;
import com.feicuiedu.eshop_20170518.network.entity.CategoryBase;

import butterknife.BindView;

/**
 * Created by Kurone on 2017/5/23.
 */

public class ChildrenAdapter extends BaseListAdapter<CategoryBase, ChildrenAdapter.ViewHolder> {

    @Override
    protected ViewHolder getItemHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_children_category;
    }

    class ViewHolder extends BaseListAdapter.ViewHolder {
        @BindView(R.id.text_category)
        TextView textCategory;
        ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(int position) {
            textCategory.setText(getItem(position).getName());
        }
    }
}
