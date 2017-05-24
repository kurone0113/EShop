package com.feicuiedu.eshop_20170518.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Superclass for ListView adapter.Created by Kurone on 2017/5/24.
 */

public abstract class BaseListAdapter<T, V extends BaseListAdapter.ViewHolder> extends BaseAdapter {
    private List<T> data = new ArrayList<>();

    public void reset(List<T> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void addAll(List<T> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public T getItem(int position) {
        return data == null ? null : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        V holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(getItemLayoutId(), parent, false);
            holder = getItemHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (V) convertView.getTag();
        }
        holder.bind(position);
        return convertView;
    }

    protected abstract V getItemHolder(View view);

    @LayoutRes
    protected abstract int getItemLayoutId();

    public abstract class ViewHolder {
        View itemView;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }

        protected abstract void bind(int position);

        protected Context getContext() {
            return itemView.getContext();
        }
    }
}
