package com.colpencil.redwood.view.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author 陈宝
 * @Description:万能适配器
 * @Email DramaScript@outlook.com
 * @date 2016/7/11
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;

    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CommonViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        convert(viewHolder, getItem(position),position);
        return viewHolder.getConvertView();

    }

    public abstract void convert(CommonViewHolder helper, T item, int position);

    private CommonViewHolder getViewHolder(int position, View convertView,
                                           ViewGroup parent) {
        return CommonViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }

}
