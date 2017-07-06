package com.colpencil.redwood.function.widgets.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.colpencil.redwood.R;
import com.colpencil.redwood.bean.PostTypeInfo;
import com.colpencil.redwood.view.base.CommonAdapter;
import com.colpencil.redwood.view.base.CommonViewHolder;

import java.util.List;

public class PostDialogAdapter extends CommonAdapter<PostTypeInfo> {

    public PostDialogAdapter(Context context, List<PostTypeInfo> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(CommonViewHolder holder, PostTypeInfo item, int position) {
        TextView tv = holder.getView(R.id.post_dialog_item_tv);
        ImageView iv = holder.getView(R.id.post_dialog_item_iv);
        tv.setText(item.getTypename());
        if (item.isChoose()) {
            iv.setImageResource(R.mipmap.post_dialog_check);
        } else {
            iv.setImageResource(R.mipmap.post_dialog_uncheck);
        }
    }
}
